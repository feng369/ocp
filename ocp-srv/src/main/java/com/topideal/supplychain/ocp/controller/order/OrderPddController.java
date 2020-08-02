package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.constants.PunctuateConstant;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.enums.OrderTypeEnum;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.master.model.Merchant;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.dto.OrderPddDto;
import com.topideal.supplychain.ocp.order.model.OrderPdd;
import com.topideal.supplychain.ocp.order.model.OrderPddItem;
import com.topideal.supplychain.ocp.order.service.OrderPddItemService;
import com.topideal.supplychain.ocp.order.service.OrderPddService;
import com.topideal.supplychain.ocp.pdd.service.PddService;
import com.topideal.supplychain.util.AES256Util;
import com.topideal.supplychain.util.InfoEncryptUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author klw
 * @date 2019/12/19 16:20
 * @description: 拼多多页面controller
 */
@Controller
@RequestMapping("pdd")
@RequiresPermissions("order.pdd")
public class OrderPddController extends BaseController {

    @Autowired
    private OrderPddService orderPddService;
    @Autowired
    private OrderPddItemService orderPddItemService;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private PddService pddService;
    @Autowired
    private MerchantService merchantService;

    @RequestMapping("")
    public String list(Model model) {
        return "order/pdd/pdd_list.html";
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("order.pdd.query")
    public String json(OrderPddDto condition, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderPddDto> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(10)).doSelectPageInfo(
                        () -> orderPddService.pageList(condition));
        Map<String, String> platformMap = Maps.newHashMap();
        Map<String, String> merchantMap = Maps.newHashMap();
        for (OrderPddDto orderDto : pageInfo.getList()){
            if (platformMap.get(orderDto.getCbepcomCode()) == null){
                Platform platform = platformService.findByCode(orderDto.getCbepcomCode());
                if (platform != null) {
                    platformMap.put(orderDto.getCbepcomCode(), platform.getName());
                }else {
                    platformMap.put(orderDto.getCbepcomCode(), null);
                }
            }
            orderDto.setPlatformName(platformMap.get(orderDto.getCbepcomCode()));

            if (merchantMap.get(orderDto.getElectricCode()) == null){
                Merchant merchant = merchantService.findByCode(orderDto.getElectricCode());
                if (merchant != null) {
                    merchantMap.put(orderDto.getElectricCode(), merchant.getName());
                }else {
                    merchantMap.put(orderDto.getElectricCode(), null);
                }
            }
            orderDto.setMerchantName(merchantMap.get(orderDto.getElectricCode()));

            orderDto.setBusiModeEnum(BusiModeEnum.getValueEnum(orderDto.getBusiMode()));
        }
        return convertPageJson(pageInfo);
    }

    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.pdd.detail")
    public String showDetail(@PathVariable("id") Long id, Model model){
        BusinessAssert.assertNotNull(id, "参数为空");
        OrderPdd order = orderPddService.selectById(id);
        BusinessAssert.assertNotNull(order, "数据不存在");
        //订购信息混淆一下
        order.setBuyerName(InfoEncryptUtil.encryptNameX(order.getBuyerName()));
        order.setBuyerTelephone(InfoEncryptUtil.encryptPhoneOrMobileX(AES256Util.decrypt(order.getBuyerTelephone())));
        order.setName(InfoEncryptUtil.encryptNameX(order.getName()));
        order.setMobilePhone(InfoEncryptUtil.encryptPhoneOrMobileX(order.getMobilePhone()));
        order.setAddress(InfoEncryptUtil.encryptAddressX(order.getAddress()));

        List<OrderPddItem> itemList = orderPddItemService.selectByOrderId(order.getId());

        model.addAttribute("order", order);
        model.addAttribute("itemList", itemList);
        return "order/pdd/pdd_detail";
    }

    @RequestMapping("/toCatch")
    @RequiresPermissions("order.pdd.catchOrder")
    public String toCatch(Model model){
        List<CatchOrderConfig> configList = Lists.newArrayList();
        //拿到拼多多电商平台编码
        List<Platform> platforms = platformService.findByVirtualCode(PlatformEnum.PDD.getCode());
        if (CollectionUtils.isNotEmpty(platforms)) {
            configList = catchOrderConfigService.selectByPlatform(platforms);
        }
        model.addAttribute("configList", configList);
        return "order/pdd/pdd_catch";
    }

    @RequestMapping("/catchOrder")
    @RequiresPermissions("order.pdd.catchOrder")
    @ResponseBody
    public BaseResponse catchOrder(OrderPddDto condition) throws Exception{
        BusinessAssert.assertNotNull(condition, "参数为空");
        BusinessAssert.assertNotEmpty(condition.getOrderSn(), "订单号不能为空");
        BusinessAssert.assertNotNull(condition.getConfigId(), "抓单配置不能为空");
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectById(condition.getConfigId());
        BusinessAssert.assertNotNull(catchOrderConfig, "抓单配置不能为空");
        pddService.catchSingleOrder(catchOrderConfig, condition.getOrderSn());
        return BaseResponse.responseSuccess("操作成功");
    }

    @RequestMapping("/repush")
    @RequiresPermissions("order.pdd.repush")
    @ResponseBody
    public BaseResponse rePush(@RequestParam(value = "ids[]") Long[] ids){
        List<OrderPdd> orderList = orderPddService.selectByIds(ids);
        BusinessAssert.assertIsTrue(CollectionUtils.isNotEmpty(orderList), "数据不存在");
        List<String> failureCodeList = Lists.newArrayList();
        orderList.forEach(order -> {
            if (OrderStatusEnum.SEND_SUCCESS.equals(order.getStatus())){
                messageSender.send(QueueConstants.QueueEnum.PDD_ORDER_PUSH_ROUTER, new BasicMessage(order.getId(), order.getCode()));
                return;
            }
            failureCodeList.add(order.getCode());
        });
        if (CollectionUtils.isEmpty(failureCodeList)){
            return BaseResponse.responseSuccess("操作成功");
        }
        return BaseResponse.responseFailure("仅下发成功的订单允许页面重推，失败内部订单号："
                + StringUtils.join(failureCodeList, PunctuateConstant.COMMA));
    }
}
