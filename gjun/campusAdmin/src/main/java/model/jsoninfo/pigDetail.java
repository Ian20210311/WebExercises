package model.jsoninfo;

public class pigDetail {
	//https://data.gov.tw/dataset/136370
	//臺灣豬證明標章商家資料
	// String filePath = "/WEB-INF/json/TaiwanPig.json";
	private String case_code;
	private String market_name;
	private String addr;
	private String business_week;
	private String context;
	private String ValidDate;
	private String Latitude;
	private String Lontitude;
	
	private String type;
	private String badge_code;
	private String business_hours;
	private String business_hurs_end;
	private String last_edited_date;
	public pigDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCase_code() {
		return case_code;
	}
	public void setCase_code(String case_code) {
		this.case_code = case_code;
	}
	public String getMarket_name() {
		return market_name;
	}
	public void setMarket_name(String market_name) {
		this.market_name = market_name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getBusiness_week() {
		return business_week;
	}
	public void setBusiness_week(String business_week) {
		this.business_week = business_week;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getValidDate() {
		return ValidDate;
	}
	public void setValidDate(String validDate) {
		ValidDate = validDate;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLontitude() {
		return Lontitude;
	}
	public void setLontitude(String lontitude) {
		Lontitude = lontitude;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBadge_code() {
		return badge_code;
	}
	public void setBadge_code(String badge_code) {
		this.badge_code = badge_code;
	}
	public String getBusiness_hours() {
		return business_hours;
	}
	public void setBusiness_hours(String business_hours) {
		this.business_hours = business_hours;
	}
	public String getBusiness_hurs_end() {
		return business_hurs_end;
	}
	public void setBusiness_hurs_end(String business_hurs_end) {
		this.business_hurs_end = business_hurs_end;
	}
	public String getLast_edited_date() {
		return last_edited_date;
	}
	public void setLast_edited_date(String last_edited_date) {
		this.last_edited_date = last_edited_date;
	}
	
	
}
