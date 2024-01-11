package com.sb.msa.currencyconversionservice.currencyconversion;

import java.math.BigDecimal;

import org.springframework.core.env.Environment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CurrencyConversion {
	@Id
	private int id;
	
	@Column
	private String currencyfrom;
	
	@Column
	private String currencyto;
	
	@Column
	private int conversionMultiple;
	
	@Column
	private int qty;
	
	@Column
	private int total_amt;
	
	@Column
	private String env;

	public CurrencyConversion() {
		super();
	}

	public CurrencyConversion(int id, String currencyfrom, String currencyto, int conversionMultiple,
			int qty, int total_amt, String env) {
		super();
		this.id = id;
		this.currencyfrom = currencyfrom;
		this.currencyto = currencyto;
		this.conversionMultiple = conversionMultiple;
		this.qty = qty;
		this.total_amt = total_amt;
		this.env = env;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCurrencyfrom() {
		return currencyfrom;
	}

	public void setCurrencyfrom(String currencyfrom) {
		this.currencyfrom = currencyfrom;
	}

	public String getCurrencyto() {
		return currencyto;
	}

	public void setCurrencyto(String currencyto) {
		this.currencyto = currencyto;
	}

	public int getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(int conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getTotal_amt() {
		return total_amt;
	}

	public void setTotal_amt(int total_amt) {
		this.total_amt = total_amt;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}
}