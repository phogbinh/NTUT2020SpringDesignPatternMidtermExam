package org.ntutssl.dp.midterm;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class WriteVisitorTest {
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

    private Title _title1;
    private Title _subTitle1;
    private Text _subTitle1Text;
    private Title _subTitle2;
    private Title _subSubTitle;
    private Text _subSubTitleText;
    private WriteVisitor _visitor;

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
        _visitor = new WriteVisitor();
    }

    @Test
    public void test_visiting_subTitle1Text()
    {
        final String EXPECTED_RESULT = SUB_TITLE_1_TEXT_DESCRIPTION;
        _visitor.visitText( _subTitle1Text );
        assertEquals( EXPECTED_RESULT, _visitor.getResult() );
    }

    @Test
    public void test_visiting_empty_title()
    {
        final String EMPTY_TITLE_DESCRIPTION = "empty title description";
        Title emptyTitle = new Title( EMPTY_TITLE_DESCRIPTION, 1 );
        _visitor.visitTitle( emptyTitle );
        assertEquals( EMPTY_TITLE_DESCRIPTION, _visitor.getResult() );
    }

    @Test
    public void test_visiting_subTitle1()
    {
        final String EXPECTED_RESULT = SUB_TITLE_1_DESCRIPTION + Definitions.END_OF_LINE
        + Definitions.TEXT_INDENT + SUB_TITLE_1_TEXT_DESCRIPTION;
        _visitor.visitTitle( _subTitle1 );
        assertEquals( EXPECTED_RESULT, _visitor.getResult() );
    }

    @Test
    public void test_visiting_subTitle2()
    {
        final String EXPECTED_RESULT = SUB_TITLE_2_DESCRIPTION + Definitions.END_OF_LINE
        + Definitions.TITLE_INDENT + SUB_SUB_TITLE_DESCRIPTION + Definitions.END_OF_LINE
        + Definitions.TITLE_INDENT + Definitions.TEXT_INDENT + SUB_SUB_TITLE_TEXT_DESCRIPTION;
        _visitor.visitTitle( _subTitle2 );
        assertEquals( EXPECTED_RESULT, _visitor.getResult() );
    }

    @Test
    public void test_visiting_title1()
    {
        final String EXPECTED_RESULT = TITLE_1_DESCRIPTION + Definitions.END_OF_LINE
        + Definitions.TITLE_INDENT + SUB_TITLE_1_DESCRIPTION + Definitions.END_OF_LINE
        + Definitions.TITLE_INDENT + Definitions.TEXT_INDENT + SUB_TITLE_1_TEXT_DESCRIPTION + Definitions.END_OF_LINE
        + Definitions.TITLE_INDENT + SUB_TITLE_2_DESCRIPTION + Definitions.END_OF_LINE
        + Definitions.TITLE_INDENT + Definitions.TITLE_INDENT + SUB_SUB_TITLE_DESCRIPTION + Definitions.END_OF_LINE
        + Definitions.TITLE_INDENT + Definitions.TITLE_INDENT + Definitions.TEXT_INDENT + SUB_SUB_TITLE_TEXT_DESCRIPTION;
        _visitor.visitTitle( _title1 );
        assertEquals( EXPECTED_RESULT, _visitor.getResult() );
    }

    @Test
    public void test_visiting_subTitle1_then_subTitle2()
    {
        final String EXPECTED_RESULT = SUB_TITLE_1_DESCRIPTION + Definitions.END_OF_LINE
        + Definitions.TEXT_INDENT + SUB_TITLE_1_TEXT_DESCRIPTION + Definitions.END_OF_LINE
        + SUB_TITLE_2_DESCRIPTION + Definitions.END_OF_LINE
        + Definitions.TITLE_INDENT + SUB_SUB_TITLE_DESCRIPTION + Definitions.END_OF_LINE
        + Definitions.TITLE_INDENT + Definitions.TEXT_INDENT + SUB_SUB_TITLE_TEXT_DESCRIPTION;
        _visitor.visitTitle( _subTitle1 );
        _visitor.visitTitle( _subTitle2 );
        assertEquals( EXPECTED_RESULT, _visitor.getResult() );
    }

    @Test
    public void test_visiting_subTitle1Text_then_subSubTitle()
    {
        final String EXPECTED_RESULT = SUB_TITLE_1_TEXT_DESCRIPTION + Definitions.END_OF_LINE
        + SUB_SUB_TITLE_DESCRIPTION + Definitions.END_OF_LINE
        + Definitions.TEXT_INDENT + SUB_SUB_TITLE_TEXT_DESCRIPTION;
        _visitor.visitText( _subTitle1Text );
        _visitor.visitTitle( _subSubTitle );
        assertEquals( EXPECTED_RESULT, _visitor.getResult() );
    }

    @Test
    public void test_get_result()
    {
        assertEquals( Definitions.EMPTY_STRING, _visitor.getResult() );
    }
}