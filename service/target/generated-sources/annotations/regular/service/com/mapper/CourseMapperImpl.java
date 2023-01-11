package regular.service.com.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import regular.model.com.dto.CourseDTO;
import regular.model.com.entity.Course;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-10T23:35:17+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseDTO mapToCourseDTO(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDTO.CourseDTOBuilder courseDTO = CourseDTO.builder();

        if ( course.getId() != null ) {
            courseDTO.id( course.getId() );
        }
        courseDTO.courseName( course.getCourseName() );
        courseDTO.coursePrice( course.getCoursePrice() );
        courseDTO.periodOfCourse( course.getPeriodOfCourse() );

        return courseDTO.build();
    }

    @Override
    public Course mapToCourse(CourseDTO courseDTO) {
        if ( courseDTO == null ) {
            return null;
        }

        Course course = new Course();

        course.setCourseName( courseDTO.getCourseName() );
        course.setCoursePrice( courseDTO.getCoursePrice() );
        course.setPeriodOfCourse( courseDTO.getPeriodOfCourse() );

        return course;
    }
}
