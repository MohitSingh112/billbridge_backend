package com.billbridge.billbridge.repository;

import com.billbridge.billbridge.model.Group;
import com.billbridge.billbridge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByMembersContaining(User user);
}
