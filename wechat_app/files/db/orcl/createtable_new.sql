--ϵͳ��ɫ��
create table SYS_ROLE
(
  NO       INTEGER not null,
  NAME     VARCHAR2(50) not null,
  CATALOG  INTEGER not null,
  ORG_TYPE INTEGER,
  NOTE     VARCHAR2(200)
)
tablespace WECHAT_SPACE;

comment on table SYS_ROLE
  is '��ɫ��';
comment on column SYS_ROLE.NO
  is '���';
comment on column SYS_ROLE.NAME
  is '����';
comment on column SYS_ROLE.CATALOG
  is '��ɫ���ͣ�1����������Ա��2��Ԥ�����Ա��3����������Ա';
comment on column SYS_ROLE.ORG_TYPE
  is '��ɫ������������';
comment on column SYS_ROLE.NOTE
  is '��ע';
alter table SYS_ROLE
  add constraint PK_SYS_ROLE primary key (NO)
  using index tablespace IDXPRIMARY;
--ϵͳ�û���
create table OP_TABLE
(
  NO                VARCHAR2(20) not null,
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
  DEFAULT_PAID		VARCHAR2(32)
)
tablespace WECHAT_SPACE;
alter table OP_TABLE
  add constraint PK_OP primary key (NO)
  using index tablespace IDXPRIMARY;

alter table OP_TABLE
  add constraint FK_OP_R_ROLE foreign key (ROLE)
  references SYS_ROLE (NO);

--ϵͳģ���
create table SYS_MODULE
(
  NO   VARCHAR2(100) not null,
  NAME VARCHAR2(60) not null
)
tablespace WECHAT_SPACE;
comment on table SYS_MODULE
  is 'ģ���';
comment on column SYS_MODULE.NO
  is '���';
comment on column SYS_MODULE.NAME
  is '����';
alter table SYS_MODULE
  add constraint PK_SYS_MODULE primary key (NO)
  using index tablespace IDXPRIMARY;

  
--ϵͳ������
create table SYS_OPERATE
(
  NO        VARCHAR2(60) not null,
  MODULE_NO VARCHAR2(100) not null,
  NAME      VARCHAR2(60) not null
)
tablespace WECHAT_SPACE;

comment on table SYS_OPERATE
  is '������';
comment on column SYS_OPERATE.NO
  is '���';
comment on column SYS_OPERATE.MODULE_NO
  is 'ģ����';
comment on column SYS_OPERATE.NAME
  is '����';
alter table SYS_OPERATE
  add constraint PK_SYS_OPERATE primary key (NO)
  using index tablespace IDXPRIMARY;

alter table SYS_OPERATE
  add constraint FK_OPER_R_MODU foreign key (MODULE_NO)
  references SYS_MODULE (NO);
  

  
--ϵͳ��־��
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
  
--ϵͳ�˵���
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
tablespace WECHAT_SPACE;

comment on table SYS_MENU
  is '�˵���';
comment on column SYS_MENU.NO
  is '�˵����';
comment on column SYS_MENU.NAME
  is '�˵�����';
comment on column SYS_MENU.MENU_FATHER
  is '�ϼ��˵�';
comment on column SYS_MENU.URL
  is '�˵�����';
comment on column SYS_MENU.MENU_LEVEL
  is '�˵����';
comment on column SYS_MENU.MENU_ORDER
  is '˳��';
comment on column SYS_MENU.NOTE
  is '��ע';
alter table SYS_MENU
  add constraint PK_SYS_MENU primary key (NO)
  using index tablespace IDXPRIMARY;

alter table SYS_MENU
  add constraint FK_MENU_SELF foreign key (MENU_FATHER)
  references SYS_MENU (NO);
  
--ϵͳ��ť��
create table SYS_BUTTON
(
  NO           INTEGER not null,
  NAME         VARCHAR2(40),
  MENU         VARCHAR2(5),
  URL          VARCHAR2(200),
  BUTTON_ORDER INTEGER,
  NOTE         VARCHAR2(30)
)
tablespace WECHAT_SPACE;

comment on table SYS_BUTTON
  is '��ť��';
comment on column SYS_BUTTON.NO
  is '���';
