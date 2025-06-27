public class FrogJump {
    public static void main(String[] args) {
        int[] arr = { 2, 1, 3, 5, 4 };
        int[] currEnergy = { 0 };
        int[] minEnergy = { Integer.MAX_VALUE };
        jumpRecursion(0, arr, currEnergy, minEnergy);
        System.out.println(minEnergy[0]);
        int[] dp = new int[arr.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        System.out.println(jumpRecursionStriver(4, arr));
        System.out.println(jumpRecursionStriverMemoization(4, arr, dp));
    }

    static void jumpRecursion(int index, int[] arr, int[] currEnergy, int[] minEnergy) {
        if (index == arr.length - 1) {
            minEnergy[0] = Math.min(minEnergy[0], currEnergy[0]);
            return;
        }
        currEnergy[0] += Math.abs(arr[index + 1] - arr[index]);
        jumpRecursion(index + 1, arr, currEnergy, minEnergy);
        currEnergy[0] -= Math.abs(arr[index + 1] - arr[index]);
        if (index + 2 < arr.length) {
            currEnergy[0] += Math.abs(arr[index + 2] - arr[index]);
            jumpRecursion(index + 2, arr, currEnergy, minEnergy);
            currEnergy[0] -= Math.abs(arr[index + 2] - arr[index]);
        }
    }

    static int jumpRecursionStriver(int index, int[] arr) {
        if (index == 0) {
            return 0;
        }
        int left = jumpRecursionStriver(index - 1, arr) + Math.abs(arr[index] - arr[index - 1]);
        int right = (index > 1) ? jumpRecursionStriver(index - 2, arr) + Math.abs(arr[index] - arr[index - 2])
                : Integer.MAX_VALUE;
        return Math.min(left, right);
    }

    static int jumpRecursionStriverMemoization(int index, int[] arr, int[] dp) {
        if(index == 0){
            return 0;
        }
        if(dp[index] != -1){
            return dp[index];
        }
        int left = jumpRecursionStriverMemoization(index-1, arr, dp) + Math.abs(arr[index] - arr[index-1]);
        int right = index > 1 ? jumpRecursionStriverMemoization(index-2, arr, dp) + Math.abs(arr[index] - arr[index-2]) : Integer.MAX_VALUE;
        dp[index] = Math.min(left, right);
        return dp[index];
    }
    static int jumpTabulationStriver(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            int fs = dp[i-1] + Math.abs(arr[i-1] - arr[i]);
            int ss = i > 1 ? dp[i-2] + Math.abs(arr[i-2] - arr[i]) : Integer.MAX_VALUE;
            dp[i] = Math.min(fs, ss);
        }
        return dp[n-1];
    }
    static int jumpOptimasationStriver(int[] arr){
        int n = arr.length;
        int prev1 = 0;
        int prev2 = 0;
        for (int i = 1; i < n; i++) {
            int fs = prev1 + Math.abs(arr[i-1] - arr[i]);
            int ss = i > 1 ? prev2 + Math.abs(arr[i-2] - arr[i]) : Integer.MAX_VALUE;
            int curr = Math.min(fs, ss);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
