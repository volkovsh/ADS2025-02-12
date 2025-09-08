package by.it.group410901.volkov.lesson08;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_Knapsack {

    int getMaxWeight(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int W = scanner.nextInt();  // вместимость рюкзака
        int n = scanner.nextInt();  // количество слитков
        int[] gold = new int[n];    // веса слитков

        for (int i = 0; i < n; i++) {
            gold[i] = scanner.nextInt();
        }

        // dp[w] - максимальный вес для рюкзака вместимостью w
        int[] dp = new int[W + 1];

        for (int i = 0; i < n; i++) {
            // Идем справа налево чтобы избежать повторного использования слитков
            for (int w = W; w >= gold[i]; w--) {
                if (dp[w - gold[i]] + gold[i] > dp[w]) {
                    dp[w] = dp[w - gold[i]] + gold[i];
                }
            }
        }

        return dp[W];
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_Knapsack.class.getResourceAsStream("dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }
}