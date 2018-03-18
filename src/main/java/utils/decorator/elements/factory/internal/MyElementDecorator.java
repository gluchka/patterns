package utils.decorator.elements.factory.internal;


import com.google.common.collect.Lists;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;
import utils.decorator.elements.base.MyWebElement;

import java.lang.reflect.*;
import java.util.List;

public class MyElementDecorator implements FieldDecorator {
    protected ElementLocatorFactory factory;

    public MyElementDecorator(ElementLocatorFactory factory) {
        this.factory = factory;
    }

    @Override
    public Object decorate(ClassLoader classLoader, Field field) {
        if (!MyWebElement.class.isAssignableFrom(field.getType()) && !this.isDecoratableList(field)) {
            return null;
        } else {
            ElementLocator locator = this.factory.createLocator(field);
            if (locator == null) {
                return null;
            } else if (MyWebElement.class.isAssignableFrom(field.getType())) {
                InvocationHandler handler = new LocatingElementHandler(locator);
//                InvocationHandler handler = new ElementHandler(MyWebElement.class, locator);
                return  (WebElement) Proxy.newProxyInstance(classLoader, new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);
            } else {
                if (List.class.isAssignableFrom(field.getType())) {
                    InvocationHandler handler = new LocatingElementListHandler(locator);
                    return Proxy.newProxyInstance(classLoader, new Class[]{List.class}, handler);
                } else {
                    return null;
                }
            }
        }
    }

    protected boolean isDecoratableList(Field field) {
        if (!List.class.isAssignableFrom(field.getType())) {
            return false;
        } else {
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return false;
            } else {
                Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
                if (!MyWebElement.class.equals(listType)) {
                    return false;
                } else {
                    return field.getAnnotation(FindBy.class) != null || field.getAnnotation(FindBys.class) != null || field.getAnnotation(FindAll.class) != null;
                }
            }
        }
    }

//    @Override
//    public Object decorate(ClassLoader classLoader, Field field) {
//        try {
//            if (MyWebElement.class.isAssignableFrom(field.getType())) {
//                return this.decorateTypifiedElement(classLoader, field);
//            }
//        } catch (ClassCastException e) {
//            return null;
//        }
//        return null;
//    }
//
//    protected MyWebElement decorateTypifiedElement(ClassLoader loader, Field field) {
//        WebElement elementToWrap = this.decorateWebElement(loader, field);
//        MyWebElement myWebElement = null;
//        try {
//            myWebElement = createTypifiedElement(field.getType(), elementToWrap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return myWebElement;
//    }
//
//    protected WebElement decorateWebElement(ClassLoader loader, Field field) {
//        ElementLocator locator = this.factory.createLocator(field);
//        InvocationHandler handler = new LocatingElementHandler(locator);
//        Class<?>[] interfaces = new Class[]{WebElement.class, WrapsElement.class, Locatable.class};
//        return (WebElement) Proxy.newProxyInstance(loader, interfaces, handler);
//    }
//
//
//    public static MyWebElement createTypifiedElement(Class elementClass, WebElement elementToWrap) throws Exception {
//        try {
//            return newInstance(elementClass, elementToWrap);
//        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException var4) {
//            throw new Exception(var4);
//        }
//    }
//
//    private static <T> T newInstance(Class clazz, Object... args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        if (clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers())) {
//            Class outerClass = clazz.getDeclaringClass();
//            Object outerObject = outerClass.newInstance();
//            return (T) ConstructorUtils.invokeConstructor(clazz, Lists.asList(outerObject, args).toArray());
//        } else {
//            return (T) ConstructorUtils.invokeConstructor(clazz, args);
//        }
//    }


//    public Object decorate(ClassLoader classLoader, Field field) {
//        if (!(WebElement.class.isAssignableFrom(field.getType()) || isDecoratableList(field))) {
//            return null;
//        }
//        ElementLocator locator = factory.createLocator(field);
//        if (locator == null) {
//            return null;
//        }
//        Class<?> fieldType = field.getType();
//        if (WebElement.class.equals(fieldType)) {
//            fieldType = MyWebElement.class;
//        }
//
//        if (WebElement.class.isAssignableFrom(fieldType)) {
//            return proxyForLocator(classLoader, fieldType, locator);
//        } else if (List.class.isAssignableFrom(fieldType)) {
//            Class<?> erasureClass = getErasureClass(field);
//            return proxyForListLocator(classLoader, erasureClass, locator);
//        } else {
//            return null;
//        }
//    }
//
//    protected <T> T proxyForLocator(ClassLoader loader, Class<T> interfaceType, ElementLocator locator) {
//        InvocationHandler handler = new ElementHandler(interfaceType, locator);
//        T proxy;
//        proxy = interfaceType.cast(Proxy.newProxyInstance(
//                loader, new Class[]{interfaceType, WebElement.class, WrapsElement.class, Locatable.class}, handler));
//        return proxy;
//    }
//
//    private Class getErasureClass(Field field) {
//        Type genericType = field.getGenericType();
//        if (!(genericType instanceof ParameterizedType)) {
//            return null;
//        }
//        return (Class) ((ParameterizedType) genericType).getActualTypeArguments()[0];
//    }
//
//    private boolean isDecoratableList(Field field) {
//        if (!List.class.isAssignableFrom(field.getType())) {
//            return false;
//        }
//        Class erasureClass = getErasureClass(field);
//        if (erasureClass == null || !WebElement.class.isAssignableFrom(erasureClass)) {
//            return false;
//        }
//        if (field.getAnnotation(FindBy.class) == null && field.getAnnotation(FindBys.class) == null) {
//            return false;
//        }
//        return true;
//    }
//
//    @SuppressWarnings("unchecked")
//    protected <T> List<T> proxyForListLocator(ClassLoader loader, Class<T> interfaceType, ElementLocator locator) {
//        InvocationHandler handler = new ElementListHandler(interfaceType, locator);
//
//        List<T> proxy;
//        proxy = (List<T>) Proxy.newProxyInstance(
//                loader, new Class[]{List.class}, handler);
//        return proxy;
//}
}
