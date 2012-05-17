package main.dataStore;

import main.entity.Informational;
import main.dataStore.Parameters;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FileDataStore implements IDataStore {

	private Parameters parameters;

	@Override
	public Informational getInformation() throws Exception {

		ArrayList<Integer> listInt = new ArrayList<Integer>();
		ArrayList<Double> listDouble = new ArrayList<Double>();

		File file = new File(parameters.getFilePath());
		Scanner scanner = new Scanner(file);
		try {
			scanner.useLocale(Locale.US);
			while (scanner.hasNextInt() || scanner.hasNextDouble()) {
				if (scanner.hasNextInt()) {
					listInt.add(scanner.nextInt());
				}
				if (scanner.hasNextDouble()) {
					listDouble.add(scanner.nextDouble());
				}
			}

			int[] firstMassive = new int[listInt.size()];
			double[] secondMassive = new double[listDouble.size()];

			for (int i = 0; i < listInt.size(); i++) {
				firstMassive[i] = listInt.get(i);
			}

			for (int i = 0; i < listDouble.size(); i++) {
				secondMassive[i] = listDouble.get(i);
			}

			Informational var = new Informational();
			var.setIntMassive(firstMassive);
			var.setDoubleMassive(secondMassive);
			return var;
		} finally {
			scanner.close();
		}

	}

	@Override
	public Parameters getParameters() {
		return parameters;
	}

	@Override
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;

	}
}