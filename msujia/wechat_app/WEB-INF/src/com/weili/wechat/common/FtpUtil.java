//package com.weili.wechat.common;
//
//import java.io.DataInputStream;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import sun.net.TelnetInputStream;
//import sun.net.TelnetOutputStream;
//public class FtpUtil 
//{
//	private static Log log = LogFactory.getLog(FtpUtil.class);
//	private static BdeFtpClient ftpClient;
//
//	
//	  public static void sendFile(String server,String user,String password,String remotePath,String localFile,String remoteFileName) throws Exception  
//	  { 
//		  try 
//		  { 
//			  if(server==null||server.equals("")||user==null||user.equals("")||password==null||password.equals("")
//					  ||remotePath==null||remotePath.equals("")||localFile==null||localFile.equals("")||remoteFileName==null||remoteFileName.equals(""))
//			  {
//				  log.error("�ϴ��ļ�����Ϊ�գ�");
//				  return;
//			  }
//	         /**����ftp��������������ָ��Ŀ¼ */
//	         connectServer(server,user,password,remotePath); 
//	         /**  �ϴ��ļ���ָ���ļ����� */ 
//	         long fileSize=upload(localFile,remoteFileName);
//	         if(fileSize==-1||fileSize==-2)
//	         {
//	        	 log.error("�ļ�Ϊ�ջ򲻴��ڣ��ϴ�ʧ�ܣ�"); 
//	        	 return;
//	         }
//	         log.info("�ɹ��ϴ��ļ�:"+localFile+" ��������·����"+ remotePath +"�� �ļ���С��"+fileSize+"�ֽ�"); 
//		  }
//		  catch (Exception e) 
//		  {
//			  log.error("Send file to server error: "+e.toString());
//			  e.printStackTrace();
//		  }
//		  finally 
//		  { 
//			  closeServer(); 
//		  } 
//	  }  	
//   /** 
//    * connectServer 
//    * ����ftp������ 
//    * @param user   ��½�û�
//    * @param password ���� 
//    * @param server ��������ַ 
//    * @param path �ļ��У��մ����Ŀ¼
//    */ 
//  public static void connectServer(String server, String user, String password, String path)throws Exception  
//  { 
//	  /** server��FTP��������IP��ַ��user:��¼FTP���������û���*/ 
//	  /** password����¼FTP���������û����Ŀ��path��FTP�������ϵ�·�� */ 
//	  try
//	  {
//		  ftpClient = new BdeFtpClient(); 
//		  ftpClient.openServer(server); 
//		  ftpClient.login(user, password);
//	  }
//	  catch(Exception e)
//	  {
//		  log.error("Connect to server " +server+" error!"+e.toString());
//		  throw new Exception(e.toString());
//	  }
//	  try
//	  {
//		  ftpClient.mkDir(path);
//	  }
//	  catch(FileNotFoundException e) 
//      {
//		log.info("Warining:mkdir "+ path+" ");
//      }
//      catch(Exception e)
//      {
//		log.error("MkDir error!");
//      	throw new Exception(e.toString());
//      }
//
//	  //path��ftp��������Ŀ¼����Ŀ¼ 
//	  try
//	  {
//		  if (path.length() != 0)  ftpClient.cd(path); 
//	  }
//	  catch(Exception e)
//	  {
//		  log.error("Change to directory "+path+" error!");
//		  throw new Exception(e.toString());
//	  }
//
//	  //��2�����ϴ������� 
//	  ftpClient.binary();
// } 
//  
// /** 
//    * upload 
//    * �ϴ��ļ� 
//    * @throws java.lang.Exception 
//    * @return -1 �ļ������� 
//    *          -2 �ļ�����Ϊ��  
//    *          >0 �ɹ��ϴ��������ļ��Ĵ�С 
//    * @param newname �ϴ�������ļ��� 
//    * @param filename �ϴ����ļ� 
//    */ 
// public static long upload(String filename,String newname) throws Exception  
// { 
//	 try
//	 {
//	     long result = 0; 
//	     TelnetOutputStream os = null; 
//	     FileInputStream is = null; 
//	     try {          
//	         java.io.File fileIn = new java.io.File(filename); 
//	         if (!fileIn.exists()) return -1; 
//	         if (fileIn.length()==0) return -2; 
//	         os = ftpClient.put(newname); 
//	         result = fileIn.length(); 
//	         is = new FileInputStream(fileIn); 
//	         byte[] bytes = new byte[1024]; 
//	         int c; 
//	         while ((c = is.read(bytes)) != -1) { 
//	              os.write(bytes, 0, c); 
//	         } 
//	     } finally { 
//	         if (is != null) { 
//	             is.close(); 
//	         } 
//	         if (os != null) { 
//	            os.close(); 
//	         } 
//	     } 
//	    return result;  
//	 }catch(Exception e)
//	 {
//		  throw new Exception("Upload file error:"+e.toString());
//	 }
//
// } 
// /** 
//    * upload 
//    * @throws java.lang.Exception 
//    * @return  
//    * @param filename 
//    */ 
// public long upload(String filename) 
// throws Exception  
// { 
//    String newname = ""; 
//    if (filename.indexOf("/")>-1) 
//    { 
//       newname = filename.substring(filename.lastIndexOf("/")+1); 
//    }else 
//    { 
//       newname = filename; 
//    } 
//    return upload(filename,newname); 
// } 
//  
// /** 
//    *  download 
//    *  ��ftp�����ļ������� 
//    * @throws java.lang.Exception 
//    * @return  
//    * @param newfilename �������ɵ��ļ��� 
//    * @param filename �������ϵ��ļ��� 
//    */ 
// public long download(String filename,String newfilename)  
// throws Exception 
// {   
//    long result = 0; 
//    TelnetInputStream is = null; 
//    FileOutputStream os = null; 
//    try  
//    { 
//       is = ftpClient.get(filename);        
//       java.io.File outfile = new java.io.File(newfilename); 
//       os = new FileOutputStream(outfile); 
//       byte[] bytes = new byte[1024]; 
//       int c; 
//       while ((c = is.read(bytes)) != -1) { 
//           os.write(bytes, 0, c); 
//           result = result + c; 
//       } 
//    } catch (IOException e)  
//    { 
//       e.printStackTrace(); 
//    } 
//    finally { 
//         if (is != null) { 
//             is.close(); 
//         } 
//         if (os != null) { 
//            os.close(); 
//         } 
//     } 
//     return result; 
// } 
// /** 
//  * ȡ��ĳ��Ŀ¼�µ������ļ��б� 
//  * 
//  */ 
// public List getFileList(String path) 
// { 
//    List list = new ArrayList(); 
//    try  
//    { 
//       DataInputStream dis = new  DataInputStream(ftpClient.nameList(path)); 
//       String filename = ""; 
//       while((filename=dis.readLine())!=null)   
//       {   
//         list.add(filename);         
//       }   
//    
//    } catch (Exception e)  
//    { 
//       e.printStackTrace(); 
//    } 
//    return list; 
// } 
//  
// /** 
//    * closeServer 
//    * �Ͽ���ftp������������ 
//    * @throws java.io.IOException 
//    */ 
// public static void closeServer() 
// throws IOException  
// {    
//   try  
//   { 
//      if (ftpClient != null)  
//      { 
//        ftpClient.closeServer();      
//      } 
//   } catch (IOException e) { 
//      e.printStackTrace(); 
//   } 
// }  
//}
//
