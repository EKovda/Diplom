package main.dataStore.mock;

import main.entity.Informational;

public class MockDataStore {
	public Informational getInformationTrend() {
		int[] firstMassive = { 1, 2, 3 };
		double[] secondMassive = { 302.4, 261.2, 397.0 };
		Informational var = new Informational();
		var.setIntMassive(firstMassive);
		var.setDoubleMassive(secondMassive);
		return var;
	}
	
	public Informational getInformationRegress() {
		int[] firstMassive = { 300, 480, 660, 840, 1020, 1200, 1380, 1560, 1740, 1920, 2100 };
		double[] secondMassive = { 0.9, 6.1, 15.0, 19.5, 18.2, 12.7, 9.3, 5.9, 3.7, 2.6, 6.1 };
		Informational var = new Informational();
		var.setIntMassive(firstMassive);
		var.setDoubleMassive(secondMassive);
		return var;
	}
}
