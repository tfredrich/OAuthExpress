/*
    Copyright 2010, Strategic Gains, Inc.

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

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 * @author toddf
 * @since Dec 15, 2010
 */
public class OAuthAuthenticator
implements Authenticator
{
	private OAuthService oauth;
	
	public OAuthAuthenticator(Class<? extends Api> provider, String apiKey, String apiSecret, String callbackUrl)
	{
		oauth = new ServiceBuilder()
	        .provider(provider)
	        .apiKey(apiKey)
	        .apiSecret(apiSecret)
	        .callback(callbackUrl)
	        .build();		
	}

	@Override
	public boolean isAuthenticated(String token)
	{
		return false;
	}
	
	public String getAuthToken()
	{
		Token requestToken = oauth.getRequestToken();
		return requestToken.getToken();
	}

	public static void main(String[] args)
	{
		OAuthAuthenticator service = new OAuthAuthenticator(TwitterApi.class,
			"Sk5be32VnALj3eTBUxgGqw",
			"Bqm5qqMYdg52FdCR1Zh4gSy1iJhHRtXkscNFMr8kTI",
//			"https://twitter.com/oauth/authorize?oauth_token=",
			"http://www.urlclix.com/oauth/twitter/authenticated");
		String authToken = service.getAuthToken();
		System.out.println("https://twitter.com/oauth/authorize?oauth_token=" + authToken);
	}
}
