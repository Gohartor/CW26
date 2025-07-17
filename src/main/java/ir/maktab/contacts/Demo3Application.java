package ir.maktab.contacts;

import ir.maktab.contacts.entity.Role;
import ir.maktab.contacts.repository.RoleRepository;
import ir.maktab.contacts.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Demo3Application {
    public static void main(String[] args) {
        SpringApplication.run(Demo3Application.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(UserRepository userRepository, RoleRepository roleRepository) {
//        return args -> {
//
//
//        if(roleRepository.count() == 0) {
//            roleRepository.save(new Role("ROLE_SENIORADMIN"));
//            roleRepository.save(new Role("ROLE_ADMIN"));
//            roleRepository.save(new Role("ROLE_MODERATOR"));
//            roleRepository.save(new Role("ROLE_AUTHOR"));
//            roleRepository.save(new Role("ROLE_SUPERADMIN"));
//        }
//        }
//
//    }
}