package de.comparus.barkalov;

public class Main {
    public static void main(String[] args) {
        LongMap<String> map = new LongMapImpl<>();

        map.put(152, "John");
        map.put(300 ,"Doe");

        System.out.println(map.get(300));
        System.out.println(map.containsValue("Doe"));
        map.clear();
        System.out.println(map.isEmpty());
    }
}
