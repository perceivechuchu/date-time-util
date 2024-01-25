package io.github.perceivechuchu.datetimeutil.validator;

import io.github.perceivechuchu.datetimeutil.exception.DateTimeValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Perceive Chuchu
 */
@ExtendWith(MockitoExtension.class)
class DateTimeValidatorTests {

    @Test
    void isValid_ReturnTrue_WhenFirstDateTimeTextForISO8601FormatIsValid() {
        assertTrue(DateTimeValidator.isValid("2024-03-22T05:06:07Z"));
    }

    @Test
    void isValid_ReturnTrue_WhenSecondDateTimeTextForISO8601FormatIsValid() {
        assertTrue(DateTimeValidator.isValid("2024-03-22 05:06:07Z"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForBelarussianFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("22.3.2024 5.06.07"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForDanishFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("22-03-2024 05:06:07"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForGermanyFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("22.03.2024 05:06:07"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForEnglishCanadaFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("Monday, March 22, 1999 5:06:07 o'clock AM CET"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForUnitedKingdomFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("22 March 2024 05:06:07 CET"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForUnitedStatesFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("Mon, 22 Mar 1999 05:06:07 +0100"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForSpanishFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("22.3.24 5:06"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForEstonianFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("22.03.2024 5:06:07"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForFinnishFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("22.3.2024 5:06"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForFrenchCanadaFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("March 22, 1999 5:06:07 CET AM"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForFrenchFranceFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("3/22/24 05:06:07"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForIrishFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("24/03/22 05:06"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForCroatianFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("2024.03.22 05:06:07"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForHungarianFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("2024.03.22. 5:06:07"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForIcelandicFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("22.3.2024 05:06:07"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForItalianFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("22-mar-2024 5.06.07"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForHebrewFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("05:06:07 22/03/2024"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForJapaneseFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("2024-3-22 5:06"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForKoreanFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("2024. 3. 22"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForLithuanianFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("99.3.22 05.06"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForLatvianFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("2024.22.3 05:06:07"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForMacedonianFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("22.3.2024 05:06:"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForDutchFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("22-3-99 5:06"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForRussianFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("22.03.1999 5:06:07"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForAlbanianFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("1999-03-22 5.06.AM"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForSerbianFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("22.3.99."));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForVietnameseFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("05:06:07 22-03-2024"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextForChineseFormatIsValid() {
        assertTrue(DateTimeValidator.isValid("1999-3-22 5:06:07"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextAndLocaleForGermanyFormatAreValid() {
        assertTrue(DateTimeValidator.isValid("Montag, 22. März 1999 05:06 Uhr MEZ", Locale.GERMANY));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextAndLocaleForFrenchCanadaFormatAreValid() {
        assertTrue(DateTimeValidator.isValid("lundi 22 mars 1999 5 h 06 CET", Locale.CANADA_FRENCH));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextAndLocaleForFranceFormatAreValid() {
        assertTrue(DateTimeValidator.isValid("22 mars 1999 05:06:07 CET", Locale.FRENCH));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextAndLocaleForItalianFormatAreValid() {
        assertTrue(DateTimeValidator.isValid("lunedì 22 marzo 1999 5.06.07 CET", Locale.ITALIAN));
    }

    @Test
    void isValid_ThrowParseException_WhenDateTimeTextForItalianFormatIsInvalid() {
        assertFalse(DateTimeValidator.isValid("invalid_date", Locale.ITALIAN));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextAndLocaleForChineseFormatAreValid() {
        assertTrue(DateTimeValidator.isValid("1999年3月22日 星期一 上午05时06分07秒 CET", Locale.CHINESE));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextAndDateFormatPatternForISO8601FormatAreValid() {
        assertTrue(DateTimeValidator.isValid("2024-03-22T05:06:07Z", "yyyy-MM-dd'T'HH:mm:ssX"));
    }

    @Test
    void isValid_ReturnTrue_WhenDateTimeTextAndLocaleDateFormatPatternForFranceFormatAreValid() {
        assertTrue(DateTimeValidator.isValid("lundi 22 mars 1999 05 h 06 CET", Locale.FRENCH, "EEEE d MMMM yyyy HH' h 'mm z"));
    }

    @Test
    void isValid_ThrowParseException_WhenDateTimeTextForFranceFormatIsInvalid() {
        assertFalse(DateTimeValidator.isValid("invalid_date", Locale.FRENCH, "EEEE d MMMM yyyy HH' h 'mm z"));
    }

    @Test
    void getFileFromResourceAsStream_ThrowDateTimeValidationException_WhenFileLocationIsInvalid() {
        // Set an invalid file location
        DateTimeValidator.fileLocation = "invalid_file_location";
        DateTimeValidationException dateValidationException = assertThrows(DateTimeValidationException.class, DateTimeValidator::getFileFromResourceAsStream);
        assertNotNull(dateValidationException);
        assertNotNull(dateValidationException.getMessage());
        // Reset file location after running the unit test
        DateTimeValidator.fileLocation = "datetime/formats/all_formats.txt";
    }

    @Test
    void getFileFromResourceAsStream_LoadDateFormats_WhenFormatsListIsNullOrEmpty() {
        DateTimeValidator.dateFormats = new ArrayList<>();
        DateTimeValidator.loadDateFormats();
        assertEquals(225, DateTimeValidator.dateFormats.size());
    }

}
