/*
    Copyright 2011, Strategic Gains, Inc.

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package com.strategicgains.oauthexpress;

import org.scribe.builder.api.TwitterApi;

/**
 * Uses the Twitter OAuth API to login and authenticate requests.
 * 
 * @author toddf
 * @since Mar 8, 2011
 */
public class TwitterAuthenticator
extends OAuthAuthenticator
{
	private static final String AUTHENTICATION_URL = "http://api.twitter.com/1/account/verify_credentials.json";
	private static final String END_SESSION_URL = "http://api.twitter.com/version/account/end_session.json";

	/**
	 * @param apiKey
	 * @param apiSecret
	 * @param callbackUrl
	 */
	public TwitterAuthenticator(String apiKey, String apiSecret, String callbackUrl)
	{
		super(TwitterApi.class, apiKey, apiSecret, callbackUrl, AUTHENTICATION_URL, END_SESSION_URL);
	}
}
