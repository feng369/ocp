package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.dxy.service.DxyService;
import com.topideal.supplychain.ocp.order.dto.OrderDxyDto;
import com.topideal.supplychain.ocp.order.model.OrderDxy;
import com.topideal.supplychain.ocp.order.model.OrderDxyItem;
import com.topideal.supplychain.ocp.order.service.OrderDxyItemService;
import com.topideal.supplychain.ocp.order.service.OrderDxyService;
import com.topideal.supplychain.util.DateUtils;
import java.util.Calendar;
import java.util.Date;
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
 * 丁香医生订单-页面控制层
 *
 * @author xuxiaoyan
 * @date 2020-05-21 15:36
 */
@Controller
@RequiresPermissions("order.dxy")
@RequestMapping("/orderDxy")
public class OrderDxyController extends BaseController {

    @Autowired
    private OrderDxyService orderDxyService;
    @Autowired
    private OrderDxyItemService orderDxyItemService;
    @Autowired
    private DxyService dxyService;


    /**
     * 加载主页面
     */
    @RequestMapping("")
    @RequiresPermissions("order.dxy")
    public String list(Model model) {
       /* model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        model.addAttribute("logisticsList", logisticsService.findAll());
       */ // 系统创建时间 默认七天前0点
        Date tempDate = DateUtils.addWeeks(new Date(), -1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(tempDate);
        // 将时分秒,毫秒域清零
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        String createTimeStart = DateUtils.dateToString(cal.getTime(), DateUtils.DATETIME_PATTERN);
        model.addAttribute("createTimeStart", createTimeStart);
        return "order/dxy/dxy_list.html";
    }


    /**
     * 主页面查询
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @RequiresPermissions("order.dxy.query")
    @ResponseBody
    public String json(OrderDxyDto filter,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("rows") Optional<Integer> rows) {
        // 组装页面查询条件
        fillCondition(filter);
        PageInfo<OrderDxy> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderDxyService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 平台订单号，运单号支持批量查询； 精准查询忽略时间条件
     */
    private void fillCondition(OrderDxyDto filter) {
        String orderIds = filter.getOrderIds();
        if (StringUtils.isNotEmpty(orderIds)) {
            orderIds = orderIds.replaceAll("(\r\n|\r|\n|\n\r|，)", ",");
            String[] orderIdArray = orderIds.split(",");
            BusinessAssert.assertIsFalse(orderIdArray.length > 1000, "批量查询单数不能大于1000");
            filter.setOrderIdList(orderIdArray);

            filter.setCreateTimeStart(null);
            filter.setCreateTimeEnd(null);
            filter.setOrderCreateTimeEnd(null);
            filter.setOrderCreateTimeStart(null);
        }

        String codes = filter.getCodes();
        if (StringUtils.isNotEmpty(codes)) {
            codes = codes.replaceAll("(\r\n|\r|\n|\n\r|，)", ",");
            String[] codeArray = codes.split(",");
            BusinessAssert.assertIsFalse(codeArray.length > 1000, "批量查询单数不能大于1000");
            filter.setCodeList(codeArray);

            filter.setCreateTimeStart(null);
            filter.setCreateTimeEnd(null);
            filter.setOrderCreateTimeEnd(null);
            filter.setOrderCreateTimeStart(null);
        }
    }

    /**
     * 跳转到详情页面
     */
    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.dxy.detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        OrderDxy orderDxy = orderDxyService.selectById(id);

        model.addAttribute("realPayAmount",
                orderDxy.getRealPayAmount().stripTrailingZeros().toPlainString());
        model.addAttribute("tax", orderDxy.getTaxAmount().stripTrailingZeros().toPlainString());
        model.addAttribute("freight",
                orderDxy.getFreightAmount().stripTrailingZeros().toPlainString());
        model.addAttribute("discount",
                orderDxy.getDiscountAmount().stripTrailingZeros().toPlainString());
        model.addAttribute("order", orderDxy);
        model.addAttribute("mainId", id);
        return "order/dxy/dxy_detail.html";
    }

    /**
     * 订单详情查询
     */
    @RequestMapping(value = "/detail/json/{id}", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("order.dxy.detail")
    public String json(@PathVariable("id") Long id, @RequestParam("page") Optional<Integer> page,
            @RequestParam("rows") Optional<Integer> rows, Model model) {
        PageInfo<OrderDxyItem> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderDxyItemService.selectByOrderId(id));

        return convertPageJson(pageInfo);
    }


    /**
     * 重推订单
     */
    @RequestMapping("/rePush")
    @ResponseBody
    @RequiresPermissions("order.dxy.repush")
    public BaseResponse rePush(@RequestParam("ids[]") List<Long> ids) {
        String msg = dxyService.rePush(ids);
        return StringUtils.isEmpty(msg) ? BaseResponse.responseSuccess(getSuccessMessage())
                : BaseResponse.responseFailure(msg);
    }

}
