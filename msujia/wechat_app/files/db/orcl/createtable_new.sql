--系统角色表
create table SYS_ROLE
(
  NO       INTEGER not null,
  NAME     VARCHAR2(50) not null,
  CATALOG  INTEGER not null,
  ORG_TYPE INTEGER,
  NOTE     VARCHAR2(200)
)
tablespace MSUJIA_SPACE;

comment on table SYS_ROLE
  is '角色表';
comment on column SYS_ROLE.NO
  is '编号';
comment on column SYS_ROLE.NAME
  is '名称';
comment on column SYS_ROLE.CATALOG
  is '角色类型：1－超级操作员；2－预设操作员；3－其他操作员';
comment on column SYS_ROLE.ORG_TYPE
  is '角色所属机构类型';
comment on column SYS_ROLE.NOTE
  is '备注';
alter table SYS_ROLE
  add constraint PK_SYS_ROLE primary key (NO)
  using index tablespace IDXPRIMARY;
--系统用户表
create table OP_TABLE
(
  NO                VARCHAR2(64) not null,
  PASSWD            VARCHAR2(50) not null,
  NAME              VARCHAR2(20) not null,
  STATUS            INTEGER not null,
  ONLINE_FLAG       INTEGER not null,
  ROLE              INTEGER not null,
  PHONE             VARCHAR2(20),
  MOBILE            VARCHAR2(20),
  EMAIL             VARCHAR2(40),
  PHOTO             VARCHAR2(50),
  LOGIN_IP          VARCHAR2(20),
  LOGIN_TIME        VARCHAR2(20),
  PASSWD_EXPIRATION VARCHAR2(10),
  PASSWD_ERROR      INTEGER default 0 not null,
  SIGN_FLAG         INTEGER default 0 not null,
  STORE_ID			VARCHAR2(36)
)
tablespace MSUJIA_SPACE;
alter table OP_TABLE
  add constraint PK_OP primary key (NO)
  using index tablespace IDXPRIMARY;

alter table OP_TABLE
  add constraint FK_OP_R_ROLE foreign key (ROLE)
  references SYS_ROLE (NO);

--系统模块表
create table SYS_MODULE
(
  NO   VARCHAR2(100) not null,
  NAME VARCHAR2(60) not null
)
tablespace MSUJIA_SPACE;
comment on table SYS_MODULE
  is '模块表';
comment on column SYS_MODULE.NO
  is '编号';
comment on column SYS_MODULE.NAME
  is '名称';
alter table SYS_MODULE
  add constraint PK_SYS_MODULE primary key (NO)
  using index tablespace IDXPRIMARY;

  
--系统操作表
create table SYS_OPERATE
(
  NO        VARCHAR2(60) not null,
  MODULE_NO VARCHAR2(100) not null,
  NAME      VARCHAR2(60) not null
)
tablespace MSUJIA_SPACE;

comment on table SYS_OPERATE
  is '操作表';
comment on column SYS_OPERATE.NO
  is '编号';
comment on column SYS_OPERATE.MODULE_NO
  is '模块编号';
comment on column SYS_OPERATE.NAME
  is '名称';
alter table SYS_OPERATE
  add constraint PK_SYS_OPERATE primary key (NO)
  using index tablespace IDXPRIMARY;

alter table SYS_OPERATE
  add constraint FK_OPER_R_MODU foreign key (MODULE_NO)
  references SYS_MODULE (NO);
  

  
--系统日志表
create table SYS_LOG
(
  USERID   VARCHAR2(20) not null,
  USERNAME VARCHAR2(20) not null,
  LOG_DATE VARCHAR2(30) not null,
  MODULE   VARCHAR2(128) not null,
  OPERATE  VARCHAR2(60) not null,
  XML      CLOB,
  XML_OLD  CLOB,
  NOTE     VARCHAR2(20)
)
tablespace LOGSPACE;

alter table SYS_LOG
  add constraint PK_SYS_LOG primary key (USERID, LOG_DATE)
  using index tablespace IDXPRIMARY;
alter table SYS_LOG
  add constraint FK_LOG_R_MODU foreign key (MODULE)
  references SYS_MODULE (NO);
alter table SYS_LOG
  add constraint FK_LOG_R_OPER foreign key (OPERATE)
  references SYS_OPERATE (NO);
CREATE INDEX IDX_LOG_DATE ON SYS_LOG (LOG_DATE)
  TABLESPACE IDXSECOND;
  