comment on column SYS_BUTTON.NAME
  is '��ť����';
comment on column SYS_BUTTON.MENU
  is '�����˵�';
comment on column SYS_BUTTON.URL
  is 'ACTION';
comment on column SYS_BUTTON.BUTTON_ORDER
  is '˳��';
comment on column SYS_BUTTON.NOTE
  is '��ע';
alter table SYS_BUTTON
  add constraint PK_SYS_BUTTON primary key (NO)
  using index tablespace IDXPRIMARY;

alter table SYS_BUTTON
  add constraint FK_BUT_R_MENU foreign key (MENU)
  references SYS_MENU (NO);
  
--ϵͳ�������ͱ�
create table SYS_PARAM_CATALOG
(
  CATALOG      INTEGER not null,
  CATALOG_NAME VARCHAR2(40) not null,
  DESCRIPTION  VARCHAR2(100)
)
tablespace WECHAT_SPACE;

comment on table SYS_PARAM_CATALOG
  is 'ϵͳ��������';
comment on column SYS_PARAM_CATALOG.CATALOG
  is '���';
comment on column SYS_PARAM_CATALOG.CATALOG_NAME
  is '�����';
comment on column SYS_PARAM_CATALOG.DESCRIPTION
  is '����';
alter table SYS_PARAM_CATALOG
  add constraint PK_PRM_CATALOG primary key (CATALOG)
  using index tablespace IDXPRIMARY;

--ϵͳ������
create table SYS_PARAM
(
  LOGIC_ID    VARCHAR2(36) not null,
  CATALOG     INTEGER not null,
  PARAM_NAME  VARCHAR2(80) not null,
  PARAM_VALUE VARCHAR2(256),
  STATEMENT   VARCHAR2(100),
  DESCRIPTION VARCHAR2(100)
)
tablespace WECHAT_SPACE;

comment on table SYS_PARAM
  is 'ϵͳ������';
comment on column SYS_PARAM.LOGIC_ID
  is '�߼�����';
comment on column SYS_PARAM.CATALOG
  is '�������';
comment on column SYS_PARAM.PARAM_NAME
  is '������';
comment on column SYS_PARAM.PARAM_VALUE
  is '����ֵ';
comment on column SYS_PARAM.STATEMENT
  is '����˵��';
comment on column SYS_PARAM.DESCRIPTION
  is '����';
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

--ϵͳ��˱�
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
tablespace WECHAT_SPACE;

comment on table SYS_AUDIT
  is '������˱�';
-- Add comments to the columns 
comment on column SYS_AUDIT.USERID
  is '������ID';
comment on column SYS_AUDIT.USERNAME
  is '��������';
comment on column SYS_AUDIT.AUDIT_DATE
  is 'ʱ��';
comment on column SYS_AUDIT.MODULE
  is 'ģ��';
comment on column SYS_AUDIT.OPERATE
  is '����';
comment on column SYS_AUDIT.TYPES
  is '��������';
comment on column SYS_AUDIT.PARAMS
  is '����';
comment on column SYS_AUDIT.OLD_PARAMS
  is '�ɲ���';
  alter table SYS_AUDIT
  add constraint PK_SYS_AUDIT primary key (USERID, AUDIT_DATE)
  using index tablespace IDXPRIMARY;
alter table SYS_AUDIT
  add constraint FK_AUDI_R_MODU foreign key (MODULE)
  references SYS_MODULE (NO);
alter table SYS_AUDIT
  add constraint FK_AUDI_R_OPER foreign key (OPERATE)
  references SYS_OPERATE (NO);
  
------------------------------------

--���ںű�
create table PUBLIC_ACCOUNT
(
 ID               	VARCHAR2(36)  not null,
 WECHAT_NAME      	VARCHAR2(64)  not null,
 WECHAT_ID        	VARCHAR2(128) not null,
 WECHAT_TYPE        INTEGER     not null,
 URL                VARCHAR2(200),
 TOKEN              VARCHAR2(36),
 APPID              VARCHAR2(128),
 APPSECRET          VARCHAR2(128),
 CREATOR            VARCHAR2(64),
 ACCESS_TOKEN		VARCHAR2(200),
 LOGO_LOCATION      VARCHAR2(200)
)
tablespace  WECHAT_SPACE;
comment on table PUBLIC_ACCOUNT
  is '���ںű�';
