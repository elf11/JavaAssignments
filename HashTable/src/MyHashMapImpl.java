import java.util.ArrayList;
import java.util.List;

/**
 * @author Niculaescu Oana
 * Clasa ce implementeaza interfata MyHashMap si metodele aferente acesteia.
 *
 */
public class MyHashMapImpl<K, V> implements MyHashMap<K,V> {
	
	int size = 0;
	
	ArrayList<Bucket<K, V>> table;
	
	/**
	 * Constructor intern ce initializeaza numarul de bucketuri continute de un HashMap, daca numarul de bucketuri este 0,
	 * atunci avem un map care contine doar bucketul null, altfel adaugam bucketuri in tabela de intrari.
	 * @param numBuckets - numarul total de bucketuri continute de tabela de intrari.
	 */
	
	public MyHashMapImpl(int numBuckets){
		if(numBuckets!=0){
			table = new ArrayList<Bucket<K,V>>();
			for (int i=0; i<numBuckets; i++)
				table.add(new Bucket<K,V>());
		}
		else table = null;
			
	}
	
	/**
	 * 
	 * @author Oana Niculaescu
	 * 	 
	 * @param <K> - K tipul cheilor din tabela
	 * @param <V> - tipul valorilor asociate cheilor din tabela
	 * 
	 * Implementarea interfetei Bucket<K,V>
	 */
	public static class Bucket<K, V> implements  MyHashMap.Bucket<K, V> {

		ArrayList<Entry<K, V>> b = new ArrayList<Entry<K,V>>();
		
		/**
		 * @param aux - intrarea ce trebuie adaugata in map
		 */
		public void add(MyHashMap.Entry<K, V> aux) {
			b.add((Entry<K, V>) aux);
		}
		/**
		 * Metoda ce intoarce intrarile dintr-un bucket, pentru a facilita accesul la acestea.
		 */
		@Override
		public List<? extends MyHashMap.Entry<K, V>> getEntries() {
			return b;
		}
		

	}
	
	/**
	 * 
	 * @author Oana Niculaescu
	 *
	 *  @param <K> - K tipul cheilor din tabela
	 * 	@param <V> - tipul valorilor asociate cheilor din tabela
	 * 
	 * Implementarea interfetei Entry<K,V>
	 */
	public static class Entry<K, V> implements MyHashMap.Entry<K, V> {
		K key;
		V value;
		
		/**
		 * 
		 * @param key - K cheia la care este introdus in map fiecare intrare.
		 * @param value - V valoarea asociata cheii, K key.
		 * Constructor implicit ce initializeaza un obiect de tip Entry cu atributele key si value.
		 */
		public Entry(K key, V value){
			this.key = key;
			this.value = value;
		}
		
		/**
		 * Metoda ce intoarce cheia asociata unei intrari.
		 */
		public K getKey(){
			return key;
		}
		
		/**
		 * Metoda ce intoarce valoare asociata unei intrari(respectiv chei).
		 */
		public V getValue(){
			return value;
		}
		
		
		@Override
		public boolean equals(Object a){
			if (!(a instanceof Entry))
				return false;
			if (value==null || value==((Entry)a).value || value.equals(((Entry)a).value)) 
				return (key==((Entry)a).key || key.equals(((Entry)a).key));
			return false;
		}
		
	}
		
	/**
	 * @param K - tipul cheilor din tabela
	 * Metoda ce intoarce valoarea asociata unei anumite chei din map.
	 */
		public V get(K key){
			
			if(table==null) return null;
			
			int bucketIndex = function(key);
			
			List<? extends Entry<K, V>> list = table.get(bucketIndex).b;
			for(Entry<K, V> entry: list)
				if(entry.getKey().equals(key)){
					return entry.getValue();}
				
			return null;
			
			
		}
		
		/**
		 * @param key - cheile din tabela
		 * @param value - valorile asociate cheilor din tabela
		 * Metoda ce introduce la o anumita cheie din map valoarea asociata.
		 */
		public V put(K key, V value) {
			
			int bucketIndex = function(key);
			
			if(table.get(bucketIndex)==null){	
				MyHashMap.Entry<K, V> aux = new Entry<K, V>(key, value);
				table.get(bucketIndex).add(aux);
				size++;
				
				return value;
			}
			
				
			List<? extends Entry<K, V>> list = table.get(bucketIndex).b;
			for(Entry<K, V> entry: list){
				if(key.equals(entry.getKey())){
					V aux = entry.value;
					entry.value = value;
					return aux;
					}
				}
					
			
			
			
			table.get(bucketIndex).add(new Entry<K, V>(key, value));
			
			size++;
			
			return null;
		}
		
		/**
		 * @param key - valorile din tabela de bucketuri
		 * Metoda ce scoate din map valoarea asociata unei anumite chei.
		 */
		public V remove(K key){
			int bucketIndex = function(key);
			
			if(table.get(bucketIndex)!=null){
				List<? extends Entry<K, V>> list = (List<? extends Entry<K, V>>) table.get(bucketIndex).getEntries();
				for(Entry<K, V> entry: list)
					if(entry.getKey().equals(key)){
						V a = entry.getValue();
						list.remove(new Entry(key,null));
						size--;
						return a;
					}
			}
			return null;
		}
		
		public int size(){
			return size;
		}
		
		/**
		 * Metoda ce intoarce tabela de bucketuri impreuna cu intrarile din aceasta.
		 * 
		 */
		@Override
		public List<? extends MyHashMap.Bucket<K, V>> getBuckets() {
			
			return table;
			
		}
		
		/**
		 * 
		 * @param key - cheile din tabela de bucketuri
		 * @return int number - un numar ce va fi folosit ca indice pentru inserarea cheii referita de key in tabela de bucketuri
		 * Metoda ce intoarce hash codul asociat unei anumite chei, daca numarul rezultat in urma aplicarii funcitei hashCode este negativ
		 * acesta se neaga si se aproximeaza prin adaos cu 1.
		 */
		public int function(K key){
			int number = key.hashCode()%(table.size());
			if(number >=0){
		        return number;
		     } else{
		        return (~number)+1;
		     }
		}

		

}


