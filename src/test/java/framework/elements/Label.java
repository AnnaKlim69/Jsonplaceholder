package framework.elements;

import org.openqa.selenium.By;

public class Label extends BaseElement {

    public Label(By titleLocator) {
        super(titleLocator);
    }
@Override
    protected String getElementType() {
        return getLoc("log.label");
    }
}