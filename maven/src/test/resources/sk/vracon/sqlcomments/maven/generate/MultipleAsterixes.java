package sk.vracon.sqlcomments.maven.generate;


public class MultipleAsterixes {

	private String city;
	private sk.vracon.sqlcomments.maven.ExampleEnum country;
	private String email;
	private String firstName;
	private Integer id;
	private String ipAddress;
	private String name;

	public String getCity() {
		return city;
	}

	public void setCity(String newValue) {
		this.city = newValue;
	}
	
	public sk.vracon.sqlcomments.maven.ExampleEnum getCountry() {
		return country;
	}

	public void setCountry(sk.vracon.sqlcomments.maven.ExampleEnum newValue) {
		this.country = newValue;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String newValue) {
		this.email = newValue;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String newValue) {
		this.firstName = newValue;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer newValue) {
		this.id = newValue;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String newValue) {
		this.ipAddress = newValue;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String newValue) {
		this.name = newValue;
	}
	
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MultipleAsterixes [city=");
        builder.append(city);
        builder.append(", country=");
        builder.append(country);
        builder.append(", email=");
        builder.append(email);
        builder.append(", firstName=");
        builder.append(firstName);
        builder.append(", id=");
        builder.append(id);
        builder.append(", ipAddress=");
        builder.append(ipAddress);
        builder.append(", name=");
        builder.append(name);
        builder.append("]");
        return builder.toString();
    }
}