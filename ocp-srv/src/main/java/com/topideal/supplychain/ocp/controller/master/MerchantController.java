package com.topideal.supplychain.ocp.controller.master;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.EnableOrDisableEnum;
import com.topideal.supplychain.ocp.master.model.Merchant;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 电商企业页面
 *
 * @author hhl
 * @date 2018/12/27
 */
@Controller

@RequestMapping("/merchant")
public class MerchantController extends BaseController {

    @Autowired
    private MerchantService merchantService;

    /**
     * 初始化
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    @RequiresPermissions("master.merchant")
    public String list(Model model) {
        return "master/merchant/merchant_list.html";
    }

    /**
     * 查询
     * @param merchant
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("master.merchant.query")
    public String json(@ModelAttribute("merchant") Merchant merchant, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<Merchant> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> merchantService.findBySelective(merchant));
        return convertPageJson(pageInfo);
    }
    @RequiresPermissions("master.merchant.add")
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "master/merchant/merchant_add.html";
    }

    @RequestMapping("/add")
    @ResponseBody
    public BaseResponse add(Merchant merchant) {
        merchantService.add(merchant);
        return BaseResponse.responseSuccess("新增成功");
    }
    
    /**
     * 启用
     */
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    @RequiresPermissions("master.merchant.enable")
    @ResponseBody
    public BaseResponse enable(String ids) {
        List<String> idList = new ArrayList<>();
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            idList = Arrays.asList(split);
    }
        merchantService.updateStatus(Long.valueOf(idList.get(0)), EnableOrDisableEnum.ENABLE.getValue());
        return BaseResponse.responseSuccess("操作成功");
    }

    /**
     * 禁用
     */
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    @RequiresPermissions("master.merchant.disable")
    @ResponseBody
    public BaseResponse disable(String ids) {
        List<String> idList = new ArrayList<>();
        if (!StringUtils.isEmpty(ids)) {
            String[] split = ids.split(",");
            idList = Arrays.asList(split);
    }
        merchantService.updateStatus(Long.valueOf(idList.get(0)), EnableOrDisableEnum.DISABLE.getValue());
        return BaseResponse.responseSuccess("操作成功");
}

    /**
     * 编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/toEdit/{id}", method = RequestMethod.POST)
    @RequiresPermissions("master.merchant.update")
    public String toEdit(@PathVariable("id") Long id,
            Model model) {
        Merchant merchant = merchantService.findById(id);
        model.addAttribute("merchant", merchant);
        return "master/merchant/merchant_update.html";
    }

    /**
     * 编辑
     * @param merchant
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @RequiresPermissions("master.merchant.update")
    @ResponseBody
    public BaseResponse update(Merchant merchant) {
        merchantService.update(merchant);
        return BaseResponse.responseSuccess("操作成功");
    }

    @RequestMapping(value = "/autoCompletionMerchant")
    @ResponseBody
    public List<Merchant> autoCompletionMerchant(String q) {
        return merchantService.autoCompletion(q);
    }
}
