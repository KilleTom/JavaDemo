package algorithm_practice;


/*
古典问题：3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
分析：首先我们要明白题目的意思指的是每个月的兔子总对数；假设将兔子分为小中大三种，兔子从出生后三个月后每个月就会生出一对兔子，
那么我们假定第一个月的兔子为小兔子，第二个月为中兔子，第三个月之后就为大兔子，那么第一个月分别有1、0、0，第二个月分别为0、1、0，
第三个月分别为1、0、1，第四个月分别为,1、1、1，第五个月分别为2、1、2，第六个月分别为3、2、3，第七个月分别为5、3、5……
兔子总数分别为：1、1、2、3、5、8、13……
 
于是得出了一个规律，从第三个月起，后面的兔子总数都等于前面两个月的兔子总数之和，即为斐波那契数列。*/
public class RabbitNumber {

    private long rabbits = 1;
    private long lastSecondRabbits ,lastRabbits;

    /**
     * 遍历从第一个月到第n个月的兔子总数
     * */
    public void forEachMothsToRabbits(int moths){
        System.out.println(System.currentTimeMillis());
        for (int i = 1;i<= moths;i++) System.out.println("第"+i+"个月兔子数为"+getRabbits(i));
        System.out.println(System.currentTimeMillis());
    }

    /**
     * 获取当前月的兔子总数
     * */
    private long getRabbits(int moths){
        if (moths == 1 || moths==2 ) return rabbits=1;
        else if (moths == 3) return rabbits = 2;
        else{
            //初始化上一个月以及上两个月兔子数量
            if (lastRabbits == 0 && lastSecondRabbits ==0 ){
                lastSecondRabbits = getRabbits(moths-2);
                lastRabbits = getRabbits(moths-1);
            }
            //计算这一个返回的兔子数量
            rabbits= lastRabbits+lastSecondRabbits;
            /*让两个月的兔子数等于上一个月兔子数，让上个月等于这个月兔子数
            为了下一次计算（下月兔子数）更加高效快捷避免过冗余递归影响计算速率*/
            lastSecondRabbits = lastRabbits;
            lastRabbits = rabbits;
            return rabbits ;
        }
    }
}
