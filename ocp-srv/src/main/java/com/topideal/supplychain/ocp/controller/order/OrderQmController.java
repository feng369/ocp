package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.order.model.OrderQm;
import com.topideal.supplychain.ocp.order.model.OrderQmItem;
import com.topideal.supplychain.ocp.order.service.OrderQmItemService;
import com.topideal.supplychain.ocp.order.service.OrderQmService;
import com.topideal.supplychain.util.InfoEncryptUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 商品信息登记
 * @author syq
 * @date 2019-12-04 16:20
 */
@Controller
@RequestMapping("orderQm")
public class OrderQmController extends BaseController {

    @Autowired
    private MessageSender messageSender;
    @Autowired
    private OrderQmService orderQmService;
    @Autowired
    private OrderQmItemService orderQmItemService;

    /**
     * 初始化
     * @return
     */
    @RequestMapping(value = "")
    public String list() {
        return "order/qm/orderQm_list.html";
    }


    /**
     * 页面查询
     * @param orderQm
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("order.qm.query")
    public String json(@ModelAttribute("orderQm") OrderQm orderQm, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderQm> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderQmService.pageList(orderQm));
        for (OrderQm order : pageInfo.getList()) {
            order.setSenderMobile(InfoEncryptUtil.encryptPhoneOrMobileX(order.getSenderMobile()));
            order.setReceiverMobile(InfoEncryptUtil.encryptPhoneOrMobileX(order.getReceiverMobile()));
            order.setSenderName(InfoEncryptUtil.encryptNameX(order.getSenderName()));
            order.setReceiverName(InfoEncryptUtil.encryptNameX(order.getReceiverName()));
            order.setSenderDetailAddress(InfoEncryptUtil.encryptAddressX(order.getSenderDetailAddress()));
            order.setReceiverDetailAddress(InfoEncryptUtil.encryptAddressX(order.getReceiverDetailAddress()));
        }
        return convertPageJson(pageInfo);
    }


    /**
     * 跳转到详情页面
     */
    @RequestMapping("/showDetail/{id}")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        OrderQm orderQm = orderQmService.selectById(id);
        //加密发货人收件人姓名、电话、地址等信息
        orderQm.setSenderMobile(InfoEncryptUtil.encryptPhoneOrMobileX(orderQm.getSenderMobile()));
        orderQm.setSenderTel(InfoEncryptUtil.encryptPhoneOrMobileX(orderQm.getSenderTel()));
        orderQm.setSenderName(InfoEncryptUtil.encryptNameX(orderQm.getSenderName()));
        orderQm.setSenderDetailAddress(InfoEncryptUtil.encryptAddressX(orderQm.getSenderDetailAddress()));
        orderQm.setReceiverMobile(InfoEncryptUtil.encryptPhoneOrMobileX(orderQm.getReceiverMobile()));
        orderQm.setReceiverTel(InfoEncryptUtil.encryptPhoneOrMobileX(orderQm.getReceiverTel()));
        orderQm.setReceiverName(InfoEncryptUtil.encryptNameX(orderQm.getReceiverName()));
        orderQm.setReceiverDetailAddress(InfoEncryptUtil.encryptAddressX(orderQm.getReceiverDetailAddress()));
        model.addAttribute("orderQm", orderQm);
        return "order/qm/orderQm_detail.html";
    }

    /**
     * 订单明细详情查询
     */
    @RequestMapping(value = "/detail/json/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String json(@PathVariable("id") Long id, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderQmItem> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderQmItemService.selectByOrderId(id));
        return convertPageJson(pageInfo);
    }

    /**
     * 重推订单至esd
     * @param ids
     * @return
     */
    @RequestMapping("/repushEsd")
    @ResponseBody
    @RequiresPermissions("order.qm.repushEsd")
    public BaseResponse rePush(@RequestParam("ids[]") List<Long> ids) {
        return BaseResponse.responseSuccess(getSuccessMessage());
    }
}
