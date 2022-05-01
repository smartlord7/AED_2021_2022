import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainWithoutSort {

    public static class Median {
        private static int a, b;

        private static int[] swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;

            return array;
        }

        private static int part(int[] array, int l, int r) {
            int lst = array[r], i = l, j = l;
            while (j < r) {
                if (array[j] < lst) {
                    swap(array, i, j);
                    i++;
                }
                j++;
            }

            swap(array, i, r);

            return i;
        }

        private static int randomPartition(int[] array, int l, int r) {
            int n = r - l + 1;
            int pivot = (int) (Math.random() % n);
            swap(array, l + pivot, r);

            return part(array, l, r);
        }

        private static int median_(int[] array, int l, int r, int k) {

            if (l <= r) {
                int partitionIndex = randomPartition(array, l, r);

                if (partitionIndex == k) {
                    b = array[partitionIndex];
                    if (a != -1)
                        return Integer.MIN_VALUE;
                } else if (partitionIndex == k - 1) {
                    a = array[partitionIndex];
                    if (b != -1)
                        return Integer.MIN_VALUE;
                }

                if (partitionIndex >= k) {
                    return median_(array, l, partitionIndex - 1, k);
                } else
                    return median_(array, partitionIndex + 1, r, k);
            }

            return Integer.MIN_VALUE;
        }

        private static int median(int[] array, int n) {
            int median;
            a = -1;
            b = -1;

            if (n % 2 == 1) {
                median_(array, 0, n - 1, n / 2);
                median = b;
            } else {
                median_(array, 0, n - 1, n / 2);
                median = (a + b) / 2;
            }

           return median;
        }
    }

    private int amplitude(int[] array) {
        int min;
        int max;

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        for (int value : array) {
            if (value < min) {
                min = value;
            }

            if (value > max) {
                max = value;
            }
        }

        return Math.abs(max - min);
    }

    private int percentile(int[] array, int value) {
        int count;
        int percentile;

        count = 0;
        for (int i : array) {
            if (value > i) {
                count++;
            }
        }

        percentile = (count * 100) / array.length;

        return percentile;
    }

    public MainWithoutSort() throws IOException {
        int nRows;
        int nCols;
        int nValues;
        int[] raster;
        String line;
        BufferedReader in;
        StringTokenizer st;

        raster = null;
        in = new BufferedReader(new InputStreamReader(System.in));

        while (!(line = in.readLine()).equals("TCHAU")) {
            if (line.startsWith("RASTER")) {
                st = new StringTokenizer(line);
                st.nextToken();

                nRows = Integer.parseInt(st.nextToken());
                nCols = Integer.parseInt(st.nextToken());

                raster = new int[nRows * nCols];

                for (int i = 0; i < nRows; i++) {
                    line = in.readLine();
                    st = new StringTokenizer(line);

                    for (int j = 0; j < nCols; j++) {
                        raster[i * nCols + j] = Integer.parseInt(st.nextToken());
                    }
                }

                System.out.println("RASTER GUARDADO");
            } else if (line.startsWith("AMPLITUDE")) {
                System.out.println(amplitude(raster));
            } else if (line.startsWith("PERCENTIL")) {
                st = new StringTokenizer(line);
                st.nextToken();

                nValues = Integer.parseInt(st.nextToken());

                line = in.readLine();
                st = new StringTokenizer(line);

                for (int i = 0; i < nValues; i++) {
                    int value = Integer.parseInt(st.nextToken());

                    System.out.print(percentile(raster, value));

                    if (i != nValues - 1) {
                        System.out.print(" ");
                    }
                }

                System.out.print("\n");

            } else if (line.startsWith("MEDIANA")) {
                System.out.println(Median.median(raster, raster.length));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new MainWithoutSort();
    }
}
