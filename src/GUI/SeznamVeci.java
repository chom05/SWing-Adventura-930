package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

import java.util.List;

/**
 * Created by MajkCajk on 21.11.17.
 */
public class SeznamVeci extends ListView implements Observer {

    private IHra hra;
    private Main main;
    private ObservableList<Object> data = FXCollections.observableArrayList();

    /**
     * Konstruktor vytvoří panel pro zobrazení obrázků věcí, které se nacházejí
     * v batohu.
     *
     * @param hra
     */
    public SeznamVeci(IHra hra, Main main) {
        this.hra = hra;
        this.main = main;
        init();
    }

    private void init() {
        this.setItems(data);
        setMaxHeight(main.getMapa().getImgHeight());
        setPadding(new Insets(0,15,0,0));
        //this.setPrefWidth(50);
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
        seznamVeci = hra.getHerniPlan().getAktualniProstor().getVeci();
        data.clear();
        for (Vec x : seznamVeci) {
            ImageView obrazek
                    = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/" + x.getNazevObrazku()), 50, 50, false, false));
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
