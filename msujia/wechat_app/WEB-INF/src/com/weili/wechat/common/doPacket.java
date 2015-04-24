package com.weili.wechat.common;

import java.math.*;

/* ������ж��ŷ��͵ı������������init()����Ϊ��ʼ�����������е��Ѿ��̶����ֶ��Ѿ���д��ϣ�
 * �������Ҫ�Ķ���ÿ���������ж�Ӧ��set����������������������֪�����壬��ʼ���ɶ�Ӧλ���Ŀո�
 * �����ʱ�ֶ��޸�
 * �������̣�����doPacket�࣬����init��ʼ���������ֶ��޸Ĳ��ֲ���������doPacketFinal������������
 * DATE��2008-09-25 */
public class doPacket {
	private	String packlen;		  /*���ĳ��ȣ���������������4λǰ��0*/
	
	//��ͷ
	private String packtyp;       /*�������� 0Ϊ8583����1Ϊ��8583��*/
	private String msgtyp;
	private String prccde;
	private String acqInstID;     /*�յ�������ͳһ��Ϊ8λ,����󲹿ո�*/
	private String termID;
	private String trcno;         /*����6λǰ��0*/
	private String custyp;        /*������*/
	private String cusidt;        /*����󲹿ո�*/
	private String apcode;
	private String srvuID;
	private String rspcde;
	private String packseq;       /* ������� */
	private String filler;        /* ������   */
	
	//����
	private String msgLen;        /*���峤�ȣ�����4λǰ��0������������*/
	private byte[] bitMap;
	private String field2;
	private String field3;
	private String sCompno;
	private String field47;   		/*������*/
	
	private String finalBuf;	/*����������*/

	public void init(){
		packlen = "";
		packtyp = "0";
		msgtyp = "8888";
		prccde = "AAAAAA";
		acqInstID = "        ";//δ֪��8λ
		termID = "CRM     ";
		trcno = "      ";//δ֪��6λ
		custyp = "AA";
		cusidt = "                         ";//δ֪��25λ
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
		sCompno = "      ";//δ֪��6λ
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
		//����mapλ�����������Ҳ�ɣ��ڳ�ʼ���������Ѿ�����
		this.bitMap = bitMap;
	}
	
	public byte[] getBitmap(){
		return bitMap;
	}
	
	public void setField2(String field2){
		//��λΪ�ֻ���
		this.field2 = field2.length() + field2;
	}
	
	public String getField2(){
		return field2;
	}

	public void setField3(String field3){
		//��λ�ڳ�ʼ���������Ѿ����ã����ֵ�����仯�ٵ��ô˺���
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
	
	//47��洢�û��Ķ���Ϣ������һ��ʱ���зָ�
	//ע�⣺���ڲ���Ӣ�Ļ������ģ�ÿ���ֶ���һ�����ȣ�������ԣ��������޸�
	public void setField47(String field47){
		int msgNum = field47.length();
		int tmpNum = 0;
		String tmpField47 = new String();
		String finalStr = new String();
		String strLength = new String();
		
		//�����������һ�����ָ���ţ���� currnum/totalnum ��ÿ������ǰ��
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
		//ȡ�ð���ĳ���
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

	//���մ������
	public void doPacketFinal(){
		int iPackLength = 0;
		int iMsgLength = (new String(bitMap) + field2 + field3 + field47).length();
		String sMsgLength = new String();
		String sPackLength = new String();
		String tmpFinalBuf = new String();
		
		//ȡ�ð���ĳ���
		for(int i = 3;i >= 0; i--){
			sMsgLength += String.valueOf(iMsgLength/(int)(Math.pow(10,i))%10);
		}
		this.setMsgLen(sMsgLength);
		
		tmpFinalBuf = (packtyp + msgtyp + prccde + acqInstID + termID + trcno + custyp + cusidt + apcode + srvuID + rspcde + packseq + filler + msgLen + new String(bitMap) + field2 + field3 + field47);
		
		//ȡ���������ĳ���
		iPackLength = tmpFinalBuf.length();
		for(int i = 3;i >= 0; i--){
			sPackLength += String.valueOf(iPackLength/(int)(Math.pow(10,i))%10);
		}
		this.setPacklen(sPackLength);
		
		//������շ��ͱ���
		finalBuf = sPackLength + tmpFinalBuf;
		
	}
	
}
