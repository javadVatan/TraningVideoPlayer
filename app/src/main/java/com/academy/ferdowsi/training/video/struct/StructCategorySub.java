package com.academy.ferdowsi.training.video.struct;


public class StructCategorySub {
    private String categoryName;
    private int image;
    private String url;
    private int flag;

    public StructCategorySub(String categoryName, int image, String url, int flag) {
        this.categoryName = categoryName;
        this.image = image;
        this.url = url;
        this.flag = flag;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }

    public int getFlag() {
        return flag;
    }
}
