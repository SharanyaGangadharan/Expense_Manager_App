package com.example.acer.mysqltest;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

/**
 * Created by VYSHU on 07-06-2016.
 */
public class DisplayB1 extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disp1);

        Button button = (Button) findViewById(R.id.bPressMe);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                //Toast.makeText(DisplayB.this,Environment.getExternalStorageDirectory().getPath() , Toast.LENGTH_SHORT).show();
                File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "invoice_1.pdf");

                try {
                    if (pdfFile.exists()) {
                        Uri path = Uri.fromFile(pdfFile);
                        Intent objIntent = new Intent(Intent.ACTION_VIEW);
                        objIntent.setDataAndType(path, "application/pdf");
                        objIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(objIntent);
                    }
                    else {
                        Toast.makeText(DisplayB1.this, "File NotFound", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText(DisplayB1.this,
                            "No Viewer Application Found", Toast.LENGTH_SHORT)
                            .show();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
