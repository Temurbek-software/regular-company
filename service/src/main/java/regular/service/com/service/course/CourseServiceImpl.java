package regular.service.com.service.course;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import regular.model.com.component.MessageService;
import regular.model.com.dto.CourseDTO;
import regular.model.com.entity.Course;
import regular.model.com.entity.Student;
import regular.model.com.payload.ApiResult;
import regular.model.com.repository.CourseRepository;
import regular.service.com.exception.RestException;
import regular.service.com.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import regular.service.com.mapper.StudentMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/***
 * @apiNote Working with Course services which includes CRUD
 * @author Temurbek
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final StudentMapper studentMapper;


    public CourseDTO mapToCourseDTO(Course course) {
        if (course == null) {
            return null;
        }
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setCoursePrice(course.getCoursePrice());
        courseDTO.setPeriodOfCourse(course.getPeriodOfCourse());
        courseDTO.setCourseStudent(course.getCourseStudent()
                .stream().map(student -> studentMapper.mapToStudentDTO(student))
                .collect(Collectors.toSet()));
        return courseDTO;
    }


    @Override
    public ApiResult<List<CourseDTO>> listOfCourseItself() {
        List<CourseDTO> courseDTOList = courseRepository.findAll()
                .stream().map(course -> courseMapper.mapToCourseDTO(course))
                .collect(Collectors.toList());
        return ApiResult.successResponse(courseDTOList);
    }

    @Override
    public ApiResult<List<CourseDTO>> listOfCourse() {
        List<Course> courseList = courseRepository.findAll();
        return ApiResult.successResponse(courseList
                .stream().map(this::mapToCourseDTO)
                .collect(Collectors.toList()));
    }

    @Override
    public ApiResult<CourseDTO> getOneCourse(long id)
    {
        Course student = courseRepository.findById(id).orElseThrow(() -> RestException
                .restThrow(MessageService.getMessage("ID_NOT_FOUND"), HttpStatus.NOT_FOUND));
        return ApiResult.successResponse(courseMapper.mapToCourseDTO(student));
    }

    @Override
    @Transactional
    public ApiResult<String> addCourse(CourseDTO courseDTO) {
        Course course = courseMapper.mapToCourse(courseDTO);
        courseRepository.save(course);
        return ApiResult.successResponse("Saved entity");
    }

    @Override
    @Transactional
    public ApiResult<?> deleteCourseById(long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            course.get().removeStudents();
            courseRepository.deleteById(course.get().getId());
        } else {
            throw RestException.restThrow(MessageService.getMessage("ID_NOT_FOUND"), HttpStatus.NOT_FOUND);
        }
        return ApiResult.successResponse();
    }

    @Override
    @Transactional
    public ApiResult<String> editStudentById(long id, CourseDTO courseDTO) {
        Course course = courseRepository.getOne(id);
        course.getCourseStudent().clear();
        Course student1 = courseMapper.mapToCourse(courseDTO);
        courseRepository.save(student1);
        return ApiResult.successResponse(MessageService.getMessage("SAVING_ENTITY"));
    }
}
