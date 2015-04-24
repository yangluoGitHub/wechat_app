
--ϵͳ�˵���
--��ɾ���������
alter table SYS_MENU drop constraint FK_MENU_SELF;

--0:ħ�ټ��ŵ����ϵͳ
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('0', 'ħ�ټ��ŵ����ϵͳ', null, ' ', 0, 0, null);

--A:�ŵ����
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A', '�ŵ�ϵͳ', '0', null, 1, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A01', '�ŵ����', 'A', null, 2, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0101', '�ŵ���Ϣ', 'A01', 'storeInfo.do?action=qry', 3, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0102', '��Ʒ��Ϣ', 'A01', 'storeCommInfo.do?action=qry', 3, 2, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0103', '��Ʒ����', 'A01', 'storeCommClassificationInfo.do?action=qry', 3, 3, null);
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


--add by yangluo since 20150323
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(11,'����','A0101','storeInfo.do?action=addPage',1,'�����ŵ���Ϣ');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(12,'�޸�','A0101','storeInfo.do?action=modPage',2,'�޸��ŵ���Ϣ');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(13,'ɾ��','A0101','storeInfo.do?action=del',3,'ɾ���ŵ���Ϣ');
commit;

--add by yangluo since 20150323
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(14,'����','A0103','storeCommClassificationInfo.do?action=addPage',1,'������Ʒ����');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(15,'�޸�','A0103','storeCommClassificationInfo.do?action=modPage',2,'�޸���Ʒ����');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(16,'ɾ��','A0103','storeCommClassificationInfo.do?action=del',3,'ɾ����Ʒ����');
commit;

--add by yangluo since 20150323
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(17,'����','A0102','storeCommInfo.do?action=addPage',1,'������Ʒ��Ϣ');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(18,'�޸�','A0102','storeCommInfo.do?action=modPage',2,'�޸���Ʒ��Ϣ');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(19,'ɾ��','A0102','storeCommInfo.do?action=del',3,'ɾ����Ʒ��Ϣ');
commit;

--ϵͳ��ɫ��
insert into SYS_ROLE (NO, NAME, CATALOG, NOTE)
values (10001, '��������Ա', 1, '��ʼ��ɫ');
insert into SYS_ROLE (NO, NAME, CATALOG, NOTE)
values (10002, '����Ա', 2, '��ʼ��ɫ');

insert into SYS_ROLE (NO, NAME, CATALOG, NOTE)
values (10003, '΢���û�', 3, '��ʼ��ɫ');
commit;
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
commit;


--add by yangluo since 20150323
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 11);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 12);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 13);
commit;

--SYS_CONF_ROLE_BUTTON 
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 14);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 15);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 16);
commit;

insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 17);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 18);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 19);
commit;

--����Ա
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
commit;

--add by yangluo since 20150323
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 11);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 12);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 13);
commit;

--add by yangluo since 20150323
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 14);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 15);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 16);
commit;

insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 17);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 18);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10002, 19);
commit;


insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 1);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 2);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 3);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 4);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 5);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 6);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 7);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 8);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 9);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 10);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 11);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 12);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 13);
commit;

insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 14);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 15);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 16);
commit;

insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 17);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 18);
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10003, 19);
commit;

--ϵͳ��ɫ�˵���ť��
--��������Ա
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'A');
commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'A01');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'A0101');
commit;
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'A0102');
commit;
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10001, 'A0103');
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
values (10002, 'A01');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'A0101');
commit;
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'A0102');
commit;
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10002, 'A0103');
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


--10003���й���Ա
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10003, 'A');
commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10003, 'A01');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10003, 'A0101');
commit;
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10003, 'A0102');
commit;
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10003, 'A0103');
commit;



insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10003, 'F');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10003, 'F01');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10003, 'F0101');
commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10003, 'F02');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10003, 'F0201');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10003, 'F0202');
commit;

insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10003, 'F04');
insert into SYS_CONF_ROLE_MENU (ROLENO, MENUNO)
values (10003, 'F0401');
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
values ('com.weili.wechat.service.manage.impl.StoreInfoServiceImpl', '�ŵ����');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.manage.impl.StoreCommInfoServiceImpl', '��Ʒ��Ϣ');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.manage.impl.StoreCommClassificationInfoServiceImpl', '��Ʒ����');
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

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('addStoreInfo', 'com.weili.wechat.service.manage.impl.StoreInfoServiceImpl', '����');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('delStoreInfo', 'com.weili.wechat.service.manage.impl.StoreInfoServiceImpl', 'ɾ��');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modStoreInfo', 'com.weili.wechat.service.manage.impl.StoreInfoServiceImpl', '�޸�');
commit;

--SYS_OPERATE 
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('addCommClassificationInfo', 'com.weili.wechat.service.manage.impl.StoreCommClassificationInfoServiceImpl', '����');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('delCommClassificationInfo', 'com.weili.wechat.service.manage.impl.StoreCommClassificationInfoServiceImpl', 'ɾ��');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modCommClassificationInfo', 'com.weili.wechat.service.manage.impl.StoreCommClassificationInfoServiceImpl', '�޸�');
commit;

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('addStoreCommInfo', 'com.weili.wechat.service.manage.impl.StoreCommInfoServiceImpl', '����');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modStoreCommInfo', 'com.weili.wechat.service.manage.impl.StoreCommInfoServiceImpl', 'ɾ��');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('delStoreCommInfo', 'com.weili.wechat.service.manage.impl.StoreCommInfoServiceImpl', '�޸�');
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
insert into OP_TABLE (NO, PASSWD, NAME, STATUS, ONLINE_FLAG, ROLE, PHONE, MOBILE, EMAIL, PHOTO, LOGIN_IP, LOGIN_TIME, PASSWD_EXPIRATION, PASSWD_ERROR, SIGN_FLAG, STORE_ID)
values ('admin1', '3ac751c1478699664bcc11b5efc04fac', '��������Ա1', 1, 0, 10001, null, null, null, null, '0:0:0:0:0:0:0:1', '2012-12-30 12:03:01', '2900-01-01', 0, 1, null);
commit;
