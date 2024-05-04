package com.example.demo.teacher;

import com.example.demo.group.Group;
import com.example.demo.group.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.example.demo.teacher.TeacherCondition.OBECNY;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, GroupRepository groupRepository) {
        this.teacherRepository = teacherRepository;
        this.groupRepository = groupRepository;
    }

    public List<Teacher> getTeachers(){
        return teacherRepository.findAll();
    }

    public void addNewTeacher(Teacher teacher) {
        // Sprawdzanie, czy grupa istnieje
        Integer groupId = teacher.getGroupId();
        if (groupId == null || !groupRepository.existsById(groupId)) {
            throw new IllegalStateException("Group with id " + groupId + " does not exist");
        }

        // Pobierz aktualną grupę
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalStateException("Group with id " + groupId + " does not exist"));

        // Sprawdzenie, czy maxCapacity nie zostanie przekroczony
        List<Teacher> teachersInGroup = teacherRepository.findByGroupId(groupId);
        int totalTeachers = teachersInGroup.size();

        if (totalTeachers >= group.getMaxCapacity()) {
            throw new IllegalStateException("Max capacity of the group has been reached");
        }

        // Zapisz nauczyciela
        teacherRepository.save(teacher);

        // Aktualizuj dane grupy
        updateGroupData(groupId);
    }


    public void deleteTeacher(Integer teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalStateException("Teacher with id " + teacherId + " does not exist"));

        teacherRepository.deleteById(teacherId);

        // Aktualizacja danych grupy
        updateGroupData(teacher.getGroupId());
    }
    private void updateGroupData(Integer groupId) {
        if (groupId != null) {
            Group group = groupRepository.findById(groupId)
                    .orElseThrow(() -> new IllegalStateException("Group with id " + groupId + " does not exist"));

            List<Teacher> teachersInGroup = teacherRepository.findByGroupId(groupId);
            int totalTeachers = teachersInGroup.size();

            // Obliczanie fillPercentage
            float fillPercentage = (float) totalTeachers / group.getMaxCapacity() * 100;
            group.setFillPercentage(fillPercentage);

            groupRepository.save(group);
        }
    }

}
