package com.ilucky.util.code.des;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @author cloudwise
 */
public class DesUtil {
	
	private DESKeySpec desKeySpec;
	private IvParameterSpec ivSpec;
	public static final String KEY = "62680693";
	public static final String SYMBOL_PLUS = "+";
    public static final String SYMBOL_EQUALS = "=";
    public static final String SYMBOL_DOUBLE_START = "**";
    public static final String SYMBOL_DOUBLE_CALL = "!!";
	
	public DesUtil () {
		try {
			byte[] keyBytes = KEY.getBytes();
			this.desKeySpec = new DESKeySpec(keyBytes);
			this.ivSpec = new IvParameterSpec(keyBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param origData
	 * @return
	 */
	public String encrypt(String origData){
		byte[] crypted = encrypt(origData.getBytes());
		String enStr = base64Encode(crypted);  
		enStr = enStr.replace(SYMBOL_PLUS, SYMBOL_DOUBLE_START);
		enStr = enStr.replace(SYMBOL_EQUALS, SYMBOL_DOUBLE_CALL); 
		return enStr; 
	} 
	
	/**
	 * @param enStr
	 * @return
	 */
	public String decrypt(String enStr){
		try {
			enStr = enStr.replace(SYMBOL_DOUBLE_START, SYMBOL_PLUS);
			enStr = enStr.replace(SYMBOL_DOUBLE_CALL, SYMBOL_EQUALS); 
			byte[]  deByte = base64Decode(enStr); 
			return new String(decrypt(deByte));
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * @param origData
	 * @return
	 */
	private byte[] encrypt(byte[] origData) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			SecretKey key = factory.generateSecret(this.desKeySpec);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, this.ivSpec);
			return cipher.doFinal(origData);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param crypted
	 * @return
	 */
	private byte[] decrypt(byte[] crypted) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			SecretKey key = factory.generateSecret(this.desKeySpec);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, this.ivSpec);
			return cipher.doFinal(crypted);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param data
	 * @return
	 */
	private static String base64Encode(byte[] data) { 
		return  Base64.encodeBase64String(data);
	}
	
	/**
	 * @param data
	 * @return
	 */
	private static byte[] base64Decode(String data) {
		try{ 
			return Base64.decodeBase64(data);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}