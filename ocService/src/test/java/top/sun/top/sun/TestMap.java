package top.sun.top.sun;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/28 0028 10:08
 */
public class TestMap {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 12);
        map.put("c", 13);
        map.put("d", 14);
        map.put("e", 15);
        map.put("f", 16);
        //todo 推荐一二 不建议for循环根据k得到v效率最低的
      /* 第一种k,v
      Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey()+"  "+entry.getValue());

        }*/
       /*
       第二种分别取出k和v
       for (String s : map.keySet()) {
            System.out.println(s);

        }
        for (Integer integer : map.values()) {
            System.out.println(integer);

        }*/
       /* 第三：迭代，根据key取出value
       Iterator<String> iterator1 = map.keySet().iterator();
        while (iterator1.hasNext()){
            String next = iterator1.next();
            System.out.println(next+":"+map.get(next));
        }*/


    }
}
