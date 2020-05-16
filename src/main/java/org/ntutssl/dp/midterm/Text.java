package org.ntutssl.dp.midterm;

public class Text extends DPFormat {
    private String _description;

	public Text( String description ) {
        _description = description;
    }
    
    public String getDescription() {
        return _description;
    }

    public void accept( Visitor visitor ) {
        visitor.visitText( this );
    }
}