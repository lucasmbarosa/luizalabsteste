package com.luizalabs.teste.model;

import java.util.ArrayList;

/**
 * Classe que recebe os dados de entrada
 */
public class PayLoadIn {

	/**
	 * Lista de produtos
	 */
	private ArrayList<Product> products;

	/**
	 * Filtro recebido para os produtos
	 */
	private String filter;
	
	 /**
     * Ordenação recebida para os produtos
     */
	private String orderBy;
	
	 /**
     * Agrupamento recebido para os produtos
     */
	private String groupBy;

	public PayLoadIn() {

	}

	public PayLoadIn(ArrayList<Product> products) {
		super();
		this.products = products;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
}
