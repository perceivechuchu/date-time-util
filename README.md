date-time-util
==============

[![Build Status](https://github.com/perceivechuchu/date-time-util/actions/workflows/maven.yml/badge.svg)](https://github.com/perceivechuchu/date-time-util/actions/workflows/maven.yml)
[![Jacoco Coverage](/.github/badges/jacoco.svg)](/.github/badges/jacoco.svg)

date-time-util is a library that provides comprehensive date and time utilities, addressing the challenges of date and time conversions, validations, formatting, and timezone handling

Installation
============
The artifact is available on Maven Central and can be added to the project's pom.xml:

```xml
<dependency>
    <groupId>io.github.perceivechuchu</groupId>
    <artifactId>date-time-util</artifactId>
    <version>2.1.0</version>
</dependency>
```

The latest version can be found [here](https://central.sonatype.com/artifact/io.github.perceivechuchu/date-time-util)

Features & Usage
========
This library provides the following features:  

## 1. DateTimeConverter 
<br />

* **Converting a LocalDateTime object to UTC ZonedDateTime in textual format**
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
String zonedDateTimeText = DateTimeConverter.convertToUTCZonedDateTimeText(localDateTime, "Africa/Johannesburg");
// Output: "2023-11-10T10:00:00.254Z"
```
<br />

* **Converting a LocalDateTime object to a UTC ZonedDateTime object**
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
ZonedDateTime zonedDateTime = DateTimeConverter.convertToUTCZonedDateTime(localDateTime, "Africa/Johannesburg");
// Output: (ZonedDateTime object with time zone -> "Africa/Johannesburg");
```
<br />

* **Converting a LocalDateTime object to a ZonedDateTime object**
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
ZonedDateTime zonedDateTime = DateTimeConverter.convertToZonedDateTime(localDateTime, "Africa/Johannesburg", "US/Pacific");
// Output: (ZonedDateTime object with time zone -> "US/Pacific");
```
<br />

* **Converting a UTC ZonedDateTime object (Zulu time) to LocalDateTime in textual format**
```
ZonedDateTime zonedDateTime = DateTimeConverter.convertToLocalDateTimeWithZone("2023-11-10T10:00:00.254Z", "Africa/Johannesburg");
// Output: (ZonedDateTime object with zone -> "Africa/Johannesburg");
```
<br />

* **Converting LocalDate in textual format with default date formatter to a LocalDate object**
```
LocalDate localDate = DateTimeConverter.convertToLocalDate("2023-12-05");
// Output: (LocalDate object")
```
<br />

* **Converting LocalDate in textual format with supplied date formatter to a LocalDate object**
```
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate localDate = DateTimeConverter.convertToLocalDate("05/12/2023", formatter);
// Output: (LocalDate object")
```
<br />

* **Converting LocalDate in textual format with supplied date format pattern to a LocalDate object**
```
LocalDate localDate = DateTimeConverter.convertToLocalDate("05/12/2023", "dd/MM/yyyy");
// Output: (LocalDate object")
```
<br />

* **Converting LocalDateTime in textual format with default date formatter to a LocalDateTime object**
```
LocalDateTime localDateTime = DateTimeConverter.convertToLocalDateTime("2023-11-18T16:02:10");
// Output: (LocalDateTime object")
```
<br />

* **Converting LocalDateTime in textual format with supplied date formatter to a LocalDateTime object**
```
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss");
LocalDateTime localDateTime = DateTimeConverter.convertToLocalDateTime("18/11/2023T16:02:10", formatter);
// Output: (LocalDateTime object")
```
<br />

* **Converting LocalDateTime in textual format with supplied date format pattern to a LocalDateTime object**
```
LocalDateTime localDateTime = DateTimeConverter.convertToLocalDateTimeWithPattern("18/11/2023T16:02:10", "dd/MM/yyyy'T'HH:mm:ss");
// Output: (LocalDateTime object")
```
<br />

* **Converting a LocalDate object with default date formatter to LocalDate in textual format**
```
LocalDate localDate = LocalDate.of(2023, 12, 5);
String localDateText = DateTimeConverter.convertToLocalDateText(localDate);
// Output: "2023-12-05"
```
<br />

* **Converting a LocalDate object with supplied date formatter to LocalDate in textual format**
```
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate localDate = LocalDate.of(2023, 12, 5);
String localDateText = DateTimeConverter.convertToLocalDateText(localDate, formatter);
// Output: "05/12/2023"
```
<br />

* **Converting a LocalDate object with supplied date format pattern to LocalDate in textual format**
```
LocalDate localDate = LocalDate.of(2023, 12, 5);
String localDateText = DateTimeConverter.convertToLocalDateText(localDate, "dd/MM/yyyy");
// Output: "05/12/2023"
```
<br />

* **Converting a LocalDateTime object with default date formatter to LocalDateTime in textual format**
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
String localDateText = DateTimeConverter.convertToLocalDateTimeText(localDateTime);
// Output: "2023-12-05T16:02:10"
```
<br />

* **Converting a LocalDateTime object with supplied date formatter to LocalDateTime in textual format**
```
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss");
String localDateTimeText = DateTimeConverter.convertToLocalDateTimeText(localDateTime, formatter);
// Output: "2023-12-05T16:02:10"
```
<br />

* **Converting a LocalDateTime object with supplied date format pattern to LocalDateTime in textual format**
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
String localDateTimeText = DateTimeConverter.convertToLocalDateTimeText(localDateTime, "dd/MM/yyyy'T'HH:mm:ss");
// Output: "2023-12-05T16:02:10"
```
<br />

* **Converting a Timestamp object to a LocalDateTime object**
```
Timestamp timestampObject = ...
LocalDateTime localDateTime = DateTimeConverter.convertToLocalDateTime(timestampObject);
// Output: (LocalDateTime object)
```
<br />

* **Converting a LocalDateTime object to a Timestamp object**
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
Timestamp timestamp = DateTimeConverter.convertToTimestamp(localDateTime);
// Output: (Timestamp object)
```
<br />

* **Converting LocalDateTime in textual format to a Timestamp object**
```
Timestamp timestamp = DateTimeConverter.convertToTimestamp("2023-12-05T16:02:10");
// Output: (Timestamp object)
```
<br />

* **Converting an Epoch time value in milliseconds with default zone (UTC) to a LocalDateTime object**
```
LocalDateTime localDateTime = DateTimeConverter.convertEpochMillisToLocalDateTime(1704124883000L);
// Output: (LocalDateTime object)
```
<br />

* **Converting an Epoch time value in milliseconds with supplied zone id to a LocalDateTime object**
```
LocalDateTime localDateTime = DateTimeConverter.convertEpochMillisToLocalDateTime(1704124883000L, "Africa/Johannesburg");
// Output: (LocalDateTime object)
```
<br />

* **Converting a LocalDateTime object to Epoch time value in milliseconds**
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
long epochMillis = DateTimeConverter.convertLocalDateTimeToEpochTimeMillis(localDateTime, "Africa/Johannesburg");
// Output: 1704124883000L
```
<br />

* **Converting a java.util.Date object to a LocalDateTime object**
```
Date date = new Date(1704124883000L);
LocalDateTime localDateTime = DateTimeConverter.convertToLocalDateTime(date);
// Output: (LocalDateTime object)
```
<br />

* **Converting a java.util.Date object with supplied time zone id to a LocalDateTime object**
```
Date date = new Date(1704124883000L);
LocalDateTime localDateTime = DateTimeConverter.convertToLocalDateTime(date, "Asia/Tokyo)
// Output: (LocalDateTime object)
```

* **Converting a java.util.Calendar object with supplied time zone id to a LocalDateTime object**
```
Calendar calendar = Calendar.getInstance();
calendar.set(Calendar.YEAR, 2024);
calendar.set(Calendar.MONTH, Calendar.JANUARY);
calendar.set(Calendar.DAY_OF_MONTH, 1);
calendar.set(Calendar.HOUR_OF_DAY, 18);
calendar.set(Calendar.MINUTE, 1);
calendar.set(Calendar.SECOND, 23);
calendar.set(Calendar.MILLISECOND, 0);

LocalDateTime actualLocalDateTime = DateTimeConverter.convertToLocalDateTime(calendar, "Africa/Johannesburg");

// Output: (LocalDateTime object)
```

* **Converting a java.util.Date object to a ZonedDateTime object**
```
Date date = new Date(1704124883000L);
ZonedDateTime zonedDateTime = DateTimeConverter.convertToZonedDateTime(date)
// Output: (ZonedDateTime object)
```

* **Converting a java.util.Calendar object to a ZonedDateTime object**
```
Calendar calendar = Calendar.getInstance();
calendar.set(Calendar.YEAR, 2024);
calendar.set(Calendar.MONTH, Calendar.JANUARY);
calendar.set(Calendar.DAY_OF_MONTH, 1);
calendar.set(Calendar.HOUR_OF_DAY, 18);
calendar.set(Calendar.MINUTE, 1);
calendar.set(Calendar.SECOND, 23);
calendar.set(Calendar.MILLISECOND, 0);

ZonedDateTime zonedDateTime = DateTimeConverter.convertToZonedDateTime(calendar)
// Output: (ZonedDateTime object)
```

* **Converting a LocalDateTime object with supplied time zone id to a ZonedDateTime object**
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
ZonedDateTime zonedDateTime = DateTimeConverter.convertToZonedDateTime(localDateTime, "Africa/Johannesburg")
// Output: (ZonedDateTime object)
```

* **Converting a ZonedDateTime object to a Calendar object**
```
ZonedDateTime zonedDateTime = ZonedDateTime.of(2024, 1, 2, 1, 1, 23, 0, ZoneId.of("Africa/Johannesburg"));
Calendar calendar = DateTimeConverter.convertToCalendar(zonedDateTime);
// Output: (Calendar object)
```

* **Converting a LocalDateTime object with supplied time zone id to a Calendar object**
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
Calendar calendar = DateTimeConverter.convertToCalendar(localDateTime, "Africa/Johannesburg");
// Output: (Calendar object)
```

* **Converting a java.util.Date object to a java.util.Calendar object**
```
Date date = new Date(1704124883000L);
Calendar calendar = DateTimeConverter.convertToCalendar(date);
// Output: (Calendar object)
```

* **Converting a java.util.Calendar object to a java.util.Date object**
```
Calendar calendar = Calendar.getInstance();
calendar.set(Calendar.YEAR, 2024);
calendar.set(Calendar.MONTH, Calendar.JANUARY);
calendar.set(Calendar.DAY_OF_MONTH, 1);
calendar.set(Calendar.HOUR_OF_DAY, 18);
calendar.set(Calendar.MINUTE, 1);
calendar.set(Calendar.SECOND, 23);
calendar.set(Calendar.MILLISECOND, 0);

Date date = DateTimeConverter.convertToDate(calendar);
// Output: (Date object)
```

## 2. TimeZoneUtil 
<br />

* **Getting all available time zones**
```
List<TimeZoneDetail> timeZoneDetails = TimeZoneUtil.getAllTimeZones();
// Output: (List of TimeZoneDetail objects)
```
<br />

* **Checking if a time zone id is valid**
```
boolean valid = TimeZoneUtil.isValidTimeZoneId("Africa/Johannesburg");
// Output: true
```
<br />

* **Getting the abbreviation for a time zone**
```
String abbreviation = TimeZoneUtil.getTimeZoneAbbreviation("Africa/Johannesburg");
// Output: "SAST"
```
<br />

* **Searching time zones by the offset text e.g. search by "+02:00"**
```
// Get all the time zones that are 2 hours from UTC
List<TimeZoneDetail> timeZones = TimeZoneUtil.searchTimeZonesByOffsetText("+02:00")
// Output: "List of TimeZoneDetail objects"
```

<br /> TimeZoneDetails class is below, showing all the fields:

```
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeZoneDetail {

    private String id;
    private String displayName;
    private String abbreviation;
    private Integer offsetMillis;
    private String offsetText;
}
```

## 3. DateTimeValidator
<br />

* **Validates the date and time in textual format. This method uses the default locale -> "Locale.ENGLISH"**
```
boolean valid = DateTimeValidator.isValid("2024-03-22T05:06:07Z");
// Output: true
```
<br />

* **Validates the date and time in textual format with the locale**
```
boolean valid = DateTimeValidator.isValid("Montag, 22. März 1999 05:06 Uhr MEZ", Locale.GERMANY);
// Output: true
```
<br />

* **Validates the date and time in textual format with the date format pattern to validate against. This method uses the default locale -> "Locale.ENGLISH"**
```
boolean valid = DateTimeValidator.isValid("2024-03-22T05:06:07Z", "yyyy-MM-dd'T'HH:mm:ssX");
// Output: true
```
<br />

* **Validates the date and time in textual format with locale and the date format pattern to validate against**
```
boolean valid = DateTimeValidator.isValid("lundi 22 mars 1999 05 h 06 CET", Locale.FRENCH, "EEEE d MMMM yyyy HH' h 'mm z");
// Output: true
```

Licence
=======
The repository code is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT).

Author
======
Perceive Chuchu [perceivechuchu@gmail.com](mailto:perceivechuchu@gmail.com) with :green_heart:

API Reference
========
* [date-time-util Java Docs](https://javadoc.io/doc/io.github.perceivechuchu/date-time-util/latest/index.html)

Feedback
========
Please [leave your feedback](https://github.com/perceivechuchu/date-time-util/issues) if you have noticed any issue or have a feature request.

Contributions and Support
=========================
If you want to create a new feature, though not compulsory, it will be helpful to reach out to me first before proceeding.

This avoids a scenario where one would submit a PR for an issue that someone else is working on already.
