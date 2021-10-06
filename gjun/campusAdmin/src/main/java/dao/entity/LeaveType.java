package dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the leave_type database table.
 * 
 */
@Entity
@Table(name="leave_type")
@NamedQuery(name="LeaveType.findAll", query="SELECT l FROM LeaveType l")
public class LeaveType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="type_no")
	private int typeNo;

	@Column(name="leave_desc")
	private String leaveDesc;

	private String typename;

	//bi-directional many-to-one association to LeaveInfo
	@OneToMany(mappedBy="leaveType")
	private List<LeaveInfo> leaveInfos;

	public LeaveType() {
	}

	public int getTypeNo() {
		return this.typeNo;
	}

	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}

	public String getLeaveDesc() {
		return this.leaveDesc;
	}

	public void setLeaveDesc(String leaveDesc) {
		this.leaveDesc = leaveDesc;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public List<LeaveInfo> getLeaveInfos() {
		return this.leaveInfos;
	}

	public void setLeaveInfos(List<LeaveInfo> leaveInfos) {
		this.leaveInfos = leaveInfos;
	}

	public LeaveInfo addLeaveInfo(LeaveInfo leaveInfo) {
		getLeaveInfos().add(leaveInfo);
		leaveInfo.setLeaveType(this);

		return leaveInfo;
	}

	public LeaveInfo removeLeaveInfo(LeaveInfo leaveInfo) {
		getLeaveInfos().remove(leaveInfo);
		leaveInfo.setLeaveType(null);

		return leaveInfo;
	}

}