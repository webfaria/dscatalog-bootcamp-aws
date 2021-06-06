package com.webfaria.dscatalog.tests;

import java.time.Instant;

import com.webfaria.dscatalog.dto.ProductDTO;
import com.webfaria.dscatalog.entities.Category;
import com.webfaria.dscatalog.entities.Product;

public class Factory {

	public static Product createdProduct() {
		Product product = new Product(1L, "Phone", "Good Phone", 800.0, "http://img.com/img.png",
				Instant.parse("2020-07-14T10:00:00Z"));
		product.getCategories().add(createCategory());
		return product;
	}

	public static ProductDTO createdProductDTO() {
		Product product = createdProduct();
		return new ProductDTO(product, product.getCategories());
	}

	public static Category createCategory() {
		return new Category(1L, "Eletronics");
	}
}