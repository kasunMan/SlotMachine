package slotMachine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class UI {

	private JFrame f = new JFrame("Slot Machine");
	// Reel buttons
	private JButton reel1 = new JButton();
	private JButton reel2 = new JButton();
	private JButton reel3 = new JButton();
	// Spin button
	private JButton spinBut = new JButton("Spin");

	void createUI(SlotMachine sm) {

		// getting the default symbol
		ImageIcon icon = new ImageIcon("images/redseven.png");
		Image scaleImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon im = new ImageIcon(scaleImage);
		// Symbol s = new Symbol(scaleImage, 6);

		

		// Setting the colors
		f.getContentPane().setBackground(Color.LIGHT_GRAY);
		spinBut.setBackground(Color.GREEN);
		reel1.setBackground(Color.WHITE);
		reel2.setBackground(Color.WHITE);
		reel3.setBackground(Color.WHITE);

		spinBut.setMinimumSize(new Dimension(75, 75));

		JPanel p = new JPanel();
		p.setOpaque(false);

		// Creating the buttons
		JButton betOneBut = new JButton("Bet One");
		JButton addCoinBut = new JButton("Add Coin");
		JButton betMaxBut = new JButton("Bet Max");
		JButton resetBut = new JButton("Reset");
		JButton statsBut = new JButton("Stats");

		statsBut.setBackground(Color.YELLOW);

		// Creating the Labels
		JLabel creditsArea = new JLabel("CREDITS: " + Integer.toString(sm.getCredits()));
		JLabel betArea = new JLabel("BET: " + Integer.toString(sm.getBet()));
		JLabel status = new JLabel();

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

		f.setSize(400, 500);
		f.setVisible(true);

		/*
		 * sm.getReels().get(0).setButton(reel1);
		 * sm.getReels().get(1).setButton(reel2);
		 * sm.getReels().get(2).setButton(reel3);
		 */

		// Action for when spin is clicked
		spinBut.addActionListener(new ActionListener() {
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

		});
		// Stopping the reels
		stopReel(reel1, creditsArea, betArea, status, sm, 0);
		stopReel(reel2, creditsArea, betArea, status, sm, 1);
		stopReel(reel3, creditsArea, betArea, status, sm, 2);

		// Add a coin button action
		addCoin(addCoinBut, creditsArea, betArea, status, sm);
		// Place bet button action
		addBet(f,betOneBut, creditsArea, betArea, status, sm);
		// Bet max button action
		betMax(f,betMaxBut, creditsArea, betArea, status, sm);
		// reset Button action
		reset(resetBut, creditsArea, betArea, status, sm);
		// Stats button action
		statsBut(statsBut, sm);

	}

	// Creating the stats window
	public void createStatsPage(SlotMachine sm) {
		JFrame f = new JFrame("Statistics");

		// Comment this if problem
		JPanel p = new JPanel();
		// Setting the layout manager
		p.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		// The JLabels
		JLabel matchesArea = new JLabel();
		JLabel winsArea = new JLabel();
		JLabel lossesArea = new JLabel();
		JLabel avgWinningsArea = new JLabel();
		JButton saveBut = new JButton("SAVE STATISTICS");
		gbc.insets = new Insets(10, 0, 5, 0);
		gbc.gridy = 0;
		p.add(matchesArea, gbc);
		gbc.gridy = 1;
		p.add(winsArea, gbc);
		gbc.gridy = 2;
		p.add(lossesArea, gbc);
		gbc.gridy = 3;
		p.add(avgWinningsArea, gbc);
		gbc.gridy = 4;
		p.add(saveBut, gbc);
		f.add(p);
		f.setVisible(true);
		f.setSize(250, 250);
		double avgCredits;
		// Getting the matches, wins losses
		String totalMatches = Integer.toString(sm.getMatches());
		String totalWins = Integer.toString(sm.getWins());
		String totalLosses = Integer.toString(sm.getLosses());
		// Average coins won
		try {
			avgCredits = 1.00 * sm.getTotalWinnings() / sm.getMatches();

		} catch (ArithmeticException e) {
			avgCredits = 0;
		}

		String avgCreditsS = Double.toString(avgCredits);
		// TAKE INTO ACCOUNT FREE SPINS
		// Displaying the stats
		matchesArea.setText("TOTAL MATCHES :" + totalMatches);
		winsArea.setText("WINS :" + totalWins);
		lossesArea.setText("LOSSES :" + totalLosses);
		avgWinningsArea.setText("AVERAGE CREDITS WON :" + avgCredits);

		// getting the date
		Date currentDate = new Date();
		/*
		 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		 * LocalDate localDate = LocalDate.now();
		 */
		// System.out.println(dtf.format(localDate));

		// Getting the Time
		// Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy.MM.dd");

		// System.out.println(sdf.format(currentDate)+"
		// "+sdfTime.format(currentDate));
		// System.out.println(sdf.format(cal.getTime()));

		// String fileText = sdf.format(cal.getTime()) + "." +
		// dtf.format(localDate);
		String fileText = sdfTime.format(currentDate) + "." + sdf.format(currentDate);
		// System.out.println(fileText);

		// Save button action to save stats to file
		saveBut.addActionListener(new ActionListener()

		{
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					// Creating a Printwriter
					PrintWriter writer = new PrintWriter(fileText + ".txt");
					// Writing the information
					writer.println("TOTAL MATCHES :" + totalMatches);
					writer.println("WINS :" + totalWins);
					writer.println("LOSSES :" + totalLosses);
					writer.println("AVERAGE CREDITS WON :" + avgCreditsS);
					writer.close();
					JOptionPane.showMessageDialog(f, "Statistics saved to file " + fileText + ".txt");

				} catch (IOException e1) {
					System.out.println("File Not found");
				}

			}

		});

	}

	// Create the new window when button is pressed
	public void statsBut(JButton b, SlotMachine sm) {
		b.addActionListener(new ActionListener()

		{
			@Override
			public void actionPerformed(ActionEvent e) {

				createStatsPage(sm);

			}

		});
	}

	// Checking if the reels are running
	public boolean reelsRunning(SlotMachine sm) {
		if (sm.getReels().get(0).isRunning() || sm.getReels().get(1).isRunning() || sm.getReels().get(2).isRunning()) {
			return true;
		} else {
			return false;
		}
	}

	// Setting the bets and credits area
	public void setCreditsBet(JLabel credits, JLabel bets, JLabel status, SlotMachine sm) {
		credits.setText("CREDITS: " + Integer.toString(sm.getCredits()));
		bets.setText("BET: " + Integer.toString(sm.getBet()));
	}

	// Add coin action
	public void addCoin(JButton b, JLabel credits, JLabel bets, JLabel status, SlotMachine sm) {
		b.addActionListener(new ActionListener()

		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// Checking if the reels are running So you can't add coins when
				// it is running
				if (!reelsRunning(sm)) {
					status.setText("");

					sm.addCoin();
					// Showing credits and bets
					setCreditsBet(credits, bets, status, sm);
				}

			}

		});
	}

	// Add bet button action
	public void addBet(JFrame f,JButton b, JLabel credits, JLabel bets, JLabel status, SlotMachine sm) {
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Checking if the reels are running and is not a free spin So
				// you can't add bets when it is running
				if (!reelsRunning(sm) /* && !sm.isFreeSpin() */) {

					status.setText("");

					// Setting the error message to null
					sm.setErrorMsg(null);
					try{
						sm.betOne();
						}
						catch(InsufficientCreditsException e1){
							JOptionPane.showMessageDialog(f, sm.getErrorMsg(),"",JOptionPane.WARNING_MESSAGE);
						}
					// Showing the credits and bets
					setCreditsBet(credits, bets, status, sm);

					// Showing if there is an error. Not enough credits
					if (sm.getErrorMsg() != null) {
						status.setText(sm.getErrorMsg());
					}

				}
			}

		});
	}

	// Betmax button action
	public void betMax(JFrame f ,JButton b, JLabel credits, JLabel bets, JLabel status, SlotMachine sm) {
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Checking if the reels are running and is not a free spin So
				// you can't add bets when it is running
				if (!reelsRunning(sm) /* && !sm.isFreeSpin() */) {
					status.setText("");
					// Setting the error message to null
					sm.setErrorMsg(null);
					try{
						sm.betMax();
						}
						catch(InsufficientCreditsException e1){
							JOptionPane.showMessageDialog(f, sm.getErrorMsg(),"",JOptionPane.WARNING_MESSAGE);
						}

					setCreditsBet(credits, bets, status, sm);

					// Showing if there is an error. Not enough credits
					if (sm.getErrorMsg() != null) {
						status.setText(sm.getErrorMsg());
					}
				}

			}

		});
	}

	// reset button action
	public void reset(JButton b, JLabel credits, JLabel bets, JLabel status, SlotMachine sm) {
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Checking if the reels are running and is not a free spin So
				// you can't remove bets when it is running
				if (!reelsRunning(sm) && !sm.isFreeSpin()) {
					status.setText("");
					sm.reset();

					// Showing if there is an error. Not enough credits
					setCreditsBet(credits, bets, status, sm);
				}

			}

		});
	}
	/*
	 * public void runReels(JLabel status, SlotMachine sm) {
	 * button.addActionListener(new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * status.setText(""); sm.setReels();
	 * 
	 * sm.getReels().get(0).setButton(reel1);
	 * sm.getReels().get(1).setButton(reel2);
	 * sm.getReels().get(2).setButton(reel3);
	 * 
	 * if (sm.getBet() != 0) { sm.getReels().get(0).start();
	 * sm.getReels().get(1).start(); sm.getReels().get(2).start(); } else {
	 * status.setText("PLACE A BET"); } }
	 * 
	 * }); }
	 */

	// Stopping a reel button action
	public void stopReel(JButton b, JLabel credits, JLabel bets, JLabel status, SlotMachine sm, int i) {
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				status.setText("");
				// stopping reel condition
				sm.getReels().get(i).setRun(false);
				sm.getReels().get(i).interrupt();

				// Reel state is not running
				sm.getReels().get(i).setRunning(false);

				// If all the reels are not running
				if (!sm.getReels().get(0).isRunning() && !sm.getReels().get(1).isRunning()
						&& !sm.getReels().get(2).isRunning() && sm.isSpinning()) {
					// compare and find the results
					sm.compare();
					// Showing the results
					status.setText(sm.getResults());
					setCreditsBet(credits, bets, status, sm);
					// Setting the reels spinning as false
					sm.setSpinning(false);
				}

			}

		});

	}

	/*
	 * public static void main(String[] args) { UI u = new UI(); SlotMachine sm
	 * = new SlotMachine(); u.createUI(sm);
	 * 
	 * }
	 */

}
