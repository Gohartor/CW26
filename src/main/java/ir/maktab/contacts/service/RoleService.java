package ir.maktab.contacts.service;

import ir.maktab.contacts.entity.Role;
import ir.maktab.contacts.entity.User;
import ir.maktab.contacts.repository.RoleRepository;
import ir.maktab.contacts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserService userService;
    private final UserRepository userRepository;


    public void createRole(String roleName) {
        if (roleRepository.findByName(roleName).isPresent() ){
            throw new RuntimeException("Role already exists");
        }
        Role role = new Role(roleName);
        roleRepository.save(role);
    }
    public void addRoleToUser(Long userId,String roleName) {
        User user = userService.findById(userId).orElseThrow((() -> new RuntimeException("User not found")));
        Role role=roleRepository.findByName(roleName).orElseThrow((() -> new RuntimeException("Role not found")));
        if(user.getRoles().contains(role)){
            throw new RuntimeException(" Role has alreadu assign");
        }
        user.addRole(role);
        userRepository.save(user);
    }

}
