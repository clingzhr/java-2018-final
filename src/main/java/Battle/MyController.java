package Battle;

import java.net.URL;
import java.util.ResourceBundle;

import MyThread.GUIrefresh;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;

public class MyController implements Initializable {

    @FXML private Canvas mycanvas;
    @FXML private MenuBar mymenu;
    @FXML private MenuItem change;
    @FXML private MenuItem init;

    private int i;
    private Confront confront;
    private GUIrefresh Refresh;
    public void initialize(URL location, ResourceBundle resources) {
        //进行初始化操作
    }

    public void init_HULUWA(ActionEvent event) {
        //进行初始化操作
        confront = new Confront(this.mycanvas); //开始新的战斗
        confront.initGrandfather();
        confront.initMonster();
        confront.initHuluwa();
        confront.sortThehuluwa();
        confront.printThefield();
    }

    public void set_queue(ActionEvent event) {
        //进行初始化操作
        confront.makeChangeofMonster(FormationName.values()[i]);
        confront.printThefield();
        i++;
        i = i % 7;
    }

    public void startfight(ActionEvent event) {
        //进行初始化操作
        confront.start_Fight();
        Refresh = new GUIrefresh(this.mycanvas,this.confront.ret_groung());
        this.Refresh.start(); //开始执行
    }
}