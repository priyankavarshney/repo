package com.lss.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lss.exception.ResourceNotFoundException;
import com.lss.model.Product;
import com.lss.respository.ProductRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/api/v1")
@Api(value="Product Management System", description="Operations pertaining to product in Product Management System")

public class ProductServiceController  
{
	@Autowired
	private ProductRepository productrepository;

	
	
	@ApiOperation(value = "View a list of available products", response = List.class)	
	

	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully retrieved list"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
@GetMapping("/products")	
	public List<Product>  getAllProducts()
	{
		return productrepository.findAll();
		
	}
@GetMapping("/products/{id}")
public ResponseEntity<Product>  getProductById(@PathVariable(value= "id") Long productid)
 throws ResourceNotFoundException
 {
	Product product=productrepository.findById(productid)
			.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id::"+productid));
	
	return ResponseEntity.ok().body(product);
		
	
}

@ApiOperation(value="Add a product")
@PostMapping("/products")
public Product createProduct(@Valid @RequestBody Product product)
{
	return productrepository.save(product);
}

@PutMapping("/product/{id}")

public ResponseEntity<Product> updateProduct(@PathVariable(value="id") Long productid, @Valid @RequestBody Product productDetails) throws ResourceNotFoundException
{
	Product product=productrepository.findById(productid)
			.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id::"+ productid));
	
	product.setName(productDetails.getName());
	final Product updateProduct=productrepository.save(product);
	
	return ResponseEntity.ok(updateProduct);
	
	
	
}

@DeleteMapping("/products/{id}")
public Map<String,Boolean> deleteProduct(@PathVariable(value="id") Long productid)
throws ResourceNotFoundException
{
	Product product=productrepository.findById(productid) 
			.orElseThrow(()-> new ResourceNotFoundException("Product not found for this id:"+ productid));
	
	productrepository.delete(product);
	Map<String,Boolean> response=new HashMap<>();
	response.put("deleted",Boolean.TRUE);
	
	return response;



}


}
