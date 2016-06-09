package vn.whoever.service.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import vn.whoever.service.GenerateToken;
/**
 * @author Nguyen Van Do
 *
 *	Make token random using ssoId and time generation.
 *	Using MD5 algorithm for generation string
 *  Replace some character in string to "-"
 *  String token have 32 characters.
 */

public class GenerateTokenImpl implements GenerateToken, Serializable {

	private static final long serialVersionUID = 18487378329292L;

	private GenerateTokenImpl() {}

	private static GenerateToken tokenImpl = new GenerateTokenImpl();

	public synchronized static GenerateToken getToken() {
		return tokenImpl;
	}

	public synchronized String getTokenId(String ssoId) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] data = (ssoId + (new Date()).getTime()).getBytes();

			byte[] result = md.digest(data);
			BigInteger number = new BigInteger(1, result);
			String hashtext = number.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			char[] arrToken = hashtext.toCharArray();
			arrToken[6] = '-';
			arrToken[11] = '-';
			arrToken[16] = '-';
			arrToken[21] = '-';
			return String.copyValueOf(arrToken);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
