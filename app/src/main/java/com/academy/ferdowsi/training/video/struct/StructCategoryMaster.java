package com.academy.ferdowsi.training.video.struct;

import java.util.ArrayList;

public class StructCategoryMaster {
    public ArrayList<StructCategorySub> list;
    private String title;

    public StructCategoryMaster(String title, ArrayList<StructCategorySub> list) {
        this.title = title;
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<StructCategorySub> getList() {
        return list;
    }

}
