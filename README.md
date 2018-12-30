# 葫芦哇Version2.0更新说明

##### ①加入了技能，重新调整了游戏内机制，增加了概率触发技能②修改了平衡性③增加了生物状态

#### 一、每个Creature都独特了！！！！

每个生物继承了接口，复写了函数usingSkill()从而实现了技能的释放。

正义方：

|      | 特征                                       | 攻击力 | 防御力 | 技能                                     |
| ---- | ------------------------------------------ | ------ | ------ | ---------------------------------------- |
| 大娃 | 力大无比，攻击力高                         | 18     | 5      | 自身恢复5HP                              |
| 二娃 | 顺风耳，千里眼，攻击力高但是脆弱           | 20     | 0      | 自身恢复5HP                              |
| 三娃 | 铜墙铁壁，防御力高                         | 10     | 10     | 自身恢复5HP                              |
| 四娃 | 火的掌控者，能够驾驭火的力量，造成大量伤害 | 15     | 5      | 释放火焰！对周围敌人造成30HP的大量伤害！ |
| 五娃 | 水的掌控者，能够驾驭水的力量，造成大量伤害 | 15     | 5      | 释放水波！对周围敌人造成30HP伤害！       |
| 六娃 | 隐身的能力，消除敌人的怒气                 | 15     | 5      | 消除所有敌人的怒气一回合！               |
| 七娃 | 紫葫芦力量无限，能够造成大量伤害           | 15     | 5      | 对所有敌人造成10HP伤害！                 |
| 爷爷 | 精神支柱，能够为每个葫芦娃回血             | 20     | 0      | 对所有人恢复15HP！                       |

邪恶方：

|        | 特征                           | 攻击力 | 防御力 | 技能                     |
| ------ | ------------------------------ | ------ | ------ | ------------------------ |
| 蛇精   | 怪物领袖，能够为每个葫芦娃回血 | 20     | 0      | 对所有人恢复15HP！       |
| 蝎子精 | 战斗力强悍，能够释放毒气       | 20     | 10     | 对所有敌人造成10HP伤害！ |
| 小怪   | 平平无奇                       | 15     | 6      | 自身恢复5HP              |

#### 生物进程行为更新！每次都会有10%的概率释放技能，关键技能能够逆转战局，一转乾坤！

#### 二、增加了生物状态

对于每个生物增加了一个愤怒的状态，当生命值低于20%时，处于愤怒状态，头上冒火，同时战斗力大幅度提升。

当高于20%时，恢复正常状态。提高了趣味性。

![愤怒](/markdown/愤怒.png)

#### 三、战斗过程更加精彩

**以下是精彩的战斗场景：**![fight](/markdown/fight.gif)

最精彩的战斗记录在bestBattle.txt中

#### 四、更新单元测试

对战斗等使用了单元测试保证正确性

---

# 葫芦哇Version1.0

#### 161220097 戚赟

---

## 关键词

- 战斗

- JavaFx
- Maven
- 多线程
- 过程记录和过程复现

---

### 一、故事背景

​	狭路相逢勇者胜，葫芦兄弟和老爷爷在山洞中与众妖精决一死战！一断江湖，啊不，葫芦山恩怨！

​	在武侠的BGM下，葫芦娃和妖精进行搏斗，在刀光剑影中（特效），大地仿佛在颤抖，伴随刀剑声音，到底谁能最后一统江湖，啊不，葫芦山？

​	效果展示：BGM和战斗音效请脑补

![Animation](/markdown/Animation.gif)

---

### 二、类的设计

#### ①生物类的设计——没有人怎么打？

![生物类](/markdown/生物类.jpg)

​	我设计了一个**Creature**类，它是所有生物的父类，其中葫芦娃，爷爷是继承了生物体，而蝎子精，小怪，蛇精都是它们所有都归属于一个相同的类——**Monster**类，从而完成了所有类的设计，在逻辑伦理上容易实现，最后我利用了动态绑定的特征，就可以使用**Creature**来进行相同的操作但是复写了接口，从而实现了之后移动，攻击和技能（准备附加功能）的接口，在线程中保证了安全性。

​	同时**Creature**继承了**Fighting**接口，里面实现了大战的方法，这是在第三次作业上的迭代，让我体会到了JAVA继承，接口等机制的方便之处。

​	这样，我们就可以创造葫芦娃和妖精了！

