package org.krmdemo.techlabs.interview.fiserve;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a coding-puzzle during technical interview
 * with <a href="https://www.fiserv.com/en.html">Fiserve</a> at <b>18 Dec 2024</b>.
 */
public class MyClass {

    final static Pattern ipAddrPattern = Pattern.compile("[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+");

    /**
     * JVM entry-point
     * <hr/>
     * Tha task is to extract and output IP-addresses from each line of <code>sampleText</code>
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        String[] sampleTexts = {
            "The server at 192.168.1.1 is currently down for maintenance.",
            "Let's meet for lunch tomorrow at noon.",
            "Contact the backup database at 10.0.0.254 for further information.",
            "Did you finish the report for the meeting?",
            "The router's gateway is set to 172.16.0.1 by default.",
            "I need some help with the presentation slides.",
            "Please configure the printer to use the IP address 192.0.2.45.",
            "What time is the event starting this evening?",
            "The old DNS server used to be at 203.0.113.17.",
            "We need to schedule the next team check-in.",
            "The client requested that their IP be whitelisted: 8.8.8.8.",
            "I'll forward you the email from the client later.",
            "Check if 198.51.100.34 is still reachable from the network.",
            "The weather forecast predicts rain tomorrow afternoon.",
            "A rogue IP address, 192.88.99.0, was detected in the logs.",
            "The new update includes several bug fixes and improvements.",
            "Try pinging 209.85.231.104 to see if the connection is stable.",
            "Please review the quarterly budget report.",
            "The subnet mask for this configuration is 255.255.255.0.",
            "Can you share the latest draft of the marketing proposal?"
        };

        System.out.println("sampleTexts.length = " + sampleTexts.length);
        int count = 0;
        for (String line : sampleTexts) {
            Matcher m = ipAddrPattern.matcher(line);
            // m.results() // <-- this approach is more advanced and flexible
            if (m.find()) {
                count++;
                System.out.printf("%2d) IP = %s;%n", count, m.group());
            }
        }
        System.out.println("count = " + count);
    }
}
