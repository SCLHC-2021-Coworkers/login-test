package internaltest.springsecuritylogin.auth.repositories;

import internaltest.springsecuritylogin.auth.entities.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SettingRepository extends JpaRepository<Setting, Long> {

	@Transactional(readOnly = true)
	boolean existsSettingByName(String name);

	@Transactional(readOnly = true)
	Setting findByName(String name);

}
