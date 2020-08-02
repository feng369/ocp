package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.enums.GsIsSendKjbEnum;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.gs.service.GsService;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.order.dto.OrderGsDto;
import com.topideal.supplychain.ocp.order.model.OrderGs;
import com.topideal.supplychain.ocp.order.model.OrderGsItem;
import com.topideal.supplychain.ocp.order.service.OrderGsItemService;
import com.topideal.supplychain.ocp.order.service.OrderGsService;
import java.math.BigDecimal;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>标题: 环球捕手 Controller</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.controller.order</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2019-12-16 17:28</p>
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/orderGs")
public class OrderGsController extends BaseController {

    @Autowired
    private GsService gsService;
    @Autowired
    private OrderGsService orderGsService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private OrderGsItemService orderGsItemService;

    /**
     * 初始化
     */
    @RequestMapping("")
    @RequiresPermissions("order.gs")
    public String list(Model model) {
        return "order/gs/gs_list.html";
    }

    /**
     * 查询
     * @param filter
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @RequiresPermissions("order.gs.query")
    @ResponseBody
    public String json(OrderGsDto filter, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderGsDto> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderGsService.pageList(filter));

        return convertPageJson(pageInfo);

    }

    /**
     * 显示详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.gs.detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        OrderGs orderGs = orderGsService.selectById(id);
        model.addAttribute("orderId", id);
        model.addAttribute("order", orderGs);
        model.addAttribute("bondedAreaGoodsPrice", orderGs.getBondedAreaGoodsPrice().stripTrailingZeros());
        model.addAttribute("bondedAreaNonCashDeduct", orderGs.getBondedAreaNonCashDeduct().stripTrailingZeros());
        model.addAttribute("bondedAreaShipExpense", orderGs.getBondedAreaShipExpense().stripTrailingZeros());
        model.addAttribute("bondedAreaTax", orderGs.getBondedAreaTax().stripTrailingZeros());
        model.addAttribute("bondedAreaPayCash", orderGs.getBondedAreaPayCash().stripTrailingZeros());

        return "order/gs/gs_detail.html";
    }

    /**
     * 商品详情查询
     */
    @RequestMapping(value = "/detail/json/{id}", method = RequestMethod.POST)
    @RequiresPermissions("order.gs.detail")
    @ResponseBody
    public String json(@PathVariable("id") Long id, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderGsItem> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderGsItemService.selectByOrderId(id, GsIsSendKjbEnum.OLD));
        pageInfo.getList().stream().forEach(o -> {
            o.setPrice(o.getBondedAreaGoodsPrice()
                    .divide(o.getQuantity(), 2, BigDecimal.ROUND_HALF_UP));
            o.setTotalPrice(o.getBondedAreaGoodsPrice()
                    .divide(o.getQuantity(), 2, BigDecimal.ROUND_HALF_UP)
                    .multiply(o.getQuantity()));
        });

        return convertPageJson(pageInfo);
    }

    /**
     * 重推订单
     * @param ids
     * @return
     */
    @RequestMapping({"/rePush"})
    @ResponseBody
    @RequiresPermissions("order.gs.repush")
    public BaseResponse rePush(@RequestParam("ids[]") List<Long> ids) {
        BusinessAssert.assertNotEmpty(ids,"请选择需要重推的数据！");
        String msg = gsService.rePush(ids);
        return StringUtils.isEmpty(msg) ? BaseResponse.responseSuccess(getSuccessMessage()) : BaseResponse.responseFailure(msg);
    }

    @RequestMapping(value = "/toCatch")
    @RequiresPermissions("order.gs.catch")
    public String toCatch(Model model) {
        String gs = PlatformEnum.GS.getCode();
        List<Platform> platforms = platformService.findByVirtualCode(gs);
        if (CollectionUtils.isNotEmpty(platforms)) {
            List<CatchOrderConfig> catchOrderConfigs = catchOrderConfigService.selectByPlatform(platforms);
            model.addAttribute("catchOrderConfigs",catchOrderConfigs);
        }
        return "order/gs/gs_catch.html";
    }

    /**
     *
     * @param catchOrderConfigId
     * @param consignCode
     * @return
     */
    @RequestMapping(value = "/catch")
    @ResponseBody
    @RequiresPermissions("order.gs.catch")
    public BaseResponse catchOrder(Long catchOrderConfigId, String consignCode) {
        List<OrderGs> oldOrderGs = orderGsService.selectByConsignCode(consignCode);
        BusinessAssert.assertEmpty(oldOrderGs,"已抓取该订单");
        return gsService.getOrder(catchOrderConfigId, consignCode);
    }

}
