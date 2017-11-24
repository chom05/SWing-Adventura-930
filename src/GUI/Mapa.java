package GUI;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 * Created by MajkCajk on 13.11.17.
 */
public class Mapa extends AnchorPane implements Observer {

    private IHra hra;
    private ImageView postava;
    private ImageView obrazekImageView;
    private ImageView app;

    private double imgWidth;
    private double imgHeight;

    public Mapa(IHra hra) {

        imgWidth = 512;
        imgHeight = 371;
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);

        init();
    }

    private void init() {

        obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/map.jpg"), imgWidth, imgHeight, false, true));

        postava = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/postava.png"), 45, 90, false, true));

//        this.setTopAnchor(tecka, 0.0);
//        this.setLeftAnchor(tecka, 0.0);

        this.getChildren().addAll(obrazekImageView, postava);
        app = (ImageView) (Node) this.getChildren().get(1);
        update();
    }

    public double getImgWidth() {
        return imgWidth;
    }

    public double getImgHeight() {
        return imgHeight;
    }

    public void newGame(IHra novaHra){
        hra.getHerniPlan().removeObserver(this);
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }

    @Override
    public void update() {
        app.setX(hra.getHerniPlan().getAktualniProstor().getPosTop());
        app.setY(hra.getHerniPlan().getAktualniProstor().getPosLeft());
        //this.setTopAnchor(postava, hra.getHerniPlan().getAktualniProstor().getPosTop());
        //this.setLeftAnchor(postava, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }

}
