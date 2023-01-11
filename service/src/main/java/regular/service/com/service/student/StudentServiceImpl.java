package regular.service.com.service.student;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import regular.model.com.component.MessageService;
import regular.model.com.dto.StudentDTO;
import regular.model.com.entity.Course;
import regular.model.com.entity.Student;
import regular.model.com.payload.ApiResult;
import regular.model.com.repository.CourseRepository;
import regular.model.com.repository.StudentRepository;
import regular.service.com.exception.RestException;
import regular.service.com.mapper.CourseMapper;
import regular.service.com.mapper.CourseMapperImpl;
import regular.service.com.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/***
 * @apiNote Working with Student services which includes CRUD
 * @author Temurbek
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    public StudentDTO mapToStudentDTO(Student student) {
        if (student == null) {
            return null;
        }
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setUsername(student.getUsername());
        studentDTO.setPhoneNumber(student.getPhoneNumber());
        studentDTO.setStudentCourse(student.getStudentCourse()
                .stream().map(course -> courseMapper.mapToCourseDTO(course))
                .collect(Collectors.toSet()));
        return studentDTO;
    }

    @Override
    public ApiResult<List<StudentDTO>> listOfStudent() {
        List<StudentDTO> studentDTOList = studentRepository.findAll()
                .stream().map(this::mapToStudentDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(studentDTOList);
    }

    @Override
    public ApiResult<?> addStudentWithId(long id, StudentDTO studentDTO) {
        Course course = courseRepository.findById(id).orElseThrow(() -> RestException
                .restThrow(MessageService.getMessage("ID_NOT_FOUND"), HttpStatus.NOT_FOUND));
        Student student = studentMapper.mapToStudent(studentDTO);
        student.addCourse(course);

        return null;
    }

    //    Get Student by ID
    @Override
    public ApiResult<StudentDTO> getOneStudent(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> RestException
                .restThrow(MessageService.getMessage("ID_NOT_FOUND"), HttpStatus.NOT_FOUND));
        return ApiResult.successResponse(mapToStudentDTO(student));
    }

    //  Add Student only without course ID
    @Override
    @Transactional
    public ApiResult<String> addStudent(StudentDTO studentDTO) {
        Student student = studentMapper.mapToStudent(studentDTO);
        studentRepository.save(student);
        return ApiResult.successResponse("Saved entity");
    }

    //   Delete Student By ID
    @Override
    @Transactional
    public ApiResult<?> deleteStudentById(long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            student.get().removeCourses();
            studentRepository.deleteById(student.get().getId());
        } else {
            throw RestException.restThrow(MessageService.getMessage("ID_NOT_FOUND"), HttpStatus.NOT_FOUND);
        }
        return ApiResult.successResponse();
    }

    public void mapDtoToEntity(StudentDTO studentDTO, Student student) {
        student.setUsername(studentDTO.getUsername());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        if (student.getStudentCourse() == null) {
            student.setStudentCourse(new HashSet<>());
        }

        studentDTO.getStudentCourse().stream()
                .forEach(courseDTO -> {
                    Course course=courseMapper.mapToCourse(courseDTO);
                    student.addCourse(course);
                });
    }


    //    Edit Student with StudentDTO and ID
    @Override
    @Transactional
    public ApiResult<String> editStudentById(long id, StudentDTO studentDTO)
    {
        Student student = studentRepository.getOne(id);
        student.getStudentCourse().clear();
        if (null == student.getStudentCourse()) {
            student.setStudentCourse(new HashSet<>());
        }
        student.setUsername(studentDTO.getUsername());
        student.setPhoneNumber(studentDTO.getPhoneNumber());

        studentDTO.getStudentCourse().stream().forEach(
                courseDTO -> {
                    Course course=courseMapper.mapToCourse(courseDTO);
                    student.addCourse(course);
                }
        );
        studentRepository.save(student);
        return ApiResult.successResponse(MessageService.getMessage("SAVING_ENTITY"));
    }
}
