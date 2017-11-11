package com.luizalabs.teste.testcases;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

import com.luizalabs.teste.service.LoggerService;

public final class TestCaseHelper {

	private TestCaseHelper() {

	}

	public static JSONObject item1() throws JSONException {
		JSONObject item = new JSONObject();
		item.put("id", "123");
		item.put("ean", "7898100848355");
		item.put("title", "Cruzador espacial Nikana - 3000m - sem garantia");
		item.put("brand", "nikana");
		item.put("price", 820900.9);
		item.put("stock", 1.0);
		return item;
	}

	public static JSONObject item2() throws JSONException {
		JSONObject item = new JSONObject();
		item.put("id", "u7042");
		item.put("ean", "7898054800492");
		item.put("title", "Espada de fótons Nikana Azul");
		item.put("brand", "nikana");
		item.put("price", 2199.9);
		item.put("stock", 82.0);
		return item;
	}

	public static JSONObject item3() throws JSONException {
		JSONObject item = new JSONObject();
		item.put("id", "bb2r3s0");
		item.put("ean", "2059251400402");
		item.put("title", "Corredor POD 3000hp Nikana");
		item.put("brand", "nikana");
		item.put("price", 17832.9);
		item.put("stock", 8);
		return item;
	}

	public static JSONObject item4() throws JSONException {
		JSONObject item = new JSONObject();
		item.put("id", "321");
		item.put("ean", "1");
		item.put("title", "Cruzador espacial Nikana - 3000m - sem garantia");
		item.put("brand", "trek");
		item.put("price", 790300.9);
		item.put("stock", 0.0);
		return item;
	}

	public static JSONObject item5() throws JSONException {
		JSONObject item = new JSONObject();
		item.put("id", "80092");
		item.put("ean", "1");
		item.put("title", "Espada de Fótons REDAV Azul");
		item.put("brand", "redav");
		item.put("price", 1799.9);
		item.put("stock", 5.0);
		return item;
	}

	public static JSONObject item6() throws JSONException {
		JSONObject item = new JSONObject();
		item.put("id", "7728uu");
		item.put("ean", "1");
		item.put("title", "Cruzador espacial Ekul - 3000m - sem garantia");
		item.put("brand", "ekul");
		item.put("price", 1300000.00);
		item.put("stock", 3.0);
		return item;
	}

	public static String sendPostRequest(String payload) throws Exception {
		StringBuffer jsonString = new StringBuffer();
		URL url = new URL("http://" + getIp() + ":8080/api/main/organize");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		writer.write(payload);
		writer.close();
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = br.readLine()) != null) {
			jsonString.append(line);
		}
		br.close();
		connection.disconnect();
		return jsonString.toString();
	}
	
	private static String getIp() throws Exception{
        String ip = "localhost";
        String apiHost = "";
        if ((apiHost.equals(""))) {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                ip = addr.getHostAddress();
            } catch (UnknownHostException e) {
                LoggerService.debug(e);
            }
        } else {
            ip = apiHost;
        }

        return ip;
    }
}
