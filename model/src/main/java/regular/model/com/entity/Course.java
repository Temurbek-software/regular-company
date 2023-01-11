package regular.model.com.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import regular.model.com.entity.template.AbsLongEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity(name = "course")
public class Course extends AbsLongEntity {
    @Column(name = "courseName")
    private String courseName;

    @Column(name = "coursePrice")
    private String coursePrice;

    @Column(name = "period")
    private String periodOfCourse;

    @ManyToMany(mappedBy = "studentCourse", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Student> courseStudent;

    public void removeStudent(Student student) {
        this.getCourseStudent().remove(student);
        student.getStudentCourse().remove(this);
    }

    public void removeStudents() {
        for (Student student : new HashSet<>(courseStudent)) {
            removeStudent(student);
        }
    }
}
