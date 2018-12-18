package Creatures;

import java.net.URL;
import javafx.scene.image.Image;

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
        this.url_of_image = this.getClass().getClassLoader().getResource(new String("pic/"+ (this.pro+1) +".jpg"));
        this.image =  new Image(url_of_image.toString(),50,50,false,false);
        theBro = Color.values()[i];
        this.name = theBro.getName();
        nature = true;
    }

    public void callTheposition()
    {
        System.out.println(name+"的位置为:"+this.i+" "+this.j);
    }

    public int getPro()
    {
        return pro;
    }
}