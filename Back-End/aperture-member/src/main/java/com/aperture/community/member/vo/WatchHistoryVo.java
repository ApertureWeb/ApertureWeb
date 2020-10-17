package com.aperture.community.member.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Auther: JayV
 * @Date: 2020-10-16 17:08
 * @Description:
 */
@Data
public class WatchHistoryVo {

    /**
     * 观看历史id
     */
    private Long id;
    /**
     * 看了多少分钟
     */
    private Integer watchMinutes;
    /**
     * 看到第几集
     */
    private Integer watchEpisode;

}