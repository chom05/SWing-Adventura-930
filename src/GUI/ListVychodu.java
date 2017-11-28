package GUI;

import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import main.Main;
import utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logika.IHra;
import logika.Prostor;
/**
 * Třída vytvářející panel Vychodu
 *
 * @author Michal Chobola - chom05
 */
public class ListVychodu extends AnchorPane implements Observer {

    private IHra hra;
    private Main main;
    private final ObservableList<Prostor> options = FXCollections.observableArrayList();

    /**
     * Konstruktor, který zavoláním metody init, zaregistruje pozorovatele a
     * nadefinuje podobu ComboBoxu.
     *
     */
    public ListVychodu(IHra hra, Main main) {
        this.hra = hra;
        this.main = main;
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
    /**
     * Inicializace třídy
     *
     */
    public void init() {
        ListView<Prostor> listVychodu = new ListView<>(options);
        listVychodu.setOrientation(Orientation.VERTICAL);
        listVychodu.setPrefHeight(200);
        listVychodu.setMinWidth(213);
        listVychodu.setMaxWidth(213);
        listVychodu.setCellFactory(param -> new ListCell<Prostor>() {
            @Override
            protected void updateItem(Prostor item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNazev() == null) {
                    setText(null);
                } else {
                    setText(item.getNazevProVypis());
                }
                this.setOnMousePressed(event -> {
                    main.zadejPrikaz("jdi "+item.getNazev());
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
            options.add(prostor);
        }
    }

    /**
     * Nastaví sledování nové hry.
     * @param novaHra instance nové hry
     */
    public void newGame(IHra novaHra){
        hra.getHerniPlan().removeObserver(this);
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }

}
