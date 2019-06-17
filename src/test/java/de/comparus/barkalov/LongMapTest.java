package de.comparus.barkalov;

import org.junit.Assert;
import org.junit.Test;

public class LongMapTest {

    LongMap<String> longMap = new LongMapImpl<>();

    @Test
    public void putTest() {
        //WHEN
        longMap.put(54682, "John");
        //THEN
        Assert.assertEquals(1, longMap.size());
    }

    @Test
    public void getTest() {
        //WHEN
        longMap.put(789456123, "Doe");
        //THEN
        Assert.assertEquals("Doe", longMap.get(789456123));
    }

    @Test
    public void removeTest() {
        //GIVEN
        longMap.put(789456123, "Doe");
        //WHEN
        longMap.remove(789456123);
        //THEN
        Assert.assertNull(longMap.get(789456123));
        Assert.assertEquals(0, longMap.size());
    }

    @Test
    public void isEmptyTest() {
        //GIVEN
        longMap.put(789456123, "Doe");
        longMap.remove(789456123);
        //WHEN
        long actualResult = longMap.size();
        //THEN
        Assert.assertEquals(0, actualResult);
        Assert.assertTrue(longMap.isEmpty());
    }

    @Test
    public void containsKeyTest() {
        //GIVEN
        longMap.put(75462, "Doe");
        //WHEN
        boolean actualResult = longMap.containsKey(75462);
        //THEN
        Assert.assertTrue(actualResult);
    }

    @Test
    public void containsValueTest() {
        //GIVEN
        longMap.put(75462, "Doe");
        //WHEN
        boolean actualResult = longMap.containsValue("Doe");
        //THEN
        Assert.assertTrue(actualResult);
    }

    @Test
    public void keysTest() {
        //GIVEN
        longMap.put(75462, "Doe");
        longMap.put(79845234, "Snow");
        //WHEN
        long[] actualResult = longMap.keys();
        //THEN
        Assert.assertEquals(2, actualResult.length);
    }

    @Test
    public void sizeTest() {
        //GIVEN
        longMap.put(75462, "Doe");
        longMap.put(79845234, "Snow");
        //THEN
        Assert.assertEquals(2, longMap.size());
    }

    @Test
    public void clearTest() {
        //GIVEN
        longMap.put(75462, "Doe");
        longMap.put(79845234, "Snow");
        //WHEN
        longMap.clear();
        //THEN
        Assert.assertTrue(longMap.isEmpty());
    }
}