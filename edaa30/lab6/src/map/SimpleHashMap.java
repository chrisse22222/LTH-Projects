package map;

public class SimpleHashMap <K,V> implements Map<K,V> {

    private Entry<K,V> [] table;
    private int size;
    private int slotsFilled;

    public SimpleHashMap(){
        table = (Entry<K,V> []) new Entry[16];
        size = 0;
        slotsFilled = 0;
    }

    public SimpleHashMap(int capacity){
        table = (Entry<K,V> []) new Entry[capacity];
        size = 0;
        slotsFilled = 0;
    }

    @Override
    public V get(Object arg0) {
        Entry<K,V> node = find(index((K) arg0), (K) arg0);
        return node == null ? null : node.getValue();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K arg0, V arg1) {
        int index = index(arg0);
        Entry<K,V> head = table[index];
        Entry<K,V> newNode = new Entry<>(arg0, arg1);

        if (head == null){ // Finns det ingen head, ökas antalet fyllda platser
            slotsFilled++;
        } else{
            Entry<K,V> node = find(index, arg0);
            if (node != null){ // Annars kollar den först om nyckeln redan finns
                V value = node.value;
                node.setValue(arg1);
                return value;
            }

            newNode.next = head; // om den inte redan fanns, sätter den att nya noden pekar på tidigare headern
        }

        size++;
        table[index] = newNode;
        rehash();
        return null;
    }

    private void rehash(){
        if (((float) slotsFilled / table.length) >= 0.75){
            Entry<K, V> [] temp = table;
            table = (Entry<K,V> []) new Entry[temp.length * 2];

            slotsFilled = 0;
            size = 0;
            for (int i = 0; i < temp.length; i++) {

                Entry<K,V> head = temp[i]; // ankaren
                while (head != null){
                    put(head.key, head.value);
                    head = head.next;
                }
            }
        }
    }

    @Override
    public V remove(Object arg0) {
        if (table == null) // om tabellen är null
            return null;

        int index = index((K)arg0);
        Entry<K,V> head = table[index]; // hämtar head på givna index
        Entry<K,V> elementToRemove = find(index, (K) arg0); // letar efter elementet som ska tas bort
        if (elementToRemove == null){ // om den inte hittade någon key
            return null;
        } else{
            size--;
            if (head.value.equals(elementToRemove.value)){ // om headern är det som ska tas bort
                table[index] = head.next; // flytar referensen till det som headern pekar på, blir null om next är null
                if (head.next == null)
                    slotsFilled--;

                return elementToRemove.value;
            }

            Entry<K,V> prev = null, current = head; // skapar nuvarande (current) som refererar till head och föregående (prev)
            while (current != null && !current.value.equals(elementToRemove.value)){
                prev = current; // föregående är lika med nuvarande
                current = current.next; // nuvuarande stegrar uppåt
            }

            prev.next = current.next; // sätter att föregående pekar på nuvarandes element nästa värde
            return elementToRemove.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    private int index(K key){
        int index = key.hashCode() % table.length; // key måstle implementera hashcode
        if (index < 0){
            index += table.length;
        }

        return index;
    }

    private Entry<K,V> find (int index, K key){
        Entry<K,V> head = table[index];
        while (head != null){
            if (head.key.equals(key)){
                return head;
            }

            head = head.next;
        }

        return null;
    }

    public String show(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            Entry<K,V> head = table[i];
            sb.append("Index: " + i + "  ");
            while (head != null){
                sb.append(head);
                head = head.next;
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    private static class Entry<K,V> implements Map.Entry<K,V>{
        private K key;
        private V value;
        private Entry<K,V> next;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
            next = null;
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
            this.value = value;
            return value;
        }

        @Override
        public String toString() {
            return key + "=" + value + " ";
        }
    }
}
