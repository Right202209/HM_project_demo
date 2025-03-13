package org.qqhru.hmpt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private String id;
    private String username;
    private String name;

    private String gender;
    private String image;
    private String job;

    private LocalDateTime entrydate;
    private String deptId;
    private LocalDateTime createTime;

    private LocalDateTime updateTime;
    private String password;

}
