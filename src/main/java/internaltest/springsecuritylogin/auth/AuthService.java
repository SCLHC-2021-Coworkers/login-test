package internaltest.springsecuritylogin.auth;

import internaltest.springsecuritylogin.auth.entities.Setting;
import internaltest.springsecuritylogin.auth.entities.User;
import internaltest.springsecuritylogin.auth.repositories.SettingRepository;
import internaltest.springsecuritylogin.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    public static final int OK = 1;
    public static final int DUPLICATED = 4;
    public static final int EMAIL_NOT_VERIFIED = 5;
    private final UserRepository userRepository;
    private final SettingRepository settingRepository;

    @Autowired
    public AuthService(UserRepository userRepository, SettingRepository settingRepository) {
        this.userRepository = userRepository;
        this.settingRepository = settingRepository;
    }
    public int addUser(User user) {
        boolean usernameExists = userRepository.existsUserByUsername(user.getUsername());

        if (usernameExists) {
            return DUPLICATED;
        }
        userRepository.save(user);

        return OK;
    }

    public User selectUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> selectAllUsers() {
        return userRepository.findAll();
    }

    public boolean isAddUserPermitted() {
        Setting addUserPermission = settingRepository.findByName("PERMIT_REGISTER");

        if (addUserPermission == null) {
            return false;
        }

        if (addUserPermission.getValue().equals("on")) {
            return true;
        }

        return false;
    }

    public String getAddUserPermission() {
        Setting addUserPermission = settingRepository.findByName("PERMIT_REGISTER");

        if (addUserPermission == null) {
            return null;
        }

        return addUserPermission.getValue();
    }

    public boolean setAddUserPermission(String value) {
        if (!value.equals("on") && !value.equals("off")) {
            return false;
        }

        Setting addUserPermission = settingRepository.findByName("PERMIT_REGISTER");

        if (addUserPermission == null) {
            return false;
        }

        if (!addUserPermission.getValue().equals(value)) {
            addUserPermission.setValue(value);
            settingRepository.save(addUserPermission);
            return true;
        }

        return false;
    }

    public User getCurrentUser() {
        return userRepository.findByUsername(getAuthContext().getName());
    }

    public Authentication getAuthContext() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
