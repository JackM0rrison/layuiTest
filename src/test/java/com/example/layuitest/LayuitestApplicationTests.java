package com.example.layuitest;

import com.example.layuitest.dao.TestMapper;
import com.example.layuitest.domain.test_table;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class LayuitestApplicationTests {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Test
    void contextLoads() {
        List<test_table> list = testMapper.selectAll();
        for(test_table current : list){
            System.out.println(current.getName());
        }
    }

    @Test
    void testBatch(){
        List<test_table> list = new ArrayList<>();
        for(int i = 1; i <= 100; i++){
            test_table test_table = new test_table();
            test_table.setId(i);
            test_table.setName(String.valueOf(i));
            list.add(test_table);
        }
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);

        try {
            long currentTime = System.currentTimeMillis();
            TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
            for (int i = 0; i < list.size(); i++) {
                testMapper.insert(list.get(i));
//                sqlSession.commit();
                if (i % 1000 == 0 || i == list.size() - 1) {
                    //手动每400条提交一次，提交后无法回滚
                    sqlSession.commit();
                    //清理缓存，防止溢出
                    sqlSession.clearCache();
                    //System.out.println("commit success");
                }
            }
            // System.out.println(System.currentTimeMillis()-currentTime);
        } catch (Exception e) {
            //没有提交的数据可以回滚
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    @Transactional
    void TestTransactional(){
        test_table one = new test_table();
        one.setName("one");
        testMapper.insert(one);

        int a = 4/0;

        test_table two = new test_table();
        one.setName("two");
        testMapper.insert(two);
    }

}