--系统菜单表
create table SYS_MENU
(
  NO          VARCHAR2(5) not null,
  NAME        VARCHAR2(40) not null,
  MENU_FATHER VARCHAR2(5),
  URL         VARCHAR2(200),
  MENU_LEVEL  INTEGER,
  MENU_ORDER  INTEGER,
  NOTE        VARCHAR2(30)
)
tablespace MSUJIA_SPACE;

comment on table SYS_MENU
  is '菜单表';
comment on column SYS_MENU.NO
  is '菜单编号';
comment on column SYS_MENU.NAME
  is '菜单名称';
comment on column SYS_MENU.MENU_FATHER
  is '上级菜单';
comment on column SYS_MENU.URL
  is '菜单链接';
comment on column SYS_MENU.MENU_LEVEL
  is '菜单层次';
comment on column SYS_MENU.MENU_ORDER
  is '顺序';
comment on column SYS_MENU.NOTE
  is '备注';
alter table SYS_MENU
  add constraint PK_SYS_MENU primary key (NO)
  using index tablespace IDXPRIMARY;

alter table SYS_MENU
  add constraint FK_MENU_SELF foreign key (MENU_FATHER)
  references SYS_MENU (NO);
  
--系统按钮表
create table SYS_BUTTON
(
  NO           INTEGER not null,
  NAME         VARCHAR2(40),
  MENU         VARCHAR2(5),
  URL          VARCHAR2(200),
  BUTTON_ORDER INTEGER,
  NOTE         VARCHAR2(30)
)
tablespace MSUJIA_SPACE;

comment on table SYS_BUTTON
  is '按钮表';
comment on column SYS_BUTTON.NO
  is '编号';
comment on column SYS_BUTTON.NAME
  is '按钮名称';
comment on column SYS_BUTTON.MENU
  is '所属菜单';
comment on column SYS_BUTTON.URL
  is 'ACTION';
comment on column SYS_BUTTON.BUTTON_ORDER
  is '顺序';
comment on column SYS_BUTTON.NOTE
  is '备注';
alter table SYS_BUTTON
  add constraint PK_SYS_BUTTON primary key (NO)
  using index tablespace IDXPRIMARY;

alter table SYS_BUTTON
  add constraint FK_BUT_R_MENU foreign key (MENU)
  references SYS_MENU (NO);
  
--系统参数类型表
create table SYS_PARAM_CATALOG
(
  CATALOG      INTEGER not null,
  CATALOG_NAME VARCHAR2(40) not null,
  DESCRIPTION  VARCHAR2(100)
)
tablespace MSUJIA_SPACE;

comment on table SYS_PARAM_CATALOG
  is '系统参数类别表';
comment on column SYS_PARAM_CATALOG.CATALOG
  is '类别';
comment on column SYS_PARAM_CATALOG.CATALOG_NAME
  is '类别名';
comment on column SYS_PARAM_CATALOG.DESCRIPTION
  is '描述';
alter table SYS_PARAM_CATALOG
  add constraint PK_PRM_CATALOG primary key (CATALOG)
  using index tablespace IDXPRIMARY;

--系统参数表
create table SYS_PARAM
(
  LOGIC_ID    VARCHAR2(36) not null,
  CATALOG     INTEGER not null,
  PARAM_NAME  VARCHAR2(80) not null,
  PARAM_VALUE VARCHAR2(256),
  STATEMENT   VARCHAR2(100),
  DESCRIPTION VARCHAR2(100)
)
tablespace MSUJIA_SPACE;

comment on table SYS_PARAM
  is '系统参数表';
comment on column SYS_PARAM.LOGIC_ID
  is '逻辑主键';
comment on column SYS_PARAM.CATALOG
  is '参数类别';
comment on column SYS_PARAM.PARAM_NAME
  is '参数名';
comment on column SYS_PARAM.PARAM_VALUE
  is '参数值';
comment on column SYS_PARAM.STATEMENT
  is '参数说明';
comment on column SYS_PARAM.DESCRIPTION
  is '描述';
alter table SYS_PARAM
  add constraint PK_SYS_PARAM primary key (LOGIC_ID)
  using index tablespace IDXPRIMARY;
alter table SYS_PARAM
  add constraint FK_PRM_R_CLG foreign key (CATALOG)
  references SYS_PARAM_CATALOG (CATALOG);
alter table SYS_PARAM
  add constraint UK_SYSP_NAME unique (PARAM_NAME);



----------------------------
----------------------------

--系统审核表
create table SYS_AUDIT
(
  USERID     VARCHAR2(20) not null,
  USERNAME   VARCHAR2(20) not null,
  AUDIT_DATE CHAR(19) not null,
  MODULE     VARCHAR2(128) not null,
  OPERATE    VARCHAR2(60) not null,
  TYPES      VARCHAR2(256) not null,
  PARAMS     CLOB not null,
  OLD_PARAMS CLOB not null
)
tablespace MSUJIA_SPACE;

