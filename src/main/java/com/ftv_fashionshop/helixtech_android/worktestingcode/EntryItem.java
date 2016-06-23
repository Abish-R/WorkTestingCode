package com.ftv_fashionshop.helixtech_android.worktestingcode;


/**
 * Created by helixtech-android on 13/6/16.
 */
public class EntryItem implements Item {
    public final String title;
    //public final String subtitle;

    public EntryItem(String title){//}, String subtitle) {
        this.title = title;
        //this.subtitle = subtitle;
    }

    @Override
    public boolean isSection() {
        return false;
    }
}