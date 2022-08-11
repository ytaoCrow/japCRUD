package com.example.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @PostMapping("/students")
    public String insert(@RequestBody Student student){

        studentRepo.save(student);

        return "執行資料庫 Create 操作";
    }

    @PutMapping("/students/{studentId}")
    public String update(@PathVariable Integer studentId,
                         @RequestBody Student student){
     Student s = studentRepo.findById(studentId).orElse(null);

        if(s != null){
            s.setName(student.name);
            studentRepo.save(s);

            return "執行資料庫 update 操作";
        }else {
            return " 失敗的 update 操作";
        }
    }

    @DeleteMapping("/students/{studentId}")
    public String delete(@PathVariable Integer studentId){


        studentRepo.deleteById(studentId);
        return "執行資料庫 delete 操作";
    }

    @GetMapping("/students/{studentId}")
    public Student read(@PathVariable Integer studentId){

     Student student = studentRepo.findById(studentId).orElse(null);

        return student;
    }
}
