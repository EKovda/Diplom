package main.dataStore;

import main.entity.Informational;
import main.dataStore.Parameters;
import java.io.File;
import java.io.FileNotFoundException;
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

			// ИЛИ такой метод:
			// ТОЛЬКО тогда надо переделать массивы в Integer и Double
			/*
			 * Integer[] firstMassive = new int[listInt.size()]; Double[]
			 * secondMassive = new double[listDouble.size()];
			 * 
			 * firstMassive = listInt.toArray(firstMassive); secondMassive =
			 * listDouble.toArray(secondMassive);
			 */
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
		// TODO Auto-generated method stub
		return parameters;
	}

	@Override
	public void setParameters(Parameters parameters) {
		// TODO Auto-generated method stub
		this.parameters = parameters;

	}

	
//	//See that!
//	public Double[][] fillingFieldsTable() throws Exception
//	{
////		FileDataStore data = new FileDataStore();
//		File file = new File(parameters.getFilePath());
//		ArrayList<Double> listDouble = new  ArrayList<Double>();  
//		Scanner scanner = new Scanner(file);
//		try 
//		{ 
//		 scanner.useLocale(Locale.US); 
//		 while(scanner.hasNextDouble()) 
//		 {
//			 listDouble.add(scanner.nextDouble()); 
//		 } 
//		 Double[][] massiveInformationRegress = new Double[listDouble.size()/2][listDouble.size()/2]; 
//		 massiveInformationRegress = listDouble.toArray(massiveInformationRegress);
//		 for (int i = 0; i < massiveInformationRegress.length; i++) {
//			 System.out.println(massiveInformationRegress[i]);
//		}
//		 return massiveInformationRegress;
//
//	 } 
//	 catch (Exception e1) 
//	 { 
//	  e1.printStackTrace(); 
//	 }
//	 finally 
//	 {
//		 
//			scanner.close();
//			return null;
//			
//	 }
//	
//	}

}