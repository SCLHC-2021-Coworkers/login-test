package internaltest.springsecuritylogin.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import internaltest.springsecuritylogin.auth.entities.User;
import internaltest.springsecuritylogin.auth.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class AppUserDetailService implements UserDetailsService {

	private final UserRepository userRepository;

    @Autowired
    public AppUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("존재하지 않는 사용자입니다 (%s)", username));
		}

		return new AppUserDetail(user);
	}

}
