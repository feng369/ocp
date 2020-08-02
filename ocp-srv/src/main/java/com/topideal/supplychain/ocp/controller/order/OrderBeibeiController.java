package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.beibei.service.BeibeiService;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.order.dto.OrderBeibeiDto;
import com.topideal.supplychain.ocp.order.model.OrderBeibeiItem;
import com.topideal.supplychain.ocp.order.model.OrderYmatou;
import com.topideal.supplychain.ocp.order.service.OrderBeibeiItemService;
import com.topideal.supplychain.ocp.order.service.OrderBeibeiService;
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
 * @date 2020/3/28
 * @description 贝贝订单 控制层
 **/
@Controller
@RequestMapping("orderBeibei")
public class OrderBeibeiController extends BaseController {
    @Autowired
    private OrderBeibeiService orderBeibeiService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private OrderBeibeiItemService orderBeibeiItemService;
    @Autowired
    private BeibeiService beibeiService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PlatformService platformService;

    @RequestMapping(value = "")
    @RequiresPermissions("order.beibei")
    public String list(Model model) {
        model.addAttribute("merchantList", merchantService.findAll());
        return "order/beibei/beibei_list";
    }


    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("order.beibei.query")
    public String json(OrderBeibeiDto filter, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderYmatou> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderBeibeiService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 跳至详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.beibei.detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("orderId", id);
        return "order/beibei/beibei_detail";
    }

    /**
     * 订单详情查询
     */
    @RequestMapping(value = "/detail/json/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String jsonDetail(@PathVariable("id") Long id, @RequestParam("page") Optional<Integer> page,
                             @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderBeibeiItem> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderBeibeiItemService.selectAllByOrderId(id));
        return convertPageJson(pageInfo);
    }

    @RequestMapping("/rePush")
    @ResponseBody
    @RequiresPermissions("order.beibei.repush")
    public BaseResponse rePush(@RequestParam("ids[]") List<Long> ids) {
        BusinessAssert.assertNotEmpty(ids, "请选择需要重推的数据");
        return beibeiService.rePush(ids);
    }

    /**
     * 指定订单抓取
     */
    @ResponseBody
    @RequestMapping("/oneGrab")
    @RequiresPermissions("order.beibei.grab")
    public BaseResponse oneGrab(String orderId, Long storeId) {
        return beibeiService.grabOnlyOrder(orderId, storeId);
    }

    /**
     * 跳转至单票抓取页面
     * 根据平台虚拟编码拿到洋码头平台
     *
     * @return
     */
    @RequestMapping("/toGrab")
    @RequiresPermissions("order.beibei.grab")
    public String toGrab(ModelMap map) {
        List<Store> mdStoreList = new ArrayList<>();
        List<Platform> platforms = platformService.findByVirtualCode(PlatformEnum.BEIBEI.getCode());
        mdStoreList = storeService.findByPlatforms(platforms);
        map.put("storeList", mdStoreList);
        return "order/beibei/beibei_grab";
    }
}
