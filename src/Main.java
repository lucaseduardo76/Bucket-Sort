import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] arr = {42, 75, 86, 23, 57, 68, 92, 31, 44, 10, 53, 84, 99, 36, 18, 27, 65, 12, 80, 40, 70, 91, 25, 38, 61, 47, 55, 21, 73, 59};
        //int[] arr = {10,5 ,6 ,9 ,7, 8, 2, 1, 3, 4};


        bucketSort(arr);

        System.out.println("Array ordenado é: ");
        for (int el : arr) {
            System.out.print(el + " ");
        }
    }

    private static int findMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int num : arr) {
            if (num > maxValue) {
                maxValue = num;
            }
        }
        return maxValue;
    }

    public static void bucketSort(int[] arr) {
        if (arr.length <= 0) return;

        // 1) Encontrar o valor máximo no array
        int maxValue = findMaxValue(arr);

        // Definir o número de baldes com base no tamanho do array (por exemplo, sqrt(arr.length))
        int bucketCount = (int) Math.sqrt(arr.length);
        if (bucketCount == 0) {
            bucketCount = 1;
        }

        // 2) Cria baldes vazios
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // 3) Distribuir os elementos pelos baldes
        for (int num : arr) {
            int bucketIndex = (num * bucketCount) / (maxValue + 1);
            buckets.get(bucketIndex).add(num);
        }

        printBuckets(buckets, "DESORDENADO");

        // 4) Ordenar os elementos em cada balde
        for (List<Integer> bucket : buckets) {

            insertionSort(bucket);
        }

        printRows();

        printBuckets(buckets, "ORDENADO");

        printRows();

        // 5) Concatenar todos os baldes ordenados de volta no array original
        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                arr[index++] = num;
            }
        }
    }


    private static void insertionSort(List<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int key = bucket.get(i);
            int j = i - 1;

            // Mover elementos do bucket[0..i-1], que são maiores que key,
            // para uma posição à frente de sua posição atual
            while (j >= 0 && bucket.get(j) > key) {
                bucket.set(j + 1, bucket.get(j));
                j = j - 1;
            }
            bucket.set(j + 1, key);
        }
    }

    private static void printBuckets(List<List<Integer>> buckets, String msg) {
        //Imprimindo baldes na tela (Ordenado)
        for (int i = 0; i < buckets.size(); i++) {

            System.out.print("Bucket " + msg + " " + (i + 1) + ":");
            int index = 0;
            for (Integer num : buckets.get(i)) {
                index++;

                if (index == buckets.get(i).size()) {
                    System.out.print(" " + num);
                } else {
                    System.out.print(" " + num + " -");
                }
            }
            System.out.println();
        }
    }

    private static void printRows() {
        System.out.println("");
        System.out.println("// -------------------------------------------------------- //");
        System.out.println("");
    }


}