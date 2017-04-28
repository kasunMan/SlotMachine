package slotMachine;

import java.awt.Image;
//import java.awt.image.BufferedImage;

public interface ISymbol {
	void setImage(Image image);

	Image getImage();

	void setValue(int val);

	int getValue();

}
