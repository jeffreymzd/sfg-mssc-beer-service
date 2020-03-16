package com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * Created by jeffreymzd on 3/16/20
 */
@Component
public class DateMapper {

    public OffsetDateTime timestampToOffsetDateTime(Timestamp ts) {

        if (null == ts) return null;

        return OffsetDateTime
                .ofInstant(Instant.ofEpochMilli(ts.getTime()),
                        ZoneId.systemDefault());
    }

    public Timestamp offSetDateTimeToTimestamp(OffsetDateTime dt) {

        if (null == dt) return null;

        return Timestamp
                .valueOf(dt.atZoneSameInstant(ZoneId.systemDefault())
                        .toLocalDateTime());
    }
}
