package com.luizalabs.teste.common;

/**
 * Classe de produto
 */
public class Product {

	 /**
     * Id do produto
     */
	private String id;
	
	 /**
     * Ean do produto
     */
	private String ean;
	
	 /**
     * Nome do produto
     */
	private String title;
	
	 /**
     * Marca do produto
     */
	private String brand;
	
	 /**
     * Preço do produto
     */
	private double price;
	
	 /**
     * Estoque do produto
     */
	private double stock;	
	
	public Product() {
		
	}
	
	public Product (String id, String ean, String title, String brand, double price, double stock) {
		this.id = id;
		this.ean = ean;
		this.title = title;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEan() {
		return ean;
	}
	public void setEan(String ean) {
		this.ean = ean;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getStock() {
		return stock;
	}
	public void setStock(double stock) {
		this.stock = stock;
	}	
	
	
}
