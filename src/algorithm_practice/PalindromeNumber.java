package algorithm_practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromeNumber {

    /**
     * 创建map集合存储数据然后对数据进行判断抛出
     */
    private Map<Integer, List<String>> palindromeData = new HashMap<>();

    /**
     * 讲数字直接转为字符串然后通过字符串中间截取用两个首尾进行对比得出
     */
    public boolean isPalindromeNumber(Long number) {
        //个位直接返回   小于100的回文数都是可以整除11 整除10的数绝对不是回文数
        if (number < 10) return true;
        if (number < 100) return number % 11 == 0;
        if (number % 10 == 0) return false;
        String numbers = number + "";
        /*三位数回文数例如：121 131 151等等都是前后相同则直接判断是否前后相同相同直接返回
         * 然后符合规则的数字放入一个key为5的map里面一个List集合中去
         * 当number为5为数字的时候判断第一个数根最后一个数是否相同
         * 相同则去除首尾取中间进行对比
         * 是否map中key为5的集合中是否含有余下的这三位数有则返回true反之flase
         * 其中返回前如果true则放入一个key为7的List中去进行递归字典处理
         * 偶数位的回文数与基数位的回文数处理类似所以不在讲解*/
        if (number > 100 && number <= 999) {
            if (number / 100 == number % 10) {
                palindromeData.computeIfAbsent(5, k -> new ArrayList<String>());
                palindromeData.get(5).add(numbers);
                return true;
            } else return false;
        }
        if (number >= 1001 && number <= 9999) {
            if (number / 1000 == number % 10 && number / 100 % 10 == number % 100 / 10) {
                palindromeData.computeIfAbsent(6, k -> new ArrayList<String>());
                palindromeData.get(6).add(numbers);
                return true;
            }
            return false;
        }
        String firstNumber = numbers.substring(0, 1),
                lastNumber = numbers.substring(numbers.length() - 1, numbers.length());
        if (!firstNumber.equals(lastNumber)) return false;
        int length = numbers.length();
        String subString = numbers.substring(1, length - 1);
        if (Integer.parseInt(subString) == 0) return true;
        return ismapData(numbers, length, number,subString);

    }

    private boolean baseIsPalindromeNumber(String numberFrist, String numbersLast, String numbersLast2) {
        for (int i = 0; i < numbersLast.length(); ) {
            int index = numbersLast.length() - i - 1;
            if (i == 0) {
                /*为了避免进行大数据类型遍历的加上第一位数的判断如果不相同直接返回以此减少每一次需要进行数据逆转的次数
                数据长度的记为n 则减少n/2-1次的判断次数
                */
                numbersLast2 = numbersLast2 + numbersLast.substring(index);
                if (!numberFrist.substring(0, 1).equals(numbersLast2)) return false;
            } else {
                numbersLast2 = numbersLast2 + numbersLast.substring(index, index + 1);
                if (index == 2 || index == 5 || index == 8) {
                    if (!numberFrist.substring(0, numbersLast2.length()).equals(numbersLast2)) return false;
                }
            }
            ++i;
        }
        return numberFrist.equals(numbersLast2);
    }

    private boolean olderIsPalindromeNumber(Long number) {
        //个位直接返回   小于100的回文数都是可以整除11 整除10的数绝对不是回文数
        // System.out.println(number+"num");
        if (number < 10) return true;
        if (number < 100) return number % 11 == 0;
        if (number % 10 == 0) return false;
        String numbers = number + "";
        //  System.out.println(numbers);
        /*System.out.println("number = [" + numbers.length() + "]");*/
        int halfIndex = numbers.length()/2;
        String  numberFrist = numbers.substring(0, halfIndex - 1),
                numbersLast = "", numbersLast2 = "";
        //对最后字符串截取需要注意的是字符串长度是否为偶数
        if (numbers.length() / 2 == 0 ){
            numbersLast= numbers.substring(halfIndex - 1);
        }else {
            numbersLast = numbers.substring(halfIndex+1);
        }

        // System.out.println(numberFrist+ "ssss   "+numbersLast);
        return baseIsPalindromeNumber(numberFrist, numbersLast, numbersLast2);
    }

    private boolean ismapData(String numbers, int length, long number, String subString) {
        if (palindromeData.get(length) != null) {
            //  System.out.println("notnull sub:"+numbers.substring(1,length-1));
            if (palindromeData.get(length).contains(subString)) {
                palindromeData.computeIfAbsent(length + 2, k -> new ArrayList<String>());
                palindromeData.get(length + 2).add(numbers);
                return true;
            } else {
                long next = (long) Math.pow(10,length-1) ,last = (long) Math.pow(10,length-2);
                if (number>(next+last+11)) return false;
                if (subIsPalindromeNumber(numbers)){
                    palindromeData.computeIfAbsent(length + 2, k -> new ArrayList<String>());
                    palindromeData.get(length + 2).add(numbers);
                    palindromeData.get(length).add(subString);
                    return true;
                }
                 return false;
            }
        } else {
            if (subIsPalindromeNumber(numbers)) {
                palindromeData.computeIfAbsent(length + 2, k -> new ArrayList<String>());
                palindromeData.get(length + 2).add(numbers);
                return true;
            } else return false;
        }
    }

    private boolean subIsPalindromeNumber(String numbers){
        int halfIndex = numbers.length()/2;
        String  numberFrist = numbers.substring(0, halfIndex - 1),
                numbersLast = "", numbersLast2 = "";
        //对最后字符串截取需要注意的是字符串长度是否为偶数
        if (numbers.length() / 2 == 0 ){
            numbersLast= numbers.substring(halfIndex );
        }else {
            numbersLast = numbers.substring(halfIndex);
        }
        return baseIsPalindromeNumber(numberFrist, numbersLast, numbersLast2);
    }
}
