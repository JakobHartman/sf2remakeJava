package util;

import org.newdawn.slick.Font;
import java.util.ArrayList;

public class DialogUtil {


    public static ArrayList<String> wrap(String text, Font font, int width) {

        ArrayList<String> list = new ArrayList<>();
        String str = text;
        String line = "";

        int i = 0;
        int lastSpace = -1;
        while (i < str.length()) {
            char c = str.charAt(i);
            if (Character.isWhitespace(c))
                lastSpace = i;

            //time to wrap
            if (c == '\n' || font.getWidth(line + c) > width) {
                //if we've hit a space recently, use that
                int split = lastSpace != -1 ? lastSpace : i;
                int splitTrimmed = split;

                if (lastSpace != -1 && split < str.length() - 1) {
                    splitTrimmed++;
                }

                line = str.substring(0, split);
                str = str.substring(splitTrimmed);

                list.add(line);
                line = "";
                i = 0;
                lastSpace = -1;
            }
            else {
                line += c;
                i++;
            }
        }
        if (str.length() != 0)
            list.add(str);
        return list;
    }


}
