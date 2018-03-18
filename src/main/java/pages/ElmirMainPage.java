package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.decorator.MyDefaultElementLocatorFactory;
import utils.decorator.elements.factory.internal.MyElementDecorator;

public class ElmirMainPage {

    public ElmirMainPage(WebDriver driver) {
        PageFactory.initElements(new MyElementDecorator(new MyDefaultElementLocatorFactory(driver)), this);
    }

}
