package hash_phonelist;
public class hash {
    private final int capacity;
    private int size;
    private int removecounter;
    private final HashNode[] Contacts;
    public hash(int cap){
        capacity=cap;
        this.Contacts = new HashNode[capacity];
        this.size=0;
    }
    public static class HashNode{
        Node node;
        boolean occupiedBefore=false;

        public HashNode(String name, String phone){
            this.node = new Node(name, phone);
            this.occupiedBefore= true;
        }
        public static class Node{
            String name;
            String phone;
            Node(String n, String ph){
                name= n;
                phone= ph;
            }
        }
    }
    private long hash_calc(String key) {
        int i, l = key.length();
        long hash = 0;
        for (i = 0; i < l; i++) {
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
    private boolean isFull(){
        if(size == capacity){
            return true;
        }else
            return false;
        
    }
    public boolean isEmpty(){
        if(size == 0)
            return true;
        else
            return false;
    }
    public int size(){
        return size;
    }
    public void insert(String name,String phone){
        if(isFull()==true){
          System.out.println("The contact list is full.");
          return;
        }
        if(search(name)==true){
            System.out.println("The contact already exists");
            return;
        }
        int index=(int)hash_calc(name);
        int i=0;
        while(Contacts[index] != null && Contacts[index].node != null){
            index=(index+i*i) % capacity;
            ++i;
        }
        Contacts[index]= new HashNode(name, phone);
        System.out.println("The contact is added successfulty.");
        size++;
    }
    public void remove(String name){
        if(isEmpty()==true){
            System.out.println("The contacts list is empty.");
            return;
        }
        int index=(int)hash_calc(name);
        int i=0;
        boolean found=false;
        while(Contacts[index] != null && Contacts[index].node != null){
            if(Contacts[index].node.name.equalsIgnoreCase(name)&& Contacts[index].occupiedBefore){
                Contacts[index].node=null;
                Contacts[index].occupiedBefore=false;
                found=true;
                System.out.println("The contact with name "+name+" is removed successfully.");
                removecounter++;
                size--;
                break;
            }
            index=(index+i*i) % capacity;
            ++i;
        }
        if(found==false){
            System.out.println("The contact with name "+name+" doesn't exist.");
        }
    }
    public boolean search(String name){
        
        
        int index=(int)hash_calc(name);
        int i=0;
        while(Contacts[index] != null && Contacts[index].node != null){
            if(Contacts[index].node.name.equalsIgnoreCase(name) && Contacts[index].occupiedBefore) {
                System.out.println("found at index: "+index);
                System.out.println("Phone: " + Contacts[index].node.phone);
                return true;
            }
            index=(index +i*i) % capacity;
            ++i;
        }
        
        return false;
    }
    public void update(String name, String newPhone){
        int index=(int)hash_calc(name);
        int i =0;

        while(Contacts[index] != null && Contacts[index].node != null){
            if(Contacts[index].node.name.equalsIgnoreCase(name) && Contacts[index].occupiedBefore) {
                String oldPhone = Contacts[index].node.phone;
                Contacts[index].node.phone = newPhone;
                System.out.println("Contact updated from "+oldPhone+" to "+newPhone);
                return;
            }
            index= (int) ((index + i*i) % capacity);
        }
        
    }
    void display(){
        if(isEmpty()==true){
            System.out.println("Contacts are empty");
            return;
        }
        System.out.println("----------------------------------");
        for(int i=0; i<capacity; i++){
            try{
                System.out.println("Name: "+Contacts[i].node.name+"\nPhone: "+Contacts[i].node.phone);
                System.out.println("At index: "+i);
                System.out.println("----------------------------------");
            } 
            catch (NullPointerException e) {}
            
            }
        System.out.println("You have "+size()+" Contacts.");
    }
        
    }
    

