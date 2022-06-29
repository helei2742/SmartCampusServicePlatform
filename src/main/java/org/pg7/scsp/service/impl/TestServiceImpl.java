package org.pg7.scsp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pg7.scsp.dto.CourseTestFormDTO;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.CourseTest;
import org.pg7.scsp.entity.UserTestRecord;
import org.pg7.scsp.mapper.CourseTestMapper;
import org.pg7.scsp.service.ITestService;
import org.pg7.scsp.service.IUserTestRecordService;
import org.pg7.scsp.utils.RedisIdWorker;
import org.pg7.scsp.utils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helei
 * @since 2022-06-29
 */
@Service
public class TestServiceImpl extends ServiceImpl<CourseTestMapper, CourseTest> implements ITestService {

    @Autowired
    private IUserTestRecordService userTestRecordService;

    @Autowired
    private RedisIdWorker redisIdWorker;

    @Override
    public Result createReTestByCourseId(CourseTestFormDTO formDTO) {
        int courseId = formDTO.getCourseId();
        //获取课程的期末考试
        CourseTest test = query().eq("course_id", courseId)
                .eq("test_type", SystemConstants.COURSE_TEST_TYPE_END).one();

        if(test == null){
            return Result.fail("没有该课程的期末考试信息!");
        }

        //查询是否生成过补考信息
        Integer count = query().eq("course_id", courseId)
                .eq("test_type", SystemConstants.COURSE_TEST_TYPE_RE).count();

        if(count>=1) {
            return Result.fail("该课程已经生成过补考信息");
        }

        Integer testId = test.getId();

        //1 查询出原来用户考试成绩中不及格的
        List<UserTestRecord> unPasses = userTestRecordService.query()
                .eq("test_id", testId).lt("score", 60).list();

        if(unPasses.size() <= 0){
            return Result.fail("没有需要补考的学生");
        }


        return dbCreateReTest(test.getCourseName(), courseId, formDTO, unPasses);
    }

    @Transactional
    public Result dbCreateReTest(String courseName,Integer courseId,
                                 CourseTestFormDTO formDTO, List<UserTestRecord> unPasses){

        //生成补考信息
        CourseTest reTest = BeanUtil.copyProperties(formDTO, CourseTest.class);
        reTest.setTestType(SystemConstants.COURSE_TEST_TYPE_RE);
        reTest.setCourseName(courseName);
        save(reTest);

        //获取生成的补考信息的id
        CourseTest re = query().eq("course_id", courseId)
                .eq("test_type", SystemConstants.COURSE_TEST_TYPE_RE).one();
        int reTestId = re.getId();

        //给学生添加补考信息
        //2 给不及格的学生生成补考信息
        for (UserTestRecord unPass : unPasses) {
            UserTestRecord utr = BeanUtil.copyProperties(unPass, UserTestRecord.class);
            long l = redisIdWorker.nextId(SystemConstants.REDIS_ID_WORKER_KEY_COURSETEST);
            utr.setId(l);
            utr.setScore(0F);
            utr.setTestId(reTestId);
            boolean save = userTestRecordService.save(utr);
            if(!save) return Result.fail("生成学生补考信息失败");
        }
        return Result.ok();
    }
}
