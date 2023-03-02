/**
 * 
 * @author Robby Ballard
 * 
 * This method constructs the Flight objects. I 
 * have commented out the data not relevant to this 
 * particular assignment, but have not deleted 
 * it in case it is necessary at a later date.
 * 
 * The class is named Flights22 because this is a product of the third approach I took to the assignment,
 * the first two being Flights and Flights11 (there was another in there but it would have been an embarrassment to 
 * the other failed attempts had I included it. 
 *
 */
public class Flights22 {
    private String dayOfMonth; //The day of the month that the flight took place
    //private String dayOfWeek; //The day of the week that the flight took place
    //private String flightDate; //The calendar date that the flight took place
    private String carrier; //The airline code, as two letters
    private String tailNum; //The tail number of the aircraft that flew the specified route.
    private String originCode; //A numeric code representing the airport from which the flight departed
    //private String origin3L; //A 3 letter code representing the airport from which the flight departed
    //private String originState; //The state from which the flight departed
    private String destCode;//A numeric code representing the airport at which the flight landed
    //private String dest3L; //A 3 letter code representing the airport at which the flight landed
    //private String destState; //The state wherein the destination airport is located
    private String depTime; //The time at which the flight departed its gate (HHMM)
    private String depDelay;//The amount of time the flight's departure was delayed (MM) - if the flight left early, + if the flight was delayed
    //private String wheelsOff; //The time at which the flight actually left the runway, not necessarily the gate (HHMM)
    //private String wheelsOn; //The time at which the flight actually touched down, not necessarily arrived at the gate (HHMM)
    private String arrTime; //The time at which the flight arrived at its destination gate (HHMM)
    private String arrDelay; //The amount of time the flight's arrival was delayed (MM) - if the flight arrived early, + if the flight was delayed
    private String cancel; //A binary code representing whether or not the flight was cancelled (0 = no; 1 = yes)
    private String cancelCode; //A letter (A, B, C, D) representing the reason for which the flight was cancelled, if it was cancelled.
    private String diverted;//An indicator of whether the flight twas diverted from its original flight path.
    private String airTime;//The amount of time the airplane spent en route to its destination (HHMM)       
    private String distance;//The total distance the airplane flew (miles)
    
    public Flights22(String dayOfMonth, String carrier, 
            String tailNum, String originCode, String destCode,
              String depTime, String depDelay ,
            String arrTime, String arrDelay, String cancel, String cancelCode, String diverted, String airTime, 
            String distance) {
        
        this.dayOfMonth = dayOfMonth;
        //this.dayOfWeek = dayOfWeek;
        //this.flightDate = flightDate;
        this.carrier = carrier;
        this.tailNum = tailNum;
        this.originCode = originCode;
        //this.origin3L = origin3L;
        //this.originState = originState;
        this.destCode= destCode;
        //this.dest3L = dest3L;
        //this.destState = destState;
        this.depTime =depTime;
        this.depDelay = depDelay;
        //this.wheelsOff = wheelsOff;
        //this.wheelsOn = wheelsOn;
        this.arrTime = arrTime;
        this.arrDelay = arrDelay;
        this.cancel = cancel;
        this.cancelCode = cancelCode;
        this.diverted = diverted;
        this.airTime = airTime;
        this.distance = distance;
        
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }
/*
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getFlightDate() {
        return flightDate;
    }*/

    public String getCarrier() {
        return carrier;
    }

    public String getTailNum() {
        return tailNum;
    }

    public String getOriginCode() {
        return originCode;
    }

    //public String getOrigin3L() {
      //  return origin3L;
    //}

    //public String getOriginState() {
      //  return originState;
    //}

    public String getDestCode() {
        return destCode;
    }
    
    //public String getDest3L() {
      //  return dest3L;
    //}

    //public String getDestState() {
      //  return destState;
    //}

    public String getDepTime() {
        return depTime;
    }

    public String getDepDelay() {
        return depDelay;
    }

    //public String getWheelsOff() {
      //  return wheelsOff;
    //}

    //public String getWheelsOn() {
      //  return wheelsOn;
    //}

    public String getArrTime() {
        return arrTime;
    }

    public String getArrDelay() {
        return arrDelay;
    }

    public String getCancel() {
        return cancel;
    }

    public String getCancelCode() {
        return cancelCode;
    }

    public String getDiverted() {
        return diverted;
    }

    public String getAirTime() {
        return airTime;
    }

    public String getDistance() {
        return distance;
    }
    
    
    
}


