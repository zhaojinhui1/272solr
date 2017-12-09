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
		// 2. �����������ļ������Ự����
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ��getMapperȡ���Զ����ɵ�DAO�ӿڵ�ʵ����
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		User userInfo = userDao.findUserById(1001);
		System.out.println(userInfo);
		sqlSession.close();
	}

	// ���Ը���id��ѯ�û���Ϣ����̬����DAO������ʽ��
	@Test
	public void test4() throws Exception {
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		// 2. �����������ļ������Ự����
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		List<User> userList = userDao.findUserByUserName("ӭ");
		System.out.println(userList);
		sqlSession.close();
	}
}
