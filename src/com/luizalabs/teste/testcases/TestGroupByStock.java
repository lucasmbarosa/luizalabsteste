package com.luizalabs.teste.testcases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class TestGroupByStock {
	@Test
	public void TestMethod() {
		try {
			String responseJson = TestCaseHelper.sendPostRequest(getInputJson().toString());
			JSONAssert.assertEquals("Erro ao agrupar por stock!", responseJson, getJsonExcepted(),
					JSONCompareMode.LENIENT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private JSONObject getInputJson() throws JSONException {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		array.put(TestCaseHelper.item1());
		array.put(TestCaseHelper.item2());
		json.put("products", array);
		json.put("groupBy", "stock");
		return json;
	}

	private JSONArray getJsonExcepted() throws JSONException {
		JSONArray array = new JSONArray();
		JSONArray array1 = new JSONArray();
		JSONObject item = new JSONObject();
		item.put("description", "1.0");
		array1.put(TestCaseHelper.item1());
		item.put("items", array1);
		array.put(item);

		array1 = new JSONArray();
		item = new JSONObject();
		item.put("description", "82.0");
		array1.put(TestCaseHelper.item2());
		item.put("items", array1);
		array.put(item);

		
		return array;
	}

}
