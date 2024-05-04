package com.example.demo.group;

import com.example.demo.group.Group;
import com.example.demo.group.GroupRepository;
import com.example.demo.group.GroupService;
import com.example.demo.teacher.Teacher;
import com.example.demo.teacher.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private GroupService groupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetGroups() {
        // Given
        List<Group> groups = new ArrayList<>();
        groups.add(new Group("WF", 5, 0, 0, 0));
        groups.add(new Group("ENG", 5, 0, 0, 0));

        when(groupRepository.findAll()).thenReturn(groups);

        // When
        List<Group> result = groupService.getGroups();

        // Then
        assertEquals(2, result.size());
        verify(groupRepository, times(1)).findAll();
    }

    @Test
    void testAddNewGroup() {
        // Given
        Group group = new Group("Math", 10, 0, 0, 0);

        // When
        groupService.addNewGroup(group);

        // Then
        verify(groupRepository, times(1)).save(group);
    }

    @Test
    void testDeleteGroup() {
        // Given
        Integer groupId = 1;

        // When
        when(groupRepository.existsById(groupId)).thenReturn(true);
        groupService.deleteGroup(groupId);

        // Then
        verify(groupRepository, times(1)).deleteById(groupId);
    }

    @Test
    void testGetTeachersByGroupId() {
        // Given
        Integer groupId = 1;
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("John", groupId));
        teachers.add(new Teacher("Jane", groupId));

        when(teacherRepository.findByGroupId(groupId)).thenReturn(teachers);

        // When
        List<Teacher> result = groupService.getTeachersByGroupId(groupId);

        // Then
        assertEquals(2, result.size());
        verify(teacherRepository, times(1)).findByGroupId(groupId);
    }

    @Test
    void testGetGroupFillPercentage() {
        // Given
        Integer groupId = 1;
        Group group = new Group(1, "Math", 10, 50, 2, 4.5f);

        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));

        // When
        float result = groupService.getGroupFillPercentage(groupId);

        // Then
        assertEquals(50, result);
    }
}
