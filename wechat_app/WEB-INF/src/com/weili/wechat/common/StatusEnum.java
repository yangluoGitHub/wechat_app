package com.weili.wechat.common;

public class StatusEnum {
	
	/**
	 * 多媒体资源类型
	 * @author zxgao
	 *
	 */
	public enum MediaType{
		IMAGE("image","'jpg','jpeg','png','gif','bmp'"),VOICE("voice","'mp3','amr'"),VIDEO("video","'mp4','avi'");
		
		private String type;
		private String suffix;
		
		private MediaType(String type,String suffix){
			this.type = type;
			this.suffix = suffix;
		}
		
		public String getType(){
			return this.type;
		}
		
		public String getSuffix(){
			return this.suffix;
		}
	}
	
	/**
	 * 消息类型
	 * @author zxgao
	 *
	 */
	public enum MessageType{
		TEXT("text"), IMAGE("image"), VOICE("voice"), VIDEO("video"), LOCATION("location"), LINK("link"), MUSIC("music"), NEWS("news");
		private String type;
		
		private MessageType(String type){
			this.type = type;
		}
		
		public String getType(){
			return this.type;
		}
	}
	
	
	// 通用操作类型，新增，修改，删除
	public enum OpType {
		ADD(1), MOD(2), DEL(3);
		private int i;

		private OpType(int i) {
			this.i = i;
		}

		public int getI() {
			return this.i;
		}
	}

	// 任务单种类：加钞/维护
	public enum TaskType {
		DISPATCH(1, "加钞"), MAINTAIN(2, "维护");
		private int i;
		private String name;

		private TaskType(int i, String name) {
			this.i = i;
			this.name = name;
		}

		public int getI() {
			return this.i;
		}

		public String getName() {
			return name;
		}

		public static TaskType reverse(int i) {
			for (TaskType tt : TaskType.values()) {
				if (tt.getI() == i) {
					return tt;
				}
			}
			return null;
		}
	}

	// 设备加钞清机表是否数据完整，参与统计
	public enum Addclear_STFLAG {
		NO(0), YES(1);
		private int i;

		private Addclear_STFLAG(int i) {
			this.i = i;
		}

		public int getI() {
			return this.i;
		}
	}

	/**
	 * 地图：服务站覆盖物点击代码
	 * 
	 * @author yyc
	 * 
	 */
	public enum Map_StationMarkerClickCode {
		INFO_WIN(0, "打开信息窗口"), OP_POINT(1, "人员监控"), DIS_POINT(2, "加钞监控"), MT_POINT(3, "维护监控");
		private int code;
		private String asStr;

		private Map_StationMarkerClickCode(int code, String asStr) {
			this.code = code;
			this.asStr = asStr;
		}

		public int getCode() {
			return this.code;
		}

		public String getAsStr() {
			return asStr;
		}

		public static String getAsStrByCode(int code) {
			for (Map_StationMarkerClickCode temp : Map_StationMarkerClickCode.values()) {
				if (temp.getCode() == code) {
					return temp.getAsStr();
				}
			}
			return "";
		}
	}

	public enum DispatchType {
		ADD, MOD, DEL
	};

	/**
	 * 网点类型
	 * 
	 * @author yyc
	 * 
	 */
	public enum NetpointType {
		ONE_OF_ONE(1), MORE_OF_ONE(2), SELF_HELP_BANK(3), METRO(4);
		private int value;

