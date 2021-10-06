package dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the student_info database table.
 * 
 */
@Entity
@Table(name="student_info")
@NamedQuery(name="StudentInfo.findAll", query="SELECT s FROM StudentInfo s")
public class StudentInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="student_no")
	private int studentNo;

	private String city;

	private String county;

	@Column(name="create_date")
	private Timestamp createDate;

	private String name;

	@Column(name="parent_mail")
	private String parentMail;

	private String pwd_hash;

	private String pwd_seed;

	private String road;

	@Column(name="student_id")
	private String studentId;

	private String zip;

	//bi-directional many-to-one association to DailyChk
	@OneToMany(mappedBy="studentInfo")
	private List<DailyChk> dailyChks;

	//bi-directional many-to-one association to LeaveInfo
	@OneToMany(mappedBy="studentInfo")
	private List<LeaveInfo> leaveInfos;

	public StudentInfo() {
	}

	public int getStudentNo() {
		return this.studentNo;
	}

	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentMail() {
		return this.parentMail;
	}

	public void setParentMail(String parentMail) {
		this.parentMail = parentMail;
	}

	public String getPwd_hash() {
		return this.pwd_hash;
	}

	public void setPwd_hash(String pwd_hash) {
		this.pwd_hash = pwd_hash;
	}

	public String getPwd_seed() {
		return this.pwd_seed;
	}

	public void setPwd_seed(String pwd_seed) {
		this.pwd_seed = pwd_seed;
	}

	public String getRoad() {
		return this.road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<DailyChk> getDailyChks() {
		return this.dailyChks;
	}

	public void setDailyChks(List<DailyChk> dailyChks) {
		this.dailyChks = dailyChks;
	}

	public DailyChk addDailyChk(DailyChk dailyChk) {
		getDailyChks().add(dailyChk);
		dailyChk.setStudentInfo(this);

		return dailyChk;
	}

	public DailyChk removeDailyChk(DailyChk dailyChk) {
		getDailyChks().remove(dailyChk);
		dailyChk.setStudentInfo(null);

		return dailyChk;
	}

	public List<LeaveInfo> getLeaveInfos() {
		return this.leaveInfos;
	}

	public void setLeaveInfos(List<LeaveInfo> leaveInfos) {
		this.leaveInfos = leaveInfos;
	}

	public LeaveInfo addLeaveInfo(LeaveInfo leaveInfo) {
		getLeaveInfos().add(leaveInfo);
		leaveInfo.setStudentInfo(this);

		return leaveInfo;
	}

	public LeaveInfo removeLeaveInfo(LeaveInfo leaveInfo) {
		getLeaveInfos().remove(leaveInfo);
		leaveInfo.setStudentInfo(null);

		return leaveInfo;
	}

}