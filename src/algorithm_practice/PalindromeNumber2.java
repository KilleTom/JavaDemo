package algorithm_practice;

public class PalindromeNumber2 {

    /*生成一位数字的回文数*/
    private void adigit(long parentNumber, long tenID) {
        long tenIDResult = (long) Math.pow(10, tenID);
        for (int i = 0; i <= 9; i++) {
            System.out.println((long) (parentNumber + (i * tenIDResult)));
        }
    }

    /*生成两位数字*/
    private void twodigit(long parentNumber, long tenID, boolean iszero) {
        int i = 0;
        if (iszero) i = 0;
        else i = 1;
        long tenIDResult = (long) Math.pow(10, tenID);
        for (; i <= 9; i++) {
            System.out.println((long) (parentNumber + 11 * i * tenIDResult));
        }
    }

    /*生成三位数字*/
    private void threedigit(long parentNumber, long tenLID, boolean isThree, long tenID) {
        long i = 1;
        if (!isThree) i = 0;
        for (; i <= 9; ) {
            long result = parentNumber;
            if (isThree) {
                adigit((long) (i * Math.pow(10, 2) + i), tenID);
            } else {
                result += i * Math.pow(10, 2 * tenID - tenLID) + i * Math.pow(10, tenLID);
                //    System.out.println("3位数"+i+"p...."+result+"i>>>>>"+i);
                adigit(result, tenID);
            }
            ++i;
        }
    }

    /*生成四位数字位数字*/
    private void fourdigit(long parentNumber, long tenFID, long tenLID, boolean isfour, long tenID) {
        long i = 1;
        if (!isfour) i = 0;
        for (; i <= 9; i++) {
            if (isfour) {
                twodigit((long) (i * Math.pow(10, 3) + i), tenID, true);
            } else {
                twodigit((long) (i * Math.pow(10, tenFID) + i * Math.pow(10, tenLID) + parentNumber), tenID - 1, true);
            }
        }
    }

    /*指定从个位数字生成到n位数字间的回文数字打印*/
    public void PalindromeNumber(long n) {
        if (n <= 0) return;
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                adigit(0, 0);
                continue;
            } else if (i == 2) {
                twodigit(0, 0, false);
                continue;
            } else if (i == 3) {
                threedigit(0, 0, true, 1);
                continue;
            } else if (i == 4) {
                fourdigit(0, 0, 0, true, 1);
                continue;
            }
            oddDigitPalindromeNumber(i, 0, false, 0, 0);
        }
    }

    public void palindromeNumber(long n) {
        for (int i = 1; i < n; i++) {
            PalindromeNumber(n);
        }
    }


    public void oddDigitPalindromeNumber(long n, long tenId, boolean isZero, long PN, long lastTenId) {
        int i = 0;
        /*判断开头是否是0*/
        if (!isZero) {
            tenId = n / 2;
            i = 1;
        }
        /*大于及等于5以上奇数位回文数生成方法*/
        if (n == 3) {
            //  System.out.println("nT"+nextTenId);
            threedigit(PN, lastTenId, false, tenId);
            return;
        }
        /*大于及等于6以上偶数位回文数生成方法*/
        else if (n == 4) {
            //  System.out.println("lId:::"+(2*tenId-lastTenId-1));
            fourdigit(PN, 2 * tenId - lastTenId - 1, lastTenId, false, tenId);
            return;
        }
        for (; i <= 9; ) {
            long result = PN;
            if (!isZero) {
                PN = (long) (i * Math.pow(10, n - 1) + i);
                result = PN;
                lastTenId = 0;
            } else {
                result += (long) (i * Math.pow(10, 2 * tenId - lastTenId) + i * Math.pow(10, lastTenId));
                //  System.out.println("res"+result);
            }
            //if (!isZero) System.out.println("i"+i);
            ++i;
            oddDigitPalindromeNumber(n - 2, tenId, true, result, lastTenId + 1);
        }
    }

    /*大于及等于6以上奇数位回文数*/

    public void fire() {
        oddDigitPalindromeNumber(15, 0, false, 0, 0);
    }
}
