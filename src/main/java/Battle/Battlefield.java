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
        boolean tempNature;
        if(this.field[i][j].retCreature()!=null)
            tempNature = this.field[i][j].retCreature().typeOfNature();
        else return null;
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

    public boolean isCalashAlive() {
        boolean alive = false;
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 20; j++) {
                if(this.field[i][j].isEmpty() == false){ //有生物体存在
                    Creature temp = this.field[i][j].retCreature();//得到引用
                    if(temp.is_Alive() && temp.retNature() == true) {//正义
                        alive = true;
                    }
                }
            }
        }
        return alive;
    }

    public boolean isMonsterAlive() {
        boolean alive = false;
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 20; j++) {
                if(this.field[i][j].isEmpty() == false){ //有生物体存在
                    Creature temp = this.field[i][j].retCreature();//得到引用
                    if(temp.is_Alive() && temp.retNature() == false){//邪恶
                        alive = true;
                    }
                }
            }
        }
        return alive;
    }

    public void killAll() {
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20; j++){
                this.field[i][j].killPos();
            }
        }
    }

    public Creature get_Creature(int i,int j){
        return this.field[i][j].retCreature(); //返回
    }

    public int[][] initFiled(Creature[] creatures) {
        int [][]init = new int[10][20];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20; j++) {
                Creature t = this.field[i][j].retCreature();
                if (t == null) init[i][j] = -1;
                else {
                    for(int k = 0; k < creatures.length; k++){
                        if(creatures[k] == t)
                        {
                            init[i][j] = k;
                        }
                    }
                }
            }
        }
        return init; //返回初始化
    }
}

