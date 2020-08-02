package com.topideal.supplychain.ocp.controller.master;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topideal.supplychain.common.controller.BaseController;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.master.dto.EsdStoreConfig;
import com.topideal.supplychain.ocp.master.dto.StoreDto;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.MerchantService;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.system.service.DictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("store")
@RequiresPermissions("master.store")
public class StoreController extends BaseController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private DictService dictService;

    @RequestMapping(value = "")
    public String list(Model model){
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        return "master/store/store_list";
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("master.store.query")
    public String json(StoreDto condition, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("rows") Optional<Integer> rows) {
        PageInfo<StoreDto> pageInfo =
                PageHelper.startPage(page.orElse(1), rows.orElse(10)).doSelectPageInfo(
                        () -> storeService.pageList(condition));
        return convertPageJson(pageInfo);
    }

    @RequestMapping("/enable")
    @RequiresPermissions("master.store.enable")
    @ResponseBody
    public BaseResponse enable(Long[] ids){
        BusinessAssert.assertNotNull(ids,"请选择至少一条数据");
        BusinessAssert.assertIsTrue(ids.length > 0,"请选择至少一条数据");
        storeService.enable(ids);
        return BaseResponse.responseSuccess("操作成功");
    }

    @RequestMapping("/disable")
    @RequiresPermissions("master.store.disable")
    @ResponseBody
    public BaseResponse disable(Long[] ids){
        BusinessAssert.assertNotNull(ids,"请选择至少一条数据");
        BusinessAssert.assertIsTrue(ids.length > 0,"请选择至少一条数据");
        storeService.disable(ids);
        return BaseResponse.responseSuccess("操作成功");
    }

    @RequestMapping("/remove")
    @RequiresPermissions("master.store.remove")
    @ResponseBody
    public BaseResponse remove(Long id){
        BusinessAssert.assertNotNull(id,"请求数据为空");
        BusinessAssert.assertNotNull(storeService.selectById(id),"数据不存在");
        storeService.removeById(id);
        return BaseResponse.responseSuccess("操作成功");
    }

    @RequestMapping("/toAdd")
    @RequiresPermissions("master.store.add")
    public String toAdd(Model model){
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        model.addAttribute("busiModeList", dictService.getAllByCode("business.mode"));
        EsdStoreConfig esdArguments = EsdStoreConfig.newBuilder().appId("").appKey("").overseaHouseCode("").dno("").build();
        model.addAttribute("esdArguments", JacksonUtils.toJSon(esdArguments));
        return "master/store/store_add";
    }
    @RequestMapping("/add")
    @RequiresPermissions("master.store.add")
    @ResponseBody
    public BaseResponse add(Store condition){
        BusinessAssert.assertNotNull(condition, "参数为空");
        BusinessAssert.assertNotEmpty(condition.getCode(), "店铺编码不能为空");
        //BusinessAssert.assertNotEmpty(condition.getAppId(), "appId不能为空");
        //BusinessAssert.assertNotEmpty(condition.getAppKey(), "店铺appKey不能为空");
        BusinessAssert.assertNotEmpty(condition.getPlatformCode(), "平台不能为空");
        BusinessAssert.assertNotEmpty(condition.getMerchantCode(), "企业不能为空");
        BusinessAssert.assertNotEmpty(condition.getBusiType(), "业务类型不能为空");
        /*Store old = storeService.selectByUniqueKey(condition.getPlatformCode(), condition.getMerchantCode(), condition.getCode(), condition.getOverseaHouseCode());
        */
        Store existStore = storeService.selectByCode(condition.getCode());
        BusinessAssert.assertIsNull(existStore, "店铺已存在");
        storeService.insert(condition);
        return BaseResponse.responseSuccess("操作成功");
    }

    @RequestMapping("/toUpdate")
    @RequiresPermissions("master.store.update")
    public String toUpdate(Model model, Long id){
        model.addAttribute("store", storeService.selectById(id));
        model.addAttribute("platformList", platformService.findAll());
        model.addAttribute("merchantList", merchantService.findAll());
        model.addAttribute("busiModeList", dictService.getAllByCode("business.mode"));
        return "master/store/store_update";
    }
    @RequestMapping("/update")
    @RequiresPermissions("master.store.update")
    @ResponseBody
    public BaseResponse update(Store condition){
        BusinessAssert.assertNotNull(condition, "参数为空");
        BusinessAssert.assertNotNull(condition.getId(), "数据不存在");
        BusinessAssert.assertNotEmpty(condition.getCode(), "店铺编码不能为空");
        //BusinessAssert.assertNotEmpty(condition.getAppId(), "appId不能为空");
        //BusinessAssert.assertNotEmpty(condition.getAppKey(), "店铺appKey不能为空");
        BusinessAssert.assertNotEmpty(condition.getPlatformCode(), "平台不能为空");
        BusinessAssert.assertNotEmpty(condition.getMerchantCode(), "企业不能为空");
        BusinessAssert.assertNotEmpty(condition.getBusiType(), "业务类型不能为空");
        Store oldStore = storeService.selectById(condition.getId());
        BusinessAssert.assertNotNull(oldStore, "数据不存在");
        Store existStore = storeService.selectByCode(condition.getCode());
        BusinessAssert.assertIsFalse((existStore != null && !existStore.getId().equals(oldStore.getId())), "店铺已存在");
        oldStore.setCode(condition.getCode());
        //oldStore.setOverseaHouseCode(condition.getOverseaHouseCode());
        //oldStore.setAppId(condition.getAppId());
        //oldStore.setAppKey(condition.getAppKey());
        oldStore.setPlatformCode(condition.getPlatformCode());
        oldStore.setMerchantCode(condition.getMerchantCode());
        oldStore.setBusiType(condition.getBusiType());
        oldStore.setArguments(condition.getArguments());
        oldStore.setEsdArguments(condition.getEsdArguments());
        storeService.updateAll(oldStore);
        return BaseResponse.responseSuccess("操作成功");
    }

    /**
     * 自动过滤补全
     * @param q
     * @return
     */
    @RequestMapping(value = "/autoCompletionStore")
    @ResponseBody
    public List<Store> autoCompletionStore(String q) {
        return storeService.autoCompletion(q);
    }
}
