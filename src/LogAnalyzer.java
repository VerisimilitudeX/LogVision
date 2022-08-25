import java.util.ArrayList;

import javax.print.attribute.Size2DSyntax;

import edu.duke.FileResource;

public class LogAnalyzer {
    private final ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource("assets/" + filename);
        for (String line : fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!uniqueIPs.contains(ip)) {
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num) {
        System.out.println("\nAll higher than " + num + ":");
        System.out.println("-------------------------------");
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }

    public void uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<>();
        String month = someday.substring(0, 3);
        String day = someday.substring(4, 6);

        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            String month2 = date.substring(4, 7);
            String day2 = date.substring(8, 10);
            if (month.equals(month2) && day.equals(day2)) {
                String ip = le.getIpAddress();
                if (!uniqueIPs.contains(ip)) {
                    uniqueIPs.add(ip);
                }
            }
        }
        System.out.println("\n" + uniqueIPs.size() + " Unique IPs on " + someday + ": " + uniqueIPs);
    }

    public void countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!uniqueIPs.contains(ip) && le.getStatusCode() >= low && le.getStatusCode() <= high) {
                uniqueIPs.add(ip);
            }
        }
        System.out.println("\n" + uniqueIPs.size() + " Unique IPs in Range " + low + "-" + high + ": " + uniqueIPs);
    }

    public void printAll() {
        System.out.println("All Log Entries:");
        System.out.println("------------------------------");
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

}
