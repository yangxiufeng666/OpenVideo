package com.devil.openvideo.bean;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.devil.openvideo.util.JSONParser;

/**
 * 电视剧和综艺的剧集
 * @author 80074242
 *
 */
//自动生成实体bean
public class EpisodeBean {
	public EpisodeBean() {
	}

	public EpisodeBean(JSONObject jsonData) throws JSONException {
		this.init(jsonData);
	}

	private int total;
	private int pg;
	private boolean show_update;
	private String order;
	private List<Results> resultsList;
	public class Results {
		public Results() {
		}

		public Results(String jsonData) throws JSONException {
			this.init(new JSONObject(jsonData));
		}

		private String desc;
		private String img;
		private String userid;
		private int total_fav;
		private int state;
		private String cats;
		private boolean is_new;
		private String username;
		private String title;
		private int total_comment;
		private double reputation;
		private String[] streamtypes;
		private String tags;
		private int limit;
		private int show_videostage;
		private int stg;
		private int total_up;
		private String pubdate;
		private int total_pv;
		private double duration;
		private int publicType;
		private int show_videoseq;
		private String img_hd;
		private int total_down;
		private String videoid;
		String[] guest;

		public void init(JSONObject jsonObject) throws JSONException {
			JSONParser parser = new JSONParser(jsonObject);

			desc = parser.getString("desc");
			img = parser.getString("img");
			userid = parser.getString("userid");
			total_fav = parser.getInt("total_fav");
			state = parser.getInt("state");
			cats = parser.getString("cats");
			is_new = parser.getBoolean("is_new");
			username = parser.getString("username");
			title = parser.getString("title");
			total_comment = parser.getInt("total_comment");
			reputation = parser.getDouble("reputation");
			streamtypes = parser.getStringArray("streamtypes");
			tags = parser.getString("tags");
			limit = parser.getInt("limit");
			show_videostage = parser.getInt("show_videostage");
			stg = parser.getInt("stg");
			total_up = parser.getInt("total_up");
			pubdate = parser.getString("pubdate");
			total_pv = parser.getInt("total_pv");
			duration = parser.getDouble("duration");
			publicType = parser.getInt("publicType");
			show_videoseq = parser.getInt("show_videoseq");
			img_hd = parser.getString("img_hd");
			total_down = parser.getInt("total_down");
			guest = parser.getStringArray("guest");
			videoid = parser.getString("videoid");
		}


		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}

		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}

		public int getTotal_fav() {
			return total_fav;
		}
		public void setTotal_fav(int total_fav) {
			this.total_fav = total_fav;
		}

		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}

		public String getCats() {
			return cats;
		}
		public void setCats(String cats) {
			this.cats = cats;
		}

		public boolean getIs_new() {
			return is_new;
		}
		public void setIs_new(boolean is_new) {
			this.is_new = is_new;
		}

		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}

		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

		public int getTotal_comment() {
			return total_comment;
		}
		public void setTotal_comment(int total_comment) {
			this.total_comment = total_comment;
		}

		public double getReputation() {
			return reputation;
		}
		public void setReputation(double reputation) {
			this.reputation = reputation;
		}

		public String[] getStreamtypes() {
			return streamtypes;
		}
		public void setStreamtypes(String[] streamtypes) {
			this.streamtypes = streamtypes;
		}

		public String getTags() {
			return tags;
		}
		public void setTags(String tags) {
			this.tags = tags;
		}

		public int getLimit() {
			return limit;
		}
		public void setLimit(int limit) {
			this.limit = limit;
		}

		public int getShow_videostage() {
			return show_videostage;
		}
		public void setShow_videostage(int show_videostage) {
			this.show_videostage = show_videostage;
		}

		public int getStg() {
			return stg;
		}
		public void setStg(int stg) {
			this.stg = stg;
		}

		public int getTotal_up() {
			return total_up;
		}
		public void setTotal_up(int total_up) {
			this.total_up = total_up;
		}

		public String getPubdate() {
			return pubdate;
		}
		public void setPubdate(String pubdate) {
			this.pubdate = pubdate;
		}

		public int getTotal_pv() {
			return total_pv;
		}
		public void setTotal_pv(int total_pv) {
			this.total_pv = total_pv;
		}

		public double getDuration() {
			return duration;
		}
		public void setDuration(double duration) {
			this.duration = duration;
		}

		public int getPublicType() {
			return publicType;
		}
		public void setPublicType(int publicType) {
			this.publicType = publicType;
		}

		public int getShow_videoseq() {
			return show_videoseq;
		}
		public void setShow_videoseq(int show_videoseq) {
			this.show_videoseq = show_videoseq;
		}

		public String getImg_hd() {
			return img_hd;
		}
		public void setImg_hd(String img_hd) {
			this.img_hd = img_hd;
		}

		public int getTotal_down() {
			return total_down;
		}
		public void setTotal_down(int total_down) {
			this.total_down = total_down;
		}

		public String[] getGuest() {
			return guest;
		}
		public void setGuest(String[] guest) {
			this.guest = guest;
		}

		public String getVideoid() {
			return videoid;
		}
		public void setVideoid(String videoid) {
			this.videoid = videoid;
		}

	}

	private String status;
	private String showcats;
	private int pz;

	public void init(JSONObject jsonObject) throws JSONException {
		JSONParser parser = new JSONParser(jsonObject);

		total = parser.getInt("total");
		pg = parser.getInt("pg");
		show_update = parser.getBoolean("show_update");
		order = parser.getString("order");
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
		showcats = parser.getString("showcats");
		pz = parser.getInt("pz");
	}


	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public int getPg() {
		return pg;
	}
	public void setPg(int pg) {
		this.pg = pg;
	}

	public boolean getShow_update() {
		return show_update;
	}
	public void setShow_update(boolean show_update) {
		this.show_update = show_update;
	}

	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
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

	public String getShowcats() {
		return showcats;
	}
	public void setShowcats(String showcats) {
		this.showcats = showcats;
	}

	public int getPz() {
		return pz;
	}
	public void setPz(int pz) {
		this.pz = pz;
	}

}

