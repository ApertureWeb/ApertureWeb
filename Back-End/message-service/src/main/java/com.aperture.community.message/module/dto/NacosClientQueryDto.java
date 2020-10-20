package com.aperture.community.message.module.dto;


import lombok.Data;

/**
 * Request Parameters
 * Name 	Type 	Required 	Description
 * serviceName 	String 	yes 	Service name
 * groupName 	String 	no 	group name
 * namespaceId 	String 	no 	ID of namespace
 * clusters 	String, splited by comma 	no 	Cluster name
 * healthyOnly 	boolean 	no, default value is false 	Return healthy instance or not
 */
@Data
public class NacosClientQueryDto {

    private String serviceName;
    private String groupName;
    private String namespaceId;
    private String clusters;
    private boolean healthyOnly;


}
