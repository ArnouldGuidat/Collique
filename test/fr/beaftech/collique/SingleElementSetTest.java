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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author Arnould Guidat
 * @version 1.0
 * @since 1.0
 */
public class SingleElementSetTest extends SingleElementCollectionTest {

	

	@Override
	public SingleElementCollection<String> getInstance() {
		return new SingleElementSet<String>();
	}

	@Test
	public void testAdd() {
		SingleElementCollection<String> sec = getInstance();
		assertTrue(sec.add("a string"));
		assertTrue(sec.contains("a string"));
		assertFalse(sec.add("a string"));

		thrown.expect(UnsupportedOperationException.class);
		sec.add("another string");
	}

	@Test
	public void testAddAll() {
		SingleElementCollection<String> sec = getInstance();
		List<String> test_list = new ArrayList<String>();
		assertFalse(sec.addAll(test_list));
		
		test_list.add("a string");
		assertTrue(sec.addAll(test_list));
		assertFalse(sec.addAll(test_list));
		
		test_list.add("another string");
		thrown.expect(UnsupportedOperationException.class);
		sec.addAll(test_list);

		

	}

}
