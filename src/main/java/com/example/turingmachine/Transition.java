package com.example.turingmachine;

public class Transition {
    String q;
    char input;
    char output;
    String q_;
    char direction;

    public Transition(String q, char input, char output, String q_, char direction) {
        this.q = q;
        this.input = input;
        this.output = output;
        this.q_ = q_;
        this.direction = direction;
    }

    public boolean matches(String q, char input){
        return q.equals(this.q) && input== this.input;
    }

    @Override
    public String toString() {
        return "Move{" +
                " output=" + output +
                ", q_='" + q_ + '\'' +
                ", direction=" + direction +
                '}';
    }
}
