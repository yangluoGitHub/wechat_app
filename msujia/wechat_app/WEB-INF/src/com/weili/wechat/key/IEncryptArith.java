/**
 * 
 */
package com.weili.wechat.key;

/**
 * @author chwu
 *
 */
public interface IEncryptArith {
	 public byte[] doDecrypt(byte[] encryptText,byte[] encryptKey) throws Exception ;
	 public byte[] doEncrypt(byte[] encryptText,byte[] encryptKey) throws Exception ;
}