```java
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
    //这些都是Creature的共有属性
}
```

​	通过这些共有属性，我们可以创建各种有画面有战斗音效的葫芦娃了！

#### ②葫芦娃的放置

​	葫芦娃怎么放置呢？我设计了一个战场类**Battlefield**，这个战场类中有**10*****20**大小的**Position**类，每个**Position**就是一个位置，能够作为一个容器（Holder），来存放一个**Creature**，这样我们就可以在战场中放置我们可爱的葫芦娃了。

```java
public class Battlefield {
    private int x; //宽
    private int y; //高
    private boolean war_start;
    private thePosition[][] field;
    public void ...//实现的函数
}
```

```JAVA
public class thePosition
{
    private Creature creature;
    private int i;
    private int j;
    private boolean empty;

    thePosition(int i,int j) {
        this.i = i;
        this.j = j;
        this.empty = true;
        creature = null;
    }
}
```

​	上述的理解设计是我在曹老板的启发下写的，对那句“葫芦娃每个站在一个荷叶上，通过荷叶的移动来改变位置”印象深刻，给了我很大的启发，让我明白一个战场不仅仅是一个二维数组那么简单，同时也是一个对象！万物皆对象。

#### ③葫芦娃的阵型选择

 	谁来指挥葫芦娃排好阵型？

​	万物皆对象，创建一个类告诉他们如何排就好了！

```java
enum FormationName {
    HEYI("鹤翼"),YANXING("雁行"),CHONGE("衝軛"),
    CHANGSHE("长蛇"), YULIN("鱼鳞"), FANG("方块"),
    YANYUE("偃月"), FENGSHI("锋矢");
    private String name;
    FormationName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
```

​	先用**enum**类型定义了所有阵型的属性名称，通过这些名称，我们就可以清楚的知道我们创建的类的名称和变量一一对应起来了！

```java
public class Formation {
    public void changeFormation_mon(int index, Scorpion scorpion, Follower[]follower, Battlefield ground)
    {
        switch (index)
        {
            case 0: ...；break;
            ...
            default: ;break;  
        }
     }
}
```

这个**Formation**类可以通过名称和下标让你控制排的阵型！所以通过这个接口来让我们的葫芦娃按照我们的阵型选择进行排序！

以上可以说是前三次代码的复用和拓展，那么如何实现本次的代码呢？

#### ④图形化：JAVAFX！

​	利用**javaFX**框架来写，进行编写，还可以用可视化的编辑器**SenceBuilder**进行编写，这样就可以编写出能够交互的可视化的界面

```java
public  class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
            Scene main_sence = new Scene(root); //创建一个新的Sence
            primaryStage.setTitle("qun's CalaBash_Brothers");
            primaryStage.setScene(main_sence);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

界面如下，虽然丑了点，但是简洁：

![格局](/markdown/格局.png)

利用**MyController**进行交互！

```java
public class MyController implements Initializable {

