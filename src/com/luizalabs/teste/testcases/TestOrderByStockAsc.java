package com.luizalabs.teste.testcases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class TestOrderByStockAsc {
	@Test
	public void TestMethod() {
		try {
			String responseJson = TestCaseHelper.sendPostRequest(getInputJson().toString());
			JSONAssert.assertEquals("Erro ao agrupar por stock ascdendente!", responseJson, getJsonExcepted(),
					JSONCompareMode.LENIENT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private JSONObject getInputJson() throws JSONException {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		array.put(TestCaseHelper.item4());
		array.put(TestCaseHelper.item5());
		array.put(TestCaseHelper.item6());
		json.put("products", array);
		json.put("orderBy", "stock:asc");
		return json;
	}

	private JSONArray getJsonExcepted() throws JSONException {
		JSONArray array = new JSONArray();
		JSONArray array1 = new JSONArray();
		JSONObject item = new JSONObject();
		item.put("description", "1");
		array1.put(TestCaseHelper.item4());
		array1.put(TestCaseHelper.item6());
		array1.put(TestCaseHelper.item5());
		item.put("items", array1);
		array.put(item);

		array1 = new JSONArray();
		item = new JSONObject();
		item.put("description", "Cruzador espacial Nikana - 3000m - sem garantia");
		array1.put(TestCaseHelper.item4());
		item.put("items", array1);
		array.put(item);
		
		array1 = new JSONArray();
		item = new JSONObject();
		item.put("description", "Espada de Fótons REDAV Azul");
		array1.put(TestCaseHelper.item5());
		item.put("items", array1);
		array.put(item);
		
		array1 = new JSONArray();
		item = new JSONObject();
		item.put("description", "Cruzador espacial Ekul - 3000m - sem garantia");
		array1.put(TestCaseHelper.item6());
		item.put("items", array1);
		array.put(item);
		
		
		array1 = new JSONArray();
		item = new JSONObject();
		item.put("description", "trek");
		array1.put(TestCaseHelper.item4());
		item.put("items", array1);
		array.put(item);
		
		
		array1 = new JSONArray();
		item = new JSONObject();
		item.put("description", "redav");
		array1.put(TestCaseHelper.item5());
		item.put("items", array1);
		array.put(item);
		
		
		array1 = new JSONArray();
		item = new JSONObject();
		item.put("description", "ekul");
		array1.put(TestCaseHelper.item6());
		item.put("items", array1);
		array.put(item);

		
		return array;
	}

}
