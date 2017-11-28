package GUI;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.List;

import javafx.scene.layout.AnchorPane;
import logika.Vec;
import utils.Observer;
import logika.IHra;
import main.Main;

/**
 * Třída vytvářející panel batohu
 *
 * @author Michal Chobola - chom05
 */
public class BatohSeznam extends AnchorPane implements Observer {

    private IHra hra;
    private Main main;
    private ObservableList<Vec> data = FXCollections.observableArrayList();

    /**
     * Konstruktor vytvoří panel pro zobrazení obrázků věcí, které se nacházejí
     * v batohu.
     *
     * @param hra
     */
    public BatohSeznam(IHra hra,Main main) {
        this.hra = hra;
        this.main = main;
        init();
    }
    /**
     * Inicializace třídy
     *
     */
    private void init() {
        ListView<Vec> listVeciVProstoru = new ListView<>(data);
        listVeciVProstoru.setOrientation(Orientation.VERTICAL);
        listVeciVProstoru.setMaxHeight(123);
        listVeciVProstoru.setMinWidth(115);
        listVeciVProstoru.setMaxWidth(115);
        listVeciVProstoru.setCellFactory(param -> new ListCell<Vec>() {
            private ImageView imageView;
            @Override
            protected void updateItem(Vec item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNazev() == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getNazev());
                    imageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/" + item.getNazevObrazku()), 50, 50, false, false));
                    imageView.setFitHeight(20);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
                this.setOnMousePressed(event -> {
                    main.zadejPrikaz("zahod "+item.getNazev());
                    hra.getHerniPlan().notifyObservers();
                    update();
                });
            }
        });
        this.getChildren().addAll(listVeciVProstoru);
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
            data.add(x);
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
