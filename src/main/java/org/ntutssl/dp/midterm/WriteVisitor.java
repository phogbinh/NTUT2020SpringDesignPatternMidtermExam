package org.ntutssl.dp.midterm;

import java.util.Iterator;

public class WriteVisitor implements Visitor {
    private final int INITIAL_LEVEL = 0;

    private String _result;
    private int _level;

    public WriteVisitor()
    {
        _result = Definitions.EMPTY_STRING;
        _level = INITIAL_LEVEL;
    }

    @Override
    public void visitText( Text text ) {
        if ( _level == INITIAL_LEVEL && !_result.equals( Definitions.EMPTY_STRING ) )
        {
            _result += Definitions.END_OF_LINE;
        }
        if ( _level > INITIAL_LEVEL )
        {
            _result += Definitions.END_OF_LINE + Definitions.getTitleIndents( _level - 1 ) + Definitions.TEXT_INDENT;
        }
        _result += text.getDescription();
    }
    
    @Override
    public void visitTitle( Title title ) {
        if ( _level == INITIAL_LEVEL && !_result.equals( Definitions.EMPTY_STRING ) )
        {
            _result += Definitions.END_OF_LINE;
        }
        if ( _level > INITIAL_LEVEL )
        {
            _result += Definitions.END_OF_LINE;
        }
        _result += Definitions.getTitleIndents( _level ) + title.getDescription();
        Iterator< DPFormat > iterator = title.iterator();
        _level++;
        while ( iterator.hasNext() )
        {
            iterator.next().accept( this );
        }
        _level--;
    }

    public String getResult() {
        return _result;
    }
}