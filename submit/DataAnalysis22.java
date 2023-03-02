import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
/**
 * 
 * @author Robby Ballard
 * This class answers questions 1-9, creating a completed flight array and a cancelled flight array from the FileReader22 class.
 * 
 * Class is called DataAnalysis22 for reasons enumerated in the header to class Flights22.
 */
public class DataAnalysis22 {
    
    FileReader22 FR22 = new FileReader22();//Creates FileReader22 (henceforth FR22) object
    private final ArrayList<Flights22> forgottenFlights;//Creates an array of cancelled flights
    private final ArrayList<Flights22> completedFlights;//Creates an array of completed flights
   
    /**
     * Class constructor. 
     */
    public DataAnalysis22() {
        forgottenFlights = FR22.cancelledFlights;
        completedFlights = FR22.filteredFlights;
    }
    
    /**
     * 
     * @param airlines An ArrayList of all airline codes
     * @param forgottenFlights An ArrayList of cancelled flight objects
     * @return Returns a HashMap of airline codes (keys) and a tally of cancellations (values) for each
     * 
     * This is a helper method for QUESTION 1
     */
   public HashMap<String, Integer> makeCancellationCountHash(ArrayList<String> airlines, ArrayList<Flights22> forgottenFlights){
    HashMap<String, Integer> cancellationCountHash = new HashMap<String, Integer>();
       
       for(String carrier : airlines) {
           int tally = 0;
           for(Flights22 flight : forgottenFlights) {
               if(flight.getCarrier().equals(carrier)) {
                   tally++;
               }
           cancellationCountHash.put(carrier, tally);
           }
       }
       return cancellationCountHash;
   }
   /**
    * 
    * @param airlines ArrayList of all airline codes
    * @param fulfilledFlights ArrayList of all completed flight objects
    * @return Returns a HashMap with Airline Codes (keys) mapped to their respective number of completed flights (values)
    * 
    * This method returns the total number of cancelled flights for all airlines
    * Helper Method for QUESTION 1
    */
   
   public HashMap<String, Integer> makeCompletedCountHash(ArrayList<String> airlines, ArrayList<Flights22> fulfilledFlights){
       HashMap<String, Integer> completedCountHash = new HashMap<String, Integer>();
       
       for(String carrier : airlines) {
           int tally = 0;
           for(Flights22 flight : fulfilledFlights) {
               if(flight.getCarrier().equals(carrier)) {
                   tally++;
               }
           completedCountHash.put(carrier, tally);
           }
       }
       return completedCountHash;
   }  
   
   /**
    * 
    * @return Returns a String in the format of AIRLINE CODE, CANCELLATION %
    * @throws NoSuchElementException I was getting a NoSuchElementException, so I included this, and it seems to work.
    * 
    * This method initiates and uses the CompletedCount and CanceledCount HashMaps. It scans the Completed HashMap and the Canceled HashMap, taking the total number 
    * of canceled flights and dividing that by the sum of canceled flights and completed flights.
    * It then maps the Airline Code as a Key to its canceled percentage as a Value. 
    */
   
    //                    //
   //ANSWERS QUESTION (1)//
  //                    //
   public String makeCancellationPercentage() throws NoSuchElementException {
       HashMap<String, Integer> completedHash = makeCompletedCountHash(castOfCarriers(completedFlights), completedFlights);
       HashMap<String, Integer> cancelledHash = makeCancellationCountHash(castOfCarriers(completedFlights), forgottenFlights);
       HashMap<String, Double> percentageHash = new HashMap<String, Double>();

       for(String comCarrierCode : completedHash.keySet()) {
           for(String canCarrierCode : cancelledHash.keySet()) {
               if(comCarrierCode.equals(canCarrierCode)) {
                   percentageHash.put(comCarrierCode, ((Double.valueOf(cancelledHash.get(canCarrierCode)) / 
                           (Double.valueOf((cancelledHash.get(canCarrierCode)) + completedHash.get(comCarrierCode))))));
               }
           }
       }
       String maxCancelCarrier = Collections.max(percentageHash.entrySet(), Map.Entry.comparingByValue()).getKey();
       
       Double maxCancelPercent = percentageHash.get(maxCancelCarrier) * 100;
       
       String answer = maxCancelCarrier + ", " + Double.toString(maxCancelPercent) + "%";
       completedHash = null;
       cancelledHash = null;
       percentageHash = null;
       return answer;   
   }
   /**
    * 
    * @param finishedFlights Uses an ArrayList of completed flights
    * @return Returns an ArrayList of all airline codes
    * 
    * This method is a helper method that scans all flight objects and puts their carrier codes into an ArrayList if the ArrayList does not already
    * contain the code.
    * 
    * This is a Helper Method for QUESTION 1
    */
   public ArrayList<String> castOfCarriers(ArrayList<Flights22> finishedFlights){
       ArrayList<String> carriers = new ArrayList<String>();
       
       for(Flights22 flight : finishedFlights) {
           if(!(carriers.contains(flight.getCarrier()))){
               carriers.add(flight.getCarrier());
       }
       }
       return carriers;
   }
   
