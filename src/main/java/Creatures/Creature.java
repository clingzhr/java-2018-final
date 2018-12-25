package Creatures;
import Battle.Battlefield;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;


public class Creature implements Fighting{
    int i; //在棋盘里面的位置
    int j;
    String name;
    boolean nature;
    Image image; //显示图片
    Image battle_Image;
    Image dead_Image;
    //引入战斗序列
    int total_blood; //总体的血量
    int cur_blood; //当前的血量
    boolean TypeOfAttack; //近程攻击还是远程攻击
    int power_of_attack; //攻击力
    int power_of_defence; //防御力
    MediaPlayer sound_of_battle; //战斗音响
    Media source_of_sound;
    boolean isAlive;

    public Creature(int x,int y) {
        i = x;
        j = y;
        //初始化都为满的
        total_blood = 100;
        cur_blood = 100; //都为满血power_of_attack
        power_of_attack = 10;
        power_of_defence = 0; //攻击力和防御力
        isAlive = true; //存活的
        this.battle_Image =  new Image(this.getClass().getClassLoader().getResource(new String("pic/火焰.png")).toString(),
                20,20,false,false);
        this.dead_Image =  new Image(this.getClass().getClassLoader().getResource(new String("pic/死亡.jpg")).toString(),
                50,50,false,false);
    }

    public Creature() {
        i = -1;
        j = -1;
        total_blood = 100;
        cur_blood = 90; //都为满血
        power_of_attack = 10;
        power_of_defence = 0; //攻击力和防御力
        isAlive = true;
        this.battle_Image =  new Image(this.getClass().getClassLoader().getResource(new String("pic/火焰.png")).toString(),
                20,20,false,false);
        this.dead_Image =  new Image(this.getClass().getClassLoader().getResource(new String("pic/死亡.jpg")).toString(),
                50,50,false,false);
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
        if(this.isAlive == true) {
            canvas.getGraphicsContext2D().drawImage(this.image, this.j * 50, this.i * 50);
            //显示血量条
            double rate = (double) this.cur_blood / this.total_blood; //进行初始化
            canvas.getGraphicsContext2D().setFill(Color.GREENYELLOW);
            canvas.getGraphicsContext2D().fillRect(this.j * 50, this.i * 50, 50 * rate, 5);
            canvas.getGraphicsContext2D().setFill(Color.RED);
            canvas.getGraphicsContext2D().fillRect(this.j * 50 + 50 * rate, this.i * 50, 50 * (1 - rate), 5);
        }
        else
        {
            canvas.getGraphicsContext2D().drawImage(this.dead_Image, this.j * 50, this.i * 50);
        }
    }

    public int[] want_to_mov_x_y(Battlefield ground) {
        ///检测，通往敌人多的方向去
        int num_up = 0;
        int num_down = 0;
        int num_right = 0;
        int num_left = 0;
        boolean to_left = true;
        boolean to_up = true; //向左和向上
        int pos[] = new int[2];
        pos[0] = this.i;
        pos[1] = this.j;
        //判断各个方位上的怪物
        for(int i = 0; i < 10;i++){
            for(int j = 0; j < 20; j++){
                if(i != this.i && j != this.j){
                    Creature temp = ground.get_Creature(i,j); //得到
                    if(temp != null){
                        if(temp.isAlive == true&& this.isAlive == true && this.nature != temp.nature){ //敌人
                            if(i < this.i){
                                num_up++;
                            }
                            else
                            {
                                num_down++;
                            }
                            if(j < this.j){
                                num_left ++;
                            }
                            else{
                                num_right++;
                            }
                        }
                    }
                }
            }
        }
        if(num_left < num_right){
            to_left =  false;
        }
        if(num_up < num_down){
            to_up = false;
        }
        int count = 0;
        //设置坐标
        while (true)
        {
            int x = this.i;
            int y = this.j;
            Random t = new Random();
            int choose = t.nextInt(4);
            if (choose == 0) {
                x = getX(to_up, x);
            } else if (choose == 1) {
                y = getY(to_left, y);
            } else if (choose == 2) {
                x = getX(to_up, x);
                y = getY(to_left, y);
            } else { //相反方向
                Random k = new Random();
                int r = k.nextInt(3);
                if (r == 0)
                    x = getX(!to_up, x);
                else if (r == 1)
                    y = getY(!to_left, y);
                else {
                    x = getX(!to_up, x);
                    y = getY(!to_left, y);
                }
            }
            Creature temp = ground.get_Creature(x, y);
            if (temp != null) {
                if (temp.is_Alive() == false) {//墓碑
                    count++;
                }
            } else {
                pos[0] = x;
                pos[1] = y;
                break;
            }
            System.out.println("change");
            if(count == 10) //找不到
            {
                System.out.println("change");
                for(int m = this.i - 1; m < this.i + 1 ; m++) {
                    for (int n = this.j - 1; n < this.j + 1; n++) {
                        if (m >= 0 && m < 10 && n >= 0 && n < 20) {
                            Creature cre_temp = ground.get_Creature(m, n);
                            if (cre_temp == null) {
                                Random can_move = new Random();
                                int chance = can_move.nextInt(2);
                                if (chance == 0) {
                                    pos[0] = m;
                                    pos[1] = n;
                                }
                            } else if (cre_temp.is_Alive() == false) {
                                Random can_move = new Random();
                                int chance = can_move.nextInt(2);
                                if (chance == 0) {
                                    pos[0] = m;
                                    pos[1] = n;
                                }
                            }
                        }
                    }
                }
                return pos;
            }
        }
        return pos;
    }

