package sk.vracon.sqlcomments.maven.generate;


public class DefaultResult {

	private Integer companyid;
	private String country;
	private String email;
	private String firstName;
	private Integer id;
	private String lastName;

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer newValue) {
		this.companyid = newValue;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String newValue) {
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
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String newValue) {
		this.lastName = newValue;
	}
	
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DefaultResult [companyid=");
        builder.append(companyid);
        builder.append(", country=");
        builder.append(country);
        builder.append(", email=");
        builder.append(email);
        builder.append(", firstName=");
        builder.append(firstName);
        builder.append(", id=");
        builder.append(id);
        builder.append(", lastName=");
        builder.append(lastName);
        builder.append("]");
        return builder.toString();
    }
    
}