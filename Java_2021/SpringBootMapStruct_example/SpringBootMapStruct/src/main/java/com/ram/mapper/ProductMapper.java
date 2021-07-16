package com.ram.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.ram.dto.ProductDTO;
import com.ram.model.Product;

@Mapper
public interface ProductMapper
{
	ProductDTO toProductDTO(Product product);

	List<ProductDTO> toProductDTOs(List<Product> products);

	Product toProduct(ProductDTO productDTO);
}
