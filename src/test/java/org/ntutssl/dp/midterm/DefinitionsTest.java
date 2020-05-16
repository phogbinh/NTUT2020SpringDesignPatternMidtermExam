package org.ntutssl.dp.midterm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DefinitionsTest {
    @Test
    public void test_getting_0_title_indents()
    {
        assertEquals( Definitions.EMPTY_STRING, Definitions.getTitleIndents( 0 ) );
    }

    @Test
    public void test_getting_2_title_indents()
    {
        assertEquals( Definitions.TITLE_INDENT + Definitions.TITLE_INDENT, Definitions.getTitleIndents( 2 ) );
    }
}