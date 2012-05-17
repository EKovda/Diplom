package main.ui.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.Box;
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

		// setPreferredSize(new Dimension(getMaximumSize()));
		setPreferredSize(new Dimension(1104, 518));
		setSize(1104, 518);
		initPanel();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void initPanel() {

		// panel
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);

		final JFileChooser fileChooserRegress = new JFileChooser();
		final JFileChooser fileChooserTrend = new JFileChooser();

		JLabel label1 = new JLabel("Моделирование накоплений семей Украины");
		// System.out.println(label1.getFont());
		label1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));

		JLabel label2 = new JLabel("Выберите файл с данными:");

		label2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));

		JLabel fileLabel1 = new JLabel("Файл регрессии");
		fileLabel1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));

		JLabel fileLabel2 = new JLabel("Файл тренд");
		fileLabel2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));

		JButton button1 = new JButton("Выполнить с заданными данными");
		//button1.setSize(300, 200);
		//button1.setSize(new Dimension(300, 200));
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panel, "Данные приняты!");

				// tabbedPane.setSelectedIndex(2);
				// System.out.println(tabbedPane.getSelectedIndex());
			}
		});

		JButton button2 = new JButton("Файл с регрессией");
		button2.addActionListener(new ActionListener() {

			private Component mainPanel;
			private int returnVal;

			@Override
			public void actionPerformed(ActionEvent e) {
				returnVal = fileChooserRegress.showOpenDialog(mainPanel);
				
				 if (returnVal == JFileChooser.APPROVE_OPTION) 
				 { 
					 File file = fileChooserRegress.getSelectedFile();
					 JOptionPane.showMessageDialog(panel, "Opening: " + file.getName()); 
					 filePathRegress = file.getPath(); 
//				  //!!!!!!!!! - сюда 
//////					 ArrayList<Double> listDouble = new  ArrayList<Double>(); 
//////					 File fileScanner = new File(filePathRegress); 
//////					 Scanner scanner; 
//////					 try 
//////					 { 
//////						 scanner = new Scanner(fileScanner); 
//////						 scanner.useLocale(Locale.US); 
//////						 while(scanner.hasNextDouble()) 
//////						 {
//////							 listDouble.add(scanner.nextDouble()); 
//////						 } 
//////						 Double[][] massiveInformationRegress = new Double[listDouble .size()/2][listDouble.size()/2]; 
//////						 massiveInformationRegress = listDouble .toArray(massiveInformationRegress);
//////						 System.out.println("Hello 1"); 
////					 } 
////					 catch (FileNotFoundException e1) 
////					 { 
////					  e1.printStackTrace(); 
////					 }
				  } 
				  else 
				  { 
					  JOptionPane.showMessageDialog(panel, "Open command cancelled by user."); 
					  filePathRegress = null; 
				  }
			}
		});

		JButton button3 = new JButton("Файл с трендом");
		button3.addActionListener(new ActionListener() {

			private int returnVal;
			private Component mainPanel;

			@Override
			public void actionPerformed(ActionEvent e) {
				returnVal = fileChooserTrend.showOpenDialog(mainPanel);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooserTrend.getSelectedFile();
					JOptionPane.showMessageDialog(panel, "Opening: " + file.getName());
					filePathTrend = file.getPath();
					// !!!!

//					ArrayList<Double> listDouble = new ArrayList<Double>();
//					File fileScanner = new File(filePathTrend);
//					Scanner scanner;
//					try {
//						scanner = new Scanner(fileScanner);
//						scanner.useLocale(Locale.US);
//						while (scanner.hasNextDouble()) {
//							listDouble.add(scanner.nextDouble());
//						}
//
//						Double[][] massiveInformationTrend = new Double[listDouble
//								.size() / 2][listDouble.size() / 2];
//						massiveInformationTrend = listDouble
//								.toArray(massiveInformationTrend);
//					} catch (FileNotFoundException e1) {
//						e1.printStackTrace();
//					}
				} else {
					JOptionPane.showMessageDialog(panel, "Open command cancelled by user.");
					filePathTrend = null;
				}
			}
		});

		JButton button4 = new JButton("Выход");
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});

		// mainPanel
		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.WHITE);
		// mainPanel.add(Box.createHorizontalStrut(50));
		mainPanel.add(Box.createRigidArea(new Dimension(getWidth() / 3,
				getHeight() / 14)));
		// System.out.println(getWidth()/3); //341
		// System.out.println(getHeight()/14); //54
		mainPanel.add(label1);
		mainPanel.add(Box.createVerticalStrut(getHeight() / 14));
		mainPanel.add(button1);
		mainPanel.add(Box.createVerticalStrut(getHeight() / 14));
		mainPanel.add(label2);
		mainPanel.add(Box.createVerticalStrut(getHeight() / 28));
		mainPanel.add(button2);
		mainPanel.add(Box.createVerticalStrut(getHeight() / 28));
		mainPanel.add(button3);
		mainPanel.add(Box.createVerticalStrut(getHeight() / 14));
		mainPanel.add(button4);

		// filePanel
