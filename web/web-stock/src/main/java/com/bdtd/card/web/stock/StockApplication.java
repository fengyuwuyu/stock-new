package com.bdtd.card.web.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bdtd.card.web.admin.AdminApplication;

@SpringBootApplication(scanBasePackages = { "com.bdtd.card", "com.bdtd.card" })
@EnableTransactionManagement
@EnableCaching
@ServletComponentScan
public class StockApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(AdminApplication.class, args);
	}
	
}
