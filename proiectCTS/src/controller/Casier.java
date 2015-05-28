package controller;

import java.util.ArrayList;
import java.util.List;

import exceptii.CnpInvalidPrimireactDeIdentitate;
import exceptii.FacturaInexistentaPlataFacturiiException;
import exceptii.FacturaInexistentaPrimireFactura;
import exceptii.InactivitateCasierVerificareSoldLaSfarsitulZileiException;
import exceptii.IndexOutOfRangeException;
import exceptii.RestIncorectDaRest;
import exceptii.SoldIncorectVerificareSoldLaSfarsitulZileiException;
import exceptii.ValoareNegativaPlataFacturiiException;
import exceptii.ValoareNulaPlataFacturiiException;
import factory.Factura;
import factory.FacturaDeGaze;

public class Casier {

	public static double SOLD_INITIAL ;
	public static double SOLD_FINAL;
	public static double SOLD_INTERMEDIAR;
	public List<Factura> facturi;
	
	private static Casier casier = null;
	
	private Casier(double soldInitial){
		SOLD_INITIAL = soldInitial;
		SOLD_INTERMEDIAR = soldInitial;
		facturi = new ArrayList<Factura>();
		
	}
	
	
	public static Casier getInstance(double soldInitial){
		if (casier == null)
			casier = new Casier(soldInitial);
		return casier;
	}
	
	public void primireFactura(Factura f) throws FacturaInexistentaPrimireFactura{
		
		if (f == null) throw new FacturaInexistentaPrimireFactura("Nu a fost primisa nici o factura");
		facturi.add(f);
	}
	
	public void validareActDeIdentitate(String cnp) throws CnpInvalidPrimireactDeIdentitate{
			
		if (cnp.length() <0) throw new CnpInvalidPrimireactDeIdentitate("Cnp invalid, nu ati introdus corect cnp-ul");
		if (cnp.length() != 14) throw new CnpInvalidPrimireactDeIdentitate("Cnp invalid, nu ati introdus corect cnp-ul");
		System.out.println("Cnp-ul este ok");
	}
	
	public void incaseazaPlataFacturii(double sumaPrimita, int numarulFacturii) throws ValoareNegativaPlataFacturiiException, 
	          IndexOutOfRangeException, ValoareNulaPlataFacturiiException, FacturaInexistentaPlataFacturiiException{
		
		if (sumaPrimita < 0) throw new ValoareNegativaPlataFacturiiException("Suma primita < 0");
		
		if (sumaPrimita == 0) throw new ValoareNulaPlataFacturiiException("Suma primita == 0");
		
		if (numarulFacturii > (facturi.size()-1) ) throw new IndexOutOfRangeException("Factura nu a fost receptionata de casier!");
		
		if (numarulFacturii < 0) throw new FacturaInexistentaPlataFacturiiException("Aceasta factura nu exista!");
		
		facturi.add(new FacturaDeGaze(100, 50));
		facturi.get(numarulFacturii).platesteFactura(sumaPrimita);
		this.SOLD_INTERMEDIAR += facturi.get(numarulFacturii).getValoare();
		
	}
	
	public void daChitantaClientului(){
		
	}
	
	public double daRest(double sumaPrimita, double valoareDePlata) {
		
		//if (sumaPrimita - f.getValoare() != rest) throw new RestIncorectDaRest("Restul dat clientului este incorect");
		return sumaPrimita - valoareDePlata;
	}
	
	public void verificareSoldLaSfarsitulZilei() throws InactivitateCasierVerificareSoldLaSfarsitulZileiException, SoldIncorectVerificareSoldLaSfarsitulZileiException{
		
		this.SOLD_FINAL = this.SOLD_INTERMEDIAR;
		if (this.SOLD_INITIAL == this.SOLD_FINAL && facturi.size() == 0) 
			throw new InactivitateCasierVerificareSoldLaSfarsitulZileiException("Casier nu a incasat nici o factura astazi");
		if (this.SOLD_INITIAL == this.SOLD_FINAL && facturi.size() != 0) 
			throw new SoldIncorectVerificareSoldLaSfarsitulZileiException("Soldul final este gresit! S-a intamplat o greseala pe parcursil zilei");
	}

	
	
	
	
}
