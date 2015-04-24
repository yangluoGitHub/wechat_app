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
	 * MD5加密算法
	 * @param arg0 - 要加密的字符串
	 * @return 加密过后的字符串
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
	 * 将字符byte数组转换为16进制字符串
	 * @param bytes  byte数组
	 * @return 转换后的16进制字符串
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
	 * 将16进制字符串转换为字符byte数组
	 * @param hex 16进制字符串
	 * @return 转换后的字符byte数组
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
	 * 加密算法
	 * @param plainText 加密对象
	 * @param desKey 密匙数据
	 * @return 加密后的字符数组
	 */
	private static byte[] doEncrypt(byte[] plainText,byte[] desKey)
	{
		try
		{
    	
	        // DES算法要求有一个可信任的随机数源
	        //SecureRandom sr = new SecureRandom();
	        /* 用某种方法获得密匙数据 */
	        byte rawKeyData[] = desKey;
	        // 从原始密匙数据创建DESKeySpec对象
	        DESKeySpec dks = new DESKeySpec(rawKeyData);
	        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
	        // 一个SecretKey对象
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey key = keyFactory.generateSecret(dks);
	        // Cipher对象实际完成加密操作
	        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");   //  DES/CBC/NoPadding");	;;;PKCS5Padding
	        
	      
	        cipher.init(Cipher.ENCRYPT_MODE, key);  //, sr);
	        // 现在，获取数据并加密
	       
	        // 正式执行加密操作
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
		
		 
		 /* 将MAB中的每8个字节分为一组（最后宜不足8个子节，则右补0x00）,用MAK作为DES密钥依次对除最后一组外的每一组进行如下操作：
		  a)	进行DES运算
		  b)	将运算结果与后面一组的8个字节异或，结果取代后一组，继续进行操作。
		  对最后一组进行DES运算，得出8个字节的加密值
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

			 // 用MAK作为DES密钥依次对除最后一组外的每一组进行进行DES运算
			 //byte[] encryptBlock = encoder.encode( curBlock );
			 byte[] encryptBlock=doEncrypt(curBlock,desKey);
			 // 将运算结果与后面一组的8个字节异或，结果取代后一组
			 for( int j = 0 ; j < DES_BLOCK_SIZE ; j++ ) {
				 nextBlock[j] = (byte)( encryptBlock[j] ^ nextBlock[j] ); 
			 }
			 System.arraycopy( nextBlock, 0, procBytes, (i + 1)* DES_BLOCK_SIZE, DES_BLOCK_SIZE );

			 
			 // 将每一组进行进行DES运算后的结果存回去 
			 System.arraycopy( encryptBlock, 0, retnBytes, i * DES_BLOCK_SIZE, DES_BLOCK_SIZE );
		 }
		 
		 // added by qzou for the MAB with only 1 block, 20060911 begin
		 if( iBlkCnt < 2 ) {
			 System.arraycopy( mabBytes, 0, nextBlock, 0, DES_BLOCK_SIZE );
		 }		 
		 // added by qzou for the MAB with only 1 block, 20060911 end
		 
		 //　对最后一组进行DES运算，得出8个字节的加密值
		 //byte[] encryptLastBlock = encoder.encode( nextBlock );
		 byte[] encryptLastBlock=doEncrypt(nextBlock,desKey);
		 // 将最后一组进行DES运算后的结果存回去 
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