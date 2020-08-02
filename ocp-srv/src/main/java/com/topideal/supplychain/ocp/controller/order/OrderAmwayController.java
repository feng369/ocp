package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.amway.service.AmwayService;
import com.topideal.supplychain.ocp.order.dto.OrderAmwayDto;
import com.topideal.supplychain.ocp.order.model.*;
import com.topideal.supplychain.ocp.order.service.OrderAmwayItemService;
import com.topideal.supplychain.ocp.order.service.OrderAmwayPaymentLineService;
import com.topideal.supplychain.ocp.order.service.OrderAmwayService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("orderAmway")
public class OrderAmwayController extends BaseController {
    @Autowired
    private OrderAmwayService orderAmwayService;
    @Autowired
    private OrderAmwayItemService orderAmwayItemService;
    @Autowired
    private OrderAmwayPaymentLineService orderAmwayPaymentLineService;
    @Autowired
    private AmwayService amwayService;
    /**
     * 加载主页面
     */
    @RequestMapping("")
    @RequiresPermissions("order.amway")
    public String list(Model model) {

        return "order/amway/amway_list.html";
    }

    /**
     * 主页面查询
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @RequiresPermissions("order.amway.query")
    @ResponseBody
    public String json(OrderAmwayDto filter, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderAmwayDto> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderAmwayService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 跳转到详情页面
     */
    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.amway.detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("orderId", id);
        List<OrderAmwayPaymentLine> amwayPaymentLineList = orderAmwayPaymentLineService.selectByOrderId(id);
        OrderAmwayPaymentLine payline = amwayPaymentLineList.get(0);
        model.addAttribute("payline",payline);
        model.addAttribute("paymentLineAmount",payline.getPaymentLineAmount().stripTrailingZeros().toPlainString());
        return "order/amway/amway_detail.html";
    }

    /**
     * 订单详情查询
     */
    @RequestMapping(value = "/detail/json/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String json(@PathVariable("id") Long id, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows,Model model) {
        PageInfo<OrderAmwayItem> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderAmwayItemService.selectByOrderId(id));

        return convertPageJson(pageInfo);
    }


    /**
     * 重新推单
     */
    @RequestMapping("/rePush")
    @ResponseBody
    @RequiresPermissions("order.amway.repush")
    public BaseResponse rePush(@RequestParam("ids[]") Long [] ids) {
        List<OrderAmway> orderAmwayList = orderAmwayService.selectByIds(ids);
        BusinessAssert.assertNotEmpty(orderAmwayList,"所选订单不存在");
        return amwayService.rePush(orderAmwayList);
    }
}
