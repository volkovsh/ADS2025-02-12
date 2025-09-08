package by.it.group410901.volkov.lesson08;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;
import java.util.stream.IntStream;

public class A_Knapsack {

    int getMaxWeight(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int W = scanner.nextInt();
        int n = scanner.nextInt();
        int[] gold = IntStream.range(0, n)
                .map(i -> scanner.nextInt())
                .toArray();

        BitSet achievable = new BitSet(W + 1);
        achievable.set(0); // 0 вес всегда достижим

        // Используем stream для обработки весов
        IntStream.rangeClosed(0, W)
                .filter(achievable::get)
                .forEach(w -> Arrays.stream(gold)
                        .filter(weight -> w + weight <= W)
                        .forEach(weight -> achievable.set(w + weight)));

        // Находим максимальный достижимый вес
        return achievable.previousSetBit(W);
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_Knapsack.class.getResourceAsStream("dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
