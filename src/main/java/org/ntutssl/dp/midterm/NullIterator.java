package org.ntutssl.dp.midterm;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NullIterator implements Iterator< DPFormat > {
    @Override
    public boolean hasNext(){
        return false;
    }

    @Override
    public DPFormat next(){
        throw new NoSuchElementException( "No such element" );
    }
}
