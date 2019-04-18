package event.abstraction;

import java.io.Serializable;

public abstract class Event implements Serializable {
	private String name;
	
	public Event(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object event) {
		if(event == null || !(event instanceof Event)) {
			return false;
		}
		return ((Event) event).getName().equals(this.name);
	}
	
	@Override
    public int hashCode() {
		int result = 17;
        result = 31 * result + this.name.hashCode();
		return result;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
