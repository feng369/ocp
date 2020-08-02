package com.topideal.supplychain.ocp.controller.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.config.dto.FxDefaultConfig;
import com.topideal.supplychain.ocp.config.model.FxConfig;
import com.topideal.supplychain.ocp.config.service.FxConfigService;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.util.JacksonUtils;
import java.util.Optional;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>标题: ocp转单配置</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.controller.master</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/11/22 11:38</p>
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/fxConfig")
public class FxConfigController extends BaseController {

    @Autowired
    private FxConfigService fxConfigService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private StoreService storeService;

    /**
     * 加载主页面
     */
    @RequestMapping("")
    @RequiresPermissions("config.fx")
    public String list(Model model) {
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        return "config/fx/fxConfig_list.html";
    }

    /**
     * 主页面查询
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @RequiresPermissions("config.fx.query")
    @ResponseBody
    public String json(FxConfig filter, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<FxConfig> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> fxConfigService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 跳转到新增页面
     */
    @RequestMapping("/loadAddPage")
    @RequiresPermissions("config.fx.add")
    public String loadAddPage(Model model) {
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        model.addAttribute("storeList", storeService.findAll());
        FxDefaultConfig configuration = FxDefaultConfig.newBuilder().ciqCode("")
                .fromEplat("").logisticsCode("").logisticsName("").merchantCode("")
                .orderSource("").orderType("").platformCode("").warehouseCode("").tpl("").itemMerchantCode("").build();
        model.addAttribute("configuration", JacksonUtils.toJSon(configuration));
        return "config/fx/fxConfig_add.html";
    }

    /**
     * 跳转到编辑页面
     */
    @RequestMapping("/loadEditPage/{id}")
    @RequiresPermissions("config.fx.edit")
    public String loadEditPage(@PathVariable("id") Long id,Model model) {
        FxConfig fxConfig = fxConfigService.selectById(id);
        model.addAttribute("fxConfig", fxConfig);
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        model.addAttribute("storeList", storeService.findAll());
        return "config/fx/fxConfig_edit.html";
    }

    /**
     * 保存新增的分销配置信息
     * @param config
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    @RequiresPermissions("config.fx.add")
    public BaseResponse save(FxConfig config) {
        FxDefaultConfig configuration = JacksonUtils.readValue(config.getConfiguration(),
                FxDefaultConfig.class);
        BusinessAssert.assertNotNull(configuration,"分销配置不正确");
        try {
            fxConfigService.save(config);
        } catch (DuplicateKeyException e) {
            //唯一索引冲突报错
            return BaseResponse.responseFailure("分销配置已存在");
        }
        return BaseResponse.responseSuccess("分销配置新增成功");
    }

    /**
     * 编辑分销配置
     * @param config
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    @RequiresPermissions("config.fx.edit")
    public BaseResponse edit(FxConfig config) {
        FxDefaultConfig configuration = JacksonUtils.readValue(config.getConfiguration(),FxDefaultConfig.class);
        BusinessAssert.assertNotNull(configuration, "分销配置不正确");
        try {
            fxConfigService.edit(config);
        } catch (DuplicateKeyException e) {
            //唯一索引冲突报错
            return BaseResponse.responseFailure("分销配置已存在");
        }
        return BaseResponse.responseSuccess("分销配置修改成功");
    }

    /**
     * 启用配置
     */
    @RequestMapping("/enable")
    @ResponseBody
    @RequiresPermissions("config.fx.enable")
    public BaseResponse enable(String ids) {
        fxConfigService.enable(ids);
        return BaseResponse.responseSuccess("配置已启用");
    }

    /**
     * 禁用配置
     */
    @RequestMapping("/disable")
    @ResponseBody
    @RequiresPermissions("config.fx.disable")
    public BaseResponse disable(String ids) {
        fxConfigService.disable(ids);
        return BaseResponse.responseSuccess("配置已禁用");
    }

    /**
     * 删除配置
     */
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("config.fx.delete")
    public BaseResponse delete(String ids) {
        fxConfigService.delete(ids);
        return BaseResponse.responseSuccess("配置已删除");
    }

    /**
     * 清除配置缓存
     */
    @RequestMapping("/clearCache")
    @ResponseBody
    @RequiresPermissions("config.fx.clear.cache")
    public BaseResponse clearCache() {
        fxConfigService.clearCache();
        return BaseResponse.responseSuccess("配置已清除缓存");
    }

}
