package MyThread;

import Battle.*;
import Creatures.Creature;
import Save.SAVE_action;
import javafx.scene.canvas.Canvas;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class myThread  extends Thread{

    private Creature creature_of_thread; //类
    private Battlefield ground; //引用类型
    private Lock lock;
    private Canvas mycanvas;
    private boolean living;
    private ArrayList<SAVE_action> save_actions;
    public myThread(Creature creature_of_thread,Battlefield ground,Lock lock,Canvas canvas, ArrayList<SAVE_action> save_actions)
    {
        this.ground = ground;
        creature_of_thread.getTheinfo();
        System.out.println("创建!");
        this.creature_of_thread = creature_of_thread;
        this.lock = lock; //锁
        this.mycanvas = canvas;
        this.save_actions = save_actions; //保存动作
        this.living = true;
    }
    //重写方法
    @Override
    public  synchronized void run() //作为进行传参
    {
            while(true)
            {
                if(this.creature_of_thread.is_Alive()) //如果存活，就进行实验
                {
                    try {
                        this.attack();
                        Thread.sleep(500);
                        this.mov();
                        Thread.sleep(500);
                        this.usingSkill();
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace(); //打印出来
                        return;
                    }
                }
                else {
                    if (this.living == true) { //结束延迟5s后死亡
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.living = false;
                        return; //结束线程
                    }
                }
            }
    }

    private void usingSkill() {
        if(this.living == false) return;
        synchronized (this.ground) {
            if (this.creature_of_thread.is_Alive() == false) return;
            Random t = new Random();
            int k = t.nextInt(10);
            if (k >= 1) {  //0.1的概率
                return;
            }
            //否则使用技能
            this.creature_of_thread.usingSkill(this.ground,this.mycanvas);
            int start[] = { this.creature_of_thread.getI() , this.creature_of_thread.getJ() };
            SAVE_action action = new SAVE_action(2,start,start);
            synchronized (this.save_actions) {
                this.save_actions.add(action);
            }
        }//保存动作
    }

    private void removeFromBattle() {
        try {
            synchronized (this.ground) {
                this.ground.remove(this.creature_of_thread.getI(),this.creature_of_thread.getJ());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("remove error");
        }
    }

    private void attack() {
        if(this.living == false) return;
        synchronized (this.ground) {
            if (this.creature_of_thread.is_Alive() == false) return;
            Creature enemy = this.ground.checkEnemy(this.creature_of_thread.getI(),this.creature_of_thread.getJ()); //根据坐标获取
            if (enemy == null) return;
            if (this.creature_of_thread.is_Alive() && enemy.is_Alive()) //都活着
            {
                int[] start = {this.creature_of_thread.getI(),this.creature_of_thread.getJ()};
                int []end = {enemy.getI(),enemy.getJ()};
                System.out.print("攻击");
                System.out.println(start[0]+" " + start[1] + " " +end[0] + " "+end[1]);
                SAVE_action action = new SAVE_action(1,start,end);
                synchronized (this.save_actions) {
                    this.save_actions.add(action);
                }
                this.creature_of_thread.attackEnemy(enemy, this.mycanvas);
            }
        }//保存动作
    }

    public void mov()
    {
        if(this.living == false) return;
        try {
            //访问临界区方法，移动
            synchronized(this.ground) {
                int pos[] = creature_of_thread.want_to_mov_x_y(this.ground); //检测
                int x= pos[0];
                int y =pos[1]; //移动的x，y
                int []start = {this.creature_of_thread.getI(),this.creature_of_thread.getJ()};
                SAVE_action action = new SAVE_action(0,start,pos);
                System.out.print("移动");
                System.out.println(start[0]+" " + start[1] + " " +pos[0] + " "+pos[1]);
                synchronized (this.save_actions) {
                    this.save_actions.add(action);
                }
                ground.movTheCre(this.creature_of_thread, x, y);
            }
        }
        catch (Exception e) {
            System.out.println("error_1");
        }
    }

}