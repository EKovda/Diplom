package main.solver;

import main.entity.Informational;

public interface ICalculationRegress {
	Informational densityAccumulationAtTheInitialTime(Informational regressMassive);
	boolean regressTestForAdequacy(Informational regressMassive);
}
