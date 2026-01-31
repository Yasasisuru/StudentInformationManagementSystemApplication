package com.LearnStudent.demo.Controller;

import com.LearnStudent.demo.Entity.Course;
import com.LearnStudent.demo.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String listCourses(@RequestParam(name = "q", required = false) String query, Model model) {
        List<Course> courses = courseService.searchCourses(query);
        model.addAttribute("courses", courses);
        model.addAttribute("totalCourses", courseService.getAllCourses().size());
        model.addAttribute("course", new Course());
        model.addAttribute("editMode", false);
        model.addAttribute("q", query);
        return "web/course";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        model.addAttribute("totalCourses", courses.size());
        model.addAttribute("course", course);
        model.addAttribute("editMode", true);
        return "web/course";
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable Long id, @ModelAttribute Course course) {
        course.setId(id);
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}
