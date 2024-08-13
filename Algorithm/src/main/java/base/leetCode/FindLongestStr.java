package base.leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 双指针滑动窗口解决此问题
 * @Author shxl
 * @Date 2024/7/29 21:56
 * @Version 1.0
 */
public class FindLongestStr {


    /**
     * 使用双指针花窗解决
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTemplate(String s) {
        int number = 0;
        if (s == null || s.isEmpty()) {
            return number;
        }

        Set<Character> hasSet = new HashSet<>();

        char[] charArray = s.toCharArray();
        int rk = 0;
        for (int i = 0; i < charArray.length; i++) {
            while (rk < s.length() && !hasSet.contains(charArray[rk ])) {
                hasSet.add(charArray[rk]);
                rk++;
            }
            number = Math.max(number,rk - i );
            hasSet.remove(charArray[i]);
        }

        return number;
    }

    public int lengthOfLongestSubstring(String s) {
        int number = 0;
        if (s == null || s.isEmpty()) {
            return number;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        char[] charArray = s.toCharArray();
        int rk = 0;
        for (int i = 0; i < charArray.length; i = map.get(charArray[rk])== null?i+1:rk) {
            while (rk < s.length() && map.get(charArray[rk ])!= null) {
                map.put(charArray[rk],rk);
                rk++;
            }
            number = Math.max(number,rk - i );
            map.remove(charArray[i]);
        }

        return number;
    }

    public static void main(String[] args) {
        FindLongestStr findLongestStr = new FindLongestStr();
        int i = findLongestStr.lengthOfLongestSubstring("pwwkew");
        System.out.println("i = " + i);
    }
}
