package com.nicebin.user.mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nicebin.user.entity.User;
import com.nicebin.user.mapper.UserMapper;
import com.nicebin.user.service.impl.UserServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author DiaoJianBin
 * @Description mybatis-plus的一些测试
 * @Date 2021/4/15 11:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration()
public class MybatisPlusTest {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void testMybaits() {
        UserMapper userMapper1 = userService.getBaseMapper();
        UserMapper userMapper2 = sqlSessionFactory.openSession().getMapper(UserMapper.class);
        //结果是false
        System.out.println("两种方式获得的Mapper是不是同一个 " + (userMapper1 == userMapper2));
    }

    /**
     * 检测Mybatis的自动填充
     * 途中再了解下sqlSession的关闭开启
     */
    @Test
    public void testFill() {

        //用userService的方式操作（常规操作）
        //看控制台发现执行每条SQL都会新建一个sqlSession，执行完毕都会Close掉（如果不在事务内）
        //Creating a new SqlSession
        //....
        //Closing non transactional SqlSession
        User user = new User();
        user.setName("tom");
        user.setAge(20);
        user.setEmail("tom@163.com");

        UserMapper userMapper1 = userService.getBaseMapper();
        System.out.println("开始保存user");
        userService.save(user);
        System.out.println("user保存结束");

        UserMapper userMapper2 = userService.getBaseMapper();
        System.out.println("开始查找user");
        List list = userService.list();
        System.out.println("user查找结束，输出查找结果");
        list.forEach(System.out::println);

        //结果是true，说明代理类UserMapper可以用sqlSession生成
        //但两者并没直接的联系，sqlSession关闭后不影响已生成的UserMapper
        System.out.println("两此执行的Mapper是不是同一个 " + (userMapper1 == userMapper2));
    }

    /**
     * 测试分页插件
     */
    @Test
    public void testPage() {
        //其实page和result指针指向的是同一个类（并不是查询之后会新建一个）
        Page<User> page = new Page<>();
        Page<User> result = userService.page(page);
        //默认的分页数据，输出结果如下
        //获取当前页 = 1
        //获取总记录数 = 2
        //获取每页的条数 = 10
        //获取每页数据的集合 = [User(id=1382590588458442754, name=tom1, age=20, email=tom@163.com, createTime=Thu Apr 15 15:04:18 CST 2021, updateTime=null, delete_flag=null)
        //                   , User(id=1382597161507405826, name=tom2, age=20, email=tom@163.com, createTime=Thu Apr 15 15:30:25 CST 2021, updateTime=null, delete_flag=null)]
        //获取总页数 = 1
        //是否存在下一页 = false
        //是否存在上一页 = false
        System.out.println("获取当前页 = "+result.getCurrent()); // 获取当前页
        System.out.println("获取总记录数 = "+result.getTotal()); // 获取总记录数
        System.out.println("获取每页的条数 = "+result.getSize()); // 获取每页的条数
        System.out.println("获取每页数据的集合 = "+result.getRecords()); // 获取每页数据的集合
        System.out.println("获取总页数 = "+result.getPages()); // 获取总页数
        System.out.println("是否存在下一页 = "+result.hasNext()); // 是否存在下一页
        System.out.println("是否存在上一页 = "+result.hasPrevious()); // 是否存在上一页

        //显示第一页的1条数据，以名字倒叙，因此会输出tom2
        page = new Page<>(1,1);
        OrderItem orderItem = OrderItem.desc("name");
        page.addOrder(orderItem);
        result = userService.page(page);
        //输出结果
        //[User(id=1382597161507405826, name=tom2, age=20, email=tom@163.com, createTime=Thu Apr 15 15:30:25 CST 2021, updateTime=null, delete_flag=null)]
        System.out.println("获取每页数据的集合 = "+result.getRecords());
    }

    /**
     * 测试版本号（乐观锁）
     * 一定要查找出原来的数据（附带版本号），才能触发@Version更新
     * 不然version字段为空的时候，不触发的
     */
    @Test
    public void testVersion() throws Exception{
        User user = new User();
        user.setName("lili");
        user.setAge(11);
        user.setEmail("old@qq.com");
        userService.save(user);

        //休息5秒，让更新时间离创建时间远一点
        //假设这里是进行了其他的逻辑
        Thread.sleep(5 * 1000);

        //查询数据库最新的内容
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name","lili");
        User user2 = userService.list(queryWrapper).get(0);
        user2.setAge(66);
        user2.setEmail("new@qq.com");

        userService.updateById(user2);
        userService.list().forEach(System.out::println);
    }
}
