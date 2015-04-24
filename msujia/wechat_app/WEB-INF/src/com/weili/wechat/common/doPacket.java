package com.weili.wechat.common;

import java.math.*;

/* 该类进行短信发送的报文组包，其中init()函数为初始化函数，所有的已经固定的字段已经填写完毕，
 * 如果还需要改动，每个参数都有对应的set函数，其中有三个参数不知道含义，初始化成对应位数的空格，
 * 请调用时手动修改
 * 调用流程：定义doPacket类，调用init初始化函数，手动修改部分参数，调用doPacketFinal生成最终数据
 * DATE：2008-09-25 */
public class doPacket {
	private	String packlen;		  /*报文长度，不包括本身，不足4位前补0*/
	
	//包头
	private String packtyp;       /*报文种类 0为8583包，1为非8583包*/
	private String msgtyp;
	private String prccde;
	private String acqInstID;     /*收单机构号统一定为8位,不足后补空格*/
	private String termID;
	private String trcno;         /*不足6位前补0*/
	private String custyp;        /*卡种类*/
	private String cusidt;        /*不足后补空格*/
	private String apcode;
	private String srvuID;
	private String rspcde;
	private String packseq;       /* 报文序号 */
	private String filler;        /* 附加域   */
	
	//包体
	private String msgLen;        /*包体长度，不足4位前补0，不包括本身*/
	private byte[] bitMap;
	private String field2;
	private String field3;
	private String sCompno;
	private String field47;   		/*数据区*/
	
	private String finalBuf;	/*打包后的数据*/

	public void init(){
		packlen = "";
		packtyp = "0";
		msgtyp = "8888";
		prccde = "AAAAAA";
		acqInstID = "        ";//未知，8位
		termID = "CRM     ";
		trcno = "      ";//未知，6位
		custyp = "AA";
		cusidt = "                         ";//未知，25位
		apcode = "AA";
		srvuID = "        ";
		rspcde = "  ";
		packseq = "01";
		filler = "      ";
		msgLen = "";
		bitMap = new byte[8];
		bitMap[0] = 0x60;bitMap[1] = 0x00;bitMap[2] = 0x00;bitMap[3] = 0x00;
		bitMap[4] = 0x00;bitMap[5] = 0x01;bitMap[6] = 0x00;bitMap[7] = 0x00;
		field2 = "";
		field3 = prccde;
		sCompno = "      ";//未知，6位
		field47 = "";
	}
	
	public void setPacklen(String packlen){
		this.packlen = packlen;
	}
	
	public String getPacklen(){
		return packlen;
	}

	public void setPacktyp(String packtyp){
		this.packtyp = packtyp;
	}
	
	public String getPacktyp(){
		return packtyp;
	}
	
	public void setMsgtyp(String msgtyp){
		this.msgtyp = msgtyp;
	}
	
	public String getMsgtyp(){
		return msgtyp;
	}
	
	public void setPrccde(String prccde){
		this.prccde = prccde;
	}
	
	public String getPrccde(){
		return prccde;
	}

	public void setAcqInstID(String acqInstID){
		this.acqInstID = acqInstID;
	}
	
	public String getAcqInstID(){
		return acqInstID;
	}

	public void setTermID(String termID){
		this.termID = termID;
	}
	
	public String getTermID(){
		return termID;
	}

	public void setTrcno(String trcno){
		this.trcno = trcno;
	}
	
	public String getTrcno(){
		return trcno;
	}

	public void setCustyp(String custyp){
		this.custyp = custyp;
	}
	
	public String getCustyp(){
		return custyp;
	}

	public void setCusidt(String cusidt){
		this.cusidt = cusidt;
	}
	
	public String getCusidt(){
		return cusidt;
	}

	public void setApcode(String apcode){
		this.apcode = apcode;
	}
	
	public String getApcode(){
		return apcode;
	}

