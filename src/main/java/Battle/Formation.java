package Battle;

import Creatures.CalabashBrother;
import Creatures.Follower;
import Creatures.Scorpion;

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

public class Formation {

    public void changeFormation_mon(int index, Scorpion scorpion, Follower[]follower, Battlefield ground)
    {
        switch (index)
        {
            case 0: {
                ground.movTheCre(scorpion, 4, 10);
                ground.movTheCre(follower[0], 5, 11);
                ground.movTheCre(follower[1], 6, 12);
                ground.movTheCre(follower[2], 7, 13);
                ground.movTheCre(follower[3], 7, 14);
                ground.movTheCre(follower[4], 6, 15);
                ground.movTheCre(follower[5], 5, 16);
                ground.movTheCre(follower[6], 4, 17);
                break;
            }
            case 1:
            {
                ground.movTheCre(scorpion,2,17);
                ground.movTheCre(follower[0],3,16);
                ground.movTheCre(follower[1],4,15);
                ground.movTheCre(follower[2],5,14);
                ground.movTheCre(follower[3],6,13);
                ground.movTheCre(follower[4],7,12);
                ground.movTheCre(follower[5],8,11);
                ground.movTheCre(follower[6],9,10);
                break;
            }
            case 2:
            {
                ground.movTheCre(scorpion,1,12);
                ground.movTheCre(follower[0],2,13);
                ground.movTheCre(follower[1],3,12);
                ground.movTheCre(follower[2],4,13);
                ground.movTheCre(follower[3],5,12);
                ground.movTheCre(follower[4],6,13);
                ground.movTheCre(follower[5],7,12);
                ground.movTheCre(follower[6],8,13);
                break;
            }
            case 3:
            {
                ground.movTheCre(scorpion,1,12);
                ground.movTheCre(follower[0],2,13);
                ground.movTheCre(follower[1],3,12);
                ground.movTheCre(follower[2],4,13);
                ground.movTheCre(follower[3],5,12);
                ground.movTheCre(follower[4],6,13);
                ground.movTheCre(follower[5],7,12);
                ground.movTheCre(follower[6],8,13);
                break;
            }
            case 4:
            {
                ground.movTheCre(scorpion,4,13);
                ground.movTheCre(follower[0],8,13);
                ground.movTheCre(follower[1],5,14);
                ground.movTheCre(follower[2],6,11);
                ground.movTheCre(follower[3],6,13);
                ground.movTheCre(follower[4],6,15);
                ground.movTheCre(follower[5],7,12);
                ground.movTheCre(follower[6],7,14);
                break;
            }
            case 5:
            {
                ground.movTheCre(scorpion,3,13);
                ground.movTheCre(follower[0],4,12);
                ground.movTheCre(follower[1],4,14);
                ground.movTheCre(follower[2],5,11);
                ground.movTheCre(follower[3],5,15);
                ground.movTheCre(follower[4],6,12);
                ground.movTheCre(follower[5],6,14);
                ground.movTheCre(follower[6],7,13);
                break;
            }
            case 6:
            {
                ground.movTheCre(scorpion,2,15);
                ground.movTheCre(follower[0],3,14);
                ground.movTheCre(follower[1],4,13);
                ground.movTheCre(follower[2],5,12);
                ground.movTheCre(follower[3],6,12);
                ground.movTheCre(follower[4],7,13);
                ground.movTheCre(follower[5],8,14);
                ground.movTheCre(follower[6],9,15);
                break;
            }
            case 7:
            {
                ground.movTheCre(scorpion,4,13);
                ground.movTheCre(follower[0],5,12);
                ground.movTheCre(follower[1],5,13);
                ground.movTheCre(follower[2],5,14);
                ground.movTheCre(follower[3],6,11);
                ground.movTheCre(follower[4],6,13);
                ground.movTheCre(follower[5],6,15);
                ground.movTheCre(follower[6],7,13);
                break;
            }
            default:
                System.out.println("无法移动");
        }
    }
    public void changeFormation_Hulu(int index, CalabashBrother[] Huluwa,Battlefield ground)
    {
        int dis = 10;
        int up = 1;
        switch (index)
        {
                case 0: {
                    ground.movTheCre(Huluwa[0], 5 - up, 11-dis);
                    ground.movTheCre(Huluwa[1], 6- up, 12-dis);
                    ground.movTheCre(Huluwa[2], 7- up, 13-dis);
                    ground.movTheCre(Huluwa[3], 7- up, 14-dis);
                    ground.movTheCre(Huluwa[4], 6- up, 15-dis);
                    ground.movTheCre(Huluwa[5], 5- up, 16-dis);
                    ground.movTheCre(Huluwa[6], 4- up, 17-dis);
                    break;
                }
                case 1:
                {
                    ground.movTheCre(Huluwa[0],3- up,16-dis);
                    ground.movTheCre(Huluwa[1],4- up,15-dis);
                    ground.movTheCre(Huluwa[2],5- up,14-dis);
                    ground.movTheCre(Huluwa[3],6- up,13-dis);
                    ground.movTheCre(Huluwa[4],7- up,12-dis);
                    ground.movTheCre(Huluwa[5],8- up,11-dis);
                    ground.movTheCre(Huluwa[6],9- up,10-dis);
                    break;
                }
                case 2:
                {
                    ground.movTheCre(Huluwa[0],2- up,13-dis);
                    ground.movTheCre(Huluwa[1],3- up,12-dis);
                    ground.movTheCre(Huluwa[2],4- up,13-dis);
                    ground.movTheCre(Huluwa[3],5- up,12-dis);
                    ground.movTheCre(Huluwa[4],6- up,13-dis);
                    ground.movTheCre(Huluwa[5],7- up,12-dis);
                    ground.movTheCre(Huluwa[6],8- up,13-dis);
                    break;
                }
                case 3:
                {
                    ground.movTheCre(Huluwa[0],2- up,13-dis);
                    ground.movTheCre(Huluwa[1],3- up,12-dis);
                    ground.movTheCre(Huluwa[2],4- up,13-dis);
                    ground.movTheCre(Huluwa[3],5- up,12-dis);
                    ground.movTheCre(Huluwa[4],6- up,13-dis);
                    ground.movTheCre(Huluwa[5],7- up,12-dis);
                    ground.movTheCre(Huluwa[6],8- up,13-dis);
                    break;
                }
                case 4:
                {
                    ground.movTheCre(Huluwa[0],8- up,13-dis);
                    ground.movTheCre(Huluwa[1],5- up,14-dis);
                    ground.movTheCre(Huluwa[2],6- up,11-dis);
                    ground.movTheCre(Huluwa[3],6- up,13-dis);
                    ground.movTheCre(Huluwa[4],6- up,15-dis);
                    ground.movTheCre(Huluwa[5],7- up,12-dis);
                    ground.movTheCre(Huluwa[6],7- up,14-dis);
                    break;
                }
                case 5:
                {
                    ground.movTheCre(Huluwa[0],4- up,12-dis);
                    ground.movTheCre(Huluwa[1],4- up,14-dis);
                    ground.movTheCre(Huluwa[2],5- up,11-dis);
                    ground.movTheCre(Huluwa[3],5- up,15-dis);
                    ground.movTheCre(Huluwa[4],6- up,12-dis);
                    ground.movTheCre(Huluwa[5],6- up,14-dis);
                    ground.movTheCre(Huluwa[6],7- up,13-dis);
                    break;
                }
                case 6:
                {
                    ground.movTheCre(Huluwa[0],3- up,14-dis);
                    ground.movTheCre(Huluwa[1],4- up,13-dis);
                    ground.movTheCre(Huluwa[2],5- up,12-dis);
                    ground.movTheCre(Huluwa[3],6- up,12-dis);
                    ground.movTheCre(Huluwa[4],7- up,13-dis);
                    ground.movTheCre(Huluwa[5],8- up,14-dis);
                    ground.movTheCre(Huluwa[6],9- up,15-dis);
                    break;
                }
                case 7:
                {
                    ground.movTheCre(Huluwa[0],5- up,12-dis);
                    ground.movTheCre(Huluwa[1],5- up,13-dis);
                    ground.movTheCre(Huluwa[2],5- up,14-dis);
                    ground.movTheCre(Huluwa[3],6- up,11-dis);
                    ground.movTheCre(Huluwa[4],6- up,13-dis);
                    ground.movTheCre(Huluwa[5],6- up,15-dis);
                    ground.movTheCre(Huluwa[6],7- up,13-dis);
                    break;
                }
                default:
                    System.out.println("无法移动");
        }
    }
}