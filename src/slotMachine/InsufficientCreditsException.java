package slotMachine;

public class InsufficientCreditsException extends RuntimeException{
	
	public InsufficientCreditsException(){
	super("Insufficient Credits");
	}

}

