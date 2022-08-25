import java.util.*;

public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAll();
        la.printAllHigherThanNum(400);
        la.uniqueIPVisitsOnDay("Sep 24");
        la.countUniqueIPsInRange(200,299);
        System.out.println("\nVisits per IP: \n" + la.countVisitsPerIP());
        System.out.println("\nUnique IPs: " + la.uniqueIPs());
        System.out.println("Highest traffic: " + la.ipMostVisits(la.countVisitsPerIP()).get(0) + " -> " + la.mostNumberVisitsByIP(la.countVisitsPerIP()));
        System.out.println("\nIPs for Days: \n" + la.ipsForDays());
        System.out.println("\nDay with most visits: " + la.dayWithMostIPVisits(la.ipsForDays()));
        System.out.println("\nIPs with most visits on day: " + la.ipsWithMostVisitsOnDay(la.ipsForDays(), "Sep 29"));
    }
}
