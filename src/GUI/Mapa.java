package GUI;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 * Třída vytvářející panel s mapou
 *
 * @author Michal Chobola - chom05
 */
public class Mapa extends AnchorPane implements Observer {

    private IHra hra;
    private ImageView postava;
    private ImageView obrazekImageView;
    private ImageView app;

    private double imgWidth;
    private double imgHeight;
    /**
     * Konstruktor třídy
     * @param hra
     *
     */
    public Mapa(IHra hra) {

        imgWidth = 512;
        imgHeight = 371;
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);

        init();
    }
    /**
     * Inicializace třídy
     *
     */
    private void init() {
        obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/map.jpg"), imgWidth, imgHeight, false, true));

        postava = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/postava.png"), 45, 90, false, true));

        this.getChildren().addAll(obrazekImageView, postava);
        app = (ImageView) (Node) this.getChildren().get(1);
        update();
    }
    /**
     * Getter na šírku mapy
     */
    public double getImgWidth() {
        return imgWidth;
    }

    /**
     * Getter na výšku mapy
     */
    public double getImgHeight() {
        return imgHeight;
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
    /**
     * Metoda vymaže vše z panelu a následně aktualizuje obsah při každé změně.
     * mapy.
     */
    @Override
    public void update() {
        postava.setX(hra.getHerniPlan().getAktualniProstor().getPosTop());
        postava.setY(hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }

}
