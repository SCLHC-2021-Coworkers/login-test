package internaltest.springsecuritylogin.auth.dto;

public class UserResponse {

	private final long id;
	private final String username;
	private final String name;
	private final String position;

	public UserResponse(long id, String username, String name, String position) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.position = position;
	}

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public String getPosition() {
		return position;
	}

}
