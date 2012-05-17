package main.solver;

import java.math.BigDecimal;
import java.math.RoundingMode;

import main.entity.Informational;

public class CalculationFinally implements ICalculationFinally {

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
	public Informational densityAccumulation(Informational trendCalculateMassive, Informational regressCalculateMassive) 
	{

		double[] coefficients = new double[4];
		int capacity = regressCalculateMassive.getIntMassive().length;
		int[] t = new int[capacity];
		for (int i = 0; i < capacity; i++) {
			t[i] = i + 1;
		}

		int[] x = regressCalculateMassive.getIntMassive();
		double[] y = regressCalculateMassive.getDoubleMassive();
		double[] psi = new double[capacity];
		double[] u = new double[capacity];

		// FIND coefficients

		double[][] masA = new double[4][4];
		double[] masB = new double[4];

		masA[0][0] = 1;
		for (int i = 1; i < 4; i++) {
			for (int j = 0; j <= i; j++) {
				for (int k = 0; k < capacity; k++) {
					masA[i][j] += Math.pow(x[k], j + i);
				}
				masA[i][j] = masA[i][j] / capacity;
				if (i != j)
					masA[j][i] = masA[i][j];
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < capacity; k++) {
				masB[i] += Math.pow(x[k], i) * y[k];
			}
			masB[i] = masB[i] / capacity;
		}

		coefficients = gauss(masA, masB);

		for (int i = 0; i < capacity; i++) {
			psi[i] = 0.006658256312 * Math.atan(0.5892556836 * t[i] - 1.021043605) + x[i];
			for (int k = 0; k < 4; k++) {
				u[i] += coefficients[k] * Math.pow(psi[i], k);
			}
		}

		for (int i = 0; i < capacity; i++)
			u[i] = new BigDecimal(u[i]).setScale(3, RoundingMode.HALF_UP).doubleValue();

		Informational var = new Informational();
		var.setIntMassive(x);
		var.setDoubleMassive(u);
		return var;
	}

}
