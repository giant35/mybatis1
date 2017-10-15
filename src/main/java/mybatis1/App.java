package mybatis1;

import java.io.InputStream;
import java.util.Objects;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class App {
	public static void main(String[] argv) throws Exception {
		final InputStream is = App.class.getClassLoader().getResourceAsStream("mybatis.xml");
		Objects.requireNonNull(is);
		SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
		SqlSession s = sf.openSession();
		System.out.println("after open session");
		int id ;
		{
			T1 t = new T1();
			t.setName("tom1");
			s.insert("mybatis1.T1Mapper.insertT1", t);
			s.commit();
			System.out.println("after insert commit");
			id = t.getId();
		}
		{
			T1 t2 = s.selectOne("mybatis1.T1Mapper.selectT1", id);
			System.out.println(t2);
		}
		s.close();
	}
}
