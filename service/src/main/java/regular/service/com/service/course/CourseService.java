package regular.service.com.service.course;



import regular.model.com.dto.CourseDTO;
import regular.model.com.payload.ApiResult;

import java.util.List;

public interface CourseService
{
    ApiResult<List<CourseDTO>> listOfCourse();
    ApiResult<List<CourseDTO>> listOfCourseItself();
    ApiResult<CourseDTO> getOneCourse(long id);
    ApiResult<String> addCourse(CourseDTO courseDTO);
    ApiResult<?> deleteCourseById(long id);
    ApiResult<String> editStudentById(long id,CourseDTO courseDTO);
}
