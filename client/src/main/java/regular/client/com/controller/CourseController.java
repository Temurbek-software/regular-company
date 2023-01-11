package regular.client.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import regular.model.com.dto.CourseDTO;
import regular.model.com.payload.ApiResult;
import regular.service.com.service.course.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/client/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping()
    public ApiResult<List<CourseDTO>> showAllCourses() {
        return courseService.listOfCourseItself();
    }

    @GetMapping("/{id}")
    public ApiResult<CourseDTO> findCourseById(@PathVariable Long id) {
        return courseService.getOneCourse(id);
    }
    @PostMapping()
    public ApiResult<String> insertNewCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.addCourse(courseDTO);
    }
   }

