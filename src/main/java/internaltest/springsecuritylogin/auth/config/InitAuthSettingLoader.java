package internaltest.springsecuritylogin.auth.config;

import internaltest.springsecuritylogin.auth.entities.Setting;
import internaltest.springsecuritylogin.auth.repositories.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitAuthSettingLoader implements ApplicationRunner {

	private final SettingRepository settingRepository;

    @Autowired
    public InitAuthSettingLoader(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

	@Override
	public void run(ApplicationArguments args) {
		Setting addUserPermission = new Setting("PERMIT_REGISTER", "on");

		if (!settingRepository.existsSettingByName(addUserPermission.getName())) {
			settingRepository.save(addUserPermission);
		}
	}

}
