package com.luizalabs.teste.service;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.luizalabs.teste.helper.OrganizeHelper;
import com.luizalabs.teste.model.PayLoadIn;
import com.luizalabs.teste.model.PayLoadOut;
import com.luizalabs.teste.model.Product;

/**
 * Classe de serviço para organização dos dados de entrada
 */
public class PayLoadInService {

	/**
	 * Método para organizar a lista de entrada de produtos
	 * @param PayLoadIn payLoadIn
	 * @return ArrayList<PayLoadOut>
	 * @throws Exception
	 */
	public ArrayList<PayLoadOut> organize(PayLoadIn payLoadIn) throws Exception {
		ArrayList<PayLoadOut> outPut = new ArrayList<PayLoadOut>();
		ArrayList<Product> produtos = payLoadIn.getProducts();
		if (payLoadIn.getFilter() != null && !payLoadIn.getFilter().equals("")) {
			produtos = filterCustom(produtos, payLoadIn.getFilter());
		}
		if (payLoadIn.getGroupBy() != null && !payLoadIn.getGroupBy().equals("")) {
			outPut = groupByCustom(produtos, payLoadIn.getGroupBy());
		} else {
			outPut = groupByCustom(produtos, "ean");
			outPut.addAll(groupByCustom(produtos, "title"));
			outPut.addAll(groupByCustom(produtos, "brand"));
		}
		if (payLoadIn.getOrderBy() != null && !payLoadIn.getOrderBy().equals("")) {
			outPut = orderByCustom(outPut, payLoadIn.getOrderBy());
		} else {
			outPut = orderByDefault(outPut);
		}
		return outPut;
	}

	/**
	 * Método para agrupar a lista de produtos na ordem padrão ou na ordem
	 * definida pelo usuário
	 * @param ArrayList<Product> products
	 * @param String groupBy
	 * @return ArrayList<PayLoadOut>
	 * @throws Exception
	 */
	public ArrayList<PayLoadOut> groupByCustom(ArrayList<Product> products, String groupBy) throws Exception {
		ArrayList<PayLoadOut> outs = new ArrayList<PayLoadOut>();
		Stream<Product> streamP = products.stream()
				.filter(OrganizeHelper.distinctByKey(OrganizeHelper.getFieldComparator(groupBy)));
		ArrayList<Product> listDistinctCustom = streamP.collect(Collectors.toCollection(ArrayList::new));
		for (int i = 0; i < listDistinctCustom.size(); i++) {
			Object custom = OrganizeHelper.getCustomFieldValue(listDistinctCustom, i, groupBy);
			Stream<Product> streamF = products.stream()
					.filter(OrganizeHelper.getGroupByComparator(groupBy, custom.toString()));
			PayLoadOut out = new PayLoadOut();
			ArrayList<Product> listOfProducts = streamF.collect(Collectors.toCollection(ArrayList::new));
			out.setItems(listOfProducts);
			out.setDescription(custom.toString());
			outs.add(out);
		}
		return outs;
	}

	/**
	 * Método para ordenar a lista de produtos na ordem padrão 
	 * definida pelo usuário
	 * @param ArrayList<PayLoadOut> payLoadOut
	 * @param String groupBy
	 * @return ArrayList<PayLoadOut>
	 * @throws Exception
	 */
	private ArrayList<PayLoadOut> orderByDefault(ArrayList<PayLoadOut> payLoadOut) throws Exception {
		for (int i = 0; i < payLoadOut.size(); i++) {
			ArrayList<Product> listOfProducts = payLoadOut.get(i).getItems();
			Stream<Product> stream = listOfProducts.stream()
					.sorted(reverseOrder(comparing(Product::getStock)).thenComparing(Product::getPrice));
			listOfProducts = stream.collect(Collectors.toCollection(ArrayList::new));
			payLoadOut.get(i).setItems(listOfProducts);
		}
		return payLoadOut;

	}

	/**
	 * Método para ordenar a lista de produtos na ordem
	 * definida pelo usuário
	 * @param ArrayList<PayLoadOut> payLoadOut
	 * @param String groupBy
	 * @return ArrayList<PayLoadOut>
	 * @throws Exception
	 */
	private ArrayList<PayLoadOut> orderByCustom(ArrayList<PayLoadOut> payLoadOut, String orderBy) throws Exception {
		String order[] = orderBy.split(";");
		for (int e=0; e < order.length; e ++) {
			String order2[] = order[e].split(":");
			for (int i = 0; i < payLoadOut.size(); i++) {
				ArrayList<Product> listOfProducts = payLoadOut.get(i).getItems();
				Stream<Product> stream = listOfProducts.stream().sorted(OrganizeHelper.getComparator(order2));
				listOfProducts = stream.collect(Collectors.toCollection(ArrayList::new));
				payLoadOut.get(i).setItems(listOfProducts);
			}
		}		
		return payLoadOut;
	}

	/**
	 * Método para filtrar a lista de produtos 
	 * @param ArrayList<PayLoadOut> payLoadOut
	 * @param ArrayList<Product> products
	 * @param String filter
	 * @return ArrayList<Product>
	 * @throws Exception
	 */
	private ArrayList<Product> filterCustom(ArrayList<Product> products, String filter) throws Exception {
		String filterField_[] = filter.split(";");
		ArrayList<Product> listOfProducts = new ArrayList<>();
		for (int e=0;e < filterField_.length; e ++) {
			String filterField[] = filterField_[e].split(":");
			Stream<Product> streamF = products.stream()
					.filter(OrganizeHelper.getGroupByComparator(filterField[0], filterField[1]));
			listOfProducts = streamF.collect(Collectors.toCollection(ArrayList::new));
		}		
		return listOfProducts;

	}

}
