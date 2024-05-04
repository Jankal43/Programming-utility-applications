package com.example.demo.group;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GroupConfig {

    @Bean
    CommandLineRunner commandLineRunnerGroup(GroupRepository groupRepository){
        return args -> {
            Group WF = new Group("WF",5,0,0,0);
            Group ENG = new Group("ENG",5,0,0,0);
            groupRepository.saveAll(List.of(WF,ENG));

        };
    }
}
