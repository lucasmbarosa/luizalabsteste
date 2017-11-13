package com.luizalabs.teste.model;

import java.util.ArrayList;

/**
 * Classe de saída com os produtos organizados
 */
public class PayLoadOut {
	
	 /**
     * Descrição do agrupamento de produtos
     */
	private String description;
	
	 /**
     * Lista de produtos organizados e agrupados
     */
	private ArrayList<Product> items;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Product> getItems() {
		return items;
	}

	public void setItems(ArrayList<Product> items) {
		this.items = items;
	}
}
