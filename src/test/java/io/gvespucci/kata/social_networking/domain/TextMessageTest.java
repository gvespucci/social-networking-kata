/*
 * =============================================================================
 *
 *   Copyright 2017 Giorgio Vespucci - giorgio.vespucci@gmail.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * =============================================================================
 */
package io.gvespucci.kata.social_networking.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.gvespucci.kata.social_networking.domain.message.TextMessage;
import io.gvespucci.kata.social_networking.util.FakePrintStream;

public class TextMessageTest {

	private FakePrintStream printStream;
	private String text;
	private LocalTime submittedOn;

	@BeforeMethod
	public void before() {
		this.printStream = new FakePrintStream(System.out);
		this.text = "Hi there!";
		this.submittedOn = LocalTime.of(13, 40, 27);
	}

	@AfterMethod
	public void after() {
		this.printStream.reset();
	}

	@Test
	public void shouldPrintUsernameFollowedByDashAndText() {
		final TextMessage message = new TextMessage("Bob", this.text, LocalTime.now());
		message.printTo(this.printStream, LocalTime.now());
		assertThat(this.printStream.printedMessages().stream().findFirst().get()).startsWith("Bob - "+this.text);
	}

	@Test
	public void shouldPrintOneElapsedSecondAfterTheText() {
		final TextMessage message = new TextMessage("Alice", this.text, this.submittedOn);
		message.printTo(this.printStream, this.submittedOn.plusSeconds(1));
		assertThat(this.printStream.printedMessages().stream().findFirst().get()).endsWith(" (1 second ago)");
	}

	@Test
	public void shouldPrintElapsedSecondsAfterTheText() {
		final TextMessage message = new TextMessage("Alice", this.text, this.submittedOn);
		message.printTo(this.printStream, this.submittedOn.plusSeconds(24));
		assertThat(this.printStream.printedMessages().stream().findFirst().get()).endsWith(" (24 seconds ago)");
	}

	@Test
	public void shouldPrintElapsedMinutesAfterTheText() {
		final TextMessage message = new TextMessage("Charlie", this.text, this.submittedOn);
		message.printTo(this.printStream, this.submittedOn.plusMinutes(4).plusSeconds(22));
		assertThat(this.printStream.printedMessages().stream().findFirst().get()).endsWith(" (4 minutes ago)");
	}

	@Test
	public void shouldPrintOneSingleElapsedMinuteAfterTheText() {
		final TextMessage message = new TextMessage("Magda", this.text, this.submittedOn);
		message.printTo(this.printStream, this.submittedOn.plusSeconds(60));
		assertThat(this.printStream.printedMessages().stream().findFirst().get()).endsWith(" (1 minute ago)");
	}

}
