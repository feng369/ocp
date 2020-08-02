package com.topideal.supplychain.ocp.controller.order;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.daling.service.DalingService;
import com.topideal.supplychain.ocp.enums.DaLingOrderStatusEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.order.dto.OrderDalingDto;
import com.topideal.supplychain.ocp.order.model.OrderDaling;
import com.topideal.supplychain.ocp.order.model.OrderDetailDaling;
import com.topideal.supplychain.ocp.order.service.OrderDalingService;
import com.topideal.supplychain.util.AES256Util;
import com.topideal.supplychain.util.JacksonUtils;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 达令家
 *
 * @author xuxiaoyan
 * @date 2019-11-29 16:45
 */
@Controller
@RequestMapping("/orderDaling")
public class OrderDalingController extends BaseController {

    @Autowired
    private OrderDalingService orderDalingService;
    @Autowired
    private DalingService dalingService;

    /**
     * 初始化
     */
    @RequestMapping("")
    @RequiresPermissions("order.daling")
    public String list(Model model) {
        return "order/daling/daling_list.html";
    }


    /**
     * 查询
     *
     * @param filter
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @RequiresPermissions("order.daling.query")
    @ResponseBody
    public String json(OrderDalingDto filter, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderDalingDto> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderDalingService.pageList(filter));
        return convertPageJson(pageInfo);

    }

    /**
     * 显示详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.daling.detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        OrderDaling orderDaling = orderDalingService.selectById(id);
        model.addAttribute("orderId", id);
        model.addAttribute("order", orderDaling);
        return "order/daling/daling_detail.html";
    }

    /**
     * 显示用户敏感信息
     *
     * @param value
     * @return
     */
    @RequestMapping("/sensitiveData")
    @ResponseBody
    @RequiresPermissions("order.daling.detail")
    public String sensitiveData(String value) {
        return value == null ? "" : AES256Util.decrypt(value);
    }

    /**
     * 商品详情查询
     */
    @RequestMapping(value = "/detail/json/{id}", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("order.daling.detail")
    public String json(@PathVariable("id") String id) {
        String infoDate = orderDalingService.selectInfoDataWithId(Long.parseLong(id));
        OrderDetailDaling detailDaling = JacksonUtils.readValue(infoDate, OrderDetailDaling.class);
        return JacksonUtils.toJSon(detailDaling.getOrderItem());

    }

    /**
     * 重推订单
     *
     * @param ids
     * @return
     */
    @RequestMapping({"/rePush"})
    @ResponseBody
    @RequiresPermissions("order.daling.repush")
    public BaseResponse rePush(@RequestParam(value = "ids[]") Long[] ids) {
        BusinessAssert.assertNotEmpty(ids, "请选择需要重推的数据！");
        String msg = dalingService.rePush(ids);
        return StringUtils.isEmpty(msg) ? BaseResponse.responseSuccess(getSuccessMessage()) : BaseResponse.responseFailure(msg);
    }

    /**
     * 手工抓取详细订单
     *
     * @param id
     * @return
     */
    @RequestMapping({"/grabDetail"})
    @ResponseBody
    @RequiresPermissions("order.daling.grab")
    public BaseResponse grabDetail(@RequestParam(value = "id") Long id) {
        OrderDaling orderDaling = orderDalingService.selectById(id);
        if(orderDaling == null){
            return BaseResponse.responseFailure("订单不存在");
        }
        // 有效且状态为制单
        boolean flag = OrderStatusEnum.INIT.equals(orderDaling.getOrderStatus())&& DaLingOrderStatusEnum.VALID
                        .equals(orderDaling.getStatus());
        if (!flag) {
            return BaseResponse.responseFailure("订单状态不为制单中或者平台订单状态不为有效!");
        }
        try {
            dalingService.grabDetail(orderDaling);
        } catch (Exception e) {
            return BaseResponse.responseFailure(e.getMessage());
        }
        return BaseResponse.responseSuccess(getSuccessMessage());
    }
}