   /**
    * 
    * @param finishedFlights Uses an ArrayList of all completed Flight objects
    * @return Returns an ArrayList of all Tail Numbers
    * 
    * This method scans all completed flights and records tail numbers in an ArrayList of the list does not 
    * already contain the unique tail number  
    * 
    * This is a Helper Method for QUESTION 3
    */
   public ArrayList<String> allTailNums(ArrayList<Flights22> finishedFlights){
       ArrayList<String> tailNums = new ArrayList<String>();
       
       for(Flights22 flight : finishedFlights) {
           if(!(flight.getTailNum().equals("")) && !(tailNums.contains(flight.getTailNum()))) {
               tailNums.add(flight.getTailNum());
           }
       }
       return tailNums;
   }
   
   /**
    * 
    * @return Returns the cancelCode that appears the most in all of the cancelledFlight objects
    * This method is self contained and answers Question 2. It goes through all of the Flight objects in the 
    * cancelledFlights ArrayList and tallys all of the cancellation codes. Then, it finds the code with the maximum
    * value and returns it. 
    * 
    */
    

   //                    //
  //ANSWERS QUESTION (2)//
 //                    //
   public String cancelCodeCount() {
       int cancelCountA = 0;
       int cancelCountB = 0;
       int cancelCountC = 0;
       int cancelCountD = 0;
       String cancelCountKing = "";
    
       
       for(Flights22 flight : forgottenFlights) {
           if(flight.getCancelCode().contains("A")) {
               cancelCountA++;
           }
           else {
               if(flight.getCancelCode().contains("B")) {
                   cancelCountB++;
               }
               if(flight.getCancelCode().contains("C")) {
                   cancelCountC++;
               }
               if(flight.getCancelCode().contains("D")) {
                   cancelCountD++;
               }
           }
           if(cancelCountA > cancelCountB && cancelCountA > cancelCountC && cancelCountA > cancelCountD) {
               cancelCountKing = "A";
           }
           if(cancelCountB > cancelCountA && cancelCountB > cancelCountC && cancelCountB > cancelCountD) {
               cancelCountKing = "B";
           }
           if(cancelCountC > cancelCountA && cancelCountC > cancelCountB && cancelCountC > cancelCountD) {
               cancelCountKing = "C";
           }
           if(cancelCountD > cancelCountA && cancelCountD > cancelCountB && cancelCountD > cancelCountC) {
               cancelCountKing = "D";
           }
    }
    return cancelCountKing;
         
   }

       /*
        * Make a hashmap that uses an arraylist of each individual tailnum as the keys. Then, a for loop goes through all
        * of the flight objects and searches for each tailnum. When it finds a tailnum, it gets the miles flown and adds
        * them to the value in the hashmap associated with the proper tailnum key.
        */  
       
