package com.lss.respository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;






import com.lss.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
	
	
}

