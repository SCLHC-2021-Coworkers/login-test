package internaltest.springsecuritylogin.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {

	@NotBlank
	@Pattern(
            regexp = "^[A-Za-z0-9_]*$",
            message = "영문, 숫자, \"_\"만 입력 가능합니다")
	@Size(min = 5, max = 12)
	private String username;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 8, max = 60)
	private String password;

	@NotBlank
	private String name;

	@NotBlank
	private String position;

	public UserForm() {
	}

	public UserForm(String username, String email, String password, String name, String position) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.position = position;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return String.format("UserForm[username=%s]", username);
	}

}
