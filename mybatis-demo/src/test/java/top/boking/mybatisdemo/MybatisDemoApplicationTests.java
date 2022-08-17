package top.boking.mybatisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import top.boking.entity.IndexStudy;
import top.boking.mapper.IndexStudyMapper;

import java.util.List;

/*
@SpringBootTest
*/
class MybatisDemoApplicationTests {

    @Autowired
    private IndexStudyMapper mapper;

//    @Test
    void contextLoads() {
        IndexStudy indexStudy = new IndexStudy();
        indexStudy.setName("sxl");
        Boolean aBoolean = mapper.deleteCase(indexStudy);
        System.out.println("aBoolean = " + aBoolean);
    }

}
