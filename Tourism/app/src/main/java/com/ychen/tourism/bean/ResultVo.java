package com.ychen.tourism.bean;

import java.io.Serializable;

/**
 * Created by 72963 on 2015/10/26.
 */
public class ResultVo implements Serializable{
    private static final long serialVersionUID = -3333964891343854642L;
    private String name;//"古楼轩",
    private String navigation;//"北京餐厅>>东城区>>地安门>>北京菜>>家常菜>>古楼轩",
    private String city;//"北京",
    private String area;//"东城区",
    private String address;//"鼓楼东大街311号",
    private String phone;//"010-64055385",
    private String latitude;//"39.94087",
    private String longitude;//"116.3966",
    private String stars;//"2.0",
    private String avg_price;//"27",
    private String photos;//"http:\/\/i3.dpfile.com\/2008-10-29\/1108661_m.jpg",
    private String tags;//"家常菜,地安门,什刹海,休闲小憩",
    private String all_remarks;//"26",
    private String very_good_remarks;//"1",
    private String good_remarks;//"3",
    private String common_remarks;//"0",
    private String bad_remarks;//"0",
    private String very_bad_remarks;//"1",
    private String product_rating;//"5.7",
    private String environment_rating;//"5.2",
    private String service_rating;//"5.8",
    private String recommended_products;//"",
    private String recommended_dishes;//"炒肝,炸咯吱,卤煮,灌肠,包子,炒红果,炸灌肠,烧二冬,糊塌塌,肘子,肘子卷饼",
    private String nearby_shops;//""

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAll_remarks() {
        return all_remarks;
    }

    public void setAll_remarks(String all_remarks) {
        this.all_remarks = all_remarks;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(String avg_price) {
        this.avg_price = avg_price;
    }

    public String getBad_remarks() {
        return bad_remarks;
    }

    public void setBad_remarks(String bad_remarks) {
        this.bad_remarks = bad_remarks;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCommon_remarks() {
        return common_remarks;
    }

    public void setCommon_remarks(String common_remarks) {
        this.common_remarks = common_remarks;
    }

    public String getEnvironment_rating() {
        return environment_rating;
    }

    public void setEnvironment_rating(String environment_rating) {
        this.environment_rating = environment_rating;
    }

    public String getGood_remarks() {
        return good_remarks;
    }

    public void setGood_remarks(String good_remarks) {
        this.good_remarks = good_remarks;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }

    public String getNearby_shops() {
        return nearby_shops;
    }

    public void setNearby_shops(String nearby_shops) {
        this.nearby_shops = nearby_shops;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getProduct_rating() {
        return product_rating;
    }

    public void setProduct_rating(String product_rating) {
        this.product_rating = product_rating;
    }

    public String getRecommended_dishes() {
        return recommended_dishes;
    }

    public void setRecommended_dishes(String recommended_dishes) {
        this.recommended_dishes = recommended_dishes;
    }

    public String getRecommended_products() {
        return recommended_products;
    }

    public void setRecommended_products(String recommended_products) {
        this.recommended_products = recommended_products;
    }

    public String getService_rating() {
        return service_rating;
    }

    public void setService_rating(String service_rating) {
        this.service_rating = service_rating;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getVery_bad_remarks() {
        return very_bad_remarks;
    }

    public void setVery_bad_remarks(String very_bad_remarks) {
        this.very_bad_remarks = very_bad_remarks;
    }

    public String getVery_good_remarks() {
        return very_good_remarks;
    }

    public void setVery_good_remarks(String very_good_remarks) {
        this.very_good_remarks = very_good_remarks;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", navigation='" + navigation + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", phone='" + phone + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", stars='" + stars + '\'' +
                ", avg_price='" + avg_price + '\'' +
                ", photos='" + photos + '\'' +
                ", tags='" + tags + '\'' +
                ", all_remarks='" + all_remarks + '\'' +
                ", very_good_remarks='" + very_good_remarks + '\'' +
                ", good_remarks='" + good_remarks + '\'' +
                ", common_remarks='" + common_remarks + '\'' +
                ", bad_remarks='" + bad_remarks + '\'' +
                ", very_bad_remarks='" + very_bad_remarks + '\'' +
                ", product_rating='" + product_rating + '\'' +
                ", environment_rating='" + environment_rating + '\'' +
                ", service_rating='" + service_rating + '\'' +
                ", recommended_products='" + recommended_products + '\'' +
                ", recommended_dishes='" + recommended_dishes + '\'' +
                ", nearby_shops='" + nearby_shops + '\'' +
                '}';
    }
}
