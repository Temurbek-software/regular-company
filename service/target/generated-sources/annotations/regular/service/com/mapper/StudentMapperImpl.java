package regular.service.com.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import regular.model.com.dto.StudentDTO;
import regular.model.com.entity.Student;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-11T00:38:57+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentDTO mapToStudentDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDTO.StudentDTOBuilder studentDTO = StudentDTO.builder();

        if ( student.getId() != null ) {
            studentDTO.id( student.getId() );
        }
        studentDTO.username( student.getUsername() );
        studentDTO.phoneNumber( student.getPhoneNumber() );

        return studentDTO.build();
    }

    @Override
    public Student mapToStudent(StudentDTO studentDTO) {
        if ( studentDTO == null ) {
            return null;
        }

        Student student = new Student();

        student.setUsername( studentDTO.getUsername() );
        student.setPhoneNumber( studentDTO.getPhoneNumber() );

        return student;
    }
}
