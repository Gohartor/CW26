package ir.maktab.contacts.config;

import ir.maktab.contacts.entity.Role;
import ir.maktab.contacts.entity.User;
import ir.maktab.contacts.repository.RoleRepository;
import ir.maktab.contacts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create default roles if they don't exist
        createRoleIfNotExists("SUPERADMIN");
        createRoleIfNotExists("ADMIN");
        createRoleIfNotExists("MODERATOR");
        createRoleIfNotExists("USER");

        // Create a default super admin user if it doesn't exist
        createSuperAdminIfNotExists();
    }

    private void createRoleIfNotExists(String roleName) {
        if (!roleRepository.findByName(roleName).isPresent()) {
            Role role = new Role(roleName);
            roleRepository.save(role);
            System.out.println("Created role: " + roleName);
        }
    }

    private void createSuperAdminIfNotExists() {
        if (!userRepository.existsByUserName("admin")) {
            User adminUser = new User();
            adminUser.setUserName("admin");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setActive(true);

            // Get SUPERADMIN and ADMIN roles
            Role superAdminRole = roleRepository.findByName("SUPERADMIN")
                    .orElseThrow(() -> new RuntimeException("SUPERADMIN role not found"));
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new RuntimeException("ADMIN role not found"));

            Set<Role> roles = new HashSet<>();
            roles.add(superAdminRole);
            roles.add(adminRole);
            adminUser.setRoles(roles);

            userRepository.save(adminUser);
            System.out.println("Created super admin user: admin/admin123");
        }
    }
} 