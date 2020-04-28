import java.awt.Color;
import java.awt.Graphics;


public class Pixel{
	private int x;//положение пикселя
	private int y;
	private int size;//размер пикселя
	private Color color;//цвет пикселя
	private int red;
	private int green;
	private int blue;
	private int alfa;
	
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
		red = newRed;
		green = newGreen;
		blue = newBlue;
		size = newSize;
		alfa = newAlfa;
		color = new Color(red, green, blue, alfa);
	}
	
	public void draw(Graphics canvas) {
		canvas.setColor(color);
		canvas.fillRect(x - size/2, y - size/2, size, size);			
	}	
}
