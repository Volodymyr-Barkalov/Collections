package de.comparus.barkalov;

import java.util.ArrayList;
import java.util.Objects;

public class LongMapImpl<V> implements LongMap<V> {

    private int capacity = 16;
    private int size = 0;
    private Entry<V>[] table = new Entry[capacity];

    private Entry<V> getEntry(long key) {
        if (size > 0) {
            int location = hashing(key);
            Entry<V> currentEntry = null;
            try {
                currentEntry = table[location];
            } catch (NullPointerException ex) {
            }
            if (currentEntry != null) {
                while (true) {
                    if (currentEntry.getKey() == key) {
                        return currentEntry;
                    } else {
                        if (currentEntry.next != null) {
                            currentEntry = currentEntry.next;
                        } else {
                            return null;
                        }
                    }
                }
            }
        }
        return null;
    }

    public V put(long key, V value) {
        int location = hashing(key);
        if (location >= capacity) {
            System.out.println("Rehashing required");
            return null;
        }
        Entry<V> currentEntry = null;
        try {
            currentEntry = table[location];
        } catch (NullPointerException ex) {
        }
        if (currentEntry != null) {
            while (true) {
                if (currentEntry.getKey() == key) {
                    V oldValue = currentEntry.getValue();
                    currentEntry.setValue(value);
                    return oldValue;
                } else {
                    if (currentEntry.next == null) {
                        Entry<V> newEntry = new Entry<>(hashing(key), key, value, null);
                        currentEntry.next = newEntry;
                        newEntry.prev = currentEntry;
                        size++;
                        return value;
                    } else {
                        currentEntry = currentEntry.next;
                    }
                }

            }

        } else {
            table[location] = new Entry<>(hashing(key), key, value, null);
            size++;
        }
        return value;
    }

    public V get(long key) {
        Entry<V> entry = getEntry(key);
        return entry == null ? null : entry.getValue();
    }

    public V remove(long key) {
        Entry<V> entry = getEntry(key);
        if (entry != null) {
            if (entry.prev == null) {
                int location = hashing(key);
                table[location] = entry.next;
            } else {
                entry.prev.next = entry.next;
            }
            size--;
            return entry.value;
        }
        return null;
    }

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    public boolean containsKey(long key) {
        Entry<V> entry = getEntry(key);
        return entry != null;

    }

    public boolean containsValue(V value) {
        Entry<V>[] tab;
        V v;
        if ((tab = table) != null && size > 0) {
            for (int i = 0; i < tab.length; ++i) {
                for (Entry<V> e = tab[i]; e != null; e = e.next) {
                    if ((v = e.value) == value ||
                            (value != null && value.equals(v)))
                        return true;
                }
            }
        }
        return false;
    }

    public long[] keys() {
        long[] keys = new long[0];
        if (size > 0) {
            keys = new long[size];
            int keyIndex = 0;
            for (int i = 0; i < table.length; i++) {
                for (Entry<V> e = table[i]; e != null; e = e.next) {
                    keys[keyIndex] = e.getKey();
                    keyIndex++;
                }
            }
        }
        return keys;
    }

    public V[] values() {
        ArrayList<V> values = new ArrayList<V>();
        if (size > 0) {
            for (int i = 0; i < table.length; i++) {
                for (Entry<V> e = table[i]; e != null; e = e.next) {
                    values.add(e.getValue());
                }
            }

        }
//        @SuppressWarnings("unchecked")
        V[] arr = (V[]) values.toArray();
        return arr;
    }

    public long size() {
        return this.size;
    }

    public void clear() {
        Entry<V>[] tab;
        if ((tab = table) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; ++i)
                tab[i] = null;
        }
    }

    private int hashing(long hashCode) {
        int location = (int) (hashCode % capacity);
        return Math.abs(location);
    }


    private class Entry<V> {
        final int hash;
        final long key;
        V value;
        Entry<V> next;
        Entry<V> prev;

        Entry(int hash, long key, V value, Entry<V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final long getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?> entry = (Entry<?>) o;
            return key == entry.key &&
                    Objects.equals(value, entry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
