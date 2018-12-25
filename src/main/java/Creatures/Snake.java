package Creatures;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Snake extends Monster
{
    public Snake()
    {
        super();
        this.name = "蛇精";
        this.power_of_attack = 30;
        this.power_of_defence = 0;
        this.image =  new Image(getClass().getClassLoader().getResource("pic/snake.jpg").toString()
                ,50,50,false,false);
        this.battle_Image =  new Image(this.getClass().getClassLoader().getResource(new String("pic/水滴.png")).toString(),
                20,20,false,false);
        this.source_of_sound = new Media(this.getClass().getClassLoader().getResource(new String("music/attack_2.mp3")).toString());
        this.sound_of_battle = new MediaPlayer(this.source_of_sound);
        this.sound_of_battle.setAutoPlay(false);
    }
}
