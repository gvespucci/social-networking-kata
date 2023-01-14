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
package io.gvespucci.kata.social_networking.domain.command;

import java.io.PrintStream;
import java.time.LocalTime;

import io.gvespucci.kata.social_networking.domain.following.FollowingRepository;
import io.gvespucci.kata.social_networking.domain.message.MessageRepository;

public class CommandDispatcher {

	private final TextBasedCommand postCommand;

	public CommandDispatcher(
			MessageRepository messageRepository,
			FollowingRepository followingRepository,
			PrintStream printStream)
	{
		postCommand = new PostCommand(messageRepository);
		final TextBasedCommand followCommand = new FollowCommand(followingRepository);
		final TextBasedCommand wallCommand = new WallCommand(messageRepository, followingRepository, printStream);
		final TextBasedCommand readCommand = new ReadCommand(messageRepository, printStream);

		postCommand.addNext(followCommand);
		followCommand.addNext(wallCommand);
		wallCommand.addNext(readCommand);
		readCommand.addNext(TextBasedCommand.NULL);
	}

	public void execute(String commandText, LocalTime submissionTime) {
		postCommand.execute(commandText, submissionTime);
	}

}
