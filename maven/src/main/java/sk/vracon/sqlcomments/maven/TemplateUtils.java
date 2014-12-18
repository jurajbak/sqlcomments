package sk.vracon.sqlcomments.maven;

public class TemplateUtils {

    public String getSimpleClassName(String className) {
        if(className == null) {
            return null;
        }
        
        int lastDot = className.lastIndexOf('.');

        if (lastDot < 0) {
            return className;
        } else {
            return className.substring(lastDot + 1);
        }
    }
}
