package com.example.turingmachine;

public class HaltException extends Exception{
    public HaltException() {
        super("String Not accepted!  ");
    }
}
