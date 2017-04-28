package slotMachine;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Reel extends Thread {
	private final int symbolsNum = 6;
	private List<Symbol> symbols = new ArrayList<Symbol>();
	

	private List<Image> images = new ArrayList<Image>();
	private int[] vals = new int[symbolsNum];
	private int[] val = { 7, 6, 5, 4, 3, 2 };
	private String[] imageLocations = { "redseven.png", "bell.png", "watermelon.png", "plum.png", "lemon.png",
			"cherry.png" };
	private JButton button = new JButton();
	private int lastVal;
	private boolean run = true;
	private boolean running = false;

	public Reel() {
		setImages();
		Collections.shuffle(symbols);
	}

	// returning the symbols in random order
	public List<Symbol> spin() {
		//Collections.shuffle(symbols);
		return symbols;
	}
	/*
	 * public List<Symbol> implement(List<BufferedImage> images){
	 * 
	 * for(int i = 0; i<symbols.size(); i++){
	 * symbols.get(i).setImage(images.get(i)); symbols.get(i).setValue(val); }
	 * 
	 * return symbols; }
	 */

	// Setting the images to the reels
	public void setImages() {
		for (int i = 0; i < symbolsNum; i++) {

			Image img1 = null;
			try {
				img1 = ImageIO.read(new File("images/" + imageLocations[i]));
			} catch (IOException e) {

				System.out.println("Images not found");
			}

			// this.images.set(i,img1);
			this.vals[i] = val[i];
			Symbol s = new Symbol(resizeImage(img1), vals[i]);
			this.symbols.add(i, s);

		}
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public int getLastVal() {
		return lastVal;
	}

	public void setLastVal(int lastVal) {
		this.lastVal = lastVal;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public List<Symbol> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<Symbol> symbols) {
		this.symbols = symbols;
	}

	public List<Image> getImages() {
		return images;
	}

	/*
	 * public void setImages(List<Image> images) { this.images = images; }
	 */
	public int[] getVals() {
		return vals;
	}

	public void setVals(int[] vals) {
		this.vals = vals;
	}

	// Resize the image
	public Image resizeImage(Image img) {
		ImageIcon icon = new ImageIcon(img);
		Image scaleImage = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		// ImageIcon im = new ImageIcon(scaleImage);
		return scaleImage;
	}

	// run method for thread
	public void run() {
		// Reel r = new Reel();
		List<Symbol> syms = this.spin();
		// System.out.println(syms.get(5).getValue());

		// Condition to stop thread
		//WAS RUN 
		while (true) {
			try {
				// List<Symbol> syms =this.spin();
				// Showing the symbols in the reel
				for (int i = 0; i < syms.size(); i++) {

					button.setIcon(new ImageIcon(syms.get(i).getImage()));
					// setting the stopped value
					this.lastVal = syms.get(i).getValue();
					Thread.sleep(75);
					
					//UNCOMMENT IF PROBLEM
					/*if (!run) {
						// Thread.interrupted();
						break;
					}*/
				}

			} catch (InterruptedException e) {
				// this.destroy();
				 break;
			}

		}
		//System.out.println(syms.get(5).getValue());
		//System.out.println(this.lastVal);
	}
	/*
	 * public void start(){ //running =false; }
	 */

}
