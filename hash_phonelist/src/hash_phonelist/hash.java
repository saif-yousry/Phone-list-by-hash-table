package hash_phonelist;
public class hash {
    private int capacity;
    private int size;
    private HashNode[] Contacts;
    public hash(int cap) {
        capacity = cap;
        this.Contacts = new HashNode[capacity];
        this.size = 0;
    }
    public static class HashNode {
        Node node;
        boolean occupiedBefore = false;
        public HashNode(String name, String phone) {
            this.node = new Node(name, phone);
            this.occupiedBefore = true;
        }
        public static class Node {
            String name;
            String phone;
            Node(String n, String ph) {
                name = n;
                phone = ph;
            }
        }
    }
    private long hash_calc(String key) {
        int l = key.length();
        long hash = 0;
        for (int i = 0; i < l; i++) {
            hash += Character.getNumericValue(key.charAt(i));
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        if (hash > 0) return hash % capacity;
        else return -hash % capacity;
    }
    private boolean isFull() {
        return size == capacity;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    private void rehash() {
        System.out.println("Rehashing...");
        int oldCapacity = capacity;
        HashNode[] oldContacts = Contacts;
        capacity *= 2;
        Contacts = new HashNode[capacity];
        size = 0;
        for (HashNode node : oldContacts) {
            if (node != null && node.node != null) {
                insert(node.node.name, node.node.phone);
            }
        }
    }
    public void insert(String name, String phone) {
        if (isFull() || (double) size / capacity > 0.7) { 
            rehash();
        }
        if (search(name)!=-1) {
            System.out.println("The contact already exists");
            return;
        }
        int index = (int) hash_calc(name);
        int i = 0;
        while (Contacts[index] != null && Contacts[index].node != null) {
            index = (index + i * i) % capacity;
            i++;
        }
        Contacts[index] = new HashNode(name, phone);
        System.out.println("The contact is added successfully.");
        size++;
    }

    public void remove(String name) {
        int index = search(name);
        int i = 0;

        if (index==-1) {
            System.out.println("The contact with name " + name + " doesn't exist.");
            return;
        }
        Contacts[index].node=null;
        Contacts[index].occupiedBefore=false;
        size--;
        System.out.println("The contact is removed successfuly.");
    }

    public int search(String name) {
        int index = (int) hash_calc(name);
        int i = 0;

        while (Contacts[index] != null && Contacts[index].node != null) {
            if (Contacts[index].node.name.equalsIgnoreCase(name) && Contacts[index].occupiedBefore) {
                System.out.println("Found at index: " + index);
                System.out.println("Phone: " + Contacts[index].node.phone);
                return index;
            }
            index = (index + i * i) % capacity;
            i++;
        }
        return -1;
    }

    public void update(int index, String newPhone) {
        String old=Contacts[index].node.phone;
        Contacts[index].node.phone=newPhone;
        System.out.println("The contact's number updaed successfuly from "+old+" to "+newPhone+".");
    }

    void display() {
        System.out.println("----------------------------------");
        for (int i = 0; i < capacity; i++) {
            try {
                System.out.println("Name: " + Contacts[i].node.name + "\nPhone: " + Contacts[i].node.phone);
                System.out.println("At index: " + i);
                System.out.println("----------------------------------");
            } catch (NullPointerException e) {}
        }
        System.out.println("You have " + size() + " Contacts.");
    }
}
