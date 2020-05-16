package org.ntutssl.dp.midterm;

import java.util.Iterator;

public abstract class DPFormat {
    public String getDescription() {
        throw new RuntimeException();
    }
    
    public void add(DPFormat dpFormat) {
        throw new RuntimeException();
    }

    public void accept(Visitor visitor) {
        throw new RuntimeException();
    }

    public int getLevel() {
        throw new RuntimeException();
    }

    public Iterator< DPFormat > iterator() {
        return new NullIterator();
    }
}