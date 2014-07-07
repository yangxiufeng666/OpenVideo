package com.devil.openvideo.bean;

import org.json.JSONException;
import org.json.JSONObject;
import com.devil.openvideo.util.JSONParser;

public class MoviesListBean {
	public MoviesListBean() {
	}

	public MoviesListBean(JSONObject jsonData) throws JSONException {
		this.init(jsonData);
	}

	private String smallPic;
	private int total;
	private int tvIsFeeMonth;
	private int vTV_IS_DOWNLOAD;
	private int score;
	private int playNum;
	private int tvYear;
	private int mobileVcount;
	private String categoryName;
	private int only;
	private String verSmallPic;
	private int pushRecommend;
	private String lastTitle;
	private int tvFeeRuleId;
	private int categoryId;
	private String issueTime;
	private int videoOrder;
	private String director;
	private int commentNum;
	private String subjectId;
	private String topicId;
	private String verBigPic;
	private String typeNames;
	private String horBigPic;
	private String mobileUrl;
	private int typeId;
	private String bigPic;
	private String des;
	private String horSmallPic;
	private int timeLength;
	private String subjectTitle;
	private int tvIsFee;
	private String showTime;
	private int recommend;
	private int noFeePlayTime;
	private int id;
	private int ipadNot;
	private String area;
	private double fscore;
	private int vcount;
	private String webPlaylistUrl;
	private String keyword;
	private int pid;
	private int hasSingle;
	private String typeName;
	private int vIP_LIMIT;
	private String source;
	private String upTime;
	private String mainActor;

	public void init(JSONObject jsonObject) throws JSONException {
		JSONParser parser = new JSONParser(jsonObject);

		smallPic = parser.getString("smallPic");
		total = parser.getInt("total");
		tvIsFeeMonth = parser.getInt("tvIsFeeMonth");
		vTV_IS_DOWNLOAD = parser.getInt("vTV_IS_DOWNLOAD");
		score = parser.getInt("score");
		playNum = parser.getInt("playNum");
		tvYear = parser.getInt("tvYear");
		mobileVcount = parser.getInt("mobileVcount");
		categoryName = parser.getString("categoryName");
		only = parser.getInt("only");
		verSmallPic = parser.getString("verSmallPic");
		pushRecommend = parser.getInt("pushRecommend");
		lastTitle = parser.getString("lastTitle");
		tvFeeRuleId = parser.getInt("tvFeeRuleId");
		categoryId = parser.getInt("categoryId");
		issueTime = parser.getString("issueTime");
		videoOrder = parser.getInt("videoOrder");
		director = parser.getString("director");
		commentNum = parser.getInt("commentNum");
		subjectId = parser.getString("subjectId");
		topicId = parser.getString("topicId");
		verBigPic = parser.getString("verBigPic");
		typeNames = parser.getString("typeNames");
		horBigPic = parser.getString("horBigPic");
		mobileUrl = parser.getString("mobileUrl");
		typeId = parser.getInt("typeId");
		bigPic = parser.getString("bigPic");
		des = parser.getString("des");
		horSmallPic = parser.getString("horSmallPic");
		timeLength = parser.getInt("timeLength");
		subjectTitle = parser.getString("subjectTitle");
		tvIsFee = parser.getInt("tvIsFee");
		showTime = parser.getString("showTime");
		recommend = parser.getInt("recommend");
		noFeePlayTime = parser.getInt("noFeePlayTime");
		id = parser.getInt("id");
		ipadNot = parser.getInt("ipadNot");
		area = parser.getString("area");
		fscore = parser.getDouble("fscore");
		vcount = parser.getInt("vcount");
		webPlaylistUrl = parser.getString("webPlaylistUrl");
		keyword = parser.getString("keyword");
		pid = parser.getInt("pid");
		hasSingle = parser.getInt("hasSingle");
		typeName = parser.getString("typeName");
		vIP_LIMIT = parser.getInt("vIP_LIMIT");
		source = parser.getString("source");
		upTime = parser.getString("upTime");
		mainActor = parser.getString("mainActor");
	}


	public String getSmallPic() {
		return smallPic;
	}
	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public int getTvIsFeeMonth() {
		return tvIsFeeMonth;
	}
	public void setTvIsFeeMonth(int tvIsFeeMonth) {
		this.tvIsFeeMonth = tvIsFeeMonth;
	}

