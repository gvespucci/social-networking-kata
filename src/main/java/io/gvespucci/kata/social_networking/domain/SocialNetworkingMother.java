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

import java.time.LocalTime;

import io.gvespucci.kata.social_networking.domain.command.CommandDispatcher;

public class SocialNetworkingMother {

	private final CommandDispatcher commandDispatcher;

	public SocialNetworkingMother(CommandDispatcher commandDispatcher) {
		this.commandDispatcher = commandDispatcher;
	}

	public void execute(String commandText, LocalTime submissionTime) {

		this.commandDispatcher.execute(commandText, submissionTime);

	}

}
