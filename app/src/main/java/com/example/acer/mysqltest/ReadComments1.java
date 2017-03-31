package com.example.acer.mysqltest;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadComments1 extends ListActivity implements Serializable
{

    //private static final String PREFS = "examplePrefs";
    public static String myMob1;// = "9845879352";
    //public static String myMob = "" + myMob1;
    //private static String myMob = myMob1;
    // Progress Dialog
    private ProgressDialog pDialog;
    ArrayList<String> pnames = new ArrayList<String>();
    ArrayList<String> pamt =  new ArrayList<String>();
    ArrayList<String> pdate =  new ArrayList<String>();


    //testing on Emulator:

    //testing from a real server:
   // private static final String READ_COMMENTS_URL = "http://192.168.1.7:80/webservice/comments.php?Mobile=" + myMob;
    // Uri.encode(myMob1);//myMob1;//"9845879352";
    private static String READ_COMMENTS_URL;

    //JSON IDS:
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_POSTS = "posts";
    private static final String TAG_PNAME = "pcat";
    private static final String TAG_AMT = "amt";
    private static final String TAG_DATE = "date";
    //it's important to note that the message is both in the parent branch of
    //our JSON tree that displays a "Post Available" or a "No Post Available" message,
    //and there is also a message for each individual post, listed under the "posts"
    //category, that displays what the user typed as their message.

    //An array of all of our comments
    private JSONArray mComments = null;
    //manages all of our comments in a list.
    private ArrayList<HashMap<String, String>> mCommentList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);        //note that use read_comments.xml instead of our single_post.xml
        setContentView(R.layout.read_comments);

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        myMob1 = sharedPreferences.getString("Mobile","NA");
        READ_COMMENTS_URL = "http://192.168.1.100:80/webservice/comments1.php?Mobile=" + myMob1;
        /*if(myMob1.equals("NA")){
            Toast.makeText(this,"FAILLL",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,myMob1,Toast.LENGTH_LONG).show();
            READ_COMMENTS_URL = "http://192.168.1.102:80/webservice/comments.php?Mobile=" + myMob1;
        }*/
    }

    public void pieGraphHandler (View view)
    {
        Intent lineIntent = new Intent(ReadComments1.this ,PieC.class);
        //putextra send the arraylist to pieC
        lineIntent.putExtra("array_list", pnames);
        lineIntent.putExtra("array_list1", pamt);
        startActivity(lineIntent);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //loading the comments via AsyncTask
        new LoadComments().execute();
    }

    /**
     * Retrieves json data of comments
     */
    public void updateJSONdata() {

        mCommentList = new ArrayList<HashMap<String, String>>();

        // Bro, it's time to power up the J parser
        JSONParser jParser = new JSONParser();
        // Feed the beast our comments url, and it spits us
        //back a JSON object.  Boo-yeah Jerome.
        JSONObject json = jParser.getJSONFromUrl(READ_COMMENTS_URL);

        try
        {
                mComments = json.getJSONArray(TAG_POSTS);


                // looping through all posts according to the json object returned
                for (int i = 0; i < mComments.length(); i++)
                {
                    JSONObject c = mComments.getJSONObject(i);

                    //gets the content of each tag
                    String pname = c.getString(TAG_PNAME);
                    String amt = c.getString(TAG_AMT);
                    String date = c.getString(TAG_DATE);

                    //we are adding elements to the arraylist and sending it to pieC
                    pnames.add(pname);
                    pamt.add(amt);
                    pdate.add(date);

                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put(TAG_PNAME, pname);
                    map.put(TAG_AMT, amt);
                    map.put(TAG_DATE, date);

                    // adding HashList to ArrayList
                    mCommentList.add(map);
                    //annndddd, our JSON data is up to date same with our array list

                }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Inserts the parsed data into our listview
     */
    private void updateList() {

        ListAdapter adapter = new SimpleAdapter(this, mCommentList,
                R.layout.single_post1, new String[] { TAG_PNAME,
                TAG_AMT, TAG_DATE }, new int[] { R.id.pname, R.id.amt, R.id.date });

        // I shouldn't have to comment on this one:
        setListAdapter(adapter);

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });
    }

    public class LoadComments extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ReadComments1.this);
            pDialog.setMessage("Loading Comments...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... arg0) {
            //we will develop this method in version 2
            updateJSONdata();
            return null;

        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            //we will develop this method in version 2
            updateList();
        }
    }
}

