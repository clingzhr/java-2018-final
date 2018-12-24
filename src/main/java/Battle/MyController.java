package Battle;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Creatures.Creature;
import MyThread.GUIrefresh;
import Save.SAVE_action;
import Save.SAVE_init;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MyController implements Initializable {

    @FXML private Canvas mycanvas;
    @FXML private MenuBar mymenu;
    @FXML private MenuItem change;
    @FXML private MenuItem init;

    private int index_form_of_Huluwa;
    private int index_form_of_Mon;
    private Confront confront;
    private GUIrefresh Refresh;
    private SAVE_init save_init;
    private ArrayList<SAVE_action> save_actions;
    private boolean is_ready_to_fight;
    private boolean is_reviewing; //
    private Writer write;
    private FileReader read; //写线程
    public void initialize(URL location, ResourceBundle resources) {
        //进行初始化操作
        this.index_form_of_Huluwa = 0;
        this.index_form_of_Mon = 0;
        this.is_ready_to_fight = false;
        this.save_init = null;
        this.save_actions = new ArrayList<SAVE_action>(); //清空操作
        this.is_reviewing = false;
    }

    public void init_HULUWA(ActionEvent event) {
        //进行初始化操作
        if(this.is_ready_to_fight == true) return;
        else {
            this.is_reviewing = false;
            this.is_ready_to_fight = true;
        }
        confront = new Confront(this.mycanvas,this.save_actions); //开始新的战斗
        confront.initGrandfather();
        confront.initMonster();
        confront.initHuluwa();
        confront.sortThehuluwa();
        confront.makeChangeofFormaton(false,FormationName.values()[index_form_of_Mon]);
        confront.printThefield();
    }

    public void set_queue_Calash(ActionEvent event) {
        //进行初始化操作
        if(this.is_ready_to_fight == false) return;
        if(this.is_reviewing == true) return;
        confront.makeChangeofFormaton(true,FormationName.values()[index_form_of_Huluwa]);
        this.mycanvas.getGraphicsContext2D().clearRect(0, 0, 1100, 600); //清空画布
        this.mycanvas.getGraphicsContext2D().setStroke(Color.WHITE); //白线
        for(int i = 0; i <= 10;i ++){
            this.mycanvas.getGraphicsContext2D().strokeLine(0,i * 50,1000, i* 50);
        }
        for(int j = 0;j <= 20; j ++){
            this.mycanvas.getGraphicsContext2D().strokeLine(j * 50, 0, j *50,500 );
        }
        confront.printThefield();
        index_form_of_Huluwa++;
        index_form_of_Huluwa = index_form_of_Huluwa % 7;
    }

    public void set_queue_monster(ActionEvent event) {
        //进行初始化操作
        if(this.is_ready_to_fight == false) return;
        if(this.is_reviewing == true) return;
        confront.makeChangeofFormaton(false,FormationName.values()[index_form_of_Mon]);
        this.mycanvas.getGraphicsContext2D().clearRect(0, 0, 1100, 600); //清空画布
        this.mycanvas.getGraphicsContext2D().setStroke(Color.WHITE); //白线
        for(int i = 0; i <= 10;i ++){
            this.mycanvas.getGraphicsContext2D().strokeLine(0,i * 50,1000, i* 50);
        }
        for(int j = 0;j <= 20; j ++){
            this.mycanvas.getGraphicsContext2D().strokeLine(j * 50, 0, j *50,500 );
        }
        confront.printThefield();
        index_form_of_Mon++;
        index_form_of_Mon = index_form_of_Mon % 7;
    }

    public void startfight(ActionEvent event) {
        File saveRes = new File("save.txt"); //文件
        if (!saveRes.exists()) {
            try {
                saveRes.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            write = new FileWriter(saveRes,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //进行初始化操作
        if(this.is_ready_to_fight == false) return;
        if(this.is_reviewing == true) return;
        //先保存战场初始化
        this.save_init = new SAVE_init(this.confront.getInitBattledFiled());
        this.output_init();
        Refresh = new GUIrefresh(this.mycanvas,this.confront,this.write,this.save_actions,false,this.read);
        this.Refresh.start(); //开始执行
        this.is_ready_to_fight = false;
    }

    public void reviewAction() throws IOException//读取
    {
        if (this.is_ready_to_fight == true) return;
        if (this.is_reviewing == false) {
            this.is_reviewing = true; //开始回放
        }
        FileChooser fileChooser = new FileChooser();
        Stage mainStage = null;
        File selectedFile = fileChooser.showOpenDialog(mainStage); //打开文件
        try {
            this.read = new FileReader(selectedFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.confront = new Confront(this.mycanvas, this.save_actions);
        this.confront.change_war_to_start();
        Refresh = new GUIrefresh(this.mycanvas,this.confront,this.write,this.save_actions,true,this.read);
        this.Refresh.start(); //开始执行
        this.is_reviewing = false;
    }


    public void output_init(){
        int[][] t = this.save_init.getField();
        for(int i = 0; i < 10; i++) {
            String lineres = "";
            for(int j = 0; j < 20; j++){
                System.out.println(t[i][j]);
                lineres = lineres + Integer.toString(t[i][j]) + " ";
            }
            try {
                this.write.write(lineres);
                this.write.write("\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}