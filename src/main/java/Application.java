import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * className:Application
 * discription:
 * author:qcm
 * createTime:2018-12-03 13:41
 */
@SpringBootApplication
@MapperScan("com.aaa.gjj.dao")//扫描dao层接口
public class Application {
    /**
     * 主函数
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
