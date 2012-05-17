package test.datastore;

import static org.junit.Assert.*;

import main.dataStore.FileDataStore;
import main.dataStore.Parameters;
import org.junit.Before;
import org.junit.Test;

public class TestFileDataStore {

	private FileDataStore dataRegress;
	private FileDataStore dataTrend;
	private Parameters paramRegress;
	private Parameters paramTrend;

	@Before
	public void setUp() throws Exception {
		dataRegress = new FileDataStore();
		dataTrend = new FileDataStore();
		paramRegress = new Parameters("regress.txt");
		paramTrend = new Parameters("trend.txt");

	}

	@Test
	public void testGetInformationRegress() throws Exception {
		dataRegress.setParameters(paramRegress);
		int [] expectedsIntRegress = {300 , 480 , 660, 840, 1020, 1200, 1380, 1560, 1740, 1920, 2100};
		double [] expectedsDoubleRegress = {0.9 , 6.1, 15.0 , 19.5 , 18.2 , 12.7 , 9.3 , 5.9 , 3.7 , 2.6 , 6.1};
		assertArrayEquals(expectedsIntRegress, dataRegress.getInformation().getIntMassive());
		assertArrayEquals(expectedsDoubleRegress, dataRegress.getInformation().getDoubleMassive(), 0.1);

	}
	
	@Test
	public void testGetInformationTrend() throws Exception {
		dataTrend.setParameters(paramTrend);
		int [] expectedsIntTrend = {1, 2, 3};
		double [] expectedsDoubleTrend = {302.4, 261.2, 397.0};
		assertArrayEquals(expectedsIntTrend, dataTrend.getInformation().getIntMassive());
		assertArrayEquals(expectedsDoubleTrend, dataTrend.getInformation().getDoubleMassive(), 0.1);
	}

}
