package com.topideal.supplychain.ocp.controller.master;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.EnableOrDisableEnum;
import com.topideal.supplychain.ocp.master.model.Logistics;
import com.topideal.supplychain.ocp.master.service.LogisticsService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 物流企业
 * @author xuxiaoyan
 * @date 2019-11-13 19:20
 */
@Controller
@RequestMapping("logistics")
public class LogisticsController extends BaseController {

    @Autowired
    private LogisticsService logisticsService;

    /**
     * 初始化
     * @return
     */
    @RequestMapping(value = "")
    @RequiresPermissions("master.logistics")
    public String list() {
        return "master/logistics/logistics_list.html";
    }


    /**
     * 页面查询
     * @param logistics
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("master.logistics.query")
    public String json(@ModelAttribute("logistics") Logistics logistics, @RequestParam("page") Optional<Integer> page,
            @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<Logistics> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> logisticsService.pageList(logistics));
        return convertPageJson(pageInfo);
    }

    /**
     * 批量启用
     */
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    @RequiresPermissions("master.logistics.enable")
    @ResponseBody
    public BaseResponse enable(String ids) {
        List<String> idList = new ArrayList<>();
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            idList = Arrays.asList(split);
        }
        logisticsService.updateStatus(Long.valueOf(idList.get(0)), EnableOrDisableEnum.ENABLE.getValue());
        return BaseResponse.responseSuccess("操作成功");
    }

    /**
     * 批量禁用
     */
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    @RequiresPermissions("master.logistics.disable")
    @ResponseBody
    public BaseResponse disable(String ids) {
        List<String> idList = new ArrayList<>();
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            idList = Arrays.asList(split);
        }
        logisticsService.updateStatus(Long.valueOf(idList.get(0)), EnableOrDisableEnum.DISABLE.getValue());
        return BaseResponse.responseSuccess("操作成功");
    }


}
