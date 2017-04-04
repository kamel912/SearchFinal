package com.mk.searchfinal;

import android.content.Context;
import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MK on 04/04/2017.
 */

public class UserData extends AsyncTask<String,String,String> {
    Context context;
    String searchQuery;
    HttpURLConnection conn;
    URL url = null;

    public UserData(Context context, String searchQuery) {
        this.context = context;
        this.searchQuery = searchQuery;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            url =new URL("")
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
