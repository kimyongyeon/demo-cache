package com.example.democache;

public class MemberDTO {
    Long count = 0L;

    MemberDTO(Long id) {
        this.count = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
