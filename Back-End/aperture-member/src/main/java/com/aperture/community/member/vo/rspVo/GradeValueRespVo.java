package com.aperture.community.member.vo.rspVo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Auther: JayV
 * @Date: 2020-10-16 16:16
 * @Description:
 */
@Data
public class GradeValueRespVo {

    /**
     * 当前等级
     */
    private Integer gradeLevel;
    /**
     * 当前等级升级需要的经验
     */
    private Integer growthValue;
    /**
     * 签到增加的经验
     */
    private Integer signInGrowthValue;
    /**
     * 发布作品增加的经验
     */
    private Integer publishGrowthValue;
    /**
     * 评论增加的经验
     */
    private Integer commentGrowthValue;
    /**
     * 最高等级
     */
    private Integer topGrade;

}