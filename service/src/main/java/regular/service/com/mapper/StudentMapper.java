package regular.service.com.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import regular.model.com.dto.StudentDTO;
import regular.model.com.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper
{
    @Mapping(target = "studentCourse",ignore = true)
    StudentDTO mapToStudentDTO(Student student);

    @Mapping(target = "studentCourse",ignore = true)
    @Mapping(target = "id",ignore = true)
    Student mapToStudent(StudentDTO studentDTO);
}
