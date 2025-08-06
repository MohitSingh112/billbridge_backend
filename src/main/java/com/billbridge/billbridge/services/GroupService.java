package com.billbridge.billbridge.services;


import com.billbridge.billbridge.model.Group;
import com.billbridge.billbridge.model.User;
import com.billbridge.billbridge.repository.GroupRepository;
import com.billbridge.billbridge.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepo;
    private final UserRepository userRepo;

    public Group createGroup(String name, String description, User creator) {
        Group group = new Group();
        group.setName(name);
        group.setDescription(description);
        group.setCreatedBy(creator);

        // Add creator to members
        group.setMembers(new HashSet<>());
        group.getMembers().add(creator);

        return groupRepo.save(group);
    }

    @Transactional
    public void addMemberToGroup(Long groupId, String email, User requester) {
        Group group = groupRepo.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        if (!group.getMembers().contains(requester)) {
            throw new RuntimeException("You are not a member of this group");
        }

        User userToAdd = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        group.getMembers().add(userToAdd);
        groupRepo.save(group);
    }

    public List<Group> getGroupsForUser(User user) {
        return groupRepo.findByMembersContaining(user);
    }
}

