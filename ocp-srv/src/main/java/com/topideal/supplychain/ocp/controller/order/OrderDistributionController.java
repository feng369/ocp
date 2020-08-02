package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.distribution.service.DistributionService;
import com.topideal.supplychain.ocp.master.service.LogisticsService;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.order.dto.OrderDistributionPageRequestDto;
import com.topideal.supplychain.ocp.order.model.OrderDistribution;
import com.topideal.supplychain.ocp.order.service.OrderDistributionService;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 分销订单-页面控制层
 *
 * @author xuxiaoyan
 * @date 2020-05-21 15:36
 */
@Controller
@RequestMapping("/orderDistribution")
public class OrderDistributionController extends BaseController {

    @Autowired
    private PlatformService platformService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private LogisticsService logisticsService;
    @Autowired
    private OrderDistributionService orderDistributionService;
    @Autowired
    private DistributionService distributionService;


    /**
     * 加载主页面
     */
    @RequestMapping("")
    @RequiresPermissions("order.distribution")
    public String list(Model model) {
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        model.addAttribute("logisticsList", logisticsService.findAll());
        // 系统创建时间 默认七天前0点
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
        return "order/distribution/distribution_list.html";
    }


    /**
     * 主页面查询
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @RequiresPermissions("order.distribution.query")
    @ResponseBody
    public String json(OrderDistributionPageRequestDto filter,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("rows") Optional<Integer> rows) {
        // 组装页面查询条件
        fillCondition(filter);
        PageInfo<OrderDistribution> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderDistributionService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 平台订单号，运单号支持批量查询； 精准查询忽略时间条件
     */
    private void fillCondition(OrderDistributionPageRequestDto filter) {
        String orderIds = filter.getOrderIds();
        if (StringUtils.isNotEmpty(orderIds)) {
            orderIds = orderIds.replaceAll("(\r\n|\r|\n|\n\r|，)", ",");
            String[] orderIdArray = orderIds.split(",");
            BusinessAssert.assertIsFalse(orderIdArray.length > 1000, "批量查询单数不能大于1000");
            List<String> orderIdList = (List<String>) CollectionUtils.arrayToList(orderIdArray);
            filter.setOrderIdList(orderIdList);

            filter.setCreateTimeStart(null);
            filter.setCreateTimeEnd(null);
            filter.setOrderDateStart(null);
            filter.setOrderDateEnd(null);
        }

        String deliveryCodes = filter.getDeliveryCodes();
        if (StringUtils.isNotEmpty(deliveryCodes)) {
            deliveryCodes = deliveryCodes.replaceAll("(\r\n|\r|\n|\n\r|，)", ",");
            String[] deliveryCodeArray = deliveryCodes.split(",");
            BusinessAssert.assertIsFalse(deliveryCodeArray.length > 1000, "批量查询单数不能大于1000");
            List<String> deliveryCodeList = (List<String>) CollectionUtils.arrayToList(deliveryCodeArray);
            filter.setDeliveryCodeList(deliveryCodeList);

            filter.setCreateTimeStart(null);
            filter.setCreateTimeEnd(null);
            filter.setOrderDateStart(null);
            filter.setOrderDateEnd(null);
        }
    }

    /**
     * 重推订单
     */
    @RequestMapping("/rePush")
    @ResponseBody
    @RequiresPermissions("order.distribution.repush")
    public BaseResponse rePush(@RequestParam("ids[]") List<Long> ids) {
        String msg = distributionService.rePush(ids);
        return StringUtils.isEmpty(msg) ? BaseResponse.responseSuccess(getSuccessMessage()) : BaseResponse.responseFailure(msg);
    }

}
