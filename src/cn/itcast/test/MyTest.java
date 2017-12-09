package cn.itcast.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.itcast.pojo.Order;
import cn.itcast.pojo.User;

public class MyTest {
	@Test
	public void enTest() throws Exception{
		SqlSession sqlSession = null;
		try {
			// 1. 读取配置文件（MyBatis有专门读取配置文件的工具类Resources）
			InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
			// 2. 根据主配置文件创建会话工厂
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			// 3. 根据会话工厂创建会话对象
			// 业务层通过SqlSession对象来访问数据库进行CRUD操作，每个执行方法中会话对象要私有
			sqlSession = sqlSessionFactory.openSession();
			System.out.println(sqlSession);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			//关闭会话
			sqlSession.close();
		}
	}
	@Test
	public void test1() throws Exception{
		// 1. 读取配置文件（MyBatis有专门读取配置文件的工具类Resources）
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		// 根据配置文件创建会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 根据会话工厂创建会话对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 根据id查询用户信息
		User userInfo = sqlSession.selectOne("user.findUserById", 1006);
		System.out.println(userInfo);
		sqlSession.close();
	}
	@Test
	public void test2() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//根据用户名查询用户信息
		List<User> list = sqlSession.selectList("user.findUserByUserName", "%王%");
		System.out.println(list);
		sqlSession.close();
	}
	@Test
	public void test3() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//根据用户名查询用户信息(二)
		List<User> list = sqlSession.selectList("user.findUserByUserName2", "王");
		System.out.println(list);
		sqlSession.close();
	}
	@Test
	public void test4() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//查询用户表记录数
		int count = sqlSession.selectOne("user.countUserRecord");
		System.out.println(count);
		sqlSession.close();
	}
	@Test
	public void test5() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			//插入用户信息
			User user = new User();
			user.setName("小明");
			user.setAddress("北京");
			user.setAge(18);
			user.setSex("男");
			user.setMobile("13500099000");
			int count = sqlSession.insert("user.addUserInfo", user);
			System.out.println(count);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		}finally{
			sqlSession.close();
		}
		
	}
	@Test
	public void test6() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			//插入用户信息
			User user = new User();
			user.setName("小明");
			user.setAddress("上海");
			user.setAge(20);
			user.setSex("男");
			user.setMobile("17602188976");
			user.setUserId(1012);
			int count = sqlSession.update("user.updateUserId", user);
			System.out.println(count);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		}finally{
			sqlSession.close();
		}
		
	}
	@Test
	public void test7() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			//根据用户id删除用户信息
			int count = sqlSession.delete("user.deleteUserId", 1013);
			System.out.println(count);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
		}
		sqlSession.close();
		}
	@Test
	public void test8() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			// 插入用户信息
			User user = new User();
			user.setName("小明");
			user.setAddress("上海");
			user.setAge(20);
			user.setSex("男");
			user.setMobile("17602188976");
			System.out.println("user.userId=" + user.getUserId());
			int count = sqlSession.insert("user.addUserInfo2", user);
			System.out.println(count);
			System.out.println("user.userId=" + user.getUserId());
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
		}
		sqlSession.close();
		}
	@Test
	public void test9() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			Order order = new Order();
			order.setGoodsId("123456789");
			order.setOrderStatus("01");
			order.setUserId(1008);
			System.out.println(order.getOrderId());
			sqlSession.insert("order.insertOrderData", order);
			System.out.println(order.getOrderId());
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
		}
		sqlSession.close();
		}	
}
