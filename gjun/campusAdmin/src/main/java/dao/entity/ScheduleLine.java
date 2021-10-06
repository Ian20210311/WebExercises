package dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the schedule_line database table.
 * 
 */
@Entity
@Table(name="schedule_line")
@NamedQuery(name="ScheduleLine.findAll", query="SELECT s FROM ScheduleLine s")
public class ScheduleLine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="msg_id")
	private int msgId;

	@Column(name="create_time")
	private Timestamp createTime;

	@Column(name="is_sent")
	private String isSent;

	private String message;

	@Column(name="setting_time")
	private Timestamp settingTime;

	public ScheduleLine() {
	}

	public int getMsgId() {
		return this.msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getIsSent() {
		return this.isSent;
	}

	public void setIsSent(String isSent) {
		this.isSent = isSent;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getSettingTime() {
		return this.settingTime;
	}

	public void setSettingTime(Timestamp settingTime) {
		this.settingTime = settingTime;
	}

}