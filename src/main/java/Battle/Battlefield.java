package Battle;


import Creatures.Creature;
import javafx.scene.canvas.Canvas;

import static java.lang.System.err;
import static java.lang.System.exit;

public class Battlefield {
    private int x;
    private int y;
    private boolean war_start;
    private thePosition[][] field;

    public Battlefield(int x,int y) {
        field = new thePosition[x][y];
        this.x = x;
        this.y = y;
        for(int i =0;i<x;i++)
        {
            for(int j = 0; j < y;j++)
            {
                field[i][j] = new thePosition(i,j);
            }
        }
        war_start = false;
    }

    public void putTheCre(Creature temp, int i, int j) {
        if(i < 0 || i > 9 || j < 0 || j > 19)
        {
            System.err.println("坐标错误!");
            exit(-1);
        }
        field[i][j].getCre(temp);
    }

    public void movTheCre(Creature temp,int i,int j) {
        if(i < 0 || i > 9 || j < 0 || j > 19)
        {
            System.err.println("坐标错误!");
            exit(-1);
        }
        int x = temp.getI();
        int y = temp.getJ();
        //未放置
        if(x == -1 && y == -1)
        {
            field[i][j].getCre(temp);
        }
        else if(x != i || y != j)
        {
            if(field[i][j].isEmpty() == false)
            {
            //交换
                if(war_start == false) field[i][j].swapCre(field[x][y]); //战斗没开始可以交换位置
            }
            else
            {
                field[i][j].movCre(field[x][y]);
            }
        }
    }

    public void showThecreature(int i, int j,Canvas canvas) {
        field[i][j].showThecreature(canvas);
    }

    public void setWar_start()
    {
        war_start = true; //战斗开始，不能随便交换位置
    }

    public Creature checkEnemy(int i,int j){ //根据位置查询周围有没有敌人
        //检查四面和左右角是否有敌人
        Creature res = null;
        boolean hasEnemy = false; //一开始没有敌人
        boolean tempNature = this.field[i][j].retCreature().typeOfNature();
        for(int x = i - 1; x <=  i + 1; x ++) {
            for(int y = j - 1; y <= j + 1; y ++){
                if(x < 0 || x > 9 || y < 0 || y > 19)
                {
                    continue;
                }
                Creature t = this.field[x][y].retCreature();
                if(t == null ) continue;
                if(t.is_Alive() == false) continue;
                if(t.typeOfNature() != tempNature) {
                    //找到敌人!
                    System.out.println("discover enemy");
                    hasEnemy = true;
                    res = t;
                    break;
                }
            }
        }
        return res;
    }

    public void remove(int i, int j) {
        this.field[i][j].clearPos();
    }
}

