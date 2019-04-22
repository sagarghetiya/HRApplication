package com.dm.hrapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dm.hrapplication.model.Notification;
import com.dm.hrapplication.service.NotificationService;

@Controller
public class NotificationController {

	@Autowired
	NotificationService notificationService;

	@GetMapping({ "/getNotifications/{userId}" })
	public ResponseEntity<List<Notification>> getAllNotifications(@PathVariable("userId") Long userId) {
		return new ResponseEntity<List<Notification>>(notificationService.getAllNotifications(userId), HttpStatus.OK);
	}
}
