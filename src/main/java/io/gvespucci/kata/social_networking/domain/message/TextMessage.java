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
package io.gvespucci.kata.social_networking.domain.message;

import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalTime;

public class TextMessage implements Message {

	private final String text;
	private final LocalTime submissionTime;
	private final String username;

	public TextMessage(String username, String text, LocalTime submissionTime) {
		this.username = username;
		this.text = text;
		this.submissionTime = submissionTime;
	}

	@Override
	public void printTo(PrintStream out, LocalTime referenceTime) {
		final long elapsedSeconds = Duration.between(this.submissionTime, referenceTime).getSeconds();

		out.println(String.format("> %s - %s (%d %s ago)",
				this.username,
				this.text,
				this.elapsedTimeValue(elapsedSeconds),
				this.elapsedTimeUnit(elapsedSeconds)));
	}

	private long elapsedTimeValue(final long elapsedSeconds) {
		return elapsedSeconds < 60 ? elapsedSeconds : elapsedSeconds/60;
	}

	private String elapsedTimeUnit(final long elapsedSeconds) {
		if(elapsedSeconds == 1) {
			return "second";
		}
		if(elapsedSeconds == 60) {
			return "minute";
		}
		return elapsedSeconds > 60 ? "minutes" : "seconds";
	}

	@Override
	public LocalTime submissionTime() {
		return this.submissionTime;
	}

	@Override
	public String username() {
		return this.username;
	}

}
