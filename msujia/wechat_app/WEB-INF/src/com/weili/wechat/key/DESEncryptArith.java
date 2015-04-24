/**
 * 
 */
package com.weili.wechat.key;

//import java.security.SecureRandom;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

import sun.misc.*;

//import com.weili.zjfz.validator.TestMacKey;


/**
 * @author qzou
 *
 */
public class DESEncryptArith implements IEncryptArith{  
	private static final byte[] PASSWORD_CRYPT_KEY = {0x19,0x70,0x79,0x75,0x6E,0x33,0x26,(byte)0x89,0x44,(byte)0xBC,0x05,(byte)0x80,(byte)0xF8,0x3D,0x00,(byte)0x80}; 
	private static final byte[] plain = {0x01,0x23,0x45,0x67,(byte)0x89,(byte)0xab,(byte)0xcd,(byte)0xe7};
	private static final byte[] cipher = {(byte)0xc9,0x57,0x44,0x25,0x6a,0x5e,(byte)0xd3,0x1d};
	private final static String DES = "DES/ECB/NoPadding"; 

	
	
    public byte[] doDecrypt(byte[] encryptText,byte[] desKey) throws Exception {
    	//byte[] encryptionIV = "00000000".getBytes();
     
    	//  DES�㷨Ҫ����һ�������ε������Դ
        //SecureRandom sr = new SecureRandom();
        /* ��ĳ�ַ�����ȡԭʼ�ܳ����� */
        byte[] rawKeyData = desKey; 
        // ��ԭʼ�ܳ����ݴ���һ��DESKeySpec����
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        // ����һ���ܳ׹�����Ȼ��������DESKeySpec����ת����
        // һ��SecretKey����
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        // Cipher����ʵ����ɽ��ܲ���
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        //PKCS5Padding"); ;;
        // ���ܳ׳�ʼ��Cipher����
        //IvParameterSpec spec = new IvParameterSpec(encryptionIV);

        
        // ���ܳ׳�ʼ��Cipher����
        //cipher.init(Cipher.ENCRYPT_MODE, key, spec);  //, sr);

//        cipher.init(Cipher.DECRYPT_MODE, key, spec);	//, sr);
        cipher.init(Cipher.DECRYPT_MODE, key);	//, sr);
        // ���ڣ���ȡ���ݲ�����
        byte[] encryptedData = encryptText;/* ��þ������ܵ����� */
        
        // ��ʽִ�н��ܲ���
        byte[] decryptedData = cipher.doFinal(encryptedData);
        
        return decryptedData;
    }
    
    public byte[] doEncrypt(byte[] plainText,byte[] desKey) throws Exception {
    	//byte[] encryptionIV = "00000000".getBytes();
    	
    	/*byte[] iv = new byte[]{
                (byte)0x8E, 0x12, 0x39, (byte)0x9C,
                0x07, 0x72, 0x6F, 0x5A
            };
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        */
        // DES�㷨Ҫ����һ�������ε������Դ
        //SecureRandom sr = new SecureRandom();
        /* ��ĳ�ַ�������ܳ����� */
        byte rawKeyData[] = desKey;
        // ��ԭʼ�ܳ����ݴ���DESKeySpec����
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        // ����һ���ܳ׹�����Ȼ��������DESKeySpecת����
        // һ��SecretKey����
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        // Cipher����ʵ����ɼ��ܲ���
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");   //  DES/CBC/NoPadding");	;;;PKCS5Padding
        
        

        // Create IvParameterSpec object with initialization vector
        //IvParameterSpec spec = new IvParameterSpec(encryptionIV);
//        IvParameterSpec spec = new IvParameterSpec();

        
        // ���ܳ׳�ʼ��Cipher����
//        cipher.init(Cipher.ENCRYPT_MODE, key, spec);  //, sr);
        cipher.init(Cipher.ENCRYPT_MODE, key);  //, sr);
        // ���ڣ���ȡ���ݲ�����
        //byte[] data = plainText;
        // ��ʽִ�м��ܲ���
        byte[] encryptedData = cipher.doFinal(plainText);

        return encryptedData;
    }
	
 
    @SuppressWarnings("unused")
	private String binToAsc(byte[] val)
	{
//		byte interByte[] = val.getBytes();

		StringBuffer str = new StringBuffer(val.length * 2);
		for (int i = 0; i < val.length; i++) {
			char hi = Character.forDigit((val[i] >> 4) & 0x0F, 16);
			char lo = Character.forDigit(val[i] & 0x0F, 16);
			str.append(Character.toUpperCase(hi));
			str.append(Character.toUpperCase(lo));
		}
		
		return str.toString();
	}
    
    public static void main(String[] args) throws Exception  {
    	byte[] ori = "11".getBytes();
    	byte[] pin = new byte[(ori.length/8+1)*8];
    	java.util.Arrays.fill(pin, (byte)0x00);
    	System.arraycopy(ori, 0, pin, 0, ori.length);
    	
    	DESEncryptArith des = new DESEncryptArith();
		byte[] tmp = des.doEncrypt(pin,PASSWORD_CRYPT_KEY);
		String base64 = new BASE64Encoder().encode(tmp);
		
		System.out.println(base64);
		System.out.println(new String(des.binToAsc(base64.getBytes())));
    	
		new BASE64Decoder().decodeBuffer(base64);
		System.out.println(new String(des.doDecrypt(new BASE64Decoder().decodeBuffer(base64), PASSWORD_CRYPT_KEY)));
    	
//    	byte[] key = new TestMacKey().getRootKey();
//    	byte[] data = new TestMacKey().getMacKey();
//    	byte[] dac = new TestMacKey().getDac();
//    	try {
//    		DESEncryptArith arith = new DESEncryptArith();
//			byte[] result = arith.doEncrypt(data,key);
//			byte[] dacResult = arith.doEncrypt(dac,data);
////			System.out.println(new String(arith.binToAsc(key)));
////			System.out.println(new String(arith.binToAsc(data)));
////			System.out.println(new String(arith.binToAsc(result)));
////			System.out.println(new String(arith.binToAsc(dacResult)));
//			
//			data = arith.doDecrypt(dacResult,data);
////			System.out.println(new String(arith.binToAsc(dac)));
////			System.out.println(new String(arith.binToAsc(data)));
//			data = arith.doDecrypt(result,key);
////			System.out.println(new String(arith.binToAsc(data)));
//			
//		} catch (Exception e) {	
//			e.printStackTrace();
//		}	
    }
    
	
}
