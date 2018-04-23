/*
 Navicat Premium Data Transfer

 Source Server         : family_home
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 114.115.222.53
 Source Database       : yxyqcy_test

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : utf-8

 Date: 11/13/2017 20:30:35 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
--  Table structure for `f_account`
-- ----------------------------
DROP TABLE IF EXISTS `f_account`;
CREATE TABLE `f_account` (
  `business_id` varchar(64) NOT NULL COMMENT 'id',
  `url` varchar(300) DEFAULT NULL COMMENT 'url',
  `description` varchar(1000) DEFAULT NULL COMMENT 'description',
  `account` varchar(300) DEFAULT NULL COMMENT '帐号',
  `passwd` varchar(300) DEFAULT NULL COMMENT '密码',
  `title` varchar(300) DEFAULT NULL COMMENT 'title',
  `seq_date` datetime DEFAULT NULL COMMENT '日期排序',
  `type` varchar(1) DEFAULT NULL COMMENT '类型 1 服务器 2 网站 0 其他',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL COMMENT '启用标识 1 启用 0 停用 ',
  PRIMARY KEY (`business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='f_account';

-- ----------------------------
--  Table structure for `f_account_serset`
-- ----------------------------
DROP TABLE IF EXISTS `f_account_serset`;
CREATE TABLE `f_account_serset` (
  `business_id` varchar(64) NOT NULL COMMENT 'id',
  `title` varchar(300) DEFAULT NULL COMMENT 'title',
  `port` varchar(100) DEFAULT NULL COMMENT 'port',
  `description` varchar(1000) DEFAULT NULL COMMENT 'description',
  `account` varchar(300) DEFAULT NULL COMMENT '帐号',
  `passwd` varchar(300) DEFAULT NULL COMMENT '密码',
  `seq_date` datetime DEFAULT NULL COMMENT '日期排序',
  `server_id` varchar(64) NOT NULL COMMENT 'server id',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL COMMENT '启用标识 1 启用 0 停用 ',
  PRIMARY KEY (`business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='f_account_serset';

-- ----------------------------
--  Table structure for `f_blog`
-- ----------------------------
DROP TABLE IF EXISTS `f_blog`;
CREATE TABLE `f_blog` (
  `business_id` varchar(64) NOT NULL,
  `title` varchar(150) DEFAULT NULL COMMENT '博客标题',
  `bkeys` varchar(255) DEFAULT NULL COMMENT '关键词,间隔 ',
  `bdesc` text COMMENT '博客描述 ',
  `blog_label_id` varchar(255) DEFAULT NULL COMMENT 'labelId',
  `blog_md` varchar(255) DEFAULT NULL COMMENT 'blog md地址 or mind 地址',
  `blog_html` varchar(255) DEFAULT NULL COMMENT 'blog html地址',
  `star` float DEFAULT NULL COMMENT '几星0-5 评价',
  `SEQ_DATE` datetime DEFAULT NULL COMMENT '排序',
  `read_times` int(3) DEFAULT NULL COMMENT '阅读次数',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL COMMENT '启用标识 1 启用 0 停用 ',
  `is_top` varchar(1) DEFAULT NULL COMMENT '推荐 1  不推荐0',
  `type` varchar(1) DEFAULT NULL COMMENT '0.md 1. mind',
  `is_public` varchar(1) DEFAULT '1' COMMENT '1公开 0 不公开',
  `classify` varchar(1) DEFAULT '0' COMMENT '0 综合 1 移动开发 2 架构 3 云计算/大数据 4 互联网 5 运维 6 数据库 7 前端 8 编程语言 9 研发管理',
  PRIMARY KEY (`business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `f_blog_label`
-- ----------------------------
DROP TABLE IF EXISTS `f_blog_label`;
CREATE TABLE `f_blog_label` (
  `business_id` varchar(64) NOT NULL,
  `title` varchar(100) DEFAULT NULL COMMENT '博客label标题',
  `SEQ_DATE` datetime DEFAULT NULL COMMENT '排序',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL COMMENT '启用标识 1 启用 0 停用 ',
  PRIMARY KEY (`business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `f_book`
-- ----------------------------
DROP TABLE IF EXISTS `f_book`;
CREATE TABLE `f_book` (
  `BUSINESS_ID` varchar(64) NOT NULL,
  `CODE` varchar(64) DEFAULT NULL COMMENT '编码',
  `NAME` varchar(100) DEFAULT NULL COMMENT '书名',
  `desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `book_url` varchar(255) DEFAULT NULL COMMENT '地址',
  `book_cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `pages` int DEFAULT NULL COMMENT '页数',
  `classify` varchar(1) DEFAULT '0' COMMENT '0 综合 1 移动开发 2 架构 3 云计算/大数据 4 互联网 5 运维 6 数据库 7 前端 8 编程语言 9 研发管理',
  `book_case` varchar(1) DEFAULT '0' COMMENT '0 初级 1 中级 2 高级 3 资深级 4 专家级',

  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改日期',

  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL COMMENT '启用标识 1 启用 0 停用',

  `is_public` varchar(1) DEFAULT '1' COMMENT '1公开 0 不公开',
  `is_top` varchar(1) DEFAULT '0' COMMENT '推荐 1  不推荐0',
  PRIMARY KEY (`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `f_movie`
-- ----------------------------
DROP TABLE IF EXISTS `f_movie`;
CREATE TABLE `f_movie` (
  `business_id` varchar(64) NOT NULL,
  `movie_date` datetime NOT NULL COMMENT '电影开始时间',
  `movie_interval` int(3) DEFAULT NULL COMMENT '时段长(分钟)',
  `star` float DEFAULT NULL COMMENT '几星0-5 评价',
  `NAME` varchar(100) DEFAULT NULL COMMENT '电影名称',
  `movie_theatre` varchar(255) DEFAULT NULL COMMENT '电影院',
  `location` varchar(255) DEFAULT NULL COMMENT '厅&位置',
  `price` float DEFAULT NULL COMMENT '价格/人',
  `fly_count` int(2) DEFAULT NULL COMMENT '人数',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL COMMENT '启用标识 1 启用 0 停用 ',
  `name_pinyin` varchar(3000) DEFAULT NULL COMMENT 'name 首字母拼音',
  PRIMARY KEY (`business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `f_movie_theatre`
-- ----------------------------
DROP TABLE IF EXISTS `f_movie_theatre`;
CREATE TABLE `f_movie_theatre` (
  `business_id` varchar(64) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL COMMENT '电影院名称',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL COMMENT '启用标识 1 启用 0 停用 ',
  `ORDER_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `f_timesheet`
-- ----------------------------
DROP TABLE IF EXISTS `f_timesheet`;
CREATE TABLE `f_timesheet` (
  `business_id` varchar(64) NOT NULL COMMENT '业务id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `ASSESS_FLAG` varchar(1) DEFAULT NULL COMMENT '审核标识 0 待审核 2 已通过 1已打回',
  `start_date` datetime NOT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL COMMENT '启用标识 1 启用 0 停用 ',
  `all_day` tinyint(1) DEFAULT NULL COMMENT '是否为一天任务 0 否  1 是',
  `ASSESS_USER` varchar(64) DEFAULT NULL COMMENT '审批人',
  `ASSESS_DATE` datetime DEFAULT NULL COMMENT '审批时间',
  `topic_id` varchar(64) DEFAULT NULL COMMENT '课题id',
  `type` varchar(1) DEFAULT NULL COMMENT '0.个人日程 1.timesheet (审批)  2.课题任务',
  PRIMARY KEY (`business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `l_story`
-- ----------------------------
DROP TABLE IF EXISTS `l_story`;
CREATE TABLE `l_story` (
  `business_id` varchar(64) NOT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '故事名称',
  `description` varchar(3000) DEFAULT NULL COMMENT '故事描述',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL COMMENT '启用标识 1 启用 0 停用 ',
  PRIMARY KEY (`business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `l_story_fragment`
-- ----------------------------
DROP TABLE IF EXISTS `l_story_fragment`;
CREATE TABLE `l_story_fragment` (
  `business_id` varchar(64) NOT NULL,
  `image_first` varchar(300) DEFAULT NULL COMMENT '图一',
  `content_first` varchar(3000) DEFAULT NULL COMMENT '图一说明',
  `class_first` varchar(100) DEFAULT NULL COMMENT '图一class',
  `image_second` varchar(300) DEFAULT NULL COMMENT '图二',
  `content_second` varchar(3000) DEFAULT NULL COMMENT '图二说明',
  `class_second` varchar(100) DEFAULT NULL COMMENT '图二class',
  `story_id` varchar(64) NOT NULL COMMENT '故事id',
  `is_year` int(1) DEFAULT NULL COMMENT '是否是年',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL COMMENT '启用标识 1 启用 0 停用 ',
  `title` varchar(3000) DEFAULT NULL COMMENT 'title',
  `story_date` datetime DEFAULT NULL,
  PRIMARY KEY (`business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `r_sheet_blog`
-- ----------------------------
DROP TABLE IF EXISTS `r_sheet_blog`;
CREATE TABLE `r_sheet_blog` (
  `BUSINESS_ID` varchar(64) NOT NULL,
  `SHEET_ID` varchar(64) NOT NULL COMMENT '日程id',
  `BLOG_ID` varchar(64) NOT NULL COMMENT '博客id',
  PRIMARY KEY (`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='r_sheet_blog';

-- ----------------------------
--  Table structure for `r_topic`
-- ----------------------------
DROP TABLE IF EXISTS `r_topic`;
CREATE TABLE `r_topic` (
  `business_id` varchar(64) NOT NULL COMMENT 'id',
  `title` varchar(300) DEFAULT NULL COMMENT 'title',
  `description` text COMMENT 'description',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `type` varchar(1) DEFAULT NULL COMMENT '类型 0 前端 1 后端 2 运维',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL COMMENT '启用标识 1 启用 0 停用 ',
  PRIMARY KEY (`business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='r_topic';

-- ----------------------------
--  Table structure for `r_topic_user`
-- ----------------------------
DROP TABLE IF EXISTS `r_topic_user`;
CREATE TABLE `r_topic_user` (
  `BUSINESS_ID` varchar(64) NOT NULL,
  `TOPIC_ID` varchar(64) NOT NULL COMMENT '课题id',
  `USER_ID` varchar(64) NOT NULL COMMENT 'userid',
  PRIMARY KEY (`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='r_topic_user';

-- ----------------------------
--  Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `BUSINESS_ID` varchar(64) NOT NULL,
  `PARENT_ID` varchar(64) DEFAULT NULL COMMENT '父机构id',
  `NAME` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `DEPT_CODE` varchar(64) DEFAULT NULL COMMENT '部门编号',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `SEQ_DATE` datetime NOT NULL COMMENT '排序日期',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_dept_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_role`;
CREATE TABLE `sys_dept_role` (
  `BUSINESS_ID` varchar(64) NOT NULL,
  `DEPT_ID` varchar(64) NOT NULL COMMENT '部门id',
  `ROLE_ID` varchar(64) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_dept_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_role_user`;
CREATE TABLE `sys_dept_role_user` (
  `BUSINESS_ID` varchar(64) NOT NULL,
  `DEPT_ROLE_ID` varchar(64) NOT NULL COMMENT '角色id',
  `USER_ID` varchar(64) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `BUSINESS_ID` varchar(64) NOT NULL COMMENT '编号',
  `pid` varchar(64) DEFAULT NULL,
  `type` varchar(64) DEFAULT NULL COMMENT '类型',
  `name` varchar(64) DEFAULT NULL COMMENT '名字',
  `value` varchar(64) DEFAULT NULL COMMENT '值',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改日期',
  `del_flag` varchar(255) DEFAULT NULL,
  `flag` char(1) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`BUSINESS_ID`,`CREATE_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
--  Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `BUSINESS_ID` varchar(64) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `level` varchar(10) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `logDate` datetime DEFAULT NULL,
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改日期',
  `del_flag` varchar(255) DEFAULT NULL,
  `flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `BUSINESS_ID` varchar(64) NOT NULL COMMENT '业务主键',
  `PARENT_ID` varchar(64) DEFAULT NULL COMMENT '父级菜单',
  `PARENT_IDS` varchar(2000) DEFAULT NULL COMMENT '所有父级菜单',
  `NAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `HREF` varchar(255) DEFAULT NULL COMMENT '链接',
  `TARGET` varchar(20) DEFAULT NULL COMMENT '目标（ mainFrame、_blank、_self、_parent、_top）',
  `ICON` varchar(100) DEFAULT NULL COMMENT '图标',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `IS_SHOW` varchar(1) DEFAULT NULL COMMENT '1 显示  0  不显示',
  `IS_ACTIVITI` varchar(1) DEFAULT NULL COMMENT '1 同步到工作流  0 不同步',
  `PERMISSION` varchar(200) DEFAULT NULL COMMENT '权限',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `CODE` varchar(255) DEFAULT NULL,
  `FLAG` varchar(1) DEFAULT NULL,
  `MODEL_ID` varchar(64) DEFAULT NULL COMMENT '模型ID',
  `CASE_COUNT` int(10) DEFAULT NULL COMMENT '层级',
  PRIMARY KEY (`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `BUSINESS_ID` varchar(64) NOT NULL,
  `CODE` varchar(64) DEFAULT NULL COMMENT '编码',
  `NAME` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `CHECKED` varchar(255) DEFAULT NULL,
  `DATA_SCOPE` varchar(1) DEFAULT NULL COMMENT '数据范围',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL COMMENT '启用标识 1 启用 0 停用',
  PRIMARY KEY (`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `BUSINESS_ID` varchar(64) NOT NULL,
  `ROLE_ID` varchar(64) NOT NULL COMMENT '角色id',
  `MENU_ID` varchar(64) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `BUSINESS_ID` varchar(64) NOT NULL,
  `COMPANY_ID` varchar(64) DEFAULT NULL COMMENT '归属公司',
  `OFFICE_ID` varchar(64) DEFAULT NULL COMMENT '归属部门',
  `LOGIN_NAME` varchar(100) DEFAULT NULL COMMENT '登录名',
  `PASSWORD` varchar(100) DEFAULT NULL COMMENT '密码',
  `NO` varchar(100) DEFAULT NULL COMMENT '工号/员工编号',
  `NAME` varchar(100) DEFAULT NULL COMMENT '姓名',
  `PERSON_ID` varchar(100) DEFAULT NULL COMMENT '身份证号',
  `EMAIL` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `PHONE` varchar(200) DEFAULT NULL COMMENT '电话',
  `MOBILE` varchar(200) DEFAULT NULL COMMENT '手机',
  `ENTRY_DATE` date DEFAULT NULL COMMENT '入职日期',
  `EMPL_GROUP` varchar(255) DEFAULT NULL COMMENT '员工组',
  `TRIAL_OR_NOT` int(1) DEFAULT NULL COMMENT '是否试用期 1是0否',
  `TRIAL_LONG` int(11) DEFAULT NULL COMMENT '试用期限 单位（月）',
  `TRIAL_DATE` date DEFAULT NULL COMMENT '试用到期日',
  `WORK_POSITION` varchar(255) DEFAULT NULL COMMENT '工作地',
  `LOGIN_IP` varchar(100) DEFAULT NULL COMMENT '最后登录IP',
  `LOGIN_DATE` datetime DEFAULT NULL COMMENT '最后登录日期',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `MOBILE_PHONE` varchar(50) DEFAULT NULL COMMENT '手机号',
  `DEPARTMENT` varchar(100) DEFAULT NULL COMMENT '角色id',
  `FLAG` varchar(1) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '家庭住址',
  `URGENCY_PERSON` varchar(255) DEFAULT NULL COMMENT '紧急联系人',
  `URGENCY_LINE` varchar(255) DEFAULT NULL COMMENT '紧急联系电话',
  `COLLEGE` varchar(255) DEFAULT NULL COMMENT '大学',
  `SPECIALITY` varchar(255) DEFAULT NULL COMMENT '专业',
  `ACADEMIC` varchar(255) DEFAULT NULL COMMENT '学历',
  `DEGREE` varchar(255) DEFAULT NULL COMMENT '学位',
  `GRADUATE_DATE` date DEFAULT NULL COMMENT '毕业日期',
  `WORK_DATE` date DEFAULT NULL COMMENT '参加工作日期',
  `NATION` int(2) DEFAULT NULL COMMENT '民族',
  `HOMETOWN` varchar(255) DEFAULT NULL COMMENT '籍贯',
  `PERSON_PROPERTY` int(1) DEFAULT NULL COMMENT '户口性质',
  `BIRTHDAY` date DEFAULT NULL COMMENT '生日',
  `SEX` int(11) DEFAULT NULL COMMENT '性别 1男0女',
  `MARRY_OR_NOT` int(11) DEFAULT NULL COMMENT '婚姻状况 0未婚1已婚2离异',
  `BANK_NAME` varchar(255) DEFAULT NULL COMMENT '银行名称',
  `BANK_NUM` varchar(255) DEFAULT NULL,
  `ACCOUNT_BY` varchar(255) DEFAULT NULL COMMENT '受款人',
  `CONTRACT_TYPE` int(11) DEFAULT NULL COMMENT '合同类型',
  `CONTRACT_START` date DEFAULT NULL COMMENT '合同开始日期',
  `CONTRACT_END` date DEFAULT NULL COMMENT '合同结束',
  `RENEW_CONTRACT_START` date DEFAULT NULL COMMENT '续约合同开始',
  `RENEW_CONTRACT_END` date DEFAULT NULL COMMENT '续约合同到期',
  `AGREEMENT_TYPE` int(11) DEFAULT NULL COMMENT '协议类型 数据字典',
  `AGREEMENT_START` date DEFAULT NULL COMMENT '协议开始日期',
  `AGREEMENT_END` date DEFAULT NULL COMMENT '协议结束',
  `USER_STATES` char(255) DEFAULT NULL COMMENT '员工状态（离职，在职，出差等）',
  `digest_key` varchar(64) DEFAULT NULL COMMENT '密钥',
  `weixin_name` varchar(200) DEFAULT NULL COMMENT '微信user',
  PRIMARY KEY (`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SET FOREIGN_KEY_CHECKS = 1;
