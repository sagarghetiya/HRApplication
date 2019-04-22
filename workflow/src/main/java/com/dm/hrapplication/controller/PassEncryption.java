package com.dm.hrapplication.controller;

import java.security.Key;
import javax.crypto.Cipher;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class PassEncryption {
	private byte keyValue[];

	public PassEncryption(String key) {
		keyValue = key.getBytes();
	}

	public String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance("AES");
		c.init(1, key);
		byte encVal[] = c.doFinal(Data.getBytes());

		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedValue = encoder.encodeToString(encVal);
		return encryptedValue;
	}

	private Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, "AES");

		return key;
	}
}

