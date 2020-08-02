package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.controller.ExcelExportController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.export.ExportContext;
import com.topideal.supplychain.export.converter.DateConverter;
import com.topideal.supplychain.export.converter.EnumConverter;
import com.topideal.supplychain.export.converter.RateConverter;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.enums.YouZanOrderNewEnum;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.order.model.OrderYmatou;
import com.topideal.supplychain.ocp.order.model.OrderYmatouItem;
import com.topideal.supplychain.ocp.order.service.OrderYmatouItemService;
import com.topideal.supplychain.ocp.order.service.OrderYmatouService;
import com.topideal.supplychain.ocp.ymatou.service.YmatouService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author wanzhaozhang
 * @date 2019/11/28
 * @description 洋码头订单 控制层
 **/
@Controller
@RequestMapping("orderYmatou")
public class OrderYmatouController extends ExcelExportController<OrderYmatou> {

    @Autowired
    private OrderYmatouService orderYmatouService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private YmatouService ymatouService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private OrderYmatouItemService itemService;

    @RequestMapping(value = "")
    @RequiresPermissions("order.ymatou")
    public String list(Model model) {
        model.addAttribute("merchantList", merchantService.findAll());
        return "order/ymatou/ymatou_list";
    }


    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("order.ymatou.query")
    public String json(OrderYmatou filter, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderYmatou> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderYmatouService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 跳至详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.ymatou.detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("orderId", id);
        return "order/ymatou/ymatou_detail";
    }

    /**
     * 订单详情查询
     */
    @RequestMapping(value = "/detail/json/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String jsonDetail(@PathVariable("id") Long id, @RequestParam("page") Optional<Integer> page,
                             @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderYmatouItem> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> itemService.selectByOrderIdAndStatus(id, YouZanOrderNewEnum.OLD.getValue()));
        return convertPageJson(pageInfo);
    }

    /**
     * 单票订单抓取
     */
    @ResponseBody
    @RequestMapping("/oneGrab")
    @RequiresPermissions("order.ymatou.grab")
    public BaseResponse oneGrab(String orderId, Long storeId) {
        BaseResponse response = ymatouService.grabOnlyOrder(orderId, storeId);
        if (response.isSuccess()) {
            return BaseResponse.responseSuccess("抓取成功");
        } else {
            return BaseResponse.responseFailure("抓取失败");
        }

    }

    /**
     * 跳转至单票抓取页面
     * 根据平台虚拟编码拿到洋码头平台
     *
     * @return
     */
    @RequestMapping("/toGrab")
    @RequiresPermissions("order.ymatou.grab")
    public String toGrab(ModelMap map) {
        List<Store> mdStoreList = new ArrayList<>();
        List<Platform> platforms = platformService.findByVirtualCode(PlatformEnum.YMATOU.getCode());
        mdStoreList = storeService.findByPlatforms(platforms);
        map.put("storeList", mdStoreList);
        return "order/ymatou/ymatou_grab";
    }

    @RequestMapping("/rePush")
    @ResponseBody
    @RequiresPermissions("order.ymatou.repush")
    public BaseResponse rePush(@RequestParam("ids[]") List<Long> ids){
        BusinessAssert.assertNotEmpty(ids,"请选择需要重推的数据");
        return ymatouService.rePush(ids);
    }

    @Override
    public void setExportContext(OrderYmatou condition, ExportContext context) {
        List<OrderYmatou> workOrderDtoList = orderYmatouService.pageList(condition);
        DateConverter dateConverter = new DateConverter();
        EnumConverter enumConverter = new EnumConverter();
        context.setDataList(workOrderDtoList).setFileName("物流工单导出表")
                .addPropertyParser("工单编号", "code")
                .addPropertyParser("工单编号", "sendStatus",enumConverter)
                .addPropertyParser("创建时间","createTime",dateConverter)
                .addPropertyParser("支付金额","shippingFee",new RateConverter(100D))
                .addPropertyParser("状态","orderStatus",new RateConverter(10D));

    }
}
