package Lesson5;

/**
 * GGCACCACG
 * ACGGCGGATACG
 *
 *  ---GGC--ACCACG
 *  AC-GGCGGAT-ACG
 *
 *  GGCAACG
 *
 *  ABCD
 *  ACDE
 *
 *  lcs(ABCD, ACDE) = A + lcs(BCD, CDE);
 *
 *  lcs(BCD, CDE) = max( lcs(CD, CDE), lcs(BCD, DE) );
 */
public class LongestCommonSubsequence {

    public static StringBuilder recursive(String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        if (str1.isEmpty() || str2.isEmpty()) {
            return sb;
        }
        if (str1.charAt(0) == str2.charAt(0)) {
            sb.append(str1.charAt(0));
            return sb.append(recursive(str1.substring(1), str2.substring(1)));
        }
        StringBuilder first = recursive(str1.substring(1), str2);
        StringBuilder second = recursive(str1, str2.substring(1));
        return sb.append(first.length() > second.length() ? first : second);
    }

    public static String dynamic(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        int[][] opt = new int[m+1][n+1];
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    opt[i][j] = opt[i+1][j+1] + 1;
                }
                else {
                    opt[i][j] = Math.max(opt[i+1][j], opt[i][j+1]);
                }
            }
        }

        // Recover LCS itself.
        StringBuilder lcs = new StringBuilder();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (str1.charAt(i) == str2.charAt(j)) {
                lcs.append(str1.charAt(i));
                i++;
                j++;
            }
            else if (opt[i+1][j] >= opt[i][j+1]) {
                i++;
            }
            else {
                j++;
            }
        }
        return lcs.toString();
    }

    public static void main(String[] args) {
        System.out.println(recursive("GGCACCACG", "ACGGCGGATACG"));
    }
}
