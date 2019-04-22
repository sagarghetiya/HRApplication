package com.dm.hrapplication.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.dm.hrapplication.model.EmailUtility;
import com.dm.hrapplication.model.EmailWrapper;

@Controller
public class EmailController {

	private EmailUtility emailUtility;

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST, produces = "text/plain")
	public ResponseEntity<Void> sendEmail(@Valid @RequestBody EmailWrapper emailWrapper,
			UriComponentsBuilder builder) {
		System.out.println("Inside the Email method");
		try {
			String pass = "hiring@123";
			String body ="Hi " + emailWrapper.getFirstname() + "," +"\n" + "Your query has been reported. We will revert back shortly";
			String subject = "Re: "+emailWrapper.getSubject();
			emailUtility.doPost("smtp.gmail.com", "587", "hiringportalacms@gmail.com", pass,emailWrapper.getEmail(),
					subject, body);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
