package Creatures;

import java.net.URL;

import Battle.Battlefield;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

enum Color
{
    Red("大娃"),Orange("二娃"),Yellow("三娃"),Green("四娃"),Cyan("五娃"),Blue("六娃"),Purple("七娃");
    private final String name;
    Color(String name)
    {
        this.name = name;
    }
    //print and get
    public void callName()
    {
        System.out.println(name);
    }
    public String getName() {
        return name;
    }
}

public class CalabashBrother extends Creature {

    private Color theBro; //葫芦娃
    private int pro;
    private URL url_of_image = null;

    //创造葫芦兄弟类
    public CalabashBrother(int i,int x,int y) {
        super(x,y);
        pro = i;
        this.power_of_attack = 15;
        this.power_of_defence = 5;
        switch (pro)
        {
            case 0:this.power_of_attack = 18;this.power_of_defence = 5; break;
            case 1:this.power_of_attack = 20; this.power_of_defence = 0;break;
            case 2:this.power_of_attack = 10;this.power_of_defence = 10;break;
        }//特殊处理
        this.url_of_image = this.getClass().getClassLoader().getResource(new String("pic/"+ (this.pro+1) +".jpg"));
        this.image =  new Image(url_of_image.toString(),50,50,false,false);
        this.source_of_sound = new Media(this.getClass().getClassLoader().getResource(new String("music/attack_1.mp3")).toString());
        this.sound_of_battle = new MediaPlayer(this.source_of_sound);
        this.sound_of_battle.setAutoPlay(false);
        theBro = Color.values()[i];
        this.name = theBro.getName();
        nature = true;
        this.initSkillImage();
    }

    public void initSkillImage(){
        if(this.pro == 3){
            this.skillImage = new Image(this.getClass().getClassLoader().getResource(new String("pic/huowa.jpg")).toString()
                    ,150,150,false,false);
        }
        else if(this.pro == 4){
            this.skillImage = new Image(this.getClass().getClassLoader().getResource(new String("pic/shuiwa.jpg")).toString()
                    ,150,150,false,false);
        }
        else if(this.pro == 6){
            this.skillImage = new Image(this.getClass().getClassLoader().getResource(new String("pic/ziwa.png")).toString()
                    ,20,20,false,false);
        }
    }

    public void callTheposition()
    {
        System.out.println(name+"的位置为:"+this.i+" "+this.j);
    }

    public int getPro()
    {
        return pro;
    }

    @Override
    public void usingSkill(Battlefield ground, Canvas canvas){ //使用技能
        //每个不同的葫芦娃的技能是不一样的
        if(this.pro == 3 || this.pro == 4){ //喷火技能
            int t_x = this.j;
            if(t_x == 0) t_x = 1;
            int t_y = this.i;
            if(t_y == 0) t_y = 1;
            canvas.getGraphicsContext2D().drawImage(this.skillImage,t_x * 50 - 50, t_y * 50 - 50);
            for(int m = i - 1; m <= i + 1 ;m ++ ){
                for(int n = j - 1; n <= j + 1; n++){
                    if(m >= 0 && m <= 9 && n >=0 && n <= 19) {
                        Creature temp = ground.get_Creature(i, j);
                        if (temp != null) {
                            if (temp.is_Alive() && temp.retNature() == false) { //正义的
                                temp.lost_blood(30);
                            }
                        }
                    }
                }
            }
        }
        else if(this.pro == 6){ //群体伤害
            if(this.isAlive == false) return;
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 20; j++){
                    Creature temp = ground.get_Creature(i,j);
                    if(temp != null){
                        if(temp.is_Alive() && temp.retNature() == false) { //正义的
                            temp.lost_blood(10);
                            canvas.getGraphicsContext2D().drawImage(this.skillImage, j * 50 + 15, i * 50 + 15);
                        }
                    }
                }
            }
        }
        else if(this.pro == 5){//消除怒气
            if(this.isAlive == false) return;
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 20; j++){
                    Creature temp = ground.get_Creature(i,j);
                    if(temp != null){
                        if(temp.is_Alive() && temp.retNature() == false) { //正义的
                            this.setAngryorNot(false); //设置不生气
                        }
                    }
                }
            }
        }
        else{
            this.addBlood(5);
        }
    }
}