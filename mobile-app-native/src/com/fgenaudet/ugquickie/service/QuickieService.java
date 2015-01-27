package com.fgenaudet.ugquickie.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class QuickieService {
//	private static final String BASE_URL = "http://sd-44145.dedibox.fr:8090";
	private static final String BASE_URL = "http://192.168.1.80:8080/";
	
	public String load() {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse httpResponse = httpclient.execute(new HttpGet(BASE_URL + "/quickies"));

			return EntityUtils.toString(httpResponse.getEntity());
		} catch (Exception e) {
			Log.d("InputStream", "" + e.getLocalizedMessage());
		}

		return null;
	}
	
	public String loadOne(String id) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse httpResponse = httpclient.execute(new HttpGet(BASE_URL + "/quicky/rest/" + id));

			return EntityUtils.toString(httpResponse.getEntity());
		} catch (Exception e) {
			Log.d("InputStream", "" + e.getLocalizedMessage());
		}

		return null;
	}

}
