package com.devil.openvideo.bean;

import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.devil.openvideo.util.JSONParser;
/**
 * results：各种格式影片播放地址，
 * 其中m3u8_flv表示标清视频链接，
 * m3u8_mp4表示高清视频链接，
 * m3u8_hd表示超清视频链接。
 * @author 80074242
 *
 */
//自动生成实体bean
public class PlayAddressBean {
	public PlayAddressBean() {
	}

	public PlayAddressBean(JSONObject jsonData) throws JSONException {
		this.init(jsonData);
	}

	private Results results;
	public class Results {
		public Results() {
		}

		public Results(String jsonData) throws JSONException {
			this.init(new JSONObject(jsonData));
		}

		private List<Hd2> hd2List;
		public class Hd2 {
			public Hd2() {
			}

			public Hd2(String jsonData) throws JSONException {
				this.init(new JSONObject(jsonData));
			}

			private int id;
			private int seconds;
			private String url;
			private int size;

			public void init(JSONObject jsonObject) throws JSONException {
				JSONParser parser = new JSONParser(jsonObject);

				id = parser.getInt("id");
				seconds = parser.getInt("seconds");
				url = parser.getString("url");
				size = parser.getInt("size");
			}


			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}

			public int getSeconds() {
				return seconds;
			}
			public void setSeconds(int seconds) {
				this.seconds = seconds;
			}

			public String getUrl() {
				return url;
			}
			public void setUrl(String url) {
				this.url = url;
			}

			public int getSize() {
				return size;
			}
			public void setSize(int size) {
				this.size = size;
			}

		}

		private List<Flvhd> flvhdList;
		public class Flvhd {
			public Flvhd() {
			}

			public Flvhd(String jsonData) throws JSONException {
				this.init(new JSONObject(jsonData));
			}

			private int id;
			private int seconds;
			private String url;
			private int size;

			public void init(JSONObject jsonObject) throws JSONException {
				JSONParser parser = new JSONParser(jsonObject);

				id = parser.getInt("id");
				seconds = parser.getInt("seconds");
				url = parser.getString("url");
				size = parser.getInt("size");
			}


			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}

			public int getSeconds() {
				return seconds;
			}
			public void setSeconds(int seconds) {
				this.seconds = seconds;
			}

			public String getUrl() {
				return url;
			}
			public void setUrl(String url) {
				this.url = url;
			}

