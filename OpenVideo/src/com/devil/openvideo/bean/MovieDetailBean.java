package com.devil.openvideo.bean;

import org.json.JSONException;
import org.json.JSONObject;
import com.devil.openvideo.util.JSONParser;
/**
 * 影片详情
 * @author 80074242
 *
 */
//自动生成实体bean
public class MovieDetailBean {
	public MovieDetailBean() {
	}

	public MovieDetailBean(JSONObject jsonData) throws JSONException {
		this.init(jsonData);
	}

	private Detail detail;
	public class Detail {
		public Detail() {
		}

		public Detail(String jsonData) throws JSONException {
			this.init(new JSONObject(jsonData));
		}

		private String[] genre;
		private String showid;
		private String desc;
		private String total_vv;
		private String total_fav;
		private String img;
		private String stripe_bottom;
		private String douban_rating;
		private String img_default;
		private String tag_type;
		private String showtotal_search;
		private String cats;
		private String title;
		private String[] area;
		private String total_comment;
		private String showdate;
		private int cats_id;
		private String reputation;
		private String show_videotype;
		private int completed;
		private int limit;
		private String img_type;
		private String episode_total;
		private String total_up;
		private String[] director;
		private int videosize;
		private int publicType;
		private String pk_odshow;
		private String total_down;
		private String[] performer;
		private String videoid;
		private int format_flag;

		public void init(JSONObject jsonObject) throws JSONException {
			JSONParser parser = new JSONParser(jsonObject);

			genre = parser.getStringArray("genre");
			showid = parser.getString("showid");
			desc = parser.getString("desc");
			total_vv = parser.getString("total_vv");
			total_fav = parser.getString("total_fav");
			img = parser.getString("img");
			stripe_bottom = parser.getString("stripe_bottom");
			douban_rating = parser.getString("douban_rating");
			img_default = parser.getString("img_default");
			tag_type = parser.getString("tag_type");
			showtotal_search = parser.getString("showtotal_search");
			cats = parser.getString("cats");
			title = parser.getString("title");
			area = parser.getStringArray("area");
			total_comment = parser.getString("total_comment");
			showdate = parser.getString("showdate");
			cats_id = parser.getInt("cats_id");
			reputation = parser.getString("reputation");
			show_videotype = parser.getString("show_videotype");
			completed = parser.getInt("completed");
			limit = parser.getInt("limit");
			img_type = parser.getString("img_type");
			episode_total = parser.getString("episode_total");
			total_up = parser.getString("total_up");
			director = parser.getStringArray("director");
			videosize = parser.getInt("videosize");
			publicType = parser.getInt("publicType");
			pk_odshow = parser.getString("pk_odshow");
			total_down = parser.getString("total_down");
			performer = parser.getStringArray("performer");
			videoid = parser.getString("videoid");
			format_flag = parser.getInt("format_flag");
		}


		public String[] getGenre() {
			return genre;
		}
		public void setGenre(String[] genre) {
			this.genre = genre;
		}

		public String getShowid() {
			return showid;
		}
		public void setShowid(String showid) {
			this.showid = showid;
		}

		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getTotal_vv() {
			return total_vv;
		}
		public void setTotal_vv(String total_vv) {
			this.total_vv = total_vv;
		}

		public String getTotal_fav() {
			return total_fav;
		}
		public void setTotal_fav(String total_fav) {
			this.total_fav = total_fav;
		}

		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}

		public String getStripe_bottom() {
			return stripe_bottom;
		}
		public void setStripe_bottom(String stripe_bottom) {
			this.stripe_bottom = stripe_bottom;
		}

		public String getDouban_rating() {
			return douban_rating;
		}
		public void setDouban_rating(String douban_rating) {
			this.douban_rating = douban_rating;
		}

		public String getImg_default() {
			return img_default;
		}
		public void setImg_default(String img_default) {
			this.img_default = img_default;
		}

		public String getTag_type() {
			return tag_type;
		}
		public void setTag_type(String tag_type) {
			this.tag_type = tag_type;
		}

		public String getShowtotal_search() {
			return showtotal_search;
		}
		public void setShowtotal_search(String showtotal_search) {
			this.showtotal_search = showtotal_search;
		}

		public String getCats() {
			return cats;
		}
		public void setCats(String cats) {
			this.cats = cats;
		}

		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

		public String[] getArea() {
			return area;
		}
		public void setArea(String[] area) {
			this.area = area;
		}

		public String getTotal_comment() {
			return total_comment;
		}
		public void setTotal_comment(String total_comment) {
			this.total_comment = total_comment;
		}

		public String getShowdate() {
			return showdate;
		}
		public void setShowdate(String showdate) {
			this.showdate = showdate;
		}

		public int getCats_id() {
			return cats_id;
		}
		public void setCats_id(int cats_id) {
			this.cats_id = cats_id;
		}

		public String getReputation() {
			return reputation;
		}
		public void setReputation(String reputation) {
			this.reputation = reputation;
		}

		public String getShow_videotype() {
			return show_videotype;
		}
		public void setShow_videotype(String show_videotype) {
			this.show_videotype = show_videotype;
		}

		public int getCompleted() {
			return completed;
		}
		public void setCompleted(int completed) {
			this.completed = completed;
		}

		public int getLimit() {
			return limit;
		}
		public void setLimit(int limit) {
			this.limit = limit;
		}

		public String getImg_type() {
			return img_type;
		}
		public void setImg_type(String img_type) {
			this.img_type = img_type;
		}

		public String getEpisode_total() {
			return episode_total;
		}
		public void setEpisode_total(String episode_total) {
			this.episode_total = episode_total;
		}

		public String getTotal_up() {
			return total_up;
		}
		public void setTotal_up(String total_up) {
			this.total_up = total_up;
		}

		public String[] getDirector() {
			return director;
		}
		public void setDirector(String[] director) {
			this.director = director;
		}

		public int getVideosize() {
			return videosize;
		}
		public void setVideosize(int videosize) {
			this.videosize = videosize;
		}

		public int getPublicType() {
			return publicType;
		}
		public void setPublicType(int publicType) {
			this.publicType = publicType;
		}

		public String getPk_odshow() {
			return pk_odshow;
		}
		public void setPk_odshow(String pk_odshow) {
			this.pk_odshow = pk_odshow;
		}

		public String getTotal_down() {
			return total_down;
		}
		public void setTotal_down(String total_down) {
			this.total_down = total_down;
		}

		public String[] getPerformer() {
			return performer;
		}
		public void setPerformer(String[] performer) {
			this.performer = performer;
		}

		public String getVideoid() {
			return videoid;
		}
		public void setVideoid(String videoid) {
			this.videoid = videoid;
		}

		public int getFormat_flag() {
			return format_flag;
		}
		public void setFormat_flag(int format_flag) {
			this.format_flag = format_flag;
		}

	}

	private String status;

	public void init(JSONObject jsonObject) throws JSONException {
		JSONParser parser = new JSONParser(jsonObject);

		detail = new Detail();
		JSONObject object0 = parser.getJSONObject("detail");
		if(object0!=null) {
			detail.init(object0);
		}
		status = parser.getString("status");
	}


	public Detail getDetail() {
		return detail;
	}
	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}

