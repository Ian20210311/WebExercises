package dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the daily_chk database table.
 * 
 */
@Entity
@Table(name="daily_chk")
@NamedQuery(name="DailyChk.findAll", query="SELECT d FROM DailyChk d")
public class DailyChk implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="daily_no")
	private int dailyNo;

	@Column(name="chk_in_time")
	private Timestamp chkInTime;

	//bi-directional many-to-one association to StudentInfo
	@ManyToOne
	@JoinColumn(name="student_no")
	private StudentInfo studentInfo;

	public DailyChk() {
	}

	public int getDailyNo() {
		return this.dailyNo;
	}

	public void setDailyNo(int dailyNo) {
		this.dailyNo = dailyNo;
	}

	public Timestamp getChkInTime() {
		return this.chkInTime;
	}

	public void setChkInTime(Timestamp chkInTime) {
		this.chkInTime = chkInTime;
	}

	public StudentInfo getStudentInfo() {
		return this.studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

}