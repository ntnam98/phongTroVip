package BEAN;

public class Report {
	private int rpId;
	private int postId;
	private String type;
	private String phone;
	private String content;
	private boolean isSolve;
	private String createdAt;
	private int accId;
	private String name;
	private String url;
	public int getRpId() {
		return rpId;
	}
	public void setRpId(int rpId) {
		this.rpId = rpId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}

