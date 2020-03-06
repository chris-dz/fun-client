import java.util.Scanner;

public class FunCli {
    private static HttpRequest request = null;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(Usage.getUsageInstructions());
            return;
        }

        if (args[0].equals("-listEntries")) {
            listEntries();
        } else if (args[0].equals("-addEntry")) {
            addEntry();
        } else {
            System.out.println(Usage.getUsageInstructions());
            return;
        }
    }

    private static void listEntries() {
        if (request == null) {
            request = new HttpRequest();
        }
        System.out.println(request.getRequestDataAsString("https://fun-kd.azurewebsites.net/api/write"));
    }

    private static void addEntry() {
        if (request == null) {
            request = new HttpRequest();
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Type a new entry:");
        String entry = "";
        String line = null;
        while ((line = scan.nextLine()) != null) {
            if (line.equals(Usage.END_OF_LINE_STRING)) {
                break;
            }
            entry += (entry.isEmpty() ? "" : System.lineSeparator()) + line;
        }
        scan.close();

        System.out.println("Sending your entry, please wait...");

        if (entry.isEmpty()) {
            listEntries();
        } else {
            System.out.println(request.send("https://fun-kd.azurewebsites.net/api/write", entry));
        }
    }
}