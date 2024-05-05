package member;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername (String username) throws UsernameNotFoundException {
        return userRepository.findByUsername (username)
            .orElseThrow (
                () -> new UsernameNotFoundException ("User not found with username: " + username));
    }

    @Transactional
    public User saveUser (User user) {
        if (userRepository.findByUsername (user.getUsername ()).isPresent ()) {
            throw new IllegalStateException ("Username already exists");
        }


        user.setPassword (passwordEncoder.encode (user.getPassword ()));
        return userRepository.save (user);
    }
}
