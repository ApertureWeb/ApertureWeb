package com.aperture.community.core.manager;

import com.aperture.community.common.standard.identity.ServiceIdentity;
import com.aperture.community.core.module.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserManager {

    private RestTemplate restTemplate;

    public UserManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto getUserDto() {
        return restTemplate.getForObject(ServiceIdentity.CONTENT_SERVICE.getValue(), UserDto.class);
    }

}
