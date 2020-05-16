package org.ntutssl.dp.midterm;

import static org.junit.Assert.assertFalse;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class NullIteratorTest {
    private NullIterator _iterator;

    @Before
    public void setUp()
    {
        _iterator = new NullIterator();
    }

    @Test
    public void test_has_next_of_null_iterator_being_false()
    {
        assertFalse( _iterator.hasNext() );
    }

    @Test( expected = NoSuchElementException.class )
    public void test_next_being_called_on_null_iterator_throwing_exception()
    {
        _iterator.next();
    }
}