   /**
    * Above comment is a note to self
    * @return Returns the Tail Number that recorded the most miles
    * 
    * This method uses the ArrayList from allTailNums() and creates a HashMap that uses tailNum s as Keys and a sum of each tailNum's miles as a value.
    * It then finds the maximum value in the HashMap, and outputs the corresponding tailNum as a return.
    * 
    */
     //                    //
    //ANSWERS QUESTION (3)//
   //                    //
   public String tailTally() {
       HashMap<String, Integer> tailNumTallyHash = new HashMap<String, Integer>();
       ArrayList<Flights22> flight = completedFlights;
       ArrayList<String> tailArray = allTailNums(completedFlights);
       String maxTailNum= "";
       int tailNumSum = 0;
       
       for(String tail : tailArray) {
           tailNumSum = 0;
           for(Flights22 flyer : flight) {
               int miles = 0;
               if(tail.equals(flyer.getTailNum())) {
                   miles = Integer.parseInt(flyer.getDistance());
                   tailNumSum += miles;  
               }
               tailNumTallyHash.put(tail, tailNumSum);
           }
       }
       maxTailNum = Collections.max(tailNumTallyHash.entrySet(), Map.Entry.comparingByValue()).getKey();

       return maxTailNum;
   }

      /**
       * 
       * @param finishedFlights This method uses an ArrayList of all completed flights
       * @return This method returns an ArrayList of all airport originCodes. 
       * It iterates through the completedFlights ArrayList and adds the origin 
       * code from each Flight object to a new ArrayList of the origin code is not already present.
       */
   public ArrayList<String> airportArray(ArrayList<Flights22> finishedFlights){
       ArrayList<String> allAirports = new ArrayList<String>();
       
       for(Flights22 flight : finishedFlights) {
           if(!(allAirports.contains(flight.getOriginCode()))) {
               allAirports.add(flight.getOriginCode());
           }
       }
       return allAirports;
   }   
   
   /**
    * 
    * @return Returns the airport code with the largest sum of inbound and outbound flights.
    * This method uses HashMaps made in two helper methods to take the number of departing flights 
    * and the number of arriving flights from each respective HashMap and put this sum as a value 
    * into another HashMap, totalMap, using the corresponding airport code as a key. This method 
    * then searches through the HashMap for the largest value and retruns the associated key.
    */
   
   
   public String airportAccruer() {
       ArrayList<String> allAirports = airportArray(completedFlights);
       HashMap<String, Integer> airportArrivalHash = makeAirportArrivalHash(allAirports, completedFlights);
       HashMap<String, Integer> airportDepartureHash = makeAirportDepartureHash(allAirports, completedFlights);
       HashMap<String, Integer> totalMap = new HashMap<String, Integer>(airportArrivalHash);
       
       for(String airportCode : airportDepartureHash.keySet()) {
           if(totalMap.containsKey(airportCode)) {
               if(totalMap.containsKey(airportCode)) {
                   totalMap.put(airportCode, airportDepartureHash.get(airportCode) + totalMap.get(airportCode));
           }else {
               totalMap.put(airportCode,  airportDepartureHash.get(airportCode));
           }
           }
       }
       String maxNum = Collections.max(totalMap.entrySet(), Map.Entry.comparingByValue()).getKey();

       return maxNum;
   }
   /**
    * 
    * @param airports An ArrayList of all airport codes
    * @param finishedFlights An ArrayList of all Flight objects that completed their routes
    * @return Returns a HashMap of airport codes (keys) and each airport's corresponding number of arrivals (values)
    * 
    * This method searches through an ArrayList of completed flights and each time an airport codes is listed as an arrival
    * destination, a tally for the corresponding airport code is incremented. 
    * 
    * This is a Helper Method for QUESTION 4, 5, and 6
    */

