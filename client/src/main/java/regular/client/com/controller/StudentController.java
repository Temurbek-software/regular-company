package regular.client.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import regular.model.com.dto.StudentDTO;
import regular.model.com.payload.ApiResult;
import regular.service.com.service.student.StudentService;

@RestController
@RequestMapping("/api/client/student")
@RequiredArgsConstructor
public class StudentController
{
    private final StudentService studentService;
    @PostMapping("/{id}/enrolled")
    public ApiResult<?> saveStudent(@RequestBody StudentDTO studentDTO, @PathVariable Long id)
    {
      return studentService.addStudentWithId(id,studentDTO);
    }

}
