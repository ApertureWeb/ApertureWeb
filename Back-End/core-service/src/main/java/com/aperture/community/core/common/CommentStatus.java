package com.aperture.community.core.common;

public enum CommentStatus {

    /**
     * 正常状态
     * */
    NORMAL(0),

    /**
     * 折叠状态，此状态表示目标评论被折叠，这将导致此评论和对其的回复不可见，必须要用户手动展开才可视
     * 折叠状态权限说明：
     * 文章/视频作者可以对评论和回复进行折叠
     * 评论作者可以对回复进行折叠
     *
     * 注意：折叠状态不同于删除，删除将由自治审核会和官方进行
     */
    FLOD(1),

    /**
     * 表明此评论进入审核状态，将通过审核会进行裁决，裁决成功即删除，失败则回复到正常状态（NORMAL）
     * */
    REVIEW(2);


    Integer value;

    CommentStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
