package com.LearnStudent.demo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.LearnStudent.demo.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByRegistrationNumberContainingIgnoreCaseOrFullNameContainingIgnoreCase(String regNo, String name);
}
