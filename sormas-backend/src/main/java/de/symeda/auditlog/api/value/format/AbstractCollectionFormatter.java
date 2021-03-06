package de.symeda.auditlog.api.value.format;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import de.symeda.auditlog.api.value.ValueContainer;

/**
 * Formats a Collection of Enums as a String.<br />
 * The method {@link #toSortedList(Collection)} provides a reliable sorting so that sorting it
 * again does not result in a changed formatted String.<br />
 * Formatting:
 * <ul>
 * <li><code>null</code>: <code>{@link #getNullString()}</code></li>
 * <li>0 values: <code>0 []</code></li>
 * <li>1 value: <code>1 [value1]</code></li>
 * <li>N values: <code>n [value1, ..., valueN]</code> (sorted according to <code>{@link #toSortedList(Collection)}</code>)</li>
 * </ul>
 * 
 * @author Stefan Kock
 * @param <V>
 * 			Type of the {@code value} to be formatted within the {@link Collection}.
 */
public abstract class AbstractCollectionFormatter<V> implements CollectionFormatter<V> {

	private final String nullString;
	private final ValueFormatter<V> valueFormatter;

	/**
	 * <code>nullString = {@link ValueContainer#DEFAULT_NULL_STRING}</code>
	 * 
	 * @param valueFormatter
	 * 			The {@link ValueFormatter} to format the individual values with.
	 */
	public AbstractCollectionFormatter(ValueFormatter<V> valueFormatter) {

		this(ValueContainer.DEFAULT_NULL_STRING, valueFormatter);
	}

	/**
	 * @param nullString
	 * 			Placeholder for {@code null} values.
	 * @param valueFormatter
	 * 			The {@link ValueFormatter} to format the individual values with.
	 */
	public AbstractCollectionFormatter(String nullString, ValueFormatter<V> valueFormatter) {

		this.nullString = nullString;
		this.valueFormatter = valueFormatter;
	}

	@Override
	public String getNullString() {
		return nullString;
	}

	@Override
	public String format(Collection<V> valueCollection) {

		final StringBuilder sb = new StringBuilder();

		sb.append(valueCollection.size());
		sb.append(" ");
		sb.append(PREFIX);

		Iterator<V> iterator = toSortedList(valueCollection).iterator();
		while (iterator.hasNext()) {
			V value = iterator.next();
			sb.append(value == null ? getNullString() : valueFormatter.format(value));
			if (iterator.hasNext()) {
				sb.append(SEPARATOR);
			}
		}

		sb.append(SUFFIX);

		return sb.toString();
	}

	/**
	 * Sorts the {@link Collection} to format prior to the formatting.
	 * Provides a reliable sorting so that sorting it again does not result in a changed formatted String.
	 * 
	 * @param valueCollection
	 * 			{@link Collection} to format.
	 * @return	Sorted values.
	 */
	protected abstract List<V> toSortedList(Collection<V> valueCollection);
}
