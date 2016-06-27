package com.ftv_fashionshop.helixtech_android.worktestingcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by helixtech-android on 22/6/16.
 */
public class Cricket extends AppCompatActivity implements View.OnClickListener {
    private static RecyclerView cricket;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    TextView text;
    Button one,two;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cricket);

        text=(TextView) findViewById(R.id.text);
        one=(Button) findViewById(R.id.one);
        two=(Button) findViewById(R.id.two);
        one.setOnClickListener(this);
        two.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.one:
                One o = new One();
                o.Test();
                break;
            case R.id.two:
//                Testing t = new Testing() {
//                    @Override
//                    public void Test() {
//                        text.setText("Called Three");
//                    }
//                };
//                t.Test();
//                Two t = new Two();
//                t.Test();
                Thread startThread = new Thread(new Task("start"));
                Thread runThread = new Thread(new Task("run"));

                startThread.start(); //calling start method of Thread - will execute in new Thread
                runThread.start();

                break;
        }
    }

    public class One implements Testing{
        @Override
        public void Test() {
            text.setText("Called One");
        }
    }

    public class Two implements Testing{
        @Override
        public void Test() {
            text.setText("Called Two");
        }
    }

    private static class Task implements Runnable{
        private String caller;

        public Task(String caller){
            this.caller = caller;
        }

        @Override
        public void run() {
            Log.v("Log is: ","Caller: "+ caller + " and code on this Thread is executed by : " + Thread.currentThread().getName());

        }
    }

}
