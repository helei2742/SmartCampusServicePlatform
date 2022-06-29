# SmartCampusServicePlatform
    智慧校园服务平台
        只包含学生使用的接口。对与学生数据、课程数据、考试数据等的插入，
    并没有编写接口。但数据库表完整，在基础上继续编写。
# 开发人员
    何磊
# 接口文档
    接口文档在src/main/resources目录下
        2022暑期实习.apifox.json
    需使用apifox打开
# 数据库
    数据库的sql文件在src/main/resources目录下的sql.sql
## 数据库选择
    关系数据库Mysql 8.x 版本
    非关系形数据库 Redis 
    *请下载安装，并更改application.yml配置文件设置
## 数据库数据随机生成脚本：
    脚本在test目录下java/org/pg7/scsp/foot，执行test即可，
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

# 其它问题
## 1、跨域问题，
    以及配置了CORS跨域处理，在filter/CorsFilter
        修改该行， 
        res.addHeader("Access-Control-Allow-Origin", "http://localhost:8081");
## 2、请求拦截问题
    由于配置有拦截器，对没有token的请求进行拦截（除登录和验证码生成)
    如果不想使用拦截可在config/MVCConfig中修改，将拦截器注释掉即可