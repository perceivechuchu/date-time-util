date-util
==============

[![Build Status](https://github.com/perceivechuchu/date-util/actions/workflows/maven.yml/badge.svg)](https://github.com/perceivechuchu/date-util/actions/workflows/maven.yml)
[![Jacoco Coverage](/.github/badges/jacoco.svg)](/.github/badges/jacoco.svg)

date-util is a library that provides comprehensive date and time utilities, addressing the challenges of date manipulation, formatting, and timezone handling

Installation
============
The artifact is available on Maven Central and can be added to the project's pom.xml:

```xml
<dependency>
    <groupId>za.co.stridepace</groupId>
    <artifactId>date-util</artifactId>
    <version>1.0.0</version>
</dependency>
```

The latest version can be found [here]()

Features & Usage
========
This library provides the following features:  

## 1. DateConverter

* **Converting a LocalDateTime object to UTC ZonedDateTime in textual format**

Usage:
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
String zonedDateTimeText = DateConverter.convertToUTCZonedDateTimeText(localDateTime, "Africa/Johannesburg");
// Output: "2023-11-10T10:00:00.254Z"
```

* **Converting a LocalDateTime object to a UTC ZonedDateTime object**

Usage:
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
ZonedDateTime zonedDateTime = DateConverter.convertToUTCZonedDateTime(localDateTime, "Africa/Johannesburg");
// Output: (ZonedDateTime object with time zone -> "Africa/Johannesburg");
```

* **Converting a LocalDateTime object to a ZonedDateTime object**

Usage:
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
ZonedDateTime zonedDateTime = DateConverter.convertToZonedDateTime(localDateTime, "Africa/Johannesburg", "US/Pacific");
// Output: (ZonedDateTime object with time zone -> "US/Pacific");
```

* **Converting a UTC ZonedDateTime object (Zulu time) to LocalDateTime in textual format**

Usage:
```
ZonedDateTime zonedDateTime = DateConverter.convertToLocalDateTimeWithZone("2023-11-10T10:00:00.254Z", "Africa/Johannesburg");
// Output: (ZonedDateTime object with zone -> "Africa/Johannesburg");
```

* **Converting LocalDate in textual format with default date formatter to a LocalDate object**

Usage:
```
LocalDate localDate = DateConverter.convertToLocalDate("2023-12-05");
// Output: (LocalDate object")
```

* **Converting LocalDate in textual format with supplied date formatter to a LocalDate object**

Usage:
```
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate localDate = DateConverter.convertToLocalDate("05/12/2023", formatter);
// Output: (LocalDate object")
```

* **Converting LocalDate in textual format with supplied date format pattern to a LocalDate object**

Usage:
```
LocalDate localDate = DateConverter.convertToLocalDate("05/12/2023", "dd/MM/yyyy");
// Output: (LocalDate object")
```

* **Converting LocalDateTime in textual format with default date formatter to a LocalDateTime object**

Usage:
```
LocalDateTime localDateTime = DateConverter.convertToLocalDateTime("2023-11-18T16:02:10");
// Output: (LocalDateTime object")
```

* **Converting LocalDateTime in textual format with supplied date formatter to a LocalDateTime object**

Usage:
```
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss");
LocalDateTime localDateTime = DateConverter.convertToLocalDateTime("18/11/2023T16:02:10", formatter);
// Output: (LocalDateTime object")
```

* **Converting LocalDateTime in textual format with supplied date format pattern to a LocalDateTime object**

Usage:
```
LocalDateTime localDateTime = DateConverter.convertToLocalDateTimeWithPattern("18/11/2023T16:02:10", "dd/MM/yyyy'T'HH:mm:ss");
// Output: (LocalDateTime object")
```

* **Converting a LocalDate object with default date formatter to LocalDate in textual format**

Usage:
```
LocalDate localDate = LocalDate.of(2023, 12, 5);
String localDateText = DateConverter.convertToLocalDateText(localDate);
// Output: "2023-12-05"
```

* **Converting a LocalDate object with supplied date formatter to LocalDate in textual format**

Usage:
```
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate localDate = LocalDate.of(2023, 12, 5);
String localDateText = DateConverter.convertToLocalDateText(localDate, formatter);
// Output: "05/12/2023"
```

* **Converting a LocalDate object with supplied date format pattern to LocalDate in textual format**

Usage:
```
LocalDate localDate = LocalDate.of(2023, 12, 5);
String localDateText = DateConverter.convertToLocalDateText(localDate, "dd/MM/yyyy");
// Output: "05/12/2023"
```

* **Converting a LocalDateTime object with default date formatter to LocalDateTime in textual format**

Usage:
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 11, 10, 12, 0, 0, 254000000);
String localDateText = DateConverter.convertToLocalDateTimeText(localDateTime);
// Output: "2023-12-05T16:02:10"
```

* **Converting a LocalDateTime object with supplied date formatter to LocalDateTime in textual format**

Usage:
```
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss");
String localDateTimeText = DateConverter.convertToLocalDateTimeText(localDateTime, formatter);
// Output: "2023-12-05T16:02:10"
```

* **Converting a LocalDateTime object with supplied date format pattern to LocalDateTime in textual format**

Usage:
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
String localDateTimeText = DateConverter.convertToLocalDateTimeText(localDateTime, "dd/MM/yyyy'T'HH:mm:ss");
// Output: "2023-12-05T16:02:10"
```

* **Converting a Timestamp object to a LocalDateTime object**

Usage:
```
Timestamp timestampObject = ...
LocalDateTime localDateTime = DateConverter.convertToLocalDateTime(timestampObject);
// Output: (LocalDateTime object)
```

* **Converting a LocalDateTime object to a Timestamp object**

Usage:
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
Timestamp timestamp = DateConverter.convertToTimestamp(localDateTime);
// Output: (Timestamp object)
```

* **Converting LocalDateTime in textual format to a Timestamp object**

Usage:
```
Timestamp timestamp = DateConverter.convertToTimestamp("2023-12-05T16:02:10");
// Output: (Timestamp object)
```

* **Converting an Epoch time value in milliseconds with default zone (UTC) to a LocalDateTime object**

Usage:
```
LocalDateTime localDateTime = DateConverter.convertEpochMillisToLocalDateTime(1704124883000L);
// Output: (LocalDateTime object)
```

* **Converting an Epoch time value in milliseconds with supplied zone id to a LocalDateTime object**

Usage:
```
LocalDateTime localDateTime = DateConverter.convertEpochMillisToLocalDateTime(1704124883000L, "Africa/Johannesburg");
// Output: (LocalDateTime object)
```

* **Converting a LocalDateTime object to Epoch time value in milliseconds**

Usage:
```
LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 5, 16, 2, 10);
long epochMillis = DateConverter.convertLocalDateTimeToEpochTimeMillis(localDateTime, "Africa/Johannesburg");
// Output: 1704124883000L
```

## 2. TimeZoneUtil

* **Gets all available time zones**

Usage:
```
List<TimeZoneDetail> timeZoneDetails = TimeZoneUtil.getAllTimeZones();
// Output: (List of TimeZoneDetail objects)
```

* **Checks if a time zone id is valid**

Usage:
```
boolean valid = TimeZoneUtil.isValidTimeZoneId("Africa/Johannesburg");
// Output: true
```

* **Gets the abbreviation for a time zone**

Usage:
```
String abbreviation = TimeZoneUtil.getTimeZoneAbbreviation("Africa/Johannesburg");
// Output: "SAST"
```

* **Search time zones by the offset text e.g. search by "+02:00"**

Usage:
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

Licence
=======
The repository code is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT).

Author
======
Perceive Chuchu [perceivechuchu@gmail.com](mailto:perceivechuchu@gmail.com) with :green_heart:

Feedback
========
Please [leave your feedback](https://github.com/perceivechuchu/date-util/issues) if you have noticed any issue or have a feature request.

Contributions and Support
=========================
If you want to create a new feature, though not compulsory, it will be helpful to reach out to me first before proceeding.

This avoids a scenario where one would submit a PR for an issue that someone else is working on already.

