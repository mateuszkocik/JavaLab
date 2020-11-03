package uj.java.pwj2020.collections;

import java.util.ArrayList;
import java.util.List;

public class ListMerger{
    public static List<Object> mergeLists(List<?> l1, List<?> l2){

        if(l1 == null && l2 == null) return new ArrayList<>();
        else if(l1 == null) return List.copyOf(l2);
        else if(l2 == null) return List.copyOf(l1);
        else return bothListsNotNull(l1, l2);

    }

    private static List bothListsNotNull(List<?> l1, List<?> l2){
        var list = new ArrayList<>();
        var i1 = l1.iterator();
        var i2 = l2.iterator();

        while(i1.hasNext() || i2.hasNext()){
            if(i1.hasNext()) list.add(i1.next());
            if(i2.hasNext()) list.add(i2.next());
        }

        return List.copyOf(list);
    }

}
