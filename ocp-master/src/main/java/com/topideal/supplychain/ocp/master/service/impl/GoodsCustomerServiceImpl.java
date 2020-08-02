package com.topideal.supplychain.ocp.master.service.impl;

import com.google.common.collect.Lists;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.CustomsCodeEnum;
import com.topideal.supplychain.ocp.master.mapper.GoodsCustomerMapper;
import com.topideal.supplychain.ocp.master.model.GoodsCustomerConfig;
import com.topideal.supplychain.ocp.master.service.GoodsCustomerService;
import com.topideal.supplychain.ocp.utils.MyPOIUtils;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import java.io.InputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 商品业主
 * @author xuxiaoyan
 * @date 2019-11-14 16:35
 */
@Service
public class GoodsCustomerServiceImpl implements GoodsCustomerService{

    private static final Integer COLUMN_SIZE = 6;

    @Autowired
    private GoodsCustomerMapper goodsCustomerMapper;
    @Autowired
    private GoodsCustomerService goodsCustomerService;

    /**
     * 分页查询
     * @param goodsCustomerConfig
     * @return
     */
    @Override
    public List<GoodsCustomerConfig> pageList(GoodsCustomerConfig goodsCustomerConfig) {
        return goodsCustomerMapper.pageList(goodsCustomerConfig);
    }

    @Override
    @Transactional
    public void insert(GoodsCustomerConfig goodsCustomerConfig) {
        goodsCustomerConfig.setCreateBy(Authentication.getUserId());
        goodsCustomerConfig.setUpdateBy(Authentication.getUserId());
        goodsCustomerConfig.setCreateTime(DateUtils.getNowTime());
        goodsCustomerConfig.setUpdateTime(DateUtils.getNowTime());
        goodsCustomerMapper.insert(goodsCustomerConfig);
    }

    @Override
    public GoodsCustomerConfig selectById(Long id) {
        return goodsCustomerMapper.selectById(id);
    }

    @Override
    @Transactional
    public void update(GoodsCustomerConfig goodsCustomerConfig) {
        goodsCustomerConfig.setUpdateBy(Authentication.getUserId());
        goodsCustomerConfig.setUpdateTime(DateUtils.getNowTime());
        goodsCustomerMapper.update(goodsCustomerConfig);
    }

    @Override
    @Transactional
    public void batchRemove(List<String> idList) {
        goodsCustomerMapper.batchRemove(idList, Authentication.getUserId(), DateUtils.getNowTime());
    }

    /**
     * 导入:
     * 1.支持批量
     * 2.重复校验：单行数据去数据库查询，如果能查到数据，则不导入
     * @param inputStream
     * @return
     */
    @Override
    @Transactional
    public String importDatas(InputStream inputStream) throws Exception{
        StringBuilder sb = new StringBuilder();
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(inputStream);
            Sheet sheet = wb.getSheetAt(0);
            List<GoodsCustomerConfig> datas = Lists.newArrayList();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                int num = MyPOIUtils.CheckRowNull(sheet.getRow(i), COLUMN_SIZE);
                // 判断行是否为空
                if (num == COLUMN_SIZE) {
                    continue;
                }

                String enterpriseName = MyPOIUtils.getStringCellValue(sheet.getRow(i).getCell(0));
                String enterpriseCode = MyPOIUtils.getStringCellValue(sheet.getRow(i).getCell(1));
                String busiModeName = MyPOIUtils.getStringCellValue(sheet.getRow(i).getCell(2));
                String goodsCode = MyPOIUtils.getStringCellValue(sheet.getRow(i).getCell(3));
                String goodsName = MyPOIUtils.getStringCellValue(sheet.getRow(i).getCell(4));
                String customCode = MyPOIUtils.getStringCellValue(sheet.getRow(i).getCell(5));

                GoodsCustomerConfig goodsCustomerConfig = new GoodsCustomerConfig();
                goodsCustomerConfig.setEnterpriseName(enterpriseName);
                goodsCustomerConfig.setEnterpriseCode(enterpriseCode);
                goodsCustomerConfig.setBusiMode(BusiModeEnum.getNameEnum(busiModeName));
                goodsCustomerConfig.setGoodsCode(goodsCode);
                goodsCustomerConfig.setGoodsName(goodsName);
                goodsCustomerConfig.setCustomerCode(CustomsCodeEnum.getValueEnum(customCode));
                // 重复校验 如果没有查到该数据才加入list
                List<GoodsCustomerConfig> oldList = this.selectByFilter(goodsCustomerConfig);
                if (oldList.size() < 1) {
                    datas.add(goodsCustomerConfig);
                }
            }

            if (CollectionUtils.isEmpty(datas)) {
                sb.append("该表格没有符合条件的数据！");
            } else {
                goodsCustomerService.batchInsert(datas);
            }

        } finally {
            if (null != wb) {
                wb.close();
            }
            inputStream.close();
        }
        return sb.toString();
    }

    @Override
    public List<GoodsCustomerConfig> selectByFilter(GoodsCustomerConfig goodsCustomerConfig) {
        return goodsCustomerMapper.selectByFilter(goodsCustomerConfig);
    }

    @Override
    @Transactional
    public void batchInsert(List<GoodsCustomerConfig> datas) {
        goodsCustomerMapper.batchInsert(datas, Authentication.getUserId(), DateUtils.getNowTime());
    }
}
