package BEAN;

public class FeedBack {
	private int fbId;
	private String name;
	private String phone;
	private String content;
	private boolean isSolve;
	private String createdAt;
	public int getFbId() {
		return fbId;
	}
	public void setFbId(int fbId) {
		this.fbId = fbId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isSolve() {
		return isSolve;
	}
	public void setSolve(boolean isSolve) {
		this.isSolve = isSolve;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
