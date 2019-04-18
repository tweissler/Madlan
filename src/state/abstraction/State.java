package state.abstraction;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import event.abstraction.Event;

public abstract class State implements Serializable {
	private String name;
	private Map<Event, State> transitions = new HashMap<Event, State>();
	
	public State(String name) {
		this.name = name;
	}
	
	public Map<Event, State> getTransitions() {
		return transitions;
	}

	public void setTransitions(Map<Event, State> transitions) {
		this.transitions = transitions;
	}

	public boolean addTransition(Event event, State transferState) {
		if(transitions.get(event) == null) {
			transitions.put(event, transferState);
			return true;
		} else {
			System.out.println("Event " + event.getName() + " from " + this.name + " already exists");
			return false;
		}
	}
	
	public boolean removeTransition(Event event, State removeState) {
		if(transitions.remove(event) != null) {
			return true;
		} else {
			System.out.println("Event " + event.getName() + " from " + this.name + " does not exist");
			return false;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void removeStateFromTransitions(State stateToRemove) {
		Iterator<Entry<Event, State>> it = transitions.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<Event, State> entry = it.next();
			if(entry.getValue().equals(stateToRemove)) {
				System.out.println("removed " + entry.getKey().getName() + " + " + entry.getValue().getName() + " from " + this.getName());
				it.remove();	
			}
	    }
	}
	
	@Override
	public boolean equals(Object state) {
		if(state == null || !(state instanceof State)) {
			return false;
		}
		return ((State) state).getName().equals(this.name);
	}
	
	@Override
    public int hashCode() {
		int result = 17;
        result = 31 * result + this.name.hashCode();
		return result;
	}
}