			public int getSize() {
				return size;
			}
			public void setSize(int size) {
				this.size = size;
			}

		}

		private List<M3u8_flv> m3u8_flvList;
		public class M3u8_flv {
			public M3u8_flv() {
			}

			public M3u8_flv(String jsonData) throws JSONException {
				this.init(new JSONObject(jsonData));
			}

			private int id;
			private int seconds;
			private String url;
			private int size;

			public void init(JSONObject jsonObject) throws JSONException {
				JSONParser parser = new JSONParser(jsonObject);

				id = parser.getInt("id");
				seconds = parser.getInt("seconds");
				url = parser.getString("url");
				size = parser.getInt("size");
			}


			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}

			public int getSeconds() {
				return seconds;
			}
			public void setSeconds(int seconds) {
				this.seconds = seconds;
			}

			public String getUrl() {
				return url;
			}
			public void setUrl(String url) {
				this.url = url;
			}

			public int getSize() {
				return size;
			}
			public void setSize(int size) {
				this.size = size;
			}

		}

		private List<M3gphd> m3gphdList;
		public class M3gphd {
			public M3gphd() {
			}

			public M3gphd(String jsonData) throws JSONException {
				this.init(new JSONObject(jsonData));
			}

			private int id;
			private int seconds;
			private String url;
			private int size;

			public void init(JSONObject jsonObject) throws JSONException {
				JSONParser parser = new JSONParser(jsonObject);

				id = parser.getInt("id");
				seconds = parser.getInt("seconds");
				url = parser.getString("url");
				size = parser.getInt("size");
			}


			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}

			public int getSeconds() {
				return seconds;
			}
			public void setSeconds(int seconds) {
				this.seconds = seconds;
			}

			public String getUrl() {
				return url;
			}
			public void setUrl(String url) {
				this.url = url;
			}

			public int getSize() {
				return size;
			}
			public void setSize(int size) {
				this.size = size;
			}

		}

		private List<Mp4> mp4List;
		public class Mp4 {
			public Mp4() {
			}

			public Mp4(String jsonData) throws JSONException {
				this.init(new JSONObject(jsonData));
			}

			private int id;
			private int seconds;
			private String url;
			private int size;

			public void init(JSONObject jsonObject) throws JSONException {
				JSONParser parser = new JSONParser(jsonObject);

				id = parser.getInt("id");
				seconds = parser.getInt("seconds");
				url = parser.getString("url");
				size = parser.getInt("size");
			}


			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}

			public int getSeconds() {
				return seconds;
			}
			public void setSeconds(int seconds) {
				this.seconds = seconds;
			}

			public String getUrl() {
				return url;
			}
			public void setUrl(String url) {
				this.url = url;
			}

			public int getSize() {
				return size;
			}
			public void setSize(int size) {
				this.size = size;
			}

		}

		private List<M3u8_hd> m3u8_hdList;
		public class M3u8_hd {
			public M3u8_hd() {
			}

			public M3u8_hd(String jsonData) throws JSONException {
				this.init(new JSONObject(jsonData));
			}

			private int id;
			private int seconds;
			private String url;
			private int size;

			public void init(JSONObject jsonObject) throws JSONException {
				JSONParser parser = new JSONParser(jsonObject);

				id = parser.getInt("id");
				seconds = parser.getInt("seconds");
				url = parser.getString("url");
				size = parser.getInt("size");
			}


			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}

			public int getSeconds() {
				return seconds;
			}
			public void setSeconds(int seconds) {
				this.seconds = seconds;
			}

			public String getUrl() {
				return url;
			}
			public void setUrl(String url) {
				this.url = url;
			}

			public int getSize() {
				return size;
			}
			public void setSize(int size) {
				this.size = size;
			}

		}

		private List<M3u8_mp4> m3u8_mp4List;
		public class M3u8_mp4 {
			public M3u8_mp4() {
			}

			public M3u8_mp4(String jsonData) throws JSONException {
				this.init(new JSONObject(jsonData));
			}

			private int id;
			private int seconds;
			private String url;
			private int size;

			public void init(JSONObject jsonObject) throws JSONException {
				JSONParser parser = new JSONParser(jsonObject);

				id = parser.getInt("id");
				seconds = parser.getInt("seconds");
				url = parser.getString("url");
				size = parser.getInt("size");
			}


			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}

			public int getSeconds() {
				return seconds;
			}
			public void setSeconds(int seconds) {
				this.seconds = seconds;
			}

			public String getUrl() {
				return url;
			}
			public void setUrl(String url) {
				this.url = url;
			}

			public int getSize() {
				return size;
			}
			public void setSize(int size) {
				this.size = size;
			}

		}

		private List<M3u8> m3u8List;
		public class M3u8 {
			public M3u8() {
			}

			public M3u8(String jsonData) throws JSONException {
				this.init(new JSONObject(jsonData));
			}

			private int id;
			private int seconds;
			private String url;
			private int size;

			public void init(JSONObject jsonObject) throws JSONException {
				JSONParser parser = new JSONParser(jsonObject);

				id = parser.getInt("id");
				seconds = parser.getInt("seconds");
				url = parser.getString("url");
				size = parser.getInt("size");
			}


			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}

			public int getSeconds() {
				return seconds;
			}
			public void setSeconds(int seconds) {
				this.seconds = seconds;
			}

			public String getUrl() {
				return url;
			}
			public void setUrl(String url) {
				this.url = url;
			}

			public int getSize() {
				return size;
			}
			public void setSize(int size) {
				this.size = size;
			}

		}


		public void init(JSONObject jsonObject) throws JSONException {
			JSONParser parser = new JSONParser(jsonObject);

			hd2List = new ArrayList<Hd2>();
			JSONArray array0 = parser.getJSONArray("hd2");
			if(array0!=null) {
				int length = array0.length();
				for (int i = 0; i < length; i++) {
					Hd2 obj = new Hd2();
					obj.init(array0.getJSONObject(i));
					hd2List.add(obj);
				}
			}
			flvhdList = new ArrayList<Flvhd>();
			JSONArray array1 = parser.getJSONArray("flvhd");
			if(array1!=null) {
				int length = array1.length();
				for (int i = 0; i < length; i++) {
					Flvhd obj = new Flvhd();
					obj.init(array1.getJSONObject(i));
					flvhdList.add(obj);
				}
			}
			m3u8_flvList = new ArrayList<M3u8_flv>();
			JSONArray array2 = parser.getJSONArray("m3u8_flv");
			if(array2!=null) {
				int length = array2.length();
				for (int i = 0; i < length; i++) {
					M3u8_flv obj = new M3u8_flv();
					obj.init(array2.getJSONObject(i));
					m3u8_flvList.add(obj);
				}
			}
			m3gphdList = new ArrayList<M3gphd>();
			JSONArray array3 = parser.getJSONArray("3gphd");
			if(array3!=null) {
				int length = array3.length();
				for (int i = 0; i < length; i++) {
					M3gphd obj = new M3gphd();
					obj.init(array3.getJSONObject(i));
					m3gphdList.add(obj);
				}
			}
			mp4List = new ArrayList<Mp4>();
			JSONArray array4 = parser.getJSONArray("mp4");
			if(array4!=null) {
				int length = array4.length();
				for (int i = 0; i < length; i++) {
					Mp4 obj = new Mp4();
					obj.init(array4.getJSONObject(i));
					mp4List.add(obj);
				}
			}
			m3u8_hdList = new ArrayList<M3u8_hd>();
			JSONArray array5 = parser.getJSONArray("m3u8_hd");
			if(array5!=null) {
				int length = array5.length();
				for (int i = 0; i < length; i++) {
					M3u8_hd obj = new M3u8_hd();
					obj.init(array5.getJSONObject(i));
					m3u8_hdList.add(obj);
				}
			}
			m3u8_mp4List = new ArrayList<M3u8_mp4>();
			JSONArray array6 = parser.getJSONArray("m3u8_mp4");
			if(array6!=null) {
				int length = array6.length();
				for (int i = 0; i < length; i++) {
					M3u8_mp4 obj = new M3u8_mp4();
					obj.init(array6.getJSONObject(i));
					m3u8_mp4List.add(obj);
				}
			}
			m3u8List = new ArrayList<M3u8>();
			JSONArray array7 = parser.getJSONArray("m3u8");
			if(array7!=null) {
				int length = array7.length();
				for (int i = 0; i < length; i++) {
					M3u8 obj = new M3u8();
					obj.init(array7.getJSONObject(i));
					m3u8List.add(obj);
				}
			}
		}


		public List<Hd2> getHd2List() {
			return hd2List;
		}
		public void setHd2List(List<Hd2> hd2List) {
			this.hd2List = hd2List;
		}

		public List<Flvhd> getFlvhdList() {
			return flvhdList;
		}
		public void setFlvhdList(List<Flvhd> flvhdList) {
			this.flvhdList = flvhdList;
		}

		public List<M3u8_flv> getM3u8_flvList() {
			return m3u8_flvList;
		}
		public void setM3u8_flvList(List<M3u8_flv> m3u8_flvList) {
			this.m3u8_flvList = m3u8_flvList;
		}

		

		public List<Mp4> getMp4List() {
			return mp4List;
		}
		public void setMp4List(List<Mp4> mp4List) {
			this.mp4List = mp4List;
		}

		public List<M3u8_hd> getM3u8_hdList() {
			return m3u8_hdList;
		}
		public void setM3u8_hdList(List<M3u8_hd> m3u8_hdList) {
			this.m3u8_hdList = m3u8_hdList;
		}

		public List<M3u8_mp4> getM3u8_mp4List() {
			return m3u8_mp4List;
		}
		public void setM3u8_mp4List(List<M3u8_mp4> m3u8_mp4List) {
			this.m3u8_mp4List = m3u8_mp4List;
		}

		public List<M3u8> getM3u8List() {
			return m3u8List;
		}
		public void setM3u8List(List<M3u8> m3u8List) {
			this.m3u8List = m3u8List;
		}

	}

	private String status;
	private List<Points> pointsList;
	public class Points {
		public Points() {
		}

		public Points(String jsonData) throws JSONException {
			this.init(new JSONObject(jsonData));
		}

		private String title;
		private String desc;
		private double start;
		private String type;

		public void init(JSONObject jsonObject) throws JSONException {
			JSONParser parser = new JSONParser(jsonObject);

			title = parser.getString("title");
			desc = parser.getString("desc");
			start = parser.getDouble("start");
			type = parser.getString("type");
		}


		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}

		public double getStart() {
			return start;
		}
		public void setStart(double start) {
			this.start = start;
		}

		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

	}

	private double totalseconds;

	public void init(JSONObject jsonObject) throws JSONException {
		JSONParser parser = new JSONParser(jsonObject);

		results = new Results();
		JSONObject object0 = parser.getJSONObject("results");
		if(object0!=null) {
			results.init(object0);
		}
		status = parser.getString("status");
		pointsList = new ArrayList<Points>();
		JSONArray array0 = parser.getJSONArray("points");
		if(array0!=null) {
			int length = array0.length();
			for (int i = 0; i < length; i++) {
				Points obj = new Points();
				obj.init(array0.getJSONObject(i));
				pointsList.add(obj);
			}
		}
		totalseconds = parser.getDouble("totalseconds");
	}


	public Results getResults() {
		return results;
	}
	public void setResults(Results results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public List<Points> getPointsList() {
		return pointsList;
	}
	public void setPointsList(List<Points> pointsList) {
		this.pointsList = pointsList;
	}

	public double getTotalseconds() {
		return totalseconds;
	}
	public void setTotalseconds(double totalseconds) {
		this.totalseconds = totalseconds;
	}

}

