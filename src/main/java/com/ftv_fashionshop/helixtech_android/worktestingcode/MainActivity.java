package com.ftv_fashionshop.helixtech_android.worktestingcode;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,MultiSpinner.multispinnerListener {

    AlertMessages am = new AlertMessages(this);
    Button btn,btn1,multidrop,multidrop1;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    ArrayList<Item> list= new ArrayList<Item>();
    List<String> list1= new ArrayList<String>();
    TextView text;
    Spinner _list;
    PopupWindow popupWindowDogs;
    boolean itemsStatus[];
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MultiSpinner ms = (MultiSpinner) findViewById(R.id.spinner);
        list1.add("two");
        list1.add("three");
        list1.add("four");
        list1.add("five");
        list1.add("six");
        list1.add("seven");
        list1.add("eight");
        list1.add("nine");
        list1.add("ten");
        ms.setItems(list1, "select", this);
        list.add(new SectionItem("My Friends"));
        list.add(new EntryItem("one"));
        list.add(new EntryItem("two"));
        list.add(new EntryItem("three"));
        list.add(new SectionItem("My Friends"));
        list.add(new EntryItem("four"));
        list.add(new EntryItem("five"));
        list.add(new EntryItem("six"));
        list.add(new EntryItem("seven"));
        list.add(new SectionItem("My Friends"));
        list.add(new EntryItem("eight"));
        list.add(new EntryItem("nine"));
        list.add(new EntryItem("ten"));



        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                am.customTwoAlert();
//                showCustomAlert();

                Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
                fab.startAnimation(shake);
                new AsyncPasswordChange(MainActivity.this).execute("");
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        initializeViews();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onItemschecked(boolean[] checked)
    {
       Toast.makeText(this,checked+"",Toast.LENGTH_LONG).show();
    }

    public void initializeViews(){
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);
        btn1 = (Button)findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        multidrop = (Button)findViewById(R.id.multidrop);
        multidrop.setOnClickListener(this);
        multidrop1 = (Button)findViewById(R.id.multidrop1);
        multidrop1.setOnClickListener(this);
        listview = (ListView)findViewById(R.id.listview);
        text = (TextView)findViewById(R.id.text);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.multidrop:
                popupWindowDogs = popupWindowDogs();
                popupWindowDogs.showAsDropDown(v, -5, 0);
                break;
            case R.id.multidrop1:
                EntryAdapter adapter = new EntryAdapter(this, list);
                listview.setAdapter(adapter);
                //listview.setOnItemClickListener(MainActivity.this);
                break;
            case R.id.btn:
                am.customTwoAlert();
                break;
            case R.id.btn2:
                ImageView image = (ImageView)findViewById(R.id.image);
                image.buildDrawingCache();
                Bitmap bm=image.getDrawingCache();
                OutputStream fOut = null;
                Uri outputFileUri;
                try {
                    File root = new File(Environment.getExternalStorageDirectory()
                            + File.separator );
                    root.mkdirs();
                    File sdImageMainDirectory = new File(root, "temp.jpg");
                    outputFileUri = Uri.fromFile(sdImageMainDirectory);
                    fOut = new FileOutputStream(sdImageMainDirectory);
                } catch (Exception e) {
                    Toast.makeText(this, "Error occured. Please try again later.",Toast.LENGTH_SHORT).show();
                }

                try {
                    bm.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                } catch (Exception e) {
                }

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                share.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory()
                        + File.separator+"temp.jpg"));
                share.putExtra(Intent.EXTRA_TEXT, "Abish");
                startActivity(Intent.createChooser(share, "Share Image"));
                break;
                //am.showCustomAlert();
        }
    }

    public PopupWindow popupWindowDogs() {

        // initialize a pop up window type
        PopupWindow popupWindow = new PopupWindow(this);

        // the drop down list is a list view
        ListView listViewDogs = new ListView(this);

        // set our adapter and pass our pop up window contents
        listViewDogs.setAdapter(dogsAdapter(list1));

        // set the item click listener
        //listViewDogs.setOnItemClickListener(new DogsDropdownOnItemClickListener());

        // some other visual settings
        popupWindow.setFocusable(true);
        popupWindow.setWidth(250);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // set the list view as pop up window content
        popupWindow.setContentView(listViewDogs);

        return popupWindow;
    }

    private ArrayAdapter<String> dogsAdapter(List<String> lt) {
        itemsStatus=new boolean[lt.size()];
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lt) {
            //CheckBox listItem1;
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {

                // setting the ID and text for every items in the list
                String item = getItem(position);
                String[] itemArr = item.split("::");
                String text = itemArr[0];
                String id = itemArr[0];

                // visual settings for the list item
                final CheckBox listItem1 = new CheckBox(MainActivity.this);
                    listItem1.setText(text);
                    listItem1.setTag(id);
                    listItem1.setTextSize(22);
                    listItem1.setPadding(10, 10, 10, 10);
                    listItem1.setTextColor(Color.WHITE);
                    listItem1.setChecked(itemsStatus[position]);



                listItem1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(listItem1.isChecked()) {
                            itemsStatus[position]=true;
                        }
                        else {
                            itemsStatus[position]=false;
                        }
                    }
                });

                return listItem1;
            }
        };

        return adapter;
    }

    public void passToAdapter(ArrayList<Pogo> data){
        CustomAdapter adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
    }


