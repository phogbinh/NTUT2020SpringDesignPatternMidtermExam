package org.ntutssl.dp.midterm;

import java.util.Stack;

public class DPFormatBuilder {
    private DPFormat _result;
    private Stack< Title > _titlesStack;

    public DPFormatBuilder()
    {
        _titlesStack = new Stack<>();
    }

    public void buildText( String description ) {
        Text text = new Text( description );
        if ( _titlesStack.empty() )
        {
            _result = text;
        }
        else
        {
            _titlesStack.peek().add( text );
        }
	}
    
    public void beginBuildTitle( String description, int level ) {   
        _titlesStack.push( new Title( description, level ) );
	}

    public void endBuildTitle() {
        Title title = _titlesStack.pop();
        if ( _titlesStack.empty() )
        {
            _result = title;
        }
        else
        {
            _titlesStack.peek().add( title );
        }
	}
	
    public DPFormat getResult() {
        return _result;
    }
}