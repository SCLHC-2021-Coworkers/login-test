package internaltest.springsecuritylogin.auth.entities;

import javax.persistence.*;

@Entity
@Table(name = "Setting", uniqueConstraints = { @UniqueConstraint(name = "unique_setting_name", columnNames = "name") })
public class Setting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String value;

	protected Setting() {
	}

	public Setting(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format("Setting[name=%s]", name);
	}

}
