package factory;

public class FactoryFactura {

	
	public Factura alegeTipDeFactura(TipFactura tip, double totalDePlata, double consum){
		
		Factura factura = null;
		
		switch (tip) {
		case GAZE:
			factura = new FacturaDeGaze(totalDePlata, consum);
			break;

		case TELEFONIE:
			factura = new FacturaDeTelefonie(totalDePlata, consum);
			break;
		default:
			break;
		}
		return factura;
	}
	
}
