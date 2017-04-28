package slotMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SlotController {
	
	private SlotMachineGUI smgui;
	//private StatisticsGUI sgui;
	private SlotMachine sm;
	
	public SlotController(/*SlotMachineGUI smgui,*/  /*StatisticsGUI sgui,*/ SlotMachine sm){
		//this.smgui =smgui;
		//this.sgui =sgui;
		
		this.sm = sm;
	}
	public void runListener(){
		this.smgui =new SlotMachineGUI();
		display(smgui.getCreditsArea(), smgui.getBetArea(),sm );
		runReels(smgui.getF(),smgui.getSpinBut(),smgui.getStatus(),sm, smgui.getReel1(),smgui.getReel2(),smgui.getReel3());
		// Stopping the reels
		stopReel(smgui.getReel1(), smgui.getCreditsArea(), smgui.getBetArea(), smgui.getStatus(), sm, 0);
		stopReel(smgui.getReel2(), smgui.getCreditsArea(), smgui.getBetArea(), smgui.getStatus(),sm, 1);
		stopReel(smgui.getReel3(), smgui.getCreditsArea(), smgui.getBetArea(), smgui.getStatus(), sm, 2);

		// Add a coin button action
		addCoin(smgui.getAddCoinBut(),smgui.getCreditsArea(), smgui.getBetArea(), smgui.getStatus(), sm);
		// Place bet button action
		addBet(smgui.getF(),smgui.getBetOneBut(), smgui.getCreditsArea(), smgui.getBetArea(), smgui.getStatus(), sm);
		// Bet max button action
		betMax(smgui.getF(),smgui.getBetMaxBut(), smgui.getCreditsArea(), smgui.getBetArea(), smgui.getStatus(), sm);
		// reset Button action
		reset(smgui.getResetBut(), smgui.getCreditsArea(), smgui.getBetArea(), smgui.getStatus(), sm);
		// Stats button action
		statsBut(smgui.getStatsBut(), sm);
		
	}

	// Creating the stats window
	

	// Create the new window when button is pressed
	public void statsBut(JButton b, SlotMachine sm) {
		b.addActionListener(new ActionListener()

		{
			@Override
			public void actionPerformed(ActionEvent e) {

				//StatisticsGUI sgui =new StatisticsGUI(sm);
				StatisticsController sc = new StatisticsController(sm);
				sc.runListener();
				
				//sgui.runListener();

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
					/*if (sm.getErrorMsg() != null) {
						//status.setText(sm.getErrorMsg());
						JOptionPane.showMessageDialog(f, sm.getErrorMsg(),"",JOptionPane.WARNING_MESSAGE);
					}*/

				}
			}

		});
	}

	// Betmax button action
	public void betMax(JFrame f, JButton b, JLabel credits, JLabel bets, JLabel status, SlotMachine sm) {
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
					/*if (sm.getErrorMsg() != null) {
						JOptionPane.showMessageDialog(f, sm.getErrorMsg(),"",JOptionPane.WARNING_MESSAGE);
					}*/
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
	

	// Stopping a reel button action
	public void stopReel(JButton b, JLabel credits, JLabel bets, JLabel status, SlotMachine sm, int i) {
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				status.setText("");
				// stopping reel condition
				//UNCOMMENT IF PROBLEM
				//sm.getReels().get(i).setRun(false);
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
	public void runReels(JFrame f,JButton b, JLabel status, SlotMachine sm, JButton reel1, JButton reel2, JButton reel3){ 
		b.addActionListener(new ActionListener() {
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
						JOptionPane.showMessageDialog(f, "Place a Bet","",JOptionPane.WARNING_MESSAGE);
					}
				}

			}

		});
	}
	public void display(JLabel creditsArea, JLabel betArea, SlotMachine sm ){
		creditsArea.setText("CREDITS: " + Integer.toString(sm.getCredits()));
		 betArea.setText("BET: " + Integer.toString(sm.getBet()));
	}

}
