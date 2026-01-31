package com.LearnStudent.demo.Service;

import com.LearnStudent.demo.Entity.Student;
import com.LearnStudent.demo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> searchStudents(String query) {
        if (query != null && !query.isEmpty()) {
            return studentRepository.findByRegistrationNumberContainingIgnoreCaseOrFullNameContainingIgnoreCase(query,
                    query);
        }
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
