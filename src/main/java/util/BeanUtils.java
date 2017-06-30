package util;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyd on 2016/7/15.
 */
public class BeanUtils {

    public static Object getProperty(Object bean,String fieldName) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method=null;
        try {
            method = bean.getClass().getMethod(getGetMethodName(fieldName));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw e;
        }
        return method.invoke(bean,new Object[]{});

    }

    public static void setProperty(Object bean,String fieldName,Object fieldValue) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method=null;
        try {
            method=bean.getClass().getMethod(getSetMethodName(fieldName));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw e;
        }
        method.invoke(bean,new Object[]{fieldValue});
    }

    /**
     * 把map中的值赋值到bean中。
     * <li>方法1：通过map中的key寻找bean中方法并赋值。缺点：当map中key和bean中参数名称不同</li>
     * <li>方法2：通过bean中的所有参数名称去map中寻找值，然后在赋值。缺点：当bean中某个参数无set方法时</li>
     * <li>方法3：通过bean中的所有方法中以set开头的方法。缺点：bean需按照标准写set方法</li>
     * <li>综合：使用方法3更合适</li>
     * @param bean
     * @param valueMap
     * @return  赋值成功的个数
     */
    public static int covertMapToBean(Object bean,Map<String,Object> valueMap) throws InvocationTargetException, IllegalAccessException {
        Map<String,Method> methodMap=new HashMap<String,Method>();
        Method[] methods=bean.getClass().getMethods();
        //获取所有set方法
        for(Method m : methods){
            if(m.getName().startsWith("set") && m.getName().length()>3){
                methodMap.put(m.getName().substring(3).toLowerCase(),m);
            }
        }
        for(Map.Entry<String,Object> entry : valueMap.entrySet()){
          Method method=methodMap.get(entry.getKey().toLowerCase());
            if(method != null){
                Object value=entry.getValue();
                //TODO 判断转型BigDecimal等。暂时未写
                method.invoke(bean,value);
            }
        }

        return 0;
    }
    public static int covertMapToBeanWithoutNull(Object bean,Map<String,Object> valueMap) throws InvocationTargetException, IllegalAccessException {
        Map<String,Method> methodMap=new HashMap<String,Method>();
        Method[] methods=bean.getClass().getMethods();
        //获取所有set方法
        for(Method m : methods){
            if(m.getName().startsWith("set") && m.getName().length()>3){
                methodMap.put(m.getName().substring(3).toLowerCase(),m);
            }
        }
        for(Map.Entry<String,Object> entry : valueMap.entrySet()){
            Method method=methodMap.get(entry.getKey().toLowerCase());
            if(method != null){
                Object value=entry.getValue();
                //TODO 判断转型BigDecimal等。暂时未写
                if(value!=null){
                    method.invoke(bean,value);
                }
            }
        }

        return 0;
    }
    /**
     * 获得所有参数，包含父类的
     * @param bena
     * @return
     */
    public static List<Field> getAllField(Object bena){
        if(bena instanceof  Class){
            return getAllField(bena);
        }else{
            return getAllField(bena.getClass());
        }
    }
    /**
     * 获得所有参数，包含父类的
     * @param cls
     * @return
     */
    public static List<Field> getAllField(Class<?> cls){
        List<Field> fields=new ArrayList<Field>();
        for(Class clas=cls;!clas.equals(Object.class);clas=clas.getSuperclass()){
            Field[] fie=clas.getDeclaredFields();
            for(Field field : fie){
                fields.add(field);
            }
        }
        return fields;
    }
    /**
     * 获得所有参数的名字，
     * @param bena
     * @return
     */
    public static List<String>  getAllFieldName(Object bena){
        if(bena instanceof  Class){
            return getAllFieldName(bena);
        }else{
            return getAllFieldName(bena.getClass());
        }
    }
    /**
     * * 获得所有参数的名字，
     * @param cls
     * @return
     */
    public static List<String> getAllFieldName(Class<?> cls){
        List<String> fieldNames=new ArrayList<>();
        for(Class clas=cls;!clas.equals(Object.class);clas=clas.getSuperclass()){
            Field[] fie=clas.getDeclaredFields();
            for(Field field : fie){
               fieldNames.add(field.getName());
            }
        }
        return fieldNames;
    }

    /**
     * 根据名字获取get方法
     * @param fieldName
     * @return
     */
    public static String getGetMethodName(String fieldName){
        return "get"+StringUtils.firstUpperCase(fieldName);
    }
    /**
     * 根据名字获取set方法
     * @param fieldName
     * @return
     */
    public static String getSetMethodName(String fieldName){
        return "set"+StringUtils.firstUpperCase(fieldName);
    }
}
