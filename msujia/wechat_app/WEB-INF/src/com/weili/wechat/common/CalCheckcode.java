package com.weili.wechat.common;


public class CalCheckcode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String tagStr = "1020231010011L19001";
		
		StringBuffer retInfo = new StringBuffer();
		System.out.println(calCheckCode(tagStr,retInfo));			
	}

	/**
	 * 
	 * @param tagStr  19位条码
	 * @param retInfo 返回错误信息
	 * @return 1位校验码
	 */
	public static String calCheckCode(String tagStr,StringBuffer retInfo){
		//解析条码编号
		try{
			
			if(tagStr==null || tagStr.length()!=19){
				retInfo.append("条码校验长度不正确，应为19位，实为["+tagStr+"]");
				return null;
			}
			
			final int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1, 6 };
			final int[] vi = { 1, 0, 9, 8, 7, 6, 5, 4, 3, 2 };
			
			int remaining = 0;
			
			if (tagStr.length() == 19) {
				int sum = 0;
				int[] ai = new int[19];
				for (int i = 0; i < 19; i++) {
					String k = tagStr.substring(i, i + 1);
					k = convertMonth(k);
					ai[i] = Integer.parseInt(k);
				}
				for (int i = 0; i < 19; i++) {
					sum = sum + wi[i] * ai[i];
				}
				//System.out.println("sum=="+sum);
				remaining = sum % 10;
			}

			return String.valueOf(vi[remaining]);
		}catch(Exception e){
			retInfo.append("计算条码校验码异常！异常信息："+e.getMessage());
			return null;
		}
		
	}
	
	public static String convertMonth(String batchNo){
		
		String batchNum = batchNo;
		
		if(batchNo.equals("A")){
			batchNum = "1";
		}else if(batchNo.equals("B")){
			batchNum = "2";
		}else if(batchNo.equals("C")){
			batchNum = "3";
		}else if(batchNo.equals("D")){
			batchNum = "4";
		}else if(batchNo.equals("E")){
			batchNum = "5";
		}else if(batchNo.equals("F")){
			batchNum = "6";
		}else if(batchNo.equals("G")){
			batchNum = "7";
		}else if(batchNo.equals("H")){
			batchNum = "8";
		}else if(batchNo.equals("I")){
			batchNum = "9";
		}else if(batchNo.equals("J")){
			batchNum = "10";
		}else if(batchNo.equals("K")){
			batchNum = "11";
		}else if(batchNo.equals("L")){
			batchNum = "12";
		}
		
		return batchNum;
	}
	
	
}
