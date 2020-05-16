package org.ntutssl.dp.midterm;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DPFormatTest {
    private DPFormat _section;
    private DPFormat _text;

    @Before
    public void setUp()
    {
        _section = new DPFormatMock();
        _text = new Text( "text description" );
    }

    @Test( expected = RuntimeException.class )
    public void test_getting_description_of_abstract_dpformat_throwing_exception()
    {
        _section.getDescription();
    }

    @Test( expected = RuntimeException.class )
    public void test_calling_add_on_abstract_dpformat_throwing_exception()
    {
        _section.add( new DPFormatMock() );
    }

    @Test( expected = RuntimeException.class )
    public void test_calling_accept_on_abstract_dpformat_throwing_exception()
    {
        _section.accept( new VisitorMock() );
    }

    @Test( expected = RuntimeException.class )
    public void test_getting_level_of_text_throwing_exception()
    {
        _text.getLevel();
    }

    @Test
    public void test_iterator_of_text_being_null_iterator()
    {
        assertTrue( _text.iterator() instanceof NullIterator );
    }
}