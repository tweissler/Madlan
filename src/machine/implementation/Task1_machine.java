package machine.implementation;

import event.abstraction.Event;
import machine.abstraction.StateMachine;

public class Task1_machine extends StateMachine {

	private int sameEventCounter = 0;
	private Event lastEvent = null;
	
	public Task1_machine() {
		super();
	}
	
	@Override
	public boolean transition(Event event) {
		boolean parentAnswer = super.transition(event);
		if(parentAnswer) {
			updateSameEventCount(event);
			lastEvent = event;
		} else {
			lastEvent = null;
			sameEventCounter = 0;
		}
		return parentAnswer;
	}

	private void updateSameEventCount(Event event) {
		if(lastEvent != null && lastEvent.equals(event)) {
			sameEventCounter++;
			if(sameEventCounter == 3) {
				sameEventCounter = 0;
				lastEvent = null;
				System.out.println("Event " + event.getName() + " has been activated 3 times in a row");
			}
		} else {
			sameEventCounter = 1;
		}
	}
}
