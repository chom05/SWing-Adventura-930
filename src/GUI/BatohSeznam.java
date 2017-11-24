package GUI;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.List;
import logika.Vec;
import utils.Observer;
import logika.IHra;
import main.Main;

/**
 * Třída vytvářející panel batohu.
 *
 * @author Michal Chobola - chom05
 * @created ZS 2017/2018
 */
public class BatohSeznam extends ListView implements Observer {

    private IHra hra;
    private ObservableList<Object> data = FXCollections.observableArrayList();

    /**
     * Konstruktor vytvoří panel pro zobrazení obrázků věcí, které se nacházejí
     * v batohu.
     *
     * @param hra
     */
    public BatohSeznam(IHra hra) {
        this.hra = hra;
        init();
    }

    private void init() {
        setItems(data);
        setMaxHeight(123);
        setPadding(new Insets(0,15,0,0));
        hra.getHerniPlan().registerObserver(this);
        update();
    }

    /**
     * Metoda vymaže vše z panelu a následně aktualizuje obsah při každé změně.
     * batohu.
     */
    @Override
    public void update() {

        List<Vec> seznamVeci;
        seznamVeci = hra.getHerniPlan().getBatoh().getObsahBatohu();
        data.clear();
        for (Vec x : seznamVeci) {
            ImageView obrazek
                    = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/" + x.getNazevObrazku()), 10, 10, false, false));
            data.add(obrazek);
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
