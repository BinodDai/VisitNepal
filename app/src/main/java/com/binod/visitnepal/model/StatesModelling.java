package com.binod.visitnepal.model;

public class StatesModelling {

    private String provinceName;
    private String provinceImage;

    public StatesModelling() {

    }

    public StatesModelling(String provinceName, String provinceImage) {
        this.provinceName = provinceName;
        this.provinceImage = provinceImage;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceImage() {
        return provinceImage;
    }

    public void setProvinceImage(String provinceImage) {
        this.provinceImage = provinceImage;
    }
}

