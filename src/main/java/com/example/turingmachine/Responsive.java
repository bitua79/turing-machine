package com.example.turingmachine;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public interface Responsive {
    public static void ResponsivePane(Stage stage, AnchorPane anchorPane, double height_percent, double width_percent){
        anchorPane.prefHeightProperty().bind(stage.heightProperty().multiply(height_percent).divide(100.0));
        anchorPane.prefWidthProperty().bind(stage.widthProperty().multiply(width_percent).divide(100.0));
    }

    public static void ResponsivePane(Stage stage, AnchorPane anchorPane, double height_percent, double width_percent, double y_percent, double x_percent){
        anchorPane.prefHeightProperty().bind(stage.heightProperty().multiply(height_percent).divide(100.0));
        anchorPane.prefWidthProperty().bind(stage.widthProperty().multiply(width_percent).divide(100.0));

        ResponsivePos(stage, anchorPane, y_percent, x_percent);
    }

    public static void ResponsivePane(Stage stage, VBox vbox, double height_percent, double width_percent, double y_percent, double x_percent){
        vbox.prefHeightProperty().bind(stage.heightProperty().multiply(height_percent).divide(100.0));
        vbox.prefWidthProperty().bind(stage.widthProperty().multiply(width_percent).divide(100.0));
        ResponsivePos(stage, vbox, y_percent, x_percent);
    }

    public static void ResponsivePane(Stage stage, HBox hBox, double height_percent, double width_percent, double y_percent, double x_percent){
        hBox.prefHeightProperty().bind(stage.heightProperty().multiply(height_percent).divide(100.0));
        hBox.prefWidthProperty().bind(stage.widthProperty().multiply(width_percent).divide(100.0));
        ResponsivePos(stage, hBox, y_percent, x_percent);
    }

    public static void ResponsivePane(Stage stage, TabPane tabPane, double height_percent, double width_percent, double y_percent, double x_percent, double maxTabW, double maxTabH){
        tabPane.prefHeightProperty().bind(stage.heightProperty().multiply(height_percent).divide(100.0));
        tabPane.prefWidthProperty().bind(stage.widthProperty().multiply(width_percent).divide(100.0));

        tabPane.setTabMaxHeight(maxTabH);
        stage.heightProperty().addListener((observable, oldValue, newValue) -> {

            for (int i=0; i<tabPane.getTabs().size(); i++){
                tabPane.getTabs().get(i).setStyle("-fx-font: "+fontSizeTab(newValue.doubleValue())+"px Gabriola ;");
            }
            tabPane.setTabMaxWidth(stage.widthProperty().multiply(maxTabW).divide(100.0).doubleValue());

        });

        ResponsivePos(stage, tabPane, y_percent, x_percent);
    }

    public static void ResponsivePane(Stage stage, ScrollPane scrollPane, double height_percent, double width_percent, double y_percent, double x_percent){
        scrollPane.prefHeightProperty().bind(stage.heightProperty().multiply(height_percent).divide(100.0));
        scrollPane.prefWidthProperty().bind(stage.widthProperty().multiply(width_percent).divide(100.0));
        ResponsivePos(stage, scrollPane, y_percent, x_percent);
    }

    public static void ResponsiveShape(Stage stage, Rectangle rectangle, double percent){
        rectangle.heightProperty().bind(stage.heightProperty().multiply(percent).divide(100.0));
        rectangle.widthProperty().bind(stage.heightProperty().multiply(percent).divide(100.0));
    }

    public static void ResponsiveShape(Stage stage, Rectangle rectangle, double height_percent, double width_percent, double y_percent, double x_percent){
        rectangle.heightProperty().bind(stage.heightProperty().multiply(height_percent).divide(100.0));
        rectangle.widthProperty().bind(stage.heightProperty().multiply(width_percent).divide(100.0));
        ResponsivePos(stage, rectangle, y_percent, x_percent);
    }

    public static void ResponsivePRB(Stage stage, ProgressBar progressBar, double width_sub, double y_percent, double y_sub){
        progressBar.prefWidthProperty().bind(stage.widthProperty().subtract(width_sub));
        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            progressBar.setLayoutY((stage.getHeight() * y_percent) - y_sub);
        });

    }

    public static void ResponsiveIMG(Stage stage, ImageView imageView, double height_percent, double y_percent, double x_percent){
        imageView.fitHeightProperty().bind(stage.heightProperty().multiply(height_percent).divide(100.0));

        ResponsivePos(stage, imageView, y_percent, x_percent);
    }

    public static void ResponsiveIMG(Stage stage, ImageView imageView, double height_percent, double width_percent, double y_percent, double x_percent){
        imageView.fitHeightProperty().bind(stage.heightProperty().multiply(height_percent).divide(100.0));
        imageView.fitWidthProperty().bind(stage.widthProperty().multiply(width_percent).divide(100.0));

        ResponsivePos(stage, imageView, y_percent, x_percent);
    }

    public static void ResponsiveTXF(Stage stage, TextField textField, double height_percent, double width_percent, double y_percent, double x_percent, int init){
        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            textField.setFont(Font.font("Gabriola", fontSize(init, newValue.doubleValue())));
            textField.setPrefWidth(stage.getWidth() * width_percent / 100.00);
            textField.setPrefHeight(stage.getHeight() * height_percent / 100.00);
        });
        ResponsivePos(stage, textField, y_percent, x_percent);
    }

    public static void ResponsiveTXF(Stage stage, TextArea textArea, double height_percent, double width_percent, double y_percent, double x_percent, int init){
        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            textArea.setFont(Font.font("Gabriola", fontSize(init, newValue.doubleValue())));
            textArea.setPrefWidth(stage.getWidth() * width_percent / 100.00);
            textArea.setPrefHeight(stage.getHeight() * height_percent / 100.00);
        });
        ResponsivePos(stage, textArea, y_percent, x_percent);
    }

    public static void ResponsiveLBL(Stage stage, Label label, double height_percent, double width_percent, double y_percent, double x_percent, int init){
        Main.stage.heightProperty().addListener((observable, oldValue, newValue) -> {

            label.setFont(Font.font("Uechi-Gothic", fontSize(init, newValue.doubleValue())));
            label.setPrefWidth(Main.stage.getWidth()* width_percent / 100.00);
            label.setPrefHeight(Main.stage.getWidth()* height_percent / 100.00);
        });
        ResponsivePos(stage, label, y_percent, x_percent);
    }

    public static void ResponsiveBtn(Stage stage, Button button, double height_percent, double width_percent, double y_percent, double x_percent, int init){
        boolean disable = button.isDisable();
        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            button.setStyle("-fx-font: "+fontSizeBtn(init, newValue.doubleValue())+"px Uechi-Gothic;");
            button.setPrefWidth(stage.getWidth() * width_percent / 100.00 );
            button.setPrefHeight(stage.getHeight() * height_percent / 100.00 );

        });
        ResponsivePos(stage, button, y_percent, x_percent);
        button.setDisable(disable);
    }


    public static void ResponsivePos(Stage stage, Node node, double y_percent, double x_percent){
        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            node.setLayoutX(stage.getWidth() * x_percent / 100.00);
        });
        stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            node.setLayoutY(stage.getHeight() * y_percent / 100.00);
        });
    }

    public static int fontSize(int init, double value){
        int reducer = init/5 - 1;
        return init - (reducer - (int) value /(1000/reducer)) * 5;
    }

    public static int fontSizeBtn(int init, double value){
        int result = init - (10 - (int) value /100) * 6 + 3;
        if (result <= 3)return 1;
        return result;
    }

    public static int fontSizeTab(double value){
        return  24 - (5-((int) value /200)) * 4;
    }
}
