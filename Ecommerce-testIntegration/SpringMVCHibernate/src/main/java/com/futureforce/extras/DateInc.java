package com.futureforce.extras;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateInc {
private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");



LocalDateTime date = LocalDateTime.now().plusDays(6);  

}
