public class Main {
  public static void main(String[] args) {
    String fileToRead = "real_runA.txt";
    if (args.length > 0) {
      fileToRead = args[0];
    }
    Tester t = new Tester();
    System.out.println("Using file: " + fileToRead);
    t.testLogAnalyzer(fileToRead);
  }
}