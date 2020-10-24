package uj.java.pwj2020.collections;

import java.util.ArrayList;
import java.util.List;

public class ListMerger {
    public static List<Object> mergeLists(List<?> l1, List<?> l2) {
        List<Object> list = new ArrayList<>();

        if(l1 == null && l2 == null) return list;
        else if(l1 == null) list = List.copyOf(l2);
        else if(l2 == null) list = List.copyOf(l1);
        else bothListsNotNull(l1, l2, list);

        final List<Object> mergedList = List.copyOf(list);
        return mergedList;
    }

    private static void bothListsNotNull(List<?> l1, List<?> l2, List<Object> list) {
        var i1 = l1.iterator();
        var i2 = l2.iterator();
        while(i1.hasNext() || i2.hasNext()){
            if(i1.hasNext()) list.add(i1.next());
            if(i2.hasNext()) list.add(i2.next());
        }
    }

}
