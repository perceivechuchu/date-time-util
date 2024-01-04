package io.github.perceivechuchu.datetimeutil.commons.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Perceive Chuchu
 */
@ExtendWith(MockitoExtension.class)
class ValidationUtilTests {

	@Test
	void rejectEmpty_ProcessesSuccessfully_WhenBothValueAndMessageAreNotEmpty() {
		assertDoesNotThrow(() -> ValidationUtil.rejectEmpty("some value", "Value is empty"));
	}

	@Test
	void rejectEmpty_ProcessSuccessfully_WhenValueIsValidAndMessageIsNull() {
		assertDoesNotThrow(() -> ValidationUtil.rejectEmpty("some value", null));
	}

	@Test
	void rejectEmpty_ProcessesSuccessfully_WhenValueIsValidLocalDate() {
		LocalDate localDate = LocalDate.of(2023, 12, 5);
		assertDoesNotThrow(() -> ValidationUtil.rejectEmpty(localDate, "Value is empty"));
	}

	@Test
	void rejectEmpty_ThrowIllegalArgumentException_WhenValueIsEmpty() {
		IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> ValidationUtil.rejectEmpty("", "Value is empty"));
		assertNotNull(illegalArgumentException);
	}

	@Test
	void rejectEmpty_ThrowIllegalArgumentException_WhenValueIsEmptySpace() {
		IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> ValidationUtil.rejectEmpty(" ", "Value is empty"));
		assertNotNull(illegalArgumentException);
	}

}
