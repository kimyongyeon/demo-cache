package com.example.democache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCaching
@RestController
public class DemoCacheApplication implements ApplicationRunner {

    @Autowired
    MemberService service;

    MemberDTO memberDTO = new MemberDTO(1L);

    @RequestMapping("/members")
    public String getMembers(String name, Long cnt) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        if (cnt == null) {
            cnt = 0L;
        }
        if (cnt == 0L) {
            cnt = memberDTO.getCount() + 1;
        }
        memberDTO.setCount(cnt);
        String result = service.memberList(name + "::" + memberDTO.getCount());
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        return result;
    }

    @RequestMapping("/delMembers")
    public String deleteMembers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String result = service.memberRemove("tonyspark");
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        return result;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoCacheApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("Cache Project start");
        service.memberList("tonyspark");
        service.memberList("tonyspark");
        System.out.println("Cache Project end");
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

    }
}
