package com.devil.openvideo.bean;

import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.devil.openvideo.util.JSONParser;
/**
 * 某一类型的子分类，如电影（大陆，香港）
 */
//自动生成实体bean
public class CategoryBean {
	public CategoryBean() {
	}

	public CategoryBean(JSONObject jsonData) throws JSONException {
		this.init(jsonData);
	}

	private List<Results> resultsList;
	public class Results {
		public Results() {
		}

		public Results(String jsonData) throws JSONException {
			this.init(new JSONObject(jsonData));
		}

		private String title;
		private String cat;
		private List<Items> itemsList;
		public class Items {
			public Items() {
			}

			public Items(String jsonData) throws JSONException {
				this.init(new JSONObject(jsonData));
			}

			private String title;
			private String value;

			public void init(JSONObject jsonObject) throws JSONException {
				JSONParser parser = new JSONParser(jsonObject);

				title = parser.getString("title");
				value = parser.getString("value");
			}


			public String getTitle() {
				return title;
			}
			public void setTitle(String title) {
				this.title = title;
			}

			public String getValue() {
				return value;
			}
			public void setValue(String value) {
				this.value = value;
			}

		}


		public void init(JSONObject jsonObject) throws JSONException {
			JSONParser parser = new JSONParser(jsonObject);

			title = parser.getString("title");
			cat = parser.getString("cat");
			itemsList = new ArrayList<Items>();
			JSONArray array0 = parser.getJSONArray("items");
			if(array0!=null) {
				int length = array0.length();
				for (int i = 0; i < length; i++) {
					Items obj = new Items();
					obj.init(array0.getJSONObject(i));
					itemsList.add(obj);
				}
			}
		}


		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

		public String getCat() {
			return cat;
		}
		public void setCat(String cat) {
			this.cat = cat;
		}

		public List<Items> getItemsList() {
			return itemsList;
		}
		public void setItemsList(List<Items> itemsList) {
			this.itemsList = itemsList;
		}

	}

	private String status;

	public void init(JSONObject jsonObject) throws JSONException {
		JSONParser parser = new JSONParser(jsonObject);

		resultsList = new ArrayList<Results>();
		JSONArray array0 = parser.getJSONArray("results");
		if(array0!=null) {
			int length = array0.length();
			for (int i = 0; i < length; i++) {
				Results obj = new Results();
				obj.init(array0.getJSONObject(i));
				resultsList.add(obj);
			}
		}
		status = parser.getString("status");
	}


	public List<Results> getResultsList() {
		return resultsList;
	}
	public void setResultsList(List<Results> resultsList) {
		this.resultsList = resultsList;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}

