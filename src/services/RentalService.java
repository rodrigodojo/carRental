package services;

import entities.CarRental;
import entities.Invoice;

public class RentalService {

	private Double pricePerHour , pricePerDay;
	private BrazilTaxService taxService;

	public RentalService() {
		
	}
	
	public RentalService(Double pricePerHour, Double pricePerDay, BrazilTaxService taxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}

	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
		
	public BrazilTaxService getTaxService() {
		return taxService;
	}

	public void setTaxService(BrazilTaxService taxService) {
		this.taxService = taxService;
	}

	public void processinvoice(CarRental carRental) {
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		double hours =(double) (t2 - t1) /1000 /60 /60;
		double basicPayment;
		
		if(hours <= 12.0) {
			basicPayment = Math.ceil(hours) * pricePerHour ;
		}
		else{
			basicPayment = Math.ceil(hours /24) * pricePerDay;
		}
		
		double tax = taxService.tax(basicPayment);
		
		carRental.setInvoice(new Invoice(basicPayment, tax));
	}
	
}
