package com.aperture.community.core.module.param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UmsReplyParam {

    private Long id;

    private Long targetId;

    private Long rootId;

    private Long commentId;

    private String content;

    private Integer status;

}
