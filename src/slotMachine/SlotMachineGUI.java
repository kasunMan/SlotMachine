package slotMachine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.*;

public class SlotMachineGUI {
	
	private JFrame f = new JFrame("Slot Machine");

	// Reel buttons
	private JButton reel1 = new JButton();
	private JButton reel2 = new JButton();
	private JButton reel3 = new JButton();
	// Spin button
	private JButton spinBut = new JButton("Spin");
	// Creating the buttons
	private JButton betOneBut = new JButton("Bet One");
	private JButton addCoinBut = new JButton("Add Coin");
	private JButton betMaxBut = new JButton("Bet Max");
	private JButton resetBut = new JButton("Reset");
	private JButton statsBut = new JButton("Stats");
	
	private JLabel creditsArea;
	private JLabel betArea;
	private JLabel status;
	
	//private SlotMachine sm;

	public SlotMachineGUI() {
		//this.sm =sm;

		// getting the default symbol
		ImageIcon icon = new ImageIcon("images/redseven.png");
		Image scaleImage = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		ImageIcon im = new ImageIcon(scaleImage);
		// Symbol s = new Symbol(scaleImage, 6);

		

		// Setting the colors
		f.getContentPane().setBackground(Color.LIGHT_GRAY);
		spinBut.setBackground(Color.GREEN);
		reel1.setBackground(Color.WHITE);
		reel2.setBackground(Color.WHITE);
		reel3.setBackground(Color.WHITE);

		spinBut.setMinimumSize(new Dimension(100, 75));
		spinBut.setPreferredSize(new Dimension(100, 75));
		spinBut.setMaximumSize(new Dimension(100, 75));

		JPanel p = new JPanel();
		p.setOpaque(false);

		
		

		statsBut.setBackground(Color.YELLOW);

		// Creating the Labels
		 creditsArea = new JLabel();
		 betArea = new JLabel();
		 status = new JLabel();

		status.setForeground(Color.red);
		// status.setFont (status.getFont ().deriveFont (14.0f));

		// Setting the default symbol to reels
		reel1.setIcon(im);
		reel2.setIcon(im);
		reel3.setIcon(im);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);

		// Setting the layoutmanager
		p.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// First row
		gbc.insets = new Insets(10, 0, 5, 0);
		gbc.gridy = 0;
		p.add(betArea, gbc);

		p.add(status, gbc);

		p.add(creditsArea, gbc);
		gbc.gridx = GridBagConstraints.RELATIVE;
		// Second row
		gbc.gridy = 1;
		p.add(reel1, gbc);
		p.add(reel2, gbc);
		p.add(reel3, gbc);

		// Third row
		gbc.gridy = 2;
		p.add(betOneBut, gbc);
		gbc.gridx = GridBagConstraints.RELATIVE;

		p.add(spinBut, gbc);
		p.add(addCoinBut, gbc);
		gbc.gridx = GridBagConstraints.RELATIVE;
		// Fourth row
		gbc.gridy = 3;
		p.add(betMaxBut, gbc);
		gbc.gridx = 2;
		p.add(resetBut, gbc);
		gbc.gridy = 4;
		gbc.gridx = 1;
		p.add(statsBut, gbc);

		//f.pack();
		f.setSize(500, 500);
		f.setVisible(true);

		/*
		 * sm.getReels().get(0).setButton(reel1);
		 * sm.getReels().get(1).setButton(reel2);
		 * sm.getReels().get(2).setButton(reel3);
		 */

		// Action for when spin is clicked
		/*spinBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				status.setText("");
				// Checking if the reels are running
				if (!reelsRunning(sm)) {
					// Setting that the reel was running

					// Resetting the reels
					sm.setReels();

					// Setting the buttons for reels
					sm.getReels().get(0).setButton(reel1);
					sm.getReels().get(1).setButton(reel2);
					sm.getReels().get(2).setButton(reel3);

					// Make running not possible if bet==0

					if (sm.getBet() != 0) {
						sm.setSpinning(true);
						sm.getReels().get(0).start();
						sm.getReels().get(1).start();
						sm.getReels().get(2).start();

						// Setting the reels as running
						sm.getReels().get(0).setRunning(true);
						sm.getReels().get(1).setRunning(true);
						sm.getReels().get(2).setRunning(true);
					} else {
						// If no bet is placed
						status.setText("PLACE A BET");
					}
				}

			}

		});*/
		/*runReels(spinBut,status,sm);
		// Stopping the reels
		stopReel(reel1, creditsArea, betArea, status, sm, 0);
		stopReel(reel2, creditsArea, betArea, status, sm, 1);
		stopReel(reel3, creditsArea, betArea, status, sm, 2);

		// Add a coin button action
		addCoin(addCoinBut, creditsArea, betArea, status, sm);
		// Place bet button action
		addBet(betOneBut, creditsArea, betArea, status, sm);
		// Bet max button action
		betMax(betMaxBut, creditsArea, betArea, status, sm);
		// reset Button action
		reset(resetBut, creditsArea, betArea, status, sm);
		// Stats button action
		statsBut(statsBut, sm);*/

	}

	public JFrame getF() {
		return f;
	}

	public void setF(JFrame f) {
		this.f = f;
	}

	public JButton getReel1() {
		return reel1;
	}

	public void setReel1(JButton reel1) {
		this.reel1 = reel1;
	}

	public JButton getReel2() {
		return reel2;
	}

	public void setReel2(JButton reel2) {
		this.reel2 = reel2;
	}

	public JButton getReel3() {
		return reel3;
	}

	public void setReel3(JButton reel3) {
		this.reel3 = reel3;
	}

	public JButton getSpinBut() {
		return spinBut;
	}

	public void setSpinBut(JButton spinBut) {
		this.spinBut = spinBut;
	}

	public JButton getBetOneBut() {
		return betOneBut;
	}

	public void setBetOneBut(JButton betOneBut) {
		this.betOneBut = betOneBut;
	}

	public JButton getAddCoinBut() {
		return addCoinBut;
	}

	public void setAddCoinBut(JButton addCoinBut) {
		this.addCoinBut = addCoinBut;
	}

	public JButton getBetMaxBut() {
		return betMaxBut;
	}

	public void setBetMaxBut(JButton betMaxBut) {
		this.betMaxBut = betMaxBut;
	}

	public JButton getResetBut() {
		return resetBut;
	}

	public void setResetBut(JButton resetBut) {
		this.resetBut = resetBut;
	}

	public JButton getStatsBut() {
		return statsBut;
	}

	public void setStatsBut(JButton statsBut) {
		this.statsBut = statsBut;
	}

	public JLabel getCreditsArea() {
		return creditsArea;
	}

	public void setCreditsArea(JLabel creditsArea) {
		this.creditsArea = creditsArea;
	}

	public JLabel getBetArea() {
		return betArea;
	}

	public void setBetArea(JLabel betArea) {
		this.betArea = betArea;
	}

	public JLabel getStatus() {
		return status;
	}

	public void setStatus(JLabel status) {
		this.status = status;
	}

	/*public SlotMachine getSm() {
		return sm;
	}

	public void setSm(SlotMachine sm) {
		this.sm = sm;
	}*/
	
	

	/*
	 * public static void main(String[] args) { UI u = new UI(); SlotMachine sm
	 * = new SlotMachine(); u.createUI(sm);
	 * 
	 * }
	 */

}
