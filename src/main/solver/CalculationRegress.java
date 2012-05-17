package main.solver;


import java.math.BigDecimal;
import java.math.RoundingMode;

import main.entity.Informational;


public class CalculationRegress implements ICalculationRegress {
	
	public double[] gauss(double[][] a, double[] b) {
	    int n = a.length;
	    for (int i = 0; i < n; i++) {
	      int best = i;
	      for (int j = i + 1; j < n; j++) {
	        if (Math.abs(a[j][i]) > Math.abs(a[best][i])) {
	          best = j;
	        }
	      }
	      double[] tmp = a[i];
	      a[i] = a[best];
	      a[best] = tmp;
	      double t = b[i];
	      b[i] = b[best];
	      b[best] = t;
	      
	      for (int j = i + 1; j < n; j++) {
	        a[i][j] /= a[i][i];
	      }
	      b[i] /= a[i][i];
	      
	      for (int j = 0; j < n; j++) {
	        if (j != i && a[j][i] != 0) {
	          for (int p = i + 1; p < n; p++) {
	            a[j][p] -= a[i][p] * a[j][i];
	          }
	          b[j] -= b[i] * a[j][i];
	        }
	      }
	    }
	    return b;
	  }

	@Override
	public Informational densityAccumulationAtTheInitialTime(Informational regressMassive) {
	
		int capacity = regressMassive.getIntMassive().length;
		double [] coefficients = new double [4];
		int[] x = regressMassive.getIntMassive();
		double [] y = regressMassive.getDoubleMassive();
		double [] regressCalculateMassive = new double [capacity];
		
		//FIND coefficients
		
		double [][] masA = new double [4][4];
		double [] masB = new double [4];
				
		masA[0][0]=1;
				for(int i=1;i<4;i++)
				{
				 	for(int j=0;j<=i;j++)
					{
						for (int k=0;k<capacity;k++)
						{
							masA[i][j]+=Math.pow(x[k],j+i);
						}
						masA[i][j]=masA[i][j]/capacity;
						if(i!=j)
						masA[j][i]=masA[i][j];
					}
				}
			
				for (int i = 0; i<4; i++)
				{
					for(int k=0; k<capacity; k++)
					{	
						masB[i] += Math.pow(x[k], i)*y[k];  
					}
					masB[i] = masB[i]/capacity;
				}
				
				coefficients = gauss(masA, masB);
					
		for (int i=0; i<capacity; i++)
		{
			for (int k=0; k<4; k++)
			{
				regressCalculateMassive[i] += coefficients[k] * Math.pow(x[i], k);
			}
		}
		
		for (int i=0; i<capacity; i++)
			regressCalculateMassive[i] = new BigDecimal(regressCalculateMassive[i]).setScale(3, RoundingMode.HALF_UP).doubleValue();
		
		Informational mass = new Informational();
		mass.setIntMassive(x);
		mass.setDoubleMassive(regressCalculateMassive);
		return mass;
	}

	@Override
	public boolean regressTestForAdequacy(Informational regressMassive) {

		double sumy = 0, ysr = 0, sum_ysr2 = 0, chisl = 0, sum_ykr2 = 0, znam = 0;
		int capacity = regressMassive.getIntMassive().length;
		double [] coefficients = new double [4];
		int[] x = regressMassive.getIntMassive();
		double [] y = regressMassive.getDoubleMassive();
		double [] y_ysr = new double [capacity]; 
		double [] y_ysr2 = new double [capacity];
		double [] ykr = new double [capacity];
		double [] y_ykr = new double [capacity];
		double [] y_ykr2 = new double [capacity];
		
		//FIND coefficients
		
		double [][] masA = new double [4][4];
		double [] masB = new double [4];
				
		masA[0][0]=1;
				for(int i=1;i<4;i++)
				{
				 	for(int j=0;j<=i;j++)
					{
						for (int k=0;k<capacity;k++)
						{
							masA[i][j]+=Math.pow(x[k],j+i);
						}
						masA[i][j]=masA[i][j]/capacity;
						if(i!=j)
						masA[j][i]=masA[i][j];
					}
				}
				
				for (int i = 0; i<4; i++)
				{
					for(int k=0; k<capacity; k++)
					{	
						masB[i] += Math.pow(x[k], i)*y[k];  
					}
					masB[i] = masB[i]/capacity;
				}

		coefficients = gauss(masA, masB);
		
		for(int i=0; i<capacity; i++)
		{
	      sumy+=y[i];
	      ysr=sumy/capacity;
	    }
		for(int i=0;i<capacity;i++)
	     {
	      y_ysr[i] = y[i]-ysr;
	      y_ysr2[i] = y_ysr[i]*y_ysr[i];
	      sum_ysr2 += y_ysr2[i];
	      chisl = sum_ysr2;
	     }
		
		for (int i=0; i<capacity; i++)
		{
			for (int k=0; k<4; k++)
			{
				ykr[i] += coefficients[k] * Math.pow(x[i], k);
			}
		}
		
		for(int i=0;i<capacity;i++)
	     {
	       y_ykr[i] = y[i]-ykr[i];
	       y_ykr2[i] = y_ykr[i]*y_ykr[i];
	       sum_ykr2 += y_ykr2[i];
	       znam = sum_ykr2/capacity - 2;
	     }
		
		double F = chisl/znam;
		//!!!!!
	    double Fkr = 5.117355008;
	    if(F > Fkr)
	    {
	    	return true;
	    }
	    else 
	    {
	    	return false; 
	    }
	}

}
