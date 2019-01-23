import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class CSVParserTest {
    @Test
    public void withThursdayOnTheUserEntryShouldReturnThursday() {
        CSVParser CSVParser = new CSVParser("Thursday 14h");
        Assert.assertEquals("Thursday", CSVParser.parseFromCSVToCheckIn().day);
    }

    @Test
    public void with14OnTheUserEntryShouldReturn14() {
        CSVParser CSVParser = new CSVParser("Thursday 14h");
        Assert.assertEquals(14, CSVParser.parseFromCSVToCheckIn().hour);
    }

    @Test
    public void withCheckinBefore21OnThursdayShouldReturnHot() {
        CSVParser CSVParser = new CSVParser("Thursday 14h");
        Assert.assertEquals(false, CSVParser.isCold());
    }

    @Test
    public void withCheckinAfter21OnThursdayShouldReturnCold() {
        CSVParser CSVParser = new CSVParser("Thursday 21h");
        Assert.assertEquals(true, CSVParser.isCold());
    }

    @Test
    public void withCheckinBefore21OnAnotherDayShouldReturnNothing() {
        CSVParser CSVParser = new CSVParser("Friday 14h");
        Assert.assertEquals(false, CSVParser.isCold());
    }

}
