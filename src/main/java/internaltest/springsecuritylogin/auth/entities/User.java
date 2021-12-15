package internaltest.springsecuritylogin.auth.entities;

import internaltest.springsecuritylogin.auth.dto.UserForm;
import internaltest.springsecuritylogin.auth.dto.UserResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "User", uniqueConstraints = { @UniqueConstraint(name = "unique_user_username", columnNames = "username"),
		@UniqueConstraint(name = "unique_user_email", columnNames = "email") })
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String position;

	protected User() {
	}

	public User(String username, String email, String password, String name, String position) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.position = position;
	}

	@Override
	public String toString() {
		return String.format("User[id=%d, username=%s]", id, username);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean passwordMatches(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(password, this.password);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

    public static User getUserByForm(UserForm userForm) {
		return new User(userForm.getUsername(), userForm.getEmail(), userForm.getPassword(), userForm.getName(),
				userForm.getPosition());
	}

	public UserResponse getResponse() {
		return new UserResponse(id, username, name, position);
	}

}
