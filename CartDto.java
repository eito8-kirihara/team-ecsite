package jp.co.internous.altair.model.domain.dto;

import java.sql.Timestamp;

public class CartDto {
	
	/* tbl_cart.id */
	private int id;
	/* mst_product.product_name */
	private String productName;
	/* mst_product.image_full_path */
	private String imageFullPath;
	/* mst_product.price */
	private int price;
	/* tbl_cart.product_count */
	private int productCount;
	/* 小計 tbl_cart.product_count,mst_product.priceの積 */
	private int subtotal;
	/* 登録日時 */
	private Timestamp createdAt;
	/* 更新日時 */
	private Timestamp updatedAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getImageFullPath() {
		return imageFullPath;
	}
	public void setImageFullPath(String imageFullPath) {
		this.imageFullPath = imageFullPath;
	}
	
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
