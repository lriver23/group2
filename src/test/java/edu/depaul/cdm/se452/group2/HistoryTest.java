package edu.depaul.cdm.se452.group2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
//import lombok.extern.log4j.Log4j2;

import edu.depaul.cdm.se452.group2.recommendations.History;

//@Log4j2
public class HistoryTest {
    @Test
    public void testLombokFunctions() {
        var history = new History();
        history.setSearchHistory("shampoo");
        history.setItem("Tea Tree Special Shampoo");
<<<<<<< HEAD
        //log.info("Product toString: " + history.toString());

        var expectedProductToString = "History(id=0, searchHistory=shampoo, item=Tea Tree Special Shampoo)";
        assertEquals(expectedProductToString, history.toString());
=======
        log.info("History toString: " + history.toString());

        var expectedHistoryToString = "History(id=0, search=shampoo, item=Tea Tree Special Shampoo)";
        assertEquals(expectedHistoryToString, history.toString());
>>>>>>> history-add-response
    }    
}