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
//				  log.error("上传文件参数为空！");
//				  return;
//			  }
//	         /**连接ftp服务器，并进入指定目录 */
//	         connectServer(server,user,password,remotePath); 
//	         /**  上传文件到指定文件夹下 */ 
//	         long fileSize=upload(localFile,remoteFileName);
//	         if(fileSize==-1||fileSize==-2)
//	         {
//	        	 log.error("文件为空或不存在，上传失败！"); 
//	        	 return;
//	         }
//	         log.info("成功上传文件:"+localFile+" 至服务器路径："+ remotePath +"， 文件大小："+fileSize+"字节"); 
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
//    * 连接ftp服务器 
//    * @param user   登陆用户
//    * @param password 密码 
//    * @param server 服务器地址 
//    * @param path 文件夹，空代表根目录
//    */ 
//  public static void connectServer(String server, String user, String password, String path)throws Exception  
//  { 
//	  /** server：FTP服务器的IP地址；user:登录FTP服务器的用户名*/ 
//	  /** password：登录FTP服务器的用户名的口令；path：FTP服务器上的路径 */ 
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
//	  //path是ftp服务下主目录的子目录 
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
//	  //用2进制上传、下载 
//	  ftpClient.binary();
// } 
//  
// /** 
//    * upload 
//    * 上传文件 
//    * @throws java.lang.Exception 
//    * @return -1 文件不存在 
//    *          -2 文件内容为空  
//    *          >0 成功上传，返回文件的大小 
//    * @param newname 上传后的新文件名 
//    * @param filename 上传的文件 
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
//    *  从ftp下载文件到本地 
//    * @throws java.lang.Exception 
//    * @return  
//    * @param newfilename 本地生成的文件名 
//    * @param filename 服务器上的文件名 
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
//  * 取得某个目录下的所有文件列表 
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
//    * 断开与ftp服务器的链接 
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
