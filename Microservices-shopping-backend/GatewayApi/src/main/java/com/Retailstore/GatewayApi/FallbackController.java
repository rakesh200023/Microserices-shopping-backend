package com.Retailstore.GatewayApi;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
	
	@RequestMapping("/shoppingservicefallback")
	public String shoppingservicefallback() {
		return "shopping service not available";
	}
	@RequestMapping("/customerservicefallback")
	public String customerservicefallback() {
		return "customer service not available";
	}
	@RequestMapping("/productservicefallback")
	public String productservicefallback() {
		return "product service not available";
	}
	@RequestMapping("/inventoryservicefallback")
	public String inventoryservicefallback() {
		return "inventory service not available";
	}
	@RequestMapping("/cartservicefallback")
	public String cartservicefallback() {
		return "cart service not available";
	}
	@RequestMapping("/orderservicefallback")
	public String orderservicefallback() {
		return "order service not available";
	}

}
