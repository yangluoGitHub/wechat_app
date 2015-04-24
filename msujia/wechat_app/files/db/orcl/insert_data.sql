
--系统菜单表
--先删除自身关联
alter table SYS_MENU drop constraint FK_MENU_SELF;

--0:魔速家门店管理系统
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('0', '魔速家门店管理系统', null, ' ', 0, 0, null);

--A:门店管理
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A', '门店系统', '0', null, 1, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A01', '门店管理', 'A', null, 2, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0101', '门店信息', 'A01', 'storeInfo.do?action=qry', 3, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0102', '商品信息', 'A01', 'storeCommInfo.do?action=qry', 3, 2, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0103', '商品分类', 'A01', 'storeCommClassificationInfo.do?action=qry', 3, 3, null);
commit;


--F:系统管理子系统
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F', '系统管理', '0', null, 1, 6, null);
commit;

insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F01', '角色管理', 'F', null, 2, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F0101', '角色信息', 'F01', 'role.do?action=qry', 3, 1, null);
commit;

insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F02', '用户管理', 'F', null, 2, 2, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F0201', '系统用户', 'F02', 'user.do?action=qry', 3, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F0202', '用户密码', 'F02', 'user.do?action=modPasswdPage', 3, 2, null);
commit;

insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F04', '参数配置', 'F', null, 2, 4, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('F0401', '系统参数', 'F04', 'param.do?action=qrySysparam', 3, 1, null);
commit;

--add foreign constraint
alter table SYS_MENU add constraint FK_MENU_SELF foreign key (MENU_FATHER) references SYS_MENU (NO);

--系统按钮表
--系统管理子系统
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE)
values (1, '增加', 'F0101', 'role.do?action=addPage', 1, '角色信息');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE)
values (2, '修改', 'F0101', 'role.do?action=modPage', 2, '角色信息');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE)
values (3, '删除', 'F0101', 'role.do?action=del', 3, '角色信息');

insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE)
values (4, '增加', 'F0201', 'user.do?action=addPage', 1, '系统用户');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE)
values (5, '修改', 'F0201', 'user.do?action=modPage', 2, '系统用户');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE)
values (6, '删除', 'F0201', 'user.do?action=del', 3, '系统用户');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE)
values (7, '重置密码', 'F0201', 'user.do?action=resetPasswd', 4, '系统用户');

insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(8,'增加','F0401','param.do?action=intoAddSysPage',1,'增加系统参数');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(9,'修改','F0401','param.do?action=intoModSysPage',2,'修改系统参数');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(10,'删除','F0401','param.do?action=delSysparam',3,'删除系统参数');
commit;


--add by yangluo since 20150323
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(11,'增加','A0101','storeInfo.do?action=addPage',1,'增加门店信息');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(12,'修改','A0101','storeInfo.do?action=modPage',2,'修改门店信息');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(13,'删除','A0101','storeInfo.do?action=del',3,'删除门店信息');
commit;

--add by yangluo since 20150323
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(14,'增加','A0103','storeCommClassificationInfo.do?action=addPage',1,'增加商品分类');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(15,'修改','A0103','storeCommClassificationInfo.do?action=modPage',2,'修改商品分类');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(16,'删除','A0103','storeCommClassificationInfo.do?action=del',3,'删除商品分类');
commit;

--add by yangluo since 20150323
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(17,'增加','A0102','storeCommInfo.do?action=addPage',1,'增加商品信息');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(18,'修改','A0102','storeCommInfo.do?action=modPage',2,'修改商品信息');
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(19,'删除','A0102','storeCommInfo.do?action=del',3,'删除商品信息');
commit;

--系统角色表
insert into SYS_ROLE (NO, NAME, CATALOG, NOTE)
values (10001, '超级管理员', 1, '初始角色');
insert into SYS_ROLE (NO, NAME, CATALOG, NOTE)
values (10002, '管理员', 2, '初始角色');

insert into SYS_ROLE (NO, NAME, CATALOG, NOTE)
values (10003, '微信用户', 3, '初始角色');
commit;
--系统角色按钮权限表
--超级管理员
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

--管理员
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

--系统角色菜单按钮表
--超级管理员
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



--10002分行管理员
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


--10003分行管理员
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

--系统模块表
--系统管理子系统

insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.system.impl.RoleServiceImpl', '系统角色');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.system.impl.UserServiceImpl', '系统用户');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.system.impl.SysParamServiceImpl', '系统参数');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.login.impl.LoginServiceImpl', '登录退出');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.manage.impl.StoreInfoServiceImpl', '门店管理');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.manage.impl.StoreCommInfoServiceImpl', '商品信息');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.manage.impl.StoreCommClassificationInfoServiceImpl', '商品分类');
commit;


