package com.weili.wechat.common;


import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


/**
 * @author shp
 * @since 2005.12.12
 */


public final class EncryptUtil {


	
	/**
	 * MD5�����㷨
	 * @param arg0 - Ҫ���ܵ��ַ���
	 * @return ���ܹ�����ַ���
	 */
	
	public static String MD5(String  arg0) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = arg0.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * ���ַ�byte����ת��Ϊ16�����ַ���
	 * @param bytes  byte����
	 * @return ת�����16�����ַ���
	 */
	public static String byte2hex(byte bytes[])
	{
		   StringBuffer retString = new StringBuffer();
		   for (int i = 0; i < bytes.length; ++i)
		   {
		     retString.append(Integer.toHexString(0x0100 + (bytes[i] & 0x00FF)).substring(1).toUpperCase());
		   }
		   return retString.toString();
	}
	/**
	 * ��16�����ַ���ת��Ϊ�ַ�byte����
	 * @param hex 16�����ַ���
	 * @return ת������ַ�byte����
	 */
	 public static byte[] hex2byte(String hex) 
	 {
		   byte[] bts = new byte[hex.length() / 2];
		   for (int i = 0; i < bts.length; i++) {
		      bts[i] = (byte) Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
		   }
		   return bts; 
	}  

	 public static byte[]  getMABBytes( String macString ) 
	{
		int DES_BLOCK_SIZE = 8;
		byte[] tmpBytes = macString.getBytes();
		
		byte[] retnBytes;
		int iOrigLen = tmpBytes.length;
		int iMode = iOrigLen % DES_BLOCK_SIZE;
		int iAppend = 0;
		
		if( iMode != 0 ) {
			iAppend = DES_BLOCK_SIZE - iMode;
		}
		
		retnBytes = new byte[ iOrigLen + iAppend ];
		System.arraycopy(tmpBytes, 0, retnBytes, 0, iOrigLen );
		for( int i = iOrigLen ; i < iOrigLen + iAppend ; i++ ) {
			retnBytes[i] = (byte)0x00;
		}
		return retnBytes;
	}

	 
	/**
	 * �����㷨
	 * @param plainText ���ܶ���
	 * @param desKey �ܳ�����
	 * @return ���ܺ���ַ�����
	 */
	private static byte[] doEncrypt(byte[] plainText,byte[] desKey)
	{
		try
		{
    	
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
	        
	      
	        cipher.init(Cipher.ENCRYPT_MODE, key);  //, sr);
	        // ���ڣ���ȡ���ݲ�����
	       
	        // ��ʽִ�м��ܲ���
	        byte[] encryptedData = cipher.doFinal(plainText);
	
	        return encryptedData;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
    }
	public static byte[] generateMacBytes( byte[] mabBytes ) 
	{
		int DES_BLOCK_SIZE = 8;
		
		 
		 /* ��MAB�е�ÿ8���ֽڷ�Ϊһ�飨����˲���8���ӽڣ����Ҳ�0x00��,��MAK��ΪDES��Կ���ζԳ����һ�����ÿһ��������²�����
		  a)	����DES����
		  b)	�������������һ���8���ֽ���򣬽��ȡ����һ�飬�������в�����
		  �����һ�����DES���㣬�ó�8���ֽڵļ���ֵ
		  */
		 // The DES/CBC method is just as above
		 		 
		 byte[] desKey=hex2byte("0123456789ABCDEF");
		 //byte[] retnBytes = encoder.encode( mabBytes );
		 byte[] retnBytes = new byte[ mabBytes.length ];
		 byte[] procBytes = new byte[ mabBytes.length ];

		 System.arraycopy( mabBytes, 0, procBytes, 0, mabBytes.length );
		 
		 int iBlkCnt = mabBytes.length / DES_BLOCK_SIZE;
		 byte[] nextBlock = new byte[ DES_BLOCK_SIZE ];
		 
		 byte[] curBlock = new byte[ DES_BLOCK_SIZE ];		 
		 for( int i = 0 ; i < iBlkCnt - 1 ; i++ ) {
			 // get current block
			 System.arraycopy( procBytes, i * DES_BLOCK_SIZE, curBlock, 0, DES_BLOCK_SIZE );
			 // get next block
			 System.arraycopy( procBytes, (i + 1)* DES_BLOCK_SIZE, nextBlock, 0, DES_BLOCK_SIZE );

			 // ��MAK��ΪDES��Կ���ζԳ����һ�����ÿһ����н���DES����
			 //byte[] encryptBlock = encoder.encode( curBlock );
			 byte[] encryptBlock=doEncrypt(curBlock,desKey);
			 // �������������һ���8���ֽ���򣬽��ȡ����һ��
			 for( int j = 0 ; j < DES_BLOCK_SIZE ; j++ ) {
				 nextBlock[j] = (byte)( encryptBlock[j] ^ nextBlock[j] ); 
			 }
			 System.arraycopy( nextBlock, 0, procBytes, (i + 1)* DES_BLOCK_SIZE, DES_BLOCK_SIZE );

			 
			 // ��ÿһ����н���DES�����Ľ�����ȥ 
			 System.arraycopy( encryptBlock, 0, retnBytes, i * DES_BLOCK_SIZE, DES_BLOCK_SIZE );
		 }
		 
		 // added by qzou for the MAB with only 1 block, 20060911 begin
		 if( iBlkCnt < 2 ) {
			 System.arraycopy( mabBytes, 0, nextBlock, 0, DES_BLOCK_SIZE );
		 }		 
		 // added by qzou for the MAB with only 1 block, 20060911 end
		 
		 //�������һ�����DES���㣬�ó�8���ֽڵļ���ֵ
		 //byte[] encryptLastBlock = encoder.encode( nextBlock );
		 byte[] encryptLastBlock=doEncrypt(nextBlock,desKey);
		 // �����һ�����DES�����Ľ�����ȥ 
		 System.arraycopy( encryptLastBlock, 0, retnBytes, mabBytes.length - DES_BLOCK_SIZE, DES_BLOCK_SIZE );
		 
		 return encryptLastBlock;
	 }	
	
	
	public static void main(String[] args){	
    
		try
		{
				
			/*
			String clearText="irene";
			byte[] macBytes = generateMacBytes(getMABBytes(clearText));
			String result=byte2hex(macBytes);
			System.out.print(result);
			*/
			System.out.println("=="+EncryptUtil.MD5("admin"));
			System.out.println("=="+EncryptUtil.MD5("admin"));
			System.out.println("=="+EncryptUtil.MD5("admin"));
		}
		
		catch (java.lang.Exception e3) 
		{
			e3.printStackTrace();
		}
	}	

}