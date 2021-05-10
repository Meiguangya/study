
/**
 * @author water33
 */
class Solution {

    /**
     * 猴子爬山
     // dp(n) = dp(n-1) + dp(n-3)
     // n= 1    1
     // n=2     1
     // n=3     2
     // 4       1+2
     * @param args
     */
    public static void main(String[] args) {
        int i = new Solution().countOfMethod(50);
        System.out.println(i);
    }

    public int countOfMethod(int n){
        if(n<=2){
            return 1;
        }
        return countOfMethod(n-1) + countOfMethod(n-3);
    }

}


