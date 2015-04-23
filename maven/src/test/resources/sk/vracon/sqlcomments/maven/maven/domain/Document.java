package sk.vracon.sqlcomments.maven.domain;

import java.sql.Blob;

public class Document implements sk.vracon.sqlcomments.maven.IDomain {

	private Blob data;
	private String description;
	private Integer id;
	private String name;
	private Integer userid;

	public Blob getData() {
		return data;
	}

	public void setData(Blob newValue) {
		this.data = newValue;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String newValue) {
		this.description = newValue;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer newValue) {
		this.id = newValue;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String newValue) {
		this.name = newValue;
	}
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer newValue) {
		this.userid = newValue;
	}
	
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Document [data=");
        builder.append(data);
        builder.append(", description=");
        builder.append(description);
        builder.append(", id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", userid=");
        builder.append(userid);
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
        Document other = (Document) obj;
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