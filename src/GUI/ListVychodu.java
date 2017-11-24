package GUI;

import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logika.IHra;
import logika.Prostor;
/**
 * Created by MajkCajk on 19.11.17.
 */
public class ListVychodu extends ListView implements Observer {

    private IHra hra;
    private final ObservableList<String> options = FXCollections.observableArrayList();
    String value = " ";

    /**
     * Konstruktor, který zavoláním metody init, zaregistruje pozorovatele a
     * nadefinuje podobu ComboBoxu.
     *
     * @param hra
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
    public ListView getListView() {
        return this;
    }

    public void init() {
        this.setItems(options);
        //this.setEditable(true);
        //ListView<Prostor> listVychodu = new ListView<Prostor>(options);
        this.setOrientation(Orientation.HORIZONTAL);
        this.setPrefHeight(50);
        this.setCellFactory(param -> new ListCell<Prostor>() {
            @Override
            protected void updateItem(Prostor item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNazev() == null) {
                    setText(null);
                } else {
                    setText(item.getNazev());
                }
                this.setOnMousePressed(event -> {
                    //hra.getHerniPlan().setAktualniProstor(item);
                    hra.zpracujPrikaz("jdi "+item.getNazev());
                    //hra.zpracujPrikaz();
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
