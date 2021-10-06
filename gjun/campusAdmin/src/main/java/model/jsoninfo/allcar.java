package model.jsoninfo;

import model.jsoninfo.car;

public class allcar {
	
	private String updateTime;
	private car[] News;

	public allcar() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public allcar(String updateTime, car[] news) {
		super();
		this.updateTime = updateTime;
		this.News = news;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public car[] getNews() {
		return News;
	}

	public void setNews(car[] news) {
		this.News = news;
	}

}
