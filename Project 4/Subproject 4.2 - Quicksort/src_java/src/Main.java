import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

                quickSort(raster);
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
            median = array[(n-1)/2];
        else
            median = (array[n/2] + array[(n/2)-1] ) / 2;

        return median;
    }

    public static void quickSort(int[] intArray) {
        recQuickSort(intArray, 0, intArray.length - 1);
    }

    public static void recQuickSort(int[] intArray, int left, int right) {
        int size = right - left + 1;
        if (size <= 3) {
            manualSort(intArray, left, right);
        } else {
            double median = medianOf3(intArray, left, right);
            int partition = partitionIt(intArray, left, right, median);

            recQuickSort(intArray, left, partition - 1);
            recQuickSort(intArray, partition + 1, right);
        }
    }

    public static int medianOf3(int[] intArray, int left, int right) {
        int center = (left + right) / 2;

        if (intArray[left] > intArray[center]) {
            swap(intArray, left, center);
        }

        if (intArray[left] > intArray[right]) {
            swap(intArray, left, right);
        }

        if (intArray[center] > intArray[right]) {
            swap(intArray, center, right);
        }

        swap(intArray, center, right - 1);

        return intArray[right - 1];
    }

    public static void swap(int[] intArray, int dex1, int dex2) {
        int temp = intArray[dex1];
        intArray[dex1] = intArray[dex2];
        intArray[dex2] = temp;
    }

    public static int partitionIt(int[] intArray, int left, int right, double pivot) {
        int leftPtr = left;
        int rightPtr = right - 1;

        while (true) {
            while (intArray[++leftPtr] < pivot);
            while (intArray[--rightPtr] > pivot);
            if (leftPtr >= rightPtr) {
                break;
            } else {
                swap(intArray, leftPtr, rightPtr);
            }
        }
        swap(intArray, leftPtr, right - 1);

        return leftPtr;
    }

    public static void manualSort(int[] intArray, int left, int right) {
        int size = right - left + 1;
        if (size <= 1)
            return;
        if (size == 2) {
            if (intArray[left] > intArray[right])
                swap(intArray, left, right);
        } else {
            if (intArray[left] > intArray[right - 1]) {
                swap(intArray, left, right - 1);
            }
            if (intArray[left] > intArray[right]) {
                swap(intArray, left, right);
            }

            if (intArray[right - 1] > intArray[right]) {
                swap(intArray, right - 1, right);
            }
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
}
