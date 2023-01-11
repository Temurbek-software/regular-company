package regular.service.com.service.student;


import regular.model.com.dto.StudentDTO;
import regular.model.com.payload.ApiResult;

import java.util.List;

public interface StudentService
{
  ApiResult<List<StudentDTO>> listOfStudent();
  ApiResult<StudentDTO> getOneStudent(long id);
  ApiResult<String> addStudent(StudentDTO studentDTO);
  ApiResult<?> addStudentWithId(long id, StudentDTO studentDTO);
  ApiResult<?> deleteStudentById(long id);
  ApiResult<String> editStudentById(long id,StudentDTO studentDTO);
}
