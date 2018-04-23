DROP TABLE IF EXISTS `acf_form`;
CREATE TABLE `acf_form` (
  `BUSINESS_ID` varchar(64) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL COMMENT '表单名称',
  `code` varchar(20) DEFAULT NULL COMMENT '表单编码',
  `des` varchar(255) DEFAULT NULL COMMENT '表单描述',
  `original_html` TEXT DEFAULT NULL COMMENT '原始html',
  `parse_html` TEXT DEFAULT NULL COMMENT '解析后html',
  `filed_count` int  COMMENT '字段总数',
  `table_name` varchar(100) DEFAULT NULL COMMENT '数据表名称',

  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL  COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL COMMENT '启用标识 1 启用 0 停用 ',
  PRIMARY KEY (`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE acf_form ADD UNIQUE (code);




DROP TABLE IF EXISTS `acf_field`;
CREATE TABLE `acf_field` (
  `BUSINESS_ID` varchar(64) NOT NULL,
  `plugin` varchar(255) DEFAULT NULL COMMENT '插件',
  `NAME` varchar(155) DEFAULT NULL COMMENT '字段名称',
  `title` varchar(155) DEFAULT NULL COMMENT '字段标题',
  `type` varchar(155) DEFAULT NULL COMMENT '字段类型',
  `flow` varchar(155) DEFAULT NULL COMMENT '',
  `form_ID` varchar(64) NOT NULL COMMENT '表单ID',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` varchar(64) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL  COMMENT '修改日期',
  `REMARKS` varchar(255) DEFAULT NULL COMMENT '备注',
  `DEL_FLAG` varchar(1) DEFAULT NULL COMMENT '删除标识 1 删除 0 未删除 2 审核',
  `FLAG` varchar(1) DEFAULT NULL COMMENT '启用标识 1 启用 0 停用 ',
  PRIMARY KEY (`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
