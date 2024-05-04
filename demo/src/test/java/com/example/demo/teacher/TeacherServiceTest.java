package com.example.demo.teacher;

import com.example.demo.group.Group;
import com.example.demo.group.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.teacher.TeacherCondition.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTeachers() {
        // Given
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1, "John", "Doe", OBECNY, 1980, 1));
        teachers.add(new Teacher(2, "Jane", "Smith", NIEOBECNY, 1975, 2));

        when(teacherRepository.findAll()).thenReturn(teachers);

        // When
        List<Teacher> result = teacherService.getTeachers();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void testAddNewTeacher_Success() {
        // Given
        Teacher teacherToAdd = new Teacher("John", "Doe", OBECNY, 1980, 1);
        Group group = new Group(1, "Math", 10);
        when(groupRepository.existsById(1)).thenReturn(true);
        when(groupRepository.findById(1)).thenReturn(Optional.of(group));
        when(teacherRepository.findByGroupId(1)).thenReturn(new ArrayList<>());

        // When
        teacherService.addNewTeacher(teacherToAdd);

        // Then
        verify(teacherRepository, times(1)).save(teacherToAdd);
    }

    @Test
    void testAddNewTeacher_GroupDoesNotExist() {
        // Given
        Teacher teacherToAdd = new Teacher("John", "Doe", OBECNY, 1980, 1);
        when(groupRepository.existsById(1)).thenReturn(false);

        // When, Then
        assertThrows(IllegalStateException.class, () -> teacherService.addNewTeacher(teacherToAdd));
    }

    // Add more test cases as needed
}
