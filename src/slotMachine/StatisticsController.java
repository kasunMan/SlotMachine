package slotMachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class StatisticsController {
	
	private StatisticsGUI sgui;
	private SlotMachine sm;
	
	public  StatisticsController(/*StatisticsGUI sgui,*/ SlotMachine sm){
		//this.sgui =sgui;
		
		this.sm = sm;
	}
	
	public void runListener(){
		this.sgui =new StatisticsGUI();
		 display(sgui, sm);
		saveStatsBut(sgui.getF(), sgui.getSaveBut(),sgui.getTotalMatches(),sgui.getTotalWins(),sgui.getTotalLosses(),sgui.getAvgCreditsS());
		
	}
	public void saveStatsBut(JFrame f,JButton saveBut, String totalMatches,String totalWins,String totalLosses,String avgCreditsS){
		
		Date currentDate = new Date();
		/*
		 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		 * LocalDate localDate = LocalDate.now();
		 */
		// System.out.println(dtf.format(localDate));

		// Getting the Time
		// Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd");

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
	public void display(StatisticsGUI sgui, SlotMachine sm){
		double avgCredits;
		// Getting the matches, wins losses
		sgui.setTotalMatches("TOTAL MATCHES :" +  Integer.toString(sm.getMatches()));
		sgui.setTotalWins("WINS :" +Integer.toString(sm.getWins())) ;
		sgui.setTotalLosses( "LOSSES :" +Integer.toString(sm.getLosses())) ;
		// Average coins won
		
		try {
			
			avgCredits = (1.0*sm.getTotalWinnings()) /(1.0*sm.getMatches());
			

		} catch (ArithmeticException e) {
			
			avgCredits = 0.0;
		}
		if(sm.getMatches() ==0){
			avgCredits = 0.0;
		}
		System.out.println(avgCredits);

		sgui.setAvgCreditsS("AVERAGE CREDITS WON :" +Double.toString(avgCredits)) ;
		// TAKE INTO ACCOUNT FREE SPINS
		// Displaying the stats
		sgui.getMatchesArea().setText(sgui.getTotalMatches());
		sgui.getWinsArea().setText(sgui.getTotalWins());
		sgui.getLossesArea().setText(sgui.getTotalLosses());
		sgui.getAvgWinningsArea().setText("AVERAGE CREDITS WON :" + avgCredits);
		
	}

}
