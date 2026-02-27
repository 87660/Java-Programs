// Multithreading Program
public class TicketBookingSimulator 
{
    // Shared resource
    static class TicketCounter 
    {
        private int availableTickets;

        public TicketCounter(int totalTickets) 
        {
            this.availableTickets = totalTickets;
        }

        // synchronized to prevent race conditions (overselling)
        public synchronized int bookTickets(int requested, String agentName) 
        {
            if (requested <= 0) 
            {
                // invalid request
                return 0;
            }
            if (availableTickets >= requested) 
            {
                availableTickets -= requested;
                System.out.printf("[SUCCESS] %s booked %d ticket(s). Tickets left: %d%n",
                                  agentName, requested, availableTickets);
                return requested;
            } 
            else 
            {
                System.out.printf("[FAILED ] %s requested %d ticket(s) but only %d left. Request denied.%n",
                                  agentName, requested, availableTickets);
                return 0;
            }
        }

        public int getAvailableTickets() 
        {
            return availableTickets;
        }
    }

    // Worker thread
    static class BookingAgent implements Runnable 
    {
        private final TicketCounter counter;
        private final String agentName;
        private final int[] requests; // sequence of requests this agent will attempt
        private int soldByThisAgent = 0;

        public BookingAgent(TicketCounter counter, String agentName, int[] requests) 
        {
            this.counter = counter;
            this.agentName = agentName;
            this.requests = requests;
        }

        @Override
        public void run() 
        {
            for (int req : requests) 
            {
                // Simulate some processing time to increase concurrency effect
                try 
                {
                    Thread.sleep(50); // 50 ms pause — small delay
                } 
                catch (InterruptedException e) 
                {
                    Thread.currentThread().interrupt();
                }

                int sold = counter.bookTickets(req, agentName);
                soldByThisAgent += sold;
            }
            System.out.printf("[DONE   ] %s finished. Total sold by %s = %d%n",
                              agentName, agentName, soldByThisAgent);
        }

        public int getSoldByThisAgent() 
        {
            return soldByThisAgent;
        }
    }

    // Main class
    public static void main(String[] args) 
    {
        int totalTickets = 10; // initial pool
        TicketCounter counter = new TicketCounter(totalTickets);

        // Each agent will try these requests in order
        int[] reqsAgentA = {2, 1, 2};     // total requested 5
        int[] reqsAgentB = {1, 3, 1};     // total requested 5
        int[] reqsAgentC = {4, 2};        // total requested 6

        BookingAgent agentA = new BookingAgent(counter, "Agent-A", reqsAgentA);
        BookingAgent agentB = new BookingAgent(counter, "Agent-B", reqsAgentB);
        BookingAgent agentC = new BookingAgent(counter, "Agent-C", reqsAgentC);

        Thread tA = new Thread(agentA);
        Thread tB = new Thread(agentB);
        Thread tC = new Thread(agentC);

        // Start threads
        tA.start();
        tB.start();
        tC.start();

        // Wait for all agents to finish
        try 
        {
            tA.join();
            tB.join();
            tC.join();
        } 
        catch (InterruptedException e) 
        {
            Thread.currentThread().interrupt();
        }

        // Print Summary
        int soldTotal = agentA.getSoldByThisAgent()
                        + agentB.getSoldByThisAgent()
                        + agentC.getSoldByThisAgent();
        System.out.println("==========================================");
        System.out.println("Simulation complete.");
        System.out.println("Initial tickets : " + totalTickets);
        System.out.println("Total tickets sold : " + soldTotal);
        System.out.println("Tickets remaining   : " + counter.getAvailableTickets());
        System.out.println("------------------------------------------");
        System.out.println("Per-agent sold:");
        System.out.println(" Agent-A : " + agentA.getSoldByThisAgent());
        System.out.println(" Agent-B : " + agentB.getSoldByThisAgent());
        System.out.println(" Agent-C : " + agentC.getSoldByThisAgent());
        System.out.println("==========================================");
    }
}