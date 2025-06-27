public class HouseRobberOne {
    public static void main(String[] args) {
        int[] arr = {2,1,4,9};
        System.out.println(robberRecursion(arr, 3));
    }
    static int robberRecursion(int[] arr, int i){
        if(i == 0){
            return arr[i];
        }
        if(i < 0){
            return 0;
        }
        int pick = arr[i] + robberRecursion(arr, i-2);
        int notPick = robberRecursion(arr, i-1);
        return Math.max(pick, notPick);
    }
    static int robberRecursionMemoization(int[] arr, int i, int[] dp){
        if(i == 0){
            dp[0] = arr[i];
            return arr[i];
        }
        if(dp[i] != -1){
            return dp[i];
        }
        if(i < 0){
            return 0;
        }
        int pick = arr[i] + robberRecursionMemoization(arr, i-2, dp);
        int notPick = robberRecursionMemoization(arr, i-1, dp);
        dp[i] = Math.max(pick, notPick);
        return dp[i];
    }
    static int robberRecursionMemoization2(int[] arr, int i, int[] dp){
        if(i == arr.length - 1){
            return arr[arr.length-1];
        }
        if(i > arr.length - 1){
            return 0;
        }
        if(dp[i] != -1){
            return dp[i];
        }
        int pick = arr[i] + robberRecursionMemoization2(arr, i+2, dp);
        int notPick = robberRecursionMemoization2(arr, i + 1, dp);
        dp[i] = Math.max(pick, notPick);
        return dp[i];
    }
    static int robberTabulation(int[] arr, int[] dp){
        int n = arr.length;
        dp[0] = arr[0];
        for(int i = 1; i < n; i++){
            int notPick = dp[i-1];
            int pick = (i <= 1 ? 0: dp[i-2]) + arr[i];
            dp[i] = Math.max(notPick, pick);
        }
        return dp[n-1];
    }
    static int robberSpaceOptimisation(int[] arr){
        int n = arr.length;
        int prev = arr[0], secondPrev = 0;
        for (int i = 1; i < n; i++) {
            int notPick = prev;
            int pick = arr[i] + secondPrev;
            int curr = Math.max(pick, notPick);
            secondPrev = prev;
            prev = curr;
        }
        return prev;
    }
}
