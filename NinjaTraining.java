public class NinjaTraining {
    public static void main(String[] args) {
        int[][] points = {
                { 10, 40, 70 },
                { 20, 50, 80 },
                { 30, 60, 90 },
                { 40, 70, 100 }
        };
        System.out.println(ninjaRecursion(points, 3, 3));
    }

    static int ninjaRecursion(int[][] tasks, int day, int lastActivity) {
        int maxi = 0;
        for (int i = 0; i < 3; i++) {
            if (day == 0) {
                maxi = Math.max(maxi, tasks[0][i]);
            } else {
                maxi = Math.max(maxi, tasks[day][i] + ninjaRecursion(tasks, day - 1, i));
            }
        }
        return maxi;
    }
}
