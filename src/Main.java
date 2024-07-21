import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] arr = {42, 32, 33, 52, 37, 47, 51};

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

        // 4) Ordenar os elementos em cada balde
        for (List<Integer> bucket : buckets) {
            insertionSort(bucket);
        }



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


}