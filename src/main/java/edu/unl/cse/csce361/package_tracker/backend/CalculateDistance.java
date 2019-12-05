package edu.unl.cse.csce361.package_tracker.backend;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CalculateDistance {
    public static void main (String[] args) throws java.lang.Exception {
//        Calendar now2 = Calendar.getInstance();
//        TimeZone timeZone = now2.getTimeZone();
//        System.out.println("Current TimeZone is : " + timeZone.getDisplayName());
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(date.format(now) + " " + now.getDayOfWeek());
        //LocalDateTime dateTime = LocalDateTime.from(date.parse("2012-01-10 23:13:26"));
//        System.out.println(distance(40.817663, -96.700037, 40.830192, -96.667434, "M") + " Miles\n");
//        System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "K") + " Kilometers\n");
//        System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "N") + " Nautical Miles\n");
    }

    private static double distance (double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }
}