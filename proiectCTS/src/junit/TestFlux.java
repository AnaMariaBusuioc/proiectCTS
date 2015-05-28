package junit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import controller.Casier;
import factory.Factura;
import factory.FacturaDeGaze;
import factory.FacturaDeTelefonie;

public class TestFlux  extends TestCase {
	
	   private Casier casier;
	   // valoare initiala detinuta de casier
	   private static double soldInitial;
	   private static String cnp;
	   
	   
	   static {
			System.out.println("Apelat in bloc static");
			try {
				getTestData("date.txt");
				
				//afisare valori intrare
				System.out.println("Sold initial "+soldInitial);
				System.out.println("cnp: "+cnp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	   // definire constructor Unit Test
		public TestFlux(String name) {
			super(name);
		}

		// functia setUp se apeleaza de fiecare data, inaintea metodelor de test
		public void setUp() {
			System.out.println("Pregatire test -  creare obiecte");
			casier = Casier.getInstance(soldInitial);
		    
		}

		// functie ce se apeleaza automat la sfarsitul fiecarui unit test
		public void tearDown() {
			System.out.println("Terminare test - stergere obiecte");
		}
		
		public void testPrimireFacturaInexistenta(){
			//nu a fost primita nici o factura 
			try {
				this.casier.primireFactura(null);
				fail("Metoda primireFactura nu genereaza exceptii pentru cazul in care nu este primita nici o factura ");
			} catch (Exception ex) {

			}
		}
		
		public void testPlataFacturiiNegativa(){
			//suma primita are valoare negativa
			try {
				this.casier.incaseazaPlataFacturii(-5, 0);
				fail("Metoda incaseazaPlatafacturii nu genereaza exceptii pentru sumaPrimita negativa");
			} catch (Exception ex) {

			}
			
		}
		
		public void testPlataFacturiiNula(){
			// suma primita are valoare nula
			try {
				this.casier.incaseazaPlataFacturii(0, 0);
				fail("Metoda incaseazaPlatafacturii nu genereaza exceptii pentru sumaPrimita nula");
			} catch (Exception ex) {

			}
		}	
		
		public void testPlataFacturiiFacturaNereceptionata(){
			// factura nu a fost receptionata
			try {
				this.casier.incaseazaPlataFacturii(120, 10);
				fail("Metoda incaseazaPlatafacturii nu genereaza exceptii pentru facturii nereceptionate");
			} catch (Exception ex) {
				
			}

		}
		
		public void testPlataFacturiiFacturaInexistenta(){
			// factura primita este inexistenta
			try {
				this.casier.incaseazaPlataFacturii(120, -5);
				fail("Metoda incaseazaPlatafacturii nu genereaza exceptii pentru facturii inexistente");
			} catch (Exception ex) {
				
			}

		}
		
		public void testVerificareSoldLaSfarsitulZileiSoldIncorect(){
			// sold incorect
			try {
			this.casier.verificareSoldLaSfarsitulZilei();
			fail("Metoda verificareSoldLaSfarsitulZilei nu genereaza exceptii pentru situatia in care sold-ul final este incorect");
			} catch (Exception ex) {
							
			}	
		}
		

		public void testDaRest(){
		assertEquals("Testare depunere cu valoare ok",
				casier.daRest(300, 200),100.0);
		}
		
		public void testValidareCnp(){
			try{
				casier.validareActDeIdentitate(cnp);
				fail("Metoda verificareSoldLaSfarsitulZilei nu genereaza exceptii pentru situatia in care cnp-ul introdus nu este valid");
			}
			catch(Exception e){
				
			}
		}
		
		private static void getTestData(String fileName) 
				throws IOException{
			File inputFile = new File(fileName);
			if(inputFile.exists()){
				BufferedReader reader = 
						new BufferedReader(new FileReader(inputFile));
				
				String linieCurenta;
				
				while((linieCurenta = reader.readLine())!=null){
					//ignoram liniile care incep cu #
					if(linieCurenta.startsWith("#"))
						continue;
					else
					{
						System.out.println(linieCurenta);
						//verificam marker semnificatie valoare
						if (linieCurenta.startsWith("*")){
							String[] simboluri = linieCurenta.split(" ");
							switch(simboluri[1]){
								case "soldInitial":{
									linieCurenta = reader.readLine();
									soldInitial = Double.parseDouble(linieCurenta);
									break;
								}
								case "cnp" :{
									linieCurenta = reader.readLine();
									cnp = linieCurenta;
									
									break;
								}
								
							}
						}
					}
				}
			}
			else
				System.out.println("Lipsa fisier date intrare");
		}


}
