package ir.maktab.contacts.service;

import ir.maktab.contacts.dto.MyUserDetails;
import ir.maktab.contacts.dto.RegisterRequest;
import ir.maktab.contacts.entity.Role;
import ir.maktab.contacts.entity.User;
import ir.maktab.contacts.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    @Transactional
//    public User register(RegisterRequest registerRequest) {
//        User user = new User();
//
//                user.setUserName(registerRequest.username());
//                user.setPassword(passwordEncoder.encode(registerRequest.password()));
//                user.setRoles(Set.of(new Role().setName("ADMIN");));
//
//        return userRepository.save(user);
//    }

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUserName(String username){
        return userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserDetails(userRepository.findByUserName(username)
                        .orElseThrow(
                                () -> new UsernameNotFoundException("not found")
                        ));
    }
}
