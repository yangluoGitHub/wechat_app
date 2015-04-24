package com.weili.wechat.business.share;

import com.weili.wechat.common.CalendarUtil;


/**
 * �����Ž��������ĺ���
 * @author hpshen
 */

public final class Translate {

	 /**
     * �����豸���С����б�־Ϊ����
     * @param key �豸���б�־��1������2�����У�
     * @return �豸����״̬
     */
	static int language = 1;
	static final String unknow_zh="δ֪";
	static final String unknow_us="Unknown";
	static final String case_zh="����";
	static final String case_us="Fault";
	
	public  void setlanguage(int a ){
		language = a;
	}
	public static String parseAwayFlagBh(int key){
		String r="";
		switch(key){
		    case 1:
		    	switch(language){
		    	case 1:r= "����";break;
		    	case 2:r= "����";break;
		    	case 3:r= "In the bank";break;
		    	}break;
		    case 2:
		    	switch(language){
		    	case 1:r= "����";break;
		    	case 2:r= "�x��";break;
		    	case 3:r= "Not the bank";break;
		    	}break;
		    default:
		    	switch(language){
		    	case 1: r= unknow_zh;break;
		    	case 2: r= unknow_zh;break;
		    	case 3: r= unknow_us;break;
		    	}
		}
		return r;
	}
	
    /**
     * �����豸���С����б�־Ϊ����
     * @param key �豸���б�־��1������2�����У�
     * @return �豸����״̬
     */
	
	public static String parseAwayFlag(int key){
		String r="";
		switch(key){
		    case 1:
		    	switch(language){
		    	case 1:r="��������������";break;
		    	case 2:r="�����������Յ^";break;
		    	case 3:r="Self-service areas in the bank";break;
		    	}break;
		    case 2:
		    	switch(language){
		    	case 1:r="�����������������(��Ӫ)";break;
		    	case 2:r="�ΙC�x�����������c(�ԠI)";break;
		    	case 3:r="Stand-alone self-service points from the line (self)";break;
		    	}break;
		    case 3:
		    	switch(language){
		    	case 1:r="������������(��Ӫ)-����ͤ";break;
		    	case 2:r="�x�������y��(�ԠI)-���yͤ";break;
		    	case 3:r="Line from self-help bank (self) - Non-Bank Pavilion";break;
		    	}break;
		    case 4:
		    	switch(language){
		    	case 1:r="������������(��Ӫ)-��ͤ";break;
		    	case 2:r="�x�������y��(�ԠI)-�yͤ";break;
		    	case 3:r="Line from self-help bank (self) - Silver Pavilion";break;
		    	}break;
		    case 5:
		    	switch(language){
		    	case 1:r="�����������������(��Ӫ)";break;
		    	case 2:r="�ΙC�x�����������c(�I)";break;
		    	case 3:r="Stand-alone self-service points from the line (joint venture)";break;
		    	}break;
		    case 6:
		    	switch(language){
		    	case 1:r="������������(��Ӫ)-����ͤ";break;
		    	case 2:r="�x�������y��(�I)-���yͤ";break;
		    	case 3:r="From self-service banking firms (joint venture) - Non-Bank Pavilion";break;
		    	}break;
		    case 7:
		    	switch(language){
		    	case 1:r="������������(��Ӫ)-��ͤ";break;
		    	case 2:r="�x�������y��(�I)-�yͤ";break;
		    	case 3:r="From self-service banking firms (joint venture) - Silver Pavilion";break;
		    	}break;
		    case 8:
		    	switch(language){
		    	case 1:r="�����������������";break;
		    	case 2:r="�ΙC�������������c";break;
		    	case 3:r="Stand-alone self-service point line";break;
		    	}break;
		    default:
		    	switch(language){
		    	case 1: r= unknow_zh;break;
		    	case 2: r= unknow_zh;break;
		    	case 3: r= unknow_us;break;
		    	}
		}
		return r;
	}
	

    /**
     * �����豸��Ӫ��ʽΪ����
     * @param key �豸��Ӫ��ʽ��1����Ӫ2����Ӫ��
     * @return �豸��Ӫ��ʽ
     */
	
	public static String parseWorkType(int key){
		String r="";
		switch(key){
		    case 1:
		    	switch(language){
		    	case 1: r= "��Ӫ";break;
		    	case 2: r= "�ԠI";break;
		    	case 3: r= "self";break;
		    	}break;
		    case 2:
		    	switch(language){
		    	case 1:r="��Ӫ";break;
		    	case 2:r="�I";break;
		    	case 3:r="Joint Venture";break;
		    	}break;
		    default:
		    	r="";
		}
		return r;
	}
	
	
	/**
     * �����豸ģ��״̬Ϊ����
     * @param status �豸ģ��״̬��null��UNKNOWN��δ֪,HEALTHY������,FATAL������,WARNING�����棩
     * @return �豸ģ��״̬
     */
	
