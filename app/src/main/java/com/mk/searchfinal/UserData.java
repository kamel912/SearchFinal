package com.mk.searchfinal;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import com.mk.searchfinal.model.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MK on 04/04/2017.
 */

public class UserData extends AsyncTask<String,String,String> {
    public static final  int CONNECTION_TIMEOUT = 10000,
                             READ_TIMEOUT = 15000;
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
            url = new URL("http://oriflamebeauty.net/app/read.php?");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e.toString();
        }
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);

            Uri.Builder builder = new Uri.Builder().appendQueryParameter("searchQuery", searchQuery);
            String query = builder.build().getEncodedQuery();

            OutputStream outputStream = conn.getOutputStream();
            //في حاجة محتاجة تتعمل هنا UTF_8
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(query);
            writer.flush();
            writer.close();
            outputStream.close();
            conn.connect();

        } catch (IOException e1) {
            e1.printStackTrace();
            return e1.toString();
        }
        StringBuilder result = null;
        try {
            int response_code = conn.getResponseCode();

            if (response_code == HttpURLConnection.HTTP_OK) {
                result = new StringBuilder();

                InputStream inputStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = reader.readLine()) != null) {

                    result.append(line);
                }
            } else {
                return ("Connection error");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return (result.toString());

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        List<UserModel> userModels =new ArrayList<>();

        if (result.equals("now rows")){
            Toast.makeText(context, "No results found for entred query", Toast.LENGTH_SHORT).show();
        } else {

            try {
                JSONArray usersArray = new JSONArray(result);

                for (int i = 0;i<usersArray.length();i++){
                    JSONObject userObject = usersArray.getJSONObject(i);
                    UserModel userModel = new UserModel();
                    userModel.setId(userObject.getInt("id"));
                    userModel.setFname(userObject.getString("fname"));
                    userModel.setLname(userObject.getString("lname"));
                    userModel.setAge(userObject.getInt("age"));
                    userModels.add(userModel);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}
