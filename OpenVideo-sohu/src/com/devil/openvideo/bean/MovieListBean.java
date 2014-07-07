package com.devil.openvideo.bean;

import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.devil.openvideo.util.JSONParser;
/**
 * 影片列表
 * @author 80074242
 *
 */
//自动生成实体bean
public class MovieListBean {
	public MovieListBean() {
	}

	public MovieListBean(JSONObject jsonData) throws JSONException {
		this.init(jsonData);
	}

	private int total;
	private String pg;
	private List<Results> resultsList;
	public class Results {
		public Results() {
		}

		public Results(String jsonData) throws JSONException {
			this.init(new JSONObject(jsonData));
		}

		private String show_thumburl_hd;
		private String showname;
		private double reputation;
		private String show_vthumburl_hd;
		private String show_vthumburl;
		private String stripe_bottom;
		private int show_thumburl_hd_type;
		private String show_thumburl;
		private String tid;
		private String type;
		private int completed;
		private int show_vthumburl_hd_type;
		private String middle_stripe;

		public void init(JSONObject jsonObject) throws JSONException {
			JSONParser parser = new JSONParser(jsonObject);

			show_thumburl_hd = parser.getString("show_thumburl_hd");
			showname = parser.getString("showname");
			reputation = parser.getDouble("reputation");
			show_vthumburl_hd = parser.getString("show_vthumburl_hd");
			show_vthumburl = parser.getString("show_vthumburl");
			stripe_bottom = parser.getString("stripe_bottom");
			show_thumburl_hd_type = parser.getInt("show_thumburl_hd_type");
			show_thumburl = parser.getString("show_thumburl");
			tid = parser.getString("tid");
			type = parser.getString("type");
			completed = parser.getInt("completed");
			show_vthumburl_hd_type = parser.getInt("show_vthumburl_hd_type");
			middle_stripe = parser.getString("middle_stripe");
		}


		public String getShow_thumburl_hd() {
			return show_thumburl_hd;
		}
		public void setShow_thumburl_hd(String show_thumburl_hd) {
			this.show_thumburl_hd = show_thumburl_hd;
		}

		public String getShowname() {
			return showname;
		}
		public void setShowname(String showname) {
			this.showname = showname;
		}

		public double getReputation() {
			return reputation;
		}
		public void setReputation(double reputation) {
			this.reputation = reputation;
		}

		public String getShow_vthumburl_hd() {
			return show_vthumburl_hd;
		}
		public void setShow_vthumburl_hd(String show_vthumburl_hd) {
			this.show_vthumburl_hd = show_vthumburl_hd;
		}

		public String getShow_vthumburl() {
			return show_vthumburl;
		}
		public void setShow_vthumburl(String show_vthumburl) {
			this.show_vthumburl = show_vthumburl;
		}

		public String getStripe_bottom() {
			return stripe_bottom;
		}
		public void setStripe_bottom(String stripe_bottom) {
			this.stripe_bottom = stripe_bottom;
		}

		public int getShow_thumburl_hd_type() {
			return show_thumburl_hd_type;
		}
		public void setShow_thumburl_hd_type(int show_thumburl_hd_type) {
			this.show_thumburl_hd_type = show_thumburl_hd_type;
		}

		public String getShow_thumburl() {
			return show_thumburl;
		}
		public void setShow_thumburl(String show_thumburl) {
			this.show_thumburl = show_thumburl;
		}

		public String getTid() {
			return tid;
		}
		public void setTid(String tid) {
			this.tid = tid;
		}

		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

		public int getCompleted() {
			return completed;
		}
		public void setCompleted(int completed) {
			this.completed = completed;
		}

		public int getShow_vthumburl_hd_type() {
			return show_vthumburl_hd_type;
		}
		public void setShow_vthumburl_hd_type(int show_vthumburl_hd_type) {
			this.show_vthumburl_hd_type = show_vthumburl_hd_type;
		}

		public String getMiddle_stripe() {
			return middle_stripe;
		}

		public void setMiddle_stripe(String middle_stripe) {
			this.middle_stripe = middle_stripe;
		}
		
	}

	private String status;
	private String pz;

	public void init(JSONObject jsonObject) throws JSONException {
		JSONParser parser = new JSONParser(jsonObject);

		total = parser.getInt("total");
		pg = parser.getString("pg");
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
		pz = parser.getString("pz");
	}


	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public String getPg() {
		return pg;
	}
	public void setPg(String pg) {
		this.pg = pg;
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

	public String getPz() {
		return pz;
	}
	public void setPz(String pz) {
		this.pz = pz;
	}

}

