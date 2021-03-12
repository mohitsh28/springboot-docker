import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.poc.spring.docker"})
@EnableBinding
@EnableAsync
public class AppRunner {

    public static void main(String[] args) {
        log.info("Booting up....");
        SpringApplication.run(AppRunner.class,args);
    }
}
