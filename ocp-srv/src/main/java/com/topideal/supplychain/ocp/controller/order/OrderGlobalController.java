package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.global.service.GlobalService;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.order.dto.OrderGlobalDto;
import com.topideal.supplychain.ocp.order.model.OrderGlobal;
import com.topideal.supplychain.ocp.order.model.OrderGlobalItem;
import com.topideal.supplychain.ocp.order.service.OrderGlobalItemService;
import com.topideal.supplychain.ocp.order.service.OrderGlobalService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>标题:全球仓订单前端交互</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.controller.order</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/11/27 18:13</p>
 *
 * @version 1.0
 */
@RequestMapping("/orderGlobal")
@Controller
public class OrderGlobalController extends BaseController {

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private GlobalService globalService;
    @Autowired
    private OrderGlobalService orderGlobalService;
    @Autowired
    private OrderGlobalItemService globalItemService;
    @Autowired
    private PlatformService platformService;


    /**
     * 加载主页面
     */
    @RequestMapping("")
    @RequiresPermissions("order.global")
    public String list(Model model) {
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        return "order/global/global_list.html";
    }

    /**
     * 主页面查询
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @RequiresPermissions("order.global.list")
    @ResponseBody
    public String json(OrderGlobalDto filter, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderGlobal> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderGlobalService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 跳转到详情页面
     */
    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.global.detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("orderId", id);
        return "order/global/global_detail.html";
    }

    /**
     * 订单详情查询
     */
    @RequestMapping(value = "/detail/json/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String json(@PathVariable("id") Long id) {
        List<OrderGlobalItem> orderGlobalItems = globalItemService.selectByOrderId(id);
        return convertPageJson(orderGlobalItems, 0L);
    }

    @RequestMapping("/rePush")
    @ResponseBody
    public BaseResponse rePush(@RequestParam("ids[]") List<Long> ids) {
        String msg = globalService.rePush(ids);
        return StringUtils.isEmpty(msg) ? BaseResponse.responseSuccess(getSuccessMessage()) : BaseResponse.responseFailure(msg);
    }
}
