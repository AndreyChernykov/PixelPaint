import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PixelListener extends MouseAdapter{//обработка мыши

	public void mousePressed(MouseEvent e) {//обработка нажатий 
		brushWork(e);
	}
	
	public void mouseDragged(MouseEvent e) {//обработка движения мышью
		brushWork(e);			
	}
		
	public void brushWork(MouseEvent e) {//работа кисти
		if(PixelPanel.erase) {//стирание
			for(Pixel p : FileWork.pixelList) {
				if(e.getX() >= p.getX() - p.getSize()/2 && e.getX() <= p.getX() + p.getSize()/2 && e.getY() >= p.getY() - p.getSize()/2 && e.getY() <= p.getY() + p.getSize()/2) {
					FileWork.pixelList.remove(p);
					break;
				}
			}
		}else {//рисование
			FileWork.pixelList.add(new Pixel(e.getX(), e.getY(), PixelPanel.size, PixelPanel.red, PixelPanel.green, PixelPanel.blue, PixelPanel.alfa));
		}			
		PixelPaint.frame.repaint();	
	}

}
