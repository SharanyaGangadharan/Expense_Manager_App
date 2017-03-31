package com.example.acer.mysqltest.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.acer.mysqltest.DisplayB;
import com.example.acer.mysqltest.R;
import com.example.acer.mysqltest.ReadComments2;

/**
 * Created by acer on 5/8/2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Fragment3 extends Fragment{

    public Fragment3() {
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment3, container, false);
        return rootView;
    }*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View rootView = inflater.inflate(R.layout.fragment3, container, false);
        final Context context;

        View rootView = inflater.inflate(R.layout.fragment3, container, false);
        context = rootView.getContext(); // Assign your rootView to context

        Button button5 = (Button) rootView.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                                           Intent intent = new Intent(context, ReadComments2.class);
                                           startActivity(intent);
                                       }
                                   }
        );


        Button button6 = (Button) rootView.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                                           Intent intent1 = new Intent(context, DisplayB.class);
                                           startActivity(intent1);
                                       }
                                   }
        );

        return rootView;
    }
}
