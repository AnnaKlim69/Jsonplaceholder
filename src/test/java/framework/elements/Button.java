package framework.elements;

import org.openqa.selenium.By;

public class Button extends BaseElement {
    public Button(By by) {//кнопка
        super(by);
    }
@Override
    protected String getElementType() {
        return getLoc("log.button");
    }
}