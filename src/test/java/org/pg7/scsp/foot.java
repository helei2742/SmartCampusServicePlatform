package org.pg7.scsp;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.pg7.scsp.dto.CourseTestFormDTO;
import org.pg7.scsp.entity.*;
import org.pg7.scsp.mapper.*;
import org.pg7.scsp.query.CourseQuery;
import org.pg7.scsp.service.impl.SeckillCourseOrderServiceImpl;
import org.pg7.scsp.service.impl.SeckillCourseServiceImpl;
import org.pg7.scsp.service.impl.TestServiceImpl;
import org.pg7.scsp.utils.RedisIdWorker;
import org.pg7.scsp.utils.SemesterUtil;
import org.pg7.scsp.utils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
public class foot {
    private static final int STUDENTCOUNT = 50;
    private static final int TEACHERCOUNT = 20;
    @Resource
    UserMapper userMapper;
    @Resource
    CourseMapper courseMapper;

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    CollageMapper collageMapper;

    @Resource
    MajorMapper majorMapper;

    @Resource
    UserCourseRecordMapper userCourseRecordMapper;

    private static String[] userNamePre = new String[]{
            "大方","年轻","聪明","雪白","漂","笔直","固定","平等","优秀","慌张","俗气","马虎"
        ,"博学","主观","明快","高兴","幸福","清楚","明确","结实","具体","伟大","勇敢","坚强","温柔","平淡",
           "早红","亮","香","稳","热","喜","闹","开朗","害羞","活泼","热情","漂亮","帅气",
            "笑呵呵","抠门儿","急性子","忙忙叨叨","稀里糊涂","密密麻麻"
    };
    private static String[] userName = new String[]{
            "张伟",	"张薇","张玮","张巍","张炜","张威","张维","张蔚"	,"张微"	,"张卫",
            "张敏","张民","张旻","张珉","章敏"	,"张闽"	,"闵章"	,"闵璋"	,"张岷",	"闵张"
    };

    private static String[] ebgs = new String[]{
            "本科","大专","小学","职高","研究生","日本普通高中生"
    };
    private static String[] majorName = new String[]{
            "金融学","土木工程","国际经济与贸易",
            "机械设计制造及其自动化","电气工程及其自动化","会计学","经济学","临床医学","英语","法学",
            "电子信息工程","计算机科学与技术","通信工程","工商管理","自动化",
    };
    private static String[] semesters = new String[]{
            "2001年秋季", "2001年春季","2002年秋季", "2002年春季","2003年秋季", "2003年春季","2004年秋季", "2004年春季",
            "2005年秋季", "2005年春季","2006年秋季", "2006年春季","2007年秋季", "2007年春季","2007年秋季", "2007年春季",
            "2008年秋季", "2008年春季","2009年秋季", "2009年春季","2010年秋季", "2010年春季","2011年秋季", "2011年春季",
            "2012年秋季", "2012年春季","2013年秋季", "2013年春季","2014年秋季", "2014年春季","2015年秋季", "2015年春季",
            "2016年秋季", "2016年春季","2017年秋季", "2017年春季","2018年秋季", "2018年春季","2019年秋季", "2019年春季",
            "2020年秋季", "2020年春季","2021年秋季", "2021年春季","2021年秋季", "2021年春季","2022年秋季", "2022年春季",
    };
    private static String[] courseName = new String[]{
            "体育","军事理论","机械制图", "算法语言","大学物理","物理实验","线性代数","法律基础","普通物理","普通物理实验",
            "复变函数与积分变换","电路理论","电路测试技术","概率论与随机过程","信号与线性系统","电子线路","脉冲与数字电路","金工实习",
    "电工实习","电子线路实验","微机原理","电磁场与电磁波","电机电器与供电","计算方法", "软件技术基础", "微波技术","数字图象处理"};

