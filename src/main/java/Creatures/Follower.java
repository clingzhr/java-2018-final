package Creatures;

import Battle.Battlefield;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Follower extends Monster
{
    public Follower()
    {
        super();
        this.name = "小怪";
        this.power_of_attack = 15;
        this.power_of_defence = 6;
        this.image =  new Image(getClass().getClassLoader().getResource("pic/monster.jpg").toString()
                ,50,50,false,false);
        this.battle_Image =  new Image(this.getClass().getClassLoader().getResource(new String("pic/水滴.png")).toString(),
                20,20,false,false);
        this.source_of_sound = new Media(this.getClass().getClassLoader().getResource(new String("music/attack_2.mp3")).toString());
        this.sound_of_battle = new MediaPlayer(this.source_of_sound);
        this.sound_of_battle.setAutoPlay(false);
    }
    @Override //使用技能
    public void usingSkill(Battlefield ground, Canvas canvas) { //对于战场上的生物加血
        this.addBlood(5); //回五
    }
}
