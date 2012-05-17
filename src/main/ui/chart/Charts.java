package main.ui.chart;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import main.entity.Informational;

public class Charts {
	public JPanel drawChartRegress(Informational regressCalculateMassive, Informational regressMassive) {
		JPanel myPanel = new JPanel();
		myPanel.setBackground(Color.WHITE);
		int capacity = regressCalculateMassive.getIntMassive().length;
		XYSeriesCollection xyDatasetRegress = new XYSeriesCollection();
		XYSeries seriesRegress = new XYSeries("Регрессия");
		XYSeries seriesInitialRegress = new XYSeries("Статистические данные");
		int[] xInitial = regressMassive.getIntMassive();
		double[] yInitial = regressMassive.getDoubleMassive();
		int[] x = regressCalculateMassive.getIntMassive();
		double[] y = regressCalculateMassive.getDoubleMassive();
		for (int i = 0; i < capacity; i++) {
			seriesRegress.add(x[i], y[i]);
		}
		for (int i = 0; i < capacity; i++) {
			seriesInitialRegress.add(xInitial[i], yInitial[i]);
		}

		xyDatasetRegress.addSeries(seriesRegress);
		xyDatasetRegress.addSeries(seriesInitialRegress);
		NumberAxis xAxisRegress= new NumberAxis("грн.");
		NumberAxis yAxisRegress = new NumberAxis("количество семей, %");
		XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
		XYPlot plotRegress = new XYPlot(xyDatasetRegress, xAxisRegress, yAxisRegress, renderer);
		JFreeChart chartRegress = new JFreeChart("RegressChart", JFreeChart.DEFAULT_TITLE_FONT, plotRegress, true);
		myPanel.add(new ChartPanel(chartRegress));
		return myPanel;
	}
	public JPanel drawChartTrend(Informational trendCalculateMassive, Informational trendMassive) {
		JPanel myPanel = new JPanel();
		myPanel.setBackground(Color.WHITE);
		int capacity = trendCalculateMassive.getIntMassive().length;
		XYSeriesCollection xyDatasetTrend = new XYSeriesCollection();
		XYSeries seriesTrend = new XYSeries("Тренд");
		XYSeries seriesInitialTrend = new XYSeries("Статистические данные");
		int[] xInitial = trendMassive.getIntMassive();
		double[] yInitial = trendMassive.getDoubleMassive();
		int[] x = trendCalculateMassive.getIntMassive();
		double[] y = trendCalculateMassive.getDoubleMassive();
		for (int i = 0; i < capacity; i++) {
			seriesTrend.add(x[i], y[i]);
		}
		
		for (int i = 0; i < capacity; i++) {
			seriesInitialTrend.add(xInitial[i], yInitial[i]);
		}
		
		xyDatasetTrend.addSeries(seriesTrend);
		xyDatasetTrend.addSeries(seriesInitialTrend);
		NumberAxis xAxisTrend= new NumberAxis("денежные накопления");
		NumberAxis yAxisTrend = new NumberAxis("год");
		XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
		XYPlot plotTrend = new XYPlot(xyDatasetTrend, xAxisTrend, yAxisTrend, renderer);
		JFreeChart chartTrend = new JFreeChart("TrendChart", JFreeChart.DEFAULT_TITLE_FONT, plotTrend, true);
		myPanel.add(new ChartPanel(chartTrend));
		return myPanel;
	}
	public JPanel drawChartFinally(Informational finallyCalculateMassive, Informational finallyMassive) {
		JPanel myPanel = new JPanel();
		myPanel.setBackground(Color.WHITE);
		int capacity = finallyCalculateMassive.getIntMassive().length;
		XYSeriesCollection xyDatasetFinally = new XYSeriesCollection();
		XYSeries seriesFinally = new XYSeries("Финальный график");
		XYSeries seriesInitialFinally = new XYSeries("Статистические данные");
		int[] xInitial = finallyMassive.getIntMassive();
		double[] yInitial = finallyMassive.getDoubleMassive();
		int[] x = finallyCalculateMassive.getIntMassive();
		double[] y = finallyCalculateMassive.getDoubleMassive();
		for (int i = 0; i < capacity; i++) {
			seriesFinally.add(x[i], y[i]);
		}
		for (int i = 0; i < capacity; i++) {
			seriesInitialFinally.add(xInitial[i], yInitial[i]);
		}
		xyDatasetFinally.addSeries(seriesFinally);
		xyDatasetFinally.addSeries(seriesInitialFinally);
		NumberAxis xAxisFinally= new NumberAxis("грн.");
		NumberAxis yAxisFinally = new NumberAxis("количество семей, %");
		XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
		XYPlot plotFinally = new XYPlot(xyDatasetFinally, xAxisFinally, yAxisFinally, renderer);
		JFreeChart chartFinally = new JFreeChart("FinallyChart", JFreeChart.DEFAULT_TITLE_FONT, plotFinally, true);
		myPanel.add(new ChartPanel(chartFinally));
		return myPanel;
	}
}
