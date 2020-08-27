package jp.co.internous.altair.model.domain.dto;

public class PurchaseHistoryDto {
	private String purchasedAt;
	private String productName;	
	private int price;
	private int productCount;
	private String familyName;
	private String firstName;	
	private String address;
	
	public PurchaseHistoryDto() {}
	
	public void setPurchasedAt(String purchasedAt) {
		this.purchasedAt = purchasedAt;
	}
	public String getPurchasedAt() {
		return purchasedAt;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductName() {
		return productName;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPrice() {
		return price;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public int getProductCount() {
		return productCount;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getFamilyName() {
		return familyName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
}


