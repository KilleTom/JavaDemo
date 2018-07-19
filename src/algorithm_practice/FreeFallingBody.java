package algorithm_practice;

import java.math.BigDecimal;

/*
一球从100米高度自由落下，每次落地后反跳回原高度的一半；再落下，
求它在第10次落地时，共经过多少米？第10次反弹多高
这里用BigDecimal避免精度丢失
*/
public class FreeFallingBody {

    public void tradition(){
        //传统算法 循环10次计算这里用BigDecimal避免精度丢失
        BigDecimal distance = new BigDecimal(100.0);
        BigDecimal height = new BigDecimal(100.0);
        BigDecimal result = null;
        BigDecimal percentage = new BigDecimal(2.0);
        for (int i = 0; i <10 ; i++) {
            height = height.divide(percentage);
            if (result == null) result = distance.add(height);
            else result = result.add(height);
            //System.out.println("distance" + result);
        }
        System.out.println( result);
    }

    /**
     * 这里用BigDecimal避免精度丢失
     * 以1为次数起点正向递归求第n次后自由落体后小球运动了多少距离
     * 自定义求第n次后自由落体后小球运动了多少距离
     * 以及逆向求第k次前的第n次之间自由落体后小球运动了多少距离
     * 递归逆向求运动距离时候distance第一次运动距离为 0
     * */
    public BigDecimal diyFreeFallingBody(int fallingTimes,int thisTimes,BigDecimal height,BigDecimal percentageHeight,BigDecimal distance  ){
        /**所求落体次数相同则直接返回高度*/
        if (thisTimes == fallingTimes) return distance.add(height.divide(percentageHeight));
        else if (thisTimes < fallingTimes){
            /**反复正向递归求下一次自由落体高度*/
            if (thisTimes == 1) distance = height;
            BigDecimal nextHeight = height.divide(percentageHeight);
            return diyFreeFallingBody(fallingTimes,thisTimes+1,nextHeight,percentageHeight,distance.add(nextHeight));
        }else if (thisTimes>fallingTimes+1){
            /**反复逆向递归求下一次自由落体高度*/
            BigDecimal lastHeight = height.multiply(percentageHeight);
            if (distance.intValue()==0) distance = height;
            System.out.println("distance" + distance);
            return diyFreeFallingBody(fallingTimes,thisTimes-1,
                    lastHeight,percentageHeight,distance.add(lastHeight));
        }else if (thisTimes ==fallingTimes+1){
            return distance.add(height.multiply(percentageHeight));
        }
        return new BigDecimal(0);
    }
}
