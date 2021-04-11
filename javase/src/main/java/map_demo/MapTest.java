package map_demo;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * @author water33
 */
public class MapTest {

    public static void main(String[] args) {

//        TreeMap<String,String>

        Map map = initMap();
        testLambda(map);
        testIterator(map);
        testKeySet(map);
        testEntry(map);
    }

    public static void testLambda(Map<String,Integer> map){
        Long start = System.nanoTime();
        map.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                //System.out.println(s+"---"+integer);
            }
        });
        Long end = System.nanoTime();
        System.out.println("lambda:" + (end - start)/1000);
    }

    public static void testIterator(Map<String,Integer> map){
        Long start = System.nanoTime();

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            //System.out.println(entry.getKey()+"-----"+entry.getValue());
        }
        Long end = System.nanoTime();
        System.out.println("Iterator:" + (end - start)/1000);
    }

    public static void testKeySet(Map<String,Integer> map){
        Long start = System.nanoTime();

        for (String key : map.keySet()) {
            //System.out.println("key:"+key+"--,val:"+map.get(key));
        }
        Long end = System.nanoTime();
        System.out.println("keyset:" + (end - start)/1000);
    }

    public static void testEntry(Map<String,Integer> map){
        Long start = System.nanoTime();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            //System.out.println(entry.getKey()+"---"+entry.getValue());
        }
        Long end = System.nanoTime();
        System.out.println("entry:" + (end - start)/1000);
    }


    public static Map initMap(){
        HashMap<String, Integer> map = new HashMap<>();
        String[] arr = new String[]{"a","b","c","d","e","f","g","h"};
        for (int i = 0; i <5000000 ; i++) {
            int m = (int)(Math.random()*8);
            map.put(String.valueOf(arr[m]+i*100),i);
        }
        return map;
    }

}
