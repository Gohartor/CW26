//package ir.maktab.contacts.service;
//
//
//import ir.maktab.contacts.dto.MyUserDetails;
//import ir.maktab.contacts.entity.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Component
//public class MyUserDetailService implements UserDetailsService {
//
//    private final UserService userService;
//
//
//    public MyUserDetailService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return new MyUserDetails(
//                userService.findByUserName(username));
//    }
//}
