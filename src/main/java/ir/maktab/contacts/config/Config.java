package ir.maktab.contacts.config;

import ir.maktab.contacts.entity.Role;
import ir.maktab.contacts.entity.User;
import ir.maktab.contacts.repository.RoleRepository;
import ir.maktab.contacts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//@Configuration
@Component
@RequiredArgsConstructor
public class Config implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if (roleRepository.count() == 0) {
            roleRepository.save(new Role("ROLE_SENIORADMIN"));
            roleRepository.save(new Role("ROLE_ADMIN"));
            roleRepository.save(new Role("ROLE_MODERATOR"));
            roleRepository.save(new Role("ROLE_AUTHOR"));
            roleRepository.save(new Role("ROLE_SUPERADMIN"));
        }
        if(userRepository.count() == 0) {
            User user = new User();
            user.setUserName("user1");
            user.setPassword("password1");
            userRepository.save(user);
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
