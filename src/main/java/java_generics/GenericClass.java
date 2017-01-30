package java_generics;

/**
 * This is a generic class that operates on objects of only T
 *         type.
 * @param <T>
 *            the type of the value
 */
public class GenericClass<T> extends ParentClass {
	// T stands for "Type"
	private T t;

	public void set(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}

	protected void add(T t) {
		this.t = t;
	}
}
