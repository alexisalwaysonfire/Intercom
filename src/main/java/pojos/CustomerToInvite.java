package pojos;

public class CustomerToInvite {
	private String name;
	private Integer user_id;
	private double distanceToTheOffice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public double getDistanceToTheOffice() {
		return distanceToTheOffice;
	}

	public void setDistanceToTheOffice(double distanceToTheOffice) {
		this.distanceToTheOffice = distanceToTheOffice;
	}
}
