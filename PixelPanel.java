
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Panel;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class PixelPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	static int size = 50;//размер пикселя
	static int red = 100;
	static int green = 100;
	static int blue = 100;
	static int alfa = 255;//альфа канал
	static boolean setka = false;//сетка
	static boolean erase = false;//стёрка
	Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();//узнаём разрешение экрана
	int horizontalSize = scrSize.width;//узнаём ширину экрана
	JSlider sliderBrush;
	JLabel lblBrushSize;
	FileWork fileWork = new FileWork();
	ActionListener actionListener = new ActLis();
	
	public PixelPanel() {
		
		setBackground(Color.white);
		setLayout(null);
			
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("Menu");
		menuBar.setBounds(0, 0, 50, 25);
		add(menuBar);
		
		JMenu menuMenu = new JMenu("Меню");
		menuBar.add(menuMenu);
		
		JMenuItem menuSave = new JMenuItem("Сохранить");
		menuMenu.add(menuSave);
		menuSave.addActionListener(actionListener);

		JMenuItem menuClear = new JMenuItem("Очистить");
		menuMenu.add(menuClear);
		menuClear.addActionListener(actionListener);
		
		JMenuItem menuAbout = new JMenuItem("О программе");
		menuMenu.add(menuAbout);
		menuAbout.addActionListener(actionListener);

		JMenuItem menuExit = new JMenuItem("Выход");
		menuMenu.add(menuExit);		
		menuExit.addActionListener(actionListener);
		
		JMenuBar brushBar = new JMenuBar();
		brushBar.setToolTipText("Кисть");
		brushBar.setBounds(50, 0, 50, 25);
		add(brushBar);
		
		JMenu brushMenu = new JMenu("Кисть");
		brushBar.add(brushMenu);
		
		sliderBrush = new JSlider();
		sliderBrush.setMajorTickSpacing(10);
		sliderBrush.setPaintLabels(true);
		sliderBrush.setPaintTicks(true);
		brushMenu.add(sliderBrush);
		sliderBrush.addChangeListener(new ChangeListener() {//настройка размера кисти
			@Override
			public void stateChanged(ChangeEvent e) {
				size = sliderBrush.getValue();	
				if(size < 2) {
					size = 2;//не меньше 2х пикселей
				}else if(size > 100) {
					size = 100;//не больше 100
				} 
				lblBrushSize.setText("Размер кисти  " + size);
			}		
		});
		
		JMenuBar coloreBar = new JMenuBar();
		coloreBar.setBounds(100, 0, 50, 25);
		add(coloreBar);
		
		JMenu coloreMenu = new JMenu("Цвет");
		coloreBar.add(coloreMenu);
		
		Panel panelColor = new Panel();
		FlowLayout fl_panelColor = (FlowLayout) panelColor.getLayout();
		fl_panelColor.setVgap(50);
		fl_panelColor.setHgap(30);
		panelColor.setBackground(Color.WHITE);

		panelColor.setBackground(new Color(red, green, blue, alfa));
		coloreMenu.add(panelColor);
		
		JSlider sliderRed = new JSlider();
		sliderRed.setPaintLabels(true);
		sliderRed.setMinorTickSpacing(1);
		sliderRed.setMajorTickSpacing(255);
		sliderRed.setSnapToTicks(true);
		sliderRed.setValue(red);
		sliderRed.setMaximum(255);
		sliderRed.setToolTipText("красный");
		coloreMenu.add(sliderRed);
		sliderRed.addChangeListener(new ChangeListener() {//красный цвет
			@Override
			public void stateChanged(ChangeEvent e) {
				red = sliderRed.getValue();
				panelColor.setBackground(new Color(red, green, blue));
			}
		});
		
		JSlider sliderGreen = new JSlider();
		sliderGreen.setMajorTickSpacing(255);
		sliderGreen.setMinorTickSpacing(1);
		sliderGreen.setPaintLabels(true);
		sliderGreen.setSnapToTicks(true);
		sliderGreen.setValue(green);
		sliderGreen.setMaximum(255);
		sliderGreen.setToolTipText("зелёный");
		coloreMenu.add(sliderGreen);
		sliderGreen.addChangeListener(new ChangeListener() {//зелёный цвет
			@Override
			public void stateChanged(ChangeEvent e) {
				green = sliderGreen.getValue();
				panelColor.setBackground(new Color(red, green, blue));
			}
		});
		
		JSlider sliderBlue = new JSlider();
		sliderBlue.setPaintLabels(true);
		sliderBlue.setMinorTickSpacing(1);
		sliderBlue.setMajorTickSpacing(255);
		sliderBlue.setSnapToTicks(true);
		sliderBlue.setValue(blue);
		sliderBlue.setMaximum(255);
		sliderBlue.setToolTipText("синий");
		coloreMenu.add(sliderBlue);
		sliderBlue.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				blue = sliderBlue.getValue();
				panelColor.setBackground(new Color(red, green, blue));
			}
		});
		
		JMenuBar backgroundBar = new JMenuBar();
		backgroundBar.setBounds(150, 0, 50, 25);
		add(backgroundBar);
		
		JMenu backgroundMenu = new JMenu("Фон");
		backgroundBar.add(backgroundMenu);
		
		JMenuItem menuBackgrWhite = new JMenuItem("Белый");
		backgroundMenu.add(menuBackgrWhite);
		menuBackgrWhite.addActionListener(new ActionListener() {//переключение режима стёрка/кисть
			public void actionPerformed(ActionEvent e) {
				setBackground(Color.WHITE);
			}			
		});
		
		JMenuItem menuBackgrBlack = new JMenuItem("Чёрный");
		backgroundMenu.add(menuBackgrBlack);
		menuBackgrBlack.addActionListener(new ActionListener() {//переключение режима стёрка/кисть
			public void actionPerformed(ActionEvent e) {
				setBackground(Color.BLACK);
			}			
		});
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(200, 0, 100, 25);
		add(toolBar);
		
		JButton btnErase = new JButton("Стёрка");
		toolBar.add(btnErase);
		btnErase.addActionListener(new ActionListener() {//переключение режима стёрка/кисть
			public void actionPerformed(ActionEvent e) {
				switch(btnErase.getText()) {
				case "Стёрка":
					btnErase.setText("Кисть");
					erase = true;
					break;
				case "Кисть":
					btnErase.setText("Стёрка");
					erase = false;
					break;
				}
			}			
		});
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setToolTipText("");
		toolBar_1.setFloatable(false);
		toolBar_1.setBounds(300, 0, 500, 25);
		add(toolBar_1);
		
		lblBrushSize = new JLabel("Размер кисти " + size);
		toolBar_1.add(lblBrushSize);
		
		JLabel lblNewLabel = new JLabel("      Прозрачность");
		toolBar_1.add(lblNewLabel);
		
		JSlider sliderAlfa = new JSlider();
		sliderAlfa.setSnapToTicks(true);
		sliderAlfa.setValue(alfa);
		sliderAlfa.setToolTipText("Альфа канал");
		sliderAlfa.setMinorTickSpacing(1);
		sliderAlfa.setMaximum(255);
		sliderAlfa.setMajorTickSpacing(255);
		toolBar_1.add(sliderAlfa);
		
		JRadioButton rdbtnSetka = new JRadioButton("Сетка");
		toolBar_1.add(rdbtnSetka);
		rdbtnSetka.addActionListener(new ActionListener() {//включение рисования по сетке
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnSetka.getSelectedObjects() != null) {
					setka = true;
				}else setka = false;			
			}
		});
		
		sliderAlfa.addChangeListener(new ChangeListener() {//настройка альфа канала
			@Override
			public void stateChanged(ChangeEvent e) {
				int temp = sliderAlfa.getValue();
				alfa = 255-temp;
			}
		});
		
		JToolBar toolBarEnd = new JToolBar();//конечный кусок тулбары
		toolBarEnd.setFloatable(false);
		toolBarEnd.setBounds(800, 0, horizontalSize, 25);
		add(toolBarEnd);
		
		addMouseListener(new PixelListener());
		addMouseMotionListener(new PixelListener());
	}
	
	public void paintComponent(Graphics canvas) {
		super.paintComponent(canvas);
		for(Pixel p : FileWork.pixelList) {
			p.draw(canvas);
		}
	}
	
}
