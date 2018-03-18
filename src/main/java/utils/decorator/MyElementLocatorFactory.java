package utils.decorator;

import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public interface MyElementLocatorFactory extends ElementLocatorFactory {
    ElementLocator createLocator(Class var1);
}
