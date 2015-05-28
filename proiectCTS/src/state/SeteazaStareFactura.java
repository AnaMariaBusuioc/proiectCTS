package state;

import factory.Factura;

public class SeteazaStareFactura {
	
	
	public void seteazaStarea(Factura factura){
		
	   if (factura.getValoare() < 0)
		        factura.seteazaStarea(Stare.CU_REST_DE_PLATA);
	   else if (factura.getValoare() == 0 || factura.getValoare() > 0)
	        factura.seteazaStarea(Stare.PLATITA_INTEGRAL);
		
	}

}
