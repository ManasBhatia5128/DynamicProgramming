// import java.util.Arrays;

public class LecOneFibonacci{
    public static void main(String[] args) {
        int n = 6;
        int[] dp = new int[n+1];
        /* for(int elem: dp){ // doesn't work, you can only retrieve values using forEach loop
            elem = -1;
        } */
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        // dp[0] = 0;
        // dp[1] = 1;
        System.out.println(fibonacciTabulation(n, dp));
        // System.out.println(Arrays.toString(dp));
    }
    static int fibonacci(int n, int[] dp){
        if(n <= 1){
            dp[n] = n;
            return dp[n];
        }
        if(dp[n] != -1){
            return dp[n];
        }
        dp[n] = fibonacci(n-1, dp) + fibonacci(n-2, dp);
        return dp[n];
    }
    static int fibonacciTabulation(int n, int[] dp){
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    static int fibonacciOptimised(int n){
        int prev2 = 0;
        int prev = 1;
        for(int i = 2; i <= n; i++){
            int curr = prev2 + prev;
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }
}