public class NinjaTraining {
    public static void main(String[] args) {
        int[][] points = {
                { 10, 40, 70 },
                { 20, 50, 80 },
                { 30, 60, 90 },
                { 40, 70, 100 }
        };
        int[][] dp = new int[points.length][3];
        System.out.println(ninjaMemoization(points, 3, 3, dp));
        System.out.println(ninjaTabulation(points));
        System.out.println(ninjaSpaceOptimisation(points));
    }

    static int ninjaRecursion(int[][] tasks, int day, int lastActivity) {
        int maxi = 0;
        for (int i = 0; i < 3; i++) {
            if (i != lastActivity) {
                if (day == 0) {
                    maxi = Math.max(maxi, tasks[0][i]);
                } else {
                    maxi = Math.max(maxi, tasks[day][i] + ninjaRecursion(tasks, day - 1, i));
                }
            }
        }
        return maxi;
    }

    static int ninjaMemoization(int[][] tasks, int day, int lastActivity, int[][] dp) {
        if (lastActivity != 3) {
            if (dp[day][lastActivity] != 0) {
                return dp[day][lastActivity];
            }
        }
        int maxi = 0;
        for (int i = 0; i < 3; i++) {
            if (i != lastActivity) {
                if (day == 0) {
                    maxi = Math.max(maxi, tasks[0][i]);
                } else {
                    maxi = Math.max(maxi, tasks[day][i] + ninjaMemoization(tasks, day - 1, i, dp));
                }
            }
        }
        if (lastActivity != 3) {
            dp[day][lastActivity] = maxi;
        }
        return maxi;
    }

    static int ninjaTabulation(int[][] tasks) {
        int[][] dp = new int[tasks.length][3];
        int n = tasks.length;
        dp[0][0] = Math.max(tasks[0][1], tasks[0][2]);
        dp[0][1] = Math.max(tasks[0][0], tasks[0][2]);
        dp[0][2] = Math.max(tasks[0][0], tasks[0][1]);
        for (int day = 1; day < n; day++) {
            for (int lastActivity = 0; lastActivity < 3; lastActivity++) {
                int maxi = 0;
                for (int i = 0; i < 3; i++) {
                    if (i != lastActivity) {
                        maxi = Math.max(maxi, tasks[day][i] + dp[day - 1][i]);
                    }
                }
                dp[day][lastActivity] = maxi; 
            }
        }
        return Math.max(dp[n-1][0], Math.max(dp[n-1][1], dp[n-1][2]));
    }
    static int ninjaSpaceOptimisation(int[][] tasks) {
        int[][] dp = new int[2][3];
        // 0th row is 2nd prev
        // 1st row is prev
        
        int n = tasks.length;
        dp[0][0] = Math.max(tasks[0][1], tasks[0][2]);
        dp[0][1] = Math.max(tasks[0][0], tasks[0][2]);
        dp[0][2] = Math.max(tasks[0][0], tasks[0][1]);
        for (int day = 1; day < n; day++) {
            for (int lastActivity = 0; lastActivity < 3; lastActivity++) {
                int maxi = 0;
                for (int i = 0; i < 3; i++) {
                    if (i != lastActivity) {
                        int prev = dp[0][i];
                        maxi = Math.max(maxi, tasks[day][i] + prev);
                    }
                }
                dp[1][lastActivity] = maxi;
            }
            for (int i = 0; i < 3; i++) {
                dp[0][i] = dp[1][i];
                dp[1][i] = 0;
            }
        }
        return Math.max(dp[0][0], Math.max(dp[0][1], dp[0][2]));
    }
}
