package com.xili.loinfo.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Auther: liangxin
 * @Date: 2019/11/3
 * @Description:
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    /**
     * 响应处理是否成功
     */
    private boolean success;

    /**
     * 响应处理的消息
     */
    private String message;

    /**
     * 响应处理的返回内容
     */
    private Object body;

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