--SYS_NAME(暂不用)


--系统操作表
--系统管理子系统
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('addRole', 'com.weili.wechat.service.system.impl.RoleServiceImpl', '增加');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('delRole', 'com.weili.wechat.service.system.impl.RoleServiceImpl', '删除');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modRole', 'com.weili.wechat.service.system.impl.RoleServiceImpl', '修改');

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('addUser', 'com.weili.wechat.service.system.impl.UserServiceImpl', '增加');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('delUser', 'com.weili.wechat.service.system.impl.UserServiceImpl', '删除');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modUser', 'com.weili.wechat.service.system.impl.UserServiceImpl', '修改');

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('resetUserPasswd', 'com.weili.wechat.service.system.impl.UserServiceImpl', '重置密码');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modUserPasswd', 'com.weili.wechat.service.system.impl.UserServiceImpl', '修改密码');

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('addSysParam', 'com.weili.wechat.service.system.impl.SysParamServiceImpl', '增加');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('delSysParam', 'com.weili.wechat.service.system.impl.SysParamServiceImpl', '删除');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modSysParam', 'com.weili.wechat.service.system.impl.SysParamServiceImpl', '修改');

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('loginLog', 'com.weili.wechat.service.login.impl.LoginServiceImpl', '登录');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('logoutLog', 'com.weili.wechat.service.login.impl.LoginServiceImpl', '退出');

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('addStoreInfo', 'com.weili.wechat.service.manage.impl.StoreInfoServiceImpl', '增加');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('delStoreInfo', 'com.weili.wechat.service.manage.impl.StoreInfoServiceImpl', '删除');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modStoreInfo', 'com.weili.wechat.service.manage.impl.StoreInfoServiceImpl', '修改');
commit;

--SYS_OPERATE 
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('addCommClassificationInfo', 'com.weili.wechat.service.manage.impl.StoreCommClassificationInfoServiceImpl', '增加');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('delCommClassificationInfo', 'com.weili.wechat.service.manage.impl.StoreCommClassificationInfoServiceImpl', '删除');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modCommClassificationInfo', 'com.weili.wechat.service.manage.impl.StoreCommClassificationInfoServiceImpl', '修改');
commit;

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('addStoreCommInfo', 'com.weili.wechat.service.manage.impl.StoreCommInfoServiceImpl', '增加');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modStoreCommInfo', 'com.weili.wechat.service.manage.impl.StoreCommInfoServiceImpl', '删除');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('delStoreCommInfo', 'com.weili.wechat.service.manage.impl.StoreCommInfoServiceImpl', '修改');
commit;

--系统参数相关
--系统参数类型表
insert into SYS_PARAM_CATALOG (CATALOG, CATALOG_NAME, DESCRIPTION)
values (1, '系统运行参数', '系统运行参数');
insert into SYS_PARAM_CATALOG (CATALOG, CATALOG_NAME, DESCRIPTION)
values (2, '定时任务参数', '定时任务参数');
commit;

--系统参数表
insert into SYS_PARAM (LOGIC_ID, CATALOG, PARAM_NAME, PARAM_VALUE, STATEMENT, DESCRIPTION)
values ('10014', 1, 'passwordUpdateDate', '365', '密码更换周期', null);
insert into SYS_PARAM (LOGIC_ID, CATALOG, PARAM_NAME, PARAM_VALUE, STATEMENT, DESCRIPTION)
values ('10016', 1, 'passwdtxDate', '364', '密码提醒日期', null);
insert into SYS_PARAM (LOGIC_ID, CATALOG, PARAM_NAME, PARAM_VALUE, STATEMENT, DESCRIPTION)
values ('10017', 1, 'auditFlag', 'false', '是否需要审核标志 true/false', null);
insert into SYS_PARAM (LOGIC_ID, CATALOG, PARAM_NAME, PARAM_VALUE, STATEMENT, DESCRIPTION)
values ('10018', 1, 'passwdmaxCount', '5', '密码重试最大次数', null);
commit;
--人员信息表
insert into OP_TABLE (NO, PASSWD, NAME, STATUS, ONLINE_FLAG, ROLE, PHONE, MOBILE, EMAIL, PHOTO, LOGIN_IP, LOGIN_TIME, PASSWD_EXPIRATION, PASSWD_ERROR, SIGN_FLAG, STORE_ID)
values ('admin1', '3ac751c1478699664bcc11b5efc04fac', '超级管理员1', 1, 0, 10001, null, null, null, null, '0:0:0:0:0:0:0:1', '2012-12-30 12:03:01', '2900-01-01', 0, 1, null);
commit;
