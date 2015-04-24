/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/4/23 16:15:03                           */
/*==============================================================*/


drop table if exists MSUJIA.OP_TABLE;

drop table if exists MSUJIA.ORDER_DETAIL_INFO;

drop table if exists MSUJIA.ORDER_INFO;

drop table if exists MSUJIA.STORE_CLASSIFICATION_INFO;

drop table if exists MSUJIA.STORE_COMM_CLASSIFICATION_INFO;

drop table if exists MSUJIA.STORE_COMM_INFO;

drop table if exists MSUJIA.STORE_INFO;

drop table if exists MSUJIA.SYS_AUDIT;

drop table if exists MSUJIA.SYS_BUTTON;

drop table if exists MSUJIA.SYS_CONF_ROLE_BUTTON;

drop table if exists MSUJIA.SYS_CONF_ROLE_MENU;

drop index IDX_LOG_DATE on MSUJIA.SYS_LOG;

drop table if exists MSUJIA.SYS_LOG;

drop table if exists MSUJIA.SYS_MENU;

drop table if exists MSUJIA.SYS_MODULE;

drop table if exists MSUJIA.SYS_OPERATE;

drop table if exists MSUJIA.SYS_PARAM;

drop table if exists MSUJIA.SYS_PARAM_CATALOG;

drop table if exists MSUJIA.SYS_ROLE;


/*==============================================================*/
/* Table: OP_TABLE                                              */
/*==============================================================*/
create table MSUJIA.OP_TABLE
(
   NO                   varchar(64) not null,
   PASSWD               varchar(50) not null,
   NAME                 varchar(20) not null,
   STATUS               int not null,
   ONLINE_FLAG          int not null,
   ROLE                 int not null,
   PHONE                varchar(20),
   MOBILE               varchar(20),
   EMAIL                varchar(40),
   PHOTO                varchar(50),
   LOGIN_IP             varchar(20),
   LOGIN_TIME           varchar(20),
   PASSWD_EXPIRATION    varchar(10),
   PASSWD_ERROR         int not null default 0,
   SIGN_FLAG            int not null default 0,
   STORE_ID             varchar(36),
   primary key (NO)
);

/*==============================================================*/
/* Table: ORDER_DETAIL_INFO                                     */
/*==============================================================*/
create table MSUJIA.ORDER_DETAIL_INFO
(
   ORDER_ID             varchar(36) not null comment '订单ID',
   COMM_INFO_ID         varchar(36) not null comment '商品ID',
   NUM                  int not null comment '个数',
   SUB_TOTAL            double not null comment '小计',
   primary key (ORDER_ID, COMM_INFO_ID)
);

alter table MSUJIA.ORDER_DETAIL_INFO comment '订单信息详细表';

/*==============================================================*/
/* Table: ORDER_INFO                                            */
/*==============================================================*/
create table MSUJIA.ORDER_INFO
(
   ID                   varchar(36) not null comment '订单ID',
   ORDER_NO             varchar(36) not null comment '订单编号',
   OP_NO                varchar(64) not null comment '订单人员',
   ORDER_TOTAL          double not null comment '订单总额',
   DELIVERY_CHARGES     double not null comment '配送费',
   PHONE                varchar(20) not null comment '订单电话',
   ADDRESS              varchar(128) not null comment '订单地址',
   DELIVERY_TIME        int comment '送达时间',
   STATUS               int not null comment '订单状态：0-未支付, 1-已支付, 2-已确认',
   NOTE                 varchar(128) comment '备注',
   primary key (ID),
   key UK_ORDER_NO (ORDER_NO)
);

alter table MSUJIA.ORDER_INFO comment '订单信息表';

/*==============================================================*/
/* Table: STORE_CLASSIFICATION_INFO                             */
/*==============================================================*/
create table MSUJIA.STORE_CLASSIFICATION_INFO
(
   ID                   varchar(36) not null comment '类别ID',
   CL_LEVEL             int not null comment '类别级别',
   CL_NAME              varchar(36) not null comment '类别名称',
   PIC_LINK             varchar(128) comment '图片链接',
   primary key (ID)
);

alter table MSUJIA.STORE_CLASSIFICATION_INFO comment '门店类别信息表';