//		JPanel filePanel = new JPanel();
//		filePanel.setLayout(new GridLayout(2, 2, 25, 25));
//		filePanel.setBackground(Color.WHITE);
//		filePanel.add(fileLabel1);
//		filePanel.add(fileLabel2);
//		String[] columnNames = { "x", "y" };
//		Double[][] data = { { 9.1, 9.2 }, { 9.5, 9.6 }, { 9.1, 9.2 }, { 9.5, 9.6 } };
//		JTable table1 = new JTable(data, columnNames);
//		JTable table2 = new JTable(data, columnNames);
//		FileDataStore store = new FileDataStore();
//		try {
//			Parameters par = new Parameters(filePathRegress);
//			store.setParameters(par);
//			store.fillingFieldsTable();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
//		filePanel.add(table1);
//		filePanel.add(table2);
		// System.out.println("Hello 2");

		// calculatePanel
		JPanel calculatePanel = new JPanel();
		calculatePanel.setLayout(new BorderLayout());
		calculatePanel.setBackground(Color.WHITE);

		final JPanel charts = new JPanel();
		charts.setBackground(Color.WHITE);
		final JPanel buttons = new JPanel();
		buttons.setBackground(Color.WHITE);
		calculatePanel.add(buttons, BorderLayout.WEST);
		calculatePanel.add(charts, BorderLayout.EAST);
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
		buttons.add(Box.createRigidArea(new Dimension(50, 150)));
		JButton regress = new JButton(
				"Плотность накоплений в начальный момент (регрессия)");
		buttons.add(regress);
		regress.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(panel, "Regress!");
				// JOptionPane.showMessageDialog(panel, filePathRegress);
				if (filePathRegress == null) {
					MockDataStore mock = new MockDataStore();
					// int[] initialIntMassive =
					// mock.getInformationRegress().getIntMassive();
					// double[] initialDoubleMassive =
					// mock.getInformationRegress().getDoubleMassive();
					CalculationRegress regress = new CalculationRegress();
					Informational calculate = regress.densityAccumulationAtTheInitialTime(mock.getInformationRegress());
					// int capacity = calculate.getIntMassive().length;
					// int[] firstMassive = calculate.getIntMassive();
					// double[] secondMassive = calculate.getDoubleMassive();
					// for (int i = 0; i < capacity; i++) {
					// System.out.println(firstMassive[i]);
					// }
					//
					// for (int i = 0; i < capacity; i++) {
					// System.out.println(secondMassive[i]);
					// }
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
						JOptionPane.showMessageDialog(panel, "Excepcion while read regress Data");
					}
				}
			}
		});
		buttons.add(Box.createVerticalStrut(25));
		JButton trend = new JButton("Скорость накоплений (тренд)");
		buttons.add(trend);
		trend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(panel, "Trend!");
				// JOptionPane.showMessageDialog(panel, filePathTrend);
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
						JOptionPane.showMessageDialog(panel, "Excepcion while read trend Data");
					}
				}
			}
		});
		buttons.add(Box.createVerticalStrut(25));
		JButton finall = new JButton("Плотность распределения семей по накоплениям");
		buttons.add(finall);
		finall.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(panel, "FinallyCalculation!");
				if (filePathRegress == null && filePathTrend == null) {

					MockDataStore mock = new MockDataStore();
					CalculationFinally finall = new CalculationFinally();
					Informational calculate = finall.densityAccumulation(mock.getInformationTrend(), mock.getInformationRegress());
					// int capacity = calculate.getIntMassive().length;
					// int[] firstMassive = calculate.getIntMassive();
					// double[] secondMassive = calculate.getDoubleMassive();
					// for (int i = 0; i < capacity; i++) {
					// System.out.println(firstMassive[i]);
					// }
					//
					// for (int i = 0; i < capacity; i++) {
					// System.out.println(secondMassive[i]);
					// }

					Charts draw = new Charts();
					// System.out.println(draw.drawChartRegress(calculate).isEnabled());
					charts.removeAll();
					charts.repaint();
					charts.add(draw.drawChartFinally(calculate,	mock.getInformationRegress()));
					pack();
					//System.out.println("x = " + getSize().getHeight() + " y = " + getSize().getWidth());
				} else {
					try {
						FileDataStore dataTrend = new FileDataStore();
						FileDataStore dataRegress = new FileDataStore();
						Parameters parTrend = new Parameters(filePathTrend);
						dataTrend.setParameters(parTrend);
						Parameters parRegress = new Parameters(filePathRegress);
						dataRegress.setParameters(parRegress);
						CalculationFinally finall = new CalculationFinally();
//						double[] yRegress = dataRegress.getInformation().getDoubleMassive();
//						double[] yTrend = dataTrend.getInformation().getDoubleMassive();
//						for (int i = 0; i < dataRegress.getInformation().getDoubleMassive().length; i++) {
//							System.out.println(yRegress[i]);
//						}
//						for (int i = 0; i < dataTrend.getInformation().getDoubleMassive().length; i++) {
//							System.out.println(yTrend[i]);
//						}
						
						Informational calculate = finall.densityAccumulation(dataTrend.getInformation(), dataRegress.getInformation());
						Charts draw = new Charts();
						charts.removeAll();
						charts.repaint();
						charts.add(draw.drawChartFinally(calculate,	dataRegress.getInformation()));
						pack();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(panel, ex.getMessage());
					}
				}
			}
		});
		buttons.add(Box.createVerticalStrut(25));
		JButton regress_adequacy = new JButton("Проверка регрессии на адекватность");
		buttons.add(regress_adequacy);
		regress_adequacy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(panel,
				// "Тест регрессии на адекватность!");
				if (filePathRegress == null) {

					MockDataStore mock = new MockDataStore();
					CalculationRegress regress = new CalculationRegress();
					regress.regressTestForAdequacy(mock.getInformationRegress());
					if (regress.regressTestForAdequacy(mock
							.getInformationRegress())) {
						JOptionPane.showMessageDialog(panel,
								"Регресия адекватна статистическим данным!");
					} else {
						JOptionPane.showMessageDialog(panel,
								"Регресия НЕ адекватна статистическим данным!");
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
							JOptionPane.showMessageDialog(panel,"Регресия адекватна статистическим данным!");
						} 
						else 
						{
							JOptionPane.showMessageDialog(panel,"Регресия НЕ адекватна статистическим данным!");
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(panel, ex.getMessage());
					}
				}
			}
		});
		buttons.add(Box.createVerticalStrut(25));
		JButton trend_adequacy = new JButton("Проверка тренда на адекватность");
		buttons.add(trend_adequacy);
		trend_adequacy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(panel,
				// "Тест тренда на адекватность!");
				if (filePathTrend == null) {

					MockDataStore mock = new MockDataStore();
					CalculationTrend trend = new CalculationTrend();
					trend.trendTestForAdequacy(mock.getInformationTrend());
					if (trend.trendTestForAdequacy(mock.getInformationTrend())) {
						JOptionPane.showMessageDialog(panel,
								"Тренд адекватен статистическим данным!");
					} else {
						JOptionPane.showMessageDialog(panel,
								"Тренд НЕ адекватен статистическим данным!");
					}
				} else {
					try {
						FileDataStore data = new FileDataStore();
						Parameters par = new Parameters(filePathTrend);
						data.setParameters(par);
						CalculationTrend trend = new CalculationTrend();
						if (trend.trendTestForAdequacy(data.getInformation())) 
						{
							JOptionPane.showMessageDialog(panel,"Тренд адекватен статистическим данным!");
						} 
						else 
						{
							JOptionPane.showMessageDialog(panel, "Тренд НЕ адекватен статистическим данным!");
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(panel, ex.getMessage());
					}
				}
			}
		});

		buttons.add(Box.createVerticalStrut(50));
		JButton exit = new JButton("Выход");
		buttons.add(exit);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.addTab("Main", mainPanel);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
//		tabbedPane.addTab("File", filePanel);
//		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		tabbedPane.addTab("Calculate", calculatePanel);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		panel.add(tabbedPane);
		add(panel);

	}
}
