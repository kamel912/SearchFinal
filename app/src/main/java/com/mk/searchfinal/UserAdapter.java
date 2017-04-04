package com.mk.searchfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mk.searchfinal.model.UserModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by MK on 04/04/2017.
 */

public class UserAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    List<UserModel> userModels = Collections.emptyList();
    UserModel userModel;

    public UserAdapter(Context context, ArrayList<UserModel> userModels) {
        this.context = context;
        this.userModels = userModels;

    }

    @Override
    public int getCount() {
        return userModels.size();
    }

    @Override
    public Object getItem(int position) {
        return userModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = LayoutInflater.from(context);
        View user = inflater.inflate(R.layout.row,null);
        TextView userId = (TextView) user.findViewById(R.id.user_id);
        TextView userFirstName = (TextView) user.findViewById(R.id.first_name);
        TextView userLastName = (TextView) user.findViewById(R.id.last_name);
        TextView userAge = (TextView) user.findViewById(R.id.user_age);


       userModel = userModels.get(position);
          userId.setText(userModel.getId());
          userFirstName.setText(userModel.getFname());
          userLastName.setText(userModel.getLname());
          userAge.setText(userModel.getAge());


        return user;
    }
}
