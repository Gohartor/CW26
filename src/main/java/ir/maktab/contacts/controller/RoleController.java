package ir.maktab.contacts.controller;


import ir.maktab.contacts.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController  {
    RoleService  roleService;

    @PostMapping
    public ResponseEntity<String> addRole(@RequestParam("role")String role) {

        roleService.createRole(role);
        return ResponseEntity.ok("Role has been created");
    }
    @PutMapping
    public ResponseEntity<String> addRoleToUser(@RequestParam("role")String role,@RequestParam("userId")Long userId) {
        roleService.addRoleToUser(userId,role);
        return ResponseEntity.ok("Role has been added to user");
    }


}
