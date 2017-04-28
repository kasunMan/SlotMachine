package slotMachine;

import java.util.ArrayList;
import java.util.List;

public class SlotMachine {

	private List<Reel> reels = new ArrayList<Reel>();
	private int credits;
	private int bet;
	private int winnings;
	private int matches;
	private int wins;
	private int losses;
	private String errorMsg;
	private String results;
	private boolean freeSpin = false;
	private boolean isSpinning = false;
	private int totalWinnings;

	public SlotMachine() {
		this.credits = 10;
		setReels();

	}

	public int getTotalWinnings() {
		return totalWinnings;
	}

	public void setTotalWinnings(int totalWinnings) {
		this.totalWinnings = totalWinnings;
	}

	public boolean isSpinning() {
		return isSpinning;
	}

	public void setSpinning(boolean isSpinning) {
		this.isSpinning = isSpinning;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public boolean isFreeSpin() {
		return freeSpin;
	}

	public void setFreeSpin(boolean freeSpin) {
		this.freeSpin = freeSpin;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List<Reel> getReels() {
		return reels;
	}

	public void setReels(List<Reel> reels) {
		this.reels = reels;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public int getWinnings() {
		return winnings;
	}

	public void setWinnings(int winnings) {
		this.winnings = winnings;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public void addCoin() {
		this.credits++;
	}

	public void betOne() throws InsufficientCreditsException{
		if (this.credits > 0) {
			this.bet++;
			this.credits--;
		} else {
			this.errorMsg = "Insufficient Credits";
			throw new InsufficientCreditsException();
		}
	}

	public void betMax() throws InsufficientCreditsException{
		if (this.credits > 2) {
			this.bet += 3;
			this.credits -= 3;
		} else {
			this.errorMsg = "Insufficient Credits";
			throw new InsufficientCreditsException();
		}
	}

	public void reset() {
		this.credits += bet;
		this.bet = 0;
	}

	/*
	 * public Symbol[] spin(){ System.out.println("Spinnging"); Symbol[] symbols
	 * = new Symbol[3];
	 * 
	 * symbols[0]=reels.get(0).spin().get(0);
	 * symbols[1]=reels.get(1).spin().get(0);
	 * symbols[2]=reels.get(2).spin().get(0);
	 * 
	 * return symbols; } public void stats(){
	 * 
	 * }
	 */
	public void compare() {
		{
			// Increment matches if it is not a free spin
			if (!this.freeSpin) {
				this.matches++;
			}
			this.freeSpin = false;
			// Check if all three are the same
			if (reels.get(0).getLastVal() == reels.get(1).getLastVal()
					&& reels.get(1).getLastVal() == reels.get(2).getLastVal()) {
				winnings = reels.get(0).getLastVal() * this.bet;
				this.credits += winnings;
				this.bet = 0;
				this.wins++;
				this.totalWinnings += winnings;
				this.results = "***!!! YOU WIN " + winnings + " !!!***";

			}
			// Check if two are the same and increment matches
			else if (reels.get(0).getLastVal() == reels.get(1).getLastVal()
					|| reels.get(0).getLastVal() == reels.get(2).getLastVal()
					|| reels.get(1).getLastVal() == reels.get(2).getLastVal()) {
				this.freeSpin = true;
				this.results = "***!!! FREE SPIN !!!***";
			}
			// If none are the same
			else {
				this.losses++;
				this.totalWinnings -= this.bet;
				this.bet = 0;
				this.results = "YOU LOSE";
			}
		}
	}

	// Setting the three reels
	public void setReels() {
		reels.add(0, new Reel());
		reels.add(1, new Reel());
		reels.add(2, new Reel());
	}
	/*
	 * public void createNewReels(){ setReels(); }
	 */

}
