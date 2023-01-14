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

import java.time.LocalTime;

import io.gvespucci.kata.social_networking.domain.message.MessageRepository;
import io.gvespucci.kata.social_networking.domain.message.TextMessage;

class PostCommand extends TextBasedCommand {

	private static final String POST_COMMAND_IDENTIFIER = " -> ";

	private final MessageRepository messageRepository;

	PostCommand(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@Override
	boolean canHandle(String textCommand) {
		return textCommand.contains(POST_COMMAND_IDENTIFIER);
	}

	@Override
	void handle(String textCommand, LocalTime submissionTime) {
		final String[] splitCommand = textCommand.split(POST_COMMAND_IDENTIFIER);
		final String username = splitCommand[0];
		final String messageText = splitCommand[1];
		this.messageRepository.add(new TextMessage(username, messageText, submissionTime));
	}


}
