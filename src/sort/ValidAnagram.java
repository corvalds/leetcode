package sort;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with leetcode
 * Author: corvalds@gmail.com
 * Date: 2017/12/1
 * Time: 17:52
 * Description: TODO
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> mapS = new HashMap<>();
        Map<Character, Integer> mapT = new HashMap<>();
        boolean ret = true;

        if (s.length() != t.length())
            return false;
        for (Character c: s.toCharArray())
            mapS.put(c, mapS.getOrDefault(c, 0) + 1);
        for (Character c: t.toCharArray())
            mapT.put(c, mapT.getOrDefault(c, 0) + 1);
        for (Character c: mapS.keySet()) {
            int tmp = mapT.getOrDefault(c, 0);
            if (tmp == 0 || tmp != mapS.get(c)) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        String s = "rat", t = "cat";
        ValidAnagram v = new ValidAnagram();

        System.out.println(v.isAnagram(s, t));
    }
}
