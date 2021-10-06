package model.dailyChk;

import java.time.*;

public class DailyChkQueryModel {

	
	LocalTime chktime=LocalTime.of(8, 0);
	int dailyNo=0;
	LocalDate date=null;
	LocalTime time=null;
	String status="曠課";
	
	public DailyChkQueryModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DailyChkQueryModel(LocalTime chktime, int dailyNo, LocalDate date, LocalTime time, String status) {
		super();
		this.chktime = chktime;
		this.dailyNo = dailyNo;
		this.date = date;
		this.time = time;
		this.status = status;
	}

	public LocalTime getChktime() {
		return chktime;
	}

	public void setChktime(LocalTime chktime) {
		this.chktime = chktime;
	}

	public int getDailyNo() {
		return dailyNo;
	}

	public void setDailyNo(int dailyNo) {
		this.dailyNo = dailyNo;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus() {
		if (time==null) {
			
		} else if (time.isAfter(chktime)) {
			this.status="遲到";
		} else {
			this.status="準時";
		}
	}

	public static void main(String[] args) {
		/*
		String status="曠課";
		LocalTime chktime= LocalTime.of(8, 0);
		LocalTime time= LocalTime.of(8, 0);
		if (time.isAfter(chktime)) {
			status="遲到";
		} else {
			status="準時";
		}
		
		System.out.println();
		System.out.println(chktime);
		System.out.println(time);
		System.out.println();
		
		System.out.println(status);*/
	}

}
