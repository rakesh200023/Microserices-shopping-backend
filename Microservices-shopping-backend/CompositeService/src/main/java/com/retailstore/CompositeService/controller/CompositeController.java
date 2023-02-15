package com.retailstore.CompositeService.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.retailstore.CompositeService.Repository.CartCustomer;
import com.retailstore.CompositeService.cartclass.Cart;
import com.retailstore.CompositeService.classes.Inventory;
import com.retailstore.CompositeService.classes.Product;
import com.retailstore.CompositeService.classes.ProductInventory;
import com.retailstore.CompositeService.customerclass.Customer;
import com.retailstore.CompositeService.customerclass.CustomerOrderres;
import com.retailstore.CompositeService.entites.CustomerCart;
import com.retailstore.CompositeService.entites.CustomerOrder;
import com.retailstore.CompositeService.orderclass.LineItems;
import com.retailstore.CompositeService.orderclass.Orders;
import com.retailstore.CompositeService.req.ProductRequest;
import com.retailstore.CompositeService.service.CompositeService;

@RestController
@RequestMapping("/api/shoppingservice/")
public class CompositeController {
	@Autowired
	CompositeService cservice;
	@Autowired
	CartCustomer crepo;
//	@Autowired
//	CustomerOrder corepo;
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/product")
	public String message() {
		return "message";
	}
	
	@PostMapping("/products")
	public ResponseEntity<?> productandinventory(@RequestBody ProductRequest p){
		System.out.println("product   "+p);
		ResponseEntity<Product> s=restTemplate.postForEntity("http://PRODUCT-SERVICE/product/addproduct",p, Product.class);        
		System.out.println("Yesssssssssssssssss"+ s.getBody().getProductId());
		Inventory j=new Inventory();
		j.setProductId(s.getBody().getProductId());
		j.setQuantity(p.getQuantity());
		ResponseEntity<Inventory> i=restTemplate.postForEntity("http://INVENTORY-SERVICE/inventory/addinventory", j, Inventory.class);
		ProductInventory result=new ProductInventory();
		result.setP(s.getBody());
		result.setI(i.getBody());
		System.out.println(i);
		return new ResponseEntity<ProductInventory>(result,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/customer")
	public ResponseEntity<?> customerandcart(@RequestBody Customer c){
		Customer customer =restTemplate.postForObject("http://CUSTOMER-SERVICE/customer/addcustomer", c, Customer.class);
		System.out.println("cusotmerre   "+customer);
		Cart ct=new Cart();
		Cart cart=restTemplate.postForObject("http://CART-SERVICE/cart/addcart", ct, Cart.class);
		System.out.println("carttt   "+cart);
		CustomerCart cc=new CustomerCart();
		cc.setCustomerId(customer.getCustomerId());
		cc.setCartId(cart.getCartId());
		cservice.create(cc);
		return new ResponseEntity<CustomerCart>(cc,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/customer/customerId={customerId}/cart")
	public ResponseEntity<?> addcartitems(@PathVariable ("customerId") int customerId,@RequestBody  Cart c){
		CustomerCart cc=crepo.findBycustomerId(customerId);
		if(cc==null)
			return new ResponseEntity<String>("no customer and cart generated",HttpStatus.NOT_FOUND);
		int cartId=cc.getCartId();
		restTemplate.put("http://CART-SERVICE/cart/updatecart/"+cartId, c);
		Cart cart=restTemplate.getForObject("http://CART-SERVICE/cart/getitems/"+cartId,Cart.class);
		return new ResponseEntity<Cart>(cart,HttpStatus.CREATED); 
	}
	
	
	@PostMapping("/customer/customerId={customerId}/order")
	public ResponseEntity<?> placeorder(@PathVariable ("customerId") int customerId){
		CustomerCart cc=crepo.findBycustomerId(customerId);
		if(cc==null)
			return new ResponseEntity<String>("no customer and cart generated",HttpStatus.NOT_FOUND);
		int cartId=cc.getCartId();
		Orders o=restTemplate.postForObject("http://ORDER-SERVICE/order/addorder/cartId="+cartId, "null",Orders.class );
		CustomerOrder co=new CustomerOrder();
		co.setCustomerId(customerId);
		co.setOrderId(o.getOrderId());
		cservice.createorder(co);
		Cart emptycart=new Cart();
		restTemplate.put("http://CART-SERVICE/cart/updatecart/"+cartId, emptycart);
		for (LineItems i : o.getItems()) {
			int p=i.getProductId();
			int q=i.getQuantity();
			Inventory inv=restTemplate.getForObject("http://INVENTORY-SERVICE/inventory/getinventorybyproduct/"+p,Inventory.class);
			Map<String,Integer> ibody=new HashMap<String, Integer>();
			ibody.put("productId", p);
			ibody.put("quantity",inv.getQuantity()-q );
			restTemplate.put("http://INVENTORY-SERVICE/inventory/updateinventory/"+inv.getInventoryId(),ibody);
		}
		return new ResponseEntity<Orders>(o,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/customer/customerId={customerId}/order")
	public ResponseEntity<?> getorders(@PathVariable ("customerId") int customerId){
		List<Orders> olist=new ArrayList<Orders>();
		List<CustomerOrder> l=cservice.findorders(customerId);
		if(l==null)
			return new ResponseEntity<String>("no orders for this customer",HttpStatus.CREATED);
		Customer c=restTemplate.getForObject("http://CUSTOMER-SERVICE/customer/searchcustomer/"+customerId, Customer.class);
		System.out.print(l);
		for (CustomerOrder customerOrder : l) {
			int i=customerOrder.getOrderId();
			Orders o=restTemplate.getForObject("http://ORDER-SERVICE/order/getorder/"+i, Orders.class);
			olist.add(o);
		}
		CustomerOrderres response=new CustomerOrderres();
		response.setCustomer(c);
		response.setOds(olist);
		return new ResponseEntity<CustomerOrderres>(response,HttpStatus.CREATED);
		
	}
}
