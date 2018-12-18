package MyThread;

import Battle.*;
import Creatures.Creature;
import javafx.scene.canvas.Canvas;
import java.util.concurrent.locks.Lock;

public class myThread  extends Thread{

    private Creature creature_of_thread; //类
    private Battlefield ground; //引用类型
    private Formation form; //引用类型
    private Lock lock;
    private Canvas mycanvas;
    private boolean living;
    public myThread(Creature creature_of_thread,Battlefield ground,Formation form,Lock lock,Canvas canvas)
    {
        this.ground = ground;
        creature_of_thread.getTheinfo();
        System.out.println("创建!");
        this.creature_of_thread = creature_of_thread;
        this.form = form;
        this.lock = lock; //锁
        this.mycanvas = canvas;
        this.living = true;
    }
    //重写方法
    @Override
    public void run() //作为进行传参
    {
            while(true)
            {
                if(this.creature_of_thread.is_Alive()) //如果存活，就进行实验
                {
                    try {
                        //移动
                        this.mov();
                        Thread.sleep(1000);
                        //check并且进攻
                        this.creature_of_thread.getTheinfo();
                        System.out.println();
                        Creature enemy = this.check();
                        if (enemy != null) {
                            this.attack(enemy);
                        } else {
                                    //System.err.println("指针为空");
                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace(); //打印出来
                        return;
                    }
                }
                else {
                    if (this.living == true) {
                        removeFromBattle();
                        this.living = false;
                        return;
                    }
                }
            }
    }

    private void removeFromBattle() {
        try {
            //攻击
            synchronized (this.ground) {
                this.ground.remove(this.creature_of_thread.getI(),this.creature_of_thread.getJ());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("remove error");
        }
    }

    private void attack(Creature enemy) {
        try {
            //攻击
            synchronized (this.creature_of_thread) {
                synchronized (enemy) {
                    this.creature_of_thread.attackEnemy(enemy);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("attack error");
        }
    }

    public void mov()
    {
        try {
            //访问临界区方法，移动
            synchronized(this.ground) {
                int x = creature_of_thread.want_to_mov_x();
                int y = creature_of_thread.want_to_mov_y();
                ground.movTheCre(this.creature_of_thread, x, y);
            }
        }
        catch (Exception e) {
            System.out.println("error_1");
        }
    }

    public Creature check()
    {
        Creature temp = null;
        try {
            //访问临界区方法，检查
            synchronized(this.ground) {
                temp = this.ground.checkEnemy(this.creature_of_thread.getI(),this.creature_of_thread.getJ()); //根据坐标获取
            }
        }
        catch (Exception e) {
           e.printStackTrace();
        }
        return  temp; //找到一个进攻的对象，如果没有就进行返回
    }
}
