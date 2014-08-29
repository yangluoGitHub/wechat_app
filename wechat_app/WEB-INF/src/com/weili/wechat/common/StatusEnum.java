package com.weili.wechat.common;

public class StatusEnum {
	
	/**
	 * ��ý����Դ����
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
	 * ��Ϣ����
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
	
	
	// ͨ�ò������ͣ��������޸ģ�ɾ��
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

	// �������ࣺ�ӳ�/ά��
	public enum TaskType {
		DISPATCH(1, "�ӳ�"), MAINTAIN(2, "ά��");
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

	// �豸�ӳ�������Ƿ���������������ͳ��
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
	 * ��ͼ������վ������������
	 * 
	 * @author yyc
	 * 
	 */
	public enum Map_StationMarkerClickCode {
		INFO_WIN(0, "����Ϣ����"), OP_POINT(1, "��Ա���"), DIS_POINT(2, "�ӳ����"), MT_POINT(3, "ά�����");
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
	 * ��������
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
	 * �Ƿ�Ϊ�ն˷���վ
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
	 * �豸ά���������
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
	 * ��γ�ȷ�Χ(�ڱ�֤��Χ����С��ǰ����ȡһλС��)
	 * 
	 * @author yyc
	 * 
	 */
	public enum XYArea {
		CHINA_EAST(135.1, 15044571.3), CHINA_WEST(73.5, 4649808.4), CHINA_NORTH(53.5, 7058289.7), CHINA_SOUTH(3.8, 391988.9);

		private double latLng; // ��׼��γ��
		private double gaussProj; // ��˹ͶӰ

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
		 * �ж��Ƿ����й�����
		 * 
		 * @param x
		 *            ����
		 * @param y
		 *            γ��
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
		 * �ж��Ƿ����й�����_��׼��γ��
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
		 * �ж��Ƿ����й�����_��˹ͶӰ
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
	 * ����-����
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
	 * ����-����
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
	 * ����-������
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
	 * ���л����ȼ�
	 * 
	 * @author yyc
	 * 
	 */
	public enum OrgGrade {
		HEAD_BANK(1, "����"), BRANCH_BANK(2, "����"), SUB_BRANCH_BANK(3, "֧��"), NETPOINT(4, "����");

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
	 * �ӳ��ƻ�״̬
	 * 
	 * @author hongwei
	 * @since 2013-1-4
	 * mod by jfjiao since 2013-8-1
	 */
	public enum AddnotesPlanStatus {
		DEV_CHOOSE(1, "��ѡ���豸"), AMOUNT_PREDICTED(2, "��Ԥ����"), GROUPED(3, "�ѷ���"), 
		UNAUDITTED(4, "�����������ύ��"), REFUSED(5, "�˻ص���"), AUDITTED(6, "����ͨ��"), SUBMITTED(7, "�ѳ���"), OUTDATE(8, "�ѹ���");
		
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
		 * �жϵ�ǰ״̬�ܷ����
		 * 
		 * @param status
		 * @return
		 */
		public static boolean canGroup(int status) {
			return status == AMOUNT_PREDICTED.getId() || status == GROUPED.getId();
		}

		/**
		 * �жϵ�ǰ״̬�ܷ�����
		 * 
		 * @param status
		 * @return
		 */
		public static boolean canSort(int status) {
			return status >= GROUPED.getId();
		}
	}
	
	/**
	 * �ӳ��ƻ���ϸ״̬
	 * 
	 * @author jfjiao
	 * @since 2013-8-7
	 */
	public enum AddnotesPlanDetailStatus {
		UNAUDITTED(1, "δ����ͨ��"), AUDITTED(2, "������ͨ��");
		
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
	 * �ӳ�����״̬
	 * 
	 * @author hongwei
	 * @since 2013-1-7
	 *
	 */
	public enum DispatchStatus {
		UNASSIGNED(1, "δ������Ա"), UNAUDITTED(2, "������"), AUDITTED(3, "����ͨ��"), REFUSED(4, "�˻ص���"), CANCELED(5, "��ȡ��"), 
		OUTDATE(6, "�ѹ���");
		
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
	 * �ӳ�������ϸ״̬
	 * 
	 * @author hongwei
	 * @since 2013-1-7
	 *
	 */
	public enum DispatchDetailStatus {
		UNAUDITTED(0, "δ����ͨ��"), AUDITTED(1, "������ͨ��");
		
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
	 * �豸��ģ����Ϣ
	 * @author yt
	 * @since 2013-05-16
	 *
	 */
	public enum ModName {
		IDC("IDC", "������"),
		CHK("CHK", "֧Ʊ��ɨ��"),
		PBK("PBK", "����"),
		PIN("PIN", "�������"),
		SIU("SIU", "������"),
		DEP("DEP", "�ŷ���"),
		CAM("CAM", "�����"),
		CIM("CIM", "���"),
		CDM("CDM", "ȡ��"),
		SPR("SPR", "�ᵥ��ӡ��"),
		RPR("RPR", "ƾ����ӡ��"),
		JPR("JPR", "��־��ӡ��"),
		TTU("TTU", "�ı��ն�");
		
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
	 * �豸����
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
	 * �Ƿ�ͨѭ��
	 * @author yt
	 * @since 2013-08-21
	 */
	public enum CycleFlag {
		UNCYCLE(0, "δ��ͨѭ��"), ISCYCLE(1, "�ѿ�ͨѭ��");
		
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
