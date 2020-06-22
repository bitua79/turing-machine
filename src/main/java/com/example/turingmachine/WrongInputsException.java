package com.example.turingmachine;

public class WrongInputsException extends Exception{
    public WrongInputsException() {
        super("Wrong Inputs! Try Again");
    }
}
