package GUI;

import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logika.IHra;
import logika.Prostor;
/**
 * Created by MajkCajk on 19.11.17.
 */
public class ListVychodu extends AnchorPane implements Observer {

    private IHra hra;
    private final ObservableList<String> options = FXCollections.observableArrayList();

    /**
     * Konstruktor, který zavoláním metody init, zaregistruje pozorovatele a
     * nadefinuje podobu ComboBoxu.
     *
     */
    public ListVychodu(IHra hra) {
        this.hra = hra;
        init();
    }

    /**
     * Metoda vrací ComboBox a je použita ve třídě Main k zpřístupnění
     * ComboBoxu.
     *
     * @return Vrací Combobox.
     */
    public AnchorPane getListView() {
        return this;
    }

    public void init() {
        ListView<String> listVychodu = new ListView<>(options);
        listVychodu.setOrientation(Orientation.VERTICAL);
        listVychodu.setPrefHeight(200);
        listVychodu.setMinWidth(213);
        listVychodu.setMaxWidth(213);
        listVychodu.setCellFactory(param -> new ListCell<String>() {

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                this.setText(item);
                this.setOnMousePressed(event -> {
                    hra.zpracujPrikaz("jdi "+item);
                });
            }
        });
        this.getChildren().addAll(listVychodu);
        hra.getHerniPlan().registerObserver(this);
        update();

    }

    /**
     * Metoda aktualizuje seznam při přechodu z místnosti do místnosti.
     */
    @Override
    public void update() {
        options.clear();
        for (Prostor prostor : hra.getHerniPlan().getAktualniProstor().getOdemceneVychody()) {
            options.add(prostor.getNazev());
        }
    }

    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     *
     * @param novaHra
     */
    public void newGame(IHra novaHra){
        hra.getHerniPlan().removeObserver(this);
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }

}