    @FXML private Canvas mycanvas;
    @FXML private MenuBar mymenu;
    @FXML private MenuItem change;
    @FXML private MenuItem init;
    ... //其他
}
```

​	**MyController**类中对于所有的变量我们都可以进行引用，比如利用**Canvas**来进行图片的绘制，利用**change**来实现整型的改变，利用**init**来实现战斗初始化的相应，加载等等方法，真的好用。利用**Mycanvas**还能够进行绘制出自己想要的东西。

### ⑤线程！让葫芦娃动起来！

​	在线程的设计中，我设计了两种线程，一个是**GUIrefresh**，这个线程是用来进行画面的刷新的函数，根据固定时间进行刷新战场，让葫芦娃看起来动起来了！因为Canvas的绘制不会覆盖，所以必须采用一个线程进行刷新。二是**myThread**继承了**Thread**方法，利用了**CRAP**原则，内存了一个**Creature**的引用类型，通过对每个**Creature**修改访问从而实现了葫芦娃的交互。同时还有一个战场的引用，来进行操作。

![打斗](/markdown/打斗.jpg)

葫芦娃就可以动起来了！

不过多线程**调的BUG**是最久的因为，它会不安全，造成死锁，让我很苦恼。

利用SYN锁实现同步，在访问共有变量，进行上锁，保证安全！

例如下面的**GUIrefresh**的**showBattleground()**方法，刷新的时候会对**ground**（公有战场）上锁保证了安全性。

```java
public  void showBattleground() {
    synchronized (this.ground) {
        this.mycanvas.getGraphicsContext2D().clearRect(0, 0, 1100, 600); //清空画布
        //画gezi
        this.mycanvas.getGraphicsContext2D().setStroke(Color.WHITE); //白线
        for (int i = 0; i <= 10; i++) {
            this.mycanvas.getGraphicsContext2D().strokeLine(0, i * 50, 1000, i * 50);
        }
        for (int j = 0; j <= 20; j++) {
            this.mycanvas.getGraphicsContext2D().strokeLine(j * 50, 0, j * 50, 500);
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                this.ground.showThecreature(i, j, this.mycanvas);
            }
        }
    }
}
```

除此以外，GUIrefresh会进行读取文件，进行复盘操作！

### ⑥回放接口

​	实现了**SAVE_action**和**SAVE_init**这两个类，从而根据这个类进行保存，下面具体讲了。

##### 以上就是我设计的主要类和接口，后面可能会进行迭代拓展！



### 二、战斗的设计

#### ①BGM和战斗音效

​	在BGM和战斗音效的烘托下，整个气氛变得武侠了起来。BGM为胡伟的偷功，战斗音效为刀剑声和皮鞭声，从而能够实现BGM，使好玩了起来。

#### ②打斗特效

​	如何看出谁掉血了？那就使用攻击特效来展示吧！

![打斗](/markdown/打斗.jpg)

​	可以看出葫芦娃可以向敌人攻击是喷射火焰的，妖怪是喷射水滴的，但是这样并不是很美观，在以后的版本会进行修改。还可能加入技能特效！

#### ③血量和死亡

​	葫芦娃会有血量条来反映当前的状态，同时死亡的葫芦娃会变成墓碑，**来实现死亡之后留下实体**。

#### ④如何移动？

​	设计了一个伪智能算法，我设计了一个检测敌人进行移动的算法，而不是随机或者向中间走动，保证了葫芦娃战斗是可以寻找敌人并且可以绕过墓碑的！让他看起来不那么傻！

​	但是还是有需要优化的地方，比如出现极低的概率情况，还没想好措施，以后会完善。

#### ⑤如何攻击？

​	生物会先检测周围的敌人，再进行攻击，攻击实现了接口，可以轻松写出，注意同步就OK

#### ⑥胜利！

会显示图片，虽然有点丑，以后会改进。

![胜利](/markdown/胜利.jpg)

#### ⑦回放

​	在战斗的时候，会先保存一个初始的位置的二维矩阵写入，然后根据每一帧的动作列表存放进一个ArrayList<Save_aciton>，SAVE_action保存了动作类型，动作起始坐标和动作结束坐标，从而实现了移动和攻击的统一，从某种意义上也是一种封装。最后写入结果。然后利用读取操作进行读取，保证了能够实现复盘操作。



### 三、期望的拓展(TODO)

- 技能的释放，每个人更加独立
- 打斗的特效，每个人的不同
- 会不会有额外的惊喜？（血瓶加血，攻击BUFF等等）
- 更加美观和更加容易实现
- 说明文档（帮助操作还没写，尴尬）

### 四、设计模式和原则

- ARP原则，不能利用多个方法改变
- SCRP原则，聚合方法来写线程
- LIP模式，父类型指针一定可以指向子类型
- 迭代器模式
- 适配器模式
- 工厂模式等

### 五、致谢！

​	虽然java考试让我有点绝望，但是看着我亲手写出来的葫芦娃还是有一种自豪感的！虽然压力也是很大，看着大佬们做的java也是很羡慕，但是自己码出来的代码宛如亲儿子怎么也好看！感谢曹春老师和余萍的教诲，曹春老师的讲课风格让我耳目一新。但是代码还是追求完美的过程，我会重新不断的改进我的代码并且逐步利用新的机制和设计模式，让我的葫芦娃更好看！

​	**最后再次感谢亲爱的曹春，余萍老师和辛苦的助教！**



#### 彩蛋：猜猜开头的战斗谁赢了？

​	当最后3个葫芦娃围剿蛇精的时候，我以为葫芦娃稳了，但是蛇精利用走位和高攻击低护甲的特性，逐个击破实现**反杀**！

![战斗结果](/markdown/caidan.gif)