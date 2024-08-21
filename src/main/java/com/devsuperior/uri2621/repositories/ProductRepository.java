package com.devsuperior.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projection.ProductMinProjection;

public interface ProductRepository extends JpaRepository<Product, Long> {
	/*SQL RAIZ*/
	
	@Query(nativeQuery = true, value = "SELECT p.name "
			+ "FROM products p "
			+ "INNER JOIN providers pr ON p.id_providers = pr.id "
			+ "WHERE p.amount BETWEEN :min AND :max "
			+ "AND pr.name LIKE CONCAT(:name, '%')")
	List<ProductMinProjection> search1(@Param("min") Integer min, 
                                        @Param("max") Integer max, 
                                        @Param("name") String name);
	
/*JPQL*/
	
	
	@Query("SELECT new com.devsuperior.uri2621.dto.ProductMinDTO(obj.name) "
			+ "FROM Product obj "
			+ "WHERE obj.amount BETWEEN :min AND :max "
			+ "AND obj.provider.name LIKE CONCAT(:name, '%')")
	List<ProductMinDTO> search2(@Param("min") Integer min, 
                                        @Param("max") Integer max, 
                                        @Param("name") String name);

}
