/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/6/24 9:06:39                            */
/*==============================================================*/


drop table if exists Table_6;

drop table if exists tb_sys_action_log;

drop index uneque on tb_sys_id_generator;

drop table if exists tb_sys_id_generator;

drop table if exists tb_sys_login_log;

drop table if exists tb_sys_privilege;

drop table if exists tb_sys_resource;

drop index uneque on tb_sys_role;

drop table if exists tb_sys_role;

drop index Index_2 on tb_sys_user;

drop index INDEX1 on tb_sys_user;

drop table if exists tb_sys_user;

drop index Index_1 on tb_user_role;

drop table if exists tb_user_role;

/*==============================================================*/
/* Table: Table_6                                               */
/*==============================================================*/
create table Table_6;

/*==============================================================*/
/* Table: tb_sys_action_log                                     */
/*==============================================================*/
create table tb_sys_action_log
(
   ACTION_ID            varchar(36) not null comment '主键',
   USER_ID              varchar(36) not null comment '操作用户id',
   ACTION_LOGS          varchar(255) comment '操作说明',
   CREATE_TIME          datetime comment '操作时间',
   REMARK_DESC          varchar(255) comment '描述',
   primary key (ACTION_ID)
);

alter table tb_sys_action_log comment '系统操作日志';

/*==============================================================*/
/* Table: tb_sys_id_generator                                   */
/*==============================================================*/
create table tb_sys_id_generator
(
   GENER_ID             bigint(30) unsigned not null auto_increment comment '唯一主键',
   GENER_VALUE          varchar(255),
   primary key (GENER_ID)
);

alter table tb_sys_id_generator comment '系统唯一id';

/*==============================================================*/
/* Index: uneque                                                */
/*==============================================================*/
create unique index uneque on tb_sys_id_generator
(
   GENER_VALUE
);

/*==============================================================*/
/* Table: tb_sys_login_log                                      */
/*==============================================================*/
create table tb_sys_login_log
(
   LID                  varchar(36) not null comment '主键',
   USER_ID              varchar(36) not null comment '登录用户id',
   LOGIN_IP             varchar(20) not null comment '登录ip',
   LOGIN_TIME           datetime not null comment '登录时间',
   REMARK_DESC          varchar(255) comment '描述',
   primary key (LID)
);

alter table tb_sys_login_log comment '系统登录日志';

/*==============================================================*/
/* Table: tb_sys_privilege                                      */
/*==============================================================*/
create table tb_sys_privilege
(
   PRIV_ID              varchar(36) not null comment '主键id',
   ROLE_ID              varchar(36) not null comment '角色ID',
   RES_ID               varchar(36) not null comment '资源ID',
   CREATE_USER          varchar(36) comment '创建人id',
   CREATE_TIME          datetime comment '创建时间',
   UPDATE_USER          varchar(36) comment '更新人id',
   UPDATE_TIME          datetime comment '更新时间',
   REMARK_DESC          varchar(200) comment '描述',
   STATUS               int(2) default 0 comment '0-有效， 1-无效 默认0',
   primary key (PRIV_ID)
);

alter table tb_sys_privilege comment '系统权限表';

/*==============================================================*/
/* Table: tb_sys_resource                                       */
/*==============================================================*/
create table tb_sys_resource
(
   RES_ID               varchar(36) not null comment '主键',
   RES_NAME             varchar(64) not null comment '唯一',
   RES_TYPE             int(2) not null comment '1-菜单资源；2-按钮资源',
   RES_URL              varchar(255) comment '资源地址',
   PAREND_RES_ID        varchar(36) comment '上级资源编号',
   RES_LEVEL            bigint comment '默认值：0',
   RES_ORDER            bigint comment '默认值：1',
   REMARK_DESC          varchar(255) comment '描述',
   CREATE_USER          varchar(36) comment '创建人',
   CREATE_TIME          datetime comment '创建时间',
   UPDATE_USER          varchar(36) comment '更新人',
   UPDATE_TIME          datetime comment '更新时间',
   STATUS               int(2) default 0 comment '0-有效， 1- 无效 默认-0',
   primary key (RES_ID)
);

alter table tb_sys_resource comment '系统资源表';

/*==============================================================*/
/* Table: tb_sys_role                                           */
/*==============================================================*/
create table tb_sys_role
(
   ROLE_ID              varchar(36) not null comment '主键',
   ROLE_NAME            varchar(64) not null comment ' 角色名称',
   REMARK_DESC          varchar(255) comment '描述',
   CREATE_USER          varchar(36) comment '创建人id',
   CREATE_TIME          datetime comment '创建时间',
   UPDATE_USER          varchar(36) comment '更新人id',
   UPDATE_TIME          datetime comment '更新时间',
   STATUS               int(2) default 0 comment '0-有效，1-无效， 默认0',
   primary key (ROLE_ID)
);

alter table tb_sys_role comment '系统角色表';

/*==============================================================*/
/* Index: uneque                                                */
/*==============================================================*/
create unique index uneque on tb_sys_role
(
   ROLE_NAME
);

/*==============================================================*/
/* Table: tb_sys_user                                           */
/*==============================================================*/
create table tb_sys_user
(
   USER_ID              varchar(36) not null comment '主键用户id',
   LOGIN_NAME           varchar(50) not null comment '登录名',
   PASSWORD             varchar(64) not null comment '登录密码',
   USER_NAME            varchar(50) comment '姓名',
   PHONE                varchar(20) not null comment '联系电话',
   EMAIL                varchar(64) comment '联系邮箱',
   LAST_LOGIN_TIME      datetime comment '最后登录时间',
   CREATE_USER          varchar(36) comment '创建人id',
   CREATE_TIME          datetime comment '创建时间',
   UPDATE_USER          varchar(36) comment '更新人id',
   UPDATE_TIMES         datetime comment '更新时间',
   DESCS                varchar(255) comment '描述',
   STATUS               int(2) default 0 comment '0-有效， 1-已删除 默认0',
   primary key (USER_ID)
);

alter table tb_sys_user comment '系统用户表';

/*==============================================================*/
/* Index: INDEX1                                                */
/*==============================================================*/
create index INDEX1 on tb_sys_user
(
   USER_ID
);

/*==============================================================*/
/* Index: Index_2                                               */
/*==============================================================*/
create unique index Index_2 on tb_sys_user
(
   LOGIN_NAME
);

/*==============================================================*/
/* Table: tb_user_role                                          */
/*==============================================================*/
create table tb_user_role
(
   TUR_ID               varchar(36) not null comment '主键',
   USER_ID              varchar(36) not null comment '用户id',
   ROLE_ID              varchar(36) not null comment '角色id',
   CREATE_USER          varchar(36) comment '创建用户',
   CREATE_TIME          datetime comment '创建时间',
   UPDATA_USER          varchar(36) comment '修改用户',
   UPDATA_TIME          datetime comment '修改时间',
   STATUS               int default 0 comment '是否有效(0-有效， 1-无效。 默认0)',
   primary key (TUR_ID)
);

alter table tb_user_role comment '用户角色表';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on tb_user_role
(
   USER_ID,
   ROLE_ID
);

