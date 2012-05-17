package main.ui.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import main.dataStore.FileDataStore;
import main.dataStore.Parameters;
import main.dataStore.mock.MockDataStore;
import main.entity.Informational;
import main.solver.CalculationFinally;
import main.solver.CalculationRegress;
import main.solver.CalculationTrend;
import main.ui.chart.Charts;

public class MyForm extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new MyForm();
	}

	private String filePathRegress = null;
	private String filePathTrend = null;
	
	public MyForm() throws HeadlessException {
		super();

		setTitle("Дипломная работа");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1104, 518));
		setSize(1104, 518);
		initPanel();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void initPanel() {

		// panel
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.addTab("Main", initMainPanel());
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.addTab("Calculate", initCalculatePanel());
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		panel.add(tabbedPane);
		add(panel);

	}
	
	public JPanel initMainPanel()		
	{
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new FlowLayout());
		mainPanel.setBackground(Color.WHITE);
		final JPanel componentCenter = new JPanel();
		mainPanel.add(componentCenter);
		componentCenter.setPreferredSize(new Dimension(500, 450));
		componentCenter.setLayout(new GridLayout(6, 1, 0, 40 ));
			
		final JFileChooser fileChooserRegress = new JFileChooser();
		final JFileChooser fileChooserTrend = new JFileChooser();
		JLabel labelTheme = new JLabel("Моделирование накоплений семей Украины");
		labelTheme.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

		JLabel labelFileChooser = new JLabel("Выберите файл с данными:");
		labelFileChooser.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		componentCenter.setBackground(Color.WHITE);
		
		JButton buttonMock = new JButton("Выполнить с заданными данными");
		buttonMock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(componentCenter, "Данные приняты!");
			}
		});

		JButton buttonRegress = new JButton("Файл с регрессией");
		buttonRegress.addActionListener(new ActionListener() {

			private Component mainPanel;
			private int returnVal;

			@Override
			public void actionPerformed(ActionEvent e) {
				returnVal = fileChooserRegress.showOpenDialog(mainPanel);
				
				 if (returnVal == JFileChooser.APPROVE_OPTION) 
				 { 
					 File file = fileChooserRegress.getSelectedFile();
					 JOptionPane.showMessageDialog(componentCenter, "Opening: " + file.getName()); 
					 filePathRegress = file.getPath(); 
				  } 
				  else 
				  { 
					  JOptionPane.showMessageDialog(componentCenter, "Open command cancelled by user."); 
					  filePathRegress = null; 
				  }
			}
		});

		JButton buttonTrend = new JButton("Файл с трендом");
		buttonTrend.addActionListener(new ActionListener() {

			private int returnVal;
			private Component mainPanel;

			@Override
			public void actionPerformed(ActionEvent e) {
				returnVal = fileChooserTrend.showOpenDialog(mainPanel);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooserTrend.getSelectedFile();
					JOptionPane.showMessageDialog(componentCenter, "Opening: " + file.getName());
					filePathTrend = file.getPath();
				} 
				else 
				{
					JOptionPane.showMessageDialog(componentCenter, "Open command cancelled by user.");
					filePathTrend = null;
				}
			}
		});

		JButton buttonExit = new JButton("Выход");
		buttonExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		
		componentCenter.add(labelTheme);
		componentCenter.add(buttonMock);
		componentCenter.add(labelFileChooser);
		componentCenter.add(buttonRegress);
		componentCenter.add(buttonTrend);
		componentCenter.add(buttonExit);
		
		return mainPanel;
	}
	
	public JPanel initCalculatePanel()
	{
		final JPanel calculatePanel = new JPanel();
		calculatePanel.setLayout(new BorderLayout());
		calculatePanel.setBackground(Color.WHITE);

		final JPanel charts = new JPanel();
		charts.setBackground(Color.WHITE);
		final JPanel buttons = new JPanel();
		buttons.setBackground(Color.WHITE);
		calculatePanel.add(buttons, BorderLayout.WEST);
		calculatePanel.add(charts, BorderLayout.EAST);
		buttons.setLayout(new FlowLayout());
		JPanel buttonsAll = new JPanel();
		buttons.add(buttonsAll);
		buttonsAll.setBackground(Color.WHITE);
		buttonsAll.setPreferredSize(new Dimension(380, 450));
		buttonsAll.setLayout(new GridLayout(6, 1, 0, 40));
		JButton regress = new JButton("Плотность накоплений в начальный момент (регрессия)");
		buttonsAll.add(regress);
		regress.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (filePathRegress == null) {
					MockDataStore mock = new MockDataStore();
					CalculationRegress regress = new CalculationRegress();
					Informational calculate = regress.densityAccumulationAtTheInitialTime(mock.getInformationRegress());
					Charts draw = new Charts();
					charts.removeAll();
					charts.repaint();
					charts.add(draw.drawChartRegress(calculate,	mock.getInformationRegress()));
					pack();
				} else {
					try {
						FileDataStore data = new FileDataStore();
						Parameters par = new Parameters(filePathRegress);
						
						data.setParameters(par);
						
						CalculationRegress regress = new CalculationRegress();
						Informational calculate = regress.densityAccumulationAtTheInitialTime(data.getInformation());
						Charts draw = new Charts();
						charts.removeAll();
						charts.repaint();
						charts.add(draw.drawChartRegress(calculate,	data.getInformation()));
						pack();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(calculatePanel, "Excepcion while read regress Data");
					}
				}
			}
		});
		JButton trend = new JButton("Скорость накоплений (тренд)");
		buttonsAll.add(trend);
		trend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (filePathTrend == null) {
					MockDataStore mock = new MockDataStore();
					CalculationTrend trend = new CalculationTrend();
					Informational calculate = trend.theSpeedOfAccumulation(mock.getInformationTrend());

					Charts draw = new Charts();
					charts.removeAll();
					charts.repaint();
					charts.add(draw.drawChartTrend(calculate,
							mock.getInformationTrend()));
					pack();
				} else {
					try {
						FileDataStore data = new FileDataStore();
						Parameters par = new Parameters(filePathTrend);
						data.setParameters(par);
						CalculationTrend trend = new CalculationTrend();
						Informational calculate = trend.theSpeedOfAccumulation(data.getInformation());
						Charts draw = new Charts();
						charts.removeAll();
						charts.repaint();
						charts.add(draw.drawChartRegress(calculate,	data.getInformation()));
						pack();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(calculatePanel, "Excepcion while read trend Data");
					}
				}
			}
		});
		JButton buttonFinally = new JButton("Плотность распределения семей по накоплениям");
		buttonsAll.add(buttonFinally);
		buttonFinally.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (filePathRegress == null && filePathTrend == null) {

					MockDataStore mock = new MockDataStore();
					CalculationFinally finall = new CalculationFinally();
					Informational calculate = finall.densityAccumulation(mock.getInformationTrend(), mock.getInformationRegress());

					Charts draw = new Charts();
					charts.removeAll();
					charts.repaint();
					charts.add(draw.drawChartFinally(calculate,	mock.getInformationRegress()));
					pack();
				} else {
					try {
						FileDataStore dataTrend = new FileDataStore();
						FileDataStore dataRegress = new FileDataStore();
						Parameters parTrend = new Parameters(filePathTrend);
						dataTrend.setParameters(parTrend);
						Parameters parRegress = new Parameters(filePathRegress);
						dataRegress.setParameters(parRegress);
						CalculationFinally finall = new CalculationFinally();
						Informational calculate = finall.densityAccumulation(dataTrend.getInformation(), dataRegress.getInformation());
						Charts draw = new Charts();
						charts.removeAll();
						charts.repaint();
						charts.add(draw.drawChartFinally(calculate,	dataRegress.getInformation()));
						pack();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(calculatePanel, ex.getMessage());
					}
				}
			}
		});
		JButton regressAdequacy = new JButton("Проверка регрессии на адекватность");
		buttonsAll.add(regressAdequacy);
		regressAdequacy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (filePathRegress == null) {

					MockDataStore mock = new MockDataStore();
					CalculationRegress regress = new CalculationRegress();
					regress.regressTestForAdequacy(mock.getInformationRegress());
					if (regress.regressTestForAdequacy(mock.getInformationRegress())) {
						JOptionPane.showMessageDialog(calculatePanel, "Регресия адекватна статистическим данным!");
					} else {
						JOptionPane.showMessageDialog(calculatePanel, "Регресия НЕ адекватна статистическим данным!");
					}
				} else {
					try 
					{
						FileDataStore data = new FileDataStore();
						Parameters par = new Parameters(filePathRegress);
						data.setParameters(par);
						CalculationRegress regress = new CalculationRegress();
						regress.densityAccumulationAtTheInitialTime(data.getInformation());

						if (regress.regressTestForAdequacy(data.getInformation())) 
						{
							JOptionPane.showMessageDialog(calculatePanel,"Регресия адекватна статистическим данным!");
						} 
						else 
						{
							JOptionPane.showMessageDialog(calculatePanel,"Регресия НЕ адекватна статистическим данным!");
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(calculatePanel, ex.getMessage());
					}
				}
			}
		});
		JButton trendAdequacy = new JButton("Проверка тренда на адекватность");
		buttonsAll.add(trendAdequacy);
		trendAdequacy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (filePathTrend == null) {

					MockDataStore mock = new MockDataStore();
					CalculationTrend trend = new CalculationTrend();
					trend.trendTestForAdequacy(mock.getInformationTrend());
					if (trend.trendTestForAdequacy(mock.getInformationTrend())) {
						JOptionPane.showMessageDialog(calculatePanel, "Тренд адекватен статистическим данным!");
					} else {
						JOptionPane.showMessageDialog(calculatePanel, "Тренд НЕ адекватен статистическим данным!");
					}
				} else {
					try {
						FileDataStore data = new FileDataStore();
						Parameters par = new Parameters(filePathTrend);
						data.setParameters(par);
						CalculationTrend trend = new CalculationTrend();
						if (trend.trendTestForAdequacy(data.getInformation())) 
						{
							JOptionPane.showMessageDialog(calculatePanel,"Тренд адекватен статистическим данным!");
						} 
						else 
						{
							JOptionPane.showMessageDialog(calculatePanel, "Тренд НЕ адекватен статистическим данным!");
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(calculatePanel, ex.getMessage());
					}
				}
			}
		});
		JButton exit = new JButton("Выход");
		buttonsAll.add(exit);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		
		return calculatePanel;
	}
}
