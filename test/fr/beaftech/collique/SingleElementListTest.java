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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

/**
 * 
 * @author Arnould Guidat
 * @version 1.0
 * @since 1.0
 */
public class SingleElementListTest extends SingleElementCollectionTest {
	
	@Override
	public SingleElementCollection<String> getInstance() {
		return new SingleElementList<String>();
	}

	@Test
	public void testAddE() {
		SingleElementCollection<String> sec = getInstance();
		assertTrue(sec.add("a string"));
		assertTrue(sec.contains("a string"));

		thrown.expect(UnsupportedOperationException.class);
		sec.add("a string");
	}

	@Test
	public void testAddAllIntCollectionOfQextendsE_1() {
		SingleElementList<String> sec = new SingleElementList<String>();
		List<String> test_list = new ArrayList<String>();
		assertFalse(sec.addAll(test_list));
		
		test_list.add("a string");
		assertTrue(sec.addAll(0,test_list));
		
		thrown.expect(UnsupportedOperationException.class);
		sec.addAll(0,test_list);
	}
	
	@Test
	public void testAddAllIntCollectionOfQextendsE_2() {
		SingleElementList<String> sec = new SingleElementList<String>();
		List<String> test_list = new ArrayList<String>();
		
		test_list.add("a string");
		
		thrown.expect(IndexOutOfBoundsException.class);
		sec.addAll(1,test_list);
	}

	@Test
	public void testGet() {
		SingleElementList<String> sec = new SingleElementList<String>();
		sec.add("a string");
		assertEquals("a string", sec.get(0));
		
		thrown.expect(IndexOutOfBoundsException.class);
		sec.get(1);
	}

	@Test
	public void testSet() {
		SingleElementList<String> sec = new SingleElementList<String>();
		sec.add("a string");
		assertEquals("a string", sec.set(0, "another string"));
		assertFalse(sec.contains("a string"));
		assertTrue(sec.contains("another string"));
		
		thrown.expect(IndexOutOfBoundsException.class);
		sec.set(1,"some string");
	}

	@Test
	public void testAddIntE() {
		SingleElementList<String> sec = new SingleElementList<String>();
		sec.add(0,"a string");
		assertTrue(sec.contains("a string"));
		
		thrown.expect(IndexOutOfBoundsException.class);
		sec.add(1,"anotherstring");
	}

	@Test
	public void testRemoveInt() {
		SingleElementList<String> sec = new SingleElementList<String>();
		sec.add("a string");
		assertEquals("a string",sec.remove(0));
		
		thrown.expect(IndexOutOfBoundsException.class);
		sec.remove(0);
	}

	@Test
	public void testIndexOf() {
		SingleElementList<String> sec = new SingleElementList<String>();
		assertEquals(-1, sec.indexOf("a string"));
		sec.add("a string");
		assertEquals(0, sec.indexOf("a string"));
		

	}

	@Test
	public void testLastIndexOf() {
		SingleElementList<String> sec = new SingleElementList<String>();
		assertEquals(-1, sec.indexOf("a string"));
		sec.add("a string");
		assertEquals(0, sec.indexOf("a string"));
	}

	@Test
	public void testListIterator() {
		SingleElementList<String> sec = new SingleElementList<String>();
		ListIterator<String> lit = sec.listIterator();
		
		assertFalse(lit.hasNext());
		assertFalse(lit.hasPrevious());
		
		sec.add("a string");
		lit = sec.listIterator();
		
		assertTrue(lit.hasNext());
		assertEquals("a string", lit.next());
		assertFalse(lit.hasNext());
		assertTrue(lit.hasPrevious());
		assertEquals("a string", lit.previous());
		
	}

	@Test
	public void testListIteratorInt_1() {
		SingleElementList<String> sec = new SingleElementList<String>();
		ListIterator<String> lit = sec.listIterator(0);
		
		assertFalse(lit.hasNext());
		assertFalse(lit.hasPrevious());
		
		lit.add("a string");
		
		assertTrue(lit.hasNext());
		assertEquals("a string", lit.next());
		assertFalse(lit.hasNext());
		assertTrue(lit.hasPrevious());
		assertEquals("a string", lit.previous());
		
		thrown.expect(UnsupportedOperationException.class);
		lit.add("another string");
		
		
	}
	
	@Test
	public void testListIteratorInt_2() {
		SingleElementList<String> sec = new SingleElementList<String>();
		thrown.expect(IndexOutOfBoundsException.class);
		sec.listIterator(1);
		
		
	}

	@Test
	public void testSubList_1() {
		SingleElementList<String> sec = new SingleElementList<String>();
		List<String> sub = sec.subList(0, 0);
		assertTrue(sub.isEmpty());
		sec.add("a string");
		sub = sec.subList(0, 1);
		assertEquals(1, sub.size());
		assertEquals("a string", sub.get(0));
		
	}
	
	@Test
	public void testSubList_2() {
		SingleElementList<String> sec = new SingleElementList<String>();

		thrown.expect(IndexOutOfBoundsException.class);
		sec.subList(1, 3);
	}
	
	@Test
	public void testSubList_3() {
		SingleElementList<String> sec = new SingleElementList<String>();
		sec.add("a string");
		thrown.expect(IndexOutOfBoundsException.class);
		sec.subList(0, 3);
		
	}

	@Test
	public void testAddAllCollectionOfQextendsE_1() {
		SingleElementCollection<String> sec = getInstance();
		List<String> test_list = new ArrayList<String>();
		assertFalse(sec.addAll(test_list));
		
		test_list.add("a string");
		assertTrue(sec.addAll(test_list));
		thrown.expect(UnsupportedOperationException.class);
		sec.addAll(test_list);
		
		test_list.add("another string");
		
		sec.addAll(test_list);
		sec.clear();
		sec.addAll(test_list);
	}
	
	@Test
	public void testAddAllCollectionOfQextendsE_2() {
		SingleElementCollection<String> sec = getInstance();
		List<String> test_list = new ArrayList<String>();
		assertFalse(sec.addAll(test_list));
		
		test_list.add("a string");
		assertTrue(sec.addAll(test_list));
		test_list.add("another string");
		
		sec.clear();
		thrown.expect(UnsupportedOperationException.class);
		sec.addAll(test_list);
	}



}
