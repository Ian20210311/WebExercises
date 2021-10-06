package model.jsoninfo;

/**
 * 
 * @author Morris
 * 
 *         教育中心服務內容
 * 
 *         https://data.gov.tw/dataset/67551
 * 
 *         String filePath = "/WEB-INF/json/Center_service_content.json";
 * 
 */
public class Center_service_content_info {

	private String 服務項目;
	private String 主題;
	private String 詳細內容;

	public Center_service_content_info() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String get服務項目() {
		return 服務項目;
	}

	public void set服務項目(String 服務項目) {
		this.服務項目 = 服務項目;
	}

	public String get主題() {
		return 主題;
	}

	public void set主題(String 主題) {
		this.主題 = 主題;
	}

	public String get詳細內容() {
		return 詳細內容;
	}

	public void set詳細內容(String 詳細內容) {
		this.詳細內容 = 詳細內容;
	}

}
