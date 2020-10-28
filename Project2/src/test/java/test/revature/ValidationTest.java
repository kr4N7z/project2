package test.revature;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.utility.Validation;

public class ValidationTest {
	private Validation validation;

	@BeforeEach
	public void setUp() {
		validation = Validation.getInstance();
	}

	@Test
	public void validEmailCheckTest() {
		assertEquals(true, validation.validEmail("coltonkrantz@krantzinc.net"));
		assertEquals(true, validation.validEmail("colton-krantz@krantzinc.net"));
		assertEquals(true, validation.validEmail("colton.krantz@krantzinc.net"));
		assertEquals(true, validation.validEmail("c.oltonkrantz@krantzinc.net"));
		assertEquals(true, validation.validEmail("coltonkrantz1@krantzinc.net"));
	}

	@Test
	public void validEmailCheckTestFailNoAtSign() {
		assertEquals(false, validation.validEmail("coltonkrantzkrantzinc.net"));
	}

	@Test
	public void validEmailCheckTestFailEnding() {
		assertEquals(false, validation.validEmail("coltonkrantz@krantzincnet"));
	}

	@Test
	public void validEmailCheckTestFailStarting() {
		assertEquals(false, validation.validEmail(".coltonkrantz@krantzincnet"));
		assertEquals(false, validation.validEmail("_coltonkrantz@krantzincnet"));

	}

	@Test
	public void validEmailCheckTestFailStartingBlank() {
		assertEquals(false, validation.validEmail("@krantzincnet"));
	}

	@Test
	public void validEmailCheckTestFailLeftMiddle() {
		assertEquals(false, validation.validEmail("coltonkrantz.@krantzincnet"));
		assertEquals(false, validation.validEmail("coltonkrantz_@krantzincnet"));

	}

	@Test
	public void validEmailCheckTestFailRightMiddle() {
		assertEquals(false, validation.validEmail("coltonkrantz@.krantzincnet"));
		assertEquals(false, validation.validEmail("coltonkrantz@_krantzincnet"));

	}

}