	public int getVTV_IS_DOWNLOAD() {
		return vTV_IS_DOWNLOAD;
	}
	public void setVTV_IS_DOWNLOAD(int vTV_IS_DOWNLOAD) {
		this.vTV_IS_DOWNLOAD = vTV_IS_DOWNLOAD;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public int getPlayNum() {
		return playNum;
	}
	public void setPlayNum(int playNum) {
		this.playNum = playNum;
	}

	public int getTvYear() {
		return tvYear;
	}
	public void setTvYear(int tvYear) {
		this.tvYear = tvYear;
	}

	public int getMobileVcount() {
		return mobileVcount;
	}
	public void setMobileVcount(int mobileVcount) {
		this.mobileVcount = mobileVcount;
	}

	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getOnly() {
		return only;
	}
	public void setOnly(int only) {
		this.only = only;
	}

	public String getVerSmallPic() {
		return verSmallPic;
	}
	public void setVerSmallPic(String verSmallPic) {
		this.verSmallPic = verSmallPic;
	}

	public int getPushRecommend() {
		return pushRecommend;
	}
	public void setPushRecommend(int pushRecommend) {
		this.pushRecommend = pushRecommend;
	}

	public String getLastTitle() {
		return lastTitle;
	}
	public void setLastTitle(String lastTitle) {
		this.lastTitle = lastTitle;
	}

	public int getTvFeeRuleId() {
		return tvFeeRuleId;
	}
	public void setTvFeeRuleId(int tvFeeRuleId) {
		this.tvFeeRuleId = tvFeeRuleId;
	}

	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}

	public int getVideoOrder() {
		return videoOrder;
	}
	public void setVideoOrder(int videoOrder) {
		this.videoOrder = videoOrder;
	}

	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}

	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getVerBigPic() {
		return verBigPic;
	}
	public void setVerBigPic(String verBigPic) {
		this.verBigPic = verBigPic;
	}

	public String getTypeNames() {
		return typeNames;
	}
	public void setTypeNames(String typeNames) {
		this.typeNames = typeNames;
	}

	public String getHorBigPic() {
		return horBigPic;
	}
	public void setHorBigPic(String horBigPic) {
		this.horBigPic = horBigPic;
	}

	public String getMobileUrl() {
		return mobileUrl;
	}
	public void setMobileUrl(String mobileUrl) {
		this.mobileUrl = mobileUrl;
	}

	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getBigPic() {
		return bigPic;
	}
	public void setBigPic(String bigPic) {
		this.bigPic = bigPic;
	}

	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}

	public String getHorSmallPic() {
		return horSmallPic;
	}
	public void setHorSmallPic(String horSmallPic) {
		this.horSmallPic = horSmallPic;
	}

	public int getTimeLength() {
		return timeLength;
	}
	public void setTimeLength(int timeLength) {
		this.timeLength = timeLength;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public int getTvIsFee() {
		return tvIsFee;
	}
	public void setTvIsFee(int tvIsFee) {
		this.tvIsFee = tvIsFee;
	}

	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getNoFeePlayTime() {
		return noFeePlayTime;
	}
	public void setNoFeePlayTime(int noFeePlayTime) {
		this.noFeePlayTime = noFeePlayTime;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getIpadNot() {
		return ipadNot;
	}
	public void setIpadNot(int ipadNot) {
		this.ipadNot = ipadNot;
	}

	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}

	public double getFscore() {
		return fscore;
	}
	public void setFscore(double fscore) {
		this.fscore = fscore;
	}

	public int getVcount() {
		return vcount;
	}
	public void setVcount(int vcount) {
		this.vcount = vcount;
	}

	public String getWebPlaylistUrl() {
		return webPlaylistUrl;
	}
	public void setWebPlaylistUrl(String webPlaylistUrl) {
		this.webPlaylistUrl = webPlaylistUrl;
	}

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getHasSingle() {
		return hasSingle;
	}
	public void setHasSingle(int hasSingle) {
		this.hasSingle = hasSingle;
	}

	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getVIP_LIMIT() {
		return vIP_LIMIT;
	}
	public void setVIP_LIMIT(int vIP_LIMIT) {
		this.vIP_LIMIT = vIP_LIMIT;
	}

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	public String getUpTime() {
		return upTime;
	}
	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public String getMainActor() {
		return mainActor;
	}
	public void setMainActor(String mainActor) {
		this.mainActor = mainActor;
	}

}