    private int getY(boolean to_left, int y) {
        if(to_left== true){
            y--;
            if(y < 0) y = 0;
        }
        else{
            y++;
            if(y > 19) y = 19;
        }
        return y;
    }

    private int getX(boolean to_up, int x) {
        if(to_up == true){
            x--;
            if(x < 0) x = 0;
        }
        else{
            x++;
            if(x > 9) x = 9;
        }
        return x;
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

    public boolean retNature() {
        return this.nature; //返回属性
    }

    public void killSelf() {
        this.isAlive = false; //死亡
    }

    public void attackEnemy(Creature enemy,Canvas canvas) {
        if(!this.is_Alive() || !enemy.is_Alive()) return;
        this.sound_of_battle.play();
        int damage = this.power_of_attack - enemy.power_of_defence;
        enemy.lost_blood(damage);
        int enemy_x = enemy.i;
        int enemy_y = enemy.j; //坐标
        //根据坐标进行绘图，进行攻击显示
        draw_attack(enemy_x,enemy_y,canvas);
        this.sound_of_battle.stop();
    }

    public void draw_attack(int t_x ,int t_y,Canvas canvas) {
        int cur_i = this.i;
        int cur_j = this.j;
        //考虑四个方向
        synchronized (canvas) {
            if (cur_j == t_y && cur_i < t_x) //向上
            {
                for (int ti = cur_i * 50 + 25; ti < t_x * 50 + 25; ti += 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battle_Image, this.j * 50 + 25, ti);
                }
            } else if (cur_j == t_y && cur_i > t_x) {
                for (int ti = cur_i * 50 + 25; ti > t_x * 50 + 25; ti -= 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battle_Image, this.j * 50 + 25, ti);
                }
            } else if (cur_i == t_x && cur_j < t_y) {
                for (int tj = cur_j * 50 + 25; tj < t_y * 50 + 25; tj += 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battle_Image, tj, this.i * 50 + 25);
                }
            } else if (cur_i == t_x && cur_j > t_y) {
                for (int tj = cur_j * 50 + 25; tj > t_y * 50 + 25; tj -= 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battle_Image, tj, this.i * 50 + 25);
                }
            } else if (cur_i > t_x && cur_j < t_y) //左上
            {
                for (int ti = cur_i * 50 + 25, tj = cur_j * 50 + 25; ti > t_x * 50 + 25 && tj < t_y * 50 + 25; ti -= 20, tj += 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battle_Image, tj, ti);
                }
            } else if (cur_i > t_x && cur_j > t_y) //左下
            {
                for (int ti = cur_i * 50 + 25, tj = cur_j * 50 + 25; ti > t_x * 50 + 25 && tj > t_y * 50 + 25; ti -= 20, tj -= 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battle_Image, tj, ti);
                }
            } else if (cur_i < t_x && cur_j < t_y) //右上
            {
                for (int ti = cur_i * 50 + 25, tj = cur_j * 50 + 25; ti < t_x * 50 + 25 && tj < t_y * 50 + 25; ti += 20, tj += 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battle_Image, tj, ti);
                }
            } else if (cur_i < t_x && cur_j > t_y) //右下
            {
                for (int ti = cur_i * 50 + 25, tj = cur_j * 50 + 25; ti < t_x * 50 + 25 && tj > t_y * 50 + 25; ti += 20, tj -= 20) {
                    canvas.getGraphicsContext2D().drawImage(this.battle_Image, tj, ti);
                }
            }
        }
    }
}

