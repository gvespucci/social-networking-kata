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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.gvespucci.kata.social_networking.domain.following.FollowingRepository;
import io.gvespucci.kata.social_networking.domain.message.Message;
import io.gvespucci.kata.social_networking.domain.message.MessageRepository;

class WallCommand extends TextBasedCommand {

	private static final String WALL_COMMAND_IDENTIFIER = " wall";

	private final MessageRepository messageRepository;
	private final PrintStream printStream;
	private final FollowingRepository followingRepository;

	WallCommand(
			MessageRepository messageRepository,
			FollowingRepository followingRepository,
			PrintStream printStream)
	{
		this.messageRepository = messageRepository;
		this.followingRepository = followingRepository;
		this.printStream = printStream;
	}

	@Override
	boolean canHandle(String textCommand) {
		return textCommand.contains(WALL_COMMAND_IDENTIFIER);
	}

	@Override
	void handle(String textCommand, LocalTime submissionTime) {
		final String[] splitCommand = textCommand.split(WALL_COMMAND_IDENTIFIER);
		final String username = splitCommand[0];

		final List<Message> wall = new ArrayList<>();

		this.messageRepository
		.findBy(username)
		.stream()
		.forEach(message -> wall.add(message));

		this.followingRepository
		.findBy(username)
		.stream()
		.forEach(following ->
			wall.addAll(this.messageRepository.findBy(following.followee()))
		);

		wall
		.stream()
		.sorted(Comparator.comparing(Message::submissionTime).reversed())
		.forEach(message -> message.printTo(this.printStream, submissionTime))
		;
	}

}
