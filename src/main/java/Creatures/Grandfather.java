package Creatures;

import javafx.scene.image.Image;

public class Grandfather extends Creature{
    public Grandfather()
    {
        super();
        name = "爷爷";
        nature = true;
        this.image =  new Image(getClass().getClassLoader().getResource("pic/grandfather.jpg").toString()
                ,50,50,false,false);
    }
}
