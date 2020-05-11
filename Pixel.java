package pixelPaint;

import java.awt.Color;
import java.awt.Graphics;

public class Pixel{
	private int x;//положение пикселя
	private int y;
	private int size;//размер пикселя
	private Color color;//цвет пикселя
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;  
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;  
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Pixel(int newX, int newY, int newSize, int newRed, int newGreen, int newBlue, int newAlfa) {
		if(PixelPanel.setka) {
			x = (newX / newSize) * newSize + newSize/2;//рисование по сетке
			y = (newY / newSize) * newSize + newSize/2;
		}else {
			x = newX;//рисование без сетке
			y = newY;
		}
		size = newSize;
		color = new Color(newRed, newGreen, newBlue, newAlfa);
	}
	
	public void draw(Graphics canvas) {
		canvas.setColor(color);
		canvas.fillRect(x - size/2, y - size/2, size, size);			
	}	
}

