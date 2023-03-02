import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * This class reads the csv file containing flight data, puts all of the information
 * into flight objects, and stores those objects in 2 of 3 ArrayLists. 
 * @author Robby Ballard
 * 
 * Class is called FileReader22 for reasons enumerated in the header to class Flights22.
 *
 */

public class FileReader22 {
    private ArrayList<Flights22> rawFlights = new ArrayList<Flights22>();
   
    public ArrayList<Flights22> filteredFlights = new ArrayList<Flights22>();
    
    public ArrayList<Flights22> cancelledFlights = new ArrayList<Flights22>();
    
    /**
     * Constructor for the class.
     * The filiteredFlights ArrayList stores all flight objects that have complete 
     * information and were not cancelled.
     * 
     * the canceledFlights ArrayList stores all Flight objects where the flight was canceled.
     * 
     * The rawFlights ArrayList is used to store all flight objects, both canceled as well as
     * finished, before they are filtered.
     */
    public FileReader22()  { 
        filteredFlights = filterFlights(rawFlights);
        try {
            rawFlights = makeList();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        cancelledFlights = makeCancelledFlights(rawFlights);
    }
    /**
     * This method reads the csv file and creates a flight object using only relevant information. 
     * Irrelevant information has been commented out, but not deleted, so that it can be used at 
     * a later date if necessary.
     * @return
     * @throws IOException
     */
    public ArrayList<Flights22> makeList() throws IOException{

        ArrayList<Flights22> rawFlights = new ArrayList<Flights22>();
        File file = new File("flights.csv");
        try {
            Scanner fileFinder = new Scanner(file);//Reads csv file
            
            while(fileFinder.hasNextLine()){
                String line = fileFinder.nextLine();
                
             
                String[] columnData = line.split(",");//Reads data delimited by commas
                    if(columnData[19].equals("0")) {
                    String dayOfMonth = (columnData[0]);
                    //String dayOfWeek = (columnData[1]);
                    //String flightDate = columnData[2];
                    String carrier = columnData[3];
                    String tailNum = columnData[4];
                    String originCode = (columnData[5]);
                    //String origin3L = columnData[6];
                    //String originState = columnData[7];
                    String destCode = (columnData[8]);
                    //String dest3L = columnData[9];
                    //String destState = columnData[10];
                    String depTime = (columnData[11]);
                    String depDelay = (columnData[12]);
                    //String wheelsOff = (columnData[13]);
                    //String wheelsOn = (columnData[14]);
                    String arrTime = (columnData[15]);
                    String arrDelay = (columnData[16]);
                    String cancel = (columnData[17]);
                    String cancelCode = columnData[18];
                    String diverted = (columnData[19]);
                    String airTime = (columnData[20]);
                    String distance = (columnData[21]);
                    
                    Flights22 flight = new Flights22(dayOfMonth, carrier, 
                            tailNum, originCode, destCode,
                            depTime, depDelay, arrTime, 
                            arrDelay, cancel, cancelCode, diverted, airTime, 
                            distance);
                    rawFlights.add(flight);
                }
            }
            fileFinder.close();
        }
        catch(FileNotFoundException e) {
            System.out.println(e);
        }
       filterFlights(rawFlights);//Sends the rawFlights to be filtered in filterFlights()
       makeCancelledFlights(rawFlights);//Sends the rawFlights to be filtered for cancellations.
       return this.rawFlights;
        }
    /**
     * This method is helper method that determines whether a flight object has 
     * a complete data set or not. If it does not have complete data, it is 
     * passed over and not included in the rawFlights ArrayList. If the data 
     * is complete, it is included in the rawFlights ArrayList.
     * 
     * @param flight A single Flight object
     * @return Returns a boolean verifying whether the flight object is missing data or not
     */
    public boolean isFullFilled(Flights22 flight) {
         
        if(flight.getAirTime().isEmpty() ||
                flight.getArrDelay().isEmpty() ||
                //flight.getArrTime().isEmpty() || 
                flight.getCancel().isEmpty() ||
                flight.getCarrier().isEmpty() ||
                flight.getDayOfMonth().isEmpty() ||
                //flight.getDayOfWeek().isEmpty() ||
                flight.getDepDelay().isEmpty() ||
                //flight.getDepTime().isEmpty() ||
                //flight.getDest3L().isEmpty() ||
                flight.getDestCode().isEmpty() ||
                //flight.getDestState().isEmpty()||
                flight.getDistance().isEmpty() ||
                flight.getDiverted().isEmpty() ||
                //flight.getFlightDate().isEmpty() ||
                //flight.getOrigin3L().isEmpty() ||
                flight.getOriginCode().isEmpty() ||
                //flight.getOriginState().contentEquals("") ||
                flight.getTailNum().isEmpty()){
                //flight.getWheelsOff().isEmpty() ||
                //flight.getWheelsOn().isEmpty()) {
            return false;
        }
        return true; 
    }
    public boolean isFinishedFlights(Flights22 flight){
        if(flight.getCancel().equals("0")) {
            return true;
        }
        return false; 
    }
    
    /**
     * This method takes the rawFlights ArrayList and removes flights 
     * that were canceled. Those that were not canceled and pass the 
     * isFullFilled() boolean as true, are included in the filteredFlights
     * ArrayList. 
     * 
     * @param flights The rawFlights ArrayList
     * @return filteredFlights An ArrayList of all flights that were not canceled.
     */
    public ArrayList<Flights22> filterFlights(ArrayList<Flights22> flights){
        for(Flights22 flight : flights) {
            if(isFinishedFlights(flight) && isFullFilled(flight)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }
    
    /**
     * This method takes the rawFlights ArrayList and searches for flights that 
     * were canceled. Those that were canceled are included in the 
     * cancelledFlights ArrayList.
     * @param flightList the rawFlights ArrayList
     * @return Returns an ArrayList of only canceled flights.
     */
    public ArrayList<Flights22> makeCancelledFlights(ArrayList<Flights22> flightList){
        
        for(Flights22 flight : flightList) {
            if(flight.getCancel().equals("1")) {
                cancelledFlights.add(flight);
            }
        }
        return this.cancelledFlights;
    }
}
     