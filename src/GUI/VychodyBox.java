package GUI;

import utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import logika.IHra;
import logika.Prostor;
/**
 * Created by MajkCajk on 19.11.17.
 */
public class VychodyBox extends ComboBox implements Observer {

    private IHra hra;
    private final ObservableList<String> options = FXCollections.observableArrayList();
    String value = " ";

    /**
     * Konstruktor, který zavoláním metody init, zaregistruje pozorovatele a
     * nadefinuje podobu ComboBoxu.
     *
     * @param hra
     */
    public VychodyBox(IHra hra) {
        this.hra = hra;
        init();
    }

    /**
     * Metoda vrací ComboBox a je použita ve třídě Main k zpřístupnění
     * ComboBoxu.
     *
     * @return Vrací Combobox.
     */
    public ComboBox getComboBox() {
        return this;
    }

    public void init() {
        this.setItems(options);
        this.setPromptText("Zadej lokaci");
        this.setEditable(true);
        this.setPrefWidth(150);
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
