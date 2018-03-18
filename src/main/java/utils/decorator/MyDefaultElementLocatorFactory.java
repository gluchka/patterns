package utils.decorator;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;

public class MyDefaultElementLocatorFactory implements MyElementLocatorFactory {
    private final SearchContext searchContext;

    public MyDefaultElementLocatorFactory(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    public ElementLocator createLocator(Class var1) {
        return new AjaxElementLocator(this.searchContext, 100, null);
    }

    public ElementLocator createLocator(Field field) {
        return new DefaultElementLocator(this.searchContext, field);
    }
}
