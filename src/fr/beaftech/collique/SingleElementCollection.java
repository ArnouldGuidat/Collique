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
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Single element implementation of {@code Collection} interface. Permits all
 * elements, including {@code null} and implements all optional collection
 * operations except {@link #add(Object)} which needs to be override by
 * subclasses. Note that, by design {@link #addAll(Collection)} is directly
 * linked to {@link #add(Object)} so its behavior (espescially for addition
 * restriction) will depends of subclasses implementation.
 * 
 * @author Arnould Guidat
 * 
 * @param <E>
 *            the type of the element to store
 * 
 * @version 1.0
 * @since 1.0
 */
public abstract class SingleElementCollection<E> implements Collection<E> {

	protected Element<E> element;

	/**
	 * A wrapper for the list element. Useful to know if the collection is empty
	 * or contains {@code null}
	 * 
	 * @author Arnould Guidat
	 *
	 * @param <E>
	 *            the wrapped element type
	 */
	protected static class Element<E> {
		E value;

		public Element() {
			value = null;
		}

		public Element(E value) {
			this.value = value;
		}

		@Override
		public boolean equals(Object obj) {
			if (value == null)
				return obj == null;
			if (obj instanceof Element)
				return value.equals(((Element<?>) obj).value);
			return value.equals(obj);
		}

		@Override
		public int hashCode() {
			return value == null ? 0 : value.hashCode();
		}

	}

	public SingleElementCollection() {
		element = null;
	}

	public SingleElementCollection(E element) {
		this.element = new Element<E>(element);
	}

	/**
	 * Get the real element contained by the collection.
	 * 
	 * @return the value of the element of the collection or {@code null} is the
	 *         collection is empty
	 */
	protected E getElement() {
		return isEmpty() ? null : element.value;
	}

	/**
	 * Set the value of the element of the collection with the given one. If the
	 * collection is empty, create a new element and et its value.
	 * 
	 * @param value
	 *            - the value to set.
	 */
	protected void setElement(E value) {
		if (element == null) {
			element = new Element<E>();
		}
		element.value = value;
	}

	@Override
	public int size() {
		return isEmpty() ? 0 : 1;
	}

	@Override
	public boolean isEmpty() {
		return element == null;
	}

	@Override
	public boolean contains(Object o) {
		return isEmpty() ? false : element.equals(o);
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			/**
			 * -1 -> empty list<br/>
			 * 0 -> next element is the element contained by the list<br/>
			 * 1 -> element has already been seen<br/>
			 */
			int state = isEmpty() ? -1 : 0;

			@Override
			public boolean hasNext() {
				return state == 0;
			}

			@Override
			public E next() {
				if (state == 0) {
					state++;
					return getElement();
				}

				throw new NoSuchElementException();
			}

			@Override
			public void remove() {
				if (state == 1) {
					element = null;
					state = -1;
				} else {
					throw new IllegalStateException();
				}

			}

		};
	}

	@Override
	public Object[] toArray() {
		if (isEmpty())
			return new Object[0];
		return new Object[] { getElement() };
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {

		if (a.length == 0) {
			a = (T[]) java.lang.reflect.Array.newInstance(a.getClass()
					.getComponentType(), 1);

		}
		int i = 0;
		if (!isEmpty()) {
			a[i] = (T) getElement();
			i++;
		}

		for (; i < a.length; i++) {
			a[i] = null;
		}

		return a;

	}

	@Override
	public boolean remove(Object o) {
		if (isEmpty())
			return false;
		if (element.equals(o)) {
			element = null;
			return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if (c.isEmpty())
			return true;
		if (isEmpty())
			return false;
		if (c.size() == 1)
			return element.equals(c.toArray()[0]);
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean changed = false;
		Iterator<? extends E> it = c.iterator();
		while (it.hasNext()) {
			changed = add(it.next()) || changed;
		}
		return changed;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		Iterator<?> it = c.iterator();
		while (it.hasNext()) {
			if (contains(it.next())) {
				element = null;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (isEmpty() || c.contains(getElement()))
			return false;
		element = null;
		return true;
	}

	@Override
	public void clear() {
		element = null;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SingleElementCollection<?> other = (SingleElementCollection<?>) obj;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		return true;
	}

}