/*==============================================================*/
/* Table: STORE_COMM_CLASSIFICATION_INFO                        */
/*==============================================================*/
create table MSUJIA.STORE_COMM_CLASSIFICATION_INFO
(
   ID                   varchar(36) not null comment '类别ID',
   STORE_ID             varchar(36) not null comment '所属门店',
   COMM_CLASSIFICATION  varchar(36) not null comment '类别名称',
   primary key (ID)
);

alter table MSUJIA.STORE_COMM_CLASSIFICATION_INFO comment '门店商品分类信息表';

/*==============================================================*/
/* Table: STORE_COMM_INFO                                       */
/*==============================================================*/
create table MSUJIA.STORE_COMM_INFO
(
   ID                   varchar(36) not null comment '商品ID',
   NAME                 varchar(64) not null,
   STORE_ID             varchar(36) not null comment '所属门店',
   CLASSIFICATION_ID    varchar(36) not null comment '商品名称',
   PRICE                double not null comment '商品原价',
   PREFERENTIAL_PRICE   double not null comment '商品优惠价',
   ONHAND               int comment '库存数量（默认1000）',
   PICTURE_LINK         varchar(128) not null comment '商品图片链接',
   ON_SHELVES           int not null comment '是否上架（0-不上架，1-上架）',
   primary key (ID),
   key UK_STORE_COMM_INFO (NAME)
);

alter table MSUJIA.STORE_COMM_INFO comment '门店商品信息表';

/*==============================================================*/
/* Table: STORE_INFO                                            */
/*==============================================================*/
create table MSUJIA.STORE_INFO
(
   ID                   varchar(36) not null comment '门店ID',
   STORE_NO             varchar(64) not null comment '门店编号',
   STORE_NAME           varchar(64) not null comment '门店名称',
   PHONE                varchar(20) not null comment '门店电话',
   ADDRESS              varchar(200) not null comment '门店地址',
   SEC_CLASSIFICATION_1 varchar(36) not null comment '门店分类1',
   SEC_CLASSIFICATION_2 varchar(36) comment '门店分类2',
   SEC_CLASSIFICATION_3 varchar(36) comment '门店分类3',
   PASSWD               varchar(50) not null comment '门店密码',
   BUSINESS_HOURS       varchar(128) not null comment '营业时间',
   SERVICE_RADIUS       varchar(36) not null comment '服务半径',
   DELIVERY_AREA        varchar(128) not null comment '配送区域',
   DELIVERY_CHARGES     int not null comment '配送费/元',
   FLAG_FALL_PRICE      int not null comment '起送价/元',
   DELIVERY_TIME        int not null comment '送达(分钟)',
   ON_LINE              int not null comment '是否显示',
   primary key (ID),
   key UK_STORE_NO (STORE_NO)
);

alter table MSUJIA.STORE_INFO comment '门店信息表';

/*==============================================================*/
/* Table: SYS_AUDIT                                             */
/*==============================================================*/
create table MSUJIA.SYS_AUDIT
(
   USERID               varchar(20) not null comment '操作人ID',
   USERNAME             varchar(20) not null comment '操作人名',
   AUDIT_DATE           char(19) not null comment '时间',
   MODULE               varchar(128) not null comment '模块',
   OPERATE              varchar(60) not null comment '操作',
   TYPES                varchar(256) not null comment '参数类型',
   PARAMS               text not null comment '参数',
   OLD_PARAMS           text not null comment '旧参数',
   primary key (USERID, AUDIT_DATE)
);

alter table MSUJIA.SYS_AUDIT comment '操作审核表';

/*==============================================================*/
/* Table: SYS_BUTTON                                            */
/*==============================================================*/
create table MSUJIA.SYS_BUTTON
(
   NO                   int not null comment '编号',
   NAME                 varchar(40) comment '按钮名称',
   MENU                 varchar(5) comment '所属菜单',
   URL                  varchar(200) comment 'ACTION',
   BUTTON_ORDER         int comment '顺序',
   NOTE                 varchar(30) comment '备注',
   primary key (NO)
);

alter table MSUJIA.SYS_BUTTON comment '按钮表';

/*==============================================================*/
/* Table: SYS_CONF_ROLE_BUTTON                                  */
/*==============================================================*/
create table MSUJIA.SYS_CONF_ROLE_BUTTON
(
   ROLENO               int not null comment '角色编号',
   BUTTONNO             int not null comment '按钮编号',
   primary key (ROLENO, BUTTONNO)
);

