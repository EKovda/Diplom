package test.solver;

import static org.junit.Assert.*;

import main.dataStore.mock.MockDataStore;
import main.entity.Informational;
import main.solver.CalculationRegress;

import org.junit.Before;
import org.junit.Test;

public class TestCalculationRegress {

	private CalculationRegress testRegress;
	private MockDataStore mockRegress;
	private Informational var;
	
	@Before
	public void setUp() throws Exception {
		testRegress = new CalculationRegress();
		mockRegress = new MockDataStore();
		var = new Informational();
	}

	@Test
	public void testGauss() {
		double[] mas = {-32.763734208181454 , 0.13820131514576914 , -1.174518211555338E-4 , 2.8744498909110406E-8};
		double[][] masA = {{1.0 , 1200.0 , 1764000.0 , 2.8944E9}, 
						   {1200.0 , 1764000.0, 2.8944E9, 5.05981728E12}, 
						   {1764000.0 , 2.8944E9, 5.05981728E12, 9.20818368E15}, 
						   {2.8944E9, 5.05981728E12, 9.20818368E15, 1.7226662843519998E19}
						  };
		double[] masB = {9.090909090909092 , 9960.0 , 1.2614530909090908E7 , 1.8095898763636364E10};
			assertArrayEquals(mas, testRegress.gauss(masA, masB), 0.1);

		
	}

	@Test
	public void testDensityAccumulationAtTheInitialTime() {
		double [] expecteds = {-1.098 , 9.691 , 15.551, 17.488, 16.509, 13.618, 9.821, 6.126, 3.536, 3.059, 5.699};
		int [] expecteds2 = {300 , 480 , 660, 840, 1020, 1200, 1380, 1560, 1740, 1920, 2100};
		var = testRegress.densityAccumulationAtTheInitialTime(mockRegress.getInformationRegress());
		double[] mas = var.getDoubleMassive();

		assertArrayEquals(expecteds, mas, 0.1);

		var = testRegress.densityAccumulationAtTheInitialTime(mockRegress.getInformationRegress());
		int[] mas2 = var.getIntMassive();
		
		assertArrayEquals(expecteds2, mas2);
	}

	@Test
	public void testRegressTestForAdequacy() {
		boolean test = testRegress.regressTestForAdequacy(mockRegress.getInformationRegress());
		assertEquals(true, test);
	}

}
