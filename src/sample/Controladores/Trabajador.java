package sample.Controladores;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ResourceBundle;

public class Trabajador extends Node {


    @Override
    protected NGNode impl_createPeer() {
        return null;
    }

    public Trabajador( BorderPane layout){
        FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("trabajador.fxml"));
       // fxmlLoader.setResources(bundle);
        fxmlLoader.setController(this);

        try {
            layout.setCenter(new AnchorPane((Parent) fxmlLoader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BaseBounds impl_computeGeomBounds(BaseBounds bounds, BaseTransform tx) {
        return null;
    }

    @Override
    protected boolean impl_computeContains(double localX, double localY) {
        return false;
    }

    @Override
    public Object impl_processMXNode(MXNodeAlgorithm alg, MXNodeAlgorithmContext ctx) {
        return null;
    }
}
