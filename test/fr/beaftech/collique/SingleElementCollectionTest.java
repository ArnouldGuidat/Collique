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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * @author Arnould Guidat
 * @version 1.0
 * @since 1.0
 */
public abstract class SingleElementCollectionTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	public abstract SingleElementCollection<String> getInstance();

	@Test
	public void testSize() {
		SingleElementCollection<String> sec = getInstance();
		assertEquals(0, sec.size());
		sec.add("a string");
		assertEquals(1, sec.size());
		sec.remove("a string");
		assertEquals(0, sec.size());
	}

	@Test
	public void testIsEmpty() {
		SingleElementCollection<String> sec = getInstance();
		assertTrue(sec.isEmpty());
		sec.add("a string");
		assertFalse(sec.isEmpty());
		sec.remove("a string");
		assertTrue(sec.isEmpty());
	}

	@Test
	public void testContains() {
		SingleElementCollection<String> sec = getInstance();
		assertFalse(sec.contains("a string"));
		sec.add("a string");
		assertTrue(sec.contains("a string"));
		assertFalse(sec.contains("another string"));
		sec.remove("a string");
		assertFalse(sec.contains("a string"));
	}

	@Test
	public void testIterator() {
		SingleElementCollection<String> sec = getInstance();
		Iterator<String> it = sec.iterator();
		assertFalse(it.hasNext());
		
		sec.add("a string");
		it = sec.iterator();
		assertTrue(it.hasNext());
		assertEquals("a string", it.next());
		assertFalse(it.hasNext());
		
		
	}
	
	@Test
	public void testIterator_NoSuchElementException() {
		SingleElementCollection<String> sec = getInstance();
		Iterator<String> it = sec.iterator();
		
		thrown.expect(NoSuchElementException.class);
		it.next();

	}
	
	@Test
	public void testIterator_IllegalStateException() {
		SingleElementCollection<String> sec = getInstance();
		Iterator<String> it = sec.iterator();
		
		sec.add("a string");
		it = sec.iterator();
		it.next();
		it.remove();
		thrown.expect(IllegalStateException.class);
		it.remove();
		
		
	}

	@Test
	public void testToArray() {
		SingleElementCollection<String> sec = getInstance();
		Object[] array = sec.toArray();
		assertEquals(0, array.length);
		
		sec.add("a string");
		array = sec.toArray();
		assertEquals(1, array.length);
		
		assertEquals("a string", array[0]);
	}

	@Test
	public void testToArrayTArray() {
		SingleElementCollection<String> sec = getInstance();
		String[] array = new String[0];
		sec.add("a string");
		array = sec.toArray(array);
		assertEquals(1, array.length);
		assertEquals("a string",array[0]);
		
		array = new String[2];
		array = sec.toArray(array);
		assertEquals(2, array.length);
		assertEquals("a string",array[0]);
		assertEquals(null,array[1]);
		
		
		
	}


	@Test
	public void testRemove() {
		SingleElementCollection<String> sec = getInstance();
		assertFalse(sec.remove("a string"));
		sec.add("a string");
		assertTrue(sec.remove("a string"));
		assertFalse(sec.contains("a string"));
	}

	@Test
	public void testContainsAll() {
		SingleElementCollection<String> sec = getInstance();
		List<String> test_list = new ArrayList<String>();
		assertTrue(sec.containsAll(test_list));
		sec.add("a string");
		assertTrue(sec.containsAll(test_list));
		test_list.add("a string");
		assertTrue(sec.containsAll(test_list));
		test_list.add("another string");
		assertFalse(sec.containsAll(test_list));
		test_list.remove("a string");
		assertFalse(sec.containsAll(test_list));
	}

	

	@Test
	public void testRemoveAll() {
		SingleElementCollection<String> sec = getInstance();
		List<String> test_list = new ArrayList<String>();
		assertFalse(sec.removeAll(test_list));
		sec.add("a string");
		test_list.add("another string");
		assertFalse(sec.removeAll(test_list));
		test_list.add("a string");
		assertTrue(sec.removeAll(test_list));
		assertFalse(sec.contains("a string"));
	}

	@Test
	public void testRetainAll() {
		SingleElementCollection<String> sec = getInstance();
		List<String> test_list = new ArrayList<String>();
		assertFalse(sec.retainAll(test_list));
		sec.add("a string");
		test_list.add("another string");
		assertTrue(sec.retainAll(test_list));
		assertFalse(sec.contains("a string"));
		sec.add("another string");
		assertFalse(sec.retainAll(test_list));
		
	}

	@Test
	public void testClear() {
		SingleElementCollection<String> sec = getInstance();
		sec.add("a string");
		sec.clear();
		assertTrue(sec.isEmpty());
	}

	@Test
	public void testEqualsObject() {
		SingleElementCollection<String> sec = getInstance();
		assertFalse(sec.equals(Collections.emptyList()));
		sec.add("a string");
		List<String> test_list = new ArrayList<String>();
		test_list.add("a string");
		assertFalse(sec.equals(test_list));
		SingleElementCollection<String> other = getInstance();
		assertFalse(sec.equals(other));
		other.add("another string");
		assertFalse(sec.equals(other));
		other.clear();
		other.add("a string");
		assertTrue(sec.equals(other));
	}

}
