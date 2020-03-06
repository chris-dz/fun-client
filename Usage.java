public class Usage {
    public static final String END_OF_LINE_STRING = "<_EOF_>";
    static String getUsageInstructions() {
        return "\n\nUsage: java FunCli arg\n\n" +
            "The \"arg\" above refers to arguments the user needs to specify.\n\n" +
            "Supported arguments:\n\n" +
            "\t-listEntries \t- lists all entries stored in the Fun app\n\n" +
            "\tExample:\n\n" +
            "\tjava FunCli -listEntries\n\n" +
            "\t-addEntry \t- adds an entry into the Fun app, then lists all entries.\n" +
            "\t\tIf this option is chosen, the user is prompted for the content of the entry.\n" +
            "\t\tMultiple lines of text are permitted and there are generally no limitations\n" +
            "\t\tas to the contents of the text. To finish typing an entry, type " + END_OF_LINE_STRING + "\n" +
            "\t\ton a line of its own and press Enter.\n\n" +
            "\tExample:\n\n" +
            "\tjava FunCli -addEntry\n\n";
    }
}