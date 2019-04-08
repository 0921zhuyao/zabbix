/*
 Navicat Premium Data Transfer
 Date: 09/10/2018 10:20:26 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` int(11) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT '' COMMENT '负责人',
  `phone` varchar(11) DEFAULT '' COMMENT '联系电话',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
--  Records of `sys_dept`
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES ('100', '0', '0', '邦道集团', '0', '邦道', '15888888888', 'jd@sohu.com', '0', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20'), ('101', '100', '0,100', '研发部门', '1', '邦道', '15888888888', 'jd@sohu.com', '0', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20'), ('102', '100', '0,100', '市场部门', '2', '邦道', '15888888888', 'jd@sohu.com', '0', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20'), ('103', '100', '0,100', '测试部门', '3', '邦道', '15888888888', 'jd@sohu.com', '0', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20'), ('104', '100', '0,100', '财务部门', '4', '邦道', '15888888888', 'jd@sohu.com', '0', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20'), ('105', '100', '0,100', '运维部门', '5', '邦道', '15888888888', 'jd@sohu.com', '0', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20'), ('106', '101', '0,100,101', '研发一部', '1', '邦道', '15888888888', 'jd@sohu.com', '0', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20'), ('107', '101', '0,100,101', '研发二部', '2', '邦道', '15888888888', 'jd@sohu.com', '0', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20'), ('108', '102', '0,100,102', '市场一部', '1', '邦道', '15888888888', 'jd@sohu.com', '0', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20'), ('109', '102', '0,100,102', '市场二部', '2', '邦道', '15888888888', 'jd@sohu.com', '1', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20');
COMMIT;

-- ----------------------------
--  Table structure for `sys_dict_data`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(500) DEFAULT '' COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(500) DEFAULT '' COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
--  Records of `sys_dict_data`
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_data` VALUES ('1', '1', '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '性别男'), ('2', '2', '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '性别女'), ('3', '3', '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '性别未知'), ('4', '1', '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '显示菜单'), ('5', '2', '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '隐藏菜单'), ('6', '1', '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '正常状态'), ('7', '2', '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '停用状态'), ('8', '1', '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '正常状态'), ('9', '2', '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '停用状态'), ('10', '1', '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '系统默认是'), ('11', '2', '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '系统默认否'), ('12', '1', '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '通知'), ('13', '2', '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '公告'), ('14', '1', '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '正常状态'), ('15', '2', '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '关闭状态'), ('16', '1', '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '新增操作'), ('17', '2', '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '新增操作'), ('18', '3', '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '新增操作'), ('19', '4', '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '新增操作'), ('20', '5', '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '新增操作'), ('21', '6', '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '新增操作'), ('22', '7', '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '新增操作'), ('23', '8', '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '新增操作'), ('24', '1', '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '正常状态'), ('25', '2', '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '停用状态');
COMMIT;

-- ----------------------------
--  Table structure for `sys_dict_type`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
--  Records of `sys_dict_type`
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_type` VALUES ('1', '用户性别', 'sys_user_sex', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '用户性别列表'), ('2', '菜单状态', 'sys_show_hide', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '菜单状态列表'), ('3', '系统开关', 'sys_normal_disable', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '系统开关列表'), ('4', '任务状态', 'sys_job_status', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '任务状态列表'), ('5', '系统是否', 'sys_yes_no', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '系统是否列表'), ('6', '通知类型', 'sys_notice_type', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '通知类型列表'), ('7', '通知状态', 'sys_notice_status', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '通知状态列表'), ('8', '操作类型', 'sys_oper_type', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '操作类型列表'), ('9', '系统状态', 'sys_common_status', '0', 'admin', '2018-09-05 10:51:21', 'admin', '2018-09-05 10:51:21', '登录状态列表');
COMMIT;

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` int(11) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) DEFAULT '' COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2018 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
--  Records of `sys_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '1', '#', 'M', '0', '', 'fa fa-gear', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', '系统管理目录'), ('3', '系统工具', '0', '1200', '#', 'M', '0', '', 'fa fa-bars', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-05 10:30:07', '系统工具目录'), ('100', '用户管理', '1', '1', '/system/user', 'C', '0', 'system:user:view', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', '用户管理菜单'), ('101', '角色管理', '1', '2', '/system/role', 'C', '0', 'system:role:view', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', '角色管理菜单'), ('102', '菜单管理', '1', '3', '/system/menu', 'C', '0', 'system:menu:view', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', '菜单管理菜单'), ('103', '部门管理', '1', '4', '/system/dept', 'C', '0', 'system:dept:view', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', '部门管理菜单'), ('105', '字典管理', '1', '5', '/system/dict', 'C', '0', 'system:dict:view', '#', 'admin', '2018-09-05 10:13:05', 'admin', '2018-09-05 10:18:34', '字典管理菜单'), ('108', '日志管理', '1', '6', '/monitor/operlog', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-09-05 10:13:05', 'admin', '2018-09-05 10:19:01', '日志管理菜单'), ('112', '表单构建', '3', '1', '/tool/build', 'C', '0', 'tool:build:view', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', '表单构建菜单'), ('113', '代码生成', '3', '2', '/tool/gen', 'C', '0', 'tool:gen:view', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', '代码生成菜单'), ('1000', '用户查询', '100', '1', '#', 'F', '0', 'system:user:list', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1001', '用户新增', '100', '2', '#', 'F', '0', 'system:user:add', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1002', '用户修改', '100', '3', '#', 'F', '0', 'system:user:edit', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1003', '用户删除', '100', '4', '#', 'F', '0', 'system:user:remove', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1004', '用户导出', '100', '5', '#', 'F', '0', 'system:user:export', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1005', '重置密码', '100', '5', '#', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1006', '角色查询', '101', '1', '#', 'F', '0', 'system:role:list', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1007', '角色新增', '101', '2', '#', 'F', '0', 'system:role:add', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1008', '角色修改', '101', '3', '#', 'F', '0', 'system:role:edit', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1009', '角色删除', '101', '4', '#', 'F', '0', 'system:role:remove', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1010', '角色导出', '101', '4', '#', 'F', '0', 'system:role:export', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1011', '菜单查询', '102', '1', '#', 'F', '0', 'system:menu:list', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1012', '菜单新增', '102', '2', '#', 'F', '0', 'system:menu:add', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1013', '菜单修改', '102', '3', '#', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1014', '菜单删除', '102', '4', '#', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1015', '部门查询', '103', '1', '#', 'F', '0', 'system:dept:list', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1016', '部门新增', '103', '2', '#', 'F', '0', 'system:dept:add', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1017', '部门修改', '103', '3', '#', 'F', '0', 'system:dept:edit', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1018', '部门删除', '103', '4', '#', 'F', '0', 'system:dept:remove', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1024', '字典查询', '105', '1', '#', 'F', '0', 'system:dict:list', '#', 'admin', '2018-09-05 10:10:51', 'admin', '2018-09-05 10:10:51', ''), ('1025', '字典新增', '105', '2', '#', 'F', '0', 'system:dict:add', '#', 'admin', '2018-09-05 10:10:51', 'admin', '2018-09-05 10:10:51', ''), ('1026', '字典修改', '105', '3', '#', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-09-05 10:10:51', 'admin', '2018-09-05 10:10:51', ''), ('1027', '字典删除', '105', '4', '#', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-09-05 10:10:51', 'admin', '2018-09-05 10:10:51', ''), ('1028', '字典导出', '105', '4', '#', 'F', '0', 'system:dict:export', '#', 'admin', '2018-09-05 10:10:51', 'admin', '2018-09-05 10:10:51', ''), ('1038', '操作查询', '108', '1', '#', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-09-05 10:10:51', 'admin', '2018-09-05 10:28:47', ''), ('1039', '操作删除', '108', '2', '#', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-09-05 10:10:51', 'admin', '2018-09-05 10:28:58', ''), ('1040', '详细信息', '108', '3', '#', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-09-05 10:10:51', 'admin', '2018-09-05 10:29:06', ''), ('1041', '日志导出', '108', '3', '#', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-09-05 10:10:51', 'admin', '2018-09-05 10:29:16', ''), ('1054', '生成查询', '113', '1', '#', 'F', '0', 'tool:gen:list', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('1055', '生成代码', '113', '2', '#', 'F', '0', 'tool:gen:code', '#', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', ''), ('2000', '工作流管理', '0', '2', '#', 'M', '0', '', 'fa fa-cogs', 'admin', '2018-09-03 11:38:58', 'admin', '2018-09-05 10:34:37', ''), ('2001', '运行中流程', '2000', '1', '#', 'C', '0', '', '#', 'admin', '2018-09-03 12:35:46', '', null, ''), ('2002', '历史流程', '2000', '2', '#', 'C', '0', '', '#', 'admin', '2018-09-03 12:37:13', '', null, ''), ('2003', '流程定义', '2000', '3', '#', 'C', '0', 'activiti:processDef:view', '#', 'admin', '2018-09-03 12:37:41', 'admin', '2018-09-08 21:40:38', ''), ('2004', '模型工作区', '2000', '4', '/activiti/models/modelList', 'C', '0', 'activiti:models:view', '#', 'admin', '2018-09-03 12:38:19', 'admin', '2018-09-08 20:54:01', ''), ('2005', '在线办公', '0', '3', '#', 'M', '0', '', 'fa fa-calendar', 'admin', '2018-09-03 12:38:56', 'admin', '2018-09-05 10:35:02', ''), ('2006', '任务申请', '2005', '1', '#', 'C', '0', '', '#', 'admin', '2018-09-03 12:41:26', '', null, ''), ('2007', '我的任务', '2005', '2', '#', 'C', '0', '', '#', 'admin', '2018-09-03 12:41:48', '', null, ''), ('2008', '运行中任务', '2005', '3', '#', 'C', '0', '', '#', 'admin', '2018-09-03 12:41:58', '', null, ''), ('2009', '已结束任务', '2005', '4', '#', 'C', '0', '', '#', 'admin', '2018-09-03 12:42:17', '', null, ''), ('2010', '模型新增', '2004', '1', '#', 'F', '0', 'activiti:models:add', '#', 'admin', '2018-09-08 20:57:52', '', null, ''), ('2011', '模型修改', '2004', '2', '#', 'F', '0', 'activiti:models:update', '#', 'admin', '2018-09-08 20:58:34', '', null, ''), ('2012', '模型查看', '2004', '3', '#', 'F', '0', 'activiti:models:view', '#', 'admin', '2018-09-08 20:59:01', '', null, ''), ('2013', '模型删除', '2004', '4', '#', 'F', '0', 'activiti:models:delete', '#', 'admin', '2018-09-08 20:59:29', '', null, ''), ('2014', '模型发布', '2004', '5', '#', 'F', '0', 'activiti:models:deploy', '#', 'admin', '2018-09-08 21:00:09', '', null, ''), ('2015', '流程部署', '2004', '6', '/activiti/deploy/modelList', 'C', '1', 'activiti:deploy:view', '#', 'admin', '2018-09-08 21:39:21', 'admin', '2018-09-10 09:35:54', ''), ('2016', '流程部署查看', '2015', '1', '#', 'F', '0', 'activiti:deploy:view', '#', 'admin', '2018-09-08 22:03:30', '', null, ''), ('2017', '流程部署删除', '2015', '2', '#', 'F', '0', 'activiti:deploy:delete', '#', 'admin', '2018-09-08 22:03:58', '', null, '');
COMMIT;

-- ----------------------------
--  Table structure for `sys_oper_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(30) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(255) DEFAULT '' COMMENT '请求参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

-- ----------------------------
--  Records of `sys_oper_log`
-- ----------------------------
BEGIN;
INSERT INTO `sys_oper_log` VALUES ('118', '菜单管理', '2', 'com.jd.project.system.menu.controller.MenuController.editSave()', '1', 'admin', '研发一部', '/system/menu/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"menuId\":[\"2004\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"模型工作区\"],\"url\":[\"/activiti/models/modelList\"],\"perms\":[\"activiti:models:view\"],\"orderNum\":[\"4\"],\"icon\":[\"#\"],\"status\":[\"0\"]}', '0', null, '2018-09-08 20:54:01'), ('119', '角色管理', '2', 'com.jd.project.system.role.controller.RoleController.editSave()', '1', 'admin', '研发一部', '/system/role/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"roleId\":[\"1\"],\"roleName\":[\"管理员\"],\"roleKey\":[\"admin\"],\"roleSort\":[\"1\"],\"status\":[\"0\"],\"remark\":[\"管理员\"],\"menuIds\":[\"1,100,1000,1001,1002,1003,1004,1005,101,1006,1007,1008,1009,1010,102,1011,1012,1013,1014,103,1015,1016,1017,1018,105,1024,1025,1026,1027,10', '0', null, '2018-09-08 20:54:27'), ('120', '菜单管理', '1', 'com.jd.project.system.menu.controller.MenuController.addSave()', '1', 'admin', '研发一部', '/system/menu/add', '0:0:0:0:0:0:0:1', 'XX XX', '{\"parentId\":[\"2004\"],\"menuType\":[\"F\"],\"menuName\":[\"模型新增\"],\"url\":[\"\"],\"perms\":[\"activiti:models:add\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"status\":[\"0\"]}', '0', null, '2018-09-08 20:57:52'), ('121', '菜单管理', '1', 'com.jd.project.system.menu.controller.MenuController.addSave()', '1', 'admin', '研发一部', '/system/menu/add', '0:0:0:0:0:0:0:1', 'XX XX', '{\"parentId\":[\"2004\"],\"menuType\":[\"F\"],\"menuName\":[\"模型修改\"],\"url\":[\"\"],\"perms\":[\"activiti:models:update\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"status\":[\"0\"]}', '0', null, '2018-09-08 20:58:34'), ('122', '菜单管理', '1', 'com.jd.project.system.menu.controller.MenuController.addSave()', '1', 'admin', '研发一部', '/system/menu/add', '0:0:0:0:0:0:0:1', 'XX XX', '{\"parentId\":[\"2004\"],\"menuType\":[\"F\"],\"menuName\":[\"模型查看\"],\"url\":[\"\"],\"perms\":[\"activiti:models:view\"],\"orderNum\":[\"3\"],\"icon\":[\"\"],\"status\":[\"0\"]}', '0', null, '2018-09-08 20:59:01'), ('123', '菜单管理', '1', 'com.jd.project.system.menu.controller.MenuController.addSave()', '1', 'admin', '研发一部', '/system/menu/add', '0:0:0:0:0:0:0:1', 'XX XX', '{\"parentId\":[\"2004\"],\"menuType\":[\"F\"],\"menuName\":[\"模型删除\"],\"url\":[\"\"],\"perms\":[\"activiti:models:delete\"],\"orderNum\":[\"4\"],\"icon\":[\"\"],\"status\":[\"0\"]}', '0', null, '2018-09-08 20:59:29'), ('124', '菜单管理', '1', 'com.jd.project.system.menu.controller.MenuController.addSave()', '1', 'admin', '研发一部', '/system/menu/add', '0:0:0:0:0:0:0:1', 'XX XX', '{\"parentId\":[\"2004\"],\"menuType\":[\"F\"],\"menuName\":[\"模型发布\"],\"url\":[\"\"],\"perms\":[\"activiti:models:deploy\"],\"orderNum\":[\"5\"],\"icon\":[\"\"],\"status\":[\"0\"]}', '0', null, '2018-09-08 21:00:09'), ('125', '角色管理', '2', 'com.jd.project.system.role.controller.RoleController.editSave()', '1', 'admin', '研发一部', '/system/role/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"roleId\":[\"1\"],\"roleName\":[\"管理员\"],\"roleKey\":[\"admin\"],\"roleSort\":[\"1\"],\"status\":[\"0\"],\"remark\":[\"管理员\"],\"menuIds\":[\"1,100,1000,1001,1002,1003,1004,1005,101,1006,1007,1008,1009,1010,102,1011,1012,1013,1014,103,1015,1016,1017,1018,105,1024,1025,1026,1027,10', '0', null, '2018-09-08 21:00:45'), ('126', '用户管理', '2', 'com.jd.project.system.user.controller.UserController.editSave()', '1', 'admin', '研发一部', '/system/user/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"userId\":[\"1\"],\"deptId\":[\"100\"],\"userName\":[\"邦道\"],\"email\":[\"admin@qq.com\"],\"phonenumber\":[\"18888888888\"],\"sex\":[\"0\"],\"status\":[\"0\"],\"roleIds\":[\"1\"],\"postIds\":[\"\"]}', '0', null, '2018-09-08 21:03:44'), ('127', '菜单管理', '1', 'com.jd.project.system.menu.controller.MenuController.addSave()', '1', 'admin', '研发一部', '/system/menu/add', '0:0:0:0:0:0:0:1', 'XX XX', '{\"parentId\":[\"2003\"],\"menuType\":[\"F\"],\"menuName\":[\"流程部署查看\"],\"url\":[\"\"],\"perms\":[\"activiti:deploy:view\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"status\":[\"0\"]}', '0', null, '2018-09-08 21:39:21'), ('128', '菜单管理', '2', 'com.jd.project.system.menu.controller.MenuController.editSave()', '1', 'admin', '研发一部', '/system/menu/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"menuId\":[\"2003\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"流程定义\"],\"url\":[\"#\"],\"perms\":[\"activiti:processDef:view\"],\"orderNum\":[\"3\"],\"icon\":[\"#\"],\"status\":[\"0\"]}', '0', null, '2018-09-08 21:40:38'), ('129', '菜单管理', '2', 'com.jd.project.system.menu.controller.MenuController.editSave()', '1', 'admin', '研发一部', '/system/menu/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"menuId\":[\"2015\"],\"parentId\":[\"2003\"],\"menuType\":[\"F\"],\"menuName\":[\"流程部署\"],\"url\":[\"#\"],\"perms\":[\"activiti:deploy:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"status\":[\"0\"]}', '0', null, '2018-09-08 21:41:30'), ('130', '菜单管理', '2', 'com.jd.project.system.menu.controller.MenuController.editSave()', '1', 'admin', '研发一部', '/system/menu/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"menuId\":[\"2015\"],\"parentId\":[\"2003\"],\"menuType\":[\"C\"],\"menuName\":[\"流程部署\"],\"url\":[\"/activiti/deploy/list\"],\"perms\":[\"activiti:deploy:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"status\":[\"0\"]}', '0', null, '2018-09-08 21:41:57'), ('131', '角色管理', '2', 'com.jd.project.system.role.controller.RoleController.editSave()', '1', 'admin', '研发一部', '/system/role/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"roleId\":[\"1\"],\"roleName\":[\"管理员\"],\"roleKey\":[\"admin\"],\"roleSort\":[\"1\"],\"status\":[\"0\"],\"remark\":[\"管理员\"],\"menuIds\":[\"1,100,1000,1001,1002,1003,1004,1005,101,1006,1007,1008,1009,1010,102,1011,1012,1013,1014,103,1015,1016,1017,1018,105,1024,1025,1026,1027,10', '0', null, '2018-09-08 21:42:25'), ('132', '菜单管理', '2', 'com.jd.project.system.menu.controller.MenuController.editSave()', '1', 'admin', '研发一部', '/system/menu/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"menuId\":[\"2015\"],\"parentId\":[\"2003\"],\"menuType\":[\"C\"],\"menuName\":[\"流程部署\"],\"url\":[\"/activiti/deploy/modelList\"],\"perms\":[\"activiti:deploy:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"status\":[\"0\"]}', '0', null, '2018-09-08 21:47:21'), ('133', '菜单管理', '1', 'com.jd.project.system.menu.controller.MenuController.addSave()', '1', 'admin', '研发一部', '/system/menu/add', '0:0:0:0:0:0:0:1', 'XX XX', '{\"parentId\":[\"2015\"],\"menuType\":[\"F\"],\"menuName\":[\"流程部署查看\"],\"url\":[\"\"],\"perms\":[\"activiti:deploy:view\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"status\":[\"0\"]}', '0', null, '2018-09-08 22:03:30'), ('134', '菜单管理', '1', 'com.jd.project.system.menu.controller.MenuController.addSave()', '1', 'admin', '研发一部', '/system/menu/add', '0:0:0:0:0:0:0:1', 'XX XX', '{\"parentId\":[\"2015\"],\"menuType\":[\"F\"],\"menuName\":[\"流程部署删除\"],\"url\":[\"\"],\"perms\":[\"activiti:deploy:delete\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"status\":[\"0\"]}', '0', null, '2018-09-08 22:03:58'), ('135', '角色管理', '2', 'com.jd.project.system.role.controller.RoleController.editSave()', '1', 'admin', '研发一部', '/system/role/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"roleId\":[\"1\"],\"roleName\":[\"管理员\"],\"roleKey\":[\"admin\"],\"roleSort\":[\"1\"],\"status\":[\"0\"],\"remark\":[\"管理员\"],\"menuIds\":[\"1,100,1000,1001,1002,1003,1004,1005,101,1006,1007,1008,1009,1010,102,1011,1012,1013,1014,103,1015,1016,1017,1018,105,1024,1025,1026,1027,10', '0', null, '2018-09-08 22:04:06'), ('136', '菜单管理', '2', 'com.jd.project.system.menu.controller.MenuController.editSave()', '1', 'admin', '研发一部', '/system/menu/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"menuId\":[\"2015\"],\"parentId\":[\"2004\"],\"menuType\":[\"C\"],\"menuName\":[\"流程部署\"],\"url\":[\"/activiti/deploy/modelList\"],\"perms\":[\"activiti:deploy:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"status\":[\"0\"]}', '0', null, '2018-09-10 09:28:37'), ('137', '菜单管理', '2', 'com.jd.project.system.menu.controller.MenuController.editSave()', '1', 'admin', '研发一部', '/system/menu/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"menuId\":[\"2015\"],\"parentId\":[\"2004\"],\"menuType\":[\"C\"],\"menuName\":[\"流程部署\"],\"url\":[\"/activiti/deploy/modelList\"],\"perms\":[\"activiti:deploy:view\"],\"orderNum\":[\"6\"],\"icon\":[\"#\"],\"status\":[\"0\"]}', '0', null, '2018-09-10 09:28:57'), ('138', '菜单管理', '2', 'com.jd.project.system.menu.controller.MenuController.editSave()', '1', 'admin', '研发一部', '/system/menu/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"menuId\":[\"2015\"],\"parentId\":[\"2004\"],\"menuType\":[\"C\"],\"menuName\":[\"流程部署\"],\"url\":[\"/activiti/deploy/modelList\"],\"perms\":[\"activiti:deploy:view\"],\"orderNum\":[\"6\"],\"icon\":[\"#\"],\"status\":[\"1\"]}', '0', null, '2018-09-10 09:30:08'), ('139', '菜单管理', '2', 'com.jd.project.system.menu.controller.MenuController.editSave()', '1', 'admin', '研发一部', '/system/menu/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"menuId\":[\"2015\"],\"parentId\":[\"2004\"],\"menuType\":[\"C\"],\"menuName\":[\"流程部署\"],\"url\":[\"/activiti/deploy/modelList\"],\"perms\":[\"activiti:deploy:view\"],\"orderNum\":[\"6\"],\"icon\":[\"#\"],\"status\":[\"1\"]}', '0', null, '2018-09-10 09:30:40'), ('140', '菜单管理', '2', 'com.jd.project.system.menu.controller.MenuController.editSave()', '1', 'admin', '研发一部', '/system/menu/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"menuId\":[\"2015\"],\"parentId\":[\"2004\"],\"menuType\":[\"C\"],\"menuName\":[\"流程部署\"],\"url\":[\"/activiti/deploy/modelList\"],\"perms\":[\"activiti:deploy:view\"],\"orderNum\":[\"6\"],\"icon\":[\"#\"],\"status\":[\"1\"]}', '0', null, '2018-09-10 09:32:17'), ('141', '菜单管理', '2', 'com.jd.project.system.menu.controller.MenuController.editSave()', '1', 'admin', '研发一部', '/system/menu/edit', '0:0:0:0:0:0:0:1', 'XX XX', '{\"menuId\":[\"2015\"],\"parentId\":[\"2004\"],\"menuType\":[\"C\"],\"menuName\":[\"流程部署\"],\"url\":[\"/activiti/deploy/modelList\"],\"perms\":[\"activiti:deploy:view\"],\"orderNum\":[\"6\"],\"icon\":[\"#\"],\"visible\":[\"1\"]}', '0', null, '2018-09-10 09:35:54');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', '1', '0', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-08 22:04:06', '管理员'), ('2', '普通角色', 'common', '2', '0', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', '普通角色');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
--  Records of `sys_role_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES ('1', '1'), ('1', '3'), ('1', '100'), ('1', '101'), ('1', '102'), ('1', '103'), ('1', '105'), ('1', '108'), ('1', '112'), ('1', '113'), ('1', '1000'), ('1', '1001'), ('1', '1002'), ('1', '1003'), ('1', '1004'), ('1', '1005'), ('1', '1006'), ('1', '1007'), ('1', '1008'), ('1', '1009'), ('1', '1010'), ('1', '1011'), ('1', '1012'), ('1', '1013'), ('1', '1014'), ('1', '1015'), ('1', '1016'), ('1', '1017'), ('1', '1018'), ('1', '1024'), ('1', '1025'), ('1', '1026'), ('1', '1027'), ('1', '1028'), ('1', '1038'), ('1', '1039'), ('1', '1040'), ('1', '1041'), ('1', '1054'), ('1', '1055'), ('1', '2000'), ('1', '2001'), ('1', '2002'), ('1', '2003'), ('1', '2004'), ('1', '2005'), ('1', '2006'), ('1', '2007'), ('1', '2008'), ('1', '2009'), ('1', '2010'), ('1', '2011'), ('1', '2012'), ('1', '2013'), ('1', '2014'), ('1', '2015'), ('1', '2016'), ('1', '2017');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐加密',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(20) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', '106', 'admin', '邦道', '00', 'admin@qq.com', '18888888888', '1', '', '29c67a30398638269fe600f73a054934', '111111', '0', '0', '0:0:0:0:0:0:0:1', '2018-09-10 10:07:39', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-10 10:07:39', '管理员'), ('2', '108', 'jd', '邦道', '00', 'jd@sohu.com', '15666666666', '1', '', '8e6d98b90472783cc73c17047ddccf36', '222222', '0', '0', '127.0.0.1', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', 'admin', '2018-09-03 10:12:20', '测试员');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- ----------------------------
--  Records of `sys_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '1'), ('2', '2');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