alter table MSUJIA.SYS_CONF_ROLE_BUTTON comment '角色按钮权限表';

/*==============================================================*/
/* Table: SYS_CONF_ROLE_MENU                                    */
/*==============================================================*/
create table MSUJIA.SYS_CONF_ROLE_MENU
(
   ROLENO               int not null comment '角色编号',
   MENUNO               varchar(5) not null comment '菜单编号',
   primary key (ROLENO, MENUNO)
);

alter table MSUJIA.SYS_CONF_ROLE_MENU comment '角色菜单权限表';

/*==============================================================*/
/* Table: SYS_LOG                                               */
/*==============================================================*/
create table MSUJIA.SYS_LOG
(
   USERID               varchar(20) not null,
   USERNAME             varchar(20) not null,
   LOG_DATE             varchar(30) not null,
   MODULE               varchar(128) not null,
   OPERATE              varchar(60) not null,
   XML                  text,
   XML_OLD              text,
   NOTE                 varchar(20),
   primary key (USERID, LOG_DATE)
);

/*==============================================================*/
/* Index: IDX_LOG_DATE                                          */
/*==============================================================*/
create index IDX_LOG_DATE on MSUJIA.SYS_LOG
(
   LOG_DATE
);

/*==============================================================*/
/* Table: SYS_MENU                                              */
/*==============================================================*/
create table MSUJIA.SYS_MENU
(
   NO                   varchar(5) not null comment '菜单编号',
   NAME                 varchar(40) not null comment '菜单名称',
   MENU_FATHER          varchar(5) comment '上级菜单',
   URL                  varchar(200) comment '菜单链接',
   MENU_LEVEL           int comment '菜单层次',
   MENU_ORDER           int comment '顺序',
   NOTE                 varchar(30) comment '备注',
   primary key (NO)
);

alter table MSUJIA.SYS_MENU comment '菜单表';

/*==============================================================*/
/* Table: SYS_MODULE                                            */
/*==============================================================*/
create table MSUJIA.SYS_MODULE
(
   NO                   varchar(100) not null comment '编号',
   NAME                 varchar(60) not null comment '名称',
   primary key (NO)
);

alter table MSUJIA.SYS_MODULE comment '模块表';

/*==============================================================*/
/* Table: SYS_OPERATE                                           */
/*==============================================================*/
create table MSUJIA.SYS_OPERATE
(
   NO                   varchar(60) not null comment '编号',
   MODULE_NO            varchar(100) not null comment '模块编号',
   NAME                 varchar(60) not null comment '名称',
   primary key (NO)
);

alter table MSUJIA.SYS_OPERATE comment '操作表';

/*==============================================================*/
/* Table: SYS_PARAM                                             */
/*==============================================================*/
create table MSUJIA.SYS_PARAM
(
   LOGIC_ID             varchar(36) not null comment '逻辑主键',
   CATALOG              int not null comment '参数类别',
   PARAM_NAME           varchar(80) not null comment '参数名',
   PARAM_VALUE          varchar(256) comment '参数值',
   STATEMENT            varchar(100) comment '参数说明',
   DESCRIPTION          varchar(100) comment '描述',
   primary key (LOGIC_ID),
   key UK_SYSP_NAME (PARAM_NAME)
);

alter table MSUJIA.SYS_PARAM comment '系统参数表';

/*==============================================================*/
/* Table: SYS_PARAM_CATALOG                                     */
/*==============================================================*/
create table MSUJIA.SYS_PARAM_CATALOG
(
   CATALOG              int not null comment '类别',
   CATALOG_NAME         varchar(40) not null comment '类别名',
   DESCRIPTION          varchar(100) comment '描述',
   primary key (CATALOG)
);

alter table MSUJIA.SYS_PARAM_CATALOG comment '系统参数类别表';

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table MSUJIA.SYS_ROLE
(
   NO                   int not null comment '编号',
   NAME                 varchar(50) not null comment '名称',
   CATALOG              int not null comment '角色类型：1－超级操作员；2－预设操作员；3－其他操作员',
   ORG_TYPE             int comment '角色所属机构类型',
   NOTE                 varchar(200) comment '备注',
   primary key (NO)
);

alter table MSUJIA.SYS_ROLE comment '角色表';

alter table MSUJIA.OP_TABLE add constraint FK_OP_R_ROLE foreign key (ROLE)
      references MSUJIA.SYS_ROLE (NO) on delete restrict on update restrict;

