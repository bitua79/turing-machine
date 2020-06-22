package com.example.turingmachine;

import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class TransitionFunctions {
    private ArrayList<Transition> transitions;
    public static ArrayList<String> ResultTransitions;
    public static ArrayList<String> ResultTapeSituations;
    public static ArrayList<HBox> tapeHBoxes;

    public TransitionFunctions() {
        this.transitions = new ArrayList<>();
        ResultTransitions = new ArrayList<>();
        ResultTapeSituations = new ArrayList<>();
        tapeHBoxes = new ArrayList<>();
    }

    //return the object of our move
    public Transition get(String q, char input){
        for (Transition transition : transitions) {
            if (transition.matches(q, input)) {
                return transition;
            }
        }
        return null;
    }

    //add Instantaneous Description at the beginning
    public void essay(Tape tape, String q) throws HaltException {
        String insDec = tape.InstantaneousDescription(q);
        String tapeS = tape.print();
        System.out.println("\n \033[1;91m Process started...\033[0m\n\n");
        printSituationAndAdd(tapeS, insDec);

        try {
            goTo(tape, q);
        } catch (InfiniteLoop infiniteLoop) {
            infiniteLoop.printStackTrace();
            System.exit(3);
        }
    }

    //recursively move in turing until halt
    private String lastState;
    private int counter=1;
    public void goTo(Tape tape, String q) throws HaltException, InfiniteLoop {
        //identify the character pointer points to
        //and identify what state machine should go in this situation
        char input = tape.getTapePoint();
        Transition i = get(q,input);

        counter++;

        if (i == null){
            if (Machine.isFinalState(lastState)){
                printAllResults();
                System.out.println("\nString Accepted!");
            }
            else throw new HaltException();
        }
        else if (counter>1000){

            throw new InfiniteLoop();
        }
        else {

            //go toward or backward in tape and change pointer character
            tape.setTapePointer(i.output, i.direction);

            //save tape position and Instantaneous Description in every move

            String insDec = tape.InstantaneousDescription(i.q_);
            String tapeS = tape.print();
            printSituationAndAdd(tapeS, insDec);

            lastState = i.q_;

            //go to new situation and process remain of input string
            goTo(tape, i.q_);

        }
    }

    public void add(Transition transition){
        transitions.add(transition);
    }

    public void printSituationAndAdd(String tapes, String IdesS){
        System.out.println("tape is                    : "+tapes+"InstantaneousDescription is: "+IdesS);
        System.out.println();
        System.out.println();

        ResultTransitions.add(IdesS);
        ResultTapeSituations.add(tapes);
    }
    public void printAllResults(){
        Main.openFxml("Show.fxml");

        System.out.println();
        System.out.println();

        for (int i = 0; i< ResultTransitions.size(); i++){
            System.out.print(ResultTransitions.get(i));
            if (i != ResultTransitions.size() -1) System.out.print("\033[0;31m   ⊢  \033[0m");
        }
    }
}
