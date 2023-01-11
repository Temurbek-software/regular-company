package regular.model.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import regular.model.com.entity.template.AbsLongEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "student")
public class Student extends AbsLongEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "phoneNumber")
    private String phoneNumber;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @JsonIgnore
    private Set<Course> studentCourse;

    public void addCourse(Course course) {
        this.studentCourse.add(course);
        course.getCourseStudent().add(this);
    }

    public void removeCourse(Course course) {
        this.getStudentCourse().remove(course);
        course.getCourseStudent().remove(this);
    }

    public void removeCourses() {
        for (Course course : new HashSet<>(studentCourse)) {
            removeCourse(course);
        }
    }

}
