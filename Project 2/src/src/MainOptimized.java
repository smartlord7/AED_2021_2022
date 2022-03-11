import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainOptimized {

    public static int getMaxSum(int[] array) {
        int i = 0;
        int l = array.length;
        int max = array[0];
        int min = array[0];

        while (i < l) {
            int curr = array[i];

            if (curr > max) {
                max = curr;
            } else if (curr < min) {
                min = curr;
            }

            i++;
        }

        return Math.abs(max - min);
    }

    public static void main(String[] args) throws IOException {
        int i = 0;
        int n;
        int[] array;
        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        line = in.readLine();
        n = Integer.parseInt(line);
        array = new int[n];

        line = in.readLine();
        st = new StringTokenizer(line);

        while (i < n) {
            array[i++] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getMaxSum(array));
    }
}
