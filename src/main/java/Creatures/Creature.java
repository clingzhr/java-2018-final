package Creatures;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;


public class Creature implements Fighting{
    int i; //在棋盘里面的位置
    int j;
    String name;
    boolean nature;
    Image image; //显示图片
    //引入战斗序列
    int total_blood; //总体的血量
    int cur_blood; //当前的血量
    boolean TypeOfAttack; //近程攻击还是远程攻击
    int power_of_attack; //攻击力
    int power_of_defence; //防御力

    boolean isAlive;

    public Creature(int x,int y) {
        i = x;
        j = y;
        //初始化都为满的
        total_blood = 100;
        cur_blood = 50; //都为满血power_of_attack
        power_of_attack = 10;
        power_of_defence = 0; //攻击力和防御力
        isAlive = true; //存活的
    }

    public Creature() {
        i = -1;
        j = -1;
        total_blood = 100;
        cur_blood = 90; //都为满血
        power_of_attack = 10;
        power_of_defence = 0; //攻击力和防御力
        isAlive = true;
    }

    public void changeTheposition(int i,int j) {
        this.i = i;
        this.j = j;
    }

    public void getTheinfo() {
        if(name != "蝎子精") System.out.print(name+"  ");
        else  System.out.print(name);
        System.out.print(i+"坐标"+j);
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void show_GUI(Canvas canvas){
        //显示图片
        canvas.getGraphicsContext2D().drawImage(this.image,this.j * 50 ,this.i * 50);
        //显示血量条
        double rate = (double)this.cur_blood / this.total_blood; //进行初始化
        canvas.getGraphicsContext2D().setFill(Color.GREENYELLOW);
        canvas.getGraphicsContext2D().fillRect(this.j * 50 ,this.i * 50, 50 * rate, 5);
        canvas.getGraphicsContext2D().setFill(Color.RED);
        canvas.getGraphicsContext2D().fillRect(this.j * 50 + 50 * rate  ,this.i * 50, 50 * (1-rate), 5);
        //canvas.getGraphicsContext2D().save();
    }

    public int want_to_mov_x() {
        int t = new Random().nextInt(2);
        //生成一个0到2
        int x  = this.i;
        if(x <= 5) {
            x ++;
        }
        else if(x > 5) {
            x--;
        }
        return  x;
    }

    public int want_to_mov_y() {
        int t = new Random().nextInt(2);
        //生成一个0到4的随机数
        int y = this.j;
        if( y <= 10)
        {
            y ++;
        }
        else
        {
            y --;
        }
        return  y;
    }

    public boolean is_Alive(){
        return this.isAlive; //是否是存活的
    }

    public boolean typeOfNature(){
        return this.nature; //返回性格
    }

    public void lost_blood(int i){
        this.cur_blood -= i;
        if(this.cur_blood <= 0)
        {
            this.cur_blood = 0;
            this.isAlive = false; //死亡
        }
    }

    public int retAttack(){
        return this.power_of_attack;
    }

    public int retDefence(){
        return this.power_of_defence;
    }

    @Override
    public void attackEnemy(Creature enemy) {
        System.out.print(this.name + "攻击了");enemy.getTheinfo();
        System.out.println();
        int damage = this.power_of_attack - enemy.power_of_defence;
        enemy.lost_blood(damage);
    }
}

