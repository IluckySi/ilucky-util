package com.ilucky.util.code.aes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author diandao
 */
public class AesUtil {

    //public static void EncryptFile(String seed, String srcFile, String distFile) throws Exception {
    public static void EncryptFile(String srcFile, String distFile) throws Exception {
        InputStream is = null;
        OutputStream out = null;
        CipherInputStream cis = null;
        try {
            int mode = Cipher.ENCRYPT_MODE;

            // byte[] keyData = getRawKey(seed.getBytes());
            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(mode, keySpec);

            is = new FileInputStream(srcFile);
            out = new FileOutputStream(distFile);
            cis = new CipherInputStream(is, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = cis.read(buffer)) > 0) {
                out.write(buffer, 0, r);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            cis.close();
            is.close();
            out.close();
        }
    }

    public static void DecryptFile(String srcFile, String distFile) throws Exception {
        InputStream is = null;
        OutputStream out = null;
        CipherOutputStream cos = null;
        try {
            int mode = Cipher.DECRYPT_MODE;
            // byte[] keyData = getRawKey(seed.getBytes());
            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(mode, keySpec);

            byte[] buffer = new byte[1024];
            is = new FileInputStream(srcFile);
            out = new FileOutputStream(distFile);
            cos = new CipherOutputStream(out, cipher);

            int r;
            while ((r = is.read(buffer)) >= 0) {
                cos.write(buffer, 0, r);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            cos.close();
            is.close();
            out.close();
        }
    }

    public static String encrypt(String cleartext) throws Exception {
        byte[] result = encrypt(cleartext.getBytes());
        return toHex(result);
    }

    public static String decrypt(String encrypted) throws Exception {
        byte[] enc = toByte(encrypted);
        byte[] result = decrypt(enc);
        return new String(result);
    }

    // public static byte[] getRawKey(byte[] seed) throws Exception {
    // KeyGenerator kgen = KeyGenerator.getInstance("AES");
    // SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
    // sr.setSeed(seed);
    // kgen.init(128, sr); // 192 and 256 bits may not be available
    // SecretKey skey = kgen.generateKey();
    // byte[] raw = skey.getEncoded();
    // return raw;
    // }

    public static byte[] encrypt(byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        // Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    private static byte[] decrypt(byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    private static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        return result;
    }

    public static byte[] toByte(byte[] buf) {
        int len = buf.length / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            String hexString = new String(buf);
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        }
        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private final static String HEX = "0123456789ABCDEF";

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }

    // private static String seed = "CF180643-0F25-41f1-B17D-7D4EC960B0A6";

    // private static byte[] raw = new byte[] {119, -21, -97, -33, 26, 75, 4, 3, -116, -55, 6, 102,
    // -71, -2, 45, 5};
    // "VIDEOS;COMMENTS!WIKIPEDIA@IMAGES#FACEBOOK$%.^&*("
    private static byte[] raw = new byte[] { 23, -111, -77, 69, -32, 65, 50, -117, 98, -27, 92, 45,
            75, 89, 111, 54  };

    // private static byte[] raw = new byte[] { 23, (byte)145, (byte)179, 69, (byte)224, 65, 50,
    // (byte)139, 98, (byte)229, 92, 45, 75, 89, 111, 54 };

    public static void main(String[] args) throws Exception {

        String str = decrypt("D7A790D536F5F86B21B08AD3A6810E53D7A790D536F5F86B21B08AD3A6810E53");
        System.out.println(str);
        System.out.println(encrypt(str));
    }

}