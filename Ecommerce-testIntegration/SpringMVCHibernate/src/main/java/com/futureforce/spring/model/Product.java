package com.futureforce.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {

@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;

private String name;

@Lob
private String description;

private String category;

private String price;	

private int quantity;

@Lob
private String image_link;



public Product() {
// TODO Auto-generated constructor stub
}

public Product(int id, String name, String description, String category,String price,int quantity,String image_link) {
super();
this.id = id;
this.name = name;
this.description = description;
this.category = category;
this.price=price;
this.quantity=quantity;
this.image_link=image_link;
}

public String getImage_link() {
return image_link;
}

public void setImage_link(String image_link) {
this.image_link = image_link;
}


public int getQuantity() {
return quantity;
}

public void setQuantity(int quantity) {
this.quantity = quantity;
}

public int getId() {
return id;
} 


public String getName() {
return name;
}


public String getDescription() {
return description;
}


public String getCategory() {
return category;
}


public String getPrice() {
return price;
}

public void setCategory(String category) {
this.category = category;
}

public void setDescription(String description) {
this.description = description;
}

public void setId(int id) {
this.id = id;
}

public void setName(String name) {
this.name = name;
}

public void setPrice(String price) {
this.price = price;
}
@Override
public String toString() {
return "Product [id=" + id + ", name=" + name + ", description=" + description + "]";
}
public int getIntPrice()
{
	String str=price;
	str=str.substring(3);
	str=str.trim();
	str=str.replaceAll(",","");
	
	return Integer.parseInt(str);
}
}