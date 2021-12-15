package internaltest.springsecuritylogin.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserEditForm {

	@Pattern(regexp = "^[A-Za-z0-9_]*$")
	@Size(min = 5, max = 12)
	private String username;

	@Email
	private String email;

	@Size(min = 8, max = 60, message = "현재 비밀번호가 8 ~ 60자 사이여야 합니다")
	private String currentPassword;

    @Size(min = 8, max = 60, message = "새 비밀번호가 8 ~ 60자 사이여야 합니다")
	private String newPassword;

	private String name;

	private String position;

	public UserEditForm() {
	}

	public UserEditForm(String username, String email, String currentPassword, String newPassword, String name,
                        String position) {
		this.username = username;
		this.email = email;
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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
		return String.format("UserEditForm[username=%s]", username);
	}

}
