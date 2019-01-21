package kspe.jsontask.services;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kspe.jsontask.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final ImageView profile_pic;
    public final TextView full_name;
    public final TextView state;
    public final TextView city;
    public final TextView phone_number;



    public ViewHolder(View view) {
        super(view);
        mView = view;
        profile_pic = view.findViewById(R.id.profile_pic);
        full_name =  view.findViewById(R.id.full_name_txt);
        state =  view.findViewById(R.id.state_txt);
        city =  view.findViewById(R.id.city_txt);
        phone_number =  view.findViewById(R.id.phone_no_txt);

    }

    @Override
    public String toString() {
        return super.toString() + " '" + full_name.getText() + "'";
    }
}