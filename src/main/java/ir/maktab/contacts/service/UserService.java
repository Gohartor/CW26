package ir.maktab.contacts.service;

import ir.maktab.contacts.dto.MyUserDetails;
import ir.maktab.contacts.entity.User;
import ir.maktab.contacts.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
