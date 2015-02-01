package sk.vracon.sqlcomments.maven.domain;


public class Users {

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
        builder.append("Users [companyid=");
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
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Users other = (Users) obj;
		if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        return true;
    }    
    
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}