comment on table SYS_AUDIT
  is '操作审核表';
-- Add comments to the columns 
comment on column SYS_AUDIT.USERID
  is '操作人ID';
comment on column SYS_AUDIT.USERNAME
  is '操作人名';
comment on column SYS_AUDIT.AUDIT_DATE
  is '时间';
comment on column SYS_AUDIT.MODULE
  is '模块';
comment on column SYS_AUDIT.OPERATE
  is '操作';
comment on column SYS_AUDIT.TYPES
  is '参数类型';
comment on column SYS_AUDIT.PARAMS
  is '参数';
comment on column SYS_AUDIT.OLD_PARAMS
  is '旧参数';
  alter table SYS_AUDIT
  add constraint PK_SYS_AUDIT primary key (USERID, AUDIT_DATE)
  using index tablespace IDXPRIMARY;
alter table SYS_AUDIT
  add constraint FK_AUDI_R_MODU foreign key (MODULE)
  references SYS_MODULE (NO);
alter table SYS_AUDIT
  add constraint FK_AUDI_R_OPER foreign key (OPERATE)
  references SYS_OPERATE (NO);


--add by yangluo since 20140810
--系统角色按钮权限表
create table SYS_CONF_ROLE_BUTTON
(
  ROLENO   INTEGER not null,
  BUTTONNO INTEGER not null
)
tablespace MSUJIA_SPACE;
comment on table SYS_CONF_ROLE_BUTTON
  is '角色按钮权限表';
comment on column SYS_CONF_ROLE_BUTTON.ROLENO
  is '角色编号';
comment on column SYS_CONF_ROLE_BUTTON.BUTTONNO
  is '按钮编号';
alter table SYS_CONF_ROLE_BUTTON
  add constraint PK_CON_ROLE_BUT primary key (ROLENO, BUTTONNO)
  using index tablespace IDXPRIMARY;
alter table SYS_CONF_ROLE_BUTTON
  add constraint FK_CONRB_R_BUT foreign key (BUTTONNO)
  references SYS_BUTTON (NO);
alter table SYS_CONF_ROLE_BUTTON
  add constraint FK_CONRB_R_ROLE foreign key (ROLENO)
  references SYS_ROLE (NO);

  
  
--系统角色菜单权限表
create table SYS_CONF_ROLE_MENU
(
  ROLENO INTEGER not null,
  MENUNO VARCHAR2(5) not null
)
tablespace MSUJIA_SPACE;
comment on table SYS_CONF_ROLE_MENU
  is '角色菜单权限表';
comment on column SYS_CONF_ROLE_MENU.ROLENO
  is '角色编号';
comment on column SYS_CONF_ROLE_MENU.MENUNO
  is '菜单编号';
alter table SYS_CONF_ROLE_MENU
  add constraint PK_CON_ROLE_MENU primary key (ROLENO, MENUNO)
  using index tablespace IDXPRIMARY;
alter table SYS_CONF_ROLE_MENU
  add constraint FK_CONRM_R_MENU foreign key (MENUNO)
  references SYS_MENU (NO);
alter table SYS_CONF_ROLE_MENU
  add constraint FK_CONRM_R_ROLE foreign key (ROLENO)
  references SYS_ROLE (NO);
commit;  
------------------------------------


--门店类别信息表
create table STORE_CLASSIFICATION_INFO
(
  ID 			VARCHAR2(36) NOT NULL,
  CL_LEVEL		INTEGER NOT NULL,
  CL_NAME   	VARCHAR2(36) NOT NULL,
  PIC_LINK      VARCHAR2(128)
)
tablespace MSUJIA_SPACE;
comment on table STORE_CLASSIFICATION_INFO
  is '门店类别信息表';
comment on column STORE_CLASSIFICATION_INFO.ID
  is '类别ID';
comment on column STORE_CLASSIFICATION_INFO.CL_LEVEL
  is '类别级别';
comment on column STORE_CLASSIFICATION_INFO.CL_NAME
  is '类别名称'; 
  comment on column STORE_CLASSIFICATION_INFO.PIC_LINK
  is '图片链接';
alter table STORE_CLASSIFICATION_INFO
	add constraint PK_STORE_CLASSIFICATION_INFO primary key(ID)
	using index tablespace IDXPRIMARY; 

