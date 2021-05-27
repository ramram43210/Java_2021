package com.ram.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.dto.ProductDTO;
import com.ram.mapper.ProductMapper;
import com.ram.model.Product;
import com.ram.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/products")
public class ProductController
{
	private final ProductService productService;
    private final ProductMapper productMapper;

	@PostMapping
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO)
	{
		System.out.println(productDTO);
		Product product = productMapper.toProduct(productDTO);
		System.out.println("productMapper = "+productMapper);
		System.out.println(product);
		System.out.println("productService = "+productService);
		productService.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id)
	{
		Optional<Product> product = productService.findById(id);
		ProductDTO productDTO = productMapper.toProductDTO(product.get());
		return ResponseEntity.ok(productDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id,
			@RequestBody ProductDTO productDTO)
	{
		Product product = productMapper.toProduct(productDTO);
		product.setId(id);

		productService.save(product);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		productService.deleteById(id);

		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll()
	{
		List<ProductDTO> productList = productMapper.toProductDTOs(productService.findAll());
		return ResponseEntity.ok(productList);
	}

}
