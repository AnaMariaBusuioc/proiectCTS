package testSuite;

import junit.TestFlux;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSuite_ extends TestCase{
	
	//metoda definire test suite
	public static Test suite(){
		TestSuite suite = new TestSuite();
	
		//adaugare partiala de metode
		suite.addTest(new TestFlux("testPlataFacturiiNegativa"));
		suite.addTest(new TestFlux("testVerificareSoldLaSfarsitulZileiSoldIncorect"));
		suite.addTest(new TestFlux("testDaRest"));
		
		//adaugare totala
		suite.addTestSuite(TestFlux.class);
		
		return suite;
	}
	
}