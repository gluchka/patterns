package utils.decorator.elements.base;

import org.openqa.selenium.WebElement;

public class MyButton extends MyWebElement {

    protected MyButton(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void click(String name) {
        System.out.println("Click on button from" + MyButton.class);
        super.click();
    }
}
