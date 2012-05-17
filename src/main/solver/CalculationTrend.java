package main.solver;

import java.math.BigDecimal;
import java.math.RoundingMode;

import main.entity.Informational;

public class CalculationTrend implements ICalculationTrend {
	
	public double getFirstFosterConstants(int value)
	{
		double[] constant = {1.964, 2.153, 2.279};
		if(value <= 10)
		{
			return constant[0];
		}	
		if(value > 10 && value <= 15)
		{	
			return constant[1];
		}	
		if(value > 15 && value <= 20)
		{	
			return constant[2];
		}
		return 0;
	}
	
	public double getSecondFosterConstants(int value)
	{
		double[] constant = {1.288, 1.521, 1.677};
		if(value <= 10)
		{
			return constant[0];
		}	
		if(value > 10 && value <= 15)
		{	
			return constant[1];
		}	
		if(value > 15 && value <= 20)
		{	
			return constant[2];
		}
		return 0;
	}
	
	public double getTableStudent (int index)
	{
		final double ALFA = 0.25;
		double[] studentTable= {1.000, 0.816, 0.765, 0.741, 0.727,
								0.718, 0.711, 0.706, 0.703, 0.700,
								0.697, 0.695, 0.694, 0.692, 0.691
							   };
		
		return studentTable[index-1];
	}
	
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
	public Informational theSpeedOfAccumulation(Informational trendMassive) {
		
		int capacity = trendMassive.getIntMassive().length;
		double [] coefficients = new double [capacity];
		int[] t = trendMassive.getIntMassive();
		double [] x_t = trendMassive.getDoubleMassive();
		double [] y_t = new double [capacity];

		y_t[0] = x_t[0];
		y_t[capacity-1] = x_t[capacity-1];
		for (int i=1; i<capacity-1; i++)
		{
				y_t[i] = (x_t[i-1]+x_t[i]+x_t[i+1])/3;	
		}	
		double [] trendCalculateMassive = new double [capacity];
		
		//FIND coefficients

		double [][] masA = new double [capacity][capacity];
		double [] masB = new double [capacity];
		
		masA[0][0]=1;
		for(int i=1;i<capacity;i++)
		{
		 	for(int j=0;j<=i;j++)
			{
				for (int k=0;k<capacity;k++)
				{
					masA[i][j]+=Math.pow(t[k],j+i);
				}
				masA[i][j]=masA[i][j]/capacity;
				if(i!=j)
				masA[j][i]=masA[i][j];
			}
		}
		
		for (int i = 0; i<capacity; i++)
		{
			for(int k=0; k<capacity; k++)
			{	
				masB[i] += Math.pow(t[k], i)*y_t[k];  
			}
			masB[i] = masB[i]/capacity;
		}
		
		coefficients = gauss(masA, masB);
		
		for (int i=0; i<capacity; i++)
		{
			for (int k=0; k<capacity; k++)
			{
				trendCalculateMassive[i] += coefficients[k] * Math.pow(t[i], k);
			}
		}
		
		for (int i=0; i<capacity; i++)
		trendCalculateMassive[i] = new BigDecimal(trendCalculateMassive[i]).setScale(3, RoundingMode.HALF_UP).doubleValue();
		
		Informational mass = new Informational();
		mass.setIntMassive(t);
		mass.setDoubleMassive(trendCalculateMassive);
		return mass;	
	}

	@Override
	public boolean trendTestForAdequacy(Informational trendMassive) {
		
		int capacity = trendMassive.getIntMassive().length;
		double [] x_t = trendMassive.getDoubleMassive();
		double [] y_t = new double [capacity];
		int [] ut = new int [capacity];
		int [] lt = new int [capacity];
		int [] st = new int [capacity];
		int [] dt = new int [capacity];
		int s = 0;
		int d = 0;

		y_t[0] = x_t[0];
		y_t[capacity-1] = x_t[capacity-1];
		for (int i=1; i<capacity-1; i++)
		{
				y_t [i] = (x_t[i-1]+x_t[i]+x_t[i+1])/3;	
		}
		
		for (int i=capacity-1; i>0; i--)
		{
			if(y_t[i] > y_t[i-1])
			{
				ut[i] = 1;
		        lt[i] = 0;
			}
			if(y_t[i] < y_t[i-1])
			{
				ut[i] = 0;
		        lt[i] = 1;
			}
			if(y_t[i] == y_t[i-1])
			{
				ut[i] = 0;
		        lt[i] = 0;
			}
		}
		for(int i=0; i<capacity; i++)
		{
			st[i] = ut[i]+lt[i];
			dt[i] = ut[i]-lt[i];
			s += st[i];
			d += dt[i];
		}
		
		double sigma1 = getFirstFosterConstants(capacity);
		double sigma2 = getSecondFosterConstants(capacity);
		//double sigma1 = 1.964;
		//double sigma2 = 1.288;
		double tkr = getTableStudent(2);
		//double tkr = 0.816;
		double t1=Math.abs(d/sigma1);
		double t2=Math.abs((s-sigma1*sigma1)/sigma2);
		if(t1 > tkr || ((t1 > tkr) && (t2 > tkr)))
		{
			return true;
		}
		else
		{
			return false;
		}	
	}
}
