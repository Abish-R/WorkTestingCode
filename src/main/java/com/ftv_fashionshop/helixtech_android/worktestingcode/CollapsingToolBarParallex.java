package com.ftv_fashionshop.helixtech_android.worktestingcode;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by helixtech-android on 20/6/16.
 */
public class CollapsingToolBarParallex extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parallextoolbar);


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("I'm Robot");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.colorAccent));

        //Toast.makeText(this,getIntent().getIntExtra("dgf",0),Toast.LENGTH_LONG).show();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.collapsing_toolbar);
//        fab.setRippleColor(getResources().getColor(R.color.colorAccent));
    }
}
