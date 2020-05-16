package org.ntutssl.dp.midterm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class DPFormatBuilderTest {
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

    private DPFormatBuilder _builder;

    @Before
    public void setUp()
    {
        _builder = new DPFormatBuilder();
    }

    @Test
    public void test_building_subSubTitleText()
    {
        _builder.buildText( SUB_SUB_TITLE_TEXT_DESCRIPTION );
        assertEquals( SUB_SUB_TITLE_TEXT_DESCRIPTION, _builder.getResult().getDescription() );
    }

    @Test
    public void test_building_subSubTitle()
    {
        _builder.beginBuildTitle( SUB_SUB_TITLE_DESCRIPTION, 1 );
        _builder.buildText( SUB_SUB_TITLE_TEXT_DESCRIPTION );
        _builder.endBuildTitle();
        DPFormat subSubTitle = _builder.getResult();
        assertEquals( SUB_SUB_TITLE_DESCRIPTION, subSubTitle.getDescription() );
        Iterator< DPFormat > iterator = subSubTitle.iterator();
        assertEquals( SUB_SUB_TITLE_TEXT_DESCRIPTION, iterator.next().getDescription() );
        assertFalse( iterator.hasNext() );
    }

    @Test
    public void test_building_title1()
    {
        _builder.beginBuildTitle( TITLE_1_DESCRIPTION, TITLE_1_LEVEL );
        _builder.beginBuildTitle( SUB_TITLE_1_DESCRIPTION, SUB_TITLE_1_LEVEL );
        _builder.buildText( SUB_TITLE_1_TEXT_DESCRIPTION );
        _builder.endBuildTitle();
        _builder.beginBuildTitle( SUB_TITLE_2_DESCRIPTION, SUB_TITLE_2_LEVEL );
        _builder.beginBuildTitle( SUB_SUB_TITLE_DESCRIPTION, SUB_SUB_TITLE_LEVEL );
        _builder.buildText( SUB_SUB_TITLE_TEXT_DESCRIPTION );
        _builder.endBuildTitle();
        _builder.endBuildTitle();
        _builder.endBuildTitle();
        DPFormat title1 = _builder.getResult();
        assertEquals( TITLE_1_DESCRIPTION, title1.getDescription() );
        Iterator< DPFormat > title1Iterator = title1.iterator();
        DPFormat subTitle1 = title1Iterator.next();
        assertEquals( SUB_TITLE_1_DESCRIPTION, subTitle1.getDescription() );
        DPFormat subTitle2 = title1Iterator.next();
        assertEquals( SUB_TITLE_2_DESCRIPTION, subTitle2.getDescription() );
        assertFalse( title1Iterator.hasNext() );
        Iterator< DPFormat > subTitle1Iterator = subTitle1.iterator();
        assertEquals( SUB_TITLE_1_TEXT_DESCRIPTION, subTitle1Iterator.next().getDescription() );
        assertFalse( subTitle1Iterator.hasNext() );
        Iterator< DPFormat > subTitle2Iterator = subTitle2.iterator();
        DPFormat subSubTitle = subTitle2Iterator.next();
        assertEquals( SUB_SUB_TITLE_DESCRIPTION, subSubTitle.getDescription() );
        assertFalse( subTitle2Iterator.hasNext() );
        Iterator< DPFormat > subSubTitleIterator = subSubTitle.iterator();
        assertEquals( SUB_SUB_TITLE_TEXT_DESCRIPTION, subSubTitleIterator.next().getDescription() );
        assertFalse( subSubTitleIterator.hasNext() );
    }

    @Test
    public void test_get_result()
    {
        assertNull( _builder.getResult() );
    }
}
