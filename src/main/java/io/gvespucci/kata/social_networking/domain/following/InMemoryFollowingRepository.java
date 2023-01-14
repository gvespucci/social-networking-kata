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
package io.gvespucci.kata.social_networking.domain.following;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemoryFollowingRepository implements FollowingRepository {

	private final List<Following> followings = new ArrayList<>();

	@Override
	public void add(Following following) {
		this.followings.add(following);
	}

	@Override
	public Boolean exists(Following theGivenFollowing) {
		return
			this.followings
			.stream()
			.filter(aFollowing -> aFollowing.equals(theGivenFollowing))
			.findAny().isPresent();
	}

	@Override
	public List<Following> findBy(String username) {
		return
			this.followings
			.stream()
			.filter(onlyFollowingsWhoseFollowerIs(username))
			.collect(Collectors.toList());
	}

	private Predicate<? super Following> onlyFollowingsWhoseFollowerIs(String username) {
		return following -> following.follower().equals(username);
	}

}