//    public void showCustomAlert()
//    {
//
//        // Create layout inflator object to inflate toast.xml file
//        LayoutInflater inflater = getLayoutInflater();
//
//        // Call toast.xml file for toast layout
//        View toastRoot = inflater.inflate(R.layout.toast, null);
//
//        Toast toast = new Toast(this);
//
//        // Set layout to toast
//        toast.setView(toastRoot);
//        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,
//                0, 0);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.show();
//
//    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this, CollapsingToolBarParallex.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, Cricket.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
//            RequestingLiveDataString rs  = new RequestingLiveDataString(this);
            GetResponseString();


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void GetResponseString(){
        // Tag used to cancel the request
        final String tag_json_obj = "string_req";

        String url = "http://synd.cricbuzz.com/j2me/1.0/sch_calender.xml";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest req = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        String KEY_ITEM = "mth"; // parent node
                        String KEY_ITEM1 = "country"; // sub parent node
                        String KEY_ITEM2 = "series"; // sub parent node
                        String KEY_NAME = "mch";
                        String KEY_COST = "desc";
                        String KEY_DESC = "srs";

                        //text.setText(response);

                        XMLParserForFixtures parse = new XMLParserForFixtures();
                        //Document doc = buildDocument(result);
                        Document doc = parse.getDomElement(response); // getting DOM element
                        NodeList nl = doc.getElementsByTagName(KEY_ITEM);
                        NodeList nl1 = doc.getElementsByTagName(KEY_ITEM1);
                        NodeList nl2 = doc.getElementsByTagName(KEY_ITEM2);
                        NodeList nl3 = doc.getElementsByTagName(KEY_NAME);
//                        Element e = (Element) nl.item(0);
//                        String test = e.getAttribute("mchs");
                        for (int i = 0; i < nl3.getLength(); i++) {
                            Element e1 = (Element) nl3.item(i);
                            String test11 =e1.getAttribute("desc");
                            String name = e1.getAttribute("srs");
                            String cost = e1.getAttribute("ddt");
                            String description = e1.getAttribute("vnu");
                            String time = e1.getAttribute("tm");
                            String mnth_yr = e1.getAttribute("mnth_yr");
                            //String mnth_yr = parse.getValue("mnth_yr");
                            Toast.makeText(getApplicationContext(),test11+"/n"+name+"/n"+cost+"/n"+description+"/n"+
                                    time+"/n"+mnth_yr,Toast.LENGTH_LONG).show();
                        }
                        pDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle error response
                        pDialog.hide();
                    }
                });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_json_obj);
        //return valiue;
    }
}
