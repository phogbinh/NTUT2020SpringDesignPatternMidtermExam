package org.ntutssl.dp.midterm;

import java.util.Scanner;

public class DPFormatParser {
    private Scanner _scanner;
    private DPFormatBuilder _builder;
    
    public DPFormatParser( String source ) {
        _scanner = new Scanner( source );
        _builder = new DPFormatBuilder();
    }
    
    public void parse() {
        parseWithCondition( 0 );
    }

    private void parseWithCondition( int conditionalTitleLevel )
    {
        if ( !_scanner.hasNext() )
        {
            return;
        }
        if ( _scanner.hasNext( Definitions.SPECIAL_CHARACTER_PRECEDING_REGEX + Definitions.TEXT_PRECEDING_SYMBOL ) )
        {
            parseThenBuildText();
        }
        else
        {
            int titleLevel = getTitleLevel();
            if ( titleLevel <= conditionalTitleLevel )
            {
                return;
            }
            parseThenBuildTitle( titleLevel );
        }
        parseWithCondition( conditionalTitleLevel );
    }

    private int getTitleLevel()
    {
        int count = 1;
        while ( true )
        {
            String titlePrecedingSymbols = getTitlePrecedingSymbols( count );
            if ( _scanner.hasNext( titlePrecedingSymbols ) )
            {
                break;
            }
            count++;
        }
        return count;
    }

    private String getTitlePrecedingSymbols( int symbolsCount )
    {
        String titlePrecedingSymbols = Definitions.EMPTY_STRING;
        for ( int index = 0; index < symbolsCount; index++ )
        {
            titlePrecedingSymbols += Definitions.TITLE_PRECEDING_SYMBOL;
        }
        return titlePrecedingSymbols;
    }

    private void parseThenBuildText()
    {
        _scanner.next();
        _builder.buildText( _scanner.nextLine().substring( 1 ) );
    }

    private void parseThenBuildTitle( int titleLevel )
    {
        _scanner.next();
        _builder.beginBuildTitle( _scanner.nextLine().substring( 1 ), titleLevel );
        parseWithCondition( titleLevel );
        _builder.endBuildTitle();
    }

	public DPFormat getResult() {
        return _builder.getResult();
    }
}