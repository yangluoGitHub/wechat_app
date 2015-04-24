package com.weili.wechat.common;

/**
 * @author hjtang
 * @since 2010-10-12
 */

public enum AtmLogRet {
	R01, R02, R04, R05, R06,R07, R30, R20, R21, R00, RFF,S00, SFF;

	/**
	 * @param ret
	 * @return ��������
	 */
	public static AtmLogRet getRemoteRet(String ret) {
		if(ret.equals("00"))
		{
			return AtmLogRet.R00;
		}
		else if(ret.equals("02"))
		{
			return AtmLogRet.R02;
		}
		else if(ret.equals("04"))
		{
			return AtmLogRet.R04;
		}
		else if(ret.equals("05"))
		{
			return AtmLogRet.R05;
		}
		else if(ret.equals("06"))
		{
			return AtmLogRet.R06;
		}
		else if(ret.equals("07"))
		{
			return AtmLogRet.R07;
		}
		else if(ret.equals("30"))
		{
			return AtmLogRet.R30;
		}
		else if(ret.equals("20"))
		{
			return AtmLogRet.R20;
		}
		else if(ret.equals("21"))
		{
			return AtmLogRet.R21;
		}
		else
		{
			return AtmLogRet.RFF;
		}		
	}
	/**
	 * @param ret
	 * @return ��������
	 */
	public static String getAtmLogRetName(AtmLogRet ret) {
		
		switch (ret) {
		case R01:
			return "�ͻ��˷��ش��ļ�ʧ��";
		case R02:
			return "�ͻ��˷��ض�λ��ָ�����ļ�λ��ʧ��";
		case R04:
			return "�ͻ��˷����ļ������ټ�����ȡ����,�ļ��Ѿ�����";
		case R05:
			return "�������˻�ȡҪ���͵��ļ���Ϣ����";
		case R06:
			return "��������";
		case R07:
			return "�ͻ��˷���ֹͣ�ļ����ͷ���";
		case R30:
			return "�ͻ��˷��ر��Ľ�������,ָ����XML�ֶ����������в�����";
		case R20:
			return "�ͻ��˷����ļ����ͽ���";
		case R21:
			return "�ͻ��˷��ر��ĵķ�������Ϊ�ļ����ݣ����ݵĳ���Ϊ(���ĳ���-8)";
		case R00:
			return "�ͻ��˷����ļ�׼�����";	
		case RFF:
			return "�ͻ���δ֪����";	
		case S00:
			return "����˻�ȡ�ļ��ɹ�";
		case SFF:
			return "������쳣ʧ��";
		default:			
			return String.valueOf(ret);
		}
	}
}
