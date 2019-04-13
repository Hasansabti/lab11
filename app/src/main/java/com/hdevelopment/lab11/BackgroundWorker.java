package com.hdevelopment.lab11;
//Make sure that the following imports are included in your file
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
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
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String, Void, String> {
    //2. Declare Context reference and AlertDialog
    Context context;
    AlertDialog alertDialog;

    //3. Create constructor for Background worker class with Context Argument
    BackgroundWorker(Context ctx)
    {
        context = ctx;
    }
    //4. Override doInBackground method
    @Override
    protected String doInBackground(String... params) {
        //5. Get the first data that was sent to the Background process “type”
        String type = params[0];
        //6. Declare login and register URL 10.0.2.2 is the local IP of AVD
        //if local AVD will not work change it to the local IP of your computer.
        String login_url = "http://10.0.2.2/login.php"; //this URL is to access the login.php
        String register_url = "http://10.0.2.2/register.php";//this URL is to access the register.php
        //7. Check if method is login
        if(type.equals("login")){
            try {
                String user_name = params[1]; //8. get 2nd data sent in background “username”
                String password = params[2]; //9. get 3rd data sent in background “password”
                //10. Declare URL reference
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                //11. Set URL connection properties
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new
                        OutputStreamWriter(outputStream, "UTF-8"));
                //12. Set URL data “username” and “password”
                String post_data = URLEncoder.encode("user_name", "UTF-8")+"="+URLEncoder.encode(user_name, "UTF-8")+"&"
                                +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password,
                                "UTF-8");
                //13. Write Buffer data
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                //14. Cloase bufferWriter and OutputStream
                bufferedWriter.close();
                outputStream.close();
                //15. Create InputStream reference to get response from the server
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,
                        "iso-8859-1"));
                String result = "";
                String line;
                //16. Read each line from the server response and put it in result string
                while((line = bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                //17.Cloase stream objects
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result; //18. Return the response

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //19. Else condition if the “type” received is register
        else if(type.equals("register")){
            try {
                //20. Get only the 2nd and 3rd data sent in the background which is “username” and
                //    password
                String user_name = params[1];
                String password = params[2];
                //21. The rest of the code is the same as before the only difference URL used in here
                //    is register_url instead of login_url.
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8")+"="+URLEncoder.encode(user_name, "UTF-8")+"&"
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while((line = bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        //22. instantiate alertDialor regerence
        alertDialog = new AlertDialog.Builder(context).create();
        //23. Set title for the alert dialog
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        //24. Set the message to be displayed in the alert dialog
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


            }
