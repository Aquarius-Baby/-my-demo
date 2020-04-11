package com.cmy.mybitis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * mybatis源码分析入口
 */
public class MyBatisTest {
    public static void main(String[] args) throws IOException {
        // 创建一个book对象
        Book book = new Book();
        book.setBookId(1006);
        book.setName("Easy Coding");
        book.setNumber(110);
        // 加载配置文件 并构建SqlSessionFactory对象
        String resource = "mybatis/mybatis-config.xml";
        // 不停调用，获取到自身的ClassLoader对象，然后交给ClassLoader(lang包下的)来加载
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // new SqlSessionFactoryBuilder()只是创建一个对象实例,而没有对象返回(建造者模式)，对象的返回交给build()方法。
        // 不停调用，直到通过Document对象解析inputStream，
        // 然后交给XMLConfigBuilder构造成org.apache.ibatis.session.Configuration对象
        // 然后交给build()方法构造程SqlSessionFactory：
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        // 从SqlSessionFactory对象中获取 SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 执行操作
        sqlSession.insert("insert", book);
        // 提交操作
        sqlSession.commit();
        // 关闭SqlSession
        sqlSession.close();
    }
}

