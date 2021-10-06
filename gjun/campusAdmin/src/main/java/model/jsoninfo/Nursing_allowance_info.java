package model.jsoninfo;

/**
 * 
 * @author Morris
 * 
 *         看護補助
 * 
 *         https://data.gov.tw/dataset/99364
 * 
 *         String filePath = "/WEB-INF/json/Nursing_allowance.json";
 * 
 */
public class Nursing_allowance_info {
	private String 年度;
	private String 件數;
	private String 補助金額;

	public Nursing_allowance_info() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String get年度() {
		return 年度;
	}

	public void set年度(String 年度) {
		this.年度 = 年度;
	}

	public String get件數() {
		return 件數;
	}

	public void set件數(String 件數) {
		this.件數 = 件數;
	}

	public String get補助金額() {
		return 補助金額;
	}

	public void set補助金額(String 補助金額) {
		this.補助金額 = 補助金額;
	}

}