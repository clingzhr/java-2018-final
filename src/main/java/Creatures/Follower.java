package Creatures;

import javafx.scene.image.Image;

public class Follower extends Monster
{
    public Follower()
    {
        super();
        this.name = "小怪";
        this.image =  new Image(getClass().getClassLoader().getResource("pic/monster.jpg").toString()
                ,50,50,false,false);

    }
}
