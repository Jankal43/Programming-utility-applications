package com.example.demo.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.teacher.Teacher;

import java.util.List;

@RestController
@RequestMapping(path="/api/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getGroups(){
        return groupService.getGroups();
    }

    @PostMapping
    public ResponseEntity<String> registerNewGroup(@RequestBody Group group){
        if (group == null || group.getClassName() == null || group.getMaxCapacity() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
        }
        try {
            groupService.addNewGroup(group);
            return ResponseEntity.ok("Group registered successfully.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @DeleteMapping(path = "{groupID}")
    public ResponseEntity<String> deleteGroup(@PathVariable("groupID") Integer groupId){
        try {
            groupService.deleteGroup(groupId);
            return ResponseEntity.ok("Group deleted successfully.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{groupId}/teacher")
    public List<Teacher> getTeachersByGroupId(@PathVariable("groupId") Integer groupId) {
        return groupService.getTeachersByGroupId(groupId);
    }

    @GetMapping("/{id}/fill")
    public ResponseEntity<Object> getGroupFillPercentage(@PathVariable("id") Integer groupId) {
        try {
            float fillPercentage = groupService.getGroupFillPercentage(groupId);
            return ResponseEntity.ok().body(fillPercentage);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
