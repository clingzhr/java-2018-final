package Creatures;

import Battle.Battlefield;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Random;

public class Snake extends Monster
{
    public Snake()
    {
        super();
        this.name = "蛇精";
        this.power_of_attack = 20;
        this.power_of_defence = 0;
        this.image =  new Image(getClass().getClassLoader().getResource("pic/snake.jpg").toString()
                ,50,50,false,false);
        this.battle_Image =  new Image(this.getClass().getClassLoader().getResource(new String("pic/水滴.png")).toString(),
                20,20,false,false);
        this.source_of_sound = new Media(this.getClass().getClassLoader().getResource(new String("music/attack_2.mp3")).toString());
        this.sound_of_battle = new MediaPlayer(this.source_of_sound);
        this.sound_of_battle.setAutoPlay(false);
        this.skillImage =  new Image(getClass().getClassLoader().getResource("pic/加.png").toString()
                ,20,20,false,false);
    }

    @Override //使用技能
    public void usingSkill(Battlefield ground, Canvas canvas){ //对于战场上的生物加血
        if(this.isAlive == false) return;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20; j++){
                Creature temp = ground.get_Creature(i,j);
                if(temp != null){
                    if(temp.is_Alive() && temp.retNature() == false) { //正义的
                        temp.addBlood(15); //回复
                        canvas.getGraphicsContext2D().drawImage(this.skillImage, j * 50, i * 50);
                    }
                }
            }
        }
    }
}
