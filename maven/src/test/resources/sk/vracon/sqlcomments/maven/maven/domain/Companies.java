package sk.vracon.sqlcomments.maven.domain;

import sk.vracon.sqlcomments.maven.ExampleEnum;

public class Companies {

	private String city;
	private sk.vracon.sqlcomments.maven.ExampleEnum country;
	private String email;
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
	
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Companies [city=");
        builder.append(city);
        builder.append(", country=");
        builder.append(country);
        builder.append(", email=");
        builder.append(email);
        builder.append(", id=");
        builder.append(id);
        builder.append(", ipAddress=");
        builder.append(ipAddress);
        builder.append(", name=");
        builder.append(name);
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
        Companies other = (Companies) obj;
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