		private NetpointType(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	/**
	 * 是否为终端服务站
	 * 
	 * @author yyc
	 * 
	 */
	public enum SrvStationIsTerminal {
		YES(1), NO(0);
		private int value;

		private SrvStationIsTerminal(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	/**
	 * 设备维护变更类型
	 * 
	 * @author yyc
	 * 
	 */
	public enum DevSrvChgType {
		STOCK(0), START(1), STOP(2), PAUSE(3);
		private int value;

		private DevSrvChgType(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	/**
	 * 经纬度范围(在保证范围不减小的前提下取一位小数)
	 * 
	 * @author yyc
	 * 
	 */
	public enum XYArea {
		CHINA_EAST(135.1, 15044571.3), CHINA_WEST(73.5, 4649808.4), CHINA_NORTH(53.5, 7058289.7), CHINA_SOUTH(3.8, 391988.9);

		private double latLng; // 标准经纬度
		private double gaussProj; // 高斯投影

		private XYArea(double latLng, double gaussianValue) {
			this.latLng = latLng;
		}

		public double getLatLng() {
			return latLng;
		}

		public double getGaussProj() {
			return gaussProj;
		}

		/**
		 * 判断是否在中国境内
		 * 
		 * @param x
		 *            经度
		 * @param y
		 *            纬度
		 * @return
		 */
		public static boolean inChina(Double x, Double y) {
			if (inChina4GaussProj(x, y) || inChina4LatLng(x, y)) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * 判断是否在中国境内_标准经纬度
		 * 
		 * @param x
		 * @param y
		 * @return
		 */
		public static boolean inChina4GaussProj(Double x, Double y) {
			if (x >= CHINA_WEST.getGaussProj() && x <= CHINA_EAST.getGaussProj() && y >= CHINA_SOUTH.getGaussProj()
					&& y <= CHINA_NORTH.getGaussProj()) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * 判断是否在中国境内_高斯投影
		 * 
		 * @param x
		 * @param y
		 * @return
		 */
		public static boolean inChina4LatLng(Double x, Double y) {
			if (x >= CHINA_WEST.getLatLng() && x <= CHINA_EAST.getLatLng() && y >= CHINA_SOUTH.getLatLng() && y <= CHINA_NORTH.getLatLng()) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 矩阵-类型
	 * 
	 * @author yyc
	 * 
	 */
	public enum MatrixType {
		ALL(-1), N_TO_N(0), C_TO_N(1), N_TO_C(2);
		private int value;

		private MatrixType(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}

		public static boolean checkIn(int value) {
			for (MatrixType temp : MatrixType.values()) {
				if (temp.getValue() == value) {
					return true;
				}
			}
			return false;
		}

		public static boolean checkDefinite(int value) {
			return MatrixType.checkIn(value) && value != MatrixType.ALL.getValue();
		}
	}

	/**
	 * 矩阵-策略
	 * 
	 * @author yyc
	 * 
	 */
	public enum MatrixTactic {
		ALL(-1), DISTANCE_SHORTEST(0), TIME_SHORTEST(1), NO_EXPRESS(2);
		private int value;

		private MatrixTactic(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}

		public static boolean checkIn(int value) {
			for (MatrixTactic temp : MatrixTactic.values()) {
				if (temp.getValue() == value) {
					return true;
				}
			}
			return false;
		}

		public static boolean checkDefinite(int value) {
			return MatrixTactic.checkIn(value) && value != MatrixTactic.ALL.getValue();
		}
	}

	/**
	 * 矩阵-点类型
	 * 
	 * @author yyc
	 * 
	 */
	public enum MatrixPointType {
		CENTER(0), NETPOINT(1);
		private int value;

		private MatrixPointType(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}

		public static boolean checkIn(int value) {
			for (MatrixPointType temp : MatrixPointType.values()) {
				if (temp.getValue() == value) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 银行机构等级
	 * 
	 * @author yyc
	 * 
	 */
	public enum OrgGrade {
		HEAD_BANK(1, "总行"), BRANCH_BANK(2, "分行"), SUB_BRANCH_BANK(3, "支行"), NETPOINT(4, "网点");

		private int no;
		private String name;

		private OrgGrade(int no, String name) {
			this.no = no;
			this.name = name;
		}

		public int getNo() {
			return this.no;
		}

		public String getName() {
			return this.name;
		}
	}

	/**
	 * 加钞计划状态
	 * 
	 * @author hongwei
	 * @since 2013-1-4
	 * mod by jfjiao since 2013-8-1
	 */
	public enum AddnotesPlanStatus {
		DEV_CHOOSE(1, "已选择设备"), AMOUNT_PREDICTED(2, "已预测金额"), GROUPED(3, "已分组"), 
		UNAUDITTED(4, "待审批（已提交）"), REFUSED(5, "退回调整"), AUDITTED(6, "审批通过"), SUBMITTED(7, "已出单"), OUTDATE(8, "已过期");
		
		private int id;
		private String name;
		
		private AddnotesPlanStatus(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public int getId() {
			return this.id;
		}
		
		public String getName() {
			return this.name;
		}

		/**
		 * 判断当前状态能否分组
		 * 
		 * @param status
		 * @return
		 */
		public static boolean canGroup(int status) {
			return status == AMOUNT_PREDICTED.getId() || status == GROUPED.getId();
		}

		/**
		 * 判断当前状态能否排序
		 * 
		 * @param status
		 * @return
		 */
		public static boolean canSort(int status) {
			return status >= GROUPED.getId();
		}
	}
	
	/**
	 * 加钞计划明细状态
	 * 
	 * @author jfjiao
	 * @since 2013-8-7
	 */
	public enum AddnotesPlanDetailStatus {
		UNAUDITTED(1, "未审批通过"), AUDITTED(2, "已审批通过");
		
		private int id;
		private String name;
		
		private AddnotesPlanDetailStatus(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public int getId() {
			return this.id;
		}
		
		public String getName() {
			return this.name;
		}
	}
	
	/**
	 * 加钞任务状态
	 * 
	 * @author hongwei
	 * @since 2013-1-7
	 *
	 */
	public enum DispatchStatus {
		UNASSIGNED(1, "未分配人员"), UNAUDITTED(2, "待审批"), AUDITTED(3, "审批通过"), REFUSED(4, "退回调整"), CANCELED(5, "已取消"), 
		OUTDATE(6, "已过期");
		
		private int id;
		private String name;
		
		private DispatchStatus(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public int getId() {
			return this.id;
		}
		
		public String getName() {
			return this.name;
		}
	}
	
	/**
	 * 加钞任务明细状态
	 * 
	 * @author hongwei
	 * @since 2013-1-7
	 *
	 */
	public enum DispatchDetailStatus {
		UNAUDITTED(0, "未审批通过"), AUDITTED(1, "已审批通过");
		
		private int id;
		private String name;
		
		private DispatchDetailStatus(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public int getId() {
			return this.id;
		}
		
		public String getName() {
			return this.name;
		}
	}
	
	/**
	 * 设备各模块信息
	 * @author yt
	 * @since 2013-05-16
	 *
	 */
	public enum ModName {
		IDC("IDC", "读卡器"),
		CHK("CHK", "支票读扫描"),
		PBK("PBK", "存折"),
		PIN("PIN", "密码键盘"),
		SIU("SIU", "传感器"),
		DEP("DEP", "信封存款"),
		CAM("CAM", "照相机"),
		CIM("CIM", "存款"),
		CDM("CDM", "取款"),
		SPR("SPR", "结单打印机"),
		RPR("RPR", "凭条打印机"),
		JPR("JPR", "日志打印机"),
		TTU("TTU", "文本终端");
		
		private String id;
		private String name;
		
		private ModName(String id, String name){
			this.id = id;
			this.name = name;
		}
		
		public String getId() {
			return id;
		}
		public String getName() {
			return name;
		}
	}
	
	/**
	 * 设备类型
	 * @author yt
	 * @since 2013-08-21
	 */
	public enum DevCatalog {
		ATM(10001, "ATM"), CRS(10002, "CRS");
		
		private int id;
		private String name;
		
		private DevCatalog(int id, String name){
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * 是否开通循环
	 * @author yt
	 * @since 2013-08-21
	 */
	public enum CycleFlag {
		UNCYCLE(0, "未开通循环"), ISCYCLE(1, "已开通循环");
		
		private int id;
		private String name;
		
		private CycleFlag(int id, String name){
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}
	}

}
