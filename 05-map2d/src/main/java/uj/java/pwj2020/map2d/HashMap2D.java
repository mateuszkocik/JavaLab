package uj.java.pwj2020.map2d;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class HashMap2D<R, C, V> implements Map2D<R, C, V>{

    private HashMap<R,HashMap<C, V>> map;

    public HashMap2D(){
        this.map = new HashMap<>();
    }

    @Override
    public V put(R rowKey, C columnKey, V value){
        if(rowKey == null || columnKey == null) throw new NullPointerException();

        V previousVal = get(rowKey,columnKey);
        var colVar = map.computeIfAbsent(rowKey, k -> new HashMap<>());
        colVar.put(columnKey,value);

        return previousVal;
    }

    @Override
    public V get(R rowKey, C columnKey){
        var colVar = map.get(rowKey);
        return colVar == null ? null : colVar.get(columnKey);
    }

    @Override
    public V remove(R rowKey, C columnKey){ return null;
    }

    @Override
    public boolean isEmpty(){
        return map.isEmpty();
    }

    @Override
    public boolean nonEmpty(){
        return !map.isEmpty();
    }

    @Override
    public int size(){
        return 0;
    }

    @Override
    public void clear(){
        map.clear();
    }

    @Override
    public Map<C, V> rowView(R rowKey){ return null;
    }

    @Override
    public Map<R, V> columnView(C columnKey){
        return null;
    }

    @Override
    public boolean hasValue(V value){ return true;
    }

    @Override
    public boolean hasKey(R rowKey, C columnKey){
        return false;
    }

    @Override
    public boolean hasRow(R rowKey){
        return false;
    }

    @Override
    public boolean hasColumn(C columnKey){
        return false;
    }

    @Override
    public Map<R, Map<C, V>> rowMapView(){
        return null;
    }

    @Override
    public Map<C, Map<R, V>> columnMapView(){
        return null;
    }

    @Override
    public Map2D<R, C, V> fillMapFromRow(Map<? super C, ? super V> target, R rowKey){
        return null;
    }

    @Override
    public Map2D<R, C, V> fillMapFromColumn(Map<? super R, ? super V> target, C columnKey){
        return null;
    }

    @Override
    public Map2D<R, C, V> putAll(Map2D<? extends R, ? extends C, ? extends V> source){
        return null;
    }

    @Override
    public Map2D<R, C, V> putAllToRow(Map<? extends C, ? extends V> source, R rowKey){
        return null;
    }

    @Override
    public Map2D<R, C, V> putAllToColumn(Map<? extends R, ? extends V> source, C columnKey){
        return null;
    }

    @Override
    public <R2, C2, V2> Map2D<R2, C2, V2> copyWithConversion(Function<? super R, ? extends R2> rowFunction, Function<? super C, ? extends C2> columnFunction, Function<? super V, ? extends V2> valueFunction){
        return null;
    }
}