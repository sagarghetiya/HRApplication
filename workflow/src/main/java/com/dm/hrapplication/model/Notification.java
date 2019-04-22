package com.dm.hrapplication.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "notification")
public class Notification {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long notificationId;
	
	private String message;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	private Long workflowInstanceId;
	
	private Long taskInstanceId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="notification_box_id")
	private NotificationBox notificationBox;

	public Long getNotificationId() {
		return notificationId;
	}

	public String getMessage() {
		return message;
	}

	public String getCreatedDate() {
		SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		String strDate = sm.format(this.createdDate);
		return strDate;
	}

	public NotificationBox getNotificationBox() {
		return notificationBox;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setNotificationBox(NotificationBox notificationBox) {
		this.notificationBox = notificationBox;
	}

	public Long getWorkflowInstanceId() {
		return workflowInstanceId;
	}

	public void setWorkflowInstanceId(Long workflowId) {
		this.workflowInstanceId = workflowId;
	}

	public Long getTaskInstanceId() {
		return taskInstanceId;
	}

	public void setTaskInstanceId(Long taskInstanceId) {
		this.taskInstanceId = taskInstanceId;
	}
	
}
