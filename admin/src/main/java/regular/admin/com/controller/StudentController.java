package regular.admin.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import regular.model.com.dto.StudentDTO;
import regular.model.com.payload.ApiResult;
import regular.service.com.service.student.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping()
    public ApiResult<List<StudentDTO>> showAllStudents() {
        return studentService.listOfStudent();
    }

    @GetMapping("/{id}")
    public ApiResult<StudentDTO> findStudentById(@PathVariable Long id) {
        return studentService.getOneStudent(id);
    }

    @PostMapping()
    public ApiResult<String> insertNewStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.addStudent(studentDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> deleteStudentById(@PathVariable Long id) {
        return studentService.deleteStudentById(id);
    }

    @PutMapping("/{id}")
    public ApiResult<String> editStudentById(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return studentService.editStudentById(id, studentDTO);
    }
}