   public HashMap<String, Integer> makeAirportArrivalHash(ArrayList<String> airports, ArrayList<Flights22> finishedFlights){
       HashMap<String, Integer> airportArrivalHash = new HashMap<String, Integer>();
       
       for(String airportCode : airports) {
           int tally = 0;
           for(Flights22 flight : finishedFlights) {
               if(flight.getDestCode().equals(airportCode)) {
                   tally++;
               }
           airportArrivalHash.put(airportCode, tally);
           }
       }
       return airportArrivalHash;
   }
  /**
   * 
   * @param airports An ArrayList of all airport codes
   * @param finishedFlights An ArrayList of all Flight objects that completed their routes
   * @return Returns a HashMap of airport codes (keys) and each airport's corresponding number of departures (values)
   * 
   * This method searches through an ArrayList of completed flights and each time an airport codes is listed as a departure
   * point, a tally for the corresponding airport code is incremented.
   * 
   * This is a Helper Method for QUESTION 4, 5, and 6
   */
   public HashMap<String, Integer> makeAirportDepartureHash(ArrayList<String> airports, ArrayList<Flights22> finishedFlights){
       HashMap<String, Integer> airportDepartureHash = new HashMap<String, Integer>();
       for(String airportCode : airports) {
           int tally = 0;
           for(Flights22 flight : finishedFlights) {
               if(flight.getOriginCode().equals(airportCode)) {
                tally++;   
               }
           airportDepartureHash.put(airportCode, tally);
           }
   }
       return airportDepartureHash;
   }
   /**
    * 
    * @param finishedFlights 
    * @return
    * 
    * This method uses HashMaps made in two helper methods to take the number of departing flights 
    * and the number of arriving flights from each respective HashMap and put this sum as a value 
    * into another HashMap, totalMap, using the corresponding airport code as a key. This method 
    * then searches through the HashMap for the largest value and returns the associated key.
    * 
    * This method is a Helper Method for QUESTION 4.
    */
   
   public HashMap<String, Integer> maxMover(ArrayList<Flights22> finishedFlights) {
       ArrayList<String> airportList = airportArray(finishedFlights);
       HashMap<String, Integer> airportArrs = makeAirportArrivalHash(airportList, finishedFlights);
       HashMap<String, Integer> airportDeps = makeAirportDepartureHash(airportList, finishedFlights);
       HashMap<String, Integer> maxMoverAirports = new HashMap<>(airportArrs);
  
       airportDeps.forEach((key, value) -> maxMoverAirports.merge(key, value, (v1, v2) -> (v1 + v2)));
     
       return maxMoverAirports;
   }
   /**
    * @return Returns the airport code with the largest sum of inbound and outbound flights. 
    *  
    *  This method takes the HashMap made in maxMover() and finds the largest value, then it returns 
    *  the corresponding key as an answer to QUESTION 4. 
    * 
    */
   
   //                    //
  //ANSWERS QUESTION (4)//
 //                    //  
   public String busiestAirport() {
       HashMap<String, Integer> busiestAirport = maxMover(completedFlights);
       String maxNum = Collections.max(busiestAirport.entrySet(), Map.Entry.comparingByValue()).getKey();
       
       return maxNum;
   }
   /**
    * This method takes HashMaps of airport codes mapped to their corresponding sums
    * of departures and arrivals. It uses these HashMaps to take the number of arrivals
    * and subtract it from the number of departures. It then puts this value into a new 
    * HashMap, busiestHash, where it is mapped to its corresponding airport code. The method 
    * then searches the HashMap for the largest value and returns the corresponding airport code. 
    * 
    * 
    * @return Returns the airport code with largest number of departures, independent of arrivals.
    */
   
   
   //                    //
  //ANSWERS QUESTION (5)//
 //                    //  
   public String biggestSource() {
       ArrayList<String> airportList = airportArray(completedFlights);
       HashMap<String, Integer> airportArrs = makeAirportArrivalHash(airportList, completedFlights);
       HashMap<String, Integer> airportDeps = makeAirportDepartureHash(airportList, completedFlights);
       HashMap<String, Integer> busiestHash = new HashMap<>();
       
       for(String airportCode : airportList) {
           int sourceDiff = airportDeps.get(airportCode) - airportArrs.get(airportCode);
           busiestHash.put(airportCode, sourceDiff);
       }
       String maxSource = Collections.max(busiestHash.entrySet(), Map.Entry.comparingByValue()).getKey();
       return maxSource;
   }
   /**
    * This method takes HashMaps of airport codes mapped to their corresponding sums
    * of departures and arrivals. It uses these HashMaps to take the number of departures
    * and subtract it from the number of arrivals. It then puts this value into a new 
    * HashMap, sinkHash, where it is mapped to its corresponding airport code. The method 
    * then searches the HashMap for the largest value and returns the corresponding airport code.
    * 
    *  
    * @return Returns the airport code with largest number of arrivals, independent of departures.
    */
   
