package com.weili.wechat.business.share;

import com.weili.wechat.common.CalendarUtil;


/**
 * 将代号解析成中文含义
 * @author hpshen
 */

public final class Translate {

	 /**
     * 解析设备离行、在行标志为中文
     * @param key 设备在行标志（1：在行2：离行）
     * @return 设备在行状态
     */
	static int language = 1;
	static final String unknow_zh="未知";
	static final String unknow_us="Unknown";
	static final String case_zh="故障";
	static final String case_us="Fault";
	
	public  void setlanguage(int a ){
		language = a;
	}
	public static String parseAwayFlagBh(int key){
		String r="";
		switch(key){
		    case 1:
		    	switch(language){
		    	case 1:r= "在行";break;
		    	case 2:r= "在行";break;
		    	case 3:r= "In the bank";break;
		    	}break;
		    case 2:
		    	switch(language){
		    	case 1:r= "离行";break;
		    	case 2:r= "離行";break;
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
     * 解析设备离行、在行标志为中文
     * @param key 设备在行标志（1：在行2：离行）
     * @return 设备在行状态
     */
	
	public static String parseAwayFlag(int key){
		String r="";
		switch(key){
		    case 1:
		    	switch(language){
		    	case 1:r="在行自助服务区";break;
		    	case 2:r="在行自助服務區";break;
		    	case 3:r="Self-service areas in the bank";break;
		    	}break;
		    case 2:
		    	switch(language){
		    	case 1:r="单机离行自助服务点(自营)";break;
		    	case 2:r="單機離行自助服務點(自營)";break;
		    	case 3:r="Stand-alone self-service points from the line (self)";break;
		    	}break;
		    case 3:
		    	switch(language){
		    	case 1:r="离行自助银行(自营)-非银亭";break;
		    	case 2:r="離行自助銀行(自營)-非銀亭";break;
		    	case 3:r="Line from self-help bank (self) - Non-Bank Pavilion";break;
		    	}break;
		    case 4:
		    	switch(language){
		    	case 1:r="离行自助银行(自营)-银亭";break;
		    	case 2:r="離行自助銀行(自營)-銀亭";break;
		    	case 3:r="Line from self-help bank (self) - Silver Pavilion";break;
		    	}break;
		    case 5:
		    	switch(language){
		    	case 1:r="单机离行自助服务点(联营)";break;
		    	case 2:r="單機離行自助服務點(聯營)";break;
		    	case 3:r="Stand-alone self-service points from the line (joint venture)";break;
		    	}break;
		    case 6:
		    	switch(language){
		    	case 1:r="离行自助银行(联营)-非银亭";break;
		    	case 2:r="離行自助銀行(聯營)-非銀亭";break;
		    	case 3:r="From self-service banking firms (joint venture) - Non-Bank Pavilion";break;
		    	}break;
		    case 7:
		    	switch(language){
		    	case 1:r="离行自助银行(联营)-银亭";break;
		    	case 2:r="離行自助銀行(聯營)-銀亭";break;
		    	case 3:r="From self-service banking firms (joint venture) - Silver Pavilion";break;
		    	}break;
		    case 8:
		    	switch(language){
		    	case 1:r="单机在行自助服务点";break;
		    	case 2:r="單機在行自助服務點";break;
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
     * 解析设备经营方式为中文
     * @param key 设备经营方式（1：自营2：联营）
     * @return 设备经营方式
     */
	
	public static String parseWorkType(int key){
		String r="";
		switch(key){
		    case 1:
		    	switch(language){
		    	case 1: r= "自营";break;
		    	case 2: r= "自營";break;
		    	case 3: r= "self";break;
		    	}break;
		    case 2:
		    	switch(language){
		    	case 1:r="联营";break;
		    	case 2:r="聯營";break;
		    	case 3:r="Joint Venture";break;
		    	}break;
		    default:
		    	r="";
		}
		return r;
	}
	
	
	/**
     * 解析设备模块状态为中文
     * @param status 设备模块状态（null、UNKNOWN：未知,HEALTHY：正常,FATAL：故障,WARNING：警告）
     * @return 设备模块状态
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
        	case 1:r="正常";break;
        	case 2:r="正常";break;
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
	    	case 1:r="警告";break;
        	case 2:r="警告";break;
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
     * 解析设备具体模块状态为中文
     * @param status 设备模块状态（null、UNKNOWN：不存在,HEALTHY：正常,FATAL：故障,WARNING：警告）
     * @return 设备模块状态
     */
	
	public static String parseParticularModStatus(String status) {
		String r="";
        if(status == null){
        	switch(language){
        	case 1:r="不存在";break;
        	case 2:r="不存在";break;
        	case 3:r="Does not exist";break;
        	}
        }
        else if (status.equalsIgnoreCase("HEALTHY")){
        	switch(language){
        	case 1:r="正常";break;
        	case 2:r="正常";break;
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
	    	case 1:r="警告";break;
        	case 2:r="警告";break;
        	case 3:r="Warning";break;
	    	}
	    }
	    else if (status.equalsIgnoreCase("UNKNOWN")){
	    	switch(language){
        	case 1:r="不存在";break;
        	case 2:r="不存在";break;
        	case 3:r="Does not exist";break;
        	}
	    }
	    else{
	    	switch(language){
        	case 1:r="不存在";break;
        	case 2:r="不存在";break;
        	case 3:r="Does not exist";break;
        	}
	    }
        return r;
	}
	/**
     * 解析设备钱箱状态为中文
     * @param status 设备钱箱状态（null、UNKNOWN：未知，FULL：充足，LOW：不足，EMPTY：缺钞）
     * @return 设备钱箱状态
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
        	case 1:r="正常";break;
        	case 2:r="正常";break;
        	case 3:r="Normal";break;
        	}
	    }
        else if (status.equalsIgnoreCase("FULL")){
        	switch(language){
        	case 1:r="满钞";break;
        	case 2:r="滿鈔";break;
        	case 3:r="cashIn Full";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("LOW")){
	    	switch(language){
        	case 1:r="不足";break;
        	case 2:r="不足";break;
        	case 3:r="not enough";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("EMPTY")){
	    	switch(language){
        	case 1:r="缺钞";break;
        	case 2:r="缺鈔";break;
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
     * 解析设备运行状态为中文
     * @param status 设备运行状态（null、UNKNOWN：未知，HEALTHY：正常，CLOSE：关机，MAINTAIN：维护，PARTSERVICE：部分服务，NOSERVICE：停止服务，NETFATAL：P通讯故障）
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
        	case 1:r="正常";break;
        	case 2:r="正常";break;
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
        	case 1:r="关机";break;
        	case 2:r="關機";break;
        	case 3:r="shutdown";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("MAINTAIN")){
	    	switch(language){
        	case 1:r="维护";break;
        	case 2:r="維護";break;
        	case 3:r="maintenance";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("PARTSERVICE")){
	    	switch(language){
        	case 1:r="部分服务";break;
        	case 2:r="部分服務";break;
        	case 3:r="reducedservice";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("NOSERVICE")){
	    	switch(language){
        	case 1:r="停止服务";break;
        	case 2:r="停止服務";break;
        	case 3:r="Stop services";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("NETFATAL")){
	    	switch(language){
        	case 1:r="P通讯故障";break;
        	case 2:r="P通訊故障";break;
        	case 3:r="P communications failure";break;
        	}
	    }	  
	    else if (status.equalsIgnoreCase("STOP")){
	    	switch(language){
        	case 1:r="停用";break;
        	case 2:r="停機";break;
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
     * 解析设备运行状态为英文
     * @param actionType 设备运行状态（1：HEALTHY，2：PARTSERVICE，3：MAINTAIN，4：NETFATAL，5、7、8：NOSERVICE，6：CLOSE）
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
     * 解析设备网络状态为中文
     * @param status 设备网络状态(null、UNKNOWN：未知，HEALTHY：正常，FATAL：故障，WARNING：警告)
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
        	case 1:r="正常";break;
        	case 2:r="正常";break;
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
	    	case 1:r="警告";break;
        	case 2:r="警告";break;
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
     * 解析设备交易状态为中文
     * @param status 设备交易状态(null、UNKNOWN:未知 HEALTHY:正常 NOSERVICE:无现金交易)
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
        	case 1:r="正常";break;
        	case 2:r="正常";break;
        	case 3:r="Normal";break;
        	}
	    }
        else if (status.equalsIgnoreCase("WARNING")){
        	switch(language){
	    	case 1:r="警告";break;
        	case 2:r="警告";break;
        	case 3:r="Warning";break;
	    	}
	    }
	    else if (status.equalsIgnoreCase("NOSERVICE")){
	    	switch(language){
        	case 1:r="无交易";break;
        	case 2:r="無交易";break;
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
	 * 解析XFS故障级别
	 * 
	 * @param faultLevel XFS故障级别（0：正常，1：故障预警，2：普通故障，3：严重故障）
	 * @return	故障级别解释
	 */
	public static String  parseFaultLevel(int faultLevel){
		String levelDescription="";
		if(faultLevel==0){
			switch(language){
        	case 1:levelDescription="正常";break;
        	case 2:levelDescription="正常";break;
        	case 3:levelDescription="Normal";break;
        	}
		}else if(faultLevel==1){
			switch(language){
        	case 1:levelDescription="故障预警";break;
        	case 2:levelDescription="故障預警";break;
        	case 3:levelDescription="Predictive Failure";break;
        	}
		}else if(faultLevel==2){
			switch(language){
        	case 1:levelDescription="普通故障";break;
        	case 2:levelDescription="普通故障";break;
        	case 3:levelDescription="General fault";break;
        	}
		}else if(faultLevel==3){
			switch(language){
        	case 1:levelDescription="严重故障";break;
        	case 2:levelDescription="嚴重故障";break;
        	case 3:levelDescription="Serious problems";break;
        	}
		}
		return levelDescription;
	}
	
	/**
	 * 解析CASE当前状态为中文
	 * 
	 * @param iStatus case状态代码(1：已创建,2：已通知,3：通知升级,4：已确认,5：响应升级,6：已响应,7：故障升级,8:已挂起,9:已关闭)
	 * @return	case状态级别解释
	 */
	public static String  parseCurStatus(int iStatus){
		String statusName="";
		if(iStatus==1){
			switch(language){
        	case 1:statusName="已创建";break;
        	case 2:statusName="已創建";break;
        	case 3:statusName="Has been created";break;
        	}
		}else if(iStatus==2){
			switch(language){
        	case 1:statusName="已通知";break;
        	case 2:statusName="已通知";break;
        	case 3:statusName="Have been notified";break;
        	}
		}else if(iStatus==3){
			switch(language){
        	case 1:statusName="通知升级";break;
        	case 2:statusName="通知升級";break;
        	case 3:statusName="Upgrade notification";break;
        	}
		}else if(iStatus==4){
			switch(language){
        	case 1:statusName="已确认";break;
        	case 2:statusName="已確認";break;
        	case 3:statusName="Has confirmed";break;
        	}
		}else if(iStatus==5){
			switch(language){
        	case 1:statusName="响应升级";break;
        	case 2:statusName="響應升級";break;
        	case 3:statusName="Response Upgrade";break;
        	}
		}else if(iStatus==6){
			switch(language){
        	case 1:statusName="已响应";break;
        	case 2:statusName="已響應";break;
        	case 3:statusName="Have to respond to";break;
        	}
		}else if(iStatus==7){
			switch(language){
        	case 1:statusName="故障升级";break;
        	case 2:statusName="故障升級";break;
        	case 3:statusName="progression of failure";break;
        	}
		}else if(iStatus==8){
			switch(language){
        	case 1:statusName="已挂起";break;
        	case 2:statusName="已掛起";break;
        	case 3:statusName="Has been suspended";break;
        	}
		}else if(iStatus==9){
			switch(language){
        	case 1:statusName="已关闭";break;
        	case 2:statusName="已關閉";break;
        	case 3:statusName="Closed";break;
        	}		
		}
		return statusName;
	}
	
	/**
	 * 解析CASE当前状态为标志
	 * @param statusName case状态名称(已创建：1,已通知：2，通知升级：3,已确认：4,响应升级：5,已响应：6,故障升级：7,已挂起：8,已关闭：9)
	 * @return	case状态级别解释
	 */
	public static int  parseCurStatusName(String statusName){
		int iStatus=0;
		if(statusName.equalsIgnoreCase("已创建")){
			iStatus=1;
		}else if(statusName.equalsIgnoreCase("已通知")){
			iStatus=2;
		}else if(statusName.equalsIgnoreCase("通知升级")){
			iStatus=3;
		}else if(statusName.equalsIgnoreCase("已确认")){
			iStatus=4;
		}else if(statusName.equalsIgnoreCase("响应升级")){
			iStatus=5;
		}else if(statusName.equalsIgnoreCase("已响应")){
			iStatus=6;
		}else if(statusName.equalsIgnoreCase("故障升级")){
			iStatus=7;
		}else if(statusName.equalsIgnoreCase("已挂起")){
			iStatus=8;
		}else if(statusName.equalsIgnoreCase("已关闭")){
			iStatus=9;
		}
		return iStatus;
	}
	
	/**
	 * @param cmdid - 命令代码
	 * @return cmdName - 命令解释
	 */
	/*public static String  parseCmd(String cmdid){
		String cmdName="";
		if(cmdid.equalsIgnoreCase("query")){
			cmdName="综合查询";
		}else if(cmdid.equalsIgnoreCase("create")){
			cmdName="人工创建";
		}else if(cmdid.equalsIgnoreCase("close")){
			cmdName="人工关闭";
		}else if(cmdid.equalsIgnoreCase("delete")){
			cmdName="删除";
		}else if(cmdid.equalsIgnoreCase("record")){
			cmdName="补录";
		}else if(cmdid.equalsIgnoreCase("notify")){
			cmdName="通知查询";
		}else if(cmdid.equalsIgnoreCase("trace")){
			cmdName="跟踪查询";
		}else if(cmdid.equalsIgnoreCase("fault")){
			cmdName="故障报告查询";
		}
		
		return cmdName;
	}*/
	
	/**
	 * 将CASE生成方式解释为中文
	 * @param type CASE生成方式(M:人工,A:自动)
	 * @return CASE生成方式
	 */
	public static String parseCreateType(String type){
		
		String typeName="";
		if(type.equalsIgnoreCase("M")){
			switch(language){
        	case 1:typeName="人工";break;
        	case 2:typeName="人工";break;
        	case 3:typeName="Artificial";break;
        	}
		}else if(type.equalsIgnoreCase("A")){
			switch(language){
        	case 1:typeName="自动";break;
        	case 2:typeName="自動";break;
        	case 3:typeName="Automatic";break;
        	}
		}
		
		return typeName;
	}
	
	/**
	 * 将钱箱类型转换为中文解释
	 * @param type 
	 * @return 钱箱类型
	 */
	public static String parseCimCdmCuType(String type){
		
		String typeName=type;
		if(type.equalsIgnoreCase("RejectCassette")){
			switch(language){
        	case 1:typeName="拒钞箱";break;
        	case 2:typeName="拒鈔箱";break;
        	case 3:typeName="The box of refused to money ";break;
        	}
		}else if(type.equalsIgnoreCase("RetractCassette")){
			switch(language){
        	case 1:typeName="回收箱";break;
        	case 2:typeName="回收箱";break;
        	case 3:typeName="recycling bin";break;
        	}
		}else if(type.equalsIgnoreCase("Recycling")){
			switch(language){
        	case 1:typeName="循环箱";break;
        	case 2:typeName="循環箱";break;
        	case 3:typeName="Recycling box";break;
        	}
		}else if(type.equalsIgnoreCase("BillCassette")){
			switch(language){
        	case 1:typeName="取款箱";break;
        	case 2:typeName="取款箱";break;
        	case 3:typeName="Cash box";break;
        	}
		}else if(type.equalsIgnoreCase("CashIn")){
			switch(language){
        	case 1:typeName="存款箱";break;
        	case 2:typeName="存款箱";break;
        	case 3:typeName="Deposit box";break;
        	}
		}else if(type.equalsIgnoreCase("NOTAPPLICABLE")){
			switch(language){
        	case 1:typeName="未使用";break;
        	case 2:typeName="未使用";break;
        	case 3:typeName="Unused";break;
        	}
		}
		return typeName;
	}
	
	/**
	 * 将钱箱类型转换为中文解释
	 * @param type 
	 * @return 钱箱类型
	 */
	public static String parseCurrencyType(String type){
		if(type.indexOf(",") >= 0)
		{
			type = type.split(",",-1)[0];
		}
		String typeName=type;
		if(type.equalsIgnoreCase("CNY")||type.equalsIgnoreCase("RMB")){
			switch(language){
        	case 1:typeName="人民币";break;
        	case 2:typeName="人民幣";break;
        	case 3:typeName="Chinese Yuan Renminbi";break;
        	}
		}else if(type.equalsIgnoreCase("HKD")){
			switch(language){
        	case 1:typeName="港币";break;
        	case 2:typeName="港幣";break;
        	case 3:typeName="HKD";break;
        	}
		}else if(type.equalsIgnoreCase("USD")){
			switch(language){
        	case 1:typeName="美元";break;
        	case 2:typeName="美元";break;
        	case 3:typeName="U.S. Dollar";break;
        	}
		}
		return typeName;
	}
	
	/**
	 * 将钱箱状态转换为中文解释
	 * @param type 
	 * @return 钱箱状态
	 */
	public static String parseCuStatus(String type){
		
		String typeName=type;
		if(type.equalsIgnoreCase("OK")||type.equalsIgnoreCase("HEALTHY")){
			switch(language){
        	case 1:typeName="正常";break;
        	case 2:typeName="正常";break;
        	case 3:typeName="Normal";break;
        	}
		}else if(type.equalsIgnoreCase("FULL")){
			switch(language){
        	case 1:typeName="满";break;
        	case 2:typeName="滿";break;
        	case 3:typeName="Full";break;
        	}
		}else if(type.equalsIgnoreCase("HIGH")){
			switch(language){
        	case 1:typeName="高";break;
        	case 2:typeName="高";break;
        	case 3:typeName="High";break;
        	}
		}else if(type.equalsIgnoreCase("LOW")){
			switch(language){
        	case 1:typeName="低";break;
        	case 2:typeName="低";break;
        	case 3:typeName="Low";break;
        	}
		}else if(type.equalsIgnoreCase("EMPTY")){
			switch(language){
        	case 1:typeName="空";break;
        	case 2:typeName="空";break;
        	case 3:typeName="Empty";break;
        	}
		}else if(type.equalsIgnoreCase("MISSING")){
			switch(language){
        	case 1:typeName="无";break;
        	case 2:typeName="無";break;
        	case 3:typeName="Without";break;
        	}
		}else if(type.equalsIgnoreCase("Manipulated")){
			switch(language){
        	case 1:typeName="操作";break;
        	case 2:typeName="操作";break;
        	case 3:typeName="Operation";break;
        	}
		}else if(type.equalsIgnoreCase("Inoperative")){
			switch(language){
        	case 1:typeName="不可用";break;
        	case 2:typeName="不可用";break;
        	case 3:typeName="Not available";break;
        	}
		}			
		 
		return typeName;
	}
	
	/**
	 * 将交易类型转换为中文解释
	 * @param type 
	 * @return 交易类型
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
	        	case 1:r="取款";break;
	        	case 2:r="取款";break;
	        	case 3:r="withdraw";break;
        	}
		} else if(type.equalsIgnoreCase("DET")){
			switch(language){
	        	case 1:r="存款";break;
	        	case 2:r="存款";break;
	        	case 3:r="Deposits";break;
        	}
		} else if(type.equalsIgnoreCase("INQ")){
			switch(language){
	        	case 1:r="查询";break;
	        	case 2:r="查詢";break;
	        	case 3:r="Inquiry";break;
        	}
		} else if(type.equalsIgnoreCase("TFR")){
				switch(language){
		        	case 1:r="转帐";break;
		        	case 2:r="轉帳";break;
		        	case 3:r="Transfer";break;
	        	}
		} else if(type.equalsIgnoreCase("PIN")){
				switch(language){
		        	case 1:r="改密";break;
		        	case 2:r="改密";break;
		        	case 3:r="Change density";break;
	        	}
		} else if(type.equalsIgnoreCase("OTH")){
				switch(language){
		        	case 1:r="其他";break;
		        	case 2:r="其他";break;
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
	 * 将交易日期转换为中文解释
	 * @param time 
	 * @return 交易日期
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
			return "未知";
		}
		return r;
	}
	
	/**
     * 获取设备交易状态颜色
     * @param status 设备交易状态(null、UNKNOWN:未知 HEALTHY:正常 NOSERVICE:无现金交易)
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
     * 设备运行状态颜色
     * @param status 设备运行状态（null、UNKNOWN：未知，HEALTHY：正常，CLOSE：关机，MAINTAIN：维护，PARTSERVICE：部分服务，NOSERVICE：停止服务，NETFATAL：P通讯故障）
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
     * 设备模块状态颜色
     * @param status 设备模块状态（null、UNKNOWN：未知,HEALTHY：正常,FATAL：故障,WARNING：警告）
     * @return 设备模块状态
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
     * 设备钱箱状态颜色
     * @param status 设备钱箱状态（null、UNKNOWN：未知，FULL：充足，LOW：不足，EMPTY：缺钞）
     * @return 设备钱箱状态
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
     * 设备网络状态颜色
     * @param status 设备网络状态(null、UNKNOWN：未知，HEALTHY：正常，FATAL：故障，WARNING：警告)
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
     * 设备模块状态颜色
     * @param status 设备模块状态（null、UNKNOWN：未知,HEALTHY：正常,FATAL：故障,WARNING：警告）
     * @return 设备模块状态
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
	 * 计算无交易时间
	 * @param time 
	 * @return 无交易的时间
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
	 * 将设备状态表中的设备状态转换为设备信息表中的设备状态
	 * @param status 
	 * @return 设备信息表中的设备状态
	 * 4－正常
	 * 5－警告
	 * 6－故障
	 * 7－关机
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
    			return 7;//关机
    		}
    		else if (modstatus.equalsIgnoreCase("HEALTHY")){
    			return 4;//正常
    		}
    		else if (modstatus.equalsIgnoreCase("WARNING")){
    			return 5;//警告
    		}
    		else if (modstatus.equalsIgnoreCase("FATAL")){
    			return 6;//故障
    		}
    		else if (modstatus.equalsIgnoreCase("UNKNOWN")){
    			return 7;//关机
    		}
    		else{
    			return 7;//关机
    		}
    	}
    }
	
	/**
     * 设备监控列表方式  显示列为可配置项
     * @param status 设备监控列表方式  显示列（）
     * @return 设备监控列表方式  显示列中文
     */
	
	public static String parseRowSelectMonitor(String status) {
		String r="";
        if(status == null){
        	 return "";
        }
		else if (status.equals("devTxStatus")){
			return "交易状态";
	    } 
        else if (status.equalsIgnoreCase("devRunStatus")){
        	switch(language){
        	case 1:r= "运行状态";break;
        	case 2:r= "運行狀態";break;
        	case 3:r= "Run State";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("devModStatus")){
	    	switch(language){
        	case 1:r= "模块状态";break;
        	case 2:r= "模組狀態";break;
        	case 3:r= "Module State";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("devCashboxStatus")){
	    	switch(language){
        	case 1:r= "钞箱状态";break;
        	case 2:r= "鈔箱狀態";break;
        	case 3:r= "Box State";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("devNetStatus")){
	    	switch(language){
        	case 1:r= "网络状态";break;
        	case 2:r= "網絡狀態";break;
        	case 3:r= "Network Status";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("idcCaptureBinCount")){
	    	switch(language){
        	case 1:r= "读卡器吞卡数量";break;
        	case 2:r= "讀卡器吞卡數量";break;
        	case 3:r= "The number of readers retain card";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("devType")){
	    	switch(language){
        	case 1:r= "设备型号";break;
        	case 2:r= "設備型號";break;
        	case 3:r= "Equipment Model";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("orgNo")){
	    	switch(language){
        	case 1:r= "所属机构";break;
        	case 2:r= "所屬機構";break;
        	case 3:r= "subsidiary organ";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("address")){
	    	switch(language){
        	case 1:r= "装机地址";break;
        	case 2:r= "裝機地址";break;
        	case 3:r= "Installation address";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("ip")){
	    	switch(language){
        	case 1:r= "IP地址";break;
        	case 2:r= "IP地址";break;
        	case 3:r= "IP Address";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("awayFlag")){
	    	switch(language){
        	case 1:r= "在行/离行";break;
        	case 2:r= "在行/離行";break;
        	case 3:r= "In the banking / not in the bank";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("workType")){
	    	switch(language){
        	case 1:r= "经营方式";break;
        	case 2:r= "經營方式";break;
        	case 3:r= "Mode of operation";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("case")){
	    	switch(language){
        	case 1:r= "查询case";break;
        	case 2:r= "查詢case";break;
        	case 3:r= "Query case";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("fault")){
	    	switch(language){
        	case 1:r= "当前故障";break;
        	case 2:r= "當前故障";break;
        	case 3:r= "Fault current";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("control")){
	    	switch(language){
        	case 1:r= "远程控制";break;
        	case 2:r= "遠程控制";break;
        	case 3:r= "Remote Control";break;
        	}
			return r;
	    }
	    else if (status.equalsIgnoreCase("cashSum")){
	    	switch(language){
        	case 1:r= "钞箱汇总";break;
        	case 2:r= "鈔箱匯總";break;
        	case 3:r= "Cash Sum";break;
        	}
			return r;
	    }
	    else{
		    return "";
	    }
	}
	
//	/**
//     * 设备监控列表方式  显示列为可配置项
//     * @param status 设备监控列表方式  显示列（）
//     * @return 设备监控列表方式  显示列中文
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
     * 多语言版翻译case跟踪
     * @param status case跟踪信息 
     * @return 
     */
	
	public static String translateCaseTraceInfo(String status) {
		String r = status;
        if(status == null){
        	 return "";
        }
        else if (status.equalsIgnoreCase("CASE创建")){
        	switch(language){
        	case 1:r= "CASE创建";break;
        	case 2:r= "CASE創建";break;
        	case 3:r= "CASE create";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("CASE人工创建")){
	    	switch(language){
        	case 1:r= "CASE人工创建";break;
        	case 2:r= "CASE人工創建";break;
        	case 3:r= "CASE artificial creation";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("CASE自动创建")){
	    	switch(language){
        	case 1:r= "CASE自动创建";break;
        	case 2:r= "CASE自動創建";break;
        	case 3:r= "CASE automatically create";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("创建CASE通知失败")){
	    	switch(language){
        	case 1:r= "创建CASE通知失败";break;
        	case 2:r= "創建CASE通知失敗";break;
        	case 3:r= "Failure to inform the creation of CASE";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("由于CASE类型没有定义，创建通知失败")){
	    	switch(language){
        	case 1:r= "由于CASE类型没有定义，创建通知失败";break;
        	case 2:r= "由於CASE類型沒有定義，創建通知失敗";break;
        	case 3:r= "CASE absence of such a definition type, create a notification failure";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("由于负责人类型定义错误，创建通知失败")){
	    	switch(language){
        	case 1:r= "由于负责人类型定义错误，创建通知失败";break;
        	case 2:r= "由於負責人類型定義錯誤，創建通知失敗";break;
        	case 3:r= "Due to the definition of responsible persons of the type of error, failure to inform the creation of";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("由于无设备责任人，创建通知失败")){
	    	switch(language){
        	case 1:r= "由于无设备责任人，创建通知失败";break;
        	case 2:r= "由於無設備責任人，創建通知失敗";break;
        	case 3:r= "Equipment as a result of non-responsibility, and create a notification failure";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("传入参数不合法")){
	    	switch(language){
        	case 1:r= "传入参数不合法";break;
        	case 2:r= "傳入參數不合法";break;
        	case 3:r= "Parameters into illegal";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("创建CASE通知成功")){
	    	switch(language){
        	case 1:r= "创建CASE通知成功";break;
        	case 2:r= "創建CASE通知成功";break;
        	case 3:r= "Notice of the success of the creation of CASE";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("创建CASE通知失败")){
	    	switch(language){
        	case 1:r= "创建CASE通知失败";break;
        	case 2:r= "創建CASE通知失敗";break;
        	case 3:r= "Failure to inform the creation of CASE";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("由于传入CASE对象为null，组织通知内容失败")){
	    	switch(language){
        	case 1:r= "由于传入CASE对象为null，组织通知内容失败";break;
        	case 2:r= "由於傳入CASE對象為null，組織通知內容失敗";break;
        	case 3:r= "Since the introduction of CASE for null, the failure of organizations to inform the content";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("由于模板中无该类通知，取程序默认模板")){
	    	switch(language){
        	case 1:r= "由于模板中无该类通知，取程序默认模板";break;
        	case 2:r= "由於範本中無該類通知，取程式默認範本";break;
        	case 3:r= "Because no such notification template, the default template check procedures";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("CASE关闭成功,关闭人员:")){
	    	switch(language){
        	case 1:r= "CASE关闭成功,关闭人员:";break;
        	case 2:r= "CASE關閉成功,關閉人員:";break;
        	case 3:r= "CASE closed successful, closed personnel:";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("CASE关闭成功")){
	    	switch(language){
        	case 1:r= "CASE关闭成功";break;
        	case 2:r= "CASE關閉成功";break;
        	case 3:r= "CASE closed successfully";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("创建case关闭通知失败")){
	    	switch(language){
        	case 1:r= "创建case关闭通知失败";break;
        	case 2:r= "創建case關閉通知失敗";break;
        	case 3:r= "Failure to inform the creation of case closure";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("到场响应")){
	    	switch(language){
        	case 1:r= "到场响应";break;
        	case 2:r= "到場回應";break;
        	case 3:r= "The scene in response";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("CASE确认")){
	    	switch(language){
        	case 1:r= "CASE确认";break;
        	case 2:r= "CASE確認";break;
        	case 3:r= "CASE confirmed";break;
        	}
	    }
	    else if (status.equalsIgnoreCase("故障升级")){
	    	switch(language){
        	case 1:r= "故障升级";break;
        	case 2:r= "故障升級";break;
        	case 3:r= "Failure to upgrade";break;
        	}
	    }
        return r;
	}
	
	
	public static void main(String[] args){	

	   
	}	

}