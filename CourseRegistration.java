import java.util.*;
public class CourseRegistration 
{
    private final int capacity;
    private final List<String> registeredList;
    private final Set<String> registeredSet;
    private final Queue<String> waitlist;
    private final Map<String, String> studentDetails;

    public CourseRegistration(int capacity) 
    {
        this.capacity = capacity;
        this.registeredList = new ArrayList<>();
        this.registeredSet = new HashSet<>();
        this.waitlist = new LinkedList<>();
        this.studentDetails = new HashMap<>();
    }

   
    public void registerStudent(String name, String email) 
    {
        System.out.println("Registering: " + name + " (" + email + ")");
        
        
        if (registeredSet.contains(email) || waitlist.contains(email)) 
        {
            System.out.println(" -> Already registered or waitlisted.\n");
            return;
        }

        studentDetails.put(email, name);

        if (registeredList.size() < capacity) 
        {
            registeredList.add(email);
            registeredSet.add(email);
            System.out.println(" -> Successfully registered.\n");
        } else 
        {
            waitlist.add(email);
            System.out.println(" -> Seats full. Added to waitlist.\n");
        }
    }

    
    public void cancelRegistration(String email) 
    {
        System.out.println("Cancelling registration for: " + email);

        if (registeredSet.contains(email)) 
        {
            registeredSet.remove(email);
            registeredList.remove(email);
            studentDetails.remove(email);
            System.out.println(" -> Registration cancelled.");

            if (!waitlist.isEmpty()) 
            {
                String nextEmail = waitlist.poll();
                registeredList.add(nextEmail);
                registeredSet.add(nextEmail);
                System.out.println(" -> " + studentDetails.get(nextEmail) + " moved from waitlist to registered.\n");
            } else 
            {
                System.out.println();
            }
        } else if (waitlist.remove(email)) 
        {
            studentDetails.remove(email);
            System.out.println(" -> Removed from waitlist.\n");
        } else 
        {
            System.out.println(" -> No record found.\n");
        }
    }

   
    public void showDetails() 
    {
        System.out.println("===== Course Registration Summary =====");
        System.out.println("Capacity: " + capacity);
        System.out.println("Registered (" + registeredList.size() + "):");

        if (registeredList.isEmpty())
            System.out.println("  None");
        else
            for (String email : registeredList)
                System.out.println("  " + studentDetails.get(email) + " (" + email + ")");

        System.out.println("\nWaitlist (" + waitlist.size() + "):");
        if (waitlist.isEmpty())
            System.out.println("  None");
        else
            for (String email : waitlist)
                System.out.println("  " + studentDetails.get(email) + " (" + email + ")");
        System.out.println("=======================================\n");
    }

    public static void main(String[] args) {
        CourseRegistration cr = new CourseRegistration(3);

        cr.registerStudent("Alice", "alice@gmail.com");
        cr.registerStudent("Bob", "bob@gmail.com");
        cr.registerStudent("Carol", "carol@gmail.com");
        cr.registerStudent("David", "david@gmail.com");
        cr.registerStudent("Eve", "eve@gmail.com");

        cr.showDetails();

        cr.cancelRegistration("bob@gmail.com"); 
        cr.showDetails();

        cr.cancelRegistration("eve@gmail.com"); 
        cr.showDetails();
    }
}