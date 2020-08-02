package com.topideal.supplychain.ocp.controller.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.dto.BigJdChannelConfigPageRequestDto;
import com.topideal.supplychain.ocp.config.model.BigJdChannelConfig;
import com.topideal.supplychain.ocp.config.service.BigJdChannelConfigService;
import com.topideal.supplychain.ocp.master.service.LogisticsService;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.master.service.StoreService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 标题：大订单京东多渠道配置页面controller
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.controller.config
 * 作者：songping
 * 创建日期：2019/12/24 15:45
 *
 * @version 1.0
 */
@RequestMapping("/bigJdChannelConfig")
@Controller
public class BigJdChannelConfigController extends BaseController {

    @Autowired
    private BigJdChannelConfigService bigJdConfigService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private LogisticsService logisticsService;
    @Autowired
    private StoreService storeService;

    /**
     * 加载主页面
     */
    @RequestMapping("")
    @RequiresPermissions("config.bigJdChannelConfig")
    public String list(Model model) {
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        model.addAttribute("logisticsList", logisticsService.findAll());
        model.addAttribute("storeList", storeService.findAll());
        return "config/bigJdChannelConfig/bigJdChannelConfig_list.html";
    }

    /**
     * 主页面查询
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @RequiresPermissions("config.bigJdChannelConfig.list")
    @ResponseBody
    public String json(BigJdChannelConfigPageRequestDto filter, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<BigJdChannelConfig> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> bigJdConfigService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 跳转到新增页面
     */
    @RequestMapping("/loadAddPage")
    @RequiresPermissions("config.bigJdChannelConfig.add")
    public String loadAddPage(Model model) {
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        model.addAttribute("logisticsList", logisticsService.findAll());
        model.addAttribute("storeList", storeService.findAll());
        return "config/bigJdChannelConfig/bigJdChannelConfig_add.html";
    }

    /**
     * 跳转到编辑页面
     */
    @RequestMapping("/loadEditPage/{id}")
    @RequiresPermissions("config.bigJdChannelConfig.edit")
    public String loadEditPage(@PathVariable("id") Long id, Model model) {
        BigJdChannelConfig config = bigJdConfigService.selectById(id);
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        model.addAttribute("logisticsList", logisticsService.findAll());
        model.addAttribute("storeList", storeService.findAll());
        model.addAttribute("bigJdChannelConfig", config);
        return "config/bigJdChannelConfig/bigJdChannelConfig_edit.html";
    }

    /**
     * 保存新增的配置
     * @param config
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public BaseResponse save(BigJdChannelConfig config) {
        bigJdConfigService.insert(config);
        return BaseResponse.responseSuccess("配置新增成功");
    }

    /**
     * 编辑配置
     * @param config
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public BaseResponse edit(BigJdChannelConfig config) {
        bigJdConfigService.update(config);
        return BaseResponse.responseSuccess("配置更新成功");
    }

    /**
     * 启用配置
     */
    @RequestMapping("/enable")
    @ResponseBody
    @RequiresPermissions("config.bigJdChannelConfig.enable")
    public BaseResponse enable(@RequestParam("ids[]") List<Long> ids) {
        bigJdConfigService.enable(ids);
        return BaseResponse.responseSuccess("配置已启用");
    }

    /**
     * 禁用配置
     */
    @RequestMapping("/disable")
    @ResponseBody
    @RequiresPermissions("config.bigJdChannelConfig.disable")
    public BaseResponse disable(@RequestParam("ids[]") List<Long> ids) {
        bigJdConfigService.disable(ids);
        return BaseResponse.responseSuccess("配置已禁用");
    }

    /**
     * 删除配置
     */
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("config.bigJdChannelConfig.delete")
    public BaseResponse delete(@RequestParam("ids[]") List<Long> ids) {
        bigJdConfigService.deleteByIds(ids);
        return BaseResponse.responseSuccess("配置已删除");
    }
}
