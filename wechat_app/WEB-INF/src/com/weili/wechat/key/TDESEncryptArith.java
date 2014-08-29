/**
 * 
 */
package com.weili.wechat.key;

//import java.security.SecureRandom;
import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * @author qzou
 *
 */
public class TDESEncryptArith implements IEncryptArith{  
    public byte[] doDecrypt(byte[] encryptText,byte[] desKey) throws Exception {
    	//byte[] encryptionIV = "00000000".getBytes();
     
    	//  DES�㷨Ҫ����һ�������ε������Դ
        //SecureRandom sr = new SecureRandom();
        /* ��ĳ�ַ�����ȡԭʼ�ܳ����� */
        byte[] rawKeyData = desKey; 
        // ��ԭʼ�ܳ����ݴ���һ��DESKeySpec����
        DESedeKeySpec dks = new DESedeKeySpec(rawKeyData);
        // ����һ���ܳ׹�����Ȼ��������DESKeySpec����ת����
        // һ��SecretKey����
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey key = keyFactory.generateSecret(dks);
        // Cipher����ʵ����ɽ��ܲ���
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        //PKCS5Padding"); ;;
        // ���ܳ׳�ʼ��Cipher����
        //IvParameterSpec spec = new IvParameterSpec(encryptionIV);

        
        // ���ܳ׳�ʼ��Cipher����
        //cipher.init(Cipher.ENCRYPT_MODE, key, spec);  //, sr);

        //cipher.init(Cipher.DECRYPT_MODE, key, spec);	//, sr);
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
        DESedeKeySpec dks = new DESedeKeySpec(rawKeyData);
        // ����һ���ܳ׹�����Ȼ��������DESKeySpecת����
        // һ��SecretKey����
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey key = keyFactory.generateSecret(dks);
        // Cipher����ʵ����ɼ��ܲ���
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");   //  DES/CBC/NoPadding");	;;;PKCS5Padding
        
        

        // Create IvParameterSpec object with initialization vector
        //IvParameterSpec spec = new IvParameterSpec(encryptionIV);

        
        // ���ܳ׳�ʼ��Cipher����
        //cipher.init(Cipher.ENCRYPT_MODE, key, spec);  //, sr);
        cipher.init(Cipher.ENCRYPT_MODE, key);  //, sr);
        // ���ڣ���ȡ���ݲ�����
        //byte[] data = plainText;
        // ��ʽִ�м��ܲ���
        byte[] encryptedData = cipher.doFinal(plainText);

        return encryptedData;
    }
	
	
}
