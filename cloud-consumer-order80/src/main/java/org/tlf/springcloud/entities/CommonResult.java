package org.tlf.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {
    private static final long serialVersionUID = 3149361523750900688L;

    private Integer code;
    private String message;
    private Object data;
}
