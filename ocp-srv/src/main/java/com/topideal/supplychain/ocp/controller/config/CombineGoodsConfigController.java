package com.topideal.supplychain.ocp.controller.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.model.CombineGoodsConfig;
import com.topideal.supplychain.ocp.config.service.CombineGoodsConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 标题：组套商品配置controller
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.controller.config
 * 作者：songping
 * 创建日期：2019/12/23 15:36
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/combineGoodsConfig")
public class CombineGoodsConfigController extends BaseController {

    @Autowired
    private CombineGoodsConfigService goodsConfigService;

    /**
     * 加载主页面
     */
    @RequestMapping("")
    @RequiresPermissions("config.combineGoodsConfig")
    public String list() {
        return "config/combineGoodsConfig/combineGoodsConfig_list.html";
    }

    /**
     * 主页面查询
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @RequiresPermissions("config.combineGoodsConfig.list")
    @ResponseBody
    public String json(CombineGoodsConfig filter, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<CombineGoodsConfig> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> goodsConfigService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 跳转到新增页面
     */
    @RequestMapping("/loadAddPage")
    @RequiresPermissions("config.combineGoodsConfig.add")
    public String loadAddPage() {
        return "config/combineGoodsConfig/combineGoodsConfig_add.html";
    }

    /**
     * 跳转到编辑页面
     */
    @RequestMapping("/loadEditPage/{id}")
    @RequiresPermissions("config.combineGoodsConfig.edit")
    public String loadEditPage(@PathVariable("id") Long id, Model model) {
        CombineGoodsConfig config = goodsConfigService.selectById(id);
        model.addAttribute("combineGoodsConfig", config);
        return "config/combineGoodsConfig/combineGoodsConfig_edit.html";
    }

    /**
     * 保存新增的配置
     * @param config
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public BaseResponse save(CombineGoodsConfig config) {
        goodsConfigService.insert(config);
        return BaseResponse.responseSuccess("配置新增成功");
    }

    /**
     * 编辑配置
     * @param config
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public BaseResponse edit(CombineGoodsConfig config) {
        goodsConfigService.update(config);
        return BaseResponse.responseSuccess("配置编辑成功");
    }

    /**
     * 启用配置
     */
    @RequestMapping("/enable")
    @ResponseBody
    @RequiresPermissions("config.combineGoodsConfig.enable")
    public BaseResponse enable(@RequestParam("ids[]") Long[] ids) {
        goodsConfigService.enable(ids);
        return BaseResponse.responseSuccess("配置启用成功");
    }

    /**
     * 禁用配置
     */
    @RequestMapping("/disable")
    @ResponseBody
    @RequiresPermissions("config.combineGoodsConfig.disable")
    public BaseResponse disable(@RequestParam("ids[]") Long[] ids) {
        goodsConfigService.disable(ids);
        return BaseResponse.responseSuccess("配置禁用成功");
    }

    /**
     * 删除配置
     */
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("config.combineGoodsConfig.delete")
    public BaseResponse delete(@RequestParam("ids[]") Long[] ids) {
        goodsConfigService.deleteByIds(ids);
        return BaseResponse.responseSuccess("配置删除成功");
    }
}
