package Battle;
import Creatures.*;
import Save.SAVE_action;
import javafx.scene.canvas.Canvas;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import MyThread.*;
import javafx.scene.paint.Color;

import static java.lang.Thread.*;

//对峙
public class Confront {
    private Creature[] creatures;
    private CalabashBrother[]Huluwas;
    private Grandfather grandfather;
    private Snake snake;
    private Scorpion scorpion;
    private Follower[]follower;
    private Battlefield ground;
    private Formation form;
    private Lock lock; //进程锁，以便后来的进行移动
    private myThread[] threads_of_mine;
    private Canvas mycanvas;

    public Confront(Canvas canvas, ArrayList<SAVE_action> save_actions){
        this.creatures = new Creature[17]; //17生物体
        threads_of_mine = new myThread[17]; //为17个线程
        ground = new Battlefield(10,20); //创造一个10行20列的战场
        form = new Formation();
        lock = new ReentrantLock(); //建立同步锁
        //创建线程
        //葫芦娃
        Huluwas = new CalabashBrother[7];
        this.mycanvas = canvas;
        for(int i  = 0; i < 7;i++)
        {
            Huluwas[i] = new CalabashBrother(i,-1,-1);
            threads_of_mine[i] = new myThread(Huluwas[i],ground,lock,mycanvas,save_actions); //线程
            creatures[i] = Huluwas[i];
        }
        //爷爷
        grandfather = new Grandfather();
        threads_of_mine[7] = new myThread(grandfather,ground,lock,mycanvas,save_actions);
        creatures[7] = grandfather;
        //蛇精
        snake = new Snake();
        threads_of_mine[8] = new myThread(snake,ground,lock,mycanvas,save_actions);
        creatures[8] = snake;
        //蝎子精
        scorpion = new Scorpion();
        threads_of_mine[9] = new myThread(scorpion,ground,lock,mycanvas,save_actions);
        creatures[9] = scorpion;
        //小怪
        follower = new Follower[7];
        for(int i = 0; i < 7; i++)
        {
            follower[i] = new Follower();
            threads_of_mine[10 + i] = new myThread(follower[i],ground,lock,mycanvas,save_actions);
            creatures[i + 10] = follower[i];
        }
        //初始化完毕
        //开始进行线程的RUN方法

    }
    //初始化放置葫芦娃
    public void initHuluwa( ) {
        Random rand =new Random();
        for(int i = 0; i  < 7;i++) {
            int k = rand.nextInt(7);
            CalabashBrother temp = Huluwas[k];
            Huluwas[k] = Huluwas[i];
            Huluwas[i] = temp;
        }
        //将葫芦娃放置在战场上
        for(int i = 0; i <7;i++)
        {
            ground.putTheCre(Huluwas[i],i+1,5);
        }
    }
    //初始化爷爷
     public void initGrandfather( )
     {
         ground.putTheCre(grandfather,5,0);
     }
     //初始化蛇精
     public void initMonster( )
     {
         ground.putTheCre(snake,5,19);
     }
     //排序葫芦娃
    public void sortThehuluwa( ) {
        for(int i = 6; i >= 0;i--)
        {
            for(int j = 0; j < i;j++)
            {
                if(Huluwas[j].getPro() > Huluwas[j+1].getPro())
                {
                    CalabashBrother temp = Huluwas[j];
                    Huluwas[j] = Huluwas[j+1];
                    Huluwas[j+1] = temp;
                }
            }
        }
        for(int i = 0; i<7;i++)
        {
            ground.movTheCre(Huluwas[i],i+1,5);
        }
    }
    //改变阵型
    public void makeChangeofFormaton(boolean t,FormationName name) {
        int index = name.ordinal();
        if(t == false)
            form.changeFormation_mon(index,scorpion,follower,ground); //改变小怪阵型
        else
            form.changeFormation_Hulu(index,this.Huluwas,ground);
    }
    //打印
    public void printThefield() {
        this.mycanvas.getGraphicsContext2D().clearRect(0, 0, 1100, 600); //清空画布
        //画gezi
        this.mycanvas.getGraphicsContext2D().setStroke(Color.WHITE); //白线
        for(int i = 0; i <= 10;i ++){
            this.mycanvas.getGraphicsContext2D().strokeLine(0,i * 50,1000, i* 50);
        }
        for(int j = 0;j <= 20; j ++){
            this.mycanvas.getGraphicsContext2D().strokeLine(j * 50, 0, j *50,500 );
        }
        for(int i = 0; i< 10 ;i++)
        {
            for(int j = 0; j < 20;j++)
            {
                ground.showThecreature(i,j,this.mycanvas);
            }
        }
    }

    public void start_Fight()
    {
        this.ground.setWar_start();
        for(int i = 0; i < 17 ; i++)
        {
                this.threads_of_mine[i].start();
        }
    }

    public void change_war_to_start()
    {
        this.ground.setWar_start();
    }
    public Battlefield ret_ground()
    {
        return this.ground;
    }

    public void closeAll() {
        this.ground.killAll();
    }

    public int[][] getInitBattledFiled(){
        return this.ground.initFiled(this.creatures);
    }

    public void put_init_pos(int [][]t){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 20;j++){
                if(t[i][j] != -1) {
                    int num = t[i][j];
                    if (num <= 6)
                        this.ground.movTheCre(Huluwas[num], i, j); //移动
                    else if (num == 7)
                        this.ground.movTheCre(this.grandfather, i, j);
                    else if (num == 8)
                        this.ground.movTheCre(this.snake, i, j);
                    else if (num == 9)
                        this.ground.movTheCre(this.scorpion, i, j);
                    else if (num < 17)
                        this.ground.movTheCre(this.follower[num - 10], i, j);
                }
            }
        }
    }
}
