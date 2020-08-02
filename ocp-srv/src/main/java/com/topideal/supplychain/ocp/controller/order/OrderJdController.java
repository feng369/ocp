package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.YesOrNoEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.jd.service.JdService;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.order.dto.OrderAmwayDto;
import com.topideal.supplychain.ocp.order.dto.OrderJdDto;
import com.topideal.supplychain.ocp.order.model.OrderAmwayItem;
import com.topideal.supplychain.ocp.order.model.OrderAmwayPaymentLine;
import com.topideal.supplychain.ocp.order.model.OrderJd;
import com.topideal.supplychain.ocp.order.model.OrderJdItem;
import com.topideal.supplychain.ocp.order.model.OrderYouzan;
import com.topideal.supplychain.ocp.order.service.OrderJdItemService;
import com.topideal.supplychain.ocp.order.service.OrderJdService;
import com.topideal.supplychain.util.AES128Util;
import com.topideal.supplychain.util.AES256Util;
import com.topideal.supplychain.util.DateUtils;
import java.util.Calendar;
import java.util.Date;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("orderJd")
public class OrderJdController extends BaseController {

    @Autowired
    private OrderJdService orderJdService;

    @Autowired
    private OrderJdItemService orderJdItemService;

    @Autowired
    private JdService jdService;

    /**
     * 加载主页面
     */
    @RequestMapping("")
    @RequiresPermissions("order.jd")
    public String list(Model model) {
        // 系统创建时间 默认当天0点
        Date tempDate = DateUtils.getTodayDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(tempDate);
        // 将时分秒,毫秒域清零
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        String createTimeStart = DateUtils.dateToString(cal.getTime(), DateUtils.DATETIME_PATTERN);
        model.addAttribute("createTimeStart", createTimeStart);
        return "order/jd/jd_list.html";
    }

    /**
     * 主页面查询
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @RequiresPermissions("order.jd.query")
    @ResponseBody
    public String json(OrderJdDto filter, @RequestParam("page") Optional<Integer> page,
            @RequestParam("rows") Optional<Integer> rows) {
        // 查询条件优化
        checkFilter(filter);
        PageInfo<OrderJd> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderJdService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 单号查询忽略时间条件
     * @param filter
     */
    private void checkFilter(OrderJdDto filter) {
        if (!StringUtils.isEmpty(filter.getCode()) || !StringUtils.isEmpty(filter.getOrderId())
                || !StringUtils.isEmpty(filter.getLogisticsNo())) {
            filter.setCreateTimeStart(null);
            filter.setCreateTimeEnd(null);
            filter.setOrderCreateTimeStart(null);
            filter.setOrderCreateTimeEnd(null);
        }
    }


    /**
     * 跳转到详情页面
     */
    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.jd.detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        OrderJd orderJd = orderJdService.selectById(id);
        /*orderJd.setBuyerIdNumber("**********");
        orderJd.setBuyerPhone("**********");
        orderJd.setConsigneePhone("**********");
        orderJd.setConsigneeIdNumber("**********");*/
        model.addAttribute("tax",orderJd.getTax().stripTrailingZeros().toPlainString());
        model.addAttribute("freight",orderJd.getFreight().stripTrailingZeros().toPlainString());
        model.addAttribute("insuredFee",orderJd.getInsuredFee().stripTrailingZeros().toPlainString());
        model.addAttribute("order", orderJd);
        model.addAttribute("mainId", id);
        return "order/jd/jd_detail.html";
    }

    /**
     * 订单详情查询
     */
    @RequestMapping(value = "/detail/json/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String json(@PathVariable("id") Long id, @RequestParam("page") Optional<Integer> page,
            @RequestParam("rows") Optional<Integer> rows, Model model) {
        PageInfo<OrderJdItem> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderJdItemService.selectByOrderId(id));

        return convertPageJson(pageInfo);
    }


    /**
     * 显示用户敏感信息
     */
    @RequestMapping("/sensitiveData")
    @ResponseBody
    @RequiresPermissions("order.jd.detail")
    public String sensitiveData(int index,Long id) {
        OrderJd orderJd = orderJdService.selectById(id);
        BusinessAssert.assertNotNull(orderJd,"订单不存在");
        switch (index) {
            case 1 : return AES256Util.decrypt(orderJd.getConsigneePhone());
            case 2 : return AES256Util.decrypt(orderJd.getConsigneeIdNumber());
            case 3 : return AES256Util.decrypt(orderJd.getBuyerIdNumber());
            case 4 : return AES256Util.decrypt(orderJd.getBuyerPhone());
        }
        return "**********";
    }

    @RequestMapping({"/rePush"})
    @ResponseBody
    @RequiresPermissions("order.jd.repush")
    public BaseResponse rePush(@RequestParam(value = "ids[]") Long[] ids) {
        BusinessAssert.assertNotEmpty(ids,"请选择需要重推的数据");
        orderJdService.rePush(ids);
        return BaseResponse.responseSuccess(getSuccessMessage());
    }

    @RequestMapping(value = "/toCatch")
    @RequiresPermissions("order.jd.catch")
    public String toCatch(Model model) {
        return "order/jd/jd_catch.html";
    }

    /**
     * 单票抓单，多渠道的必须有第三方平台编码
     * @param dto
     * @return
     */
    @RequestMapping(value = "/catch")
    @ResponseBody
    @RequiresPermissions("order.jd.catch")
    public BaseResponse catchOrder(OrderJdDto dto) {
        BusinessAssert.assertNotNull(dto,"抓单参数接收失败");
        BusinessAssert.assertNotEmpty(dto.getOrderId(),"订单号不能为空！");
        BusinessAssert.assertNotEmpty(dto.getGrabKey(),"抓单Id不能为空！");
        if(dto.getIsMc() == YesOrNoEnum.YES) {
            BusinessAssert.assertNotEmpty(dto.getThirdPlatformCode(),"抓取多渠道订单时，三方平台编码不能为空！");
        }else {
            dto.setThirdPlatformCode(null);
        }

        jdService.getOneOrder(dto.getOrderId(),dto.getGrabKey(),dto.getThirdPlatformCode(),dto.getIsMc() == YesOrNoEnum.YES);
        return BaseResponse.responseSuccess("抓单成功!");
    }
}