   //                    //
  //ANSWERS QUESTION (6)//
 //                    //  
   public String biggestSink() {
       ArrayList<String> airportList = airportArray(completedFlights);
       HashMap<String, Integer> airportArrs = makeAirportArrivalHash(airportList, completedFlights);
       HashMap<String, Integer> airportDeps = makeAirportDepartureHash(airportList, completedFlights);
       HashMap<String, Integer> sinkHash = new HashMap<>();
       
       for(String airportCode : airportList) {
           int sinkDiff = airportArrs.get(airportCode) - airportDeps.get(airportCode);
           sinkHash.put(airportCode, sinkDiff);
       }
       //airportList.clear();

       String maxSource = Collections.max(sinkHash.entrySet(), Map.Entry.comparingByValue()).getKey();
       return maxSource;
   }
   
   /**
    * This method searches all Flight objects in the completedFlights ArrayList and finds those with carrierCode "AA"
    * that had arrival or departure delays of more than or equal to 60 minutes.
    * 
    * 
    * @return This method returns the number of flights delayed by 60 minutes or more
    */
 
   //                    //
  //ANSWERS QUESTION (7)//
 //                    //
   public int AADelay() {
       int AADelayTally = 0;
       for(Flights22 flight : completedFlights) {
           if(flight.getCarrier().equals("AA")) {
               if(Integer.parseInt(flight.getDepDelay()) >= 60 || Integer.parseInt(flight.getArrDelay()) >= 60) {
                   AADelayTally++;{
               }
           }
       } 
   }
       return AADelayTally;  
   }
    /**
     * This method takes in the ArrayList of fulfilled flights, iterates through flights, finding 
     * biggest difference between depDelay and arrDelay. If it is the biggest so far, it stores the 
     * day of month, the tailNum, and the depDelay in 3 different variables. 
     * 
     * 
     * @return This method returns the day of month, the amount of time made up by the airplane, 
     * and the tailNum of the plane.
     */
   //                    //
  //ANSWERS QUESTION (8)//
 //                    //
   public String savesTheDelay() {
       String saveReturn = "";
       int maxSave = 0;
       for(Flights22 flight : completedFlights) {
          
           int save = Integer.parseInt(flight.getDepDelay());
           if(Integer.parseInt(flight.getArrDelay()) <= 0 && save > maxSave) {
               maxSave = save;
               int moDay = Integer.parseInt(flight.getDayOfMonth());
               int saveDelay = Integer.parseInt(flight.getDepDelay());
               String saveTail = flight.getTailNum();
               saveReturn = Integer.toString(moDay) + ", " + Integer.toString(saveDelay) + ", " + saveTail;
           }
       }
       return saveReturn;
   } 
   
   /**
    * Answers the question: "Which airport had the fewest total number of flights?"
    * This method finds the airport with the fewest total number of flights. It uses a HashMap 
    * made in minMover() to find the airport with the smallest number of arrivals and departures.
    * @return
    */ 
   
   //                    //
  //ANSWERS QUESTION (9)//
 //                    //
   public String fewestFlights() {
       HashMap<String, Integer> slowestAirport = minMover();
       String minNum = Collections.min(slowestAirport.entrySet(), Map.Entry.comparingByValue()).getKey();
       return minNum;
   }
   /**
    * This method takes HashMaps of airport codes and their corresponding numbers of arrivals and departures, and adds them together.
    * It then maps this sums to the respective airport code in the HashMap minMoverAirports.
    * @return Returns the minMoverAirports HashMap for use by the fewestFlights() method.
    */
   public HashMap<String, Integer> minMover() {
       ArrayList<String> airportList = airportArray(completedFlights);
       HashMap<String, Integer> airportArrs = makeAirportArrivalHash(airportList, completedFlights);
       HashMap<String, Integer> airportDeps = makeAirportDepartureHash(airportList, completedFlights);
       HashMap<String, Integer> minMoverAirports = new HashMap<>(airportArrs);
       
       airportDeps.forEach((key, value) -> minMoverAirports.merge(key, value, (v1, v2) -> (v1 + v2)));
      
       return minMoverAirports;
   } 
}
