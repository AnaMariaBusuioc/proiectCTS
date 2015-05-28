package factory;

import state.Stare;

public class FacturaDeGaze implements Factura {
	
	public double totalDePlata;
	public double totalConsumGaze;
	public static double valoare = 0;
	public Stare stareFactura = Stare.DE_PLATA;
	
	public FacturaDeGaze(double totalDePlata,double totalConsumGaze){
		this.totalDePlata = totalDePlata;
	}

	@Override
	public boolean platesteFactura(double sumaPrimita) {
		
		if (this.totalDePlata == sumaPrimita){
			System.out.println("Plata s-a realizat cu succes!");
			return true;
		}
		else
			if (this.totalDePlata <= sumaPrimita)
				{
					System.out.println("Clientul trebuie sa primeasca inapoi suma de  "+ (sumaPrimita-totalDePlata));
					valoare = sumaPrimita-totalDePlata;
					return false;
				}
		    else{
		    		System.out.println("A ramas un rest de plata in valoare de " + (this.totalDePlata-sumaPrimita));
		    		valoare = sumaPrimita - totalDePlata;
		    		return false;
		         }
		
	}
	
	public double getValoare(){
		return this.valoare;
	}

	@Override
	public void seteazaStarea(Stare stareFactura) {
		this.stareFactura= stareFactura;
	}

}
