package org.ntutssl.dp.midterm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class TitleTest {
    private String MEMBER_VARIABLE_NAME_SECTIONS = "_sections";
    
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

    private DPFormat _title1;
    private DPFormat _subTitle1;
    private DPFormat _subTitle1Text;
    private DPFormat _subTitle2;
    private DPFormat _subSubTitle;
    private DPFormat _subSubTitleText;

    @Before
    public void setUp()
    {
        _title1 = new Title( TITLE_1_DESCRIPTION, TITLE_1_LEVEL );
        _subTitle1 = new Title( SUB_TITLE_1_DESCRIPTION, SUB_TITLE_1_LEVEL );
        _subTitle1Text = new Text( SUB_TITLE_1_TEXT_DESCRIPTION );
        _subTitle2 = new Title( SUB_TITLE_2_DESCRIPTION, SUB_TITLE_2_LEVEL );
        _subSubTitle = new Title( SUB_SUB_TITLE_DESCRIPTION, SUB_SUB_TITLE_LEVEL );
        _subSubTitleText = new Text( SUB_SUB_TITLE_TEXT_DESCRIPTION );
        _title1.add( _subTitle1 );
        _title1.add( _subTitle2 );
        _subTitle1.add( _subTitle1Text );
        _subTitle2.add( _subSubTitle );
        _subSubTitle.add( _subSubTitleText );
    }

    @Test( expected = RuntimeException.class )
    public void test_adding_subtitle2_to_subtitle_1_throwing_exception()
    {
        _subTitle1.add( _subTitle2 );
    }

    @Test
    public void test_adding_sutitle_to_title()
    {
        DPFormat title = new Title( "title description", 1 );
        DPFormat subTitle = new Title( "subtitle description", 2 );
        title.add( subTitle );
        Iterator< DPFormat > iterator = title.iterator();
        assertSame( subTitle, iterator.next() );
        assertFalse( iterator.hasNext() );
    }

    @Test
    public void test_adding_text_to_title()
    {
        DPFormat title = new Title( "title description", 1 );
        DPFormat text = new Text( "text description" );
        title.add( text );
        Iterator< DPFormat > iterator = title.iterator();
        assertSame( text, iterator.next() );
        assertFalse( iterator.hasNext() );
    }

    @Test
    public void test_get_level()
    {
        assertEquals( TITLE_1_LEVEL, _title1.getLevel() );
    }

    @Test
    public void test_get_description()
    {
        assertEquals( TITLE_1_DESCRIPTION, _title1.getDescription() );
    }

    @Test
    public void test_iterator_of_title1_being_that_of_its_sections()
    {
        try
        {
            Field sectionsField = Title.class.getDeclaredField( MEMBER_VARIABLE_NAME_SECTIONS );
            sectionsField.setAccessible( true );
            try
            {
                ArrayList< DPFormat > sections = ( ArrayList< DPFormat > )sectionsField.get( _title1 );
                Iterator< DPFormat > title1Iterator = _title1.iterator();
                Iterator< DPFormat > sectionsIterator = sections.iterator();
                assertSame( title1Iterator.next(), sectionsIterator.next() );
                assertSame( title1Iterator.next(), sectionsIterator.next() );
                assertFalse( title1Iterator.hasNext() );
                assertFalse( sectionsIterator.hasNext() );
            }
            catch ( IllegalAccessException exception )
            {
                assertTrue( false );
            }
        }
        catch ( NoSuchFieldException exception )
        {
            assertTrue( false );
        }
    }

    @Test
    public void test_accept_calling_visitor_visit_title()
    {
        VisitorMock visitorMock = new VisitorMock();
        _title1.accept( visitorMock );
        assertTrue( visitorMock.IsCalledVisitTitle );
    }
}