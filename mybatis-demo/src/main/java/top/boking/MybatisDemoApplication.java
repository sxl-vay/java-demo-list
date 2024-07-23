package top.boking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import top.boking.mapper.IndexStudyMapper;
import org.mybatis.spring.boot.autoconfigure.MybatisLanguageDriverAutoConfiguration;
import top.boking.entity.IndexStudy;

import java.util.List;

@SpringBootApplication()
public class MybatisDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MybatisDemoApplication.class, args);
        IndexStudyMapper indexStudyMapper = (IndexStudyMapper) run.getBean("indexStudyMapper");
        MybatisLanguageDriverAutoConfiguration bean = run.getBean(MybatisLanguageDriverAutoConfiguration.class);
        List<IndexStudy> indexStudies = indexStudyMapper.selectAll();
        System.out.println("indexStudies = " + indexStudies);
    }

}
