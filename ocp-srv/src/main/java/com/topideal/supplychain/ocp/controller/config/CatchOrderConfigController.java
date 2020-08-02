package com.topideal.supplychain.ocp.controller.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.config.dto.CatchDefaultConfig;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.enums.GrabIdEnum;
import com.topideal.supplychain.ocp.master.dto.StoreDto;
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
 * @description: 接单配置
 * @author: syq
 * @create: 2019-11-20 14:11
 **/
@Controller
@RequestMapping("/catchOrderConfig")
public class CatchOrderConfigController extends BaseController {

    @Autowired
    private PlatformService platformService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private StoreService storeService;

    /**
     * 初始化
     *
     * @return
     */
    @RequestMapping(value = "")
    @RequiresPermissions("config.catchOrderConfig")
    public String list(Model model) {

        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        model.addAttribute("storeList", storeService.pageList(new StoreDto()));
        model.addAttribute("grapIdList", GrabIdEnum.values());
        return "config/catchOrderConfig/catchOrderConfig_list.html";
    }

    /**
     * 页面查询
     *
     * @param catchOrderConfig
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("config.catchOrderConfig.query")
    public String json(@ModelAttribute("catchOrderConfig") CatchOrderConfig catchOrderConfig, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<CatchOrderConfig> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> catchOrderConfigService.pageList(catchOrderConfig));
        return convertPageJson(pageInfo);
    }

    @RequestMapping("/enable")
    @RequiresPermissions("config.catchOrderConfig.enable")
    @ResponseBody
    public String enable(Long[] ids) {
        BusinessAssert.assertNotNull(ids, "请选择至少一条数据进行启用");
        BusinessAssert.assertIsTrue(ids.length > 0, "请选择至少一条数据进行启用");
        catchOrderConfigService.enable(ids);
        return "ok";
    }

    @RequestMapping("/disable")
    @RequiresPermissions("config.catchOrderConfig.disable")
    @ResponseBody
    public String disable(Long[] ids) {
        BusinessAssert.assertNotNull(ids, "请选择至少一条数据进行启用");
        BusinessAssert.assertIsTrue(ids.length > 0, "请选择至少一条数据进行启用");
        catchOrderConfigService.disable(ids);
        return "ok";
    }

    @RequestMapping("/toAdd")
    @RequiresPermissions("config.catchOrderConfig.add")
    public String toAdd(Model model) {

        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        model.addAttribute("storeList", storeService.findAll());
        CatchDefaultConfig arg = CatchDefaultConfig.newBuilder().busiMode("").ciqCode("").customsCode("")
                .logisticsCode("").logisticsName("").customsType("")
                .tpl("").build();

        model.addAttribute("defaultArguments", JacksonUtils.toJSon(arg));
        return "config/catchOrderConfig/catchOrderConfig_add.html";
    }

    /**
     * 保存新增的转单配置信息
     *
     * @param config
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public BaseResponse add(CatchOrderConfig config) {
        try {
            catchOrderConfigService.save(config);
        } catch (DuplicateKeyException e) {
            //唯一索引冲突报错
            return BaseResponse.responseFailure("抓单编码【" + config.getCode() + "】配置已存在");
        }
        return BaseResponse.responseSuccess("接单配置新增成功");
    }

    /**
     * 跳转到编辑的页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toEdit/{id}")
    @RequiresPermissions("config.catchOrderConfig.edit")
    public String toEdit(@PathVariable("id") Long id, Model model) {
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectById(id);
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        model.addAttribute("storeList", storeService.findAll());
        model.addAttribute("catchOrderConfig", catchOrderConfig);
        return "config/catchOrderConfig/catchOrderConfig_edit.html";
    }

    /**
     * 编辑转单配置
     *
     * @param config
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public BaseResponse edit(CatchOrderConfig config) {
        try {
            catchOrderConfigService.edit(config);
        } catch (DuplicateKeyException e) {
            //唯一索引冲突报错
            return BaseResponse.responseFailure("抓单编码【" + config.getCode() + "】配置已存在");
        }
        return BaseResponse.responseSuccess("接单配置修改成功");
    }
}
