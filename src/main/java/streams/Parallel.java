package streams;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parallel {

    private static long sumSeq = 0;
    private static long sumPar = 0;

    public static void main(String[] args) {

        final Collection<Long> coll = createArrayList(2000);

        sumSeq = 0;
        coll.stream().forEach(x -> sumSeq = sumSeq + x);
        System.out.format("Sequential sum %8d\n", sumSeq);

        sumPar = 0;
        coll.parallelStream().forEach(x -> sumPar = sumPar + x);
        System.out.format("Parallel sum   %8d\n", sumPar);
    }

    private static Collection<Long> createArrayList(int n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).collect(Collectors.toList());
    }
}
