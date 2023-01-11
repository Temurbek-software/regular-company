package regular.model.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import regular.model.com.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
