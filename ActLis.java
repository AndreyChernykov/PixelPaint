package pixelPaint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

class ActLis implements ActionListener{//обработка Меню меню
	
	PixelPanel pixelPanel;
	
	ActLis(){
		pixelPanel = new PixelPanel();
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()) {
		case "Сохранить":
			saveFile();
			break;
		case "Выход":
			exit();
			break;
		case "Очистить":
			clear();
			break;
		case "О программе":
			aboutApp();
			break;
		}			
	}
	
	private void saveFile() {//сохранение файла
		JFileChooser fch = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());//создаём окно выбора директории куда сохранять
		fch.setFileFilter(new FileNameExtensionFilter("*.png", "*.*"));
		int returnValue = fch.showSaveDialog(null);

		if(returnValue == JFileChooser.APPROVE_OPTION) {
			String fileName = fch.getSelectedFile().getName();
			FileWork fileWork = new FileWork();
			fileWork.fileWrite(fch.getCurrentDirectory() + "\\" + fileName);						
		}
	}
	
	private void exit() {//выход из программы
		PixelPaint.frame.dispose();
	}
	
	private void clear() {//очистка экрана
		
		pixelPanel.pixelList.clear();
		PixelPaint.frame.repaint();
	}
	
	private void aboutApp() {//о программе
		String about = "Пиксельная рисовалка v1.1 \n2020г.";
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, about);
	}
}
