package ir.maktab.contacts.config;

import ir.maktab.contacts.entity.Role;
import ir.maktab.contacts.entity.User;
import ir.maktab.contacts.repository.RoleRepository;
import ir.maktab.contacts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//@Configuration
@Component
@RequiredArgsConstructor
public class Config implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if (roleRepository.count() == 0) {
            Role roleSeniorAdmin = roleRepository.save(new Role("SENIORADMIN"));
            Role roleAdmin = roleRepository.save(new Role("ADMIN"));
            Role roleModerator = roleRepository.save(new Role("MODERATOR"));
            Role roleAuthor = roleRepository.save(new Role("AUTHOR"));
            Role roleSuperadmin = roleRepository.save(new Role("SUPERADMIN"));
        }

        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setUserName("user1");
            user1.setPassword(passwordEncoder.encode("password1"));
            user1.addRole(roleRepository.findByName("SENIORADMIN").get());
            user1.setActive(true);
            userRepository.save(user1);

            User user2 = new User();
            user2.setUserName("user2");
            user2.setPassword(passwordEncoder.encode("password1"));
            user2.addRole(roleRepository.findByName("ADMIN").get());
            user2.setActive(true);
            userRepository.save(user2);

            User user3 = new User();
            user3.setUserName("user3");
            user3.setPassword(passwordEncoder.encode("password1"));
            user3.addRole(roleRepository.findByName("MODERATOR").get());
            user3.setActive(true);
            userRepository.save(user3);

            User user4 = new User();
            user4.setUserName("user4");
            user4.setPassword(passwordEncoder.encode("password1"));
            user4.addRole(roleRepository.findByName("AUTHOR").get());
            user4.setActive(true);
            userRepository.save(user4);

            User user5 = new User();
            user5.setUserName("user5");
            user5.setPassword(passwordEncoder.encode("password1"));
            user5.addRole(roleRepository.findByName("SUPERADMIN").get());
            user5.setActive(true);
            userRepository.save(user5);


        }
    }


//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//        return Persistence.createEntityManagerFactory("default");
//    }
//
//    @Bean
//    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
//        return entityManagerFactory.createEntityManager();
//
//    }


}
