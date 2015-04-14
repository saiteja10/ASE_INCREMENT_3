package com.example.FrontEnd_PG4.request;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.FrontEnd_PG4.util.Property;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public abstract class BaseRequest extends Activity {
	private ProgressDialog progDailog;
	private Context ctx;
	private JsonHandler jsonHandler;

	public BaseRequest(Context ctx, JsonHandler jsonHandler) {
		this.ctx = ctx;
		this.jsonHandler = jsonHandler;
	}

	private void ShowProgress()    {
        progDailog = ProgressDialog.show(ctx, "", "please wait....", true);
    }

	public void execute() {
		BalanceProgressTask obj = new BalanceProgressTask();
		obj.execute("");
	}

	class BalanceProgressTask extends AsyncTask<String, Long, String> {

		@Override
		protected String doInBackground(String... params) {
			String returnVal = "";
			try {
				HttpClient client = new DefaultHttpClient();
				HttpRequestBase httpRequest = getHttpRequest();
                httpRequest.setHeader("Accept", "application/json");
				HttpResponse response = client.execute(httpRequest);
				HttpEntity entity = response.getEntity();
				InputStream stream = entity.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						stream));
				String line = br.readLine();
				while (null != line) {
					returnVal += line;
					line = br.readLine();
				}
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("Log Tag", "Error in connection" + e.toString());
			}
			return returnVal; // Return your real result here
		}

		@Override
		protected void onPreExecute() {
			ShowProgress();
		}

		protected void onPostExecute(String result) {
			jsonHandler.parseJson(result);
			progDailog.dismiss();
		}
	}

    protected abstract HttpRequestBase getHttpRequest();
}