comment on column PUBLIC_ACCOUNT.ID
  is '���ں�ԭʼ���';
comment on column PUBLIC_ACCOUNT.WECHAT_NAME
  is '���ں�����';
comment on column PUBLIC_ACCOUNT.WECHAT_ID
  is '΢�ź�';
comment on column PUBLIC_ACCOUNT.WECHAT_TYPE
  is '΢�ź�����';  
comment on column PUBLIC_ACCOUNT.URL
  is '����΢�ŷ����������URL';
comment on column PUBLIC_ACCOUNT.TOKEN
  is '��֤����';
comment on column PUBLIC_ACCOUNT.APPID
  is '����΢�ŷ����������URL';
comment on column PUBLIC_ACCOUNT.APPSECRET
  is '��֤����';
comment on column PUBLIC_ACCOUNT.CREATOR
  is '������';
comment on column PUBLIC_ACCOUNT.ACCESS_TOKEN
  is '��������';
comment on column PUBLIC_ACCOUNT.LOGO_LOCATION
  is '���ں�LOGOͼ��Ĵ��λ��';
alter table PUBLIC_ACCOUNT
	add constraint PK_ACCOUNT primary key(ID)
	using index tablespace IDXPRIMARY; 


--�û����ں�Ȩ�ޱ�
create table USER_PUBACCOUNT
(
  USER_ID 			VARCHAR2(20) NOT NULL,
  PA_ID   			VARCHAR2(36) NOT NULL
)
tablespace WECHAT_SPACE;
comment on table USER_PUBACCOUNT
  is '�û����ں�Ȩ�ޱ�';
comment on column USER_PUBACCOUNT.USER_ID
  is '�û�ID';
comment on column USER_PUBACCOUNT.PA_ID
  is '���ں�ID'; 
alter table USER_PUBACCOUNT
  add constraint PK_USER_PA primary key (USER_ID, PA_ID);
alter table USER_PUBACCOUNT
  add constraint FK_USERID foreign key (USER_ID)
  references OP_TABLE (NO);
alter table USER_PUBACCOUNT
  add constraint FK_PAID foreign key (PA_ID)
  references PUBLIC_ACCOUNT (ID);

--΢�Ų˵���
create table WECHAT_MENU
(		
  ID	  			VARCHAR2(36) NOT NULL,
  VERSION 			VARCHAR2(64) NOT NULL,
  WECHAT_ID			VARCHAR2(128) NOT NULL,
  CREATE_TIME		VARCHAR2(20) NOT NULL,
  LAST_MODIFIED_TIME VARCHAR2(20) NOT NULL
)
tablespace WECHAT_SPACE;
alter table WECHAT_MENU
  add constraint PK_WECHAT_MENU primary key (ID);
alter table WECHAT_MENU
  add constraint FK_MENU_PAID foreign key (WECHAT_ID)
  references PUBLIC_ACCOUNT (ID);

--΢��ҵ���ܱ�
create table WECHAT_FUNCTION
(
  ID 				VARCHAR2(36) NOT NULL,
  WECHATLET_NAME	VARCHAR2(36) NOT NULL,
  DESCRIPTION		VARCHAR2(256),
  CLASSNAME 		VARCHAR2(128) NOT NULL
)
tablespace WECHAT_SPACE;
alter table WECHAT_FUNCTION
  add constraint PK_WECHAT_FUNC primary key (ID);
alter table WECHAT_FUNCTION
  add constraint UK_FUNCTION_NAME unique (WECHATLET_NAME);
  
--΢�Ų˵����
create table WECHAT_MENU_ITEM
(
  ID				VARCHAR2(36) NOT NULL,
  MENU_ID			VARCHAR2(36) NOT NULL,
  WECHAT_ID			VARCHAR2(128) NOT NULL,
  NAME				VARCHAR2(20) NOT NULL,
  MENU_LEVEL		VARCHAR2(2)  NOT NULL,
  WECHATLET_ID		VARCHAR2(36),
  PARENT_ID			VARCHAR2(36),
  MENU_ORDER		VARCHAR2(2) NOT NULL			
)
tablespace WECHAT_SPACE;
alter table WECHAT_MENU_ITEM
  add constraint PK_MENU_ITEM primary key (ID);
