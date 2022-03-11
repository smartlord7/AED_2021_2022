import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainExaustive {

    public static int getMaxSum(int[] array) {
        int i = 0;
        int l = array.length;
        int maxDiff = -1;

        while (i < l - 1) {
            int j = i + 1;

            while (j < l) {
                int diff = Math.abs(array[i] - array[j]);

                if (diff > maxDiff) {
                    maxDiff = diff;
                }

                j++;
            }
            i++;
        }

        return maxDiff;
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
