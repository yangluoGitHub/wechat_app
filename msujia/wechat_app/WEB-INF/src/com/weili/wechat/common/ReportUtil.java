package com.weili.wechat.common;

/**
 * 处理报表
 * 
 * @author yyc
 * 
 */
public class ReportUtil {

	/**
	 * 项目目录下的报表相关根目录
	 */
	private static String reportDir;
	static {
		String fileName[] = ReportUtil.class.getResource("").toString().split("/");
		String file = "";
		String filePath = "";
		for (int i = 0; i < fileName.length - 6; i++) {
			file = fileName[i].replace("%20", " ");
			if (file != null) {
				filePath = filePath + file + FileUtil.sysFileSeparator;
			}
		}
		reportDir = filePath.substring(6) + "report";
	}

	/**
	 * 报表模板文件名
	 * 
	 * @author yyc
	 * 
	 */
	public enum ReportTemplateFile {
		DEV_FAULT_MAINTAIN_REPORT("devFaultMaintainReport.xls"), DEV_FAULT_MAINTAIN_PRI_REPORT("devFaultMaintainPriReport.xls"), DEV_URGENT_ADDNOTES_REPORT(
				"devUrgentAddnotesReport.xls"), DEV_LIST_REPORT("devListReport.xls"), DAILY_REPORT("dailyReport.xls"), DAILY_LOG_REPORT_AC1(
				"dailyLogReportAC1.xls"), DAILY_LOG_REPORT_AC2("dailyLogReportAC2.xls"), DAILY_LOG_REPORT_M("dailyLogReportM.xls"), DAILY_LOG_REPORT_C(
				"dailyLogReportC.xls"), MONTHLY_REPORT("monthlyReport.xls"), SH_ICBC_CHARGE_BANK("shICBCChargeBank.xls"), SH_ICBC_CHARGE_ESCORT(
				"shICBCChargeEscort.xls");

		private String name;
		private String dir;
		private String path;

		private ReportTemplateFile(String name) {
			this.name = name;
			this.dir = reportDir + FileUtil.sysFileSeparator + "template";
			this.path = this.dir + FileUtil.sysFileSeparator + this.name;
		}

		public String getName() {
			return name;
		}

		public String getDir() {
			return dir;
		}

		public String getPath() {
			return path;
		}
	}

	/**
	 * 获取项目目录下的报表相关根目录(全路径)
	 * 
	 * @return
	 */
	public static String getReportDir() {
		return reportDir;
	}

	/**
	 * 获取项目目录下的临时报表目录
	 * 
	 * @return
	 */
	public static String getReportTempDir() {
		return reportDir + FileUtil.sysFileSeparator + "temp";
	}

	/**
	 * 组织报表名称_通用
	 * 
	 * @param simpleName
	 * @param ccOrSsName
	 *            清机中心or服务站名称
	 * @param dateStr
	 * @param opName
	 * @param suffix
	 * @return
	 */
	public static String mkReportNameComm(String simpleName, String ccOrSsName, String dateStr, String opName, String suffix) {
		StringBuffer reportName = new StringBuffer();
		reportName.append(ccOrSsName);
		reportName.append("_");
		reportName.append(simpleName);
		reportName.append("_");
		reportName.append(dateStr);
		if (opName != null && !opName.equals("")) {
			reportName.append("_BY_");
			reportName.append(opName);
		}
		reportName.append(".");
		reportName.append((suffix == null || suffix.equals("")) ? "xls" : suffix);
		return reportName.toString();
	}

	/**
	 * 组织报表名称_日志
	 * 
	 * @param simpleName
	 * @param ccOrSsName
	 *            清机中心or服务站名称
	 * @param dateStr
	 * @param opName
	 * @param suffix
	 * @return
	 */
	public static String mkReportNameLog(String simpleName, String ccOrSsName, String dateStr, String opName, String suffix) {
		StringBuffer reportName = new StringBuffer();
		reportName.append(dateStr);
		reportName.append("_");
		reportName.append(ccOrSsName);
		reportName.append("_");
		reportName.append(simpleName);
		reportName.append("_");
		reportName.append(opName);
		reportName.append(".");
		reportName.append((suffix == null || suffix.equals("")) ? "xls" : suffix);
		return reportName.toString();
	}

	/**
	 * 组织报表名称_上海工行费用核算
	 * 
	 * @param simpleName
	 * @param ccOrSsName
	 *            清机中心or服务站名称
	 * @param dateStr
	 * @param suffix
	 * @return
	 */
	public static String mkReportNameSHICBCChr(String simpleName, String ccOrSsName, String dateStr, String suffix) {
		StringBuffer reportName = new StringBuffer();
		reportName.append(ccOrSsName);
		reportName.append((simpleName == null || simpleName.equals("") ? "费用核算" : simpleName));
		reportName.append("_");
		reportName.append(dateStr);
		reportName.append(".");
		reportName.append((suffix == null || suffix.equals("")) ? "xls" : suffix);
		return reportName.toString();
	}

}