alter table WECHAT_MENU_ITEM
  add constraint FK_MENU_ITEM_PAID foreign key (WECHAT_ID)
  references PUBLIC_ACCOUNT (ID);
alter table WECHAT_MENU_ITEM
  add constraint FK_MENU_ITEM_FUNCID foreign key (WECHATLET_ID)
  references WECHAT_FUNCTION (ID);
  
--���ںŹ���Ȩ�ޱ�
create table PUBACCOUNT_FUNCTION
(
  PA_ID				VARCHAR2(36) NOT NULL,
  FUNC_ID			VARCHAR2(36) NOT NULL
)
tablespace WECHAT_SPACE;
alter table PUBACCOUNT_FUNCTION
  add constraint PK_PA_FUNC primary key (PA_ID, FUNC_ID);
alter table PUBACCOUNT_FUNCTION
  add constraint FK_PA_FUNC_PAID foreign key (PA_ID)
  references PUBLIC_ACCOUNT (ID);
alter table PUBACCOUNT_FUNCTION
  add constraint FK_PA_FUNC_FUNCID foreign key (FUNC_ID)
  references WECHAT_FUNCTION (ID);

--�زı�
create table WECHAT_MEDIA
(
ID                   VARCHAR2(36) not null,
WECHAT_ID            VARCHAR2(36) not null,
MEDIA_NAME			 VARCHAR2(128) not null,
MEDIA_TYPE           VARCHAR2(64) not null,
MEDIA_PATH           VARCHAR2(128) not null,
MEDIA_RESOURCE       INTEGER   not null,
MEDIA_SUFFIX         VARCHAR2(120) not null,
MEDIA_STATE          VARCHAR2(64) not null,
CREATE_DATE			 VARCHAR2(10) NOT NULL,
CREATE_TIME          VARCHAR2(8) not null,
CREATOR              VARCHAR2(20) not null,
DESCRIBE             VARCHAR2(256)
)
tablespace WECHAT_SPACE; 
alter table WECHAT_MEDIA
	add constraint PK_ID primary key(ID)
	using index tablespace IDXPRIMARY;
comment on column WECHAT_MEDIA.WECHAT_ID
	is '���ں�ID';
comment on column WECHAT_MEDIA.MEDIA_TYPE
	is '��Դ����';		
comment on column WECHAT_MEDIA.MEDIA_PATH
	is '·��';
comment on column WECHAT_MEDIA.MEDIA_RESOURCE
	is '��Դ��Դ';	
comment on column WECHAT_MEDIA.MEDIA_SUFFIX
	is '�ļ�����';	
comment on column WECHAT_MEDIA.MEDIA_STATE
	is '״̬';	
comment on column WECHAT_MEDIA.CREATE_TIME
	is '�ϴ�ʱ��';	
comment on column WECHAT_MEDIA.CREATOR
	is '������';		
comment on column WECHAT_MEDIA.DESCRIBE
	is '����';	

--�༭ģʽ��Դ(RESOURCE)
create table WECHAT_RESOURCE
(
  ID					VARCHAR2(36) NOT NULL,
  WECHAT_ID					VARCHAR2(36) NOT NULL,
  MEDIA_ID            		VARCHAR2(36),
  RESOURCE_TITTLE			VARCHAR2(128),
  RESOURCE_CONTENT			VARCHAR2(2048),
  CREATE_DATE				VARCHAR2(10) NOT NULL,
  CREATE_TIME				VARCHAR2(8) NOT NULL,
  OUT_LINK					VARCHAR2(128),
  MULTIRESOURCE_ID			VARCHAR2(360)
) tablespace WECHAT_SPACE;

alter table WECHAT_RESOURCE
  add constraint PK_RESOURCE_ID primary key (ID)
  using index tablespace IDXPRIMARY;
