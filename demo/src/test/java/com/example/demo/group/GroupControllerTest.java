package com.example.demo.group;

import com.example.demo.group.Group;
import com.example.demo.teacher.Teacher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GroupController.class)
@AutoConfigureMockMvc
class GroupControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GroupService groupService;

    @Test
    public void whenGetGroups_thenReturns200() throws Exception {
        List<Group> groups = new ArrayList<>();
        groups.add(new Group("Math", 10));

        when(groupService.getGroups()).thenReturn(groups);

        mockMvc.perform(get("/api/group")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenValidInput_thenReturns200() throws Exception {
        Group group = new Group("Math", 15);

        mockMvc.perform(post("/api/group")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(group)))
                .andExpect(status().isOk());
    }


    @Test
    public void whenInvalidInput_thenReturns400() throws Exception {
        Group group = new Group("Math", 10);
        String errorMessage = "Invalid input";

        doNothing().when(groupService).addNewGroup(any(Group.class));

        mockMvc.perform(post("/api/group")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(group)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> result.getResponse().getContentAsString().equals(errorMessage));
    }

    @Test
    public void whenDeleteGroup_thenReturns200() throws Exception {
        Integer groupId = 1;

        mockMvc.perform(delete("/api/group/{groupId}", groupId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetTeachersByGroupId_thenReturns200() throws Exception {
        Integer groupId = 1;
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("John", groupId));
        teachers.add(new Teacher("Jane", groupId));

        when(groupService.getTeachersByGroupId(groupId)).thenReturn(teachers);

        mockMvc.perform(get("/api/group/{groupId}/teacher", groupId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetGroupFillPercentage_thenReturns200() throws Exception {
        Integer groupId = 1;
        float fillPercentage = 75.0f;

        when(groupService.getGroupFillPercentage(groupId)).thenReturn(fillPercentage);

        mockMvc.perform(get("/api/group/{groupId}/fill", groupId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGroupNotFound_thenReturns404() throws Exception {
        Integer groupId = 1;
        String errorMessage = "Group not found";

        when(groupService.getGroupFillPercentage(groupId)).thenThrow(new IllegalStateException(errorMessage));

        mockMvc.perform(get("/api/group/{groupId}/fill", groupId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> result.getResponse().getContentAsString().equals(errorMessage));
    }
}
