package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import com.topideal.supplychain.ocp.order.dto.OrderVipDto;
import com.topideal.supplychain.ocp.order.model.OrderVipItem;
import com.topideal.supplychain.ocp.order.model.OrderVip;
import com.topideal.supplychain.ocp.order.service.OrderVipItemService;
import com.topideal.supplychain.ocp.order.service.OrderVipService;
import com.topideal.supplychain.ocp.vip.service.VipService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>标题: 唯品会订单前端交互</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.controller.order</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/11/27 18:13</p>
 *
 * @version 1.0
 */
@RequestMapping("/orderVip")
@Controller
public class OrderVipController extends BaseController {

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private OrderVipService orderVipService;
    @Autowired
    private OrderVipItemService orderVipItemService;
    @Autowired
    private VipService vipService;

    /**
     * 加载主页面
     */
    @RequestMapping("")
    @RequiresPermissions("order.vip")
    public String list(Model model) {
        model.addAttribute("merchantList", merchantService.findAll());
        return "order/vip/vip_list.html";
    }

    /**
     * 主页面查询
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @RequiresPermissions("order.vip.list")
    @ResponseBody
    public String json(OrderVipDto filter, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderVip> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderVipService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 跳转到详情页面
     */
    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.vip.detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("orderId", id);
        return "order/vip/vip_detail.html";
    }

    /**
     * 订单详情查询
     */
    @RequestMapping(value = "/detail/json/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String json(@PathVariable("id") Long id, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderVipItem> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderVipItemService.selectByOrderId(id));
        return convertPageJson(pageInfo);
    }

    @RequestMapping("/rePush")
    @ResponseBody
    public BaseResponse rePush(@RequestParam("ids[]") List<Long> ids) {
        String msg = vipService.rePush(ids);
        return StringUtils.isEmpty(msg) ? BaseResponse.responseSuccess(getSuccessMessage()) : BaseResponse.responseFailure(msg);
    }
}