    private static int[] courseTimeStart = new int[]{1,3,5,8,10};
    private static Random random = new Random();
    private String getRandomStudentName(){
        return userNamePre[random.nextInt(userNamePre.length)] +
                "的学生" + userName[random.nextInt(userName.length)];
    }
    private String getRandomTeacherName(){
        return userNamePre[random.nextInt(userNamePre.length)] +
                "的教师" + userName[random.nextInt(userName.length)];
    }
    public static String characters1 = "0123456789";
    public static String randomStr(int factor){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < factor; i++) {
            // nextInt(10) = [0, 10)
            sb.append(characters1.charAt(random.nextInt(10)));
        }
        return sb.toString();
    }
    private String getRandomIdNumber(){

        return "ID"+ randomStr(13);
    }

    private String getRandomEbg(){
        return ebgs[random.nextInt(ebgs.length)];
    }

    private User getRandomStudent(int majorId){
        User user = new User();
        user.setTrueName(getRandomStudentName());
        user.setIdNumber(getRandomIdNumber());
        user.setEbg(getRandomEbg());
        user.setMajorId(majorId);
        user.setPassword("123456");
        return user;
    }
    private User getRandomTeacher(){
        User user = new User();
        user.setTrueName(getRandomTeacherName());
        user.setIdNumber(getRandomIdNumber());
        user.setEbg(getRandomEbg());
        user.setPassword("123456");
        return user;
    }
    private UserInfo getRandomUserInfo(int userId){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setDormitory("江安校区西园"+random.nextInt(5));
        userInfo.setHomeAddress("中国四川成都");
        userInfo.setChinaId(RandomStringUtils.random(14));
        userInfo.setEmail(RandomStringUtils.random(9)+"@qq.com");
        userInfo.setPhone("19142349984");
        return userInfo;
    }

    private String getRandomSemester(){
        return semesters[random.nextInt(semesters.length)];
    }

    private String getRandomCourseName(){
        return courseName[random.nextInt(courseName.length)];
    }

    private Course getRandomCourse(Integer majorId,String semester){
        Course course = new Course();
        course.setCourseName(getRandomCourseName());
        course.setSemester(semester);
        course.setCredit((float) (random.nextInt(5)+0.5));
        course.setDetail("课程详情");
        course.setMajorId(majorId);
        return course;
    }

    private Major getRandomMajor(int collageId){
        Major major = new Major();
        major.setName(majorName[random.nextInt(majorName.length-1)]);
        major.setManagerName("专业老大666");
        major.setCollegeId(collageId);
        major.setPeopleCount(random.nextInt(125)+5);
        major.setDetail("很强到专业");
        return major;
    }

    private UserCourseRecord getRandomUserCourseRecord(Integer userId, Integer courseId,String courseName,String semester){
        UserCourseRecord userCourseRecord = new UserCourseRecord();
        userCourseRecord.setCourseId(courseId);
        userCourseRecord.setUserId(userId);
        userCourseRecord.setCount(1);
        userCourseRecord.setCourseName(courseName);
        userCourseRecord.setScore(random.nextInt(101));
        userCourseRecord.setSemester(semester);
        return userCourseRecord;
    }

    @Test
    void startFoot(){
        //TODO 插入基本但学院信息，专业信息，老师学生信息，课程信息，学生的选课信息。

        //生成5个学院，插入取出
        Collage collage = new Collage();
        collage.setDetail("学院详情");
        collage.setPeopleCount(500);
        collage.setManagerName("张三");
        collage.setName("文学与新闻学院");
        collageMapper.insert(collage);
        collage.setManagerName("李四");
        collage.setName("数学学院");
        collageMapper.insert(collage);
        collage.setManagerName("王武");
        collage.setName("物理学院");
        collageMapper.insert(collage);
        collage.setManagerName("赵六");
        collage.setName("体育学院");
        collageMapper.insert(collage);
        collage.setManagerName("马力");
        collage.setName("计算机学院");
        collageMapper.insert(collage);

        List<Collage> collages = collageMapper.selectList(null);
        //生成20个专业，插入取出
        for (Collage co : collages) {
            for (int j = 0; j < 4; j++) {
                Major randomMajor = getRandomMajor(co.getId());
                majorMapper.insert(randomMajor);
            }
        }

        List<Major> majors = majorMapper.selectList(null);
        //生成50个学生用户
        for (int i = 0; i < STUDENTCOUNT; i++) {
            int majorId = majors.get(random.nextInt(majors.size())).getId();
            User user = getRandomStudent(majorId);
            userMapper.insert(user);
        }

        //取出所有学生，产生随机信息插入userInfo
        List<User> students = userMapper.selectList(null);
        for (User student : students) {
            //设置角色
            userMapper.setUserRole(student.getId(), 1);
            //产生随机userInfo
            UserInfo userInfo = getRandomUserInfo(student.getId());
            userInfoMapper.insert(userInfo);
        }


        //生成20个老师，然后取出全部老师
        for (int i = 0; i < TEACHERCOUNT; i++) {
            User user = getRandomTeacher();
            userMapper.insert(user);
        }

        List<User> teachers = userMapper.selectPage(new Page<>(2, 50), null).getRecords();
        for (User teacher : teachers) {
            //设置角色
            userMapper.setUserRole(teacher.getId(), 2);
        }

        //生成课程,每个学期的每个专业都生成15个课程
        for (String semester : semesters) {
            for (Major major : majors) {
                for (int i = 0; i < 15; i++) {
                    Course randomCourse = getRandomCourse(major.getId(), semester);
                    courseMapper.insert(randomCourse);
                }
            }
        }

        List<Course> courses = courseMapper.selectList(null);

        for (Course course : courses) {
            //课程添加随机的时间
            int startTime = courseTimeStart[random.nextInt(courseTimeStart.length)];
            int move = random.nextInt(7) * 12;
            if(startTime == 1 || startTime == 3 || startTime == 8){
                courseMapper.setCourseTime(course.getId(), startTime + move);
                courseMapper.setCourseTime(course.getId(), startTime + move + 1);
            }else if(startTime == 5|| startTime == 10){
                courseMapper.setCourseTime(course.getId(), startTime + move);
                courseMapper.setCourseTime(course.getId(), startTime + move + 1);
                courseMapper.setCourseTime(course.getId(), startTime + move + 2);
            }


            //课程添加随机老师
            int times = random.nextInt(3)+1;
            for (int i = 0; i < times; i++) {
                int t = random.nextInt(teachers.size());
                courseMapper.setCourseTeacher(course.getId(), teachers.get(t).getId());
            }
        }

        //学生添加课程
        for (User student : students) {
            try {
                //一个学生选80个课
                for (int i = 0; i < 80; i++) {
                    int cidx = random.nextInt(courses.size());
                    Course course = courses.get(cidx);

                    UserCourseRecord courseRecord = getRandomUserCourseRecord(student.getId(),
                            course.getId(), course.getCourseName(), course.getSemester());
                    userCourseRecordMapper.insert(courseRecord);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        List<UserCourseRecord> userCourseRecords = userCourseRecordMapper.selectList(null);

        System.out.println("执行完毕");
        System.out.println("插入学生:"+students.size());
        System.out.println("插入老师："+teachers.size());
        System.out.println("插入课程："+courses.size());
        System.out.println("插入选课记录："+userCourseRecords.size());
    }


    @Test
    public void setRetakeCourse(){
        //TODO 对学生选的课按课程名分组后学期排序，然后将选课次数依次加1，表示第几次选该课


        //查询用户
        List<User> users = userMapper.selectList(null);
        //遍历用户
        for (User user : users) {
            //查询用户选的全部课程
            QueryWrapper<UserCourseRecord> queryWrapper= new QueryWrapper<>();
            queryWrapper.eq("user_id", user.getId());
            List<UserCourseRecord> list = userCourseRecordMapper.selectList(queryWrapper);

            HashMap<String, List<UserCourseRecord>> map = new HashMap<>();

            for (UserCourseRecord userCourseRecord : list) {
                String courseName = userCourseRecord.getCourseName();
                if(!map.containsKey(courseName)){
                    map.put(courseName, new ArrayList<>());
                }
                map.get(courseName).add(userCourseRecord);
            }

            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                List<UserCourseRecord> t = map.get(key);
                t.sort(new Comparator<UserCourseRecord>() {
                    @Override
                    public int compare(UserCourseRecord o1, UserCourseRecord o2) {
                        return o1.getSemester().compareTo(o2.getSemester());
                    }
                });
                for (int i = 0; i < t.size(); i++) {
                    UserCourseRecord record = t.get(i);
                    Integer courseId = record.getId();
                    Integer count = i+1;
                    UserCourseRecord update = new UserCourseRecord();
                    update.setId(courseId);
                    update.setCount(count);
                    userCourseRecordMapper.updateById(update);
                }
            }
        }
    }



    @Resource
    SeckillCourseMapper seckillCourseMapper;
    @Test
    public void setSeckillCourse(){
        //TODO 设置当前学期选课表，并删除之前随机生成到的当前学期的选课记录

        String currentSemester = SemesterUtil.getCurrentSemester();


        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("semester", currentSemester);
        List<Course> courses = courseMapper.selectList(wrapper);

        for (Course cours : courses) {
            //设置当前学期的选课秒杀表
            SeckillCourse seckillCourse = new SeckillCourse();
            seckillCourse.setCourseId(cours.getId());
            seckillCourse.setStock(RandomUtil.randomInt(50, 100));
            seckillCourse.setStartTime(LocalDateTime.now());
            seckillCourse.setEndTime(LocalDateTime.now().plusDays(60));
            seckillCourseMapper.insert(seckillCourse);

            //删除当前学期选过的记录
            UpdateWrapper<UserCourseRecord> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("course_id", cours.getId());
            int delete = userCourseRecordMapper.delete(updateWrapper);
        }

        //选课信息加到redis中
        seckillCourseService.addSeckillCourseStockToRedis();
    }
    @Autowired
    SeckillCourseServiceImpl seckillCourseService;

    @Resource
    private SeckillCourseOrderMapper seckillCourseOrderMapper;

    @Test
    public void jiesuanSeckillCourse(){
        //TODO 结算秒杀课程，
        QueryWrapper<SeckillCourseOrder> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("status", SystemConstants.SECKILL_COURSE_ORDER_STATUSE_WAIT);
        List<SeckillCourseOrder> seckillCourseOrders = seckillCourseOrderMapper.selectList(orderQueryWrapper);
        for (SeckillCourseOrder seckillCourseOrder : seckillCourseOrders) {
            Integer userId = seckillCourseOrder.getUserId();
            Integer courseId = seckillCourseOrder.getCourseId();
            QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
            courseQueryWrapper.eq("id", courseId);
            Course course = courseMapper.selectOne(courseQueryWrapper);

            QueryWrapper<UserCourseRecord> userCourseRecordQueryWrapper = new QueryWrapper<>();
            userCourseRecordQueryWrapper.eq("user_id", userId)
                    .eq("course_name", course.getCourseName());
            Integer count = userCourseRecordMapper.selectCount(userCourseRecordQueryWrapper);

            UserCourseRecord userCourseRecord = new UserCourseRecord();
            userCourseRecord.setUserId(userId);
            userCourseRecord.setCourseId(courseId);
            userCourseRecord.setCourseName(course.getCourseName());
            userCourseRecord.setScore(0);
            userCourseRecord.setSemester(course.getSemester());
            userCourseRecord.setCount(count);

            userCourseRecordMapper.insert(userCourseRecord);

            seckillCourseOrder.setStatus(SystemConstants.SECKILL_COURSE_ORDER_STATUSE_SETTLE);
            seckillCourseOrder.setSettleTime(LocalDateTime.now());
            seckillCourseOrder.setUpdateTime(null);
            seckillCourseOrder.setCreateTime(null);
            seckillCourseOrder.setUserId(null);
            seckillCourseOrder.setCourseId(null);

            seckillCourseOrderMapper.updateById(seckillCourseOrder);
        }

        //删除数据库秒杀课信息
        seckillCourseMapper.delete(null);
        //删除redis秒杀课信息
        Set<String> keys = stringRedisTemplate.keys("seckill:course:*");
        System.out.println(keys);
        stringRedisTemplate.delete(keys);
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Resource
    CourseTestMapper courseTestMapper;

    @Test
    public void setCourseTest(){

        //TODO 设置考试课程

//        String currentSemester = SemesterUtil.getCurrentSemester();


        QueryWrapper<Course> wrapper = new QueryWrapper<>();
//        wrapper.eq("semester", currentSemester);
        wrapper.eq("semester", "2021年春季");
        List<Course> courses = courseMapper.selectList(wrapper);

        for (Course cours : courses) {
            CourseTest test = new CourseTest();

            test.setCourseId(cours.getId());
            test.setCourseName(cours.getCourseName());
            test.setStartTime(LocalDateTime.now());
//            test.setTestType(1);
//            test.setTestType(2);
            test.setTestType(3);
            test.setEndTime(LocalDateTime.now().plusDays(60));
            test.setLocation("江安一教405");

            courseTestMapper.insert(test);
        }

    }

    @Resource
    UserTestRecordMapper userTestRecordMapper;
    @Resource
    RedisIdWorker redisIdWorker;

    @Test
    public void setUserTestRecord(){
        //TODO 对考试添加考试的学生（选了该课的就会被添加）

        QueryWrapper<CourseTest> courseTestQueryWrapper = new QueryWrapper<>();
        courseTestQueryWrapper.eq("test_type", SystemConstants.COURSE_TEST_TYPE_MID);
//        courseTestQueryWrapper.eq("test_type", SystemConstants.COURSE_TEST_TYPE_END);
        List<CourseTest> courseTests = courseTestMapper.selectList(courseTestQueryWrapper);

        for (CourseTest courseTest : courseTests) {

            QueryWrapper<UserCourseRecord> wrapper = new QueryWrapper<>();

            wrapper.eq("course_id", courseTest.getCourseId());

            List<UserCourseRecord> userCourseRecords = userCourseRecordMapper.selectList(wrapper);

            for (UserCourseRecord userCourseRecord : userCourseRecords) {
                long nextId = redisIdWorker.nextId(SystemConstants.REDIS_ID_WORKER_KEY_COURSETEST);

                UserTestRecord userTestRecord = new UserTestRecord();
                userTestRecord.setId(nextId);
                userTestRecord.setUserId(userCourseRecord.getUserId());
                userTestRecord.setTestId(courseTest.getId());
                userTestRecord.setScore((float) RandomUtil.randomDouble(0,100));

                //TODO 设置考试状态
//                userTestRecord.setStatus(SystemConstants.COURSE_TEST_RECORD_STATUS_UNSTART);
//                userTestRecord.setStatus(SystemConstants.COURSE_TEST_RECORD_STATUS_UNJOIN);
                userTestRecord.setStatus(SystemConstants.COURSE_TEST_RECORD_STATUS_JOINED);
//                userTestRecord.setStatus(SystemConstants.COURSE_TEST_RECORD_STATUS_CANCEL);

                userTestRecordMapper.insert(userTestRecord);
            }
        }
    }

    @Autowired
    TestServiceImpl testService;

    @Test
    public void createCourseReTest(){

        //TODO 对有不及格学生对期末考试的考试创建补考，并给不及格的学生添加起对应不及格考试的补考

        List<CourseTest> courseTests = courseTestMapper.selectList(null);
        for (CourseTest courseTest : courseTests) {
            CourseTestFormDTO courseTestFormDTO = new CourseTestFormDTO();

            courseTestFormDTO.setCourseId(courseTest.getCourseId());
            courseTestFormDTO.setStartTime(LocalDateTime.now());
            courseTestFormDTO.setEndTime(LocalDateTime.now().plusDays(7));
            courseTestFormDTO.setLocation(RandomUtil.randomString("补考场地",10));

            testService.createReTestByCourseId(courseTestFormDTO);
        }

    }

}
