
--系统菜单表
--先删除自身关联
alter table SYS_MENU drop constraint FK_MENU_SELF;

--0:微信公众号管理系统
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('0', '紫金微信公众号管理平台', null, ' ', 0, 0, null);

--A:微信公众号管理
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A', '公众号管理', '0', null, 1, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A02', '素材管理', 'A', null, 2, 2, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0201', '图文管理', 'A02', 'imageText.do?action=qry', 3, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0202', '多媒体管理', 'A02', 'media.do?action=qry', 3, 2, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A03', '自定义菜单', 'A', null, 2, 3, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0301', '菜单设置', 'A03', 'weMenu.do?action=qry', 3, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A04', '消息管理', 'A', null, 2, 4, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0401', '消息应答', 'A04', 'message.do?action=qry', 3, 1, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A05', '业务功能管理', 'A', null, 2, 5, null);
insert into SYS_MENU (NO, NAME, MENU_FATHER, URL, MENU_LEVEL, MENU_ORDER, NOTE)
values ('A0501', '功能配置', 'A05', 'menuFunction.do?action=qryFuncInfo', 3, 1, null);
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
insert into SYS_BUTTON (NO, NAME, MENU, URL, BUTTON_ORDER, NOTE) 
values(11,'配置','A0501','menuFunction.do?action=configureFunction',1,'配置公众号业务功能');
commit;

--系统角色表
insert into SYS_ROLE (NO, NAME, CATALOG, NOTE)
values (10001, '超级管理员', 1, '初始角色');
insert into SYS_ROLE (NO, NAME, CATALOG, NOTE)
values (10002, '管理员', 2, '初始角色');

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
insert into SYS_CONF_ROLE_BUTTON (ROLENO, BUTTONNO)
values (10001, 11);
commit;

--分行管理员
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

--系统角色菜单按钮表
--超级管理员
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



--10002分行管理员
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
values ('com.weili.wechat.service.system.impl.AccountServiceImpl', '公众号管理');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.manage.impl.ImageTextServiceImpl', '图文信息管理');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.manage.impl.MediaServiceImpl', '多媒体资源管理');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.manage.impl.MenuFunctionServiceImpl', '业务功能管理');
insert into SYS_MODULE (NO, NAME)
values ('com.weili.wechat.service.manage.impl.MessageServiceImpl', '信息管理');
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
--ZjAdd by yangzyrd 2014-3-6
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('addAccount', 'com.weili.wechat.service.system.impl.AccountServiceImpl', '增加');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('delAccount', 'com.weili.wechat.service.system.impl.AccountServiceImpl', '删除');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values ('modAccount', 'com.weili.wechat.service.system.impl.AccountServiceImpl', '修改');
--ZjAdd by zxgao 2014-3-18
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('addUserPubAccount','com.weili.wechat.service.system.impl.UserServiceImpl','增加用户公众号权限');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('delUserPubAccount','com.weili.wechat.service.system.impl.UserServiceImpl','删除用户公众号权限');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('addImageText','com.weili.wechat.service.manage.impl.ImageTextServiceImpl','添加图文信息');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('delImageText','com.weili.wechat.service.manage.impl.ImageTextServiceImpl','删除图文信息');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('modImageText','com.weili.wechat.service.manage.impl.ImageTextServiceImpl','修改图文信息');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('addMedia','com.weili.wechat.service.manage.impl.MediaServiceImpl','新增资源');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('modMedia','com.weili.wechat.service.manage.impl.MediaServiceImpl','修改资源');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('delMedia','com.weili.wechat.service.manage.impl.MediaServiceImpl','删除资源');

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('addFunction','com.weili.wechat.service.manage.impl.MenuFunctionServiceImpl','新增业务功能');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('modFunction','com.weili.wechat.service.manage.impl.MenuFunctionServiceImpl','修改业务功能');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('delFunction','com.weili.wechat.service.manage.impl.MenuFunctionServiceImpl','删除业务功能');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('delPubAccountFunction','com.weili.wechat.service.manage.impl.MenuFunctionServiceImpl','删除公众号业务功能关系');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('delPubAccountFunctionByWechatId','com.weili.wechat.service.manage.impl.MenuFunctionServiceImpl','删除公众号业务功能关系');
insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('savaPubFuncRelations','com.weili.wechat.service.manage.impl.MenuFunctionServiceImpl','业务功能配置');

insert into SYS_OPERATE (NO, MODULE_NO, NAME)
values('addMessage','com.weili.wechat.service.manage.impl.MessageServiceImpl','新增信息');
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
insert into OP_TABLE (NO, PASSWD, NAME, STATUS, ONLINE_FLAG, ROLE, PHONE, MOBILE, EMAIL, PHOTO, LOGIN_IP, LOGIN_TIME, PASSWD_EXPIRATION, PASSWD_ERROR, SIGN_FLAG)
values ('admin1', '3ac751c1478699664bcc11b5efc04fac', '超级管理员1', 1, 0, 10001, null, null, null, null, '0:0:0:0:0:0:0:1', '2012-12-30 12:03:01', '2900-01-01', 0, 1);
commit;

