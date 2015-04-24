package com.weili.wechat.key;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.util.List;
import java.util.Properties;

public class ProxoolUtil {
	private static String key = "0102030405060708";
	private static DESEncryptArith des= new DESEncryptArith();

	/**
	 * ��������
	 * @param data
	 * @param key
	 * @return ��λ���ݳ���+���ܺ������
	 */
	public static String encryptData(String data){
		int len = data.getBytes().length;
		byte[] tmp = new byte[(len/8+1)*8];
		System.arraycopy(data.getBytes(), 0, tmp, 0, len);
		try{
			byte[] result = des.doEncrypt(tmp, BinaryTransfer.ascToBin(key));
			return String.format("%02d", len)+ BinaryTransfer.binToAsc(result);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ��������
	 * @param data
	 * @param key
	 * @return ���ܺ������
	 */
	public static String dncryptData(String data){
		int len = Integer.parseInt(data.substring(0,2));
		try{
			byte[] result = des.doDecrypt(BinaryTransfer.ascToBin(data.substring(2)), BinaryTransfer.ascToBin(key));
			return new String(result,0,len);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * ��ȡ����
	 * @param file
	 * @return ��ȡ��ǰ�ļ��е�����
	 */
	public static String[] getData(String file){
		String[] result = new String[2];
		Document doc = FastXMLUtil.loadXMLs( file );
		Node root = doc.getRoot();		
		List<Node> nodeList = root.findChilds("proxool").get(0).getChildList();
		
		for(int i=0; i<nodeList.size(); i++ ){
			Node node = nodeList.get(i);
			if(node.getName().equals("driver-properties")){
				List<Node> properties = node.findChilds("property");
				for(int j=0;j<properties.size();j++){
					Node property = properties.get(j);
					if(property.getAttribute("name").equals("user")){
						result[0] = property.getAttribute("value");
					}else if(property.getAttribute("name").equals("password")){
						result[1] = property.getAttribute("value");
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * �滻��ǰ�ļ��е�����
	 * @param file
	 * @param username
	 * @param pwd
	 * @return 
	 */
	public static void replaceFileData(String file,String username, String pwd,String dbType) throws Exception{
		String[] fileFilter =file.split("\\.");
		if (fileFilter[fileFilter.length-1].equalsIgnoreCase("xml")){
			Document doc = FastXMLUtil.loadXMLs( file );
			Node root = doc.getRoot();		
			List<Node> nodeList = root.findChilds("proxool").get(0).getChildList();
			
			for(int i=0; i<nodeList.size(); i++ ){
				Node node = nodeList.get(i);
				if(node.getName().equals("driver-properties")){
					List<Node> properties = node.findChilds("property");
					for(int j=0;j<properties.size();j++){
						Node property = properties.get(j);
						if(property.getAttribute("name").equals("user")){
							property.setAttribute("value", username);
						}else if(property.getAttribute("name").equals("password")){
							property.setAttribute("value", pwd);
						}
					}
				}
			}
			
			DataOutputStream out = null;
			try{
				out = new DataOutputStream(new FileOutputStream(file,false));
				String outString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+doc.asXML();
				out.writeBytes(outString);
			}catch(Exception e){
				e.printStackTrace();
				throw new Exception("Save To File Error!");
			}finally{
				out.close();
			}
		}else{
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);
			prop.list(System.out);
			fis.close();
			if (dbType.equalsIgnoreCase("p")){
				prop.setProperty("atmpdb.username", username);
				prop.setProperty("atmpdb.password", pwd);
			}else{
				prop.setProperty("dbUser", username);
				prop.setProperty("dbPwd", pwd);
			}
			
			FileOutputStream fos = new FileOutputStream(file);
			prop.store(fos, "#config#");
			fos.close();
		}
	}
	
	/**
	 * �滻����
	 * @param file
	 * @return �滻��ǰDocument�е�����
	 */
	public static String replaceDocData(String file,String username, String pwd) throws Exception{
		Document doc = FastXMLUtil.loadXMLs( file );
		Node root = doc.getRoot();		
		List<Node> nodeList = root.findChilds("proxool").get(0).getChildList();
		
		for(int i=0; i<nodeList.size(); i++ ){
			Node node = nodeList.get(i);
			if(node.getName().equals("driver-properties")){
				List<Node> properties = node.findChilds("property");
				for(int j=0;j<properties.size();j++){
					Node property = properties.get(j);
					if(property.getAttribute("name").equals("user")){
						property.setAttribute("value", username);
					}else if(property.getAttribute("name").equals("password")){
						property.setAttribute("value", pwd);
					}
				}
			}
		}
		
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+doc.asXML();
	}
	
}
