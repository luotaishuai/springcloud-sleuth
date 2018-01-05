package com.sleuth.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;

/**
 * @author luoxuri
 * @create 2017-12-15 10:21
 **/
@SpringBootApplication
@RestController
public class SleuthProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SleuthProviderApplication.class, args);
    }

    private static final Logger LOG = LoggerFactory.getLogger(SleuthProviderApplication.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/hi")
    public String callHome(){
        LOG.info("--->调用追踪：sleuth-provider");
        return restTemplate.getForObject("http://192.168.1.185:9413/miya", String.class);
    }

    @GetMapping("/info")
    public String info(){
        LOG.info("--->调用追踪：sleuth-provider");
        return "--->我是sleuth-provider";
    }

    @Bean
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }
}
