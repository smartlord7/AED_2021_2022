import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainWithSort {
    public static void main(String[] args) throws IOException {
        new MainWithSort();
    }

    private int median(int[] array, int n) {
        int median;
        if (n % 2 == 1)
            median = array[(n - 1) / 2];
        else
            median = (array[n / 2] + array[(n / 2) - 1] ) / 2;

        return median;
    }

    private void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    private int amplitude(int[] array) {
        int min = array[0];
        int max = array[array.length-1];

        return Math.abs(max - min);
    }

    private int percentile(int[] array, int value) {
        int count = 0;
        int percentile;

        for (int i = 0; i < array.length; i++) {
            if (array[i] < value){
                count ++;
            } else{
                break;
            }
        }

        percentile = (count * 100) / array.length;

        return percentile;
    }

    public MainWithSort() throws IOException {
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
                sort(raster);
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
}
