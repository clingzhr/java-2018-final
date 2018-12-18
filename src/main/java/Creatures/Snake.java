package Creatures;

import javafx.scene.image.Image;

public class Snake extends Monster
{
    public Snake()
    {
        super();
        this.name = "蛇精";
        this.image =  new Image(getClass().getClassLoader().getResource("pic/snake.jpg").toString()
                ,50,50,false,false);
    }
}
