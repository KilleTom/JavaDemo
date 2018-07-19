package algorithm_practice;
/*
原问题
猴子分桃：海滩上有一堆桃子，有五只猴子来分。
第一只猴子把这堆桃子平分为五份，多了一个，这只猴子把多的一个扔入海中，拿走了一份。
第二只猴子把剩下的桃子又平分成五份，又多了一个，它同样把多的一个扔入海中，拿走了一份，
第三、第四、第五只猴子都是这样做的，
问：
海滩上原来最少有多少个桃子
拓展问题
猴子分桃：海滩上有一堆桃子，有N只猴子来分。
第一只猴子把这堆桃子平分为N份，多了K个，这只猴子把多的一个扔入海中，拿走了一份。
第二只猴子把剩下的桃子又平分成N份，又多了K个，它同样把多的K个扔入海中，拿走了一份，
  一直到最后一只猴子也是这么做
问：
海滩上原来最少有多少个桃子
第一个传统问题首先最后剩下最小应该是6个，以6为最开始的基地进行逆序递归求出最小桃子数当不满足条件的时候直接让桃子数加5
拓展问题解决方案剩下最小应该是N+K个，以此进行逆序递归求最小桃子数当不满足条件的时候直接让桃子数加N
*/
public class MonkeyPeach {
    //传统猴子分桃
    public  int get(int times ,int sum ,int lastSum){
        //0则返回
        if (times == 0) {
            return sum;
        }else if (times == 5) {
            //重新开始计算判断是否要重置数据
            if ((sum-1)%5==0&&sum>=6) {
                return get(times-1, sum, (sum-1)/5*4);
            }else {
                return get(5, 6, 0);
            }
        }else {
            //计算是否满足这一次分桃
            if ((lastSum-1)%5==0&&lastSum>6) {
                return get(times-1, sum, (lastSum-1)/5*4);
            }else return get(5,sum+5,0);
        }
    }
    //自定义猴子分桃包括猴子数量以及每一次遗留下相同的桃子数量
    public  int get(int monkeys,int loseSimpleNum){
        int minNum = monkeys+loseSimpleNum;
        return getDiy(monkeys, minNum, 0, loseSimpleNum, monkeys);
    }

    public  int getDiy(int times ,int sum ,int lastNum,int loseSimpleNum,int monkeys) {
        int minNum = monkeys+loseSimpleNum;
        int nextmonkeys = monkeys-1;
        /* System.out.println(times+"sum:"+sum+"\nlastNum:"+lastNum);*/
        //0则返回
        if (times == 0) {
            return sum;
        }else if (times == monkeys) {
            //重新开始计算判断是否要重置数据
            if ((sum-loseSimpleNum)%monkeys==0&&sum>=minNum) {
                //System.out.println("replare");
                return getDiy(times-1, sum, (sum-loseSimpleNum)/monkeys*nextmonkeys,loseSimpleNum,monkeys);
            }else {
                //System.out.println("replare222");
                return getDiy(times, minNum, 0,loseSimpleNum,monkeys);
            }
        }else {
            //计算是否满足这一次分桃
            if ((lastNum-loseSimpleNum)%monkeys==0&&lastNum>minNum) {
                //System.out.println("nest");
                return getDiy(times-1, sum, (lastNum-loseSimpleNum)/monkeys*nextmonkeys,loseSimpleNum,monkeys);
            }else return getDiy(monkeys, sum+monkeys, 0,loseSimpleNum,monkeys);
        }
    }
}
