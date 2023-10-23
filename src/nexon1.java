import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'autocompletes' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY inputs as parameter.
     */

    public static List<Integer> autocompletes(List<String> inputs) {
        // Write your code here
        List<Integer> results = new ArrayList<>();


        return results;
    }

}

public class nexon1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int inputsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> inputs = IntStream.range(0, inputsCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        List<Integer> result = Result.autocompletes(inputs);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}