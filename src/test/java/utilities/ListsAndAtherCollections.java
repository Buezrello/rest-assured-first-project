package utilities;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ListsAndAtherCollections {

    public static  <K, V> Map<K, V> zipListsToMap(List<K> firstList, List<V> secondList) {
        // mapping years to options
        return IntStream.range(0, firstList.size()).collect(
                LinkedHashMap::new,
                (m, i) -> m.put(firstList.get(i), secondList.get(i)),
                Map::putAll
        );
    }
}
