package com.example.demo.group;

import com.example.demo.teacher.Teacher;
import com.example.demo.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, TeacherRepository teacherRepository) {
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Group> getGroups(){
        return groupRepository.findAll();
    }

    public void addNewGroup(Group group) {
        System.out.println(group);
        groupRepository.save(group);
    }

    public void deleteGroup(Integer groupId) {
        boolean exists = groupRepository.existsById(groupId);

        if(!exists){
            throw new IllegalStateException("Group with id "+ groupId+ " does not exist");
        }
        groupRepository.deleteById(groupId);
    }
    public List<Teacher> getTeachersByGroupId(Integer groupId) {
        // Assuming each teacher has groupId property, adjust this according to your data model
        return teacherRepository.findByGroupId(groupId);
    }
    public float getGroupFillPercentage(Integer groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalStateException("Group with id " + groupId + " does not exist"));
        return group.getFillPercentage();
    }

}
