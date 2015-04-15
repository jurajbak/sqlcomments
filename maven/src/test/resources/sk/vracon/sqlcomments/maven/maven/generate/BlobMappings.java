package sk.vracon.sqlcomments.maven.generate;

import java.sql.Blob;

public class BlobMappings {

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
        builder.append("BlobMappings [data=");
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
    
}