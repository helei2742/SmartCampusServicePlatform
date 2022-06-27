# SmartCampusServicePlatform
    智慧校园服务平台

# 开发人员
    何磊
# 接口文档
    接口文档在src/main/resources目录下
        2022暑期实习.apifox.json
    需使用apifox打开
# 数据库
    数据库的sql文件在src/main/resources目录下的sql.sql
## 数据库数据随机生成脚本：
    脚本在test目录下java/org/pg7/scsp/foot，执行test命令即可，
    默认50个学生
       20个老师
       5个学院
       每个学院4个专业
       48个学期
       每个学期每个专业生成15个课程
       每个课程1到3个老师
       课程时间随机，早上两大节，下午两大节，晚上一大节
       一个学生80节课
## 数据库相关数据清除命令
    delete from tb_user  where true;
    ALTER TABLE tb_user AUTO_INCREMENT = 1;
    
    delete from tb_user_info where true;
    
    delete from tb_user_major where true;
    ALTER TABLE tb_user_major AUTO_INCREMENT = 1;
    
    delete from tb_collage  where true;
    ALTER TABLE tb_collage AUTO_INCREMENT = 1;
    
    delete from tb_major where true;
    ALTER TABLE tb_major AUTO_INCREMENT = 1;
    
    
    delete from tb_course where true;
    ALTER TABLE tb_course AUTO_INCREMENT = 1;
    
    
    delete from tb_course_course_time where true;
    
    delete from tb_user_course_record where true;
    ALTER TABLE tb_user_course_record AUTO_INCREMENT = 1;
    
    
    delete from tb_course_teacher where true;