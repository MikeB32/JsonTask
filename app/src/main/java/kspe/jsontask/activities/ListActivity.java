package kspe.jsontask.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import kspe.jsontask.R;

import kspe.jsontask.adapters.RecyclerViewAdapter;
import kspe.jsontask.model.DummyContent;
import kspe.jsontask.constants.SharedPrefSingleton;
import kspe.jsontask.services.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ListActivity extends AppCompatActivity {


    static {
        System.loadLibrary("native-lib");
    }
    // this String if for the url that no one can know about it if they try to hack the application
    //they can't reach it
    public native String stringFromJNI();


    private RecyclerView recyclerView;
    private Parcelable recyclerViewState;


    private boolean mTwoPane =false;
    private RequestQueue queue;
    private Boolean isScrolling= false;
    private int currentItem , totalItems , scrollOutItems;
    private ProgressBar progressBar;
    private LinearLayoutManager mLayoutManager;
    //basic info
    private String capital_full_name,full_name,phoneNo,gender,profile_pic,email,cell;
    //location info
    private String street,city,state,postcode,latitude,longitude,offset,description;
    //login info
    private String uuid,username,password,salt,md5,sha1,sha256;
    //dob info
    private String dobDate,dobAge;
    //registered info
    private String registerDate,registerAge;
    // id info
    private String idName,idValue;
    //natonality
    private String nat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //checking the screenSizes to make the layout landscape or portrait
        if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        setContentView(R.layout.activity_list);
        queue = Volley.newRequestQueue(this);
        SharedPrefSingleton.init(getApplicationContext());

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        progressBar = findViewById(R.id.progress_bar);


        recyclerView = findViewById(R.id.item_list);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        assert recyclerView != null;

        if (findViewById(R.id.detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
            SharedPrefSingleton.write(SharedPrefSingleton.LAYOUT,mTwoPane);
        }

        //loading the data with volley
        connectionVolley();






//        refresh the list for new data (infinity list) by swiping
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItem = mLayoutManager.getChildCount();
                totalItems = mLayoutManager.getItemCount();
                scrollOutItems = mLayoutManager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItem + scrollOutItems == totalItems)){
                    isScrolling = false;
                    //add new data to the list after swiping
                    fetchData();

                }
            }
        });

    }


    private void fetchData(){
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                connectionVolley();
                recyclerView.getAdapter().notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);

            }
        },1000);
    }



    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(new RecyclerViewAdapter(getApplicationContext(),DummyContent.ITEMS));
    }


    public void connectionVolley(){
        progressBar.setVisibility(View.VISIBLE);
        final String url = stringFromJNI();

        // prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject obj = jsonArray.getJSONObject(i);
                                gender = obj.getString("gender");



                                full_name = obj.getJSONObject("name").getString("title") +" "+
                                        obj.getJSONObject("name").getString("first") +" "+
                                        obj.getJSONObject("name").getString("last");

                                //make captial first and last names;
                                capital_full_name = capitalize(full_name);

                                phoneNo =obj.getString("phone");
                                profile_pic = obj.getJSONObject("picture").getString("large");
                                email = obj.getString("email");
                                cell=obj.getString("cell");

                                //parse location jsons
                                street =obj.getJSONObject("location").getString("street");
                                state = obj.getJSONObject("location").getString("state");
                                city = obj.getJSONObject("location").getString("city");
                                postcode =obj.getJSONObject("location").getString("postcode");
                                latitude =obj.getJSONObject("location").getJSONObject("coordinates").getString("latitude");
                                longitude =obj.getJSONObject("location").getJSONObject("coordinates").getString("longitude");
                                offset =obj.getJSONObject("location").getJSONObject("timezone").getString("offset");
                                description =obj.getJSONObject("location").getJSONObject("timezone").getString("description");


                                //login info
                                uuid = obj.getJSONObject("login").getString("uuid");
                                username = obj.getJSONObject("login").getString("username");
                                password = obj.getJSONObject("login").getString("password");
                                salt = obj.getJSONObject("login").getString("salt");
                                md5 = obj.getJSONObject("login").getString("md5");
                                sha1 = obj.getJSONObject("login").getString("sha1");
                                sha256 = obj.getJSONObject("login").getString("sha256");

                                //dob
                                dobDate = obj.getJSONObject("dob").getString("date");
                                dobAge = obj.getJSONObject("dob").getString("age");

                                //register
                                registerDate = obj.getJSONObject("registered").getString("date");
                                registerAge = obj.getJSONObject("registered").getString("age");

                                //id
                                idName = obj.getJSONObject("id").getString("name");
                                idValue = obj.getJSONObject("id").getString("value");

                                //nationality
                                nat = obj.getString("nat");



                                DummyContent.addItem(new DummyContent.DummyItem(profile_pic,capital_full_name,gender,email,phoneNo,cell,
                                        street,city,state,postcode,latitude,longitude,offset,description,
                                        uuid,username,password,salt,md5,sha1,sha256,
                                        dobDate,dobAge,
                                        registerDate,registerAge,
                                        idName,idValue,
                                        nat));

                            }
                            recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();

                            recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
                            setupRecyclerView(recyclerView);
                            recyclerView.getAdapter().notifyDataSetChanged();

                            progressBar.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                        Toast.makeText(getApplicationContext(),"check internet connection",Toast.LENGTH_SHORT).show();

                    }
                }
        );

        // add it to the RequestQueue
        queue.add(getRequest);
    }


    private String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }




}
