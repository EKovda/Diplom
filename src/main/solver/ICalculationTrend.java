package main.solver;

import main.entity.Informational;

public interface ICalculationTrend {
	Informational theSpeedOfAccumulation(Informational trendMassive);
	boolean trendTestForAdequacy(Informational trendMassive);
}