--门店表
create table STORE_INFO
(
 ID               	VARCHAR2(36)  not null,
 STORE_NO      					VARCHAR2(64)  not null,
 STORE_NAME      					VARCHAR2(64)  not null,
 PHONE        			VARCHAR2(20)  not null,
 ADDRESS        		VARCHAR2(200) not null,
 SEC_CLASSIFICATION_1  VARCHAR2(36) not null,
 SEC_CLASSIFICATION_2  VARCHAR2(36),
 SEC_CLASSIFICATION_3  VARCHAR2(36),
 PASSWD              VARCHAR2(50) not null,
 BUSINESS_HOURS              VARCHAR2(128) not null,
 SERVICE_RADIUS          VARCHAR2(36) not null,
 DELIVERY_AREA            VARCHAR2(128) not null,
 DELIVERY_CHARGES		INTEGER not null,
 FLAG_FALL_PRICE      INTEGER not null,
 DELIVERY_TIME      INTEGER not null,
 ON_LINE            INTEGER not null
)
tablespace MSUJIA_SPACE;
comment on table STORE_INFO
  is '门店信息表';
comment on column STORE_INFO.ID
  is '门店ID';
comment on column STORE_INFO.STORE_NO
  is '门店编号';
comment on column STORE_INFO.STORE_NAME
  is '门店名称';
comment on column STORE_INFO.PHONE
  is '门店电话';
comment on column STORE_INFO.ADDRESS
  is '门店地址';  
comment on column STORE_INFO.SEC_CLASSIFICATION_1
  is '门店分类1';
comment on column STORE_INFO.SEC_CLASSIFICATION_2
  is '门店分类2';
comment on column STORE_INFO.SEC_CLASSIFICATION_3
  is '门店分类3';
comment on column STORE_INFO.PASSWD
  is '门店密码';
comment on column STORE_INFO.BUSINESS_HOURS
  is '营业时间';
comment on column STORE_INFO.SERVICE_RADIUS
  is '服务半径';
comment on column STORE_INFO.DELIVERY_AREA
  is '配送区域';
comment on column STORE_INFO.DELIVERY_CHARGES
  is '配送费/元';
comment on column STORE_INFO.FLAG_FALL_PRICE
  is '起送价/元';
comment on column STORE_INFO.DELIVERY_TIME
  is '送达(分钟)';
comment on column STORE_INFO.ON_LINE
  is '是否显示';
alter table STORE_INFO
	add constraint PK_STORE_INFO primary key(ID)
	using index tablespace IDXPRIMARY; 
alter table STORE_INFO
  add constraint FK_STORE_SEC_CL_1 foreign key (SEC_CLASSIFICATION_1)
  references STORE_CLASSIFICATION_INFO (ID);
alter table STORE_INFO
  add constraint FK_STORE_SEC_CL_2 foreign key (SEC_CLASSIFICATION_2)
  references STORE_CLASSIFICATION_INFO (ID);
alter table STORE_INFO
  add constraint FK_STORE_SEC_CL_3 foreign key (SEC_CLASSIFICATION_3)
  references STORE_CLASSIFICATION_INFO (ID);
alter table STORE_INFO
  add constraint UK_STORE_NO unique (STORE_NO);




--门店商品分类信息表
create table STORE_COMM_CLASSIFICATION_INFO
(		
  ID	  			VARCHAR2(36) NOT NULL,
  STORE_ID 			VARCHAR2(36) NOT NULL,
  COMM_CLASSIFICATION			VARCHAR2(36) NOT NULL
)
tablespace MSUJIA_SPACE;
comment on table STORE_COMM_CLASSIFICATION_INFO
  is '门店商品分类信息表';
comment on column STORE_COMM_CLASSIFICATION_INFO.ID
  is '类别ID';
comment on column STORE_COMM_CLASSIFICATION_INFO.STORE_ID
  is '所属门店'; 
comment on column STORE_COMM_CLASSIFICATION_INFO.COMM_CLASSIFICATION
  is '类别名称'; 
alter table STORE_COMM_CLASSIFICATION_INFO
	add constraint PK_STORE_COMM_C_INFO primary key(ID)
	using index tablespace IDXPRIMARY; 
alter table STORE_COMM_CLASSIFICATION_INFO
  add constraint FK_COMM_C_R_STORE foreign key (STORE_ID)
  references STORE_INFO (ID);

--门店商品信息表
create table STORE_COMM_INFO
(
  ID 				VARCHAR2(36) NOT NULL,
  NAME	    VARCHAR2(64) NOT NULL,
  STORE_ID		VARCHAR2(36) NOT NULL,
  CLASSIFICATION_ID 		VARCHAR2(36) NOT NULL,
  PRICE		BINARY_DOUBLE NOT NULL,
  PREFERENTIAL_PRICE		BINARY_DOUBLE NOT NULL,
  ONHAND		INTEGER,
  PICTURE_LINK		VARCHAR2(128) NOT NULL,
  ON_SHELVES		INTEGER not null
)
tablespace MSUJIA_SPACE;
comment on table STORE_COMM_INFO
  is '门店商品信息表';
