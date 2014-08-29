
--ϵͳ�˵���
--��ɾ���������
alter table SYS_MENU drop constraint FK_MENU_SELF;

--0:΢�Ź��ںŹ���ϵͳ
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('0', '�Ͻ�΢�Ź��ںŹ���ƽ̨', null, ' ', 0, 0, null);

--A:΢�Ź��ںŹ���
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A', '���ںŹ���', '0', null, 1, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A02', '�زĹ���', 'A', null, 2, 2, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0201', 'ͼ�Ĺ���', 'A02', 'imageText.do?action=qry', 3, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0202', '��ý�����', 'A02', 'media.do?action=qry', 3, 2, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A03', '�Զ���˵�', 'A', null, 2, 3, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0301', '�˵�����', 'A03', 'weMenu.do?action=qry', 3, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A04', '��Ϣ����', 'A', null, 2, 4, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0401', '��ϢӦ��', 'A04', 'message.do?action=qry', 3, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A05', 'ҵ���ܹ���', 'A', null, 2, 5, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0501', '��������', 'A05', 'menuFunction.do?action=qryFuncInfo', 3, 1, null);
commit;

--F:ϵͳ������ϵͳ
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F', 'ϵͳ����', '0', null, 1, 6, null);
commit;

insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F01', '��ɫ����', 'F', null, 2, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F0101', '��ɫ��Ϣ', 'F01', 'role.do?action=qry', 3, 1, null);
commit;

insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F02', '�û�����', 'F', null, 2, 2, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F0201', 'ϵͳ�û�', 'F02', 'user.do?action=qry', 3, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F0202', '�û�����', 'F02', 'user.do?action=modPasswdPage', 3, 2, null);
commit;

insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F04', '��������', 'F', null, 2, 4, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F0401', 'ϵͳ����', 'F04', 'param.do?action=qrySysparam', 3, 1, null);
commit;

--add foreign constraint
alter table SYS_MENU add constraint FK_MENU_SELF foreign key (MENU_FATHER) references SYS_MENU (NO);

--ϵͳ��ť��
--ϵͳ������ϵͳ
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE)
values (1, '����', 'F0101', 'role.do?action=addPage', 1, '��ɫ��Ϣ');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE)
values (2, '�޸�', 'F0101', 'role.do?action=modPage', 2, '��ɫ��Ϣ');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE)
values (3, 'ɾ��', 'F0101', 'role.do?action=del', 3, '��ɫ��Ϣ');

insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE)
values (4, '����', 'F0201', 'user.do?action=addPage', 1, 'ϵͳ�û�');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE)
values (5, '�޸�', 'F0201', 'user.do?action=modPage', 2, 'ϵͳ�û�');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE)
values (6, 'ɾ��', 'F0201', 'user.do?action=del', 3, 'ϵͳ�û�');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE)
values (7, '��������', 'F0201', 'user.do?action=resetPasswd', 4, 'ϵͳ�û�');

insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(8,'����','F0401','param.do?action=intoAddSysPage',1,'����ϵͳ����');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(9,'�޸�','F0401','param.do?action=intoModSysPage',2,'�޸�ϵͳ����');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(10,'ɾ��','F0401','param.do?action=delSysparam',3,'ɾ��ϵͳ����');
commit;
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(11,'����','A0501','menuFunction.do?action=configureFunction',1,'���ù��ں�ҵ����');
commit;

--ϵͳ��ɫ��
insert into SYS_ROLE (NO, NAME, CATALOG, NOTE)
values (10001, '��������Ա', 1, '��ʼ��ɫ');
insert into SYS_ROLE (NO, NAME, CATALOG, NOTE)
values (10002, '����Ա', 2, '��ʼ��ɫ');

--ϵͳ��ɫ��ťȨ�ޱ�
--��������Ա
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 1);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 2);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 3);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 4);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 5);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 6);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 7);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 8);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 9);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 10);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 11);
commit;

--���й���Ա
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 4);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 5);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 6);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 7);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 8);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 9);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 10);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 11);
commit;

--ϵͳ��ɫ�˵���ť��
--��������Ա
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'A');
commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'A02');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'A0201');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'A0202');
commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'A03');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'A0301');

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'A04');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'A0401');
commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'A05');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'A0501');
commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'F');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'F01');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'F0101');
commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'F02');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'F0201');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'F0202');
commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'F04');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'F0401');
commit;



--10002���й���Ա
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'A');
commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'A02');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'A0201');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'A0202');
commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'A03');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'A0301');

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'A04');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'A0401');
commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'A05');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'A0501');
commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'F');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'F01');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'F0101');
commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'F02');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'F0201');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'F0202');
commit;

--insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
--values (10002, 'F03');
--insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
--values (10002, 'F0301');
--commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'F04');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'F0401');
commit;

--ϵͳģ���
--ϵͳ������ϵͳ

insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.system.impl.RoleServiceImpl', 'ϵͳ��ɫ');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.system.impl.UserServiceImpl', 'ϵͳ�û�');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.system.impl.SysParamServiceImpl', 'ϵͳ����');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.login.impl.LoginServiceImpl', '��¼�˳�');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.system.impl.AccountServiceImpl', '���ںŹ���');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.manage.impl.ImageTextServiceImpl', 'ͼ����Ϣ����');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.manage.impl.MediaServiceImpl', '��ý����Դ����');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.manage.impl.MenuFunctionServiceImpl', 'ҵ���ܹ���');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.manage.impl.MessageServiceImpl', '��Ϣ����');
commit;

--SYS_NAME(�ݲ���)


