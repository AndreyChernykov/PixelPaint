package pixelPaint;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class FileWork{
	
	private Scanner scan;

	public void fileWrite(String fileName) {//сохранение файла png
		
		int widthImg = 0;
		int heightImg = 0;
		for(Pixel p : PixelPanel.pixelList) {//находим самые крайние пиксели по горизонтали и вертикали
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
			for(Pixel p : PixelPanel.pixelList) {//считываем из листа и записываем картинку в буффер
				graphics.setColor(p.getColor());
				graphics.fillRect(p.getX(), p.getY(), p.getSize(), p.getSize());
			}

			File output = new File(fileName + ".png");//создаём файл 
			ImageIO.write(pict, "png", output);//записываем в файл буффер
			widthImg = 0;
			heightImg = 0;
			
		} catch (IOException e1) {
			errorSave(e1, "Файл не сохранен!");
		}
	}
	
	public void projectWrite(String fileName){//сохранение файла проекта pixArt
		
		try {
			FileWriter fw = new FileWriter(fileName + ".pixArt");
			for(Pixel p : PixelPanel.pixelList) {
				fw.write(p.getX() + "/" + p.getY() + "/" + p.getSize() + "/" + p.getColor().getRed() + "/" + p.getColor().getGreen() + "/" + p.getColor().getBlue() + "/" + p.getColor().getAlpha() + "\n");
			}
			fw.close();
		} catch (IOException e) {
			errorSave(e, "Файл не сохранен!");
		}
	
	}
	
	public void projectRead(String filename) {//загружаем файл из проекта
		
		try {
			FileReader fr = new FileReader(filename);
			scan = new Scanner(fr);
			while(scan.hasNextLine()) {
				String tempStr = scan.nextLine();
				String [] pix = tempStr.split("/", 7);
				int x = Integer.parseInt(pix[0]);
				int y = Integer.parseInt(pix[1]);
				int size = Integer.parseInt(pix[2]);
				int red = Integer.parseInt(pix[3]);
				int green = Integer.parseInt(pix[4]);
				int blue = Integer.parseInt(pix[5]);
				int alpha = Integer.parseInt(pix[6]);
				PixelPanel.pixelList.add(new Pixel(x, y, size, red, green, blue, alpha));
				
			}
			try {
				fr.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			PixelPaint.frame.repaint();
			
		} catch (FileNotFoundException e) {
			errorSave(e, "Файл не может быть открыт!");
		}
		
	}
	
	private void errorSave(IOException e1, String txtErr) {//окно ошибки сохранения
		JOptionPane.showMessageDialog(PixelPaint.frame, "Ошибка! " + txtErr, e1 + "", JOptionPane.ERROR_MESSAGE);
		//e1.printStackTrace();
	}
}


