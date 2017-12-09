package cn.itcast.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.itcast.dao.UserDao;
import cn.itcast.pojo.User;

public class MyTest2 {

	@Test
	public void test3() throws Exception {
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		// 2. 根据主配置文件创建会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 用getMapper取得自动生成的DAO接口的实现类
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		User userInfo = userDao.findUserById(1001);
		System.out.println(userInfo);
		sqlSession.close();
	}

	// 测试根据id查询用户信息（动态代理DAO开发方式）
	@Test
	public void test4() throws Exception {
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		// 2. 根据主配置文件创建会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		List<User> userList = userDao.findUserByUserName("迎");
		System.out.println(userList);
		sqlSession.close();
	}
}
