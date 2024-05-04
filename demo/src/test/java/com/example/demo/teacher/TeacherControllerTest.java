package com.example.demo.teacher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetTeachers() {
        // Given
        List<Teacher> teachers = new ArrayList<>();
        when(teacherService.getTeachers()).thenReturn(teachers);

        // When
        List<Teacher> result = teacherController.getTeachers();

        // Then
        assertEquals(teachers, result);
    }

    @Test
    void testRegisterNewTeacher_Success() {
        // Given
        Teacher teacher = new Teacher();
        doNothing().when(teacherService).addNewTeacher(teacher);

        // When
        ResponseEntity<Object> responseEntity = teacherController.registerNewTeacher(teacher);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    void testDeleteTeacher_Success() {
        // Given
        int teacherId = 1;
        doNothing().when(teacherService).deleteTeacher(teacherId);

        // When
        ResponseEntity<Object> responseEntity = teacherController.deleteTeacher(teacherId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    void testRegisterNewTeacher_Failure() {
        // Given
        Teacher teacher = new Teacher();
        String errorMessage = "Teacher not found";
        doThrow(new IllegalStateException(errorMessage)).when(teacherService).addNewTeacher(teacher);

        // When
        ResponseEntity<Object> responseEntity = teacherController.registerNewTeacher(teacher);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }

    @Test
    void testDeleteTeacher_Failure() {
        // Given
        int teacherId = 1;
        String errorMessage = "Teacher not found";
        doThrow(new IllegalStateException(errorMessage)).when(teacherService).deleteTeacher(teacherId);

        // When
        ResponseEntity<Object> responseEntity = teacherController.deleteTeacher(teacherId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }
}
