package sk.vracon.sqlcomments.maven.generate;


public class SelectWithCollectionAndMapper {

	private Integer id;
	private String name;

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
	
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SelectWithCollectionAndMapper [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append("]");
        return builder.toString();
    }
    
}