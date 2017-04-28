package slotMachine;

public class SlotMachineGame {

	public static void main(String[] args) {

		SlotMachine sm = new SlotMachine();
		SlotController slotContol = new SlotController(sm);
		//StatisticsController statsControl = new StatisticsController(sm);
		slotContol.runListener();
		//statsControl.runListener();
		

	}

}
