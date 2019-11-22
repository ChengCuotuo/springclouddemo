package cn.nianzuochen.http;

import cn.nianzuochen.http.pojo.IT;
import cn.nianzuochen.http.pojo.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

// 确定测试的驱动器
@RunWith(SpringRunner.class)
// 确定进行测试的类
@SpringBootTest(classes = HttpDemoApplication.class)
public class HttpDemoApplicationTests {

    @Autowired
    RestTemplate restTEmplate;

    @Test
    public void testJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        user.setId(8);
        user.setAge(21);
        user.setName("柳岩");
        user.setUsername("liuyan");
        // 序列化
        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);
        // {"id":8,"username":"liuyan","password":null,"name":"柳岩","age":21,"gender":null,"birthday":null,"created":null,"note":null}
        // 反序列化，输入 json 字符串和对象
        User reverse  = objectMapper.readValue(json, User.class);
        System.out.println(reverse);
        // User{id=8, username='liuyan', password='null', name='柳岩', age=21, gender=null, birthday=null, created=null, note='null'}

        // 将集合处理成 json 字符串， jsckson 处理的时候允许传递集合
        String moreObjJson = objectMapper.writeValueAsString(Arrays.asList(user, user));
        System.out.println(moreObjJson);
        //[{"id":8,"username":"liuyan","password":null,"name":"柳岩","age":21,"gender":null,"birthday":null,"created":null,"note":null},
        // {"id":8,"username":"liuyan","password":null,"name":"柳岩","age":21,"gender":null,"birthday":null,"created":null,"note":null}]

        // 对于集合的反序列化，通过类型工厂提供构造函数，数据类型是 User 类的 List 集合，
        List<User> userList = objectMapper.readValue(moreObjJson,
                objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
        System.out.println(userList);
        //[User{id=8, username='liuyan', password='null', name='柳岩', age=21, gender=null, birthday=null, created=null, note='null'},
        // User{id=8, username='liuyan', password='null', name='柳岩', age=21, gender=null, birthday=null, created=null, note='null'}]

        // 当泛型关系比较复杂的时候提供上面的方法也是比较复杂的，使用 TypeReference 来指定泛型来处理

        IT it = new IT();
        it.setId(8);
        it.setAge(21);
        it.setName("柳岩");
        it.setUsername("liuyan");
        it.setCompany("凯捷");
        it.setDirection("java开发");
        String referenceJson = objectMapper.writeValueAsString(Arrays.asList(user, it));
        System.out.println(referenceJson);
        //[{"id":8,"username":"liuyan","password":null,"name":"柳岩","age":21,"gender":null,"birthday":null,"created":null,"note":null},
        // {"id":8,"username":"liuyan","password":null,"name":"柳岩","age":21,"gender":null,"birthday":null,"created":null,"note":null,
        // "identify":null,"company":"凯捷","direction":"java开发"}]

        // 并没有实现上转型，对于 IT 扩展的属性，不能实现忽略
//        List<User> users = objectMapper.readValue(referenceJson, new TypeReference<List<User>>(){});
//        System.out.println(users);
        // 但是可以将 List 同类型的方法进行简化反转
        List<User> userList1 = objectMapper.readValue(moreObjJson, new TypeReference<List<User>>(){});
        System.out.println(userList1);
        // [User{id=8, username='liuyan', password='null', name='柳岩', age=21, gender=null, birthday=null, created=null, note='null'},
        // User{id=8, username='liuyan', password='null', name='柳岩', age=21, gender=null, birthday=null, created=null, note='null'}]
    }

    @Test
    public void httpGet() {
        // RestTemplete 的 getForObject() 方法，传递 url 地址及实体类的字节码，
        // RestTemplete 会自动发起请求，接受响应，并对响应进行反序列化
        // 这个是访问本地的服务，所以 controller 中要有 hello 接口，并将服务启动起来
        User user = this.restTEmplate.getForObject("http://localhost:8080/hello", User.class);
        System.out.println(user);
        //User{id=8, username='liuyan', password='null', name='柳岩', age=21, gender=null, birthday=null, created=null, note='null'}
    }
}
