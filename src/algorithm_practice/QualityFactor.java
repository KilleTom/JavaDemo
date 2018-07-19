package algorithm_practice;

/*
每个非素数（合数）都可以写成几个素数（也可称为质数）相乘的形式，这几个素数就都叫做这个合数的质因数。
比如，6可以被分解为2x3，而24可以被分解为2x2x2x3。
现在，你的程序要读入一个[2,100000]范围内的整数，然后输出它的质因数分解式；当读到的就是素数时，输出它本身
*/
public class QualityFactor {

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

    public void getQualityFactor(int number) {
        String out = number + "=";
        if (isPrimeNumber(2, number)) out = out + number;
        else {
            while (number != 1) {
                for (int j = 2; j <= number; j++) {
                    /*如果每一次Number都能整除j则让Number/=j*/
                    if (number % j == 0) {
                        number /= j;
                        /*整除完判断是否是素数这样就避免了最后剩下一个比较大的素数然后还要进行计算重复计算*/
                        if (number!=1){
                            out += j + "x";
                            if (isPrimeNumber(2,number)){
                                out +=  number;
                                number = 1;
                            }
                        }else out +=  j ;

                        break;
                    }
                }
            }
        }
        System.out.println(out);
    }
}
