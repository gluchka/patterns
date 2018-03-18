package utils.decorator.elements.base;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

import java.util.List;

@Log4j
public class MyWebElement implements WebElement, WrapsElement, Locatable {

    private WebElement wrappedElement;

    public MyWebElement(WebElement wrappedElement) {
        this.wrappedElement = wrappedElement;
    }

    public void click() {
//        log.info("Click on element");
        wrappedElement.click();
    }

    public void submit() {
//        log.info("Submit element");
        wrappedElement.submit();
    }

    public void sendKeys(CharSequence... charSequences) {
//        log.info("Send keys: " + Arrays.asList(charSequences).toString());
        wrappedElement.sendKeys(charSequences);
    }

    public void clear() {

    }

    public String getTagName() {
        return wrappedElement.getTagName();
    }

    public String getAttribute(String s) {
        return null;
    }

    public boolean isSelected() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }

    public String getText() {
        return null;
    }

    public List<WebElement> findElements(By by) {
        return null;
    }

    public WebElement findElement(By by) {
        return null;
    }

    public boolean isDisplayed() {
        return false;
    }

    public Point getLocation() {
        return null;
    }

    public Dimension getSize() {
        return null;
    }

    public Rectangle getRect() {
        return null;
    }

    public String getCssValue(String s) {
        return null;
    }

    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }

    public WebElement getWrappedElement() {
        return null;
    }

    @Override
    public Coordinates getCoordinates() {
        return null;
    }
}
