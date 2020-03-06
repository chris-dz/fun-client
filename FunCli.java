// This is a terminal based client for my Fun project
// Copyright (C) 2020  Krzysztof Dziedzic

// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.

import java.util.Scanner;

/**
 * This is a terminal based client for my Fun project
 * 
 * @author Krzysztof Dziedzic
 */
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