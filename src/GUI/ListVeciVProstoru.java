package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import logika.IHra;
import logika.Monstrum;
import logika.Postava;
import logika.Vec;
import main.Main;
import utils.Observer;

import java.util.List;
/**
 * Třída vytvářející panel veci, lidi a monster v Prostoru.
 *
 * @author Michal Chobola - chom05
 */
public class ListVeciVProstoru extends BorderPane implements Observer {

    private IHra hra;
    private Main main;
    private ObservableList<Vec> dataVec = FXCollections.observableArrayList();
    private ObservableList<Postava> dataPostava = FXCollections.observableArrayList();
    private ObservableList<Monstrum> dataMonstrum = FXCollections.observableArrayList();

    /**
     * Konstruktor třídy
     *
     * @param hra
     */
    public ListVeciVProstoru(IHra hra, Main main) {
        this.hra = hra;
        this.main = main;
        update();
        init();
    }
    /**
     * Inicializace třídy
     *
     */
    private void init() {
        this.setMinWidth(220);
        this.setMaxWidth(220);
        //this.setMaxHeight(main.getMapa().getImgHeight()-15);
        ListView<Vec> listVeciVProstoru = new ListView<>(dataVec);
        listVeciVProstoru.setMaxHeight(main.getMapa().getImgHeight()/2);
        listVeciVProstoru.setOrientation(Orientation.VERTICAL);
        listVeciVProstoru.setCellFactory(param -> new ListCell<Vec>() {
            private ImageView imageView;
            @Override
            protected void updateItem(Vec item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNazev() == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    imageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/" + item.getNazevObrazku()), 50, 50, false, false));
                    imageView.setFitHeight(40);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                    setText(item.getNazev());
                }
                this.setOnMousePressed(event -> {
                    main.zadejPrikaz("vezmi "+item.getNazev());
                    hra.getHerniPlan().notifyObservers();
                    update();
                });
            }
        });
        ListView<Postava> listPostavVProstoru = new ListView<>(dataPostava);
        listPostavVProstoru.setOrientation(Orientation.VERTICAL);
        listPostavVProstoru.setCellFactory(param -> new ListCell<Postava>() {
            private ImageView imageView;
            @Override
            protected void updateItem(Postava item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getJmeno() == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getJmeno());
                    imageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/" + item.getNazevObrazku()), 50, 50, false, false));
                    imageView.setFitHeight(40);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
                this.setOnMousePressed(event -> {
                    main.zadejPrikaz("mluv "+item.getJmeno());
                    hra.getHerniPlan().notifyObservers();
                    update();
                });
            }
        });
        ListView<Monstrum> listMonsterVProstoru = new ListView<>(dataMonstrum);
        listMonsterVProstoru.setOrientation(Orientation.VERTICAL);
        listMonsterVProstoru.setCellFactory(param -> new ListCell<Monstrum>() {
            private ImageView imageView;
            @Override
            protected void updateItem(Monstrum item, boolean empty) {
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
                    main.zadejPrikaz("bojuj "+item.getNazev());
                    hra.getHerniPlan().notifyObservers();
                    update();
                });
            }
        });
        listVeciVProstoru.setMinHeight(150);
        listPostavVProstoru.setMinHeight(150);
        listMonsterVProstoru.setMinHeight(150);
        listMonsterVProstoru.setMaxHeight(150);
        this.getChildren().clear();
        this.setTop(listVeciVProstoru);
        this.setCenter(listPostavVProstoru);
        this.setBottom(listMonsterVProstoru);
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
        List<Postava> seznamPostava;
        List<Monstrum> seznamMonstrum;
        seznamVeci = hra.getHerniPlan().getAktualniProstor().getVeci();
        seznamPostava = hra.getHerniPlan().getAktualniProstor().getSeznamPostav();
        seznamMonstrum = hra.getHerniPlan().getAktualniProstor().getSeznamMonster();
        dataVec.clear();
        for (Vec x : seznamVeci) {
            dataVec.add(x);
        }
        dataPostava.clear();
        for (Postava p : seznamPostava){
            dataPostava.add(p);
        }
        dataMonstrum.clear();
        for (Monstrum m : seznamMonstrum){
            dataMonstrum.add(m);
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
