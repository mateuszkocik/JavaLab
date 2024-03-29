package uj.java.pwj2020.map2d;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class HashMap2D<R, C, V> implements Map2D<R, C, V>{

    private final HashMap<R, HashMap<C, V>> map;

    public HashMap2D(){
        this.map = new HashMap<>();
    }

    @Override
    public V put(R rowKey, C columnKey, V value){
        if(rowKey == null || columnKey == null) throw new NullPointerException();

        V previousVal = get(rowKey, columnKey);
        map.computeIfAbsent(rowKey, k -> new HashMap<>()).put(columnKey, value);

        return previousVal;
    }

    @Override
    public V get(R rowKey, C columnKey){
        var colVar = map.get(rowKey);
        return colVar == null ? null : colVar.get(columnKey);
    }

    @Override
    public V remove(R rowKey, C columnKey){
        var value = get(rowKey, columnKey);
        var colVar = map.get(rowKey);
        if(colVar != null && colVar.get(columnKey) != null) map.remove(colVar, value);

        return value;
    }

    @Override
    public boolean isEmpty(){
        for(R row : map.keySet()){
            if(!map.get(row).isEmpty()) return false;
        }
        return true;
    }

    @Override
    public boolean nonEmpty(){
        return !map.isEmpty();
    }

    @Override
    public int size(){
        int size = 0;
        for(R row : map.keySet()) size += map.get(row).size();
        return size;
    }

    @Override
    public void clear(){
        map.clear();
    }

    @Override
    public Map<C, V> rowView(R rowKey){
        return Map.copyOf(map.get(rowKey));
    }

    @Override
    public Map<R, V> columnView(C columnKey){
        var tempMap = new HashMap<R, V>();
        for(R row : map.keySet()) if(map.get(row).containsKey(columnKey)) tempMap.put(row, map.get(row).get(columnKey));

        return tempMap;
    }

    @Override
    public boolean hasValue(V value){
        for(R row : map.keySet()){
            if(map.get(row).containsValue(value)) return true;
        }
        return false;
    }

    @Override
    public boolean hasKey(R rowKey, C columnKey){
        var keySet = map.keySet();
        if(keySet.contains(rowKey)){
            for(R row : keySet){
                if(map.get(row).containsKey(columnKey)) return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasRow(R rowKey){
        return map.containsKey(rowKey);
    }

    @Override
    public boolean hasColumn(C columnKey){
        for(R row : map.keySet()){
            if(map.get(row).containsKey(columnKey)) return true;
        }
        return false;
    }

    @Override
    public Map<R, Map<C, V>> rowMapView(){
        return Map.copyOf(map);
    }

    @Override
    public Map<C, Map<R, V>> columnMapView(){
        var resultMap = new HashMap2D<C, R, V>();
        for(R row : map.keySet()){
            for(C column : map.get(row).keySet()){
                resultMap.put(column, row, map.get(row).get(column));
            }
        }
        return Map.copyOf(resultMap.getMap());
    }

    @Override
    public Map2D<R, C, V> fillMapFromRow(Map<? super C, ? super V> target, R rowKey){
        if(map.get(rowKey) != null) target.putAll(map.get(rowKey));
        return this;
    }

    @Override
    public Map2D<R, C, V> fillMapFromColumn(Map<? super R, ? super V> target, C columnKey){
        for(R row : map.keySet()){
            target.put(row, get(row, columnKey));
        }
        return this;
    }

    @Override
    public Map2D<R, C, V> putAll(Map2D<? extends R, ? extends C, ? extends V> source){
        var sourceMap = source.rowMapView();
        for(var entry : sourceMap.entrySet()){
            putAllToRow(entry.getValue(), entry.getKey());
        }
        return this;
    }

    @Override
    public Map2D<R, C, V> putAllToRow(Map<? extends C, ? extends V> source, R rowKey){
        map.computeIfAbsent(rowKey, k -> new HashMap<>()).putAll(source);
        return this;
    }

    @Override
    public Map2D<R, C, V> putAllToColumn(Map<? extends R, ? extends V> source, C columnKey){
        for(R row : source.keySet()){
            put(row, columnKey, source.get(row));
        }
        return this;
    }

    @Override
    public <R2, C2, V2> Map2D<R2, C2, V2> copyWithConversion(Function<? super R, ? extends R2> rowFunction, Function<? super C, ? extends C2> columnFunction, Function<? super V, ? extends V2> valueFunction){
        var resultMap = new HashMap2D<R2,C2,V2>();
        for(R row : map.keySet()){
            var resultColVal = new HashMap<C2,V2>();
            var colVal = map.get(row);
            for(C column : colVal.keySet()){
                resultColVal.put(columnFunction.apply(column),valueFunction.apply(colVal.get(column)));
            }
            resultMap.putAllToRow(resultColVal,rowFunction.apply(row));
        }
        return resultMap;
    }

    private HashMap<R, HashMap<C, V>> getMap(){
        return map;
    }
}
