package dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the leave_info database table.
 * 
 */
@Entity
@Table(name="leave_info")
@NamedQuery(name="LeaveInfo.findAll", query="SELECT l FROM LeaveInfo l")
public class LeaveInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="leave_no")
	private int leaveNo;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="leave_begin")
	private Timestamp leaveBegin;

	@Column(name="leave_end")
	private Timestamp leaveEnd;

	private String reason;

	//bi-directional many-to-one association to LeaveType
	@ManyToOne
	@JoinColumn(name="type_no")
	private LeaveType leaveType;

	//bi-directional many-to-one association to StudentInfo
	@ManyToOne
	@JoinColumn(name="student_no")
	private StudentInfo studentInfo;

	public LeaveInfo() {
	}

	public int getLeaveNo() {
		return this.leaveNo;
	}

	public void setLeaveNo(int leaveNo) {
		this.leaveNo = leaveNo;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getLeaveBegin() {
		return this.leaveBegin;
	}

	public void setLeaveBegin(Timestamp leaveBegin) {
		this.leaveBegin = leaveBegin;
	}

	public Timestamp getLeaveEnd() {
		return this.leaveEnd;
	}

	public void setLeaveEnd(Timestamp leaveEnd) {
		this.leaveEnd = leaveEnd;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LeaveType getLeaveType() {
		return this.leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public StudentInfo getStudentInfo() {
		return this.studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

}