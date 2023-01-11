package regular.service.com.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import regular.model.com.dto.CourseDTO;
import regular.model.com.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper
{
    @Mapping(target = "courseStudent",ignore = true)
    CourseDTO mapToCourseDTO(Course course);

    @Mapping(target = "courseStudent",ignore = true)
    @Mapping(target = "id",ignore = true)
    Course mapToCourse(CourseDTO courseDTO);
}
