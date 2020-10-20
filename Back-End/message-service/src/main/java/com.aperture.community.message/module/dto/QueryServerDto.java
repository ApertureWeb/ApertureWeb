package com.aperture.community.message.module.dto;

import lombok.Data;

/**
 * sample:
 * {
 * ip: "1.1.1.2",
 * servePort: 8848,
 * site: "unknown",
 * weight: 1,
 * adWeight: 0,
 * alive: false,
 * lastRefTime: 0,
 * lastRefTimeStr: null,
 * key: "1.1.1.2:8848"
 * }
 */
@Data
public class QueryServerDto {

    private String ip;
    private Integer sererPort;
    private String site;
    private Integer weight;
    private Integer adWeight;
    private Boolean alive;
    private Integer lastRefTime;
    private Integer lastRefTimeStr;
    private String key;

}
