/*
Copyright (C) 2015  Arnould GUIDAT

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation; version 3 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package fr.beaftech.collique;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SingleElementMapTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testSize() {
		SingleElementMap<String, String> sem = new SingleElementMap<String, String>();
		assertEquals(0, sem.size());
		
		sem.put("key", "value");
		assertEquals(1, sem.size());
		
		sem.remove("key");
		assertEquals(0, sem.size());
	}

	@Test
	public void testIsEmpty() {
		SingleElementMap<String, String> sem = new SingleElementMap<String, String>();
		assertTrue(sem.isEmpty());
		
		sem.put("key", "value");
		assertFalse(sem.isEmpty());
		
		sem.remove("key");
		assertTrue(sem.isEmpty());
	}

	@Test
	public void testContainsKey() {
		SingleElementMap<String, String> sem = new SingleElementMap<String, String>();
		assertFalse(sem.containsKey("key"));
		
		sem.put("key", "value");
		assertTrue(sem.containsKey("key"));
		assertFalse(sem.containsKey("another key"));
		
		sem.remove("key");
		assertFalse(sem.containsKey("key"));
	}

	@Test
	public void testContainsValue() {
		SingleElementMap<String, String> sem = new SingleElementMap<String, String>();
		assertFalse(sem.containsValue("value"));
		
		sem.put("key", "value");
		assertTrue(sem.containsValue("value"));
		assertFalse(sem.containsValue("another value"));
		
		sem.remove("key");
		assertFalse(sem.containsValue("value"));
	}

	@Test
	public void testGet() {
		SingleElementMap<String, String> sem = new SingleElementMap<String, String>();
		assertEquals(null, sem.get("key"));
		
		sem.put("key", "value");
		assertEquals("value", sem.get("key"));
		assertEquals(null, sem.get("another key"));
		
		sem.remove("key");
		assertEquals(null, sem.get("key"));
	}

	@Test
	public void testPut() {
		SingleElementMap<String, String> sem = new SingleElementMap<String, String>();
		
		sem.put("key", "value");
		assertTrue(sem.containsKey("key"));
		assertTrue(sem.containsValue("value"));
		assertEquals("value",sem.get("key"));
		
		sem.put("key", "another value");
		assertTrue(sem.containsKey("key"));
		assertFalse(sem.containsValue("value"));
		assertTrue(sem.containsValue("another value"));
		assertEquals("another value",sem.get("key"));
		
		thrown.expect(UnsupportedOperationException.class);
		sem.put("another key", "another value");
	}

	@Test
	public void testRemove() {
		SingleElementMap<String, String> sem = new SingleElementMap<String, String>();
		assertEquals(null,sem.remove("key"));
		
		
		sem.put("key", "value");
		assertEquals("value", sem.remove("key"));
		assertEquals(null, sem.remove("key"));
		
	}

	@Test
	public void testPutAll() {
		SingleElementMap<String, String> sem = new SingleElementMap<String, String>();
		Map<String,String> map = new HashMap<String, String>();
		
		sem.putAll(map);
		assertTrue(sem.isEmpty());
		
		map.put("key", "value");
		sem.putAll(map);
		assertFalse(sem.isEmpty());
		assertTrue(sem.containsKey("key"));
		assertTrue(sem.containsValue("value"));
		assertEquals("value",sem.get("key"));
		
		sem.putAll(map);
		assertFalse(sem.isEmpty());
		assertTrue(sem.containsKey("key"));
		assertTrue(sem.containsValue("value"));
		assertEquals("value",sem.get("key"));
		
		map.put("key", "another value");
		sem.putAll(map);
		assertTrue(sem.containsKey("key"));
		assertFalse(sem.containsValue("value"));
		assertTrue(sem.containsValue("another value"));
		assertEquals("another value",sem.get("key"));
		
		map.put("another key", "another value");
		thrown.expect(UnsupportedOperationException.class);
		sem.putAll(map);
		
	}

	@Test
	public void testClear() {
		SingleElementMap<String, String> sem = new SingleElementMap<String, String>();
		
		sem.put("key", "value");
		sem.clear();
		assertTrue(sem.isEmpty());
		assertFalse(sem.containsKey("key"));
	}

	@Test
	public void testKeySet() {
		SingleElementMap<String, String> sem = new SingleElementMap<String, String>();
		
		Set<String> ks = sem.keySet();
		assertTrue(ks.isEmpty());
		
		sem.put("key", "value");
		ks = sem.keySet();
		assertEquals(1, ks.size());
		assertTrue(ks.contains("key"));
		
	}

	@Test
	public void testValues() {
		SingleElementMap<String, String> sem = new SingleElementMap<String, String>();
		
		Collection<String> v = sem.values();
		assertTrue(v.isEmpty());
		
		sem.put("key", "value");
		v = sem.values();
		assertEquals(1, v.size());
		assertTrue(v.contains("value"));
	}

	@Test
	public void testEntrySet() {
		SingleElementMap<String, String> sem = new SingleElementMap<String, String>();
		
		Set<Entry<String,String>> es = sem.entrySet();
		assertTrue(es.isEmpty());
		
		sem.put("key", "value");
		es = sem.entrySet();
		assertEquals(1, es.size());
		for(Entry<String,String> e : es){
			assertEquals("key", e.getKey());
			assertEquals("value", e.getValue());
		}
	}

	@Test
	public void testEqualsObject() {
		SingleElementMap<String, String> sem = new SingleElementMap<String, String>();
		assertFalse(sem.equals(Collections.emptyMap()));
		
		sem.put("key", "value");
		Map<String, String> other = new HashMap<String, String>();
		other.put("key", "value");
		assertFalse(sem.equals(other));
		
		other = new SingleElementMap<String, String>();
		assertFalse(sem.equals(other));
		
		other.put("key", "value");
		assertTrue(sem.equals(other));
		
	}

}
