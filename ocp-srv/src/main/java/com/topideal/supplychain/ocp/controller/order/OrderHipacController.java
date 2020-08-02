package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.hipac.service.HipacService;
import com.topideal.supplychain.ocp.order.dto.OrderHipacDto;
import com.topideal.supplychain.ocp.order.model.OrderHipac;
import com.topideal.supplychain.ocp.order.model.OrderHipacItem;
import com.topideal.supplychain.ocp.order.service.OrderHipacItemService;
import com.topideal.supplychain.ocp.order.service.OrderHipacService;
import com.topideal.supplychain.util.AES256Util;
import java.util.List;
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
 * 海拍客订单
 *
 * @author xuxiaoyan
 * @date 2019-12-16 15:55
 */
@Controller
@RequestMapping("/orderHipac")
public class OrderHipacController extends BaseController {

    @Autowired
    private OrderHipacService orderHipacService;
    @Autowired
    private OrderHipacItemService orderHipacItemService;
    @Autowired
    private HipacService hipacService;

    /**
     * 初始化
     */
    @RequestMapping("")
    @RequiresPermissions("order.hipac")
    public String list(Model model) {
        return "order/hipac/hipac_list.html";
    }


    /**
     * 查询
     * @param filter
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @RequiresPermissions("order.hipac.query")
    @ResponseBody
    public String json(OrderHipacDto filter, @RequestParam("page") Optional<Integer> page,
            @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderHipacDto> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderHipacService.pageList(filter));
        return convertPageJson(pageInfo);

    }

    /**
     * 显示详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.hipac.detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        OrderHipac orderHipac = orderHipacService.selectById(id);
        orderHipac.setCustIdNum("**********");
        orderHipac.setCustPhone("**********");
        // 去掉多余的0
        orderHipac.setLogisticsAmount(orderHipac.getLogisticsAmount().stripTrailingZeros());
        orderHipac.setTotalTaxAmount(orderHipac.getTotalTaxAmount().stripTrailingZeros());
        orderHipac.setTotalOrderAmount(orderHipac.getTotalOrderAmount().stripTrailingZeros());
        model.addAttribute("orderId", id);
        model.addAttribute("order", orderHipac);
        return "order/hipac/hipac_detail.html";
    }


    /**
     * 显示用户敏感信息
     * @param propertyName
     * @param orderId
     * @return
     */
    @RequestMapping("/sensitiveData")
    @ResponseBody
    @RequiresPermissions("order.hipac.detail")
    public String sensitiveData(String propertyName, String orderId) {
        String sensitiveData = orderHipacService.selectSensitiveData(propertyName, Long.parseLong(orderId));
        return AES256Util.decrypt(sensitiveData);
    }

    /**
     * 商品详情查询
     */
    @RequestMapping(value = "/detail/json/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String json(@PathVariable("id") Long id, @RequestParam("page") Optional<Integer> page,
            @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderHipacItem> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderHipacItemService.selectByOrderId(id));
        return convertPageJson(pageInfo);
    }

    /**
     * 重推
     * @param ids
     * @return
     */
    @RequestMapping("/rePush")
    @ResponseBody
    public BaseResponse rePush(@RequestParam("ids[]") List<Long> ids) {
        String msg = hipacService.rePush(ids);
        return StringUtils.isEmpty(msg) ? BaseResponse.responseSuccess(getSuccessMessage()) : BaseResponse.responseFailure(msg);
    }
}
