package com.example.acer.mysqltest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Register extends Activity implements OnClickListener{

    EditText name, email, mob, pass;
    private Button  mRegister;
    String emailPattern = "[a-zA-Z0-9._-]+@gmail+\\.+com";

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    //php login script

    //localhost :
    //testing on your device
    //put your local ip instead,  on windows, run CMD > ipconfig
    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
    // private static final String LOGIN_URL = "http://xxx.xxx.x.x:1234/webservice/register.php";

    //testing on Emulator:
    private static final String LOGIN_URL = "http://192.168.1.100:80/webservice/register11.php";

    //testing from a real server:
    //private static final String LOGIN_URL = "http://www.yourdomain.com/webservice/register.php";

    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        mob = (EditText)findViewById(R.id.mobile);
        pass = (EditText)findViewById(R.id.password);

        mRegister = (Button)findViewById(R.id.register);
        mRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if(!validate()) {
            new CreateUser(name.getText().toString(), email.getText().toString(), mob.getText().toString(), pass.getText().toString()).execute();
        }
    }

    private boolean validate() {

        if ((name.getText().toString().trim().length() <= 0) || (name.getText().toString().trim().length() > 40)) {
            Toast.makeText(Register.this, "Please Enter a Valid Name", Toast.LENGTH_LONG).show();
            return true;
        } else if ((email.getText().toString().trim().length() <= 0) || (!(email.getText().toString().matches(emailPattern)))) {
            Toast.makeText(Register.this, "Please Enter a Valid Email Id", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
            builder2.setTitle("HINT");
            builder2.setMessage("xyz@gmail.com");
            builder2.show();
            return true;
        } else if ((mob.getText().toString().trim().length() <= 0) || (mob.getText().toString().trim().length() < 10) || (mob.getText().toString().trim().length() > 10)) {
            Toast.makeText(Register.this, "Please Enter a Valid Number", Toast.LENGTH_LONG).show();
            return true;
        } else if ((pass.getText().toString().trim().length() < 8) || (pass.getText().toString().trim().length() > 12) || (pass.getText().toString().contains(" "))) {
            Toast.makeText(Register.this, "Please Enter Valid Password", Toast.LENGTH_LONG).show();

            if (!(Pattern.matches("[a-zA-Z0-9_]", pass.getText().toString()))) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("HINT");
                builder1.setMessage("No whitespaces" + "\n" + "Character Limit:8-12" + "\n" + "Special Symbols:.-_");
                builder1.show();
                return true;
            }
        }
        return false;
    }

    class CreateUser extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        boolean failure = false;
        String username;
        String emailadd;
        String mobile;
        String password;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Creating User...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        public CreateUser(String namepar, String emailpar, String mob, String pass) {
            username = namepar;
            emailadd = emailpar;
            mobile = mob;
            password = pass;
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;

            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("name", username));
                params.add(new BasicNameValuePair("email", emailadd));
                params.add(new BasicNameValuePair("mobile", mobile));
                params.add(new BasicNameValuePair("password", password));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                // full json response
                Log.d("Login attempt", json.toString());

                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("User Created!", json.toString());
                    finish();
                    return json.getString(TAG_MESSAGE);
                }else{
                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(Register.this, file_url, Toast.LENGTH_LONG).show();
            }
        }
    }
}

