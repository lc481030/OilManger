package com.oil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author oli
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class OilApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(OilApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  启动成功  ");
    }
}