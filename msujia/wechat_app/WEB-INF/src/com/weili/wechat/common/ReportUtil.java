package com.weili.wechat.common;

/**
 * ������
 * 
 * @author yyc
 * 
 */
public class ReportUtil {

	/**
	 * ��ĿĿ¼�µı�����ظ�Ŀ¼
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
	 * ����ģ���ļ���
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
	 * ��ȡ��ĿĿ¼�µı�����ظ�Ŀ¼(ȫ·��)
	 * 
	 * @return
	 */
	public static String getReportDir() {
		return reportDir;
	}

	/**
	 * ��ȡ��ĿĿ¼�µ���ʱ����Ŀ¼
	 * 
	 * @return
	 */
	public static String getReportTempDir() {
		return reportDir + FileUtil.sysFileSeparator + "temp";
	}

	/**
	 * ��֯��������_ͨ��
	 * 
	 * @param simpleName
	 * @param ccOrSsName
	 *            �������or����վ����
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
	 * ��֯��������_��־
	 * 
	 * @param simpleName
	 * @param ccOrSsName
	 *            �������or����վ����
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
	 * ��֯��������_�Ϻ����з��ú���
	 * 
	 * @param simpleName
	 * @param ccOrSsName
	 *            �������or����վ����
	 * @param dateStr
	 * @param suffix
	 * @return
	 */
	public static String mkReportNameSHICBCChr(String simpleName, String ccOrSsName, String dateStr, String suffix) {
		StringBuffer reportName = new StringBuffer();
		reportName.append(ccOrSsName);
		reportName.append((simpleName == null || simpleName.equals("") ? "���ú���" : simpleName));
		reportName.append("_");
		reportName.append(dateStr);
		reportName.append(".");
		reportName.append((suffix == null || suffix.equals("")) ? "xls" : suffix);
		return reportName.toString();
	}

}
