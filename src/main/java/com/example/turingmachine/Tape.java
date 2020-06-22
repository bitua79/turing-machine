package com.example.turingmachine;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Tape {
    String input;

    int ak;
    int startBound;
    int endBound;


    //ak is tape pointer and a is input string
    public Tape(String input) {
        this.input = input;
        this.ak = 0;
        this.startBound = 0;
        this.endBound = input.length();
    }

    //out of bounds => ⊡
    public char getTapePoint(){
        if (ak >= input.length() || ak <0)return '⊡';
        return input.charAt(ak);
    }

    //change character pointer points and move in tape
    public void setTapePointer(char out, char direction){
        if (ak >= 0 && ak < input.length()){
            input = input.substring(0, ak)+ out + input.substring(ak+1);
        }

        if (direction == 'L')ak--;
        if (direction =='R') ak++;

        //out of bounds => add ⊡ to input
        if (ak < 0 ){
            input = "⊡"+ input;
            startBound++;endBound++;

        }else if (ak >= input.length()){
            input = input +"⊡";
        }
    }

    //construct InstantaneousDescription with present state and tape situation
    public String InstantaneousDescription(String q){

        //if we are in bounds, return tape in its bound
        //and if not return from first until pointer
        if (ak<endBound-1)
            return (input.substring(startBound, ak)+q+" "+ input.substring(ak, endBound));
        else
            return (input.substring(startBound, ak)+q+" "+ input.charAt(ak));
    }

    //show result
    public String print(){
        HBox hBox = new HBox();
        Text t;
        String s = "";
        int size = 300/input.length();

        //print tape situation and add it to fx
        for (int i=0; i<input.length(); i++){

            Rectangle r = new Rectangle(size, size);

            //not pointer
            t = new Text();
            t.setFont(Font.font(20));
            t.setFill(Paint.valueOf("#d8adc0"));
            t.setText(String.valueOf(input.charAt(i)));
            r.setFill(Paint.valueOf("#5e5e96"));

            //is pointer
            if (i==ak){
                s+= "\033[0;36m";
                t.setFill(Paint.valueOf("#5e5e96"));
                s+= t.getText()+"\033[0m";
                r.setFill(Paint.valueOf("#d8adc0"));
            }
            else {
                s+= t.getText()+"\033[0m";
            }

            Main.InnerShadow(r);
            r.hoverProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    r.setTranslateY(-3);
                }
                else {
                    r.setTranslateY(3);
                }
            });
            hBox.getChildren().add(new StackPane(r, t));

        }
        TransitionFunctions.tapeHBoxes.add(hBox);
        s += "\n\n";

        return s;
    }
}