comment on column STORE_COMM_INFO.ID
  is '商品ID';
comment on column STORE_COMM_INFO.STORE_ID
  is '所属门店'; 
comment on column STORE_COMM_INFO.CLASSIFICATION_ID
  is '商品名称'; 
comment on column STORE_COMM_INFO.PRICE
  is '商品原价';
comment on column STORE_COMM_INFO.PREFERENTIAL_PRICE
  is '商品优惠价';
comment on column STORE_COMM_INFO.ONHAND
  is '库存数量（默认1000）';
comment on column STORE_COMM_INFO.PICTURE_LINK
  is '商品图片链接';
comment on column STORE_COMM_INFO.ON_SHELVES
  is '是否上架（0-不上架，1-上架）';
alter table STORE_COMM_INFO
	add constraint PK_STORE_COMM_INFO primary key(ID)
	using index tablespace IDXPRIMARY; 
alter table STORE_COMM_INFO
  add constraint FK_COMM_R_STORE foreign key (STORE_ID)
  references STORE_INFO (ID);
alter table STORE_COMM_INFO
  add constraint FK_COMM_R_COMM_C foreign key (CLASSIFICATION_ID)
  references STORE_COMM_CLASSIFICATION_INFO (ID);
alter table STORE_COMM_INFO
  add constraint UK_STORE_COMM_INFO unique (NAME);
  
  
  
--订单信息表
create table ORDER_INFO
(
 ID               	VARCHAR2(36)  not null,
 ORDER_NO      					VARCHAR2(36)  not null,
 OP_NO      					VARCHAR2(64)  not null,
 ORDER_TOTAL          BINARY_DOUBLE NOT NULL,
 DELIVERY_CHARGES     BINARY_DOUBLE NOT NULL,
 PHONE        			VARCHAR2(20)  not null,
 ADDRESS        		VARCHAR2(128) not null,
 DELIVERY_TIME      INTEGER,
 STATUS             INTEGER not null,
 NOTE               VARCHAR2(128)
)
tablespace MSUJIA_SPACE;
comment on table ORDER_INFO
  is '订单信息表';
comment on column ORDER_INFO.ID
  is '订单ID';
comment on column ORDER_INFO.ORDER_NO
  is '订单编号';
comment on column ORDER_INFO.OP_NO
  is '订单人员';
comment on column ORDER_INFO.ORDER_TOTAL
  is '订单总额';
comment on column ORDER_INFO.DELIVERY_CHARGES
  is '配送费'; 
comment on column ORDER_INFO.PHONE
  is '订单电话';
comment on column ORDER_INFO.ADDRESS
  is '订单地址';  
comment on column ORDER_INFO.DELIVERY_TIME
  is '送达时间';
comment on column ORDER_INFO.STATUS
  is '订单状态：0-未支付, 1-已支付, 2-已确认';
comment on column ORDER_INFO.NOTE
  is '备注';
alter table ORDER_INFO
	add constraint PK_ORDER_INFO primary key(ID)
	using index tablespace IDXPRIMARY; 
alter table ORDER_INFO
  add constraint UK_ORDER_NO unique (ORDER_NO);
  
  
--add by yangluo since 20150423 
--订单详细信息表
create table ORDER_DETAIL_INFO
(
 ORDER_ID               	VARCHAR2(36)  not null,
 COMM_INFO_ID      					VARCHAR2(36)  not null,
 NUM                   INTEGER not null,
 SUB_TOTAL             BINARY_DOUBLE NOT NULL
)
tablespace MSUJIA_SPACE;
comment on table ORDER_DETAIL_INFO
  is '订单信息详细表';
comment on column ORDER_DETAIL_INFO.ORDER_ID
  is '订单ID';
comment on column ORDER_DETAIL_INFO.COMM_INFO_ID
  is '商品ID';
comment on column ORDER_DETAIL_INFO.NUM
  is '个数';
comment on column ORDER_DETAIL_INFO.SUB_TOTAL
  is '小计';
alter table ORDER_DETAIL_INFO
  add constraint PK_ORDER_DETAIL_INFO primary key (ORDER_ID, COMM_INFO_ID)
  using index tablespace IDXPRIMARY;
alter table ORDER_DETAIL_INFO
  add constraint FK_ORDER_R_COMM_INFO foreign key (COMM_INFO_ID)
  references STORE_COMM_INFO (ID);
  
  
  
  
  
  
  
  
  