--ϵͳ������
--ϵͳ������ϵͳ
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('addRole', 'com.weili.wechat.service.system.impl.RoleServiceImpl', '����');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('delRole', 'com.weili.wechat.service.system.impl.RoleServiceImpl', 'ɾ��');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modRole', 'com.weili.wechat.service.system.impl.RoleServiceImpl', '�޸�');

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('addUser', 'com.weili.wechat.service.system.impl.UserServiceImpl', '����');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('delUser', 'com.weili.wechat.service.system.impl.UserServiceImpl', 'ɾ��');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modUser', 'com.weili.wechat.service.system.impl.UserServiceImpl', '�޸�');

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('resetUserPasswd', 'com.weili.wechat.service.system.impl.UserServiceImpl', '��������');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modUserPasswd', 'com.weili.wechat.service.system.impl.UserServiceImpl', '�޸�����');

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('addSysParam', 'com.weili.wechat.service.system.impl.SysParamServiceImpl', '����');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('delSysParam', 'com.weili.wechat.service.system.impl.SysParamServiceImpl', 'ɾ��');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modSysParam', 'com.weili.wechat.service.system.impl.SysParamServiceImpl', '�޸�');

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('loginLog', 'com.weili.wechat.service.login.impl.LoginServiceImpl', '��¼');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('logoutLog', 'com.weili.wechat.service.login.impl.LoginServiceImpl', '�˳�');
--ZjAdd by yangzyrd 2014-3-6
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('addAccount', 'com.weili.wechat.service.system.impl.AccountServiceImpl', '����');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('delAccount', 'com.weili.wechat.service.system.impl.AccountServiceImpl', 'ɾ��');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modAccount', 'com.weili.wechat.service.system.impl.AccountServiceImpl', '�޸�');
--ZjAdd by zxgao 2014-3-18
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('addUserPubAccount','com.weili.wechat.service.system.impl.UserServiceImpl','�����û����ں�Ȩ��');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('delUserPubAccount','com.weili.wechat.service.system.impl.UserServiceImpl','ɾ���û����ں�Ȩ��');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('addImageText','com.weili.wechat.service.manage.impl.ImageTextServiceImpl','���ͼ����Ϣ');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('delImageText','com.weili.wechat.service.manage.impl.ImageTextServiceImpl','ɾ��ͼ����Ϣ');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('modImageText','com.weili.wechat.service.manage.impl.ImageTextServiceImpl','�޸�ͼ����Ϣ');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('addMedia','com.weili.wechat.service.manage.impl.MediaServiceImpl','������Դ');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('modMedia','com.weili.wechat.service.manage.impl.MediaServiceImpl','�޸���Դ');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('delMedia','com.weili.wechat.service.manage.impl.MediaServiceImpl','ɾ����Դ');

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('addFunction','com.weili.wechat.service.manage.impl.MenuFunctionServiceImpl','����ҵ����');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('modFunction','com.weili.wechat.service.manage.impl.MenuFunctionServiceImpl','�޸�ҵ����');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('delFunction','com.weili.wechat.service.manage.impl.MenuFunctionServiceImpl','ɾ��ҵ����');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('delPubAccountFunction','com.weili.wechat.service.manage.impl.MenuFunctionServiceImpl','ɾ�����ں�ҵ���ܹ�ϵ');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('delPubAccountFunctionByWechatId','com.weili.wechat.service.manage.impl.MenuFunctionServiceImpl','ɾ�����ں�ҵ���ܹ�ϵ');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('savaPubFuncRelations','com.weili.wechat.service.manage.impl.MenuFunctionServiceImpl','ҵ��������');

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('addMessage','com.weili.wechat.service.manage.impl.MessageServiceImpl','������Ϣ');
commit;

--ϵͳ�������
--ϵͳ�������ͱ�
insert into SYS_PARAM_CATALOG (CATALOG, CATALOG_NAME, DESCRIPTION)
values (1, 'ϵͳ���в���', 'ϵͳ���в���');
insert into SYS_PARAM_CATALOG (CATALOG, CATALOG_NAME, DESCRIPTION)
values (2, '��ʱ�������', '��ʱ�������');
commit;

--ϵͳ������
insert into SYS_PARAM (LOGIC_ID, CATALOG, PARAM_NAME, PARAM_VALUE, STATEMENT, DESCRIPTION)
values ('10014', 1, 'passwordUpdateDate', '365', '�����������', null);
insert into SYS_PARAM (LOGIC_ID, CATALOG, PARAM_NAME, PARAM_VALUE, STATEMENT, DESCRIPTION)
values ('10016', 1, 'passwdtxDate', '364', '������������', null);
insert into SYS_PARAM (LOGIC_ID, CATALOG, PARAM_NAME, PARAM_VALUE, STATEMENT, DESCRIPTION)
values ('10017', 1, 'auditFlag', 'false', '�Ƿ���Ҫ��˱�־ true/false', null);
insert into SYS_PARAM (LOGIC_ID, CATALOG, PARAM_NAME, PARAM_VALUE, STATEMENT, DESCRIPTION)
values ('10018', 1, 'passwdmaxCount', '5', '��������������', null);
commit;

--��Ա��Ϣ��
insert into OP_TABLE (NO, PASSWD, NAME, STATUS, ONLINE_FLAG, ROLE, PHONE, MOBILE, EMAIL, PHOTO, LOGIN_IP, LOGIN_TIME, PASSWD_EXPIRATION, PASSWD_ERROR, SIGN_FLAG)
values ('admin1', '3ac751c1478699664bcc11b5efc04fac', '��������Ա1', 1, 0, 10001, null, null, null, null, '0:0:0:0:0:0:0:1', '2012-12-30 12:03:01', '2900-01-01', 0, 1);
commit;

