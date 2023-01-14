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
package io.gvespucci.kata.social_networking;

import java.io.Console;
import java.time.LocalTime;

import io.gvespucci.kata.social_networking.domain.SocialNetworkingMother;
import io.gvespucci.kata.social_networking.domain.command.CommandDispatcher;
import io.gvespucci.kata.social_networking.domain.following.InMemoryFollowingRepository;
import io.gvespucci.kata.social_networking.domain.message.InMemoryMessageRepository;

public class Runner {

	public static void main(String[] args) {
		System.out.println("--- Social Network ---");

		final SocialNetworkingMother socialNetwork =
				new SocialNetworkingMother(
						new CommandDispatcher(
								new InMemoryMessageRepository(),
								new InMemoryFollowingRepository(),
								System.out
								)
						);

		final Console console = System.console();
		if (console == null) {
			System.out.println("No console: non-interactive mode!");
			System.exit(0);
		}

		String commandText;

		while ((commandText = console.readLine("> ")) != null) {
			socialNetwork.execute(commandText, LocalTime.now());
		}
		System.exit(0);
	}

}
