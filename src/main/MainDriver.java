package main;

import event.abstraction.Event;
import event.implementation.Event_1;
import event.implementation.Event_2;
import serializeService.SerializeService;
import state.abstraction.State;
import state.implementation.A;
import state.implementation.B;
import machine.abstraction.StateMachine;
import machine.implementation.EmptyMachine;
import machine.implementation.Task1_machine;

public class MainDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		task1();
		System.out.println("******************************************************");
		ABplus();
		System.out.println("******************************************************");
		saveMachineExample();
		
	}
	
	// a demonstration of saving a state machine to workspace and retrieving it.
	private static void saveMachineExample() {
		String machineName = "exampleMachine";
		StateMachine machine = new EmptyMachine();
		State state1 = new A("one");
		State state2 = new B("two");
		Event add = new Event_1("add");
		Event sub = new Event_1("sub");

		state1.addTransition(add, state2);
		state2.addTransition(sub, state1);
		
		machine.addNewState(state1);
		machine.addNewState(state2);
		machine.setStartState(state1);
		machine.transition(add);
		
		SerializeService serService = new SerializeService();
		serService.serialize(machineName, machine);
		machine = null;
		StateMachine savedMachine = serService.deserialize(machineName);
		savedMachine.transition(sub);
	}
	
	// A state machine that demonstrates a finite automata that accepts (ab)+
	private static void ABplus() {
		StateMachine ABplus = new EmptyMachine();
		State a = new A("a");
		State b = new B("b");
		Event moveToB = new Event_1("aToB");
		Event moveToA = new Event_1("bToA");
		
		a.addTransition(moveToB, b);
		b.addTransition(moveToA, a);
		ABplus.addNewState(a);
		ABplus.addNewState(b);
		
		ABplus.addAcceptState(b);
		ABplus.setStartState(a);
		
		ABplus.transition(moveToB);
		ABplus.transition(moveToA);
		ABplus.transition(moveToB);
		ABplus.transition(moveToB);
	}

	/* 
	 * An example of a state machine that notifies via standard output every time
	 * a legal event has been invoked three times in a row
	 */
	private static void task1() {
		StateMachine stateMachine = new Task1_machine();
		State a1 = new A("a");
		State b1 = new A("b");
		State c1 = new A("c");

		Event e1 = new Event_1("move");
		Event e2 = new Event_2("dont_move");

		a1.addTransition(e1, b1);
		b1.addTransition(e1, c1);
		c1.addTransition(e1, a1);
		c1.addTransition(e2, c1);

		stateMachine.addNewState(a1);
		stateMachine.addNewState(b1);
		stateMachine.addNewState(c1);
		
		stateMachine.setStartState(a1);
		stateMachine.addAcceptState(c1);
		System.out.println("start");
		System.out.println("Start State : " + stateMachine.getStartState().getName());
		
		stateMachine.transition(e1);
		stateMachine.transition(e1);
		stateMachine.transition(e2);
		stateMachine.transition(e2);
		stateMachine.transition(e2);
		stateMachine.transition(e1);
	}

}
