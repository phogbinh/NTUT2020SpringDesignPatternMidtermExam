package org.ntutssl.dp.midterm;

public class VisitorMock implements Visitor
{
    public boolean IsCalledVisitText;
    public boolean IsCalledVisitTitle;

    public VisitorMock()
    {
        IsCalledVisitText = false;
        IsCalledVisitTitle = false;
    }

    @Override
    public void visitText(Text text) {
        IsCalledVisitText = true;
    }

    @Override
    public void visitTitle(Title title) {
        IsCalledVisitTitle = true;
    }
}