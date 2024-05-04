package com.example.demo.teacher;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.example.demo.teacher.TeacherCondition.NIEOBECNY;
import static com.example.demo.teacher.TeacherCondition.OBECNY;

@Configuration
public class TeacherConfig {

    @Bean
    CommandLineRunner commandLineRunnerTeacher(TeacherRepository teacherRepository){
        return args -> {
//            Teacher Jan =new Teacher("Jan","Kowalski",OBECNY,2002,1);
//            Teacher Kuba = new Teacher("Kuba","Kow",NIEOBECNY,2001,1);
//            teacherRepository.saveAll(List.of(Jan,Kuba));

        };
    }
}
