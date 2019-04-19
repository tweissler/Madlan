## Run instructions

### Creating a new state machine

 - Create a new class that extends `StateMachine`
 - Initialize your machine with `new`
 - Each machine needs to have its starting state initialized using `setStartState(<State>)`
 - States can be added using `addAcceptState(<State>)`

```
Class myMachine extends StateMachine {
...
}

StateMachine A = new myMachine();
State b = new myState("b");
A.setStartState(b);
A.setAcceptState(b);
```

### Adding a new state

Do the same as above for states and events, extending State and Event accordingly.
Each State and Event have to have a name. Two states can't have the same name.
After creating your states, you must add them to the machine.

```
State b = new myState("b");
A.addNewState(b);
```

### Adding transition

In order to create a transition from one state to another based on an Event, use `addTransition`. 

```
State b = new myState("b");
State c = new myState("c");
Event ev_1 = new myEvent("ev_1");
b.addTransition(ev_1, c);
```

This means that if you are currently on state b and you recieve Event ev_1 you transition to state c.


## Assumptions

 - For each machine there is one start state and as many accept states as you want. There could be zero accept states.
Everytime you reach an accept state, there is a print to standard output that says so.
This is controlled (to print or not) by a variable named 'notifyWhenAcceptStateReached' in StateMachine. Defualt value is false.

 - For assignement 1, it says: "The machine should indicate (e.g. print a warning to the standard output) the first time it receives 3 consecutive events of the same type."
I print a warning for each time that three valid events occured in a row.
An invalid event is an event that does not trigger a transition.

 - For Assignment 2, I use windows path to save to the workplace itself.

## Provided examples

- `task1()` - a simple machine that gets an event 3 times in a row and prints a warning to screen.
- `ABplus()` - a machine that accepts `(ab)+`.
- `saveMachineExample()` - demonstrates how to save and retrieve a saved machine.


## Future Work

- Create a machine factory class that implements the factory design pattern.
- Have a configutration file to hold all the configurations (`notifyWhenAcceptStateReached`, save path, etc.)
- Have a constants class to hold all the string constants
- Create a transitions list that will hold all the transitions that occured in the correct order.
- Increase efficiency by having each state have a accept/start field instead of the state machine holding this information. This would only help in the case of printing when reaching an accepted state.
