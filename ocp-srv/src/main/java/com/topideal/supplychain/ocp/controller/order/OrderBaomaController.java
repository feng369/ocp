package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.baoma.service.BaomaService;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import com.topideal.supplychain.ocp.order.model.OrderBaoma;
import com.topideal.supplychain.ocp.order.model.OrderYmatou;
import com.topideal.supplychain.ocp.order.model.OrderYmatouItem;
import com.topideal.supplychain.ocp.order.service.OrderBaomaGoodsService;
import com.topideal.supplychain.ocp.order.service.OrderBaomaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author wanzhaozhang
 * @date 2019/11/28
 * @description 洋码头订单 控制层
 **/
@Controller
@RequestMapping("orderBaoma")
public class OrderBaomaController extends BaseController {

    @Autowired
    private OrderBaomaService orderBaomaService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private OrderBaomaGoodsService orderBaomaGoodsService;
    @Autowired
    private BaomaService baomaService;

    @RequestMapping(value = "")
    @RequiresPermissions("order.baoma")
    public String list(Model model) {
        model.addAttribute("merchantList", merchantService.findAll());
        return "order/baoma/baoma_list";
    }


    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("order.baoma.query")
    public String json(OrderBaoma filter, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderYmatou> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderBaomaService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 跳至详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.baoma.detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("orderId", id);
        return "order/baoma/baoma_detail";
    }

    /**
     * 订单详情查询
     */
    @RequestMapping(value = "/detail/json/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String jsonDetail(@PathVariable("id") Long id, @RequestParam("page") Optional<Integer> page,
                             @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderYmatouItem> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderBaomaGoodsService.selectAllByOrderId(id));
        return convertPageJson(pageInfo);
    }

    @RequestMapping("/rePush")
    @ResponseBody
    @RequiresPermissions("order.baoma.repush")
    public BaseResponse rePush(@RequestParam("ids[]") List<Long> ids){
        BusinessAssert.assertNotEmpty(ids,"请选择需要重推的数据");
        return baomaService.rePush(ids);
    }
}
