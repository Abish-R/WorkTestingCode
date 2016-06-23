package com.ftv_fashionshop.helixtech_android.worktestingcode;

/**
 * Created by helixtech-android on 13/6/16.
 */
public class SectionItem implements Item{

    private final String title;

    public SectionItem(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    @Override
    public boolean isSection() {
        return true;
    }
}
