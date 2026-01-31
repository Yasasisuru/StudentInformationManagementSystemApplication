package com.LearnStudent.demo.Controller;

import com.LearnStudent.demo.Entity.Student;
import com.LearnStudent.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String index() {
        return "redirect:/students";
    }

    private final List<String> statuses = Arrays.asList("ACTIVE", "INACTIVE");

    @GetMapping
    public String listStudents(@RequestParam(name = "q", required = false) String query, Model model) {
        List<Student> students = studentService.searchStudents(query);

        model.addAttribute("students", students);
        model.addAttribute("totalStudents", studentService.getAllStudents().size()); // Get absolute total for count
        model.addAttribute("student", new Student());
        model.addAttribute("statuses", statuses);
        model.addAttribute("editMode", false);
        model.addAttribute("q", query);
        return "web/index";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        model.addAttribute("totalStudents", students.size());
        model.addAttribute("student", student);
        model.addAttribute("statuses", statuses);
        model.addAttribute("editMode", true);
        return "web/index";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student) {
        student.setId(id);
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
