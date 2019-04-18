Assumptions:
For each machine there is one start state and as many accept states as you want. There could be zero accept states.
Everytime you reach an accept state, there is a print to standard output that says so.
This is controlled (to print or not) by a variable named 'notifyWhenAcceptStateReached' in StateMachine. Defualt value is false.

For assignement 1, it says: "The machine should indicate (e.g. print a warning to the standard output) the first time it receives 3 consecutive events of the same type."
I print a warning for each time that three valid events occured in a row.
An invalid event is an event that does not trigger a transition.

For Assignment 2, I use windows path to save to the workplace itself.

There are three examples that i provided:
1) task1() - an example of a simple machine that gets an event 3 times in a row and prints a warning to screen.
2) ABplus() - a machine that accepts (ab)+.
3) saveMachineExample() - demonstrates how to save and retrieve a saved machine.


Future Work:
- Create a machine factory class that implements the factory design pattern.
- Have a configutration file to hold all the configurations(notifyWhenAcceptStateReached, save path...)
- Have a constants class to hold all the string constants
- Create a transitions list that will hold all the transitions that occured in the correct order.
- Increase efficiency by having each state have a accept/start field instead of the state machine holding this information. This would only help in the case of printing when reaching an accepted state.