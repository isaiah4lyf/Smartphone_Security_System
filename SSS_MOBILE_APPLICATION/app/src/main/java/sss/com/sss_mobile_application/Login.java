package sss.com.sss_mobile_application;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import sss.com.sss_mobile_application.ClientFunctions.LoginClient;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginAsy task = new loginAsy();
        task.execute();
    }
    private class loginAsy extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            //if you want, start progress dialog here
            //progressDialog = ProgressDialog.show(Login.this,"Please wait.","Connecting..!", true);
        }
        @Override
        protected String doInBackground(String... urls) {

            String response = "";
            try
            {
                LoginClient login = new LoginClient();
                response = login.DoThework("srdg","fsrd");
            }
            catch(Exception ex)
            {
                response = "Error: "+ex.getLocalizedMessage();
            }
            return  response;
        }
        @Override
        protected void onPostExecute(String result) {
            //if you started progress dialog dismiss it here
            try
            {
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
            catch (Exception ex)
            {
                Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }
}