	public static String parseModStatus(String status) {
		String r="";
		//System.out.println("--language["+language+"]");
        if(status == null){
        	switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
        }
        else if (status.equalsIgnoreCase("HEALTHY")){
        	switch(language){
        	case 1:r="����";break;
        	case 2:r="����";break;
        	case 3:r="Normal";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("FATAL")){
	    	switch(language){
	    	case 1:r=case_zh;break;
        	case 2:r=case_zh;break;
        	case 3:r=case_us;break;
	    	}
	    }
	    else if (status.equalsIgnoreCase("WARNING")){
	    	switch(language){
	    	case 1:r="����";break;
        	case 2:r="����";break;
        	case 3:r="Warning";break;
	    	}
	    }
	    else if (status.equalsIgnoreCase("UNKNOWN")){
	    	switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
	    }
	    else{
	    	switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
	    }
        return r;
	}
	/**
     * �����豸����ģ��״̬Ϊ����
     * @param status �豸ģ��״̬��null��UNKNOWN��������,HEALTHY������,FATAL������,WARNING�����棩
     * @return �豸ģ��״̬
     */
	
	public static String parseParticularModStatus(String status) {
		String r="";
        if(status == null){
        	switch(language){
        	case 1:r="������";break;
        	case 2:r="������";break;
        	case 3:r="Does not exist";break;
        	}
        }
        else if (status.equalsIgnoreCase("HEALTHY")){
        	switch(language){
        	case 1:r="����";break;
        	case 2:r="����";break;
        	case 3:r="Normal";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("FATAL")){
	    	switch(language){
	    	case 1:r=case_zh;break;
        	case 2:r=case_zh;break;
        	case 3:r=case_us;break;
	    	}
	    }
	    else if (status.equalsIgnoreCase("WARNING")){
	    	switch(language){
	    	case 1:r="����";break;
        	case 2:r="����";break;
        	case 3:r="Warning";break;
	    	}
	    }
	    else if (status.equalsIgnoreCase("UNKNOWN")){
	    	switch(language){
        	case 1:r="������";break;
        	case 2:r="������";break;
        	case 3:r="Does not exist";break;
        	}
	    }
	    else{
	    	switch(language){
        	case 1:r="������";break;
        	case 2:r="������";break;
        	case 3:r="Does not exist";break;
        	}
	    }
        return r;
	}
	/**
     * �����豸Ǯ��״̬Ϊ����
     * @param status �豸Ǯ��״̬��null��UNKNOWN��δ֪��FULL�����㣬LOW�����㣬EMPTY��ȱ����
     * @return �豸Ǯ��״̬
     */
	
	public static String parseCashStatus(String status) {
		String r="";
        if(status == null){
        	switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
        }
        else if (status.equalsIgnoreCase("OK")){
        	switch(language){
        	case 1:r="����";break;
        	case 2:r="����";break;
        	case 3:r="Normal";break;
        	}
	    }
        else if (status.equalsIgnoreCase("FULL")){
        	switch(language){
        	case 1:r="����";break;
        	case 2:r="�M�n";break;
        	case 3:r="cashIn Full";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("LOW")){
	    	switch(language){
        	case 1:r="����";break;
        	case 2:r="����";break;
        	case 3:r="not enough";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("EMPTY")){
	    	switch(language){
        	case 1:r="ȱ��";break;
        	case 2:r="ȱ�n";break;
        	case 3:r="Lack of money";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("UNKNOWN")){
	    	switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
	    }
	    else{
	    	switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
	    }
        return r;
	}	
	
	/**
     * �����豸����״̬Ϊ����
     * @param status �豸����״̬��null��UNKNOWN��δ֪��HEALTHY��������CLOSE���ػ���MAINTAIN��ά����PARTSERVICE�����ַ���NOSERVICE��ֹͣ����NETFATAL��PͨѶ���ϣ�
     */
	public static String parseRunStatus(String status) {
		String r="";
        if(status == null){
        	switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
        }
        else if (status.equalsIgnoreCase("HEALTHY")){
        	switch(language){
        	case 1:r="����";break;
        	case 2:r="����";break;
        	case 3:r="Normal";break;
        	}
	    }
        else if (status.equalsIgnoreCase("UNKNOWN")){
        	switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
	    }
	    else if (status.equalsIgnoreCase("CLOSE")){
	    	switch(language){
        	case 1:r="�ػ�";break;
        	case 2:r="�P�C";break;
        	case 3:r="shutdown";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("MAINTAIN")){
	    	switch(language){
        	case 1:r="ά��";break;
        	case 2:r="�S�o";break;
        	case 3:r="maintenance";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("PARTSERVICE")){
	    	switch(language){
        	case 1:r="���ַ���";break;
        	case 2:r="���ַ���";break;
        	case 3:r="reducedservice";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("NOSERVICE")){
	    	switch(language){
        	case 1:r="ֹͣ����";break;
        	case 2:r="ֹͣ����";break;
        	case 3:r="Stop services";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("NETFATAL")){
	    	switch(language){
        	case 1:r="PͨѶ����";break;
        	case 2:r="PͨӍ����";break;
        	case 3:r="P communications failure";break;
        	}
	    }	  
	    else if (status.equalsIgnoreCase("STOP")){
	    	switch(language){
        	case 1:r="ͣ��";break;
        	case 2:r="ͣ�C";break;
        	case 3:r="disuse";break;
        	}
	    }	
	    else{
	    	switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
	    }
        return r;
	}		
	
	/**
     * �����豸����״̬ΪӢ��
     * @param actionType �豸����״̬��1��HEALTHY��2��PARTSERVICE��3��MAINTAIN��4��NETFATAL��5��7��8��NOSERVICE��6��CLOSE��
     */
	
    public static String getRunStatus(String actionType){
    	
    	if(actionType == null){
    		return null;
    	}
    	if(actionType.equalsIgnoreCase("1") || actionType.equalsIgnoreCase("9")){
			return "HEALTHY";
		}
		if(actionType.equalsIgnoreCase("2")){
			return "PARTSERVICE";
		}
		if(actionType.equalsIgnoreCase("3")){
			return "MAINTAIN";							
		}
		if(actionType.equalsIgnoreCase("4")){
			return "NETFATAL";
		}
		if(actionType.equalsIgnoreCase("5") || actionType.equalsIgnoreCase("7") || actionType.equalsIgnoreCase("8")){
			return "NOSERVICE";
		}
		if(actionType.equalsIgnoreCase("6")){
			return "CLOSE";
		}
		return null;
    }
	
	public static String getActionType(String runStatus){
    	
    	if(runStatus == null){
    		return null;
    	}
    	if(runStatus.equalsIgnoreCase("HEALTHY")){
			return "1";
		}
		if(runStatus.equalsIgnoreCase("PARTSERVICE")){
			return "2";
		}
		if(runStatus.equalsIgnoreCase("MAINTAIN")){
			return "3";							
		}
		if(runStatus.equalsIgnoreCase("NETFATAL")){
			return "4";
		}
		if(runStatus.equalsIgnoreCase("NOSERVICE")){
			return "8";
		}
		if(runStatus.equalsIgnoreCase("CLOSE")){
			return "6";
		}
		return null;
    }
	
	/**
     * �����豸����״̬Ϊ����
     * @param status �豸����״̬(null��UNKNOWN��δ֪��HEALTHY��������FATAL�����ϣ�WARNING������)
     */
	
	public static String parseNetStatus(String status) {
		String r="";
        if(status == null){
        	switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
        }
        else if (status.equalsIgnoreCase("HEALTHY")){
        	switch(language){
        	case 1:r="����";break;
        	case 2:r="����";break;
        	case 3:r="Normal";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("FATAL")){
	    	switch(language){
	    	case 1:r=case_zh;break;
        	case 2:r=case_zh;break;
        	case 3:r=case_us;break;
	    	}
	    }
	    else if (status.equalsIgnoreCase("WARNING")){
	    	switch(language){
	    	case 1:r="����";break;
        	case 2:r="����";break;
        	case 3:r="Warning";break;
	    	}
	    }
	    else if (status.equalsIgnoreCase("UNKNOWN")){
	    	switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
	    }
	    else{
	    	switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
	    }
        return r;
	}

	/**
     * �����豸����״̬Ϊ����
     * @param status �豸����״̬(null��UNKNOWN:δ֪ HEALTHY:���� NOSERVICE:���ֽ���)
     */
	
	public static String parseTxStatus(String status) {
		String r="";
        if(status == null){
        	switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
        }
        else if (status.equalsIgnoreCase("HEALTHY")){
        	switch(language){
        	case 1:r="����";break;
        	case 2:r="����";break;
        	case 3:r="Normal";break;
        	}
	    }
        else if (status.equalsIgnoreCase("WARNING")){
        	switch(language){
	    	case 1:r="����";break;
        	case 2:r="����";break;
        	case 3:r="Warning";break;
	    	}
	    }
	    else if (status.equalsIgnoreCase("NOSERVICE")){
	    	switch(language){
        	case 1:r="�޽���";break;
        	case 2:r="�o����";break;
        	case 3:r="Non-cash transactions";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("UNKNOWN")){
	    	switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
	    }
	    else{
	    	switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
	    }
        return r;
	}
	/**
	 * ����XFS���ϼ���
	 * 
	 * @param faultLevel XFS���ϼ���0��������1������Ԥ����2����ͨ���ϣ�3�����ع��ϣ�
	 * @return	���ϼ������
	 */
	public static String  parseFaultLevel(int faultLevel){
		String levelDescription="";
		if(faultLevel==0){
			switch(language){
        	case 1:levelDescription="����";break;
        	case 2:levelDescription="����";break;
        	case 3:levelDescription="Normal";break;
        	}
		}else if(faultLevel==1){
			switch(language){
        	case 1:levelDescription="����Ԥ��";break;
        	case 2:levelDescription="�����A��";break;
        	case 3:levelDescription="Predictive Failure";break;
        	}
		}else if(faultLevel==2){
			switch(language){
        	case 1:levelDescription="��ͨ����";break;
        	case 2:levelDescription="��ͨ����";break;
        	case 3:levelDescription="General fault";break;
        	}
		}else if(faultLevel==3){
			switch(language){
        	case 1:levelDescription="���ع���";break;
        	case 2:levelDescription="���ع���";break;
        	case 3:levelDescription="Serious problems";break;
        	}
		}
		return levelDescription;
	}
	
	/**
	 * ����CASE��ǰ״̬Ϊ����
	 * 
	 * @param iStatus case״̬����(1���Ѵ���,2����֪ͨ,3��֪ͨ����,4����ȷ��,5����Ӧ����,6������Ӧ,7����������,8:�ѹ���,9:�ѹر�)
	 * @return	case״̬�������
	 */
	public static String  parseCurStatus(int iStatus){
		String statusName="";
		if(iStatus==1){
			switch(language){
        	case 1:statusName="�Ѵ���";break;
        	case 2:statusName="�ф���";break;
        	case 3:statusName="Has been created";break;
        	}
		}else if(iStatus==2){
			switch(language){
        	case 1:statusName="��֪ͨ";break;
        	case 2:statusName="��֪ͨ";break;
        	case 3:statusName="Have been notified";break;
        	}
		}else if(iStatus==3){
			switch(language){
        	case 1:statusName="֪ͨ����";break;
        	case 2:statusName="֪ͨ����";break;
        	case 3:statusName="Upgrade notification";break;
        	}
		}else if(iStatus==4){
			switch(language){
        	case 1:statusName="��ȷ��";break;
        	case 2:statusName="�Ѵ_�J";break;
        	case 3:statusName="Has confirmed";break;
        	}
		}else if(iStatus==5){
			switch(language){
        	case 1:statusName="��Ӧ����";break;
        	case 2:statusName="푑�����";break;
        	case 3:statusName="Response Upgrade";break;
        	}
		}else if(iStatus==6){
			switch(language){
        	case 1:statusName="����Ӧ";break;
        	case 2:statusName="��푑�";break;
        	case 3:statusName="Have to respond to";break;
        	}
		}else if(iStatus==7){
			switch(language){
        	case 1:statusName="��������";break;
        	case 2:statusName="��������";break;
        	case 3:statusName="progression of failure";break;
        	}
		}else if(iStatus==8){
			switch(language){
        	case 1:statusName="�ѹ���";break;
        	case 2:statusName="�ђ���";break;
        	case 3:statusName="Has been suspended";break;
        	}
		}else if(iStatus==9){
			switch(language){
        	case 1:statusName="�ѹر�";break;
        	case 2:statusName="���P�]";break;
        	case 3:statusName="Closed";break;
        	}		
		}
		return statusName;
	}
	
	/**
	 * ����CASE��ǰ״̬Ϊ��־
	 * @param statusName case״̬����(�Ѵ�����1,��֪ͨ��2��֪ͨ������3,��ȷ�ϣ�4,��Ӧ������5,����Ӧ��6,����������7,�ѹ���8,�ѹرգ�9)
	 * @return	case״̬�������
	 */
	public static int  parseCurStatusName(String statusName){
		int iStatus=0;
		if(statusName.equalsIgnoreCase("�Ѵ���")){
			iStatus=1;
		}else if(statusName.equalsIgnoreCase("��֪ͨ")){
			iStatus=2;
		}else if(statusName.equalsIgnoreCase("֪ͨ����")){
			iStatus=3;
		}else if(statusName.equalsIgnoreCase("��ȷ��")){
			iStatus=4;
		}else if(statusName.equalsIgnoreCase("��Ӧ����")){
			iStatus=5;
		}else if(statusName.equalsIgnoreCase("����Ӧ")){
			iStatus=6;
		}else if(statusName.equalsIgnoreCase("��������")){
			iStatus=7;
		}else if(statusName.equalsIgnoreCase("�ѹ���")){
			iStatus=8;
		}else if(statusName.equalsIgnoreCase("�ѹر�")){
			iStatus=9;
		}
		return iStatus;
	}
	
	/**
	 * @param cmdid - �������
	 * @return cmdName - �������
	 */
	/*public static String  parseCmd(String cmdid){
		String cmdName="";
		if(cmdid.equalsIgnoreCase("query")){
			cmdName="�ۺϲ�ѯ";
		}else if(cmdid.equalsIgnoreCase("create")){
			cmdName="�˹�����";
		}else if(cmdid.equalsIgnoreCase("close")){
			cmdName="�˹��ر�";
		}else if(cmdid.equalsIgnoreCase("delete")){
			cmdName="ɾ��";
		}else if(cmdid.equalsIgnoreCase("record")){
			cmdName="��¼";
		}else if(cmdid.equalsIgnoreCase("notify")){
			cmdName="֪ͨ��ѯ";
		}else if(cmdid.equalsIgnoreCase("trace")){
			cmdName="���ٲ�ѯ";
		}else if(cmdid.equalsIgnoreCase("fault")){
			cmdName="���ϱ����ѯ";
		}
		
		return cmdName;
	}*/
	
	/**
	 * ��CASE���ɷ�ʽ����Ϊ����
	 * @param type CASE���ɷ�ʽ(M:�˹�,A:�Զ�)
	 * @return CASE���ɷ�ʽ
	 */
	public static String parseCreateType(String type){
		
		String typeName="";
		if(type.equalsIgnoreCase("M")){
			switch(language){
        	case 1:typeName="�˹�";break;
        	case 2:typeName="�˹�";break;
        	case 3:typeName="Artificial";break;
        	}
		}else if(type.equalsIgnoreCase("A")){
			switch(language){
        	case 1:typeName="�Զ�";break;
        	case 2:typeName="�Ԅ�";break;
        	case 3:typeName="Automatic";break;
        	}
		}
		
		return typeName;
	}
	
	/**
	 * ��Ǯ������ת��Ϊ���Ľ���
	 * @param type 
	 * @return Ǯ������
	 */
	public static String parseCimCdmCuType(String type){
		
		String typeName=type;
		if(type.equalsIgnoreCase("RejectCassette")){
			switch(language){
        	case 1:typeName="�ܳ���";break;
        	case 2:typeName="���n��";break;
        	case 3:typeName="The box of refused to money ";break;
        	}
		}else if(type.equalsIgnoreCase("RetractCassette")){
			switch(language){
        	case 1:typeName="������";break;
        	case 2:typeName="������";break;
        	case 3:typeName="recycling bin";break;
        	}
		}else if(type.equalsIgnoreCase("Recycling")){
			switch(language){
        	case 1:typeName="ѭ����";break;
        	case 2:typeName="ѭ�h��";break;
        	case 3:typeName="Recycling box";break;
        	}
		}else if(type.equalsIgnoreCase("BillCassette")){
			switch(language){
        	case 1:typeName="ȡ����";break;
        	case 2:typeName="ȡ����";break;
        	case 3:typeName="Cash box";break;
        	}
		}else if(type.equalsIgnoreCase("CashIn")){
			switch(language){
        	case 1:typeName="�����";break;
        	case 2:typeName="�����";break;
        	case 3:typeName="Deposit box";break;
        	}
		}else if(type.equalsIgnoreCase("NOTAPPLICABLE")){
			switch(language){
        	case 1:typeName="δʹ��";break;
        	case 2:typeName="δʹ��";break;
        	case 3:typeName="Unused";break;
        	}
		}
		return typeName;
	}
	
	/**
	 * ��Ǯ������ת��Ϊ���Ľ���
	 * @param type 
	 * @return Ǯ������
	 */
	public static String parseCurrencyType(String type){
		if(type.indexOf(",") >= 0)
		{
			type = type.split(",",-1)[0];
		}
		String typeName=type;
		if(type.equalsIgnoreCase("CNY")||type.equalsIgnoreCase("RMB")){
			switch(language){
        	case 1:typeName="�����";break;
        	case 2:typeName="�����";break;
        	case 3:typeName="Chinese Yuan Renminbi";break;
        	}
		}else if(type.equalsIgnoreCase("HKD")){
			switch(language){
        	case 1:typeName="�۱�";break;
        	case 2:typeName="�ێ�";break;
        	case 3:typeName="HKD";break;
        	}
		}else if(type.equalsIgnoreCase("USD")){
			switch(language){
        	case 1:typeName="��Ԫ";break;
        	case 2:typeName="��Ԫ";break;
        	case 3:typeName="U.S. Dollar";break;
        	}
		}
		return typeName;
	}
	
	/**
	 * ��Ǯ��״̬ת��Ϊ���Ľ���
	 * @param type 
	 * @return Ǯ��״̬
	 */
	public static String parseCuStatus(String type){
		
		String typeName=type;
		if(type.equalsIgnoreCase("OK")||type.equalsIgnoreCase("HEALTHY")){
			switch(language){
        	case 1:typeName="����";break;
        	case 2:typeName="����";break;
        	case 3:typeName="Normal";break;
        	}
		}else if(type.equalsIgnoreCase("FULL")){
			switch(language){
        	case 1:typeName="��";break;
        	case 2:typeName="�M";break;
        	case 3:typeName="Full";break;
        	}
		}else if(type.equalsIgnoreCase("HIGH")){
			switch(language){
        	case 1:typeName="��";break;
        	case 2:typeName="��";break;
        	case 3:typeName="High";break;
        	}
		}else if(type.equalsIgnoreCase("LOW")){
			switch(language){
        	case 1:typeName="��";break;
        	case 2:typeName="��";break;
        	case 3:typeName="Low";break;
        	}
		}else if(type.equalsIgnoreCase("EMPTY")){
			switch(language){
        	case 1:typeName="��";break;
        	case 2:typeName="��";break;
        	case 3:typeName="Empty";break;
        	}
		}else if(type.equalsIgnoreCase("MISSING")){
			switch(language){
        	case 1:typeName="��";break;
        	case 2:typeName="�o";break;
        	case 3:typeName="Without";break;
        	}
		}else if(type.equalsIgnoreCase("Manipulated")){
			switch(language){
        	case 1:typeName="����";break;
        	case 2:typeName="����";break;
        	case 3:typeName="Operation";break;
        	}
		}else if(type.equalsIgnoreCase("Inoperative")){
			switch(language){
        	case 1:typeName="������";break;
        	case 2:typeName="������";break;
        	case 3:typeName="Not available";break;
        	}
		}			
		 
		return typeName;
	}
	
	/**
	 * ����������ת��Ϊ���Ľ���
	 * @param type 
	 * @return ��������
	 */
	public static String parseTxType(String type){
		String r="";
		if(type == null){
			switch(language){
	        	case 1:r=unknow_zh;break;
	        	case 2:r=unknow_zh;break;
	        	case 3:r=unknow_us;break;
        	}
		}
		else if(type.equalsIgnoreCase("CWD")){
			switch(language){
	        	case 1:r="ȡ��";break;
	        	case 2:r="ȡ��";break;
	        	case 3:r="withdraw";break;
        	}
		} else if(type.equalsIgnoreCase("DET")){
			switch(language){
	        	case 1:r="���";break;
	        	case 2:r="���";break;
	        	case 3:r="Deposits";break;
        	}
		} else if(type.equalsIgnoreCase("INQ")){
			switch(language){
	        	case 1:r="��ѯ";break;
	        	case 2:r="��ԃ";break;
	        	case 3:r="Inquiry";break;
        	}
		} else if(type.equalsIgnoreCase("TFR")){
				switch(language){
		        	case 1:r="ת��";break;
		        	case 2:r="�D��";break;
		        	case 3:r="Transfer";break;
	        	}
		} else if(type.equalsIgnoreCase("PIN")){
				switch(language){
		        	case 1:r="����";break;
		        	case 2:r="����";break;
		        	case 3:r="Change density";break;
	        	}
		} else if(type.equalsIgnoreCase("OTH")){
				switch(language){
		        	case 1:r="����";break;
		        	case 2:r="����";break;
		        	case 3:r="Other";break;
	        	}
		}else {
			switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
		}
		return r;
	}
	/**
	 * ����������ת��Ϊ���Ľ���
	 * @param time 
	 * @return ��������
	 */
	public static String parseTxTime(String time){
		String r="";
		if(time == null){
			switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
		}
		else
		if(time.trim().length()==14){
			r= time.substring(0,4)+"-"+time.substring(4,6)+"-"+time.substring(6,8)+" "+time.substring(8,10)+":"+time.substring(10,12)+":"+time.substring(12,14);
		}
		else
		{
			return "δ֪";
		}
		return r;
	}
	
	/**
     * ��ȡ�豸����״̬��ɫ
     * @param status �豸����״̬(null��UNKNOWN:δ֪ HEALTHY:���� NOSERVICE:���ֽ���)
     */
	
	public static String parseTxColor(String status) {
		
        if(status == null){
        	 return "GREY";
        }
        else if (status.equalsIgnoreCase("HEALTHY")){
			return "";
	    }
	    else if (status.equalsIgnoreCase("NOSERVICE")){
			return "RED";
	    }
	    else if (status.equalsIgnoreCase("WARNING")){
			return "#cccc33";
	    }
	    else if (status.equalsIgnoreCase("UNKNOWN")){
			return "GREY";
	    }
	    else{
		    return "GREY";
	    }
	}
	
	/**
     * �豸����״̬��ɫ
     * @param status �豸����״̬��null��UNKNOWN��δ֪��HEALTHY��������CLOSE���ػ���MAINTAIN��ά����PARTSERVICE�����ַ���NOSERVICE��ֹͣ����NETFATAL��PͨѶ���ϣ�
     */
	public static String parseRunColor(String status) {
		
        if(status == null){
        	 return "GRAY";
        }
        else if (status.equalsIgnoreCase("HEALTHY")){
			return "";
	    }
        else if (status.equalsIgnoreCase("UNKNOWN")){
			return "GRAY";
	    }
	    else if (status.equalsIgnoreCase("CLOSE")){
			return "RED";
	    }
	    else if (status.equalsIgnoreCase("MAINTAIN")){
			return "RED";
	    }
	    else if (status.equalsIgnoreCase("PARTSERVICE")){
			return "#cccc33";
	    }
	    else if (status.equalsIgnoreCase("NOSERVICE")){
			return "RED";
	    }
	    else if (status.equalsIgnoreCase("NETFATAL")){
			return "RED";
	    }	    
	    else{
		    return "GRAY";
	    }
	}
	
	/**
     * �豸ģ��״̬��ɫ
     * @param status �豸ģ��״̬��null��UNKNOWN��δ֪,HEALTHY������,FATAL������,WARNING�����棩
     * @return �豸ģ��״̬
     */
	
	public static String parseModColor(String status) {
		
        if(status == null){
        	 return "GRAY";
        }
        else if (status.equalsIgnoreCase("HEALTHY")){
			return "";
	    }
	    else if (status.equalsIgnoreCase("FATAL")){
			return "RED";
	    }
	    else if (status.equalsIgnoreCase("WARNING")){
			return "#cccc33";
	    }
	    else if (status.equalsIgnoreCase("UNKNOWN")){
			return "GRAY";
	    }
	    else{
		    return "GRAY";
	    }
	}
	
	/**
     * �豸Ǯ��״̬��ɫ
     * @param status �豸Ǯ��״̬��null��UNKNOWN��δ֪��FULL�����㣬LOW�����㣬EMPTY��ȱ����
     * @return �豸Ǯ��״̬
     */
	
	public static String parseCashColor(String status) {
		
        if(status == null){
        	 return "GRAY";
        }
        else if (status.equalsIgnoreCase("OK")){
			return "";
	    }
	    else if (status.equalsIgnoreCase("LOW")){
			return "#cccc33";
	    }
	    else if (status.equalsIgnoreCase("EMPTY") || status.equalsIgnoreCase("FULL")){
			return "RED";
	    }
	    else if (status.equalsIgnoreCase("UNKNOWN")){
			return "GRAY";
	    }
	    else{
		    return "GRAY";
	    }
	}
	
	/**
     * �豸����״̬��ɫ
     * @param status �豸����״̬(null��UNKNOWN��δ֪��HEALTHY��������FATAL�����ϣ�WARNING������)
     */
	
	public static String parseNetColor(String status) {
		
        if(status == null){
        	 return "GRAY";
        }
        else if (status.equalsIgnoreCase("HEALTHY")){
			return "";
	    }
	    else if (status.equalsIgnoreCase("FATAL")){
			return "RED";
	    }
	    else if (status.equalsIgnoreCase("WARNING")){
			return "#cccc33";
	    }
	    else if (status.equalsIgnoreCase("UNKNOWN")){
			return "GRAY";
	    }
	    else{
		    return "GRAY";
	    }
	}
	
	/**
     * �豸ģ��״̬��ɫ
     * @param status �豸ģ��״̬��null��UNKNOWN��δ֪,HEALTHY������,FATAL������,WARNING�����棩
     * @return �豸ģ��״̬
     */
	
	public static String parseMutiModColor(String status) {
		
        if(status == null){
        	 return "GRAY";
        }
        else if (status.equalsIgnoreCase("HEALTHY")){
			return "";
	    }
	    else if (status.equalsIgnoreCase("FATAL")){
			return "RED";
	    }
	    else if (status.equalsIgnoreCase("WARNING")){
			return "#cccc33";
	    }
	    else if (status.equalsIgnoreCase("UNKNOWN")){
			return "GRAY";
	    }
	    else{
		    return "GRAY";
	    }
	}
	
	/**
	 * �����޽���ʱ��
	 * @param time 
	 * @return �޽��׵�ʱ��
	 */
	public static String parseNoServiceTime(String time){
		String r="";
		if(time == null){
			switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
		}
		else
		if(time.trim().length()==14){
			r= CalendarUtil.getDifOfTime(time);
		}
		else
		{
			switch(language){
        	case 1:r=unknow_zh;break;
        	case 2:r=unknow_zh;break;
        	case 3:r=unknow_us;break;
        	}
		}
		return r;
	}
	/**
	 * ���豸״̬���е��豸״̬ת��Ϊ�豸��Ϣ���е��豸״̬
	 * @param status 
	 * @return �豸��Ϣ���е��豸״̬
	 * 4������
	 * 5������
	 * 6������
	 * 7���ػ�
	 */
	public static Integer TransDevStatus(String netstatus,String modstatus)
    {
    	if(netstatus.equalsIgnoreCase("WARNING"))
    	{
    		return 5;
    	}
    	else if(netstatus.equalsIgnoreCase("FATAL"))
    	{
    		return 6;
    	}
    	else if(netstatus.equalsIgnoreCase("UNKNOWN"))
    	{
    		return 7;
    	}
    	else
    	{
    		if(modstatus == null){
    			return 7;//�ػ�
    		}
    		else if (modstatus.equalsIgnoreCase("HEALTHY")){
    			return 4;//����
    		}
    		else if (modstatus.equalsIgnoreCase("WARNING")){
    			return 5;//����
    		}
    		else if (modstatus.equalsIgnoreCase("FATAL")){
    			return 6;//����
    		}
    		else if (modstatus.equalsIgnoreCase("UNKNOWN")){
    			return 7;//�ػ�
    		}
    		else{
    			return 7;//�ػ�
    		}
    	}
    }
	
	/**
     * �豸����б�ʽ  ��ʾ��Ϊ��������
     * @param status �豸����б�ʽ  ��ʾ�У���
     * @return �豸����б�ʽ  ��ʾ������
     */
	
	public static String parseRowSelectMonitor(String status) {
		String r="";
        if(status == null){
        	 return "";
        }
		else if (status.equals("devTxStatus")){
			return "����״̬";
	    } 
        else if (status.equalsIgnoreCase("devRunStatus")){
        	switch(language){
        	case 1:r= "����״̬";break;
        	case 2:r= "�\�Р�B";break;
        	case 3:r= "Run State";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("devModStatus")){
	    	switch(language){
        	case 1:r= "ģ��״̬";break;
        	case 2:r= "ģ�M��B";break;
        	case 3:r= "Module State";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("devCashboxStatus")){
	    	switch(language){
        	case 1:r= "����״̬";break;
        	case 2:r= "�n���B";break;
        	case 3:r= "Box State";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("devNetStatus")){
	    	switch(language){
        	case 1:r= "����״̬";break;
        	case 2:r= "�W�j��B";break;
        	case 3:r= "Network Status";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("idcCaptureBinCount")){
	    	switch(language){
        	case 1:r= "�������̿�����";break;
        	case 2:r= "�x�����̿�����";break;
        	case 3:r= "The number of readers retain card";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("devType")){
	    	switch(language){
        	case 1:r= "�豸�ͺ�";break;
        	case 2:r= "�O����̖";break;
        	case 3:r= "Equipment Model";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("orgNo")){
	    	switch(language){
        	case 1:r= "��������";break;
        	case 2:r= "���ٙC��";break;
        	case 3:r= "subsidiary organ";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("address")){
	    	switch(language){
        	case 1:r= "װ����ַ";break;
        	case 2:r= "�b�C��ַ";break;
        	case 3:r= "Installation address";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("ip")){
	    	switch(language){
        	case 1:r= "IP��ַ";break;
        	case 2:r= "IP��ַ";break;
        	case 3:r= "IP Address";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("awayFlag")){
	    	switch(language){
        	case 1:r= "����/����";break;
        	case 2:r= "����/�x��";break;
        	case 3:r= "In the banking / not in the bank";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("workType")){
	    	switch(language){
        	case 1:r= "��Ӫ��ʽ";break;
        	case 2:r= "���I��ʽ";break;
        	case 3:r= "Mode of operation";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("case")){
	    	switch(language){
        	case 1:r= "��ѯcase";break;
        	case 2:r= "��ԃcase";break;
        	case 3:r= "Query case";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("fault")){
	    	switch(language){
        	case 1:r= "��ǰ����";break;
        	case 2:r= "��ǰ����";break;
        	case 3:r= "Fault current";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("control")){
	    	switch(language){
        	case 1:r= "Զ�̿���";break;
        	case 2:r= "�h�̿���";break;
        	case 3:r= "Remote Control";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("cashSum")){
	    	switch(language){
        	case 1:r= "�������";break;
        	case 2:r= "�n��R��";break;
        	case 3:r= "Cash Sum";break;
        	}
			return r;
	    }
	    else{
		    return "";
	    }
	}
	
//	/**
//     * �豸����б�ʽ  ��ʾ��Ϊ��������
//     * @param status �豸����б�ʽ  ��ʾ�У���
//     * @return �豸����б�ʽ  ��ʾ������
//     */
//	
//	public static String VauleOfRowSelectMonitor(String status,DevMonitor devInfo) {
//		
//        if(status == null){
//        	 return "";
//        }
//        else if (status.equalsIgnoreCase("devRunStatus")){
//			return "devRunStatus";
//	    }
//	    else if (status.equalsIgnoreCase("devModStatus")){
//			return "devModStatus";
//	    }
//	    else if (status.equalsIgnoreCase("devCashboxStatus")){
//			return "devCashboxStatus";
//	    }
//	    else if (status.equalsIgnoreCase("devNetStatus")){
//			return "devNetStatus";
//	    }
//	    else if (status.equalsIgnoreCase("idcCaptureBinCount")){
//			return devInfo.getIdcCaptureBinCount();
//	    }
//	    else if (status.equalsIgnoreCase("devType")){
//			return devInfo.getDevType();
//	    }
//	    else if (status.equalsIgnoreCase("orgNo")){
//			return devInfo.getOrgName();
//	    }
//	    else if (status.equalsIgnoreCase("address")){
//			return devInfo.getAddress();
//	    }
//	    else if (status.equalsIgnoreCase("ip")){
//			return devInfo.getIp();
//	    }
//	    else if (status.equalsIgnoreCase("awayFlag")){
//			return devInfo.getAwayFlag();
//	    }
//	    else if (status.equalsIgnoreCase("workType")){
//			return devInfo.getWorkType();
//	    }
//	    else if (status.equalsIgnoreCase("case")){
//			return "case";
//	    }
//	    else if (status.equalsIgnoreCase("fault")){
//			return "fault";
//	    }
//	    else if (status.equalsIgnoreCase("control")){
//			return "control";
//	    }
//	    else{
//		    return "";
//	    }
//	}
	
	/**
     * �����԰淭��case����
     * @param status case������Ϣ 
     * @return 
     */
	
	public static String translateCaseTraceInfo(String status) {
		String r = status;
        if(status == null){
        	 return "";
        }
        else if (status.equalsIgnoreCase("CASE����")){
        	switch(language){
        	case 1:r= "CASE����";break;
        	case 2:r= "CASE����";break;
        	case 3:r= "CASE create";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("CASE�˹�����")){
	    	switch(language){
        	case 1:r= "CASE�˹�����";break;
        	case 2:r= "CASE�˹�����";break;
        	case 3:r= "CASE artificial creation";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("CASE�Զ�����")){
	    	switch(language){
        	case 1:r= "CASE�Զ�����";break;
        	case 2:r= "CASE�Ԅӄ���";break;
        	case 3:r= "CASE automatically create";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("����CASE֪ͨʧ��")){
	    	switch(language){
        	case 1:r= "����CASE֪ͨʧ��";break;
        	case 2:r= "����CASE֪ͨʧ��";break;
        	case 3:r= "Failure to inform the creation of CASE";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("����CASE����û�ж��壬����֪ͨʧ��")){
	    	switch(language){
        	case 1:r= "����CASE����û�ж��壬����֪ͨʧ��";break;
        	case 2:r= "���CASE��͛]�ж��x������֪ͨʧ��";break;
        	case 3:r= "CASE absence of such a definition type, create a notification failure";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("���ڸ��������Ͷ�����󣬴���֪ͨʧ��")){
	    	switch(language){
        	case 1:r= "���ڸ��������Ͷ�����󣬴���֪ͨʧ��";break;
        	case 2:r= "���ؓ؟����Ͷ��x�e�`������֪ͨʧ��";break;
        	case 3:r= "Due to the definition of responsible persons of the type of error, failure to inform the creation of";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("�������豸�����ˣ�����֪ͨʧ��")){
	    	switch(language){
        	case 1:r= "�������豸�����ˣ�����֪ͨʧ��";break;
        	case 2:r= "��춟o�O��؟���ˣ�����֪ͨʧ��";break;
        	case 3:r= "Equipment as a result of non-responsibility, and create a notification failure";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("����������Ϸ�")){
	    	switch(language){
        	case 1:r= "����������Ϸ�";break;
        	case 2:r= "���녢�����Ϸ�";break;
        	case 3:r= "Parameters into illegal";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("����CASE֪ͨ�ɹ�")){
	    	switch(language){
        	case 1:r= "����CASE֪ͨ�ɹ�";break;
        	case 2:r= "����CASE֪ͨ�ɹ�";break;
        	case 3:r= "Notice of the success of the creation of CASE";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("����CASE֪ͨʧ��")){
	    	switch(language){
        	case 1:r= "����CASE֪ͨʧ��";break;
        	case 2:r= "����CASE֪ͨʧ��";break;
        	case 3:r= "Failure to inform the creation of CASE";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("���ڴ���CASE����Ϊnull����֪֯ͨ����ʧ��")){
	    	switch(language){
        	case 1:r= "���ڴ���CASE����Ϊnull����֪֯ͨ����ʧ��";break;
        	case 2:r= "��춂���CASE�����null���M��֪ͨ����ʧ��";break;
        	case 3:r= "Since the introduction of CASE for null, the failure of organizations to inform the content";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("����ģ�����޸���֪ͨ��ȡ����Ĭ��ģ��")){
	    	switch(language){
        	case 1:r= "����ģ�����޸���֪ͨ��ȡ����Ĭ��ģ��";break;
        	case 2:r= "��춹����Пoԓ�֪ͨ��ȡ��ʽĬ�J����";break;
        	case 3:r= "Because no such notification template, the default template check procedures";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("CASE�رճɹ�,�ر���Ա:")){
	    	switch(language){
        	case 1:r= "CASE�رճɹ�,�ر���Ա:";break;
        	case 2:r= "CASE�P�]�ɹ�,�P�]�ˆT:";break;
        	case 3:r= "CASE closed successful, closed personnel:";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("CASE�رճɹ�")){
	    	switch(language){
        	case 1:r= "CASE�رճɹ�";break;
        	case 2:r= "CASE�P�]�ɹ�";break;
        	case 3:r= "CASE closed successfully";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("����case�ر�֪ͨʧ��")){
	    	switch(language){
        	case 1:r= "����case�ر�֪ͨʧ��";break;
        	case 2:r= "����case�P�]֪ͨʧ��";break;
        	case 3:r= "Failure to inform the creation of case closure";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("������Ӧ")){
	    	switch(language){
        	case 1:r= "������Ӧ";break;
        	case 2:r= "�����ؑ�";break;
        	case 3:r= "The scene in response";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("CASEȷ��")){
	    	switch(language){
        	case 1:r= "CASEȷ��";break;
        	case 2:r= "CASE�_�J";break;
        	case 3:r= "CASE confirmed";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("��������")){
	    	switch(language){
        	case 1:r= "��������";break;
        	case 2:r= "��������";break;
        	case 3:r= "Failure to upgrade";break;
        	}
	    }
        return r;
	}
	
	
	public static void main(String[] args){	

	   
	}	

}