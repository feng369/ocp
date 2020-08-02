package com.topideal.supplychain.ocp.controller.master;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.enums.SyncStatusEnum;
import com.topideal.supplychain.ocp.master.model.GoodsInfo;
import com.topideal.supplychain.ocp.master.service.GoodsInfoService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderPub;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 商品信息登记
 * @author syq
 * @date 2019-12-04 16:20
 */
@Controller
@RequestMapping("goodsInfo")
public class GoodsInfoController extends BaseController {

    @Autowired
    private GoodsInfoService goodsInfoService;
    @Autowired
    private MessageSender messageSender;

    /**
     * 初始化
     * @return
     */
    @RequestMapping(value = "")
    public String list() {
        return "master/goodsInfo/goodsInfo_list.html";
    }


    /**
     * 页面查询
     * @param goodsInfo
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("master.goodsInfo.query")
    public String json(@ModelAttribute("goodsInfo") GoodsInfo goodsInfo, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<GoodsInfo> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> goodsInfoService.pageList(goodsInfo));
        return convertPageJson(pageInfo);
    }


    /**
     * 跳转到详情页面
     */
    @RequestMapping("/showDetail/{id}")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        GoodsInfo goodsInfo = goodsInfoService.selectById(id);
        model.addAttribute("goodsInfo", goodsInfo);
        return "master/goodsInfo/goodsInfo_detail.html";
    }

    /**
     * 重推商品信息至主数据
     * @param ids
     * @return
     */
    @RequestMapping("/repushMdm")
    @ResponseBody
    @RequiresPermissions("master.goodsInfo.repushMdm")
    public BaseResponse rePush(@RequestParam("ids[]") List<Long> ids) {
        for (Long id : ids) {
            GoodsInfo goodsInfo = goodsInfoService.selectById(id);
            if (!SyncStatusEnum.SYNC_FAIL.equals(goodsInfo.getStatus())) {
                continue;
            }
            messageSender.send(QueueConstants.QueueEnum.OCP_MDM_SYNC_GOODS_INFO, new BasicMessage(goodsInfo.getId(), goodsInfo.getCode()));
        }
        return BaseResponse.responseSuccess(getSuccessMessage());
    }
}
