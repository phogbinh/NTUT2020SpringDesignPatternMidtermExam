package org.ntutssl.dp.midterm;

import java.util.ArrayList;

public class Document {
    private ArrayList< DPFormat > _objects;

    public Document() {
        _objects = new ArrayList<>();
    }

    public void parse( String source ){
        DPFormatParser parser = new DPFormatParser( source );
        parser.parse();
        _objects.add( parser.getResult() );
    }

    public String transfer() {
        WriteVisitor visitor = new WriteVisitor();
        for ( DPFormat object : _objects )
        {
            object.accept( visitor );
        }
        return visitor.getResult();
    }
}