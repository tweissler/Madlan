package machine.abstraction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import event.abstraction.Event;
import state.abstraction.State;

public abstract class StateMachine implements Serializable  {
	private State currentState;
	private State startState;
	private List<State> acceptStates;
	private boolean notifyWhenAcceptStateReached = false;
	
	private List<State> allStates;

	public StateMachine() {
		allStates = new ArrayList<State>();
		acceptStates = new ArrayList<State>();
	}
		
	public boolean transition(Event event) {
		if(currentState == null)
			return false;
		
		State transferState = currentState.getTransitions().get(event);
		return applyTransition(transferState, event);
	}
		
	private boolean applyTransition(State transferState, Event event) {
		if(transferState == null || !allStates.contains(transferState)) {
			System.out.println("The transition " + event.getName() + " is not possible");
			return false;
		}
		System.out.println("Event " + event.getName() + " from : " + this.currentState.getName() + " to " + transferState.getName());
		setCurrentState(transferState);
		if(notifyWhenAcceptStateReached && acceptStates.contains(transferState))
			System.out.println("Accept State Reached");
		return true;
	}

	public boolean addNewState(State newState) {
		if(allStates.contains(newState)) {
			System.out.println("State " + newState.getName() + " already exists");
			return false;
		} 
		allStates.add(newState);
		return true;
	}
	
	public boolean removeState(State stateToRemove) {
		if(!allStates.contains(stateToRemove)) {
			System.out.println("State doesn't exist");
			return false;
		} else if(startState.equals(stateToRemove)) {
			System.out.println("You cannot delete the start state without setting a new start state");
			return false;
		} else if(currentState.equals(stateToRemove)) {
			System.out.println("You cannot delete the current state without setting a new current state");
			return false;
		} else {
			removeStateFromTransitions(stateToRemove);
			System.out.println(stateToRemove.getName() + " has been deleted" );
			allStates.remove(stateToRemove);
			return true;
		}
	}
	
	private void removeStateFromTransitions(State stateToRemove) {
		for (State state : allStates) {
			if(state != stateToRemove)
				state.removeStateFromTransitions(stateToRemove);
		}		
	}

	public List<State> getAllStates() {
		return allStates;
	}

	public void setAllStates(List<State> allStates) {
		this.allStates = allStates;
	}

	public State getCurrentState() {
		return currentState;
	}
	
	private void setCurrentState(State currentState) {
		if(allStates.contains(currentState))
			this.currentState = currentState;
		else
			System.out.println("You must add the state to the machine and after that set current state");
	}

	public State getStartState() {
		return startState;
	}

	public void setStartState(State startState) {
		if(allStates.contains(startState)) {
			this.startState = startState;
			setCurrentState(startState);
		} else {
			System.out.println("state " + startState.getName() + " is not in the system");
		}
	}

	public List<State> getAcceptStates() {
		return acceptStates;
	}

	public void addAcceptState(State acceptState) {
		if(allStates.contains(acceptState)) {
			this.acceptStates.add(acceptState);
		} else {
			System.out.println("state " + acceptState.getName() + " is not in the system");
		}
	}
}
