package com.sleuth.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author luoxuri
 * @create 2017-12-15 10:39
 **/
@SpringBootApplication
@RestController
public class SleuthConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SleuthConsumerApplication.class, args);
    }

    private static final Logger LOG = LoggerFactory.getLogger(SleuthConsumerApplication.class.getName());

    @GetMapping("/hi")
    public String home(){
        LOG.info("sleuth-consumer的 home 被调用");
        return "你好，我是sleuth-consumer";
    }

    @GetMapping("/miya")
    public String info(){
        LOG.info("sleuth-consumer的 info 被调用");
        return restTemplate.getForObject("http://192.168.1.185:9412/info", String.class);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
