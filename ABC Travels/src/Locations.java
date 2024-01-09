
public class Locations {
	String source;
	String destination;
	String date;
	int cost;
    public double getCost(int priceHike) {
    	double gst=((cost+priceHike)*18)/100;
    	return this.cost+priceHike+gst;
    }
}
