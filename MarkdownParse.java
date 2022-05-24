//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int exclamation = markdown.indexOf("!", closeBracket);
            int openParen = markdown.indexOf("(", currentIndex);
            int closeParen = markdown.indexOf(")", openParen);
            int backtick1 = markdown.indexOf("`", currentIndex);
            int backtick2 = markdown.indexOf("`", backtick1 + 1);
            if(openBracket > backtick1 && openBracket < backtick2){
                openBracket = -1;
            }
            if(closeBracket > backtick1 && closeBracket < backtick2){
                openBracket = -1;
            }
            if(closeBracket == -1){
                currentIndex = closeParen + 1;
                continue;
            }
            if(openBracket == -1){
                currentIndex = closeParen + 1;
                continue;
            }

            /*
            //check to see if every open bracket has a close bracket
            String betweenBracket = markdown.substring(openBracket, closeBracket);
            int newOpenBracket = betweenBracket.indexOf("(");
            if(newOpenBracket != -1){
                openBracket = openBracket + newOpenBracket + 1;
            }

            //check to see if every open paren has a close paren
            String betweenParen = markdown.substring(openParen, closeParen);
            int newOpenParen = betweenParen.indexOf("(");
            System.out.println(openParen + " " + closeParen + " " + newOpenParen);
            if(newOpenParen != -1){
                openParen = openParen + newOpenParen;
            }
            */
            
            if(openParen != -1 && closeParen != -1){
                toReturn.add(markdown.substring(openParen + 1, closeParen).replaceAll(
                    System.getProperty("line.separator"), "").replaceAll(" ", ""));
            } else if(exclamation != -1){
                toReturn.add(markdown.substring(closeBracket + 1, exclamation).replaceAll(
                    System.getProperty("line.separator"), "").replaceAll(" ", ""));
            }
            currentIndex = closeParen + 1;
            if(!markdown.substring(currentIndex, markdown.length()).contains(")")){
                break;
            }
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
