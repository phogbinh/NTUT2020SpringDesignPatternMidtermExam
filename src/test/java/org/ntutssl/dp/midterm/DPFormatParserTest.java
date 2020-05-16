package org.ntutssl.dp.midterm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;

import org.junit.Test;

public class DPFormatParserTest {
    private final String TITLE_1_DESCRIPTION = "title 1 description";
    private final int TITLE_1_LEVEL = 1;
    private final String SUB_TITLE_1_DESCRIPTION = "sub title 1 description";
    private final int SUB_TITLE_1_LEVEL = 2;
    private final String SUB_TITLE_1_TEXT_DESCRIPTION = "sub title 1 text description";
    private final String SUB_TITLE_2_DESCRIPTION = "sub title 2 description";
    private final int SUB_TITLE_2_LEVEL = 2;
    private final String SUB_SUB_TITLE_DESCRIPTION = "sub sub title description";
    private final int SUB_SUB_TITLE_LEVEL = 3;
    private final String SUB_SUB_TITLE_TEXT_DESCRIPTION = "sub sub title text description";

    private final String SUB_SUB_TITLE_TEXT_DP_FORMAT_STRING = Definitions.TEXT_PRECEDING_SYMBOL + Definitions.SPACE + SUB_SUB_TITLE_TEXT_DESCRIPTION + Definitions.END_OF_LINE;
    private final String SUB_SUB_TITLE_DP_FORMAT_STRING = Definitions.TITLE_PRECEDING_SYMBOL + Definitions.TITLE_PRECEDING_SYMBOL + Definitions.TITLE_PRECEDING_SYMBOL + Definitions.SPACE + SUB_SUB_TITLE_DESCRIPTION + Definitions.END_OF_LINE
    + SUB_SUB_TITLE_TEXT_DP_FORMAT_STRING;
    private final String SUB_TITLE_2_DP_FORMAT_STRING = Definitions.TITLE_PRECEDING_SYMBOL + Definitions.TITLE_PRECEDING_SYMBOL + Definitions.SPACE + SUB_TITLE_2_DESCRIPTION + Definitions.END_OF_LINE
    + SUB_SUB_TITLE_DP_FORMAT_STRING;
    private final String SUB_TITLE_1_TEXT_DP_FORMAT_STRING = Definitions.TEXT_PRECEDING_SYMBOL + Definitions.SPACE + SUB_TITLE_1_TEXT_DESCRIPTION + Definitions.END_OF_LINE;
    private final String SUB_TITLE_1_DP_FORMAT_STRING = Definitions.TITLE_PRECEDING_SYMBOL + Definitions.TITLE_PRECEDING_SYMBOL + Definitions.SPACE + SUB_TITLE_1_DESCRIPTION + Definitions.END_OF_LINE
    + SUB_TITLE_1_TEXT_DP_FORMAT_STRING;
    private final String TITLE_1_DP_FORMAT_STRING = Definitions.TITLE_PRECEDING_SYMBOL + Definitions.SPACE + TITLE_1_DESCRIPTION + Definitions.END_OF_LINE
    + SUB_TITLE_1_DP_FORMAT_STRING
    + SUB_TITLE_2_DP_FORMAT_STRING;
    
    @Test
    public void test_parsing_subSubTitleText()
    {
        DPFormatParser parser = new DPFormatParser( SUB_SUB_TITLE_TEXT_DP_FORMAT_STRING );
        parser.parse();
        assertEquals( SUB_SUB_TITLE_TEXT_DESCRIPTION, parser.getResult().getDescription() );
    }

    @Test
    public void test_parsing_subSubTitle()
    {
        DPFormatParser parser = new DPFormatParser( SUB_SUB_TITLE_DP_FORMAT_STRING );
        parser.parse();
        DPFormat subSubTitle = parser.getResult();
        assertEquals( SUB_SUB_TITLE_DESCRIPTION, subSubTitle.getDescription() );
        assertEquals( SUB_SUB_TITLE_LEVEL, subSubTitle.getLevel() );
        Iterator< DPFormat > iterator = subSubTitle.iterator();
        assertEquals( SUB_SUB_TITLE_TEXT_DESCRIPTION, iterator.next().getDescription() );
        assertFalse( iterator.hasNext() );
    }

