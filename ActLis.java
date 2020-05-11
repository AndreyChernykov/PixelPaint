package pixelPaint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

class ActLis implements ActionListener{//обработка Меню меню
	FileWork fileWork;
	ActLis(){
		fileWork = new FileWork();
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()) {
		case "Сохранить":
			saveFile("*.png");
			break;
		case "Сохранить проект":
			saveFile("*.pixArt");
			break;
		case "Загрузить проект":
			loadFile();
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
	
	
	private void saveFile(String txt) {//сохранение файла
		JFileChooser fch = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());//создаём окно выбора директории куда сохранять
		fch.setFileFilter(new FileNameExtensionFilter(txt, "*.*"));
		int returnValue = fch.showDialog(null, "Сохранить файл");

		if(returnValue == JFileChooser.APPROVE_OPTION) {
			String fileName = fch.getSelectedFile().getName();
			if(txt.equals("*.png")) {
				fileWork.fileWrite(fch.getCurrentDirectory() + "\\" + fileName);// сохранение в png
			}else {
				fileWork.projectWrite(fch.getCurrentDirectory() + "\\" + fileName);	//сохранение проекта в pixArt
			}									
		}
	}
	
	private void loadFile(){//загрузить проект
		clear();
		JFileChooser fileopen = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int ret = fileopen.showDialog(null, "Открыть файл");
		if(ret == JFileChooser.APPROVE_OPTION){
			File file = fileopen.getSelectedFile();
			fileWork.projectRead(fileopen.getCurrentDirectory() + "\\" + file.getName());
			
		}
	}
		
	private void exit() {//выход из программы
		PixelPaint.frame.dispose();
	}
	
	private void clear() {//очистка экрана
		
		PixelPanel.pixelList.clear();
		PixelPaint.frame.repaint();
	}
	
	private void aboutApp() {//о программе
		String about = "Пиксельная рисовалка v1.2 \n2020г.";
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, about);
	}
}
