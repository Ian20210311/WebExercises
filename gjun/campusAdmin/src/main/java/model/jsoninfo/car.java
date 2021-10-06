package model.jsoninfo;

public class car {
	
	private String chtmessage, engmessage, starttime, endtime, updatetime, content;

	public car() {
		super();
		// TODO Auto-generated constructor stub
	}

	public car(String chtmessage, String engmessage, String starttime, String endtime, String updatetime,
			String content) {
		super();
		this.chtmessage = chtmessage;
		this.engmessage = engmessage;
		this.starttime = starttime;
		this.endtime = endtime;
		this.updatetime = updatetime;
		this.content = content;
	}

	public String getChtmessage() {
		return chtmessage;
	}

	public void setChtmessage(String chtmessage) {
		this.chtmessage = chtmessage;
	}

	public String getEngmessage() {
		return engmessage;
	}

	public void setEngmessage(String engmessage) {
		this.engmessage = engmessage;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

}
