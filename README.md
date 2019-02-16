# tyilack-game-assist


windows程序启动表
```sql
CREATE TABLE `game_windows_runner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) NOT NULL COMMENT '游戏ID',
  `program_source` varchar(255) NOT NULL COMMENT '运行程序路径',
  `location` varchar(255) DEFAULT NULL COMMENT '区域找图定位图片，或者某个具体坐标如：11,110',
  `operation` varchar(255) DEFAULT NULL COMMENT '操作指令，包括鼠标操作和键盘操作',
  `duration` int(11) DEFAULT '1000' COMMENT '操作延时',
  `repeat` int(11) DEFAULT '1' COMMENT '操作延时',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

游戏表
```sql
CREATE TABLE `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '游戏名称',
  `logo` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '下边tab图标',
  `status` int(11) DEFAULT '0' COMMENT '游戏状态，0->未启动，1->配置完成可启动任务，2->正在执行任务',
  `complete_operation` int(11) DEFAULT '0' COMMENT '任务执行完成之后的操作，0->待命，1->关机',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

任务表
```sql
CREATE TABLE `game_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) NOT NULL COMMENT '游戏ID',
  `trigger_time` datetime DEFAULT NULL COMMENT '游戏触发时间',
  `location` varchar(255) DEFAULT NULL COMMENT '区域找图定位图片，或者某个具体坐标如：11,110',
  `operation` varchar(255) DEFAULT NULL COMMENT '操作指令，包括鼠标操作和键盘操作',
  `duration` int(11) DEFAULT '1000' COMMENT '操作延时',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

指令集表
```sql
CREATE TABLE `game_command_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '指令集名称',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

游戏任务指令集表
```sql
CREATE TABLE `game_task_command_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL COMMENT '任务ID',
  `command_group_id` int(11) NOT NULL COMMENT '指令集ID',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

指令集指令表
```sql
CREATE TABLE `game_command_group_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL COMMENT '指令集ID',
  `location` varchar(255) DEFAULT NULL COMMENT '区域找图定位图片，或者某个具体坐标如：11,110',
  `operation` varchar(255) DEFAULT NULL COMMENT '操作指令，包括鼠标操作和键盘操作',
  `duration` int(11) DEFAULT '1000' COMMENT '操作延时',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

前置指令表
```sql
CREATE TABLE `game_pre_command` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) NOT NULL COMMENT '游戏ID',
  `name` varchar(255) NOT NULL COMMENT '前置指令名称',
  `condition` varchar(255) DEFAULT NULL COMMENT '执行条件，一般是存在某个图表执行指定操作',
  `location` varchar(255) DEFAULT NULL COMMENT '区域找图定位图片，或者某个具体坐标如：11,110',
  `operation` varchar(255) DEFAULT NULL COMMENT '操作指令，包括鼠标操作和键盘操作',
  `duration` int(11) DEFAULT '1000' COMMENT '操作延时',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

游戏参数表
```sql
CREATE TABLE `game_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '参数名',
  `desc` varchar(255) DEFAULT NULL COMMENT '参数描述',
  `value` varchar(255) DEFAULT NULL COMMENT '参数值',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

定位指令说明（location字段）：
```
image{a.png} : 一张图片
images{a.png,b.png} : 多张图片


```
