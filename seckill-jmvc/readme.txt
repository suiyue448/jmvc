�˿��Ϊ MVC��ܣ����ߣ��֥���������JMVC
Ҫ������JMVC��ǰ�����������÷�����Ŀ���̱�����spring boot ���̣�����JMVC����Ӱ��spring mvc�� ���Ժ�spring mvc ����ʹ��




����pom.xml ���룺

org.springframework.boot
spring-boot-starter-web
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-freemarker</artifactId>
    </dependency>
ʹ�ý̳̣� ���� @ServletComponentScan ע��
@ServletComponentScan
@SpringBootApplication
public class SeckillAppApplication {

�½� action�ࣺ�൱�� spring mvc ��controller
HelloDemoAction �̳� JingMVC

HelloDemoAction �������£�

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
     * �������ж���������ж�����ֲ�������ôֻ�ǻ�ȡ��һ�������������url����ȡ���� age �Ĳ�����
     * ������ȡage��������Ҫ����д����
     */

    Integer aaa2= GetParamters.obtainInteger(req);
    Integer age = GetParamters.obtainInteger("age", req);
    String lastAaa=aaa+aaa2;
    map.put("name", lastAaa);
    map.put("age", age);
    /**
     * ��Ӧ��·���� resources/templates/hello/first.ftl
     */
    return "/hello/first.ftl";
}

/**
 *http://localhost:2047/seckill_app/helloDemo/helloSss?id=9&age=88&name=GGG&hobby=play game
 */
public String helloSss(HttpServletRequest req, HttpServletResponse resp, Map map){
    /**
     * �������ж���������ж�����ֲ�������ôֻ�ǻ�ȡ��һ�������������url����ȡ���� age �Ĳ�����
     * ������ȡage��������Ҫ����д����
     * ͬ��������������������ַ����͵ģ�Ҳ��һ��
     */
    Integer aaa2= GetParamters.obtainInteger(req); // ��ȡ�� id
    Integer age = GetParamters.obtainInteger("age", req);
    String name=GetParamters.obtainString(req);
    String hobby=GetParamters.obtainString("hobby", req);
    map.put("name", name);
    map.put("age", age);
    map.put("hobby", hobby);
    /**
     * ��Ӧ��·���� resources/templates/hello/hobby.ftl
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
 *ע�⣺ʵ����ĳ�Ա����һ���� public���ε�
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