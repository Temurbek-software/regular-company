package regular.admin.com.controller;

import org.springframework.web.bind.annotation.*;
import regular.model.com.dto.CourseDTO;
import regular.model.com.payload.ApiResult;
import regular.service.com.service.course.CourseService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/admin/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping()
    public ApiResult<List<CourseDTO>> showAllCourses() {
        return courseService.listOfCourse();
    }

    @GetMapping("/{id}")
    public ApiResult<CourseDTO> findCourseById(@PathVariable Long id) {
        return courseService.getOneCourse(id);
    }

    @PostMapping()
    public ApiResult<String> insertNewCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.addCourse(courseDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> deleteCourseById(@PathVariable Long id) {
        return courseService.deleteCourseById(id);
    }

    @PutMapping("/{id}")
    public ApiResult<String> editCourseById(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        return courseService.editStudentById(id, courseDTO);
    }
}
