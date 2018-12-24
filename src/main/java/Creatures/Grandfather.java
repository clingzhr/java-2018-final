package Creatures;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Grandfather extends Creature{
    public Grandfather()
    {
        super();
        name = "爷爷";
        nature = true;
        this.image =  new Image(getClass().getClassLoader().getResource("pic/grandfather.jpg").toString()
                ,50,50,false,false);
        this.source_of_sound = new Media(this.getClass().getClassLoader().getResource(new String("music/attack_1.mp3")).toString());
        this.sound_of_battle = new MediaPlayer(this.source_of_sound);
        this.sound_of_battle.setAutoPlay(false);
    }
}
