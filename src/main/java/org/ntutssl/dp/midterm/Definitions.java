package org.ntutssl.dp.midterm;

public final class Definitions {
    public static String EMPTY_STRING = "";
    public static String END_OF_LINE = "\n";
    public static String SPACE = " ";
    public static String TITLE_INDENT = SPACE + SPACE + SPACE + SPACE;
    public static String TEXT_INDENT = SPACE + SPACE;
    public static char TITLE_PRECEDING_CHAR = '#';
    public static String TITLE_PRECEDING_SYMBOL = "#";
    public static String TEXT_PRECEDING_SYMBOL = "**";
    public static String SPECIAL_CHARACTER_PRECEDING_REGEX = "\\";

    private Definitions()
    {
        /* Body intentionally empty */
    }

    public static String getTitleIndents( int titleIndentsCount )
    {
        String titleIndents = EMPTY_STRING;
        for ( int index = 0; index < titleIndentsCount; index++ )
        {
            titleIndents += TITLE_INDENT;
        }
        return titleIndents;
    }
}