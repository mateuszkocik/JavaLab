package uj.java.pwj2020.map2d;

import java.util.Map;
import java.util.function.Function;

public class HashMap2D implements Map2D{
    @Override
    public Object put(Object rowKey, Object columnKey, Object value){
        return null;
    }

    @Override
    public Object get(Object rowKey, Object columnKey){
        return null;
    }

    @Override
    public Object remove(Object rowKey, Object columnKey){
        return null;
    }

    @Override
    public boolean isEmpty(){
        return false;
    }

    @Override
    public boolean nonEmpty(){
        return false;
    }

    @Override
    public int size(){
        return 0;
    }

    @Override
    public void clear(){

    }

    @Override
    public Map rowView(Object rowKey){
        return null;
    }

    @Override
    public Map columnView(Object columnKey){
        return null;
    }

    @Override
    public boolean hasValue(Object value){
        return false;
    }

    @Override
    public boolean hasKey(Object rowKey, Object columnKey){
        return false;
    }

    @Override
    public boolean hasRow(Object rowKey){
        return false;
    }

    @Override
    public boolean hasColumn(Object columnKey){
        return false;
    }

    @Override
    public Map rowMapView(){
        return null;
    }

    @Override
    public Map columnMapView(){
        return null;
    }

    @Override
    public Map2D fillMapFromRow(Map target, Object rowKey){
        return null;
    }

    @Override
    public Map2D fillMapFromColumn(Map target, Object columnKey){
        return null;
    }

    @Override
    public Map2D putAll(Map2D source){
        return null;
    }

    @Override
    public Map2D putAllToRow(Map source, Object rowKey){
        return null;
    }

    @Override
    public Map2D putAllToColumn(Map source, Object columnKey){
        return null;
    }

    @Override
    public Map2D copyWithConversion(Function rowFunction, Function columnFunction, Function valueFunction){
        return null;
    }
}
