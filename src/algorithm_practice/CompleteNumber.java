package algorithm_practice;
/*一个数如果恰好等于它的因子之和，这个数就称为 "完数 "。*/
public class CompleteNumber {

    private int firstFactorNumber;
    /**
     * 因为不管怎么计算由于非素数数都可以通过1·9中通过乘计算得出所以除了1和2只需要继续是否可以被2-9整除就可以
     * 这一说法利用了提取最小公因式来计算得出
     * 当然要避免一个重要问题就是当它是个位数字的时候也就是1 、 2 、 3 、 5 、7的时候直接返回
     * 这样计算的好处在于避免了传统递归从1到n的反复计算更加高效的计算出素数面对千位以上的数据使用
     * 也避免了过多使用这一算法（冗余重复性计算）的：
     * 判断素数的方法：用一个数分别去除2到sqrt(这个数的平方根)，如果能被整除， 则表明此数不是素数，反之是素数这一种算法更加快捷
     * 避免了重复计算的冗余
     */
    public boolean isPrimeNumber(int divisor, int number) {
        if (number % divisor == 0){
            firstFactorNumber = divisor;
            return false;
        }
        if (number == 1 || number == 2 || number == 3 || number == 5 || number == 7
                || number == 11 || number == 13 || number == 17 || number == 19) return true;
        else if (number <= 20){
            firstFactorNumber =1;
            return false;
        }
        else if (divisor == 9) {
            return isPrimeNumber(11, divisor);
        } else if (divisor > 9) {
            if (divisor < Math.sqrt(number)) {
                return isPrimeNumber(divisor + 1, number);
            } else if (divisor == Math.sqrt(number)){
                firstFactorNumber = divisor;
                return false;
            }
            else return true;
        }
        return isPrimeNumber(divisor + 1, number);
    }

    /*调用优化算法*/
    public void isCompleteNumber(int number) {
        if (number < 6) {
            System.out.println("从1到" + number + "没有完全数");
        } else {
            for (int i = 6; i <= number; i++) {
                /*素数不可能是完全数所以可以计算直接跳过
                * 每一次计算判断素数的时候如果不是通过计算
                * 是否为素数出的第一个因数+1为起始点进行计算避免起点重复计算*/
                if (isPrimeNumber(2,number)) continue;
                int sum = 0;
                if (firstFactorNumber>1) sum+=1+firstFactorNumber;
                else if (firstFactorNumber==1) sum+=1;
                for (int j = firstFactorNumber+1; j < i; j++) {
                    if (i % j == 0) {
                        sum = sum + j;
                    }
                }
                if (sum == i) {
                    System.out.println(i);
                }
            }
        }
    }

    /*传统做法*/
    public void tradition(){
        int s;
        for (int i = 1; i <= 1000000; i++) {
            s = 0;
            for (int j = 1; j < i; j++)
                if (i % j == 0)
                    s = s + j;
            if (s == i) System.out.print(i + " ");
        }
        System.out.println();
    }
}
