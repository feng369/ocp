package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.kjb.service.KjbService;
import com.topideal.supplychain.ocp.order.model.OrderKjb;
import com.topideal.supplychain.ocp.order.model.OrderKjbItem;
import com.topideal.supplychain.ocp.order.service.OrderKjbItemService;
import com.topideal.supplychain.ocp.order.service.OrderKjbService;
import com.topideal.supplychain.util.AES256Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 跨境宝订单
 * @author syq
 * @date 2019-12-04 16:20
 */
@Controller
@RequestMapping("orderKjb")
public class OrderKjbController extends BaseController {

    @Autowired
    private OrderKjbService orderKjbService;
    @Autowired
    private OrderKjbItemService orderKjbItemService;
    @Autowired
    private KjbService kjbService;

    /**
     * 初始化
     * @return
     */
    @RequestMapping(value = "")
    public String list() {
        return "order/kjb/orderKjb_list.html";
    }


    /**
     * 页面查询
     * @param orderKjb
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("order.kjb.query")
    public String json(@ModelAttribute("orderKjb") OrderKjb orderKjb, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderKjb> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderKjbService.pageList(orderKjb));
        List<OrderKjb> orderKjbList = pageInfo.getList();
        for (OrderKjb order : orderKjbList) {
            //解密发货人收件人姓名、电话、地址等信息
            decryptInfo(order);
        }
        return convertPageJson(pageInfo);
    }


    /**
     * 跳转到详情页面
     */
    @RequestMapping("/showDetail/{id}")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        OrderKjb orderKjb = orderKjbService.selectById(id);
        //解密发货人收件人姓名、电话、地址等信息
        decryptInfo(orderKjb);
        model.addAttribute("orderKjb", orderKjb);
        return "order/kjb/orderKjb_detail.html";
    }

    /**
     * 解密发货人收件人姓名、电话、地址等信息用于页面显示
     * @param orderKjb
     */
    private void decryptInfo(OrderKjb orderKjb) {
        if (StringUtils.isNotEmpty(orderKjb.getSenderName())) {
            orderKjb.setSenderName(AES256Util.decrypt(orderKjb.getSenderName()));
        }
        if (StringUtils.isNotEmpty(orderKjb.getSenderPhone())) {
            orderKjb.setSenderPhone(AES256Util.decrypt(orderKjb.getSenderPhone()));
        }
        if (StringUtils.isNotEmpty(orderKjb.getSenderMobile())) {
            orderKjb.setSenderMobile(AES256Util.decrypt(orderKjb.getSenderMobile()));
        }
        if (StringUtils.isNotEmpty(orderKjb.getSenderAddress())) {
            orderKjb.setSenderAddress(AES256Util.decrypt(orderKjb.getSenderAddress()));
        }
        if (StringUtils.isNotEmpty(orderKjb.getReceiverName())) {
            orderKjb.setReceiverName(AES256Util.decrypt(orderKjb.getReceiverName()));
        }
        if (StringUtils.isNotEmpty(orderKjb.getReceiverMobile())) {
            orderKjb.setReceiverMobile(AES256Util.decrypt(orderKjb.getReceiverMobile()));
        }
        if (StringUtils.isNotEmpty(orderKjb.getReceiverPhone())) {
            orderKjb.setReceiverPhone(AES256Util.decrypt(orderKjb.getReceiverPhone()));
        }
        if (StringUtils.isNotEmpty(orderKjb.getReceiverAddress())) {
            orderKjb.setReceiverAddress(AES256Util.decrypt(orderKjb.getReceiverAddress()));
        }
        if (StringUtils.isNotEmpty(orderKjb.getReceiverIdentityNo())) {
            orderKjb.setReceiverIdentityNo(AES256Util.decrypt(orderKjb.getReceiverIdentityNo()));
        }
        if (StringUtils.isNotEmpty(orderKjb.getBuyerName())) {
            orderKjb.setBuyerName(AES256Util.decrypt(orderKjb.getBuyerName()));
        }
        if (StringUtils.isNotEmpty(orderKjb.getBuyerPhone())) {
            orderKjb.setBuyerPhone(AES256Util.decrypt(orderKjb.getBuyerPhone()));
        }
        if (StringUtils.isNotEmpty(orderKjb.getBuyerIdentityNo())) {
            orderKjb.setBuyerIdentityNo(AES256Util.decrypt(orderKjb.getBuyerIdentityNo()));
        }
    }

    /**
     * 订单明细详情查询
     */
    @RequestMapping(value = "/detail/json/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String json(@PathVariable("id") Long id, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderKjbItem> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderKjbItemService.selectByOrderId(id));
        return convertPageJson(pageInfo);
    }

    /**
     * 重推订单至esd
     * @param ids
     * @return
     */
    @RequestMapping("/repushEsd")
    @ResponseBody
    @RequiresPermissions("order.kjb.repushEsd")
    public BaseResponse rePush(@RequestParam("ids[]") List<Long> ids) {
        BusinessAssert.assertNotEmpty(ids,"请选择需要重推的数据");
        kjbService.repushEsd(ids);
        return BaseResponse.responseSuccess(getSuccessMessage());
    }
}
