package com.example.democache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    /**
     * 캐시 지정
     */
    @Cacheable(key = "#name", value = "tony", condition = "#name.length() < 10")
    public String memberList(String name) {
        System.out.println("MemberService::memberList => " + name);
        // 키값이 같다면 서비스를 call하지 않는다.
        simulationSlowService();
        return "MemberList";
    }

    /**
     * DB처리라고 생각해봐
     */
    private void simulationSlowService() {
        try {
            System.out.println("simulationSlowService called");
            long time = 3000L;
            Thread.sleep(time);
        } catch(Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 키값 초기화
     */
    @CacheEvict(key = "#name", value = "tony")
    public String memberRemove(String name) {
        System.out.println("MemberService::memberOne => " + name);
        return "memberRemove";
    }

    /**
     * 매번 메서드 호출
     */
    @CachePut(key = "#name", value = "tony")
    public void memberRemember(String name) {
        System.out.println("MemberService::memberRemember");
    }
}
