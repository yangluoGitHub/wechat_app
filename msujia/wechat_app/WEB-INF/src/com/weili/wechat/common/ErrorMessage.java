package com.weili.wechat.common;

public  class ErrorMessage {
    
	public static String getReportErrorMessage(int no,Resource resource) {
		switch(no) {
             case 1 : return  resource.srcStr("system.cashTraffic");
             case 2 : return  resource.srcStr("error.dayFWRate");
             case 3 : return  resource.srcStr("error.monthFWRate");
             case 4 : return  resource.srcStr("error.yearFWRate");
             case 5 : return  resource.srcStr("error.dayGzRate");
             case 6 : return  resource.srcStr("error.monthGzRate");
             case 7 : return  resource.srcStr("error.yearGzRate");
             case 8 : return  resource.srcStr("error.dayOpenRate");
             case 9 : return  resource.srcStr("error.monthOpenRate");
             case 10 : return resource.srcStr("error.yearOpenRate");
             case 11 : return resource.srcStr("error.ServiceCompanykh");
             case 12 : return resource.srcStr("error.shortDayPaperMoney");
             case 13 : return resource.srcStr("error.shortMonthPaperMoney");
             case 14 : return resource.srcStr("error.shortYearPaperMoney");
             case 15 : return resource.srcStr("error.AdminInverstigateKh");
             case 16 : return resource.srcStr("error.casePromoteCmmdErr");
             case 19 : return resource.srcStr("error.fixclearFileErr");
             case 20 : return resource.srcStr("error.createReport");
             default :return resource.srcStr("error.reportTjErr");
          }
    }
	
}
