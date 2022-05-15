import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public Main() throws IOException {
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

                lsdRadixSort(raster, raster.length);
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
                System.out.println(median(raster, raster.length));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }

    private static int median(int[] array, int n) {
        int median;
        if (n % 2 == 1)
            median = array[(n - 1) / 2];
        else
            median = (array[n / 2] + array[(n / 2) - 1] ) / 2;

        return median;
    }

     private int amplitude(int[] array) {
        int min = array[0];
        int max = array[array.length-1];

        return Math.abs(max - min);
    }

    private int percentile(int[] array, int value) {
        int count = 0;
        int percentile;

        for (int j : array) {
            if (j < value) {
                count++;
            } else {
                break;
            }
        }
        percentile = (count * 100) / array.length;

        return percentile;
    }

    private int getMax(int[] array) {
        int max;

        max = Integer.MIN_VALUE;

        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }

        return max;
    }

    private void countingSort(int[] arr, int n, int exp) {
        int i;
        int[] sorted;
        int[] count;

        sorted = new int[n];
        count = new int[10];
        Arrays.fill(count,0);

        for (i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (i = n - 1; i >= 0; i--) {
            sorted[count[(arr[i] / exp) % 10 ] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (i = 0; i < n; i++) {
            arr[i] = sorted[i];
        }
    }

    private void lsdRadixSort(int[] arr, int n) {
        int max;

        max = getMax(arr);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, n, exp);
        }
    }
}
