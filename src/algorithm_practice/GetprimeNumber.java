package algorithm_practice;

import java.util.ArrayList;
import java.util.List;

public class GetprimeNumber {

    /*
     * 获取[1,n]区间素数*/
    public List<Integer> forEachNumberGetprime(int number) {
        List<Integer> integers = new ArrayList<>();
        for (int i = 1; i < number; i++) if (isPrimeNumber(2, i)) integers.add(i);
        return integers;
    }

    /**
     * 获取从指定区间的素数
     */
    public List<Integer> forEachNumberGetprimeToSpecifiedPosition(int startPosition, int endPosition) {
        List<Integer> integers = new ArrayList<>();
        for (int i = startPosition; i < endPosition; i++) if (isPrimeNumber(2, i)) integers.add(i);
        return integers;
    }

    /**
     * 打印从1到N的素数
     */
    public void forEachPrintNumberGetprime(int number) {
        for (int i = 1; i < number; i++) if (isPrimeNumber(2, i)) System.out.println("number = [" + i + "]是素数");
    }

    /**
     * 打印从指定区间的素数
     */
    public void forEachPrintNumberGetprimeToSpecifiedPosition(int startPosition, int endPosition) {
        for (int i = startPosition; i < endPosition; i++)
            if (isPrimeNumber(2, i)) System.out.println("number = [" + i + "]是素数");
    }

    /**
     * 因为不管怎么计算由于非素数数都可以通过1·9中通过乘计算得出所以除了1和2只需要继续是否可以被2-9整除就可以
     * 这一说法利用了提取最小公因式来计算得出
     * 当然要避免一个重要问题就是当它是个位数字的时候也就是1 、 2 、 3 、 5 、7的时候所以直接返回20以内素数
     * 这样计算的好处在于避免了传统递归从1到n的反复计算更加高效的计算出素数面对千位以上的数据使用
     *  也避免了过多使用这一算法（冗余重复性计算）的：判断素数的方法：用一个数分别去除2到sqrt(这个数的平方根)，如果能被整除， 则表明此数不是素数，反之是素数这一种算法更加快捷
     * 避免了重复计算的冗余
     */
    public boolean isPrimeNumber(int divisor, int number) {
        if (number % divisor == 0) return false;
        else if (number == 1 || number == 2 || number == 3 || number == 5 || number == 7
                || number == 11 || number == 13 || number == 17 || number == 19) return true;
        else if (number <= 20) return false;
        else if (divisor == 9) {
            return isPrimeNumber(11, divisor);
        } else if (divisor > 9) {
            if (divisor < Math.sqrt(number)) {
                return isPrimeNumber(divisor + 1, number);
            } else if (divisor == Math.sqrt(number)) return false;
            else return true;
        }
        return isPrimeNumber(divisor + 1, number);
    }
}
