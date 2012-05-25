package mlptd;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Map {
	/**
	 * Evita instanciação.
	 */
	private Map() {
		throw new UnsupportedOperationException();
	}
	
	public static <E> void map(Collection<E> lista, Method<E> method) {
		Collection<E> copiaLista = new LinkedList<E>(lista);
		Map.recursiveMap(copiaLista, method);
		//for(E e : lista) {
		//	method.method(e);
		//}
	}
	
	private static <E> void recursiveMap(Collection<E> lista, Method<E> method) {
		Iterator<E> iterator = lista.iterator();
		if(iterator.hasNext()) {
			E head = iterator.next();
			method.method(head);
			iterator.remove();
			head = null;
			iterator = null;
			Map.recursiveMap(lista, method);
		}
	}
}
