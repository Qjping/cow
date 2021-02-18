
-- ALTER  TABLE 表名 CHANGE [column] 旧字段名 新字段名 新数据类型;
ALTER TABLE user_define_param add  type int COMMENT '类型，1是用户添加的，2是系统提取的' ;
ALTER TABLE user_define_param add  param_value varchar(255);
ALTER TABLE user_define_param change user_define_parameters param_name varchar(255) ;