comment on table WECHAT_RESOURCE is '�༭ģʽ��Դ';
comment on column WECHAT_RESOURCE.MEDIA_ID is '��ý����ԴID';
comment on column WECHAT_RESOURCE.WECHAT_ID is '���ں�ID';
comment on column WECHAT_RESOURCE.RESOURCE_TITTLE is '����';
comment on column WECHAT_RESOURCE.RESOURCE_CONTENT is '����';
comment on column WECHAT_RESOURCE.CREATE_DATE is '��������';
comment on column WECHAT_RESOURCE.CREATE_TIME is '����ʱ��';
comment on column WECHAT_RESOURCE.OUT_LINK is 'ͼ������';
comment on column WECHAT_RESOURCE.MULTIRESOURCE_ID is '������ԴID';


--΢�Ų˵���������ֶ� �˵����ͣ�0-click 1��view
ALTER TABLE WECHAT_MENU_ITEM ADD MENU_TYPE INTEGER;

--΢����Ϣ��
create table WECHAT_MESSAGE
(
  ID						VARCHAR2(36)  NOT NULL,
  WECHAT_ID					VARCHAR2(36)  NOT NULL,
  OPEN_ID					VARCHAR2(36)  NOT NULL,
  MESSAGE_MODE				INTEGER	  NOT NULL,
  CREATE_DATE				VARCHAR2(10)  NOT NULL,
  CREATE_TIME				VARCHAR2(8)   NOT NULL,
  MESSAGE_TYPE				VARCHAR2(10)  NOT NULL,
  CONTENT					VARCHAR2(2048),
  PIC_URL					VARCHAR2(128),
  MEDIA_ID					VARCHAR2(36),
  FORMAT					VARCHAR2(10),
  THUMB_MEDIA_ID			VARCHAR2(36),
  LOCATION_X				VARCHAR2(36), 
  LOCATION_Y				VARCHAR2(36),
  SCALE						VARCHAR2(5),
  LABEL						VARCHAR2(10),
  TITLE						VARCHAR2(50),
  DESCRIPTION				VARCHAR2(128),
  URL						VARCHAR2(128),
  MUSIC_URL					VARCHAR2(128),
  HQ_MUSIC_URL				VARCHAR2(128),
  ARTICLE_COUNT				INTEGER,
  ARTICLES					VARCHAR2(256)
)
tablespace WECHAT_SPACE;
alter table WECHAT_MESSAGE
  add constraint PK_MESSAGE_ID primary key (ID)
  using index tablespace IDXPRIMARY;
  

  
-------���ܿͷ�--------
create table ANSWER
(	
	ID 			VARCHAR2(10) NOT NULL, 
	CONTENT 	VARCHAR2(500) NOT NULL, 
	EDIT_TIME 	VARCHAR2(20), 
	ADOPT_COUNT INTEGER
)
tablespace WECHAT_SPACE;
alter table ANSWER
  add constraint ANSWER_ID primary key (ID)
  using index tablespace IDXPRIMARY;
 
create table QUESTION
(
	ID			VARCHAR2(10) NOT NULL,
	CONTENT		VARCHAR2(200) NOT NULL,
	ANSWER_ID	VARCHAR2(10),
	KEYWORDS	VARCHAR2(200),
	SUB_ATTR	VARCHAR2(20),
	SUBJECT		VARCHAR2(20),
	VERB		VARCHAR2(20),
	OB_ATTR		VARCHAR2(20),
	OBJECT		VARCHAR2(20),
	QTYPE		INTEGER,
	EDIT_TIME	VARCHAR2(20),
	ASK_COUNT	INTEGER
)
tablespace WECHAT_SPACE;
alter table QUESTION
  add constraint QUESTION_ID primary key (ID)
  using index tablespace IDXPRIMARY;
  
create table WORD
(
	ID 			VARCHAR2(10) NOT NULL,
	WORD		VARCHAR2(40) NOT NULL,
	SYNONYM_ID	VARCHAR2(10),
	POS			VARCHAR2(10),
	WORD_COUNT	INTEGER,
	KEY			INTEGER
)
tablespace WECHAT_SPACE;
alter table WORD
  add constraint WORD_ID primary key (ID)
  using index tablespace IDXPRIMARY;
  
  
  
  
  
  
  
  
  
