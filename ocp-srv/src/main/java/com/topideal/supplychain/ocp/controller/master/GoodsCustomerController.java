package com.topideal.supplychain.ocp.controller.master;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.master.model.GoodsCustomerConfig;
import com.topideal.supplychain.ocp.master.service.GoodsCustomerService;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商品业主
 *
 * @author xuxiaoyan
 * @date 2019-11-14 16:24
 */
@Controller
@RequestMapping("/goodsCustomer")
public class GoodsCustomerController extends BaseController{

    @Autowired
    private GoodsCustomerService goodsCustomerService;
    @Autowired
    private MerchantService merchantService;

    /**
     * 初始化
     * @return
     */
    @RequestMapping(value = "")
    @RequiresPermissions("master.goodsCustomer")
    public String list() {
        return "master/goodsCustomer/goodsCustomer_list.html";
    }


    /**
     * 页面查询
     * @param goodsCustomerConfig
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("master.goodsCustomer.query")
    public String json(@ModelAttribute("goodsCustomerConfig") GoodsCustomerConfig goodsCustomerConfig, @RequestParam("page") Optional<Integer> page,
            @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<GoodsCustomerConfig> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(5)).doSelectPageInfo(
                        () -> goodsCustomerService.pageList(goodsCustomerConfig));
        return convertPageJson(pageInfo);
    }

    /**
     * 新增页面
     * @param model
     * @return
     */
    @RequestMapping("/toAdd")
    @RequiresPermissions("master.goodsCustomer.add")
    public String toAdd(Model model) {
        // 电商企业
        model.addAttribute("merchantList", merchantService.findAll());
        return "master/goodsCustomer/goodsCustomer_add.html";
    }

    /**
     * 新增
     * @param goodsCustomerConfig
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("master.goodsCustomer.add")
    @ResponseBody
    public BaseResponse add(GoodsCustomerConfig goodsCustomerConfig) {
        // 必填校验
        String msg = validate(goodsCustomerConfig);
        if (StringUtils.isNotEmpty(msg)) {
            return BaseResponse.responseFailure(msg);
        }

        // 新增幂等校验：企业编码+商品货号+关区+贸易模式+商品名称
        List<GoodsCustomerConfig> oldConfigList = goodsCustomerService
                .selectByFilter(goodsCustomerConfig);
        if (!CollectionUtils.isEmpty(oldConfigList)) {
            return BaseResponse.responseFailure("该货号"+ goodsCustomerConfig.getGoodsCode() +"已存在相同的配置");
        }
        // 填充企业名称
        goodsCustomerConfig.setEnterpriseName(merchantService.findByCode(goodsCustomerConfig.getEnterpriseCode()).getName());
        goodsCustomerService.insert(goodsCustomerConfig);

        return BaseResponse.responseSuccess("操作成功");

    }

    /**
     * 编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/toEdit/{id}", method = RequestMethod.POST)
    @RequiresPermissions("master.goodsCustomer.update")
    public String toEdit(@PathVariable("id") Long id,
            Model model) {
        GoodsCustomerConfig goodsCustomerConfig = goodsCustomerService.selectById(id);
        model.addAttribute("goodsCustomerConfig", goodsCustomerConfig);
        // 电商企业
        model.addAttribute("merchantList", merchantService.findAll());
        return "master/goodsCustomer/goodsCustomer_edit.html";
    }

    /**
     * 编辑
     * @param goodsCustomerConfig
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresPermissions("master.goodsCustomer.update")
    @ResponseBody
    public BaseResponse edit(GoodsCustomerConfig goodsCustomerConfig) {
        // 校验
        String msg = validate(goodsCustomerConfig);
        if (StringUtils.isNotEmpty(msg)) {
            return BaseResponse.responseFailure(msg);
        }
        // 编辑幂等校验：企业编码+商品货号+关区+贸易模式+商品名称
        List<GoodsCustomerConfig> oldConfigList = goodsCustomerService
                .selectByFilter(goodsCustomerConfig);
        if (!CollectionUtils.isEmpty(oldConfigList)) {
            return BaseResponse.responseFailure("该货号"+ goodsCustomerConfig.getGoodsCode() +"已存在相同的配置");
        }
        goodsCustomerConfig.setEnterpriseName(merchantService.findByCode(goodsCustomerConfig.getEnterpriseCode()).getName());
        goodsCustomerService.update(goodsCustomerConfig);

        return BaseResponse.responseSuccess("操作成功");
    }

    /**
     * 批量逻辑删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @RequiresPermissions("master.goodsCustomer.remove")
    @ResponseBody
    public BaseResponse remove(String ids) {
        String[] idArray = ids.split(",");
        List<String> idList = Arrays.asList(idArray);

        goodsCustomerService.batchRemove(idList);
        return BaseResponse.responseSuccess("操作成功");
    }

    /**
     * 字段校验
     * @param goodsCustomerConfig
     * @return
     */
    private String validate(GoodsCustomerConfig goodsCustomerConfig) {
        if (StringUtils.isEmpty(goodsCustomerConfig.getEnterpriseCode())) {
            return "企业编码为空！";
        }
        if (null == goodsCustomerConfig.getCustomerCode()) {
            return "关区代码为空！";
        }
        if (null == goodsCustomerConfig.getBusiMode()) {
            return "贸易模式为空！";
        }
        if (StringUtils.isEmpty(goodsCustomerConfig.getGoodsCode())) {
            return "商品货号为空！";
        }
        if (StringUtils.isEmpty(goodsCustomerConfig.getGoodsName())) {
            return "商品名称为空！";
        }
        return "";
    }

    /**
     * 上传文件弹框
     */
    @RequestMapping("/toImport")
    @RequiresPermissions("master.goodsCustomer.import")
    public String toImport() {
        return "master/goodsCustomer/goodsCustomer_upload.html";
    }


    /**
     * 导入
     */
    @RequestMapping("/import")
    @ResponseBody
    @RequiresPermissions("master.goodsCustomer.import")
    public BaseResponse importExcel(@RequestParam("excel") MultipartFile myFileName)
            throws Exception {
        // 获取文件名，校验文件格式
        String originalFilename = myFileName.getOriginalFilename();
        BusinessAssert.assertNotEmpty(originalFilename, "请选择需要上传的文件！");
        if (!StringUtils.endsWithIgnoreCase(originalFilename, ".xls")
                && !StringUtils.endsWithIgnoreCase(originalFilename, ".xlsx")) {
            return BaseResponse.responseFailure("文件格式不正确，请上传.xls或.xlsx格式文件！");
        }
        String message = goodsCustomerService.importDatas(myFileName.getInputStream());
        return StringUtils.isEmpty(message) ? BaseResponse
                .responseSuccess("操作成功") :
                BaseResponse.responseFailure(message);
    }


}
