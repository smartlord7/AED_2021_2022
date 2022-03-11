import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainImproved {

    public static int getMaxSum(int[] array) {
        int l = array.length;

        Arrays.sort(array);
        return Math.abs(array[l - 1] - array[0]);
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
