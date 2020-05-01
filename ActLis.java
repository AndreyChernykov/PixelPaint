package pixelPaint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

class ActLis implements ActionListener{//обработка Меню меню
	
	@Override
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()) {
		case "Сохранить":
			JFileChooser fch = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());//создаём окно выбора директории куда сохранять
			fch.setFileFilter(new FileNameExtensionFilter("*.png", "*.*"));
			int returnValue = fch.showSaveDialog(null);

			if(returnValue == JFileChooser.APPROVE_OPTION) {
				String fileName = fch.getSelectedFile().getName();
				FileWork fileWork = new FileWork();
				fileWork.fileWrite(fch.getCurrentDirectory() + "\\" + fileName);						
			}
			break;
		case "Выход":
			PixelPaint.frame.dispose();
			break;
		case "Очистить":
			PixelPanel.pixelList.clear();
			PixelPaint.frame.repaint();
			break;
		case "О программе":
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Пиксельная рисовалка v1.1 \n2020г.");
			break;
		}			
	}
}
