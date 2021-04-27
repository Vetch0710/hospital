package com.ruoyi.project.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Vetch
 * @Description
 * @create 2021-04-22-10:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Appoint {
    private static final long serialVersionUID = 1L;


    private String time;
    private String seeTime;
    private Long messageId;
    private Long patientId;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Appoint) {
            Appoint appoint = (Appoint) obj;

            return this.getMessageId().equals(appoint.getMessageId());
        }
        return false;
    }

}