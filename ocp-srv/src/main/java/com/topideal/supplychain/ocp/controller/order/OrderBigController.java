package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.big.service.BigService;
import com.topideal.supplychain.ocp.master.service.LogisticsService;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.order.dto.OrderBigPageRequestDto;
import com.topideal.supplychain.ocp.order.model.OrderBig;
import com.topideal.supplychain.ocp.order.service.OrderBigItemService;
import com.topideal.supplychain.ocp.order.service.OrderBigService;
import com.topideal.supplychain.util.AES256Util;
import com.topideal.supplychain.web.system.service.DictService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * <p>标题: 大订单前端交互</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.controller.order</p>
 * <p>作者: songping</p>
 * <p>创建日期: 2019/12/17 15:55</p>
 *
 * @version 1.0
 */
@RequestMapping("/orderBig")
@Controller
public class OrderBigController extends BaseController {

    @Autowired
    private PlatformService platformService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private LogisticsService logisticsService;
    @Autowired
    private BigService orderService;
    @Autowired
    private OrderBigService orderBigService;
    @Autowired
    private OrderBigItemService goodsBigService;
    @Autowired
    private DictService dictService;

    /**
     * 加载主页面
     */
    @RequestMapping("")
    @RequiresPermissions("order.big")
    public String list(Model model) {
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        model.addAttribute("logisticsList", logisticsService.findAll());
        model.addAttribute("busiModeMap", dictService.getByCode("business.mode"));
        return "order/big/big_list.html";
    }

    /**
     * 主页面查询
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @RequiresPermissions("order.big.list")
    @ResponseBody
    public String json(OrderBigPageRequestDto filter, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderBig> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderBigService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 跳转到详情页面
     */
    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.big.detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        OrderBig orderBig = orderBigService.selectById(id);
        orderBig.setBuyerTelephone("***********");
        orderBig.setBuyerIdNumber("***********");
        orderBig.setReceiverMobile("***********");
        orderBig.setReceiverIdNumber("***********");
        orderBig.setShipperPhone("***********");
        orderBig.setFreight(orderBig.getFreight() == null ? BigDecimal.ZERO : orderBig.getFreight().stripTrailingZeros());
        orderBig.setInsuredFee(orderBig.getInsuredFee() == null ? BigDecimal.ZERO : orderBig.getInsuredFee().stripTrailingZeros());
        orderBig.setTax(orderBig.getTax() == null ? BigDecimal.ZERO : orderBig.getTax().stripTrailingZeros());
        orderBig.setActuralPaid(orderBig.getActuralPaid() == null ? BigDecimal.ZERO : orderBig.getActuralPaid().stripTrailingZeros());
        orderBig.setDiscount(orderBig.getDiscount() == null ? BigDecimal.ZERO : orderBig.getDiscount().stripTrailingZeros());
        orderBig.setOtherRate(orderBig.getOtherRate().stripTrailingZeros());
        orderBig.setOtherPayment(orderBig.getOtherPayment().stripTrailingZeros());
        model.addAttribute("order", orderBig);
        model.addAttribute("mainId", id);
        return "order/big/big_detail.html";
    }

     /**
     * 订单详情查询
     */
    @RequestMapping(value = "/detail/json/{mainId}", method = RequestMethod.POST)
    @ResponseBody
    public String json(@PathVariable("mainId") Long mainId, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderBig> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> goodsBigService.selectByMainId(mainId));
        return convertPageJson(pageInfo);
    }

    /**
     * 解密敏感信息
     */
    @RequestMapping("/sensitiveData")
    @ResponseBody
    @RequiresPermissions("order.big.detail")
    public String sensitiveData(String propertyName, Long mainId) {
        String sensitiveData = orderBigService.selectSensitiveData(propertyName,mainId);
        if (StringUtils.isBlank(sensitiveData)){
            return "*************";
        }
        return AES256Util.decrypt(sensitiveData);
    }

    /**
     * 重推订单
     */
    @RequestMapping("/rePush")
    @ResponseBody
    @RequiresPermissions("order.big.repush")
    public BaseResponse rePush(@RequestParam("ids[]") List<Long> ids) {
        String msg = orderService.rePush(ids);
        return StringUtils.isEmpty(msg) ? BaseResponse.responseSuccess(getSuccessMessage()) : BaseResponse.responseFailure(msg);
    }

}
