package com.LearnStudent.demo.Repository;

import com.LearnStudent.demo.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCourseCodeContainingIgnoreCaseOrCourseNameContainingIgnoreCase(String courseCode,
            String courseName);
}
