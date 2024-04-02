package framework.elements;

import org.openqa.selenium.By;

public class TextBox extends BaseElement {
    public TextBox(By by) {
        super(by);
    }
@Override
    protected String getElementType() {
        return getLoc("log.text.box");
    }
}