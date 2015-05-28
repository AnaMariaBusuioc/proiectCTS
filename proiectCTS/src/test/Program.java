package test;

import state.SeteazaStareFactura;
import controller.Casier;
import factory.FactoryFactura;
import factory.Factura;
import factory.FacturaDeGaze;
import factory.FacturaDeTelefonie;
import factory.TipFactura;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		double soldInitial = 200;
		Casier casier = Casier.getInstance(soldInitial);
		
		//testare factory
		FactoryFactura factory = new FactoryFactura();
		Factura facturaDeGaze = factory.alegeTipDeFactura(TipFactura.GAZE, 100, 340);
		if (facturaDeGaze != null)
		   facturaDeGaze.platesteFactura(200);
		
		Factura facturaDeTelefonie = (FacturaDeTelefonie)factory.alegeTipDeFactura(TipFactura.TELEFONIE, 50, 0);
		if (facturaDeTelefonie != null)
		facturaDeTelefonie.platesteFactura(40);
		
		System.out.println("valoare: "+((FacturaDeGaze)facturaDeGaze).valoare);
		System.out.println("valoare: "+((FacturaDeTelefonie)facturaDeTelefonie).valoare);
		System.out.println("stare: "+((FacturaDeGaze)facturaDeGaze).stareFactura);
		System.out.println("stare: "+((FacturaDeTelefonie)facturaDeTelefonie).stareFactura);
		
		//testare stare
		SeteazaStareFactura seteaza = new SeteazaStareFactura();
		seteaza.seteazaStarea(facturaDeGaze);
		seteaza.seteazaStarea(facturaDeTelefonie);
		System.out.println("stare: "+((FacturaDeGaze)facturaDeGaze).stareFactura);
		System.out.println("stare: "+((FacturaDeTelefonie)facturaDeTelefonie).stareFactura);
		
		
	}

}
