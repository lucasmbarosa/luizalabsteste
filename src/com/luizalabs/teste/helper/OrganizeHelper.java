package com.luizalabs.teste.helper;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import com.luizalabs.teste.common.Product;

/**
 * Classe helper para organização dos dados de entrada
 */
public final class OrganizeHelper {

	private OrganizeHelper() {

	}

	/**
	 * Método para receber a comparação necessária para a ordenação da lista de
	 * produtos
	 * 
	 * @return Comparator<Product>
	 * @throws Exception
	 */
	public static Comparator<Product> getComparator(String[] order) throws Exception {
		switch (order[0]) {
		case "id":
			if (order[1].toLowerCase().equals("desc"))
				return reverseOrder(comparing(Product::getId));
			else if ((order[1].toLowerCase().equals("asc")))
				return comparing(Product::getId);
		case "ean":
			if (order[1].toLowerCase().equals("desc"))
				return reverseOrder(comparing(Product::getEan));
			else if ((order[1].toLowerCase().equals("asc")))
				return comparing(Product::getEan);
		case "title":
			if (order[1].toLowerCase().equals("desc"))
				return reverseOrder(comparing(Product::getTitle));
			else if ((order[1].toLowerCase().equals("asc")))
				return comparing(Product::getTitle);
		case "brand":
			if (order[1].toLowerCase().equals("desc"))
				return reverseOrder(comparing(Product::getBrand));
			else if ((order[1].toLowerCase().equals("asc")))
				return comparing(Product::getBrand);
		case "price":
			if (order[1].toLowerCase().equals("desc"))
				return reverseOrder(comparing(Product::getPrice));
			else if ((order[1].toLowerCase().equals("asc")))
				return comparing(Product::getPrice);
		case "stock":
			if (order[1].toLowerCase().equals("desc"))
				return reverseOrder(comparing(Product::getStock));
			else if ((order[1].toLowerCase().equals("asc")))
				return comparing(Product::getStock);
		default:
			if (order[1].toLowerCase().equals("desc"))
				return reverseOrder(comparing(Product::getId));
			else if ((order[1].toLowerCase().equals("asc")))
				return comparing(Product::getId);
		}
		return comparing(Product::getId);
	}

	/**
	 * Método para receber a comparação necessária para o agrupamento da lista
	 * de produtos
	 * @param String field
	 * @return <T> Function<? super T, ?>
	 * @throws Exception
	 */
	public static <T> Function<? super T, ?> getFieldComparator(String field) throws Exception {
		switch (field) {
		case "id":
			return product -> ((Product) product).getId();
		case "ean":
			return product -> ((Product) product).getEan();
		case "title":
			return product -> ((Product) product).getTitle();
		case "brand":
			return product -> ((Product) product).getBrand();
		case "price":
			return product -> ((Product) product).getPrice();
		case "stock":
			return product -> ((Product) product).getStock();
		}
		return product -> ((Product) product).getId();
	}

	/**
	 * Método para retornar um objeto para posteriormente pegar o valor do mesmo
	 * para descrição do agrupamento 
	 * @param ArrayList<Product> listDistinctCustom
	 * @param int i
	 * @param String field
	 * @return <T> Function<? super T, ?>
	 * @throws Exception
	 */
	public static Object getCustomFieldValue(ArrayList<Product> listDistinctCustom, int i, String field) {
		switch (field) {
		case "id":
			return listDistinctCustom.get(i).getId();
		case "ean":
			return listDistinctCustom.get(i).getEan();
		case "title":
			return listDistinctCustom.get(i).getTitle();
		case "brand":
			return listDistinctCustom.get(i).getBrand();
		case "price":
			return listDistinctCustom.get(i).getPrice();
		case "stock":
			return listDistinctCustom.get(i).getStock();
		}
		return listDistinctCustom.get(i).getId();
	}

	
	/**
	 * Método para retornar um comparador para o agrupamento
	 * @param String field
	 * @param String custom	 
	 * @return Predicate<? super Product>
	 * @throws Exception
	 */
	public static Predicate<? super Product> getGroupByComparator(String field, String custom) throws Exception {
		switch (field) {
		case "id":
			return product -> product.getId().equals(custom);
		case "ean":
			return product -> product.getEan().equals(custom);
		case "title":
			return product -> product.getTitle().equals(custom);
		case "brand":
			return product -> product.getBrand().equals(custom);
		case "price":
			return product -> product.getPrice() == Double.valueOf(custom);
		case "stock":
			return product -> product.getStock() == (Double.valueOf(custom));
		}
		return product -> product.getId().equals(custom);
	}

	
	/**
	 * Método para retornar as chaves distintas para agrupamento
	 * @param Function<? super T, ?> key
	 * @return <T> Predicate<T>
	 * @throws Exception
	 */
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> key) throws Exception{
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return a -> map.putIfAbsent(key.apply(a), Boolean.TRUE) == null;
	}
}
