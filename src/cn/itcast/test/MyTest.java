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
			// 1. ��ȡ�����ļ���MyBatis��ר�Ŷ�ȡ�����ļ��Ĺ�����Resources��
			InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
			// 2. �����������ļ������Ự����
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			// 3. ���ݻỰ���������Ự����
			// ҵ���ͨ��SqlSession�������������ݿ����CRUD������ÿ��ִ�з����лỰ����Ҫ˽��
			sqlSession = sqlSessionFactory.openSession();
			System.out.println(sqlSession);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			//�رջỰ
			sqlSession.close();
		}
	}
	@Test
	public void test1() throws Exception{
		// 1. ��ȡ�����ļ���MyBatis��ר�Ŷ�ȡ�����ļ��Ĺ�����Resources��
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		// ���������ļ������Ự����
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// ���ݻỰ���������Ự����
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ����id��ѯ�û���Ϣ
		User userInfo = sqlSession.selectOne("user.findUserById", 1006);
		System.out.println(userInfo);
		sqlSession.close();
	}
	@Test
	public void test2() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//�����û�����ѯ�û���Ϣ
		List<User> list = sqlSession.selectList("user.findUserByUserName", "%��%");
		System.out.println(list);
		sqlSession.close();
	}
	@Test
	public void test3() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//�����û�����ѯ�û���Ϣ(��)
		List<User> list = sqlSession.selectList("user.findUserByUserName2", "��");
		System.out.println(list);
		sqlSession.close();
	}
	@Test
	public void test4() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//��ѯ�û����¼��
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
			//�����û���Ϣ
			User user = new User();
			user.setName("С��");
			user.setAddress("����");
			user.setAge(18);
			user.setSex("��");
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
			//�����û���Ϣ
			User user = new User();
			user.setName("С��");
			user.setAddress("�Ϻ�");
			user.setAge(20);
			user.setSex("��");
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
			//�����û�idɾ���û���Ϣ
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
			// �����û���Ϣ
			User user = new User();
			user.setName("С��");
			user.setAddress("�Ϻ�");
			user.setAge(20);
			user.setSex("��");
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
