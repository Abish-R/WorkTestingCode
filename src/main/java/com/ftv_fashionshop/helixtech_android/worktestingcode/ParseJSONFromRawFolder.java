package com.ftv_fashionshop.helixtech_android.worktestingcode;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by helixtech-android on 23/6/16.
 */
public class ParseJSONFromRawFolder {
public  static String json="{\"CricketHistory\":\"The sport\",\"cricket\":[{\"subtitle\":\"Ball Experiment\",\"text\":\"Some countries experimented with eight\"}]}";
    //"{\"Employee\":[{\"id\":\"01\",\"name\":\"Gopal Varma\",\"salary\":\"500000\"},{\"id\":\"02\",\"name\":\"Sairamkrishna\",\"salary\":\"500000\"}]}";
    public static void parser(Context con) {
        //Get Data From Text Resource File Contains Json Data.
        InputStream inputStream = con.getResources().openRawResource(R.raw.cricket);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("Text Data", byteArrayOutputStream.toString());
        try {
            // Parse the data into jsonobject to get original data in form of json.
            JSONObject jObject = new JSONObject(byteArrayOutputStream.toString());
            String jObjectResult = jObject.getString("CricketHistory");
            JSONArray jArray = jObject.getJSONArray("cricket");
            String cat_Id = "";
            String cat_name = "";
            ArrayList<String[]> data = new ArrayList<String[]>();
            for (int i = 0; i < jArray.length(); i++) {
                cat_Id = jArray.getJSONObject(i).getString("subtitle");
                cat_name = jArray.getJSONObject(i).getString("text");
                Log.v("Cat ID", cat_Id);
                Log.v("Cat Name", cat_name);
                data.add(new String[]{cat_Id, cat_name});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
