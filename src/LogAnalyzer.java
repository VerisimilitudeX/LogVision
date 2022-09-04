import edu.duke.FileResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
        String ip = le.getIPAddress();
        if (!uniqueIPs.contains(ip)) {
          uniqueIPs.add(ip);
        }
      }
    }
    System.out.println("\n" + uniqueIPs.size() + " Unique IPs on " + someday + ": \n" + uniqueIPs);
  }

  public void countUniqueIPsInRange(int low, int high) {
    ArrayList<String> uniqueIPs = new ArrayList<>();
    for (LogEntry le : records) {
      String ip = le.getIPAddress();
      if (!uniqueIPs.contains(ip) && le.getStatusCode() >= low && le.getStatusCode() <= high) {
        uniqueIPs.add(ip);
      }
    }
    System.out.println(
        "\n" + uniqueIPs.size() + " Unique IPs in Range " + low + "-" + high + ": \n" + uniqueIPs);
  }

  public void printAll() {
    System.out.println("All Log Entries:");
    System.out.println("------------------------------");
    for (LogEntry le : records) {
      System.out.println(le);
    }
  }

  public HashMap<String, Integer> countVisitsPerIP() {
    HashMap<String, Integer> counts = new HashMap<String, Integer>();
    for (LogEntry le : records) {
      String ip = le.getIPAddress();
      if (counts.containsKey(ip)) {
        counts.put(ip, counts.get(ip) + 1);
      } else {
        counts.put(ip, 1);
      }
    }
    return counts;
  }

  public int uniqueIPs() {
    return countVisitsPerIP().size();
  }

  public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
    int max = 0;
    for (String ip : counts.keySet()) {
      if (counts.get(ip) > max) {
        max = counts.get(ip);
      }
    }
    return max;
  }

  public ArrayList<String> ipMostVisits(HashMap<String, Integer> counts) {
    int max = mostNumberVisitsByIP(counts);
    ArrayList<String> ips = new ArrayList<>();
    for (String ip : counts.keySet()) {
      if (counts.get(ip) == max) {
        ips.add(ip);
      }
    }
    return ips;
  }

  public HashMap<String, ArrayList<String>> ipsForDays() {
    HashMap<String, ArrayList<String>> days = new HashMap<>();
    for (LogEntry le : records) {
      String date = le.getAccessTime().toString().substring(4, 10);
      if (days.containsKey(date)) {
        days.get(date).add(le.getIPAddress());
      } else {
        ArrayList<String> ips = new ArrayList<>();
        ips.add(le.getIPAddress());
        days.put(date, ips);
      }
    }
    return days;
  }

  public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> days) {
    int max = 0;
    String day = "";
    for (String d : days.keySet()) {
      if (days.get(d).size() > max) {
        max = days.get(d).size();
        day = d;
      }
    }
    return day;
  }

  public Set<String> ipsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> days, String day) {
    Set<String> IPs = new HashSet<String>();
    int max = 0;
    for (String ip : days.get(day)) {
      if (days.get(day).size() > max) {
        max = days.get(day).size();
        IPs.add(ip);
      }
    }
    return IPs;
  }
}
