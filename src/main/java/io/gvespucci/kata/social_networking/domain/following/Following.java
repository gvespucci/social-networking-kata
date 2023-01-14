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

public class Following {

	private final String followerName;
	private final String followeeName;

	public Following(String followerName, String followeeName) {
		this.followerName = followerName;
		this.followeeName = followeeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.followeeName == null ? 0 : this.followeeName.hashCode());
		result = prime * result + (this.followerName == null ? 0 : this.followerName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Following)) {
			return false;
		}
		final Following other = (Following) obj;
		if (this.followeeName == null) {
			if (other.followeeName != null) {
				return false;
			}
		} else if (!this.followeeName.equals(other.followeeName)) {
			return false;
		}
		if (this.followerName == null) {
			if (other.followerName != null) {
				return false;
			}
		} else if (!this.followerName.equals(other.followerName)) {
			return false;
		}
		return true;
	}

	public String follower() {
		return this.followerName;
	}

	public String followee() {
		return this.followeeName;
	}

	@Override
	public String toString() {
		return this.followerName + " follows " + this.followeeName;
	}

}
