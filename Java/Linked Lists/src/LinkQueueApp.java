

public class LinkQueueApp {
    public static void main(String[] arg) {
        final int NUM_CUSTOMERS = 7;
        long cashierTime = 0, totalTime = 0, 
        count = NUM_CUSTOMERS - 1,
        start, depart;
        double rateOfCustomer;
        LinkQueue<Customer> customerQueue = new LinkQueue<>(NUM_CUSTOMERS);
        customerQueue.setProcessingTime(30);
        
        for(int i = 0; i < NUM_CUSTOMERS; i++) {
        customerQueue.insert(new Customer(15*i));
        }
        
        while(!customerQueue.isEmpty()) {
        Customer customer = customerQueue.remove();
        System.out.println("Arrival time of the customer "
                + (NUM_CUSTOMERS - count) + ": " + customer.getArrivalTime());
        count--;
        if(customer.getArrivalTime() > cashierTime) {
            start = customer.getArrivalTime();
        } else {
            start = cashierTime;
            }
            depart = start + customerQueue.getProcessingTime();
            customer.setDepartureTime(depart);
            cashierTime = depart;
            totalTime += customer.getTotalTime();
            System.out.println("Time from waiting to done being served: "
                    + customer.getTotalTime() + " seconds");
        }
        rateOfCustomer = (double)60*NUM_CUSTOMERS/totalTime;

        System.out.println("The total time for processing all the customers is "
            + totalTime + " seconds");
        System.out.println("The rate at which customers arrive at the queue is "
        + rateOfCustomer + " customer/minute");
    }
}