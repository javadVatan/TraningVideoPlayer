package com.academy.ferdowsi.training.video.struct;


public class StructCategorySub {
    private String categoryName;
    private int image;
    private String url;

    public StructCategorySub() {
    }

    public StructCategorySub(String categoryName, int image, String url, int flag) {
        this.categoryName = categoryName;
        this.image = image;
        this.url = url;
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

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
