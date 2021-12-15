package internaltest.springsecuritylogin.auth.repositories;

import internaltest.springsecuritylogin.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Transactional(readOnly = true)
	boolean existsUserByUsername(String username);

    @Transactional(readOnly = true)
    boolean existsUserByEmail(String email);

	@Transactional(readOnly = true)
    User findByUsername(String username);

    @Transactional(readOnly = true)
    User findByEmail(String email);

}
