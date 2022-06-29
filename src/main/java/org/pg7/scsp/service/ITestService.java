package org.pg7.scsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pg7.scsp.dto.CourseTestFormDTO;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.CourseTest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helei
 * @since 2022-06-29
 */
public interface ITestService extends IService<CourseTest> {

    /**
     * 根据课程id，创建该课程的补考信息
     * @param courseId
     * @return
     */
    Result createReTestByCourseId(CourseTestFormDTO formDTO);
}
