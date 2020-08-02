package com.topideal.supplychain.ocp.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.order.dto.OrderPubDto;
import com.topideal.supplychain.ocp.order.model.OrderPubItem;
import com.topideal.supplychain.ocp.order.model.OrderPub;
import com.topideal.supplychain.ocp.order.service.OrderPubItemService;
import com.topideal.supplychain.ocp.order.service.OrderPubService;
import com.topideal.supplychain.ocp.pub.service.PubService;
import com.topideal.supplychain.ocp.vip.service.VipService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>标题: 标准订单前端交互</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.controller.order</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/11/27 18:13</p>
 *
 * @version 1.0
 */
@RequestMapping("/orderPub")
@Controller
public class OrderPubController extends BaseController {

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private OrderPubService orderPubService;
    @Autowired
    private OrderPubItemService orderPubItemService;
    @Autowired
    private VipService vipService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private PubService pubService;

    /**
     * 加载主页面
     */
    @RequestMapping("")
    @RequiresPermissions("order.pub")
    public String list(Model model) {
        model.addAttribute("storeList", storeService.findAll());
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        return "order/pub/pub_list.html";
    }

    /**
     * 主页面查询
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @RequiresPermissions("order.pub.list")
    @ResponseBody
    public String json(OrderPubDto filter, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderPub> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderPubService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 跳转到详情页面
     */
    @RequestMapping("/showDetail/{id}")
    @RequiresPermissions("order.pub.detail")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        OrderPub orderPub = orderPubService.selectById(id);
        model.addAttribute("orderPub", orderPub);
        return "order/pub/pub_detail.html";
    }

    /**
     * 订单详情查询
     */
    @RequestMapping(value = "/detail/json/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String json(@PathVariable("id") Long id, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<OrderPubItem> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> orderPubItemService.selectByOrderId(id));
        return convertPageJson(pageInfo);
    }

    @RequestMapping("/rePush")
    @ResponseBody
    public BaseResponse rePush(@RequestParam("ids[]") List<Long> ids) {
        String msg = pubService.rePush(ids);
        return StringUtils.isEmpty(msg) ? BaseResponse.responseSuccess(getSuccessMessage()) : BaseResponse.responseFailure(msg);
    }
}
