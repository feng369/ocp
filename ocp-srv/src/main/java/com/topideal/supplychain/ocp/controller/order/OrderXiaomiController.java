package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.order.dto.OrderXiaomiDto;
import com.topideal.supplychain.ocp.order.model.OrderYmatouItem;
import com.topideal.supplychain.ocp.order.service.OrderXiaomiGoodsService;
import com.topideal.supplychain.ocp.order.service.OrderXiaomiService;
import com.topideal.supplychain.ocp.xiaomi.service.XiaomiService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author wanzhaozhang
 * @date 2019/11/28
 * @description 洋码头订单 控制层
 **/
@Controller
@RequestMapping("orderXiaomi")
public class OrderXiaomiController extends BaseController {

    @Autowired
    private OrderXiaomiService orderXiaomiService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private OrderXiaomiGoodsService orderXiaomiGoodsService;
    @Autowired
    private XiaomiService xiaomiService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PlatformService platformService;

    @RequestMapping(value = "")
    @RequiresPermissions("order.xiaomi")
    public String list(Model model) {
        model.addAttribute("merchantList", merchantService.findAll());
        return "order/xiaomi/xiaomi_list";
    }


    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("order.xiaomi.query")
    public String json(OrderXiaomiDto filter, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderXiaomiDto> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderXiaomiService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 跳至详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.xiaomi.detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("orderId", id);
        return "order/xiaomi/xiaomi_detail";
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
                        () -> orderXiaomiGoodsService.selectAllByOrderId(id));
        return convertPageJson(pageInfo);
    }

    @RequestMapping("/rePush")
    @ResponseBody
    @RequiresPermissions("order.xiaomi.repush")
    public BaseResponse rePush(@RequestParam("ids[]") List<Long> ids) {
        BusinessAssert.assertNotEmpty(ids, "请选择需要重推的数据");
        return xiaomiService.rePush(ids);
    }

    /**
     * 单票订单抓取
     */
    @ResponseBody
    @RequestMapping("/oneGrab")
    @RequiresPermissions("order.xiaomi.grab")
    public BaseResponse oneGrab(String orderId, Long storeId) {
        return xiaomiService.grabOnlyOrder(orderId, storeId);
    }

    /**
     * 跳转至单票抓取页面
     * 根据平台虚拟编码拿到洋码头平台
     *
     * @return
     */
    @RequestMapping("/toGrab")
    @RequiresPermissions("order.xiaomi.grab")
    public String toGrab(ModelMap map) {
        List<Store> mdStoreList = new ArrayList<>();
        List<Platform> platforms = platformService.findByVirtualCode(PlatformEnum.XIAOMI.getCode());
        mdStoreList = storeService.findByPlatforms(platforms);
        map.put("storeList", mdStoreList);
        return "order/xiaomi/xiaomi_grab";
    }
}
