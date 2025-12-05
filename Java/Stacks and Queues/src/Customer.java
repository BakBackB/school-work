public class Customer implements Comparable<Customer>{
    private long arrivalTime, departureTime, totalTimeServed;
    public Customer(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime(long departureTime) {
        this.departureTime = departureTime;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public long getTotalTime() {
        return departureTime - arrivalTime;
    }

    @Override
    public int compareTo(Customer o) {
        return Integer.compare((int)arrivalTime, (int)o.arrivalTime);
    }
}
