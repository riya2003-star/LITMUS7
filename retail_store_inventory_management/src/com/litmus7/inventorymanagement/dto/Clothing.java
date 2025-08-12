package com.litmus7.inventorymanagement.dto;

public class Clothing extends Product{
	private String size;
    private String material;

    public Clothing(String productId, String productName, String category, String status,
                    double price, String size, String material) {
        super(productId, productName, category, status, price);
        this.setSize(size);
        this.setMaterial(material);
    }

	public String getSize() { return size; }
	public void setSize(String size) { this.size = size; }
	public String getMaterial() { return material; }
	public void setMaterial(String material) { this.material = material; }
}
