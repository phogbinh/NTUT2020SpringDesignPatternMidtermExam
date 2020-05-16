package org.ntutssl.dp.midterm;

import java.util.ArrayList;
import java.util.Iterator;

public class Title extends DPFormat {
    private ArrayList< DPFormat > _sections;
    private String _description;
    private int _level;
    
    public Title(String description, int level) {
        _sections = new ArrayList<>();
        _description = description;
        _level = level;
    }

    @Override
    public void add( DPFormat section ) {
        if ( section instanceof Title && section.getLevel() <= _level )
        {
            throw new RuntimeException( "Cannot add title/ subtitle of smaller or equal level (higher or equal priority) to the current subtitle/ title" );
        }
        _sections.add( section );
    }

    @Override
    public int getLevel() {
        return _level;
    }
    
    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public Iterator< DPFormat > iterator() {
        return _sections.iterator();
    }

    @Override
    public void accept( Visitor visitor ) {
        visitor.visitTitle( this );
    }
}