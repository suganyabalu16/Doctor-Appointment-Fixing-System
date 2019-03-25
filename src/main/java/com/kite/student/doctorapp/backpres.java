package com.kite.student.doctorapp;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
public class backpres extends AsyncTask<String,Void,String> {
    AlertDialog dialog;
    Context context;
    public backpres(Context context)
    {
        this.context = context;
    }
    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Detail Status");
    }
    @Override
    protected void onPostExecute(String s) {
        dialog.setMessage(s);
        dialog.show();
        if(s.contains("Insert Successful"))
        {
            Intent intent_name = new Intent();
            intent_name.setClass(context.getApplicationContext(),RecommandPharmacy.class);
            context.startActivity(intent_name); }
    }
    @Override
    protected String doInBackground(String... voids) {
        String result ="";
        String na = voids[0];
        String age = voids[1];
        String his = voids[2];
        String hde= voids[3];
        String pre = voids[4];
        String connstr = "http://192.168.43.64/prescription.php";
        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);
           OutputStream ops = http.getOutputStream();
            BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(na,"UTF-8")
                    +"&&"+URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")
                    +"&&"+URLEncoder.encode("healthissue","UTF-8")+"="+URLEncoder.encode(his,"UTF-8")
                    +"&&"+URLEncoder.encode("healthdetail","UTF-8")+"="+URLEncoder.encode(hde,"UTF-8")
                    +"&&"+URLEncoder.encode("prescription","UTF-8")+"="+URLEncoder.encode(pre,"UTF-8");

            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();
            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
            String line = "";
            while((line = reader.readLine()) != null)
            {
                result += line;

            }
            reader.close();
            ips.close();
            http.disconnect();
            return result;
        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result =e.getMessage();
        }
        return result;
    }
}
