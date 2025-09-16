package by.it.group410902.vidilin.lesson06;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_LongDivComSubSeq.class.getResourceAsStream("dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        List<Integer> tails = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int num = m[i];

            // Binary search to find the largest tail <= current number
            int j = 0, k = tails.size();
            while (j != k) {
                int mid = (j + k) / 2;
                if (tails.get(mid) >= num) {
                    j = mid + 1;
                } else {
                    k = mid;
                }
            }

            if (j == tails.size()) {
                tails.add(num);
                indices.add(i);
            } else {
                tails.set(j, num);
                indices.set(j, i);
            }
        }

        int result = tails.size();
        System.out.println(result);

        //Construct path
        List<Integer> path = new ArrayList<>();
        int current_index = indices.get(indices.size()-1);
        path.add(current_index + 1);
        int current_value = m[current_index];

        for(int i= indices.size()-2; i>=0; i--){
            int test_index = indices.get(i);

            if(m[test_index] >= current_value){
                continue;
            }

            if(test_index < current_index){
                current_index = test_index;
                path.add(current_index+1);
                current_value = m[current_index];
            }
        }


        for (int i = path.size()-1; i >=0; i--) {
            System.out.print(path.get(i) + " ");
        }
        System.out.println();


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

}