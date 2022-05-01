import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainWithoutSort {
    private int a;
    private int b;
    private static final String CMD_END = "TCHAU";
    private static final String CMD_RASTER = "RASTER";
    private static final String CMD_AMPLITUDE = "AMPLITUDE";
    private static final String CMD_PERCENTILE = "PERCENTIL";
    private static final String CMD_MEDIAN = "MEDIAN";
    private static final String TXT_RASTER = "RASTER GUARDADO";

    public MainWithoutSort() throws IOException {
        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int nR;
        int nC;
        int n;
        int[] array = null;

        while (!(line = in.readLine()).equals(CMD_END)) {
            if (line.startsWith(CMD_RASTER)) {
                st = new StringTokenizer(line);
                st.nextToken();

                nR = Integer.parseInt(st.nextToken());
                nC = Integer.parseInt(st.nextToken());

                array = new int[nR * nC];

                for (int i = 0; i < nR; i++) {
                    line = in.readLine();
                    st = new StringTokenizer(line);

                    for (int j = 0; j < nC; j++) {
                        array[i * nC + j] = Integer.parseInt(st.nextToken());
                    }
                }

                System.out.println(TXT_RASTER);
            } else if (line.startsWith(CMD_AMPLITUDE)) {
                System.out.println(getAmplitude(array));
            } else if (line.startsWith(CMD_PERCENTILE)) {
                st = new StringTokenizer(line);
                st.nextToken();

                n = Integer.parseInt(st.nextToken());

                line = in.readLine();
                st = new StringTokenizer(line);

                for (int i = 0; i < n; i++) {
                    int value = Integer.parseInt(st.nextToken());

                    System.out.print(getPercentile(array, value));

                    if (i != n - 1) {
                        System.out.print(" ");
                    }
                }

                System.out.print("\n");

            } else if (line.startsWith(CMD_MEDIAN)) {
                System.out.println(getMedian(array, array.length));
            }
        }
    }

    private int[] swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;

        return array;
    }

    private int getPartition(int[] array, int l, int r) {
        int lst = array[r];
        int i = l;
        int j = l;

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

    private int getRandomPartition(int[] array, int l, int r) {
        int n = r - l + 1;
        int p = (int) (Math.random() % n);

        swap(array, l + p, r);

        return getPartition(array, l, r);
    }

    private int getMedianInner(int[] array, int l, int r, int k) {
        if (l <= r) {
            int idx = getRandomPartition(array, l, r);

            if (idx == k) {
                b = array[idx];

                if (a != -1) {
                    return Integer.MIN_VALUE;
                }
            } else if (idx == k - 1) {
                a = array[idx];

                if (b != -1) {
                    return Integer.MIN_VALUE;
                }
            }

            if (idx >= k) {
                return getMedianInner(array, l, idx - 1, k);
            } else
                return getMedianInner(array, idx + 1, r, k);
        }

        return Integer.MIN_VALUE;
    }

    private int getMedian(int[] array, int n) {
        int median;
        a = -1;
        b = -1;

        if (n % 2 == 1) {
            getMedianInner(array, 0, n - 1, n / 2);
            median = b;
        } else {
            getMedianInner(array, 0, n - 1, n / 2);
            median = (a + b) / 2;
        }

        return median;
    }

    private int getAmplitude(int[] array) {
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

    private int getPercentile(int[] array, int value) {
        int c;
        int result;
        int l;

        c = 0;
        l = array.length;
        for (int i : array) {
            if (value > i) {
                c++;
            }
        }

        result = (c * 100) / l;

        return result;
    }

    public static void main(String[] args) throws IOException {
        new MainWithoutSort();
    }
}
