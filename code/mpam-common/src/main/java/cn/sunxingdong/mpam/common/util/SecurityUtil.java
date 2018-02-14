package cn.sunxingdong.mpam.common.util;

import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * <p>
 * Title: 密码帮助类
 * </p>
 * <p>
 * Description: 系统加解密管理
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: Ztesoft
 * </p>
 * 本类只支持MD5加密、base64的加密、解密。更多的SHA\RSA的加密、解密在com.ztesoft.zsmart.core.security中被支持。
 */
public class SecurityUtil {

    // 瀵嗛挜(24浣?
    private static String aesPwdKey = "4EGJ6D9CFFA2GG9A";

    static Cipher eCipher = null;

    static Cipher dCipher = null;

    static byte[] bkey = null;

    static byte[] biv = null;

    static AlgorithmParameterSpec paramSpec = null;

    static SecretKey keySpec = null;

    static String encoding = "UTF-8";

    static BASE64Decoder base64Decoder = new BASE64Decoder();
    
    private static boolean enhanceCommonEncryptionAlgorithm;

    static {
        try {
            enhanceCommonEncryptionAlgorithm = false;
        }
        catch (Exception e) {
            enhanceCommonEncryptionAlgorithm = false;
        }
        init();
    }
    
    /**
     * Description: 初始化 <br> 
     *  
     * @author he.yongjin1<br>
     * @taskId <br> <br>
     */
    private static synchronized void init() {
        try {
            eCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            dCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            bkey = base64Decoder.decodeBuffer("SivnBF2z0IY=");
            biv = base64Decoder.decodeBuffer("uK1EBgjPTr0=");
            paramSpec = new IvParameterSpec(biv);
            keySpec = new SecretKeySpec(bkey, "DES");

            dCipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
            eCipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
            
//            rsaCipher = CipherDigest.instance("rsaDigest");
////            asymmericKeyPair = rsaCipher.generateAsymmericKey();
//
//            shaCipher = CipherDigest.instance("shaDigest");
        }
        catch (Exception e) {

        }
        catch (Throwable t) {

        }
    }
    
    /**
     * 解密
     * 
     * @param decryptString String <br>
     * @return String <br>
     * @throws Exception <br>
     */
    public static String decrypt(String decryptString) throws Exception {
        if (enhanceCommonEncryptionAlgorithm) {
            return "";//SecurityUtil.aESRandomDecrypt(decryptString);
        }
        else {
            return SecurityUtil.dESDecrypt(decryptString);
        }
    }

    /**
     * 加密
     * 
     * @param encryptString String <br>
     * @return String <br>
     * @throws Exception <br>
     */
    public static String encrypt(String encryptString) throws Exception {
        if (enhanceCommonEncryptionAlgorithm) {
            return SecurityUtil.aESRandomEncrypt(encryptString);
        }
        else {
            return SecurityUtil.dESEncrypt(encryptString);
        }
    }



    /**
     * 采用DES的CBC模式进行加密，补齐方式为PKCS5Padding
     * 
     * @param encryptString <br>
     * @return <br>
     */
    public static String dESEncrypt(String encryptString) {
        try {
            byte[] eout = null;
            synchronized (eCipher) {
                eout = eCipher.doFinal(encryptString.getBytes(encoding));
            }
            return new BASE64Encoder().encode(eout);
        }
        catch (Exception e) {
            init();
        }
        return null;
    }

    /**
     * 采用DES的CBC模式进行解密，补齐方式为PKCS5Padding
     * 
     * @param decryptString <br>
     * @return <br>
     */
    public static String dESDecrypt(String decryptString) {
        try {
            byte[] bout = null;
            synchronized (dCipher) {
                bout = dCipher.doFinal(base64Decoder.decodeBuffer(decryptString));
            }
            return new String(bout, encoding);
        }
        catch (Exception e) {
            init();
        }
        return null;
    }
    
    /**
     * 采用AES的CBC模式方式加密
     * 
     * @param encryptString <br>
     * @return <br>
     */
    public static String aESEncrypt(String encryptString) {
        String result = null;
        try {
            byte[] raw = aesPwdKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // "绠楁硶/妯″紡/琛ョ爜鏂瑰紡"
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes()); // 浣跨敤CBC妯″紡锛岄渶瑕佷竴涓悜閲廼v锛屽彲澧炲姞鍔犲瘑绠楁硶鐨勫己搴?
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(encryptString.getBytes());

            result = new BASE64Encoder().encode(encrypted);
        }
        catch (Exception ex) {
            // 鍔犲瘑澶辫触
        }
        return result;
    }

    /**
     * 采用AES的CBC模式进行解密
     * 
     * @param decryptString <br>
     * @return <br>
     */
    public static String aESDecrypt(String decryptString) {
        String result = null;
        try {
            byte[] raw = aesPwdKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(decryptString); // 鍏堢敤base64瑙ｅ瘑

            byte[] original = cipher.doFinal(encrypted1);
            result = new String(original);
        }
        catch (Exception ex) {
            // 瑙ｅ瘑澶辫触
        }

        return result;

    }
    
    /**
     * 
     * 采用AES的CBC模式方式加密 <br> 
     *  
     * @author ji.chunli<br>
     * @taskId 1050395<br>
     * @param encryptString 明文字符串<br>
     * @return result 密文 <br>
     */
    @SuppressWarnings("restriction")
    public static String aESRandomEncrypt(String encryptString) {
        String result = null;
        try {
            byte[] salt = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);

            byte[] raw = aesPwdKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(salt));
            byte[] encrypted = cipher.doFinal(encryptString.getBytes("UTF-8"));

            byte[] resultBytes = new byte[salt.length + encrypted.length];
            System.arraycopy(salt, 0, resultBytes, 0, salt.length);
            System.arraycopy(encrypted, 0, resultBytes, salt.length, encrypted.length);
            result = new BASE64Encoder().encode(resultBytes);
        }
        catch (Exception ex) {
        }
        return result;
    }
    

    
    /**
     * SHA算法不支持解密
     * @param decryptString
     * @return
     */
    public static String sHADecrypt(String decryptString) {
    	throw new UnsupportedOperationException("SHA arithmetic decrypt not UnsupportedOperationException !");
    }
    
    public static void main(String[] args) {
    	try {
			System.out.println(SecurityUtil.decrypt("/cHTBxkKpV3dmyyo3PIELQ=="));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
