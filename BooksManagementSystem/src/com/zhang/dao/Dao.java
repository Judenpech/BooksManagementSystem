package com.zhang.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhang.model.BookInfo;
import com.zhang.model.BookType;
import com.zhang.model.Operator;
import com.zhang.model.Reader;
import com.zhang.model.User;

public class Dao {
	protected static String dbClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	protected static String dbUrl = "jdbc:sqlserver://localhost:1433;" + "DatabaseName=db_lib;SelectMethod=Cursor";
	protected static String dbUser = "sa";
	protected static String dbPwd = "zhangyong";
	protected static String second = null;
	private static Connection conn = null;

	// 创建数据库连接
	private Dao() {
		try {
			if (conn == null) {
				Class.forName(dbClassName).newInstance();
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
				System.out.println("数据库连接成功！");
			} else
				return;
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	// 执行查询
	private static ResultSet executeQuery(String sql) {
		try {
			if (conn == null)
				new Dao();
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}

	// 执行更新
	private static int executeUpdate(String sql) {

		try {
			if (conn == null)
				new Dao();
			return conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// if(e.getMessage().equals("[Microsoft][SQLServer 2000 Driver for
			// JDBC][SQLServer]DELETE 语句与 COLUMN REFERENCE 约束
			// 'FK_TB_BORRO_REFERENCE_TB_BOOKI' 冲突。该冲突发生于数据库 'db_library'，表
			// 'tb_borrow', column 'bookISBN'。"))
			return -1;
		} finally {
		}
	}

	// 关闭数据库
	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}

	/*
	 * 管理员登录方法
	 */
	public static Operator check(String name, String password) {
		int i = 0;
		Operator operater = new Operator();
		String sql = "select *  from tb_operator where name='" + name + "' and password='" + password + "'and admin=1";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				String names = rs.getString(1);
				operater.setId(rs.getString("id"));
				operater.setName(rs.getString("name"));
				operater.setGrade(rs.getString("admin"));
				operater.setPassword(rs.getString("password"));
				if (names != null) {
					i = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return operater;
	}

	/*
	 * 查询类别方法
	 */
	// 查询图书目录
	public static List selectBookCategory() {
		List list = new ArrayList();
		String sql = "select *  from tb_bookType";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookType bookType = new BookType();
				bookType.setId(rs.getString("id"));
				bookType.setTypeName(rs.getString("typeName"));
				list.add(bookType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;

	}

	public static List selectBookCategory(String bookType) {
		List list = new ArrayList();
		String sql = "select days  from tb_bookType where typeName='" + bookType + "'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookType type = new BookType();
				type.setDays(rs.getString("days"));
				list.add(type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;

	}

	/*
	 * 图书类别表相关操作
	 * 
	 */
	// 插入图书类型
	public static int InsertBookType(String bookTypeName) {
		int i = 0;
		try {
			String sql = "insert into tb_bookType(typeName) values('" + bookTypeName + "')";
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	// 更新图书类别
	public static int UpdatebookType(String id, String typeName) {
		int i = 0;
		try {
			String sql = "update tb_bookType set typeName='" + typeName + "' where id='" + id + "'";
			// System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	// 删除图书类别
	public static int DelbookType(String id) {
		int i = 0;
		try {
			String sql = "delete from tb_bookType where id='" + id + "'";
			// System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	/*
	 * 图书信息表相关操作
	 */
	/*
	 * 插入图书信息方法
	 */
	// 插入图书信息
	public static int Insertbook(String ISBN, String typeId, String bookname, String writer, String translator,
			String publisher, Date date, Double price) {
		int i = 0;
		try {
			String sql = "insert into tb_bookInfo(ISBN,typeId,bookname,writer,translator,publisher,date,price) values('"
					+ ISBN + "','" + typeId + "','" + bookname + "','" + writer + "','" + translator + "','" + publisher
					+ "','" + date + "'," + price + ")";
			// System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Dao.close();
		return i;
	}

	/*
	 * 查询图书相关信息
	 * 
	 */
	public static List selectBookInfo() {
		List list = new ArrayList();
		String sql = "select *  from tb_bookInfo";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookInfo bookinfo = new BookInfo();
				bookinfo.setISBN(rs.getString("ISBN"));
				bookinfo.setTypeid(rs.getString("typeid"));
				bookinfo.setBookname(rs.getString("bookname"));
				bookinfo.setWriter(rs.getString("writer"));
				bookinfo.setTranslator(rs.getString("translator"));
				bookinfo.setPublisher(rs.getString("publisher"));
				bookinfo.setDate(rs.getDate("date"));
				bookinfo.setPrice(rs.getDouble("price"));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	public static List selectBookInfo(String ISBN) {
		List list = new ArrayList();
		String sql = "select *  from tb_bookInfo where ISBN='" + ISBN + "'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookInfo bookinfo = new BookInfo();
				bookinfo.setISBN(rs.getString("ISBN"));
				bookinfo.setTypeid(rs.getString("typeid"));
				bookinfo.setBookname(rs.getString("bookname"));
				bookinfo.setWriter(rs.getString("writer"));
				bookinfo.setTranslator(rs.getString("translator"));
				bookinfo.setPublisher(rs.getString("publisher"));
				bookinfo.setDate(rs.getDate("date"));
				bookinfo.setPrice(rs.getDouble("price"));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	/*
	 * 修改图书信息方法
	 */
	// 更新图书信息
	public static int Updatebook(String ISBN, String typeId, String bookname, String writer, String translator,
			String publisher, Date date, Double price) {
		int i = 0;
		try {
			String sql = "update tb_bookInfo set ISBN='" + ISBN + "',typeId='" + typeId + "',bookname='" + bookname
					+ "',writer='" + writer + "',translator='" + translator + "',publisher='" + publisher + "',date='"
					+ date + "',price=" + price + " where ISBN='" + ISBN + "'";
			// System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	// /*
	// * 删除图书信息方法
	// */
	public static int Delbook(String ISBN) {
		int i = 0;
		try {
			String sql = "delete from tb_bookInfo where ISBN='" + ISBN + "'";
			// System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	/*
	 * 对读者信息表执行的相关操作
	 */
	// 增加读者信息
	public static int InsertReader(String name, String sex, String age, String identityCard, Date date, String maxNum,
			String tel, Double keepMoney, String zj, String zy, Date bztime, String ISBN) {
		int i = 0;
		try {
			String sql = "insert into tb_reader(name,sex,age,identityCard,date,maxNum,tel,keepMoney,zj,zy,bztime,ISBN) values('"
					+ name + "','" + sex + "','" + age + "','" + identityCard + "','" + date + "','" + maxNum + "','"
					+ tel + "'," + keepMoney + ",'" + zj + "','" + zy + "','" + bztime + "','" + ISBN + "')";
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	public static List selectReader() {
		List list = new ArrayList();
		String sql = "select *  from tb_reader";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader = new Reader();
				// reader.setId(rs.getString("id"));
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdentityCard(rs.getString("identityCard"));
				reader.setDate(rs.getDate("date"));
				reader.setMaxNum(rs.getString("maxNum"));
				reader.setTel(rs.getString("tel"));
				reader.setKeepMoney(rs.getDouble("keepMoney"));
				reader.setZj(rs.getInt("zj"));
				reader.setZy(rs.getString("zy"));
				reader.setISBN(rs.getString("ISBN"));
				reader.setBztime(rs.getDate("bztime"));
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	public static List selectReader(String readerISBN) {
		List list = new ArrayList();
		String sql = "select *  from tb_reader where ISBN='" + readerISBN + "'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader = new Reader();
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdentityCard(rs.getString("identityCard"));
				reader.setDate(rs.getDate("date"));
				reader.setMaxNum(rs.getString("maxNum"));
				reader.setTel(rs.getString("tel"));
				reader.setKeepMoney(rs.getDouble("keepMoney"));
				reader.setZj(rs.getInt("zj"));
				reader.setZy(rs.getString("zy"));
				reader.setISBN(rs.getString("ISBN"));
				reader.setBztime(rs.getDate("bztime"));
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// 更新读者信息
	public static int UpdateReader(String id, String name, String sex, String age, String identityCard, Date date,
			String maxNum, String tel, Double keepMoney, String zj, String zy, Date bztime, String ISBN) {
		int i = 0;
		try {
			String sql = "update tb_reader set name='" + name + "',sex='" + sex + "',age='" + age + "',identityCard='"
					+ identityCard + "',date='" + date + "',maxNum='" + maxNum + "',tel='" + tel + "',keepMoney="
					+ keepMoney + ",zj='" + zj + "',zy='" + zy + "',bztime='" + bztime + "'where ISBN='" + ISBN + "'";
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	// 删除读者信息
	public static int DelReader(String ISBN) {
		int i = 0;
		try {
			String sql = "delete from tb_reader where ISBN='" + ISBN + "'";
			// System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	// new
	public static List selectbookserch() {
		List list = new ArrayList();
		String sql = "select *  from tb_bookInfo";
		ResultSet s = Dao.executeQuery(sql);
		try {
			while (s.next()) {
				BookInfo bookinfo = new BookInfo();
				bookinfo.setISBN(s.getString(1));
				bookinfo.setTypeid(s.getString(2));
				bookinfo.setBookname(s.getString(3));
				bookinfo.setWriter(s.getString(4));
				bookinfo.setTranslator(s.getString(5));
				bookinfo.setPublisher(s.getString(6));
				bookinfo.setDate(s.getDate(7));
				bookinfo.setPrice(s.getDouble(8));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// 插入管理员
	public static int Insertoperator(String name, String sex, int age, String identityCard, Date workdate, String tel,
			String password, int yajin) {
		int i = 0;
		try {
			String sql = "insert into tb_operator(name,sex,age,identityCard,workdate,tel,password,yajin) values('"
					+ name + "','" + sex + "'," + age + ",'" + identityCard + "','" + workdate + "','" + tel + "','"
					+ password + "','" + yajin + "')";
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	public static List selectuser() {
		List list = new ArrayList();
		String sql = "select id,name,sex,age,yajin,workdate,tel,password  from tb_operator where admin=0";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSex(rs.getString(3));
				user.setAge(rs.getInt(4));
				// user.setIdentityCard(rs.getString(5));
				user.setYajin(rs.getInt(5));
				user.setWorkdate(rs.getDate(6));
				user.setTel(rs.getString(7));
				user.setPassword(rs.getString(8));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// 删除用户
	public static int Deluser(int id) {
		int i = 0;
		try {
			String sql = "delete from tb_operator where id='" + id + "'";
			// System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	public static int Updateuser(int id, String name, String sex, int age, String identityCard, Date workdate,
			String tel, String password) {
		int i = 0;
		try {
			String sql = "update tb_operator set name='" + name + "',sex='" + sex + "',age=" + age + ",identityCard='"
					+ identityCard + "',workdate='" + workdate + "',tel='" + tel + "',password='" + password
					+ "' where id='" + id + "'";
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	public static int Updatepass(String password, String name) {
		int i = 0;
		try {
			String sql = "update tb_operator set password='" + password + "' where name='" + name + "'";

			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
}
