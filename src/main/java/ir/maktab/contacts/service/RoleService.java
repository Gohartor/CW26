package ir.maktab.contacts.service;

import ir.maktab.contacts.entity.Role;
import ir.maktab.contacts.entity.User;
import ir.maktab.contacts.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserService userService;


    public void createRole(String roleName) {

        Role role = new Role(roleName);
    }

    public void addRoleToUser(Long userId, Role role) {
        User user = userService.findById(userId).orElseThrow();
    }

}
