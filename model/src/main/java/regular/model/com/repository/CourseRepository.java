package regular.model.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import regular.model.com.entity.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long>
{
    @Query(value = "select * from course",nativeQuery = true)
    List<Course> getAllByCourses();
}
