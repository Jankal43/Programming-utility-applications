package com.example.demo.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping(path="/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getTeachers(){
        return teacherService.getTeachers();
    }

    @PostMapping
    public ResponseEntity<Object> registerNewTeacher(@RequestBody Teacher teacher){
        try {
            teacherService.addNewTeacher(teacher);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{teacherID}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable("teacherID") Integer teacherId){
        try {
            teacherService.deleteTeacher(teacherId);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
