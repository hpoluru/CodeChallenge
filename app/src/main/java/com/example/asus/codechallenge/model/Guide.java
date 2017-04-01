package com.example.asus.codechallenge.model;

import java.io.Serializable;

/**
 * Created by ASUS on 31-03-2017.
 */

public class Guide implements Serializable {
     String endDate, name, imgurl;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
