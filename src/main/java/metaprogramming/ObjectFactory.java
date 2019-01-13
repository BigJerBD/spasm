package metaprogramming;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * Scavenged class from Flux to create Object from a classname string and a list of argument
 */
public class ObjectFactory {


    public static Object getObject(String classname, Object[] args)
            throws IllegalAccessException, InstantiationException,InvocationTargetException, ClassNotFoundException {

        Class cls = Class.forName(classname);
        Object obj;

        if (args.length != 0) {
            List<Object> constructorArgs = Arrays.asList(args);
            Constructor con = findCompatibleConstructor(constructorArgs, cls);
            if (con != null) {
                obj = con.newInstance(args);
            }
            else {
                String msg = String.format(
                        "Couldn't find a suitable constructor for class '%s' with arguments '%s'.",
                        cls.getName(),
                        constructorArgs);
                throw new IllegalArgumentException(msg);
            }
        }
        else {
            obj = cls.newInstance();
        }
        return obj;
    }


    private static Constructor findCompatibleConstructor(List<Object> args, Class target) {
        Constructor retval = null;
        Constructor[] cons = target.getDeclaredConstructors();

        for (Constructor con : cons) {
            Class[] paramClasses = con.getParameterTypes();
            if (paramClasses.length == args.size()) {
                boolean invokable = canInvokeWithArgs(args, con.getParameterTypes());
                if (invokable) {
                    retval = con;
                }
            }
        }
        return retval;
    }


    private static boolean canInvokeWithArgs(List<Object> args, Class[] parameterTypes) {
        if (parameterTypes.length != args.size()) {
            return false;
        }

        for (int i = 0; i < args.size(); i++) {
            Object obj = args.get(i);
            if (obj == null) {
                throw new IllegalArgumentException("argument shouldn't be null - index: " + i);
            }
            Class paramType = parameterTypes[i];
            Class objectType = obj.getClass();

            if (paramType != objectType){
                return false;
            }
        }
        return true;
    }

}
