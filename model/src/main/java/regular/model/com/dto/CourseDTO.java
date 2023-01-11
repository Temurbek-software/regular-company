package regular.model.com.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO
{
    private long id;
    private String courseName;
    private String coursePrice;
    private String periodOfCourse;
    private Set<StudentDTO> courseStudent;
}
