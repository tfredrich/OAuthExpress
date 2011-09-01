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
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * @author toddf
 * @since Dec 15, 2010
 */
public abstract class OAuthAuthenticator
implements Authenticator
{
	private OAuthService oauth;
	private String authenticationUrl;
	private String logoutUrl;

	public OAuthAuthenticator(Class<? extends Api> provider,
		String apiKey,
		String apiSecret,
		String callbackUrl,
		String authenticationUrl,
		String logoutUrl
	)
	{
		oauth = new ServiceBuilder()
	        .provider(provider)
	        .apiKey(apiKey)
	        .apiSecret(apiSecret)
	        .callback(callbackUrl)
	        .build();
		this.authenticationUrl = authenticationUrl;
		this.logoutUrl = logoutUrl;
	}

	public Token getRequestToken()
	{
		return oauth.getRequestToken();
	}

	public String getAuthorizationUrl(Token requestToken)
	{
		return oauth.getAuthorizationUrl(requestToken);
	}
	
	public String getSigninUrl(Token requestToken)
	{
		return getAuthorizationUrl(requestToken);
	}

    @Override
    public Token getAccessToken(Token requestToken, String verification)
    {
		Verifier verifier = new Verifier(verification);
		return oauth.getAccessToken(requestToken, verifier);
    }

    @Override
    public boolean isAuthenticated(Token accessToken)
    {
		OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, authenticationUrl);
		oauth.signRequest(accessToken, oauthRequest);
		Response oauthResponse = oauthRequest.send();
		return (oauthResponse.getCode() == 200);
    }
    
    @Override
    public void endSession(Token accessToken)
    {
		OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, logoutUrl);
		oauth.signRequest(accessToken, oauthRequest);
		oauthRequest.send();
    }
}
