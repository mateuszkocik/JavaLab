package uj.java.pwj2020.delegations;

import static java.util.concurrent.TimeUnit.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Calc{

    BigDecimal calculate(String start, String end, BigDecimal dailyRate){
        ZonedDateTime startZDT = getZonedDateTime(start);
        ZonedDateTime endZDT = getZonedDateTime(end);

        if(endZDT.isAfter(startZDT)){
            var map = getDaysHoursMinutes(startZDT,endZDT);
            return calculateSalary(map,dailyRate);
        }

        return BigDecimal.ZERO.setScale(2);
    }

    private BigDecimal calculateSalary(Map<TimeUnit, Long> map, BigDecimal dailyRate){
        BigDecimal sum = BigDecimal.ZERO;
        sum = sum.add(dailyRate.multiply(new BigDecimal(map.get(DAYS))));
        long hours = map.get(HOURS);
        if(hours > 12)
            sum = sum.add(dailyRate);
        else if(hours > 8)
            sum = sum.add(dailyRate.multiply(new BigDecimal("0.5")));
        else if(hours > 0 || hours == 0 && map.get(MINUTES) > 0)
            sum = sum.add(dailyRate.divide(new BigDecimal(3),2, RoundingMode.HALF_UP));

        return sum.setScale(2,RoundingMode.HALF_UP);
    }

    private Map<TimeUnit,Long> getDaysHoursMinutes(ZonedDateTime startZDT, ZonedDateTime endZDT){
        var map = new HashMap<TimeUnit,Long>();
        Duration duration = Duration.between(startZDT, endZDT);

        map.put(DAYS, duration.toDays());
        duration = duration.minusDays(map.get(DAYS));

        map.put(HOURS, duration.toHours());
        duration = duration.minusHours(map.get(HOURS));

        map.put(MINUTES, duration.toMinutes());

        return map;
    }

    private ZonedDateTime getZonedDateTime(String start){
        var valList = start.split(" ");
        LocalDate localDate = LocalDate.parse(valList[0]);
        LocalTime localTime = LocalTime.parse(valList[1]);
        ZoneId zoneId = ZoneId.of(valList[2]);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        return localDateTime.atZone(zoneId);
    }

}
