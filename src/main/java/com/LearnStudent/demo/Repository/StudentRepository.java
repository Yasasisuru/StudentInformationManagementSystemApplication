package com.LearnStudent.demo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.LearnStudent.demo.Entity.Student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE " +
            "LOWER(s.registrationNumber) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(s.fullName) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Student> searchStudents(@Param("query") String query);
}
