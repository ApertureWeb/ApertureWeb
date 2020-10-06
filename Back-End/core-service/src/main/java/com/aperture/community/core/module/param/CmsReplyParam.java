package com.aperture.community.core.module.param;

import lombok.Data;
import lombok.Getter;

/**
 * @author HALOXIAO
 * @since 2020-10-04 13:48
 **/
@Data
public class CmsReplyParam {

    private Long id;

    private Long targetId;

    private Long userId;

    private String content;

    private Long rootId;

    private Long replyId;

}
