package test.solver;

import static org.junit.Assert.*;

import main.dataStore.mock.MockDataStore;
import main.entity.Informational;
import main.solver.CalculationTrend;

import org.junit.Before;
import org.junit.Test;

public class TestCalculationTrend {

	private CalculationTrend testTrend;
	private MockDataStore mockTrend;
	private Informational var;
	
	@Before
	public void setUp() throws Exception {
		testTrend = new CalculationTrend();
		mockTrend = new MockDataStore();
		var = new Informational();
	}

	@Test
	public void testGauss() {
		double[] mas = {343.59999999998666, -70.69999999998456, 29.499999999996227};
		double[][] masA = {{1.0 , 2.0 , 4.666666666666667}, 
						   {2.0 , 4.666666666666667, 12.0}, 
						   {4.666666666666667 , 12.0, 32.666666666666664}, 
						  };
		double[] masB = {339.8666666666666, 711.2666666666668, 1718.7333333333333};
			assertArrayEquals(mas, testTrend.gauss(masA, masB), 0.1);
	}

	@Test
	public void testTheSpeedOfAccumulation() {
		double [] expecteds = {302.4, 320.2 , 397.0};
		int [] expecteds2 = {1, 2 ,3};
		var = testTrend.theSpeedOfAccumulation(mockTrend.getInformationTrend());
		double[] mas = var.getDoubleMassive();

		assertArrayEquals(expecteds, mas, 0.1);

		var = testTrend.theSpeedOfAccumulation(mockTrend.getInformationTrend());
		int[] mas2 = var.getIntMassive();
		
		assertArrayEquals(expecteds2, mas2);
	}

	@Test
	public void testTrendTestForAdequacy() {
		boolean test = testTrend.trendTestForAdequacy(mockTrend.getInformationTrend());
		assertEquals(true, test);
	}

}
