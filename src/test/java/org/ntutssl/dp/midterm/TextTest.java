package org.ntutssl.dp.midterm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TextTest {
    private final String DESCRIPTION = "some description";
    private Text _text;

    @Before
    public void setUp()
    {
        _text = new Text( DESCRIPTION );
    }

    @Test
    public void test_get_description()
    {
        assertEquals( DESCRIPTION, _text.getDescription() );
    }

    @Test
    public void test_accept_calling_visitor_visit_text()
    {
        VisitorMock visitorMock = new VisitorMock();
        _text.accept( visitorMock );
        assertTrue( visitorMock.IsCalledVisitText );
    }
}