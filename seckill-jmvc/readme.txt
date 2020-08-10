此框架为 MVC框架，作者：李敬芝，框架名：JMVC
要想引入JMVC，前提条件是引用方的项目工程必须是spring boot 工程，引入JMVC，不影响spring mvc， 可以和spring mvc 兼容使用




工程pom.xml 引入：

org.springframework.boot
spring-boot-starter-web
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-freemarker</artifactId>
    </dependency>
使用教程： 加入 @ServletComponentScan 注解
@ServletComponentScan
@SpringBootApplication
public class SeckillAppApplication {

新建 action类：相当于 spring mvc 的controller
HelloDemoAction 继承 JingMVC

HelloDemoAction 代码如下：

import org.springframework.beans.factory.annotation.Autowired;
import seckill.com.seckillapp.entity.HelloVo;
import seckill.com.seckillapp.service.HelloDemoService;
import seckill.com.seckilljmvc.jingzhi.GetParamters;
import seckill.com.seckilljmvc.jingzhi.JingMVC;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet(urlPatterns = "/helloDemo/*")
public class HelloDemoAction extends JingMVC {
@Autowired
private HelloDemoService helloDemoService;

/**
 *http://localhost:2047/seckill_app/helloDemo/helloAaa?id=9&age=88
 */
public String helloAaa(HttpServletRequest req, HttpServletResponse resp, Map map){
    String aaa = helloDemoService.helloFirst();
    /**
     * 当参数有多个，并且有多个数字参数，那么只是获取第一个，根据上面的url，获取不到 age 的参数，
     * 如果想获取age参数，需要另外写代码
     */

    Integer aaa2= GetParamters.obtainInteger(req);
    Integer age = GetParamters.obtainInteger("age", req);
    String lastAaa=aaa+aaa2;
    map.put("name", lastAaa);
    map.put("age", age);
    /**
     * 对应的路径： resources/templates/hello/first.ftl
     */
    return "/hello/first.ftl";
}

/**
 *http://localhost:2047/seckill_app/helloDemo/helloSss?id=9&age=88&name=GGG&hobby=play game
 */
public String helloSss(HttpServletRequest req, HttpServletResponse resp, Map map){
    /**
     * 当参数有多个，并且有多个数字参数，那么只是获取第一个，根据上面的url，获取不到 age 的参数，
     * 如果想获取age参数，需要另外写代码
     * 同理：如果参数里面包括多个字符类型的，也是一样
     */
    Integer aaa2= GetParamters.obtainInteger(req); // 获取到 id
    Integer age = GetParamters.obtainInteger("age", req);
    String name=GetParamters.obtainString(req);
    String hobby=GetParamters.obtainString("hobby", req);
    map.put("name", name);
    map.put("age", age);
    map.put("hobby", hobby);
    /**
     * 对应的路径： resources/templates/hello/hobby.ftl
     */
    return "/hello/hobby.ftl";
}

/**
 * http://localhost:2047/seckill_app/helloDemo/helloBbb?id=8&name=yyy&age=99
 *
 * public class HelloVo {
 *     public Integer id;
 *     public String name;
 *     public int age;
 *
 *     public Integer getId() {
 *         return id;
 *     }
 *
 *     public void setId(Integer id) {
 *         this.id = id;
 *     }
 *
 *     public String getName() {
 *         return name;
 *     }
 *
 *     public void setName(String name) {
 *         this.name = name;
 *     }
 *
 *     public int getAge() {
 *         return age;
 *     }
 *
 *     public void setAge(int age) {
 *         this.age = age;
 *     }
 * }
 *注意：实体类的成员变量一定是 public修饰的
 */

public String helloBbb(HttpServletRequest req, HttpServletResponse resp, Map map){
    HelloVo vo = GetParamters.obtainParam(HelloVo.class, req);
    map.put("hello", vo);
    return "/hello/two.ftl";
}

/**
 *
 http://localhost:2047/seckill_app/helloDemo/helloCcc?name=HHH
 */
public String helloCcc(HttpServletRequest req, HttpServletResponse resp, Map map){
    String name = GetParamters.obtainString("name", req);
    map.put("name", name);
    return "/hello/first.ftl";
}
}