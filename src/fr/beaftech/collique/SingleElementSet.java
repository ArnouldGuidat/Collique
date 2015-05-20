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

import java.util.Set;

/**
 * Single element implementation of {@code Set}. Permits all elements, including
 * {@code null} and implements all optional collection operations with some
 * restriction to {@link #add(Object)} and {@link #addAll(java.util.Collection)}
 * that will throw an {@code UnsupportedOperationException} when trying to add
 * an element that is different than the one contains in the set, thus because
 * the set can only contain one element.
 * 
 * @author Arnould Guidat
 * 
 * @param <E>
 *            the type of the element to store
 * 
 * @version 1.0
 * @since 1.0
 */
public class SingleElementSet<E> extends SingleElementCollection<E> implements
		Set<E> {

	public SingleElementSet() {
		super();
	}

	public SingleElementSet(E element) {
		super(element);

	}

	@Override
	public boolean add(E e) {
		if (!isEmpty()) {
			if (element.equals(e)) {
				return false;
			}
			throw new UnsupportedOperationException(
					"Cannot add more than one element to the list");
		}

		setElement(e);
		return true;
	}

}
