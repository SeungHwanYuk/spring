package DW.firstapp.service;

import DW.firstapp.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    public int getStudentScore(Student student) {
        System.out.println(student.getFirstName() + " " + student.getLastName());
        return 100;
    }
}
