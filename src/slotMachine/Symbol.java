package slotMachine;

import java.awt.Image;
//import java.awt.image.BufferedImage;

public class Symbol implements ISymbol {

	private Image image;
	private int value;

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return this.image;
	}

	public void setValue(int val) {
		this.value = val;
	}

	public int getValue() {
		return this.value;
	}

	public Symbol(Image image, int value) {
		super();
		this.image = image;
		this.value = value;
	}

	public Symbol() {

	}

}
