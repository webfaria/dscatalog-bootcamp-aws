package com.webfaria.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.webfaria.dscatalog.entities.Product;
import com.webfaria.dscatalog.tests.Factory;

@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repository;

	private long existingId;
	private long nonExistingId;
	private long countTotalProduct;

	@BeforeEach
	void setup() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProduct = 25L;
	}

	@Test
	public void saveShoudPersistWithAutoincrementWhenIdIsNull() {
		Product product = Factory.createdProduct();
		product.setId(null);
		product = repository.save(product);

		Assertions.assertNotNull(product.getId());
		Assertions.assertEquals(countTotalProduct + 1, product.getId());
	}

	@Test
	public void deleteShouldDeleteObjetctWhenIdExists() {
		repository.deleteById(existingId);

		Optional<Product> result = repository.findById(existingId);
		Assertions.assertFalse(result.isPresent());
	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});
	}

	@Test
	public void findByIdShouldReturnNonEmptyOptiontWhenIdExists() {
		Optional<Product> result = repository.findById(existingId);
		Assertions.assertTrue(result.isPresent());
	}

	@Test
	public void findByIdShouldReturnEmptyOptiontWhenIdDoesNotExists() {
		Optional<Product> result = repository.findById(nonExistingId);
		Assertions.assertTrue(result.isEmpty());
	}
}
