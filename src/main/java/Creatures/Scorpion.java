package Creatures;

import javafx.scene.image.Image;

public class Scorpion extends Monster
{
    public Scorpion()
    {
        super();
        this.name = "蝎子精";
        this.image =  new Image(getClass().getClassLoader().getResource("pic/xiezi.jpg").toString()
                ,50,50,false,false);
    }
}