    @Test
    public void test_parsing_subTitle2()
    {
        DPFormatParser parser = new DPFormatParser( SUB_TITLE_2_DP_FORMAT_STRING );
        parser.parse();
        DPFormat subTitle2 = parser.getResult();
        assertEquals( SUB_TITLE_2_DESCRIPTION, subTitle2.getDescription() );
        assertEquals( SUB_TITLE_2_LEVEL, subTitle2.getLevel() );
        Iterator< DPFormat > subTitle2Iterator = subTitle2.iterator();
        DPFormat subSubTitle = subTitle2Iterator.next();
        assertEquals( SUB_SUB_TITLE_DESCRIPTION, subSubTitle.getDescription() );
        assertEquals( SUB_SUB_TITLE_LEVEL, subSubTitle.getLevel() );
        assertFalse( subTitle2Iterator.hasNext() );
        Iterator< DPFormat > subSubTitleIterator = subSubTitle.iterator();
        assertEquals( SUB_SUB_TITLE_TEXT_DESCRIPTION, subSubTitleIterator.next().getDescription() );
        assertFalse( subSubTitleIterator.hasNext() );
    }

    @Test
    public void test_parsing_title1()
    {
        DPFormatParser parser = new DPFormatParser( TITLE_1_DP_FORMAT_STRING );
        parser.parse();
        DPFormat title1 = parser.getResult();
        assertEquals( TITLE_1_DESCRIPTION, title1.getDescription() );
        assertEquals( TITLE_1_LEVEL, title1.getLevel() );
        Iterator< DPFormat > title1Iterator = title1.iterator();
        DPFormat subTitle1 = title1Iterator.next();
        assertEquals( SUB_TITLE_1_DESCRIPTION, subTitle1.getDescription() );
        assertEquals( SUB_TITLE_1_LEVEL, subTitle1.getLevel() );
        Iterator< DPFormat > subTitle1Iterator = subTitle1.iterator();
        assertEquals( SUB_TITLE_1_TEXT_DESCRIPTION, subTitle1Iterator.next().getDescription() );
        assertFalse( subTitle1Iterator.hasNext() );
        DPFormat subTitle2 = title1Iterator.next();
        assertEquals( SUB_TITLE_2_DESCRIPTION, subTitle2.getDescription() );
        assertEquals( SUB_TITLE_2_LEVEL, subTitle2.getLevel() );
        Iterator< DPFormat > subTitle2Iterator = subTitle2.iterator();
        DPFormat subSubTitle = subTitle2Iterator.next();
        assertEquals( SUB_SUB_TITLE_DESCRIPTION, subSubTitle.getDescription() );
        assertEquals( SUB_SUB_TITLE_LEVEL, subSubTitle.getLevel() );
        assertFalse( subTitle2Iterator.hasNext() );
        Iterator< DPFormat > subSubTitleIterator = subSubTitle.iterator();
        assertEquals( SUB_SUB_TITLE_TEXT_DESCRIPTION, subSubTitleIterator.next().getDescription() );
        assertFalse( subSubTitleIterator.hasNext() );
        assertFalse( title1Iterator.hasNext() );
    }

    @Test
    public void test_parsing_titleA1_containing_empty_subTitle1_and_subTitle2()
    {
        final String TITLE_A1_DP_FORMAT_STRING = Definitions.TITLE_PRECEDING_SYMBOL + Definitions.SPACE + TITLE_1_DESCRIPTION + Definitions.END_OF_LINE
        + Definitions.TITLE_PRECEDING_SYMBOL + Definitions.TITLE_PRECEDING_SYMBOL + Definitions.SPACE + SUB_TITLE_1_DESCRIPTION + Definitions.END_OF_LINE
        + Definitions.TITLE_PRECEDING_SYMBOL + Definitions.TITLE_PRECEDING_SYMBOL + Definitions.SPACE + SUB_TITLE_2_DESCRIPTION + Definitions.END_OF_LINE;
        DPFormatParser parser = new DPFormatParser( TITLE_A1_DP_FORMAT_STRING );
        parser.parse();
        DPFormat title1 = parser.getResult();
        assertEquals( TITLE_1_DESCRIPTION, title1.getDescription() );
        assertEquals( TITLE_1_LEVEL, title1.getLevel() );
        Iterator< DPFormat > title1Iterator = title1.iterator();
        DPFormat subTitle1 = title1Iterator.next();
        assertEquals( SUB_TITLE_1_DESCRIPTION, subTitle1.getDescription() );
        assertEquals( SUB_TITLE_1_LEVEL, subTitle1.getLevel() );
        DPFormat subTitle2 = title1Iterator.next();
        assertEquals( SUB_TITLE_2_DESCRIPTION, subTitle2.getDescription() );
        assertEquals( SUB_TITLE_2_LEVEL, subTitle2.getLevel() );
        assertFalse( title1Iterator.hasNext() );
    }
}