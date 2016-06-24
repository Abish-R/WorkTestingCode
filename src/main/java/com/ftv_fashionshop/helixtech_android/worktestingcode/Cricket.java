package com.ftv_fashionshop.helixtech_android.worktestingcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by helixtech-android on 22/6/16.
 */
public class Cricket extends AppCompatActivity {
    private static RecyclerView cricket;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cricket);

        cricket = (RecyclerView) findViewById(R.id.cricket);
        cricket.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        cricket.setLayoutManager(layoutManager);
        cricket.setItemAnimator(new DefaultItemAnimator());

        ArrayList<Pogo> list = new ArrayList<Pogo>();
        ParseJSONFromRawFolder.parser(this);

// //       for(int i=0; i<20; i++) {
//            Pogo p = new Pogo();
//            if(i%10==0)
//                p.setOne("OOOOOOOOOOOOOOOOOOOOOO");
//            if(i%2==0 || i%5 == 0)
//                p.setThree("THTHTHTHTHTHTHTHTH");
//            if(i%5==0)
//                p.setTwo("TTTTTTTTTTTTTTTTTTT");
//            list.add(p);
//        }
//        passToAdapter(list);
    }

    public void passToAdapter(ArrayList<Pogo> data){
        CricketCustomAdapter adapter = new CricketCustomAdapter(data);
        cricket.setAdapter(adapter);
    }
}
