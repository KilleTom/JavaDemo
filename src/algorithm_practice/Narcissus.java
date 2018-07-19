package algorithm_practice;

/*
打印出所有的“水仙花数”，所谓“水仙花数”是指一个三位数，其各位
数字立方和等于该数本身。例如：153是一个“水仙花数”，因为153=1的三次方
＋5的三次方＋3的三次方
由于100 200 300 500 400 整百数都不是水仙花可以直接排除
而且 101 为基数的也不是水仙花可以直接排除
这样排除这两个规律的直接跳过计算
*/
public class Narcissus {

    /*
    开始运行时间1524629170834
    153
    370
    371
    407
    结束运行时间1524629170838
    耗时：4
    因为有18个跳过计算立方的方法避免了计算步骤的冗余所以运行更快了
    */
    public void printNarcissus() {
        for (int i = 102; i < 999; i++) {
            if (i % 100 == 0 || i % 101 == 0) continue;
            int j = i / 100, k = i / 10 % 10, z = i % 10;
            if (i == Math.pow(j, 3) + Math.pow(k, 3) + Math.pow(z, 3))
                System.out.println(i);
        }
    }

    /**
     * 传统算法运行结果
     * <p>
     * 开始运行时间1524629049929
     * 水仙花数是：153
     * 水仙花数是：370
     * 水仙花数是：371
     * 水仙花数是：407
     * 结束运行时间1524629049949
     * 耗时：21
     */
    public void ss() {
        for (int number = 100; number <= 999; number++) {
            int gewei = number % 10;
            int shiwei = number / 10 % 10;
            int baiwei = number / 100 % 10;
            if (gewei * gewei * gewei + shiwei * shiwei * shiwei + baiwei * baiwei * baiwei == number) {
                System.out.println("水仙花数是：" + number);
            }
        }
    }
}
