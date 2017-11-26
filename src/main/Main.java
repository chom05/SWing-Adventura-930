/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import GUI.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.event.EventHandler;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import uiText.TextoveRozhrani;
import javafx.scene.control.TextField;
import logika.Hra;
import logika.IHra;


/**
 *
 * @author chom05
 */

public class Main extends Application {

    private TextArea centralText;
    private IHra hra;

    private TextField zadejPrikazTextArea;

    private Label inventory = new Label();
    private Label outventory = new Label("Outventář");

    private Mapa mapa;
    private ImageView hrac;
    private MenuLista menuLista;
    private BatohSeznam batohSeznam;
    private ListVeciVProstoru listVeciVProstoru;
    private ListVychodu listVychodu;
    private Button prostorButton;
    private Button zahodButton;

    private Stage stage;


    @Override
    public void start(Stage primaryStage) {
        this.setStage(primaryStage);

        hra = new Hra();

        mapa = new Mapa(hra);
        hrac = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/postava.png"), 96, 123, false, true));
        menuLista = new MenuLista(hra, this);
        batohSeznam = new BatohSeznam(hra);
        listVeciVProstoru = new ListVeciVProstoru(hra, this);

        listVychodu = new ListVychodu(hra);
        prostorButton = new Button("Potvrdit");
        zahodButton = new Button("Zahodit");

        BorderPane borderPane = new BorderPane();
        BorderPane borderPaneInside = new BorderPane();
        BorderPane borderPaneRight = new BorderPane();
        borderPaneRight.setPadding(new Insets(0, 0, 0, 10));
        BorderPane borderPaneRightBottom = new BorderPane();
        borderPaneRightBottom.setPadding(new Insets(0, 0, 0, 10));
        //BorderPane buttonPane = new BorderPane();
        borderPane.setCenter(borderPaneInside);

        inventory.setText("Inventář");
        inventory.setFont(Font.font("Courier", FontWeight.BOLD, 12));

        // Text s prubehem hry
        centralText = new TextArea();
        centralText.setMinWidth(mapa.getImgWidth());
        centralText.setText(hra.vratUvitani());
        centralText.setFont(Font.font("Courier", 12));
        centralText.setEditable(false);
        borderPaneInside.setCenter(centralText);

        //initListProstoru();


        //label s textem zadej prikaz
        Label zadejPrikazLabel = new Label("Zadej prikaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // text area do ktere piseme prikazy
        zadejPrikazTextArea = new TextField("...");
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String vstupniPrikaz = zadejPrikazTextArea.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");

                zadejPrikazTextArea.setText("");

                if (hra.konecHry()) {
                    zadejPrikazTextArea.setEditable(false);
                    centralText.appendText(hra.vratEpilog());
                }
            }
        });

        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel,zadejPrikazTextArea);

        //buttonPane.setLeft(listVychodu);
        //buttonPane.setRight(prostorButton);

        zahodButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               zahodButton.getOnMouseClicked();
            }
        });

        borderPaneRight.setTop(listVeciVProstoru);
        borderPaneRight.setCenter(inventory);
        borderPaneRight.setCenter(borderPaneRightBottom);

        borderPaneRightBottom.setLeft(hrac);
        borderPaneRightBottom.setRight(batohSeznam);
        borderPaneRightBottom.setBottom(listVychodu);

        borderPaneInside.setTop(mapa);
        borderPane.setBottom(dolniLista);
        borderPane.setTop(menuLista);
        borderPane.setRight(borderPaneRight);
        //borderPane.setLeft(borderPaneRightBottom);

        Scene scene = new Scene(borderPane, 750, 900);
        primaryStage.setTitle("Adventura");

        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
    }

    /*
    private void initListProstoru() {
        prostorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String value = (String) listVychodu.getListView().get();
                String prikaz = "jdi " + value;
                String text = hra.zpracujPrikaz(prikaz);

                //Zpracovani textu na centralni panel
                centralText.appendText("\n\n" + prikaz + "\n");
                centralText.appendText("\n\n" + text + "\n");

                zadejPrikazTextArea.setText("");

            }
        });
    }
    */

    public TextArea getCentralText() {
        return centralText;
    }

    public Mapa getMapa() {
        return mapa;
    }

    /**
     * @return Stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void newGame(IHra novaHra){
        this.hra = novaHra;
        this.mapa.newGame(novaHra);
        this.centralText.setText(novaHra.vratUvitani());
        this.batohSeznam.newGame(novaHra);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else{
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else{
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }

}