alter table MSUJIA.ORDER_DETAIL_INFO add constraint FK_ORDER_R_COMM_INFO foreign key (COMM_INFO_ID)
      references MSUJIA.STORE_COMM_INFO (ID) on delete restrict on update restrict;

alter table MSUJIA.STORE_COMM_CLASSIFICATION_INFO add constraint FK_COMM_C_R_STORE foreign key (STORE_ID)
      references MSUJIA.STORE_INFO (ID) on delete restrict on update restrict;

alter table MSUJIA.STORE_COMM_INFO add constraint FK_COMM_R_COMM_C foreign key (CLASSIFICATION_ID)
      references MSUJIA.STORE_COMM_CLASSIFICATION_INFO (ID) on delete restrict on update restrict;

alter table MSUJIA.STORE_COMM_INFO add constraint FK_COMM_R_STORE foreign key (STORE_ID)
      references MSUJIA.STORE_INFO (ID) on delete restrict on update restrict;

alter table MSUJIA.STORE_INFO add constraint FK_STORE_SEC_CL_1 foreign key (SEC_CLASSIFICATION_1)
      references MSUJIA.STORE_CLASSIFICATION_INFO (ID) on delete restrict on update restrict;

alter table MSUJIA.STORE_INFO add constraint FK_STORE_SEC_CL_2 foreign key (SEC_CLASSIFICATION_2)
      references MSUJIA.STORE_CLASSIFICATION_INFO (ID) on delete restrict on update restrict;

alter table MSUJIA.STORE_INFO add constraint FK_STORE_SEC_CL_3 foreign key (SEC_CLASSIFICATION_3)
      references MSUJIA.STORE_CLASSIFICATION_INFO (ID) on delete restrict on update restrict;

alter table MSUJIA.SYS_AUDIT add constraint FK_AUDI_R_MODU foreign key (MODULE)
      references MSUJIA.SYS_MODULE (NO) on delete restrict on update restrict;

alter table MSUJIA.SYS_AUDIT add constraint FK_AUDI_R_OPER foreign key (OPERATE)
      references MSUJIA.SYS_OPERATE (NO) on delete restrict on update restrict;

alter table MSUJIA.SYS_BUTTON add constraint FK_BUT_R_MENU foreign key (MENU)
      references MSUJIA.SYS_MENU (NO) on delete restrict on update restrict;

alter table MSUJIA.SYS_CONF_ROLE_BUTTON add constraint FK_CONRB_R_BUT foreign key (BUTTONNO)
      references MSUJIA.SYS_BUTTON (NO) on delete restrict on update restrict;

alter table MSUJIA.SYS_CONF_ROLE_BUTTON add constraint FK_CONRB_R_ROLE foreign key (ROLENO)
      references MSUJIA.SYS_ROLE (NO) on delete restrict on update restrict;

alter table MSUJIA.SYS_CONF_ROLE_MENU add constraint FK_CONRM_R_MENU foreign key (MENUNO)
      references MSUJIA.SYS_MENU (NO) on delete restrict on update restrict;

alter table MSUJIA.SYS_CONF_ROLE_MENU add constraint FK_CONRM_R_ROLE foreign key (ROLENO)
      references MSUJIA.SYS_ROLE (NO) on delete restrict on update restrict;

alter table MSUJIA.SYS_LOG add constraint FK_LOG_R_MODU foreign key (MODULE)
      references MSUJIA.SYS_MODULE (NO) on delete restrict on update restrict;

alter table MSUJIA.SYS_LOG add constraint FK_LOG_R_OPER foreign key (OPERATE)
      references MSUJIA.SYS_OPERATE (NO) on delete restrict on update restrict;

alter table MSUJIA.SYS_MENU add constraint FK_MENU_SELF foreign key (MENU_FATHER)
      references MSUJIA.SYS_MENU (NO) on delete restrict on update restrict;

alter table MSUJIA.SYS_OPERATE add constraint FK_OPER_R_MODU foreign key (MODULE_NO)
      references MSUJIA.SYS_MODULE (NO) on delete restrict on update restrict;

alter table MSUJIA.SYS_PARAM add constraint FK_PRM_R_CLG foreign key (CATALOG)
      references MSUJIA.SYS_PARAM_CATALOG (CATALOG) on delete restrict on update restrict;

