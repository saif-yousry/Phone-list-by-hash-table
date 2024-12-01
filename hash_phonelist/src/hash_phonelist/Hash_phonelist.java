package hash_phonelist;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Hash_phonelist {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        hash list = new hash(1009);
        String name, phone;
        
        while (true) {
            int choice;
            System.out.println("""
                               
                    Choose an operation from below
                    1-Insert a Contact.
                    2-Search for a Contact.
                    3-Delete a Contact.
                    4-Update a Contact number.
                    5-Display all contacts.
                    6-Exit.""");
            try {
                choice = input.nextInt();
            }
            catch (InputMismatchException e) {
                choice = 0;
                input.next();
            }
            switch (choice) {
                case 1:
                    System.out.println("\nEnter a name: ");
                    name = input.next();
                    System.out.println("Enter a phone: ");
                    phone = input.next();
                    list.insert(name, phone);
                    break;
                case 2:
                    if(list.isEmpty()){
                        System.out.println("\nThe list is empty.");
                        break;
                    }
                    System.out.println("\nEnter the name for search:");
                    name = input.next();
                    if(!list.search(name))
                        System.out.println("\nThe contact doesn't exist.");
                    break;
                case 3:
                    if (list.isEmpty()){
                        System.out.println("The list is empty.");
                        break;
                    }
                    System.out.println("Enter the name for delete:");
                    name = input.next();
                    list.remove(name);
                    break;
                case 4:
                    if (list.isEmpty()){
                        System.out.println("The list is empty.");
                        break;
                    }
                    System.out.println("Enter the name to update:");
                    name = input.next();
                    if(!list.search(name)){
                        System.out.println("The contact doesn't exist.");
                        break;
                    }
                    System.out.println("Enter the updated phone number");
                    phone = input.next();
                    list.update(name, phone);
                    break;
                case 5:
                    if (list.isEmpty()){
                        System.out.println("The list is empty.");
                        break;
                    }
                    list.display();
                    break;
                
                case 6:
                    return;
                
            }

        }
    }
    
}
