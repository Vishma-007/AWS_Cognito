package com.example.aws_cognito.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CognitoService {

    @Value("${aws.cognito.poolId}")
    private String userPoolId;

    @Value("${aws.cognito.clientId}")
    private String clientId;

    private final AWSCognitoIdentityProvider cognitoClient;

    public CognitoService() {
        this.cognitoClient = AWSCognitoIdentityProviderClientBuilder.defaultClient();
    }

    // Authenticate User
    public AuthenticationResultType authenticateUser(String username, String password) {
        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withUserPoolId(userPoolId)
                .withClientId(clientId)
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .addAuthParametersEntry("USERNAME", username)
                .addAuthParametersEntry("PASSWORD", password);

        AdminInitiateAuthResponse authResponse = cognitoClient.adminInitiateAuth(authRequest);
        return authResponse.getAuthenticationResult();
    }

    // Sign Up User
    public SignUpResult signUpUser(String username, String password, String email) {
        SignUpRequest signUpRequest = new SignUpRequest()
                .withUsername(username)
                .withPassword(password)
                .withClientId(clientId)
                .addUserAttributesEntry("email", email);

        return cognitoClient.signUp(signUpRequest);
    }

    // Confirm Sign-Up (after receiving confirmation code)
    public void confirmUser(String username, String confirmationCode) {
        ConfirmSignUpRequest confirmSignUpRequest = new ConfirmSignUpRequest()
                .withUsername(username)
                .withConfirmationCode(confirmationCode)
                .withClientId(clientId);

        cognitoClient.confirmSignUp(confirmSignUpRequest);
    }

    // Optionally: Add more methods for other functionality (password reset, MFA, etc.)
}

