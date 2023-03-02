import java.io.*;

public class FlightRunner22 {
    
    
    
    public static void main(String[] args) throws IOException {
        FormattedOutput FO = new FormattedOutput();
        DataAnalysis22 DA22 = new DataAnalysis22();
        FO.addAnswer(1, DA22.makeCancellationPercentage()); 
        FO.addAnswer(2, DA22.cancelCodeCount());
        FO.addAnswer(3, DA22.tailTally());
        FO.addAnswer(4, DA22.busiestAirport());
        FO.addAnswer(5, DA22.biggestSource());
        FO.addAnswer(6, DA22.biggestSink());
        FO.addAnswer(7, DA22.AADelay());
        FO.addAnswer(8, DA22.savesTheDelay());
        FO.addAnswer(9, DA22.fewestFlights());
        FO.writeAnswers();
        System.out.println("Data Analysis Complete: Finally figured out what the Hell I'm doing.");
        
        
        
          
}

    

}

