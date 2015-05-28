package factory;

import state.Stare;

public interface Factura {
	
	public boolean platesteFactura (double sumaPrimita);
	public double getValoare();
	public void seteazaStarea(Stare stareFactura);
	
	

}
