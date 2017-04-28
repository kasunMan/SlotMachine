package slotMachine;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatisticsGUI {
	private JFrame f ;
	private JPanel p ;
	private JLabel matchesArea = new JLabel();
	private JLabel winsArea = new JLabel();
	private JLabel lossesArea = new JLabel();
	private JLabel avgWinningsArea = new JLabel();
	private JButton saveBut;
	
	
	private String totalMatches;
	private String totalWins;
	private String totalLosses;
	private String avgCreditsS;
	
	//private SlotMachine sm;
	//I have used JLabels to show the matches, wins, lossess and average 
	//credits won. Used JButton to show the Save Statistics button
	public  StatisticsGUI() {
		//this.sm = sm;
		 this.f = new JFrame("Statistics");

		// Comment this if problem
		 this.p = new JPanel();
		// Setting the layout manager
		p.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		// The JLabels
		/*JLabel matchesArea = new JLabel();
		JLabel winsArea = new JLabel();
		JLabel lossesArea = new JLabel();
		JLabel avgWinningsArea = new JLabel();*/
		this.saveBut = new JButton("SAVE STATISTICS");
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
		//f.pack();
		f.setSize(275, 275);
		/*double avgCredits;
		// Getting the matches, wins losses
		this.totalMatches ="TOTAL MATCHES :" +  Integer.toString(sm.getMatches());
		this.totalWins = "WINS :" +Integer.toString(sm.getWins());
		this.totalLosses = "LOSSES :" +Integer.toString(sm.getLosses());
		// Average coins won
		try {
			avgCredits = 1.00 * sm.getTotalWinnings() / sm.getMatches();

		} catch (ArithmeticException e) {
			avgCredits = 0;
		}

		this.avgCreditsS = "AVERAGE CREDITS WON :" +Double.toString(avgCredits);
		// TAKE INTO ACCOUNT FREE SPINS
		// Displaying the stats
		matchesArea.setText("TOTAL MATCHES :" + totalMatches);
		winsArea.setText("WINS :" + totalWins);
		lossesArea.setText("LOSSES :" + totalLosses);
		avgWinningsArea.setText("AVERAGE CREDITS WON :" + avgCredits);
*/
		

	}

	public JFrame getF() {
		return f;
	}

	public void setF(JFrame f) {
		this.f = f;
	}

	public JPanel getP() {
		return p;
	}

	public void setP(JPanel p) {
		this.p = p;
	}

	public JLabel getMatchesArea() {
		return matchesArea;
	}

	public void setMatchesArea(JLabel matchesArea) {
		this.matchesArea = matchesArea;
	}

	public JLabel getWinsArea() {
		return winsArea;
	}

	public void setWinsArea(JLabel winsArea) {
		this.winsArea = winsArea;
	}

	public JLabel getLossesArea() {
		return lossesArea;
	}

	public void setLossesArea(JLabel lossesArea) {
		this.lossesArea = lossesArea;
	}

	public JLabel getAvgWinningsArea() {
		return avgWinningsArea;
	}

	public void setAvgWinningsArea(JLabel avgWinningsArea) {
		this.avgWinningsArea = avgWinningsArea;
	}

	public JButton getSaveBut() {
		return saveBut;
	}

	public void setSaveBut(JButton saveBut) {
		this.saveBut = saveBut;
	}

	public String getTotalMatches() {
		return totalMatches;
	}

	public void setTotalMatches(String totalMatches) {
		this.totalMatches = totalMatches;
	}

	public String getTotalWins() {
		return totalWins;
	}

	public void setTotalWins(String totalWins) {
		this.totalWins = totalWins;
	}

	public String getTotalLosses() {
		return totalLosses;
	}

	public void setTotalLosses(String totalLosses) {
		this.totalLosses = totalLosses;
	}

	public String getAvgCreditsS() {
		return avgCreditsS;
	}

	public void setAvgCreditsS(String avgCreditsS) {
		this.avgCreditsS = avgCreditsS;
	}

	/*public SlotMachine getSm() {
		return sm;
	}

	public void setSm(SlotMachine sm) {
		this.sm = sm;
	}
	*/
	
	

}
