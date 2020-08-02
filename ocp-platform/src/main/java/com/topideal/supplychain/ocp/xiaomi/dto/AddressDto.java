package com.topideal.supplychain.ocp.xiaomi.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/30
 * @description
 **/
public class AddressDto implements Serializable {
    private String add_time;
    private String address;
    private String address_id;
    private String address_name;
    private BeanDto area;
    private BeanDto city;
    private String consignee;
    private String consignee_idx;
    private BeanDto country;
    private BeanDto district;
    private String email;
    private BeanDto province;
    private String tag_name;
    private String tel;
    private String tel_idx;
    private String update_time;
    private String used_count;
    private String zipcode;
    private Integer is_invalid;
    private String type;
    private Integer need_edit;
    private List<String> matching;



    /**
     * 有id 和name的实体
     */
  public  static class BeanDto {
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public BeanDto getArea() {
        return area;
    }

    public void setArea(BeanDto area) {
        this.area = area;
    }

    public BeanDto getCity() {
        return city;
    }

    public void setCity(BeanDto city) {
        this.city = city;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsignee_idx() {
        return consignee_idx;
    }

    public void setConsignee_idx(String consignee_idx) {
        this.consignee_idx = consignee_idx;
    }

    public BeanDto getCountry() {
        return country;
    }

    public void setCountry(BeanDto country) {
        this.country = country;
    }

    public BeanDto getDistrict() {
        return district;
    }

    public void setDistrict(BeanDto district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BeanDto getProvince() {
        return province;
    }

    public void setProvince(BeanDto province) {
        this.province = province;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel_idx() {
        return tel_idx;
    }

    public void setTel_idx(String tel_idx) {
        this.tel_idx = tel_idx;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUsed_count() {
        return used_count;
    }

    public void setUsed_count(String used_count) {
        this.used_count = used_count;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Integer getIs_invalid() {
        return is_invalid;
    }

    public void setIs_invalid(Integer is_invalid) {
        this.is_invalid = is_invalid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNeed_edit() {
        return need_edit;
    }

    public void setNeed_edit(Integer need_edit) {
        this.need_edit = need_edit;
    }

    public List<String> getMatching() {
        return matching;
    }

    public void setMatching(List<String> matching) {
        this.matching = matching;
    }
}
