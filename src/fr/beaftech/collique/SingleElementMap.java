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
import java.util.Map;
import java.util.Set;

/**
 * Single key/value pair implementation of {@code Map}. This implementation
 * permits {@code null} values and the {@code null} key, and provides all
 * optional map operations with some restrictions on element addition as this
 * map can only contains one key/value pair. More precisely,
 * {@link #put(Object, Object)} and {@link #putAll(Map)} operations will throw
 * {@code UnsupportedOperationException} when trying to add another key/value
 * pair to this map.
 * 
 * @author Arnould Guidat
 *
 * @param <K>
 *            the key type
 * @param <V>
 *            the value type
 */
public class SingleElementMap<K, V> implements Map<K, V> {

	private Entry<K, V> entry;

	public static class DefaultEntry<K, V> implements Entry<K, V> {

		private K key;
		private V value;

		public DefaultEntry() {
			key = null;
			value = null;
		}

		public DefaultEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V previous = this.value;
			this.value = value;
			return previous;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
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
			DefaultEntry<?, ?> other = (DefaultEntry<?, ?>) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

	}

	public SingleElementMap() {
		entry = null;
	}

	public SingleElementMap(K key, V value) {
		entry = new DefaultEntry<K, V>(key, value);
	}

	private K getKey() {
		return isEmpty() ? null : entry.getKey();
	}

	private V getValue() {
		return isEmpty() ? null : entry.getValue();
	}

	private V setValue(V value) {
		return entry.setValue(value);
	}

	@Override
	public int size() {
		return isEmpty() ? 0 : 1;
	}

	@Override
	public boolean isEmpty() {
		return entry == null;
	}

	@Override
	public boolean containsKey(Object key) {
		return isEmpty() ? false : getKey().equals(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return isEmpty() ? false : getValue().equals(value);
	}

	@Override
	public V get(Object key) {
		if (containsKey(key))
			return getValue();
		return null;
	}

	@Override
	public V put(K key, V value) {
		if (isEmpty()) {
			entry = new DefaultEntry<K, V>(key, value);
			return null;
		}

		if (containsKey(key)) {
			return setValue(value);
		}
		throw new UnsupportedOperationException(
				"Cannot add more than one element");

	}

	@Override
	public V remove(Object key) {
		if (containsKey(key)) {
			V removed = getValue();
			entry = null;
			return removed;
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
			put(e.getKey(), e.getValue());
		}

	}

	@Override
	public void clear() {
		entry = null;

	}

	@Override
	public Set<K> keySet() {
		if (isEmpty())
			return Collections.emptySet();
		return new SingleElementSet<K>(getKey());
	}

	@Override
	public Collection<V> values() {
		if (isEmpty())
			return Collections.emptyList();
		return new SingleElementList<V>(getValue());
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		if (isEmpty())
			return Collections.emptySet();
		return new SingleElementSet<Map.Entry<K, V>>(entry);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entry == null) ? 0 : entry.hashCode());
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
		SingleElementMap<?, ?> other = (SingleElementMap<?, ?>) obj;
		if (entry == null) {
			if (other.entry != null)
				return false;
		} else if (!entry.equals(other.entry))
			return false;
		return true;
	}

}
