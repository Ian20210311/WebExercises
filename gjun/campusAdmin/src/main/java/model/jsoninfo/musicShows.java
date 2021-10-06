package model.jsoninfo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class musicShows {
	

	// String filePath = "/WEB-INF/json/music.json";
	private String version;
	private String myUID;
	private String title;
	private String category;
	
	private List<musicDetail> showInfo;
	
	
	private String showUnit;
	private String discountInfo;
	private String descriptionFilterHtml;
	private String imageUrl;
	private String masterUnit;
	private String subUnit;
	private String supportUnit;
	private String otherUnit;
	private String webSales;
	private String sourceWebPromote;
	private String comment;
	private String editModifyDate;
	private String sourceWebName;
	private String startDate;
	private String endDate;
	private String hitRate;
	
	public musicShows() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
	public String getMyUID() {
		return myUID;
	}

	public void setMyUID(String myUID) {
		this.myUID = myUID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<musicDetail> getShowInfo() {
		return showInfo;
	}

	public void setShowInfo(List<musicDetail> showInfo) {
		this.showInfo = showInfo;
	}

	public String getShowUnit() {
		return showUnit;
	}

	public void setShowUnit(String showUnit) {
		this.showUnit = showUnit;
	}

	public String getDiscountInfo() {
		return discountInfo;
	}

	public void setDiscountInfo(String discountInfo) {
		this.discountInfo = discountInfo;
	}

	public String getDescriptionFilterHtml() {
		return descriptionFilterHtml;
	}

	public void setDescriptionFilterHtml(String descriptionFilterHtml) {
		this.descriptionFilterHtml = descriptionFilterHtml;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMasterUnit() {
		return masterUnit;
	}

	public void setMasterUnit(String masterUnit) {
		this.masterUnit = masterUnit;
	}

	public String getSubUnit() {
		return subUnit;
	}

	public void setSubUnit(String subUnit) {
		this.subUnit = subUnit;
	}

	public String getSupportUnit() {
		return supportUnit;
	}

	public void setSupportUnit(String supportUnit) {
		this.supportUnit = supportUnit;
	}

	public String getOtherUnit() {
		return otherUnit;
	}

	public void setOtherUnit(String otherUnit) {
		this.otherUnit = otherUnit;
	}

	public String getWebSales() {
		return webSales;
	}

	public void setWebSales(String webSales) {
		this.webSales = webSales;
	}

	public String getSourceWebPromote() {
		return sourceWebPromote;
	}

	public void setSourceWebPromote(String sourceWebPromote) {
		this.sourceWebPromote = sourceWebPromote;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEditModifyDate() {
		return editModifyDate;
	}

	public void setEditModifyDate(String editModifyDate) {
		this.editModifyDate = editModifyDate;
	}

	public String getSourceWebName() {
		return sourceWebName;
	}

	public void setSourceWebName(String sourceWebName) {
		this.sourceWebName = sourceWebName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getHitRate() {
		return hitRate;
	}

	public void setHitRate(String hitRate) {
		this.hitRate = hitRate;
	}
	
	
	
}
