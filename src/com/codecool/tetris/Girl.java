package com.codecool.tetris;

import com.codecool.termlib.*;
import com.codecool.tetris.Shapes.Shape;
import java.util.HashMap;
import java.util.Map;


public class Girl {

    public static final Map<Integer, String> scoreCase1 = fillMap();
    private static Map<Integer, String> fillMap() {
        Map<Integer, String> aMap = new HashMap<>();
        aMap.put(6, "                .=.");
        aMap.put(7, "               //\"\\\\");
        aMap.put(8, "              (/6 6\\)");
        aMap.put(9, "              )\\ = /(");
        aMap.put(10, "             (_ ) ( _)");
        aMap.put(11, "             / `\\_/` \\");
        aMap.put(12, "            / (_ @ _) \\");
        aMap.put(13, "            \\ \\)___(/ /");
        aMap.put(14, "             \\/     \\/");
        aMap.put(15, "              |     |");
        aMap.put(16, "              |     |");
        aMap.put(17, "              |_____|");
        aMap.put(18, "                |||");
        aMap.put(19, "                |||");
        aMap.put(20, "               / Y \\");
        aMap.put(21, "               `\"`\"`");
        return aMap;
    }


    public static final Map<Integer, String> scoreCase2 = fillMap2();
    private static Map<Integer, String> fillMap2() {
        Map<Integer, String> aMap = new HashMap<>();
        aMap.put(6, "                .---.");
        aMap.put(7, "               (_,/\\ \\");
        aMap.put(8, "              (`a a(  )");
        aMap.put(9, "              ) \\=  ) (");
        aMap.put(10, "             (.--' '--.)");
        aMap.put(11, "             / (_)^(_) \\");
        aMap.put(12, "            | / \\   / \\ |");
        aMap.put(13, "             \\\\ / . \\ //");
        aMap.put(14, "              \\/\\___/\\/");
        aMap.put(15, "              |  \\_/  |");
        aMap.put(16, "               \\  /  /");
        aMap.put(17, "                \\/  /");
        aMap.put(18, "                 ( (");
        aMap.put(19, "                 |\\ \\");
        aMap.put(20, "                 | \\ \\");
        aMap.put(21, "                 /_Y/_Y");
        return aMap;
    }

    public static final Map<Integer, String> scoreCase3 = fillMap3();
    private static Map<Integer, String> fillMap3() {
        Map<Integer, String> aMap = new HashMap<>();
        aMap.put(6, "                    ___.-\"\"\"-.");
        aMap.put(7, "                   (  (___,/\\ \\");
        aMap.put(8, "                    \\(  |')' ) )");
        aMap.put(9, "                     \\)  \\=_/  (");
        aMap.put(10, "               ___   / _,'  \\   )");
        aMap.put(11, "             .'  \\|-(.(_|_   ; (");
        aMap.put(12, "            /   //.     (_\\, |  )");
        aMap.put(13, "  /`'---.._/   /.\\_ ____..'| |_/");
        aMap.put(14, " | /`'-._     /  |         '_|");
        aMap.put(15, "  `      `;-\"`;  |         /,'");
        aMap.put(16, "           `'.__/         ( \\");
        aMap.put(17, "                          '\\/");

        return aMap;
    }


    public static final Map<Integer, String> scoreCase4 = fillMap4();
    private static Map<Integer, String> fillMap4() {
        Map<Integer, String> aMap = new HashMap<>();
        aMap.put(6, "            ,S&S&S&s,");
        aMap.put(7, "            S&C ^^>S&");
        aMap.put(8, "           &S`\\_ =_)`S");
        aMap.put(9, "             .-)  (-.");
        aMap.put(10, "            / /\\  /\\ \\  |||||");
        aMap.put(11, "           / (_ \\/ _) \\/   @@");
        aMap.put(12, "   .-._    \\ \\)____(__|c   _\\");
        aMap.put(13, "    \\__)  //)|\\\\    (  \\ _( ");
        aMap.put(14, "   .-._\\ //   __\\____\\ \\/_ __");
        aMap.put(15, "    \\__)\\/   /) ) ) ) \\___(__\\");
        aMap.put(16, "     \\ \\/  /` / | / |");
        aMap.put(17, "      \\  /`   | | | |");
        aMap.put(18, "       `'     \\ | \\ |");
        aMap.put(19, "              (\\\\ (\\\\");
        aMap.put(20, "              Y\\_\\Y\\_\\");

        return aMap;
    }

}