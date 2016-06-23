package com.ftv_fashionshop.helixtech_android.worktestingcode;
/** Profit Key 1.0.0
 *  Purpose	   : Password Change AsyncTask
 *  Created by : Abish
 *  Created Dt :
 *  Modified on:
 *  Verified by:
 *  Verified Dt:
 */
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class AsyncPasswordChange extends AsyncTask<String, Void, String> {
    ProgressDialog progressDialog;
    private Context context;

    static final String KEY_ITEM = "item"; // parent node
    static final String KEY_NAME = "name";
    static final String KEY_COST = "cost";
    static final String KEY_DESC = "description";

    /**Constructor*/
    public AsyncPasswordChange(MainActivity conx){
        context=conx;
    }

    /** Loader screen  **/
    protected void onPreExecute() {
        super.onPreExecute();
        // do stuff before posting data
        progressDialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_LIGHT);
        progressDialog.setMessage("Loading. Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        progressDialog.show();
    }

    /** Function to handle background operations*/
    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        String response="";
        try {
                /* forming th java.net.URL object */
            URL url = new URL("http://api.androidhive.info/pizza/?format=xml");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            /** Add params to the urls */
//            Uri.Builder builder = new Uri.Builder()
//                    .appendQueryParameter("uuid", params[0])
//                    .appendQueryParameter("existing_password", params[1])
//                    .appendQueryParameter("new_password", params[2]);
//            String query = builder.build().getEncodedQuery();
//
//            //OutputStream os = urlConnection.getOutputStream();
//            DataOutputStream writer = new DataOutputStream(urlConnection.getOutputStream());
//
//            writer.writeBytes(query);
//            writer.flush();
//            writer.close();
//            writer.close();

            int statusCode = urlConnection.getResponseCode();
                /* 200 represents HTTP OK */
            if (statusCode ==  200) {
                String result;
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((result=br.readLine()) != null) {
                    response+=result;
                }
            }else{
                response = ""; //"Failed to fetch data!";
            }
        } catch (Exception e) {
            Log.d("", e.getLocalizedMessage());
        }
        return response; //"Failed to fetch data!";
    }

    /**Function execute last to update*/
    @Override
    protected void onPostExecute(String result) {
        // do stuff after posting data
        progressDialog.dismiss();

        XMLParser parse = new XMLParser();
        //Document doc = buildDocument(result);
        Document doc = parse.getDomElement(result); // getting DOM element
        NodeList nl = doc.getElementsByTagName(KEY_ITEM);
        // looping through all item nodes <item>

        ArrayList<Pogo> list = new ArrayList<Pogo>();

// looping through all item nodes <item>
        for (int i = 0; i < nl.getLength(); i++) {
            Element e = (Element) nl.item(i);

            String name = parse.getValue(e, KEY_NAME); // name child value
            String cost = parse.getValue(e, KEY_COST); // cost child value
            String description = parse.getValue(e, KEY_DESC); // description child value

            Pogo p = new Pogo();
            p.setOne(name);
            p.setTwo(cost);
            p.setThree(description);
            list.add(p);


            Toast.makeText( context, name+" "+cost+" "+description,Toast.LENGTH_LONG).show();
        }
        ((MainActivity)context).passToAdapter(list);
    }


}

/*public Document buildDocument(String result){
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(result));
            doc = db.parse(is);

        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        }
        // return DOM
        return doc;
    }*/

//    @Override
//    protected String doInBackground(String... params) {
//        InputStream inputStream = null;
//        HttpURLConnection urlConnection = null;
//        String response=null;
//        try {
//                /* forming th java.net.URL object */
//            URL url = new URL(StaticConstants.password_change_url);
//            urlConnection = (HttpURLConnection) url.openConnection();
//
//                 /* optional request header */
//            urlConnection.setRequestProperty("Content-Type", "application/json");
//
//                /* optional request header */
//            urlConnection.setRequestProperty("Accept", "application/json");
//
//                /* for Get request */
//            urlConnection.setRequestMethod("GET");
//            int statusCode = urlConnection.getResponseCode();
//
//                /* 200 represents HTTP OK */
//            if (statusCode ==  200) {
//                inputStream = new BufferedInputStream(urlConnection.getInputStream());
//                response =inputStream.toString();
//                //parseResult(response);
////                result = 1; // Successful
//            }else{
//                response = null; //"Failed to fetch data!";
//            }
//        } catch (Exception e) {
//            Log.d("", e.getLocalizedMessage());
//        }
//        return response; //"Failed to fetch data!";
//    }
