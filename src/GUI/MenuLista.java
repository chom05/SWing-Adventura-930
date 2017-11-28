package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;
/**
 * Třída vytvářející panel s horní lištou
 *
 * @author Michal Chobola - chom05
 */
public class MenuLista extends MenuBar{

    private IHra hra;
    private Main main;
    /**
     * Konstruktor třídy
     *
     * @param hra
     */
    public MenuLista(IHra hra, Main main){
        this.hra = hra;
        this.main = main;
        init();
    }
    /**
     * Inicializace třídy
     *
     */
    private void init(){

        Menu novySoubor = new Menu("Adventura");
        Menu napoveda = new Menu("Help");

        MenuItem novaHra = new MenuItem("Nova hra");

        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        MenuItem konecHry = new MenuItem("Konec hry");

        novySoubor.getItems().addAll(novaHra, konecHry);

        MenuItem oProgramu = new MenuItem("O programu");
        MenuItem napovedaItem = new MenuItem("Napoveda");

        napoveda.getItems().addAll(oProgramu, napovedaItem);

        this.getMenus().addAll(novySoubor, napoveda);

        konecHry.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        novaHra.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hra = new Hra();
                main.newGame(hra);
            }
        });

        oProgramu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.setTitle("O Programu");

                WebView webView = new WebView();

                webView.getEngine().load(Main.class.getResource("/zdroje/zadani/zadani.html").toExternalForm());

                stage.setScene(new Scene(webView, 700,500));
                stage.show();
            }
        });

        napovedaItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Stage stage = new Stage();
                stage.setTitle("Napoveda");

                WebView webView = new WebView();

                webView.getEngine().load(Main.class.getResource("/zdroje/napoveda/napoveda.html").toExternalForm());

                stage.setScene(new Scene(webView, 500,500));
                stage.show();

            }
        });


    }

}
