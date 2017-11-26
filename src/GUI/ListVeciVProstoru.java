package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

import java.util.List;

/**
 * Created by MajkCajk on 21.11.17.
 */
public class ListVeciVProstoru extends AnchorPane implements Observer {

    private IHra hra;
    private Main main;
    private ObservableList<Vec> data = FXCollections.observableArrayList();

    /**
     * Konstruktor vytvoří panel pro zobrazení obrázků věcí, které se nacházejí
     * v batohu.
     *
     * @param hra
     */
    public ListVeciVProstoru(IHra hra, Main main) {
        this.hra = hra;
        this.main = main;
        init();
    }

    private void init() {
        ListView<Vec> listVeciVProstoru = new ListView<>(data);
        listVeciVProstoru.setOrientation(Orientation.VERTICAL);
        listVeciVProstoru.setMaxHeight(main.getMapa().getImgHeight());
        listVeciVProstoru.setMinWidth(220);
        listVeciVProstoru.setMaxWidth(220);
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
                    imageView.setFitHeight(40);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
                this.setOnMousePressed(event -> {
                    hra.zpracujPrikaz("vezmi "+item.getNazev());
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
        seznamVeci = hra.getHerniPlan().getAktualniProstor().getVeci();
        data.clear();
        for (Vec x : seznamVeci) {
            //ImageView obrazek = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/" + x.getNazevObrazku()), 50, 50, false, false));
            data.add(x);
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
