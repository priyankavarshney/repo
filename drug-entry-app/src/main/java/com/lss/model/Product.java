
package com.lss.model;

import javax.persistence.Entity;
import javax.persistence.*;


@Entity
@Table(name="product")
public class Product
{

	private Long id;
	private String name;
	
	public Product()
	{
		
	}
	
	public Product(String name)
	{
		this.name=name;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id=id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	
	
@Override
	public  String toString()
	{
      return "Product[id=" +id + ", name=" +name+ "]";
     
	}
}


