package com.weili.wechat.service.login;

import java.util.List;

import com.weili.wechat.common.IRetInfo;
import com.weili.wechat.vo.User;

/**
 * ��¼�߼�
 */
public interface LoginService extends IRetInfo 
{
   
   /**
    * �����û���Ų�ѯ�û�
    * @param userId
    * @param passwd
    * @return com.weili.wechat.vo.User
    * @roseuid 47CFB97E0196
    */
   public User checkLogin(String userId, String passwd);
   
   /**
    * @param roleId
    * @return List
    * @roseuid 47CFC8D70280
    */
   public List qryMenuByRole(Integer roleId);
   
   /**
    * @param roleId
    * @return List
    * @roseuid 47D088BE004E
    */
   public List qryButtonByRole(Integer roleId);
   
   /**
    * @return List
    * @roseuid 47D088C90177
    */
   public List qryButton();
   

	public int loginLog();
	
	public int logoutLog();
	
	public int checkLoginUpdateDate(String no);
	public int passwdFlarmDay(String no);
	
	
	
}
