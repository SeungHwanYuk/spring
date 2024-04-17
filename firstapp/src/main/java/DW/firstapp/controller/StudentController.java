package DW.firstapp.controller;

import DW.firstapp.model.Student;
import DW.firstapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;
    @GetMapping("/student") // 객체 하나 보내주기
    public Student getStudent() {
        return new Student("Tom", "Smith");
    }

    @GetMapping("/students") // 객체 여러개 보내주기 (List<> 사용)
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Tom","Smith"));
        students.add(new Student("Yami","Go"));
        students.add(new Student("Steve","White"));
        students.add(new Student("Leon","Red"));
        students.add(new Student("Mike","Tuson"));
        return students;
    }

    @GetMapping("/student/{firstName}/{lastName}") // 주소 변수 방법 = 주소창에 보안이 필요없는 변수를 담는다
    public Student studentPathVariable(@PathVariable String firstName,
                                       @PathVariable String lastName) {
        return new Student(firstName,lastName);
    }

    @GetMapping("/student/query")
    // localhost:8080/student/query?firstName=Tom&lastName=Smith
    public Student studentRequestParam(@RequestParam String firstName,
                                        @RequestParam String lastName) {
        return new Student(firstName, lastName);
    }

    @PostMapping("/student/post")
    public Student studentPost(@RequestBody Student student) {
        System.out.println(student.getFirstName() + " " + student.getLastName());
        return new Student(student.getFirstName(), student.getLastName());
    }

    @GetMapping("/student/score/{firstName}/{lastName}")
    public int getStudentScore(@PathVariable String firstName,
                               @PathVariable String lastName) {
        Student student = new Student(firstName,lastName);
//        StudentService studentService = new StudentService(); //  Bean이므로 인스턴스화 하지 않는다
        return studentService.getStudentScore(student);
    }
}

