package test.solver;

import static org.junit.Assert.*;

import main.dataStore.mock.MockDataStore;
import main.entity.Informational;
import main.solver.CalculationFinally;
import main.solver.CalculationRegress;
import main.solver.CalculationTrend;

import org.junit.Before;
import org.junit.Test;

public class TestCalculationFinally {

	private CalculationRegress testRegress;
	private CalculationTrend testTrend;
	private CalculationFinally testFinally;
	private MockDataStore mockTrend;
	private MockDataStore mockRegress;
	private Informational var;
	
	@Before
	public void setUp() throws Exception {
		testRegress = new CalculationRegress();
		testTrend = new CalculationTrend();
		testFinally = new CalculationFinally();
		mockRegress = new MockDataStore();
		mockTrend = new MockDataStore();
		var = new Informational();
	}

	@Test
	public void testDensityAccumulation() {
		double [] expecteds = {-1.098 , 9.691 , 15.551, 17.488, 16.509, 13.618, 9.821, 6.125, 3.536, 3.059, 5.7};
		int [] expecteds2 = {300 , 480 , 660, 840, 1020, 1200, 1380, 1560, 1740, 1920, 2100};
		var = testFinally.densityAccumulation(testTrend.theSpeedOfAccumulation(mockTrend.getInformationTrend()), testRegress.densityAccumulationAtTheInitialTime(mockRegress.getInformationRegress()));
		double[] mas = var.getDoubleMassive();

		assertArrayEquals(expecteds, mas, 0.1);

		var = testFinally.densityAccumulation(testTrend.theSpeedOfAccumulation(mockTrend.getInformationTrend()), testRegress.densityAccumulationAtTheInitialTime(mockRegress.getInformationRegress()));
		int[] mas2 = var.getIntMassive();
		
		assertArrayEquals(expecteds2, mas2);
	}

}
