package org.myhashmap;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Testing constructors ===");
        MyHashMap<String, Integer> map1 = new MyHashMap<>();
        System.out.println("Default constructor: " + map1.size());

        MyHashMap<String, Integer> map2 = new MyHashMap<>(32);
        System.out.println("Constructor with capacity: " + map2.size());

        MyHashMap<String, Integer> map3 = new MyHashMap<>(16, 0.5f);
        System.out.println("Constructor with capacity and load factor: " + map3.size());

        System.out.println("\n=== Testing put and get ===");
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put(null, 0);
        map.put("four", 4);

        System.out.println("get('one'): " + map.get("one"));
        System.out.println("get('two'): " + map.get("two"));
        System.out.println("get('three'): " + map.get("three"));
        System.out.println("get(null): " + map.get(null));
        System.out.println("get('four'): " + map.get("four"));
        System.out.println("get('five'): " + map.get("five"));

        System.out.println("size(): " + map.size());

        System.out.println("\n=== Testing collisions ===");

        String key1 = "FB";
        String key2 = "Ea";

        System.out.println("Hash for 'FB': " + Objects.hashCode(key1));
        System.out.println("Hash for 'Ea': " + Objects.hashCode(key2));

        map.put(key1, 100);
        map.put(key2, 200);
        System.out.println("get('FB'): " + map.get(key1));
        System.out.println("get('Ea'): " + map.get(key2));
        System.out.println("size(): " + map.size());

        System.out.println("\n=== Testing value replacement ===");
        Integer oldValue = map.put("one", 111);
        System.out.println("Replaced value for 'one': " + oldValue);
        System.out.println("New value for 'one': " + map.get("one"));
        System.out.println("size(): " + map.size());

        System.out.println("\n=== Testing remove ===");
        Integer removedValue = map.remove("two");
        System.out.println("Removed value for 'two': " + removedValue);
        System.out.println("get('two') after remove: " + map.get("two"));
        System.out.println("size(): " + map.size());

        Integer nonExistent = map.remove("five");
        System.out.println("Removed value for 'five': " + nonExistent);
        System.out.println("size(): " + map.size());

        Integer nullRemoved = map.remove(null);
        System.out.println("Removed value for null: " + nullRemoved);
        System.out.println("get(null) after remove: " + map.get(null));
        System.out.println("size(): " + map.size());

        System.out.println("\n=== Testing containsKey ===");
        System.out.println("containsKey('three'): " + map.containsKey("three"));
        System.out.println("containsKey('two'): " + map.containsKey("two"));
        System.out.println("containsKey(null): " + map.containsKey(null));

        System.out.println("\n=== Testing clear ===");
        map.clear();
        System.out.println("size() after clear: " + map.size());
        System.out.println("get('one') after clear: " + map.get("one"));

        System.out.println("\n=== Testing auto-resizing ===");
        MyHashMap<Integer, String> resizeMap = new MyHashMap<>(4, 0.5f);
        System.out.println("Initial capacity: ~4");

        for (int i = 0; i < 10; i++) {
            resizeMap.put(i, "Value" + i);
        }
        System.out.println("size() after adding 10 elements: " + resizeMap.size());

        boolean allCorrect = true;
        for (int i = 0; i < 10; i++) {
            if (!("Value" + i).equals(resizeMap.get(i))) {
                allCorrect = false;
                break;
            }
        }
        System.out.println("All values correct after resizing: " + allCorrect);

        System.out.println("\n=== Testing with custom object as key ===");
        class Person {
            String name;
            int age;

            Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, age);
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Person person = (Person) obj;
                return age == person.age && Objects.equals(name, person.name);
            }
        }

        MyHashMap<Person, String> personMap = new MyHashMap<>();
        Person alice = new Person("Alice", 30);
        Person bob = new Person("Bob", 25);

        personMap.put(alice, "Developer");
        personMap.put(bob, "Designer");

        System.out.println("Job for Alice: " + personMap.get(alice));
        System.out.println("Job for Bob: " + personMap.get(bob));

        Person aliceClone = new Person("Alice", 30);
        System.out.println("Job for Alice clone: " + personMap.get(aliceClone));
    }
}