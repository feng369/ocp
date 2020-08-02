package com.topideal.supplychain.ocp.controller.master;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.master.model.Merchant;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import java.util.List;
import java.util.Optional;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 电商平台页面
 *
 * @author hhl
 * @date 2018/12/27
 */
@Controller
@RequestMapping("platform")
public class PlatformController extends BaseController {

    @Autowired
    private PlatformService platformService;

    @RequestMapping(value = "")
    @RequiresPermissions("master.platform")
    public String list() {
        return "master/platform/platform_list.html";
    }


    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("master.platform.query")
    public String json(@ModelAttribute("platform") Platform platform, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<Platform> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> platformService.pageList(platform));
        return convertPageJson(pageInfo);
    }

    @RequiresPermissions("master.platform.add")
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "master/platform/platform_add.html";
    }

    @RequestMapping("/add")
    @ResponseBody
    public BaseResponse add(Platform platform) {
        platformService.add(platform);
        return BaseResponse.responseSuccess("新增成功");
    }

    @RequestMapping("/toEditArgs")
    @RequiresPermissions("master.platform.editArgs")
    public String toEditArgs(Long id, Model model){
        BusinessAssert.assertNotNull(id, "参数为空");
        Platform platform = platformService.findById(id);
        BusinessAssert.assertNotNull(platform, "数据不存在");
        model.addAttribute("platform", platform);
        return "master/platform/platform_editArgs";
    }

    @RequestMapping("/editArgs")
    @RequiresPermissions("master.platform.editArgs")
    @ResponseBody
    public BaseResponse editArgs(Platform platform) {
        BusinessAssert.assertNotNull(platform,"数据不存在");
        BusinessAssert.assertNotNull(platform.getId(), "数据不存在");
        Platform old = platformService.findById(platform.getId());
        BusinessAssert.assertNotNull(old, "数据不存在");
        Platform updateRecord = new Platform();
        updateRecord.setId(old.getId());
        updateRecord.setArguments(platform.getArguments());
        platformService.update(updateRecord);
        return BaseResponse.responseSuccess("操作成功");
    }

    @RequestMapping("/enable")
    @RequiresPermissions("master.platform.enable")
    @ResponseBody
    public BaseResponse enable(Long[] ids) {
        BusinessAssert.assertNotNull(ids,"请选择至少一条数据进行启用");
        BusinessAssert.assertIsTrue(ids.length > 0,"请选择至少一条数据进行启用");
        platformService.enable(ids);
        return BaseResponse.responseSuccess("操作成功");
    }

    @RequestMapping("/disable")
    @RequiresPermissions("master.platform.disable")
    @ResponseBody
    public BaseResponse disable(Long[] ids) {
        BusinessAssert.assertNotNull(ids,"请选择至少一条数据进行启用");
        BusinessAssert.assertIsTrue(ids.length > 0,"请选择至少一条数据进行启用");
        platformService.disable(ids);
        return BaseResponse.responseSuccess("操作成功");
    }

    /**
     * 自动过滤补全
     * @param q
     * @return
     */
    @RequestMapping(value = "/autoCompletionPlatform")
    @ResponseBody
    public List<Platform> autoCompletionPlatform(String q) {
        return platformService.autoCompletion(q);
    }

}
