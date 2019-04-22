package com.dm.hrapplication.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notification_box")
public class NotificationBox {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long notificationBoxId;
	
	@OneToMany(mappedBy = "notificationBox", orphanRemoval = true, cascade=CascadeType.ALL)
	private List<Notification> notificationList = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Long getNotificationBoxId() {
		return notificationBoxId;
	}

	public List<Notification> getNotificationList() {
		return notificationList;
	}

	public User getUser() {
		return user;
	}

	public void setNotificationBoxId(Long notificationBoxId) {
		this.notificationBoxId = notificationBoxId;
	}

	public void setNotificationList(List<Notification> notificationList) {
		this.notificationList = notificationList;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
