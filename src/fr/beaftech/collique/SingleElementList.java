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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Single element implementation of {@code List}. Permits all elements,
 * including {@code null} and implements all optional collection operations with
 * some restriction to {@link #add(Object)} and
 * {@link #addAll(java.util.Collection)} that will throw an
 * {@code UnsupportedOperationException} when trying to add a second element to
 * the list, thus because the list can only contain one element.
 * 
 * @author Arnould Guidat
 * 
 * @param <E>
 *            the type of the element to store
 * 
 * @version 1.0
 * @since 1.0
 */
public class SingleElementList<E> extends SingleElementCollection<E> implements
		List<E> {

	public SingleElementList() {
		super();
	}

	public SingleElementList(E element) {
		super(element);
	}

	@Override
	public boolean add(E e) {
		if (!isEmpty()) {
			throw new UnsupportedOperationException(
					"Cannot add more than one element to the list");
		}

		setElement(e);
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		if (index == 0)
			return addAll(c);
		throw new IndexOutOfBoundsException();

	}

	@Override
	public E get(int index) {
		if (!isEmpty() && index == 0)
			return getElement();
		throw new ArrayIndexOutOfBoundsException(index);
	}

	@Override
	public E set(int index, E element) {
		if (index == 0) {
			E previous = getElement();
			setElement(element);
			return previous;
		}
		throw new ArrayIndexOutOfBoundsException(index);

	}

	@Override
	public void add(int index, E element) {
		if (index == 0) {
			add(element);

		} else {
			throw new ArrayIndexOutOfBoundsException(index);
		}

	}

	@Override
	public E remove(int index) {
		if (index == 0 && !isEmpty()) {
			E deleted = getElement();
			element = null;
			return deleted;
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public int indexOf(Object o) {
		if (contains(o))
			return 0;
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		return indexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return new SingleElementListIterator();

	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new SingleElementListIterator(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex)
			throw new IndexOutOfBoundsException();
		if (isEmpty())
			return Collections.emptyList();
		return new SingleElementList<E>(getElement());
	}

	private class SingleElementListIterator implements ListIterator<E> {

		/**
		 * The pointer position. (will be -1 for an empty list)
		 */
		int pt;
		boolean hasCurrent;

		public SingleElementListIterator() {
			pt = isEmpty() ? -1 : 0;
			hasCurrent = false;
		}

		public SingleElementListIterator(int start) {
			if (start < -1 || start > size())
				throw new IndexOutOfBoundsException();

			pt = isEmpty() ? -1 : start;
			hasCurrent = false;
		}

		@Override
		public boolean hasNext() {
			return pt == 0;
		}

		@Override
		public E next() {
			if (pt == 0) {
				pt++;
				hasCurrent = true;
				return getElement();
			}
			throw new NoSuchElementException();
		}

		@Override
		public boolean hasPrevious() {
			return pt == 1;
		}

		@Override
		public E previous() {
			if (pt == 1) {
				pt--;
				hasCurrent = true;
				return getElement();
			}
			throw new NoSuchElementException();
		}

		@Override
		public int nextIndex() {
			if (pt < 0)
				return 0;
			if (pt > 1)
				return 1;
			return pt;
		}

		@Override
		public int previousIndex() {
			if (pt == 1)
				return 0;
			return -1;
		}

		@Override
		public void remove() {
			if (hasCurrent) {
				element = null;
				hasCurrent = false;
				pt = -1;
			} else {
				throw new IllegalStateException();
			}

		}

		@Override
		public void set(E e) {
			if (hasCurrent) {
				setElement(e);
			} else {
				throw new IllegalStateException();
			}

		}

		@Override
		public void add(E e) {
			if (pt == -1) {
				setElement(e);
				pt++;
			} else {
				throw new UnsupportedOperationException(
						"This list can only contains one element");
			}

		}

	}

}