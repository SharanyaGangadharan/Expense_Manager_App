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

import com.example.acer.mysqltest.DisplayB1;
import com.example.acer.mysqltest.R;
import com.example.acer.mysqltest.ReadComments;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Fragment1 extends Fragment{
    public Fragment1() {
    }

    /*@Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment3, container, false);
         return rootView;
     }*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View rootView = inflater.inflate(R.layout.fragment3, container, false);
        final Context context;

        View rootView = inflater.inflate(R.layout.fragment1, container, false);
        context = rootView.getContext(); // Assign your rootView to context

        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                                           Intent intent = new Intent(context, ReadComments.class);
                                           startActivity(intent);
                                       }
                                   }
        );


        Button button2 = (Button) rootView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                                           Intent intent1 = new Intent(context, DisplayB1.class);
                                           startActivity(intent1);
                                       }
                                   }
        );

        return rootView;
    }

}
