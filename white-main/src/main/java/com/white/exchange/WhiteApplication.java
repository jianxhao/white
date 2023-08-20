package com.white.exchange;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author xh
 * @create 2023-08-19  22:19
 */

@SpringBootApplication
@Slf4j
@MapperScan("com.white.exchange.mapper")
public class WhiteApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WhiteApplication.class, args);
        try {
            getInfo(context);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    // 打印启动信息
    private static void getInfo(ConfigurableApplicationContext context) throws UnknownHostException {
        ConfigurableEnvironment environment = context.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        String active = environment.getProperty("spring.profiles.active");

        log.info("\n----------------------------------------------------------\n\t" +
                "示例程序【" + active + "】环境已启动! 地址如下:\n\t" +
                "Local: \t\thttp://localhost:" + port + "\n\t" +
                "External: \thttp://" + ip + ':' + port + '\n' +
                "----------------------------------------------------------");

    }

}
