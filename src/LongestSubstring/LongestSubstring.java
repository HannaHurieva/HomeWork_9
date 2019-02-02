package LongestSubstring;

/*Longest Substring Without Repeating Characters
Given a string, find the length of the longest substring without repeating characters. */
public class LongestSubstring {
    public static void main(String[] args) {
        LongestSubstring find = new LongestSubstring();
        System.out.println(find.lengthOfLongestSubstring("abcabcbb"));
    }

    private int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int max = (length == 0 ? 0 : 1);
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                String subStr = s.substring(i, j);
                int size = subStr.length();
                String symbol = String.valueOf(s.charAt(j));
                int index = subStr.indexOf(symbol);
                if (index >= 0) {
                    i += index + 1;
                    max = max > (size) ? max : size;
                } else {
                    max = max > (size + 1) ? max : size + 1;
                }
            }
        }
        return max;
    }
}
