package com.example.server.domain;

import lombok.Data;

/**
 * @author pangjunming
 * @Description:
 * @date 2020/9/28
 */
@Data
public class FlowerVender {
    private Long id;
    private String venderId;
    private String venderName;
    private String addess;
    private String phone;
    private String source;
    private String city;
}
