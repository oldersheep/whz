package top.deramertn9527.center.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * 对象转换工具类
 */
public class BeanUtil {

    private BeanUtil() {
    }

    /**
     * 字符串转换set集合
     *
     * @param s   字符串
     * @param <T> 转换集合中类型
     * @return Set<T>
     */
    public static <T> Set<T> toSet(String s, Class<T> target) {
        return new HashSet<>(toList(s, target));
    }

    /**
     * 字符串转换list集合
     *
     * @param s 字符串
     * @return List<T>
     */
    public static <T> List<T> toList(String s, Class<T> target) {
        return JSONArray.parseArray(s, target);
    }

    /**
     * Object to T
     *
     * @param source 待转换对象
     * @param target 转换目标类
     * @param <T>    泛型
     * @return T
     */
    public static <T> T toBean(Object source, Class<T> target) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(source);
        return JSONObject.parseObject(jsonObject.toJSONString(), target);
    }

    /**
     * List to List<T>
     *
     * @param source 待转换对象
     * @param target 转换目标类
     * @param <T>    泛型
     * @return List<T>
     */
    public static <T> List<T> toBeanList(List source, Class<T> target) {
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(source);
        return JSONArray.parseArray(jsonArray.toJSONString(), target);
    }

    /**
     * 字符串转换对象
     *
     * @param s      字符串
     * @param target 对象类型
     * @param <T>    泛型
     * @return T
     */
    public static <T> T toBean(String s, Class<T> target) {
        return JSONObject.parseObject(s, target);
    }


    /**
     * 将集合中的数据，随机排序
     *
     * @param tList 待排序的集合
     * @param <T>   集合中的数据类型
     * @return List<T>
     */
    public static <T> List<T> randomList(List<T> tList) {
        List<T> retList = new ArrayList<>();
        int size = tList.size();
        boolean[] booleans = new boolean[size];
        int index;
        int totalNum = 0;
        Random r = new Random();
        while (totalNum != size) {
            index = r.nextInt(size);
            if (!booleans[index]) {
                retList.add(tList.get(index));
                booleans[index] = true;
                totalNum++;
            }
        }
        return retList;
    }

    /**
     * 去掉separate并将separate后的第一个字符大写
     *
     * @param source
     * @param separate
     * @return
     */
    public static String separateToUpper(String source, String separate) {
        int index = source.indexOf(separate);
        if (index == -1) {
            return source;
        }
        String upper = source.substring(index + 1, index + 2).toUpperCase();
        StringBuffer sb = new StringBuffer();
        sb.append(source);
        sb.replace(index, index + 2, upper);
        return sb.toString();
    }
}