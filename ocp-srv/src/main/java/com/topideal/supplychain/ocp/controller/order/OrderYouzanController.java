package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.topideal.supplychain.common.controller.ExcelExportController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.YesOrNoEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.export.ExportContext;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.enums.YouZanOrderNewEnum;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.order.dto.OrderYouzanDto;
import com.topideal.supplychain.ocp.order.model.OrderYouzan;
import com.topideal.supplychain.ocp.order.model.OrderYouzanItem;
import com.topideal.supplychain.ocp.order.model.OrderYouzanTags;
import com.topideal.supplychain.ocp.order.service.OrderYouzanItemService;
import com.topideal.supplychain.ocp.order.service.OrderYouzanService;
import com.topideal.supplychain.ocp.order.service.OrderYouzanTagsService;
import com.topideal.supplychain.ocp.youzan.service.YouzanService;
import com.topideal.supplychain.util.AES256Util;
import com.topideal.supplychain.util.InfoEncryptUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName OrderYouzanController
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/22 14:33
 * @Version 1.0
 **/
@Controller
@RequestMapping("orderYouzan")
public class OrderYouzanController extends ExcelExportController<OrderYouzan> {

    @Autowired
    private OrderYouzanService orderYouzanService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private YouzanService youzanService;
    @Autowired
    private OrderYouzanItemService orderYouzanItemService;
    @Autowired
    private OrderYouzanTagsService orderYouzanTagsService;

    @RequestMapping(value = "")
    @RequiresPermissions("order.youzan")
    public String list() {
        return "order/youzan/youzan_list.html";
    }


    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("order.youzan.query")
    public String json(@ModelAttribute("orderYouzanDto") OrderYouzanDto orderYouzanDto, @RequestParam("page") Optional<Integer> page,
            @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderYouzanDto> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderYouzanService.pageList(orderYouzanDto));
        for (OrderYouzanDto order : pageInfo.getList()) {
                order.setBuyerPhone(InfoEncryptUtil.encryptPhoneOrMobileX(AES256Util.decrypt(order.getBuyerPhone())));
                order.setReceiverTel(InfoEncryptUtil.encryptPhoneOrMobileX(order.getReceiverTel()));
        }
        return convertPageJson(pageInfo);
    }


    @RequestMapping(value = "/toCatch")
    @RequiresPermissions("order.youzan.catch")
    public String toCatch(Model model) {
        String youzan = PlatformEnum.YOUZAN.getCode();
        List<Platform> platforms = platformService.findByVirtualCode(youzan);
        if (!CollectionUtils.isEmpty(platforms)) {
            List<Store> stores = storeService.findByPlatforms(platforms);
            model.addAttribute("stores",stores);
        }
        return "order/youzan/youzan_catch.html";
    }

    @RequestMapping(value = "/catch")
    @ResponseBody
    @RequiresPermissions("order.youzan.catch")
    public BaseResponse catchOrder(Long storeId,String tid) {
        OrderYouzan youZanOrder = orderYouzanService.orderIsExist(tid);
        BusinessAssert.assertIsNull(youZanOrder,"已抓取该订单");
        Store store = storeService.selectById(storeId);
        return youzanService.getOrder(store,tid);
        //return BaseResponse.responseSuccess("操作成功!");
    }

    @RequestMapping({"/rePush"})
    @ResponseBody
    @RequiresPermissions("order.youzan.repush")
    public BaseResponse rePush(@RequestParam(value = "ids[]") Long[] ids) {
        BusinessAssert.assertNotEmpty(ids,"请选择需要重推的数据");
        orderYouzanService.rePush(ids);
        return BaseResponse.responseSuccess(getSuccessMessage());
    }

    /**
     * 跳至详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.youzan.query")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("orderId", id);
        OrderYouzanDto orderYouzanDto = orderYouzanService.selectDtoById(id);
        OrderYouzanTags tags = orderYouzanTagsService.selectByOrderId(id);
        Boolean fx = tags!=null && tags.getIsPurchaseOrder()!=null && tags.getIsPurchaseOrder();
        List<OrderYouzanItem> list = orderYouzanItemService.selectByOrderId(id,YouZanOrderNewEnum.OLD);
        BigDecimal feePrice = BigDecimal.ZERO;
        BigDecimal payPrice = BigDecimal.ZERO;
        BigDecimal goodsPrice = BigDecimal.ZERO;
        BigDecimal fxGoodsPrice = BigDecimal.ZERO;
        for (OrderYouzanItem item : list) {
            feePrice = feePrice.add(item.getDiscount());
            payPrice = payPrice.add(item.getPayment()).add(item.getFreight());
            goodsPrice = goodsPrice.add(item.getDiscountPrice().multiply(item.getNum()));
            if (fx) {
                fxGoodsPrice = fxGoodsPrice.add(item.getFenxiaoDiscountPrice().multiply(item.getNum()));
            }
        }
        model.addAttribute("feePrice",feePrice.stripTrailingZeros().toPlainString());
        model.addAttribute("payPrice",payPrice.stripTrailingZeros().toPlainString());
        model.addAttribute("goodsPrice",fx ? fxGoodsPrice.stripTrailingZeros().toPlainString() : goodsPrice.stripTrailingZeros().toPlainString());
        model.addAttribute("fx",fx ? YesOrNoEnum.YES.getMemo() : YesOrNoEnum.NO.getMemo());
        model.addAttribute("status",orderYouzanDto.getToStatus().getDesc());
        model.addAttribute("orderYouzan",orderYouzanDto);
        return "order/youzan/youzan_detail";
    }

    /**
     * 订单详情查询
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String jsonDetail(@PathVariable("id") Long id) {
        List<OrderYouzanItem> list = orderYouzanItemService.selectByOrderId(id,YouZanOrderNewEnum.OLD);
        OrderYouzanTags tags = orderYouzanTagsService.selectByOrderId(id);
        Boolean fx = tags!=null && tags.getIsPurchaseOrder()!=null && tags.getIsPurchaseOrder();
        List<Map> result = Lists.newArrayList();
        for (OrderYouzanItem item : list) {
            Map map = Maps.newHashMap();
            map.put("outerSkuId",item.getOuterSkuId());
            map.put("title",item.getTitle());
            map.put("num",item.getNum());
            map.put("discount",item.getDiscount());
            map.put("discountPrice",fx?item.getFenxiaoDiscountPrice():item.getDiscountPrice());

            BigDecimal totalDiscountPrice = BigDecimal.ZERO;
            if (fx) {
                totalDiscountPrice =
                        item.getFenxiaoDiscountPrice() != null ? item.getFenxiaoDiscountPrice()
                                .multiply(item.getNum() != null ? item.getNum() : BigDecimal.ZERO)
                                : BigDecimal.ZERO;
            } else {
                totalDiscountPrice = item.getDiscountPrice() != null ? item.getDiscountPrice()
                        .multiply(item.getNum() != null ? item.getNum() : BigDecimal.ZERO)
                        : BigDecimal.ZERO;
            }
            map.put("totalDiscountPrice",totalDiscountPrice);
            result.add(map);
        }

        return convertPageJson(result,0L);
    }

    @Override
    public void setExportContext(OrderYouzan condition, ExportContext context) {

    }
}