--add by yangluo since 20140810
--ϵͳ��ɫ��ťȨ�ޱ�
create table SYS_CONF_ROLE_BUTTON
(
  ROLENO   INTEGER not null,
  BUTTONNO INTEGER not null
)
tablespace WECHAT_SPACE;
comment on table SYS_CONF_ROLE_BUTTON
  is '��ɫ��ťȨ�ޱ�';
comment on column SYS_CONF_ROLE_BUTTON.ROLENO
  is '��ɫ���';
comment on column SYS_CONF_ROLE_BUTTON.BUTTONNO
  is '��ť���';
alter table SYS_CONF_ROLE_BUTTON
  add constraint PK_CON_ROLE_BUT primary key (ROLENO, BUTTONNO)
  using index tablespace IDXPRIMARY;
alter table SYS_CONF_ROLE_BUTTON
  add constraint FK_CONRB_R_BUT foreign key (BUTTONNO)
  references SYS_BUTTON (NO);
alter table SYS_CONF_ROLE_BUTTON
  add constraint FK_CONRB_R_ROLE foreign key (ROLENO)
  references SYS_ROLE (NO);

  
  
--ϵͳ��ɫ�˵�Ȩ�ޱ�
create table SYS_CONF_ROLE_MENU
(
  ROLENO INTEGER not null,
  MENUNO VARCHAR2(5) not null
)
tablespace WECHAT_SPACE;
comment on table SYS_CONF_ROLE_MENU
  is '��ɫ�˵�Ȩ�ޱ�';
comment on column SYS_CONF_ROLE_MENU.ROLENO
  is '��ɫ���';
comment on column SYS_CONF_ROLE_MENU.MENUNO
  is '�˵����';
alter table SYS_CONF_ROLE_MENU
  add constraint PK_CON_ROLE_MENU primary key (ROLENO, MENUNO)
  using index tablespace IDXPRIMARY;
alter table SYS_CONF_ROLE_MENU
  add constraint FK_CONRM_R_MENU foreign key (MENUNO)
  references SYS_MENU (NO);
alter table SYS_CONF_ROLE_MENU
  add constraint FK_CONRM_R_ROLE foreign key (ROLENO)
  references SYS_ROLE (NO);
  
create table WECHAT_ARTICLE
(
  ID          VARCHAR2(64) not null,
  WECHAT_ID        VARCHAR2(36) not null,
  FUNCTION    VARCHAR2(36) not null,
  TYPE         VARCHAR2(64),
  ARTICLE_ID  VARCHAR2(36),
  ARTICLE_PICURL  VARCHAR2(128),
  ARTICLE_URL        VARCHAR2(128),
  ARTICLE_TITLE      VARCHAR2(2048),
  ARTICLE_DESCRIBE   VARCHAR2(256),
  CREATE_TIME      VARCHAR2(20)
)
tablespace WECHAT_SPACE;

comment on table WECHAT_ARTICLE
  is 'ͼ��';
comment on column WECHAT_ARTICLE.ID
  is 'ID';
comment on column WECHAT_ARTICLE.WECHAT_ID
  is '���ںź�ID';
comment on column WECHAT_ARTICLE.FUNCTION
  is 'ҵ����ID';
comment on column WECHAT_ARTICLE.TYPE
  is '����';
comment on column WECHAT_ARTICLE.ARTICLE_ID
  is 'ARTICLE_ID';
comment on column WECHAT_ARTICLE.ARTICLE_PICURL
  is 'ARTICLE_PICURL';
  comment on column WECHAT_ARTICLE.ARTICLE_URL
  is 'ARTICLE_URL';
  comment on column WECHAT_ARTICLE.ARTICLE_TITLE
  is 'ARTICLE_TITLE';
  comment on column WECHAT_ARTICLE.ARTICLE_DESCRIBE
  is 'ARTICLE_DESCRIBE';
  comment on column WECHAT_ARTICLE.CREATE_TIME
  is 'CREATE_TIME';
alter table WECHAT_ARTICLE
  add constraint PK_SYS_ARTICLE primary key (ID)
  using index tablespace IDXPRIMARY;
  
  
  
--΢�Ų˵���������ֶ� �˵����ͣ�0-click 1��view
ALTER TABLE WECHAT_MENU_ITEM ADD WECHAT_URL VARCHAR2(256);