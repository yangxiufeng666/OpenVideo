package com.devil.openvideo.bean;

import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.devil.openvideo.util.JSONParser;

//自动生成实体bean
public class MainPageBean {
	public MainPageBean() {
	}

	public MainPageBean(JSONObject jsonObject) throws JSONException {//String jsonData
		this.init(jsonObject);
	}

	private List<Result_movie> result_movieList;
	public class Result_movie {
		public Result_movie() {
		}

		public Result_movie(String jsonData) throws JSONException {
			this.init(new JSONObject(jsonData));
		}

		private int total;
		private String title;
		private String showid;
		private String category;
		private String desc;
		private String showname;
		private String show_bannerurl;
		private int seq;
		private String show_vthumburl;
		private String show_thumburl;
		private int stage;
		private String videoid;

		public void init(JSONObject jsonObject) throws JSONException {
			JSONParser parser = new JSONParser(jsonObject);

			total = parser.getInt("total");
			title = parser.getString("title");
			showid = parser.getString("showid");
			category = parser.getString("category");
			desc = parser.getString("desc");
			showname = parser.getString("showname");
			show_bannerurl = parser.getString("show_bannerurl");
			seq = parser.getInt("seq");
			show_vthumburl = parser.getString("show_vthumburl");
			show_thumburl = parser.getString("show_thumburl");
			stage = parser.getInt("stage");
			videoid = parser.getString("videoid");
		}


		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}

		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

		public String getShowid() {
			return showid;
		}
		public void setShowid(String showid) {
			this.showid = showid;
		}

		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}

		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getShowname() {
			return showname;
		}
		public void setShowname(String showname) {
			this.showname = showname;
		}

		public String getShow_bannerurl() {
			return show_bannerurl;
		}
		public void setShow_bannerurl(String show_bannerurl) {
			this.show_bannerurl = show_bannerurl;
		}

		public int getSeq() {
			return seq;
		}
		public void setSeq(int seq) {
			this.seq = seq;
		}

		public String getShow_vthumburl() {
			return show_vthumburl;
		}
		public void setShow_vthumburl(String show_vthumburl) {
			this.show_vthumburl = show_vthumburl;
		}

		public String getShow_thumburl() {
			return show_thumburl;
		}
		public void setShow_thumburl(String show_thumburl) {
			this.show_thumburl = show_thumburl;
		}

		public int getStage() {
			return stage;
		}
		public void setStage(int stage) {
			this.stage = stage;
		}

		public String getVideoid() {
			return videoid;
		}
		public void setVideoid(String videoid) {
			this.videoid = videoid;
		}

	}

	private String status;
	private List<Result_subject> result_subjectList;
	public class Result_subject {
		public Result_subject() {
		}

		public Result_subject(String jsonData) throws JSONException {
			this.init(new JSONObject(jsonData));
		}

		private String background_image;
		private int sid;
		private String title;
		private String launcher_banner_image;
		private int layout;
		private String show_bannerurl;
		private String show_vthumburl;
		private String show_thumburl;

		public void init(JSONObject jsonObject) throws JSONException {
			JSONParser parser = new JSONParser(jsonObject);

			background_image = parser.getString("background_image");
			sid = parser.getInt("sid");
			title = parser.getString("title");
			launcher_banner_image = parser.getString("launcher_banner_image");
			layout = parser.getInt("layout");
			show_bannerurl = parser.getString("show_bannerurl");
			show_vthumburl = parser.getString("show_vthumburl");
			show_thumburl = parser.getString("show_thumburl");
		}


		public String getBackground_image() {
			return background_image;
		}
		public void setBackground_image(String background_image) {
			this.background_image = background_image;
		}

		public int getSid() {
			return sid;
		}
		public void setSid(int sid) {
			this.sid = sid;
		}

		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

		public String getLauncher_banner_image() {
			return launcher_banner_image;
		}
		public void setLauncher_banner_image(String launcher_banner_image) {
			this.launcher_banner_image = launcher_banner_image;
		}

		public int getLayout() {
			return layout;
		}
		public void setLayout(int layout) {
			this.layout = layout;
		}

		public String getShow_bannerurl() {
			return show_bannerurl;
		}
		public void setShow_bannerurl(String show_bannerurl) {
			this.show_bannerurl = show_bannerurl;
		}

		public String getShow_vthumburl() {
			return show_vthumburl;
		}
		public void setShow_vthumburl(String show_vthumburl) {
			this.show_vthumburl = show_vthumburl;
		}

		public String getShow_thumburl() {
			return show_thumburl;
		}
		public void setShow_thumburl(String show_thumburl) {
			this.show_thumburl = show_thumburl;
		}

	}


	public void init(JSONObject jsonObject) throws JSONException {
		JSONParser parser = new JSONParser(jsonObject);

		result_movieList = new ArrayList<Result_movie>();
		JSONArray array0 = parser.getJSONArray("result_movie");
		if(array0!=null) {
			int length = array0.length();
			for (int i = 0; i < length; i++) {
				Result_movie obj = new Result_movie();
				obj.init(array0.getJSONObject(i));
				result_movieList.add(obj);
			}
		}
		status = parser.getString("status");
		result_subjectList = new ArrayList<Result_subject>();
		JSONArray array1 = parser.getJSONArray("result_subject");
		if(array1!=null) {
			int length = array1.length();
			for (int i = 0; i < length; i++) {
				Result_subject obj = new Result_subject();
				obj.init(array1.getJSONObject(i));
				result_subjectList.add(obj);
			}
		}
	}


	public List<Result_movie> getResult_movieList() {
		return result_movieList;
	}
	public void setResult_movieList(List<Result_movie> result_movieList) {
		this.result_movieList = result_movieList;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public List<Result_subject> getResult_subjectList() {
		return result_subjectList;
	}
	public void setResult_subjectList(List<Result_subject> result_subjectList) {
		this.result_subjectList = result_subjectList;
	}

}

