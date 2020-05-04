package pixelPaint;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class FileWork{
	
	PixelPanel pixelPanel;
	
	FileWork(){
		pixelPanel = new PixelPanel();
	}
	
	public void fileWrite(String fileName) {//сохранение файла

		int widthImg = 0;
		int heightImg = 0;
		for(Pixel p : pixelPanel.pixelList) {//находим самые крайние пиксели по горизонтали и вертикали
			if(p.getX() + p.getSize() > widthImg) {
				widthImg = p.getX() + p.getSize();
			}
			if(p.getY() + p.getSize() > heightImg) {
				heightImg = p.getY() + p.getSize();
			}
		}
		try {
			BufferedImage pict = new BufferedImage(widthImg+10, heightImg+10, BufferedImage.TYPE_INT_ARGB);//создаём буфер изображения
			pict.createGraphics().drawImage(pict, 0, 0, null, null);
						
			Graphics2D graphics = (Graphics2D) pict.getGraphics();
			for(Pixel p : pixelPanel.pixelList) {//считываем из листа и записываем картинку в буффер
				graphics.setColor(p.getColor());
				graphics.fillRect(p.getX(), p.getY(), p.getSize(), p.getSize());
			}

			File output = new File(fileName + ".png");//создаём файл 
			ImageIO.write(pict, "png", output);//записываем в файл буффер
			widthImg = 0;
			heightImg = 0;
			
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(PixelPaint.frame, "Ошибка! Файл не сохранен!", e1 + "", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}
}

