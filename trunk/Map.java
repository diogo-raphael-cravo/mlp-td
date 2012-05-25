package mlptd;

import java.util.Collection;

public class Map {
	/**
	 * Evita instancia��o.
	 */
	private Map() {
		throw new UnsupportedOperationException();
	}
	
	public static <E> void map(Collection<E> lista, Method<E> method) {
		for(E e : lista) {
			method.method(e);
		}
	}
}
