package com.topideal.supplychain.ocp.controller.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.config.service.TransferConfigService;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.util.JacksonUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
@RequestMapping("/transferConfig")
public class TransferConfigController extends BaseController {

    @Autowired
    private TransferConfigService transferConfigService;
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
    @RequiresPermissions("config.transfer")
    public String list(Model model) {
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        return "config/transfer/transferConfig_list.html";
    }

    /**
     * 主页面查询
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @RequiresPermissions("config.transfer.list")
    @ResponseBody
    public String json(TransferConfig filter, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<TransferConfig> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> transferConfigService.pageList(filter));
        return convertPageJson(pageInfo);
    }

    /**
     * 跳转到新增页面
     */
    @RequestMapping("/loadAddPage")
    public String loadAddPage(Model model) {
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        model.addAttribute("storeList", storeService.findAll());
        TransferDefaultConfig configuration = TransferDefaultConfig.newBuilder().ciqCode("")
                .fromEplat("").logisticsCode("").logisticsName("").merchantCode("")
                .orderSource("").orderType("").platformCode("").warehouseCode("").tpl("").itemMerchantCode("").build();
        model.addAttribute("configuration", JacksonUtils.toJSon(configuration));
        return "config/transfer/transferConfig_add.html";
    }

    /**
     * 跳转到编辑页面
     */
    @RequestMapping("/loadEditPage/{id}")
    public String loadEditPage(@PathVariable("id") Long id,Model model) {
        TransferConfig transferConfig = transferConfigService.selectById(id);
        model.addAttribute("transferConfig", transferConfig);
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        model.addAttribute("storeList", storeService.findAll());
        return "config/transfer/transferConfig_edit.html";
    }

    /**
     * 保存新增的转单配置信息
     * @param config
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public BaseResponse save(TransferConfig config) {
        TransferDefaultConfig configuration = JacksonUtils.readValue(config.getConfiguration(),
                TransferDefaultConfig.class);
        BusinessAssert.assertNotNull(configuration,"转单配置不正确");
        try {
            transferConfigService.save(config);
        } catch (DuplicateKeyException e) {
            //唯一索引冲突报错
            return BaseResponse.responseFailure("转单配置已存在");
        }
        return BaseResponse.responseSuccess("转单配置新增成功");
    }

    /**
     * 编辑转单配置
     * @param config
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public BaseResponse edit(TransferConfig config) {
        TransferDefaultConfig configuration = JacksonUtils.readValue(config.getConfiguration(),TransferDefaultConfig.class);
        BusinessAssert.assertNotNull(configuration, "转单配置不正确");
        try {
            transferConfigService.edit(config);
        } catch (DuplicateKeyException e) {
            //唯一索引冲突报错
            return BaseResponse.responseFailure("转单配置已存在");
        }
        return BaseResponse.responseSuccess("转单配置修改成功");
    }

    /**
     * 启用配置
     */
    @RequestMapping("/enable")
    @ResponseBody
    public BaseResponse enable(String ids) {
        transferConfigService.enable(ids);
        return BaseResponse.responseSuccess("配置已启用");
    }

    /**
     * 禁用配置
     */
    @RequestMapping("/disable")
    @ResponseBody
    public BaseResponse disable(String ids) {
        transferConfigService.disable(ids);
        return BaseResponse.responseSuccess("配置已禁用");
    }

    /**
     * 删除配置
     */
    @RequestMapping("/delete")
    @ResponseBody
    public BaseResponse delete(String ids) {
        transferConfigService.delete(ids);
        return BaseResponse.responseSuccess("配置已删除");
    }

    /**
     * 清除配置缓存
     */
    @RequestMapping("/clearCache")
    @ResponseBody
    public BaseResponse clearCache() {
        transferConfigService.clearCache();
        return BaseResponse.responseSuccess("配置已清除缓存");
    }

}
