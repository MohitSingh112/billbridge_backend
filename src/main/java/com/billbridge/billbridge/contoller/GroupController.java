package com.billbridge.billbridge.contoller;

import com.billbridge.billbridge.model.Group;
import com.billbridge.billbridge.model.User;
import com.billbridge.billbridge.services.GroupService;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestParam String name,
                                             @RequestParam(required = false) String description,
                                             @AuthenticationPrincipal User creator) {
        return ResponseEntity.ok(groupService.createGroup(name, description, creator));
    }

    @PostMapping("/{groupId}/add")
    public ResponseEntity<String> addMember(@PathVariable Long groupId,
                                            @RequestParam String userEmail,
                                            @AuthenticationPrincipal User requester) {
        groupService.addMemberToGroup(groupId, userEmail, requester);
        return ResponseEntity.ok("User added to group.");
    }

    @GetMapping
    public ResponseEntity<List<Group>> getUserGroups(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(groupService.getGroupsForUser(user));
    }
}