	public void setSrvuID(String srvuID){
		this.srvuID = srvuID;
	}
	
	public String getSrvuID(){
		return srvuID;
	}

	public void setRspcde(String rspcde){
		this.rspcde = rspcde;
	}
	
	public String getRrvuID(){
		return rspcde;
	}

	public void setPackseq(String packseq){
		this.packseq = packseq;
	}
	
	public String getPackseq(){
		return packseq;
	}

	public void setFiller(String filler){
		this.filler = filler;
	}
	
	public String getFiller(){
		return filler;
	}

	public void setMsgLen(String msgLen){
		this.msgLen = msgLen;
	}
	
	public String getMsgLen(){
		return msgLen;
	}

	public void setBitmap(byte[] bitMap){
		//设置map位，如果不设置也可，在初始化函数中已经设置
		this.bitMap = bitMap;
	}
	
	public byte[] getBitmap(){
		return bitMap;
	}
	
	public void setField2(String field2){
		//此位为手机号
		this.field2 = field2.length() + field2;
	}
	
	public String getField2(){
		return field2;
	}

	public void setField3(String field3){
		//此位在初始化函数中已经设置，如果值发生变化再调用此函数
		this.field3 = field3;
	}
	
	public String getField3(){
		return field3;
	}
	
	public void setSCompno(String sCompno){
		this.sCompno = sCompno;
	}
	
	public String getSCompno(){
		return sCompno;
	}
	
	//47域存储用户的短信息，超过一条时进行分割
	//注意：现在不管英文还是中文，每个字都算一个长度，如果不对，请自行修改
	public void setField47(String field47){
		int msgNum = field47.length();
		int tmpNum = 0;
		String tmpField47 = new String();
		String finalStr = new String();
		String strLength = new String();
		
		//如果字数超过一条，分割短信，添加 currnum/totalnum 在每条短信前面
		if(msgNum > 70){
			tmpNum = (int)(msgNum/66) + 1;
			for(int i = 0;i < tmpNum;i++){
				tmpField47 += (i+1) + "/" + tmpNum + field47.substring(i*66,(i+1)*66 >= field47.length()?field47.length():(i+1)*66);
			}
		}
		else{
			tmpField47 = field47;
		}
		
		finalStr = sCompno + field2.substring(2,13) + "                     " + tmpField47;
		//取得包体的长度
		for(int i = 2;i >= 0; i--){
			strLength += String.valueOf(finalStr.length()/(int)(Math.pow(10,i))%10);
		}
		
		this.field47 = strLength + finalStr;
	}
	
	public String getField47(){
		return field47;
	}

	public void setFinalBuf(String finalBuf){
		this.finalBuf = finalBuf;
	}
	
	public String getFinalBuf(){
		return finalBuf;
	}

	//最终打包程序
	public void doPacketFinal(){
		int iPackLength = 0;
		int iMsgLength = (new String(bitMap) + field2 + field3 + field47).length();
		String sMsgLength = new String();
		String sPackLength = new String();
		String tmpFinalBuf = new String();
		
		//取得包体的长度
		for(int i = 3;i >= 0; i--){
			sMsgLength += String.valueOf(iMsgLength/(int)(Math.pow(10,i))%10);
		}
		this.setMsgLen(sMsgLength);
		
		tmpFinalBuf = (packtyp + msgtyp + prccde + acqInstID + termID + trcno + custyp + cusidt + apcode + srvuID + rspcde + packseq + filler + msgLen + new String(bitMap) + field2 + field3 + field47);
		
		//取得整个包的长度
		iPackLength = tmpFinalBuf.length();
		for(int i = 3;i >= 0; i--){
			sPackLength += String.valueOf(iPackLength/(int)(Math.pow(10,i))%10);
		}
		this.setPacklen(sPackLength);
		
		//组合最终发送报文
		finalBuf = sPackLength + tmpFinalBuf;
		
	}
	
}
