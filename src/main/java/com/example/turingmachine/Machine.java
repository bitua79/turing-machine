package com.example.turingmachine;

import java.util.ArrayList;
import java.util.Scanner;

public class Machine {
    ArrayList<String> statesArr = new ArrayList<>();
    ArrayList<String> alphabetArr = new ArrayList<>();
    ArrayList<String> tapeAlphabetArr = new ArrayList<>();
    TransitionFunctions transitionFunctions = new TransitionFunctions();


    public static ArrayList<String> finalStatesArr = new ArrayList<>();

    public Machine(String states, String alphabet, String tapeAlphabet, String finalStates, ArrayList<String> transitionFun, String input) {
        //handle input strings and put them in lists
        setStates(deleteSpaces(states));
        setAlpha(deleteSpaces(alphabet));
        setTapeAlpha(deleteSpaces(tapeAlphabet));
        setFinalStates(deleteSpaces(finalStates));

        //check inputs matching
        //throw error if there is something wrong
        try {

            Transitions(transitionFun);
            checkInput(input);
            generalCheck();
        } catch (WrongInputsException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    //convert inputs to machine///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setStates(String states) {
        set(statesArr, states);
    }

    public void setAlpha(String alphabet) {
        set(alphabetArr, alphabet);
    }

    public void setTapeAlpha(String tapeAlphabet) {
        set(tapeAlphabetArr, tapeAlphabet);
    }

    public void setFinalStates(String finalStates) {
        set(finalStatesArr, finalStates);
    }

    //convert string to array
    public void set(ArrayList<String> arr, String s) {
        s = s.replaceAll(",", " ");
        s = s.substring(3, s.length() - 1);
        Scanner scanner = new Scanner(s);
        while (scanner.hasNext()) {
            arr.add(scanner.next());
        }
    }

    //add transitions functions to list and check every function matching
    public void Transitions(ArrayList<String> transitions) throws WrongInputsException {
        for (String si : transitions) {
            si = deleteSpaces(si);
            String q = si.substring(2, 4);
            char in = si.charAt(5);
            String q_ = si.substring(9, 11);
            char out = si.charAt(12);
            char direction = si.charAt(14);

            checkSituation(q, q_, in, out, direction);
            transitionFunctions.add(new Transition(q, in, out, q_, direction));
        }
    }

    public String deleteSpaces(String s) {
        return s.replaceAll(" ", "");
    }

    //check inputs //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //check general factors
    public void generalCheck() throws WrongInputsException {
        if (finalStatesArr.isEmpty() || statesArr.isEmpty() ||
                !tapeAlphabetArr.contains("⊡") || alphabetArr.isEmpty()) {
            throw new WrongInputsException();
        }

        //final states are available or not
        for (String s : finalStatesArr) {
            if (!statesArr.contains(s)) throw new WrongInputsException();
        }
    }

    //check every transition matching
    public void checkSituation(String q, String q_, char in, char out, char direction) throws WrongInputsException {
        checkState(q);
        checkState(q_);
        checkTapeAlpha(String.valueOf(in));
        checkTapeAlpha(String.valueOf(out));
        checkDirection(direction);
    }

    //if state is available or not
    public void checkState(String state) throws WrongInputsException {
        if (!statesArr.contains(state)) throw new WrongInputsException();

    }

    //if alphabet character is available or not
    public void checkStrAlpha(String in) throws WrongInputsException {
        if (!alphabetArr.contains(in) && !in.equals("⊡")) throw new WrongInputsException();
    }

    //if tape character is available in Γ or not
    public void checkTapeAlpha(String out) throws WrongInputsException {
        if (!tapeAlphabetArr.contains(out)) throw new WrongInputsException();
    }

    //if its just 'L' or 'R'
    public void checkDirection(char direction) throws WrongInputsException {
        if (direction != 'L' && direction != 'R') throw new WrongInputsException();
    }

    //if input characters matches with alphabet or not
    public void checkInput(String input) throws WrongInputsException {
        for (int i = 0; i < input.length(); i++) {
            if (!alphabetArr.contains(input.substring(i, i + 1))) throw new WrongInputsException();
        }
    }

    public static boolean isFinalState(String q) {
        return finalStatesArr.contains(q);
    }


    //process the input
    public void run(String input) throws HaltException {

        Tape tape = new Tape(input);
        transitionFunctions.essay(tape, "q0");

    }
}

