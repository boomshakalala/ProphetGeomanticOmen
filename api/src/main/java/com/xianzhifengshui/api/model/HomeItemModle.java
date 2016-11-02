package com.xianzhifengshui.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/27.
 * 描述: 主页模型
 */
public class HomeItemModle implements Serializable {
    private static final long serialVersionUID = -292618941496512047L;

    private List<City> cityList ;

    private List<Carousel> carouselList ;

    private List<NaviMenu> naviMenuList ;

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public List<Carousel> getCarouselList() {
        return carouselList;
    }

    public void setCarouselList(List<Carousel> carouselList) {
        this.carouselList = carouselList;
    }

    public List<NaviMenu> getNaviMenuList() {
        return naviMenuList;
    }

    public void setNaviMenuList(List<NaviMenu> naviMenuList) {
        this.naviMenuList = naviMenuList;
    }

    @Override
    public String toString() {
        return "HomeItemModle{" +
                "cityList=" + cityList +
                ", carouselList=" + carouselList +
                ", naviMenuList=" + naviMenuList +
                '}';
    }
}
