# fortress-saml-sample REGISTER-SSOCIRCLE

 This document describes the steps to register for a new account and load your service provider metadata at ssocircle.com.  This websute will act as the *Identity Provider* **(IdP)** with your fortress-saml-demo *Service Provider* **(SP)**

-------------------------------------------------------------------------------
## Prerequisites
 Completion of the steps under [SPRING-SECURITY-SAML2-SAMPLE.md](SPRING-SECURITY-SAML2-SAMPLE.md) document.

-------------------------------------------------------------------------------

## How to enable a new account on SSOCircle.com

#### 1. Goto website:

 [http://www.ssocircle.com](http://www.ssocircle.com)

#### 2. Click on **Signin/Register**->**Register** menu option.

#### 3. Fill in the info in the web form:

 ![SSO Circle Registration page](src/main/javadoc/doc-files/SSO-Circle-Registration.png "Registration Page")

 ```
 User Name [a-zA-Z.-]:
 this is the userid you will use to log into the SSO Circle Identity Provider (IdP)

 Required Field Password - at least 8 characters:
 this is the password you will use to log into the IdP

 Required Field First Name:
 enter anything

 Required Field Last Name:
 enter: sam*

 this field maps to a fortress userId.  For now enter 'sam*' who will be allowed full access to fortress-saml-sample web app.

 Required Field Full Name:
 anything

 Required Field Email Address:
 this email address must be valid.  it is needed for registration confirmation later.
 ```
 **Remember**: the values entered into **User Name**, **Password** and **Last Name**.

 **Note**: the *Last Name* attribute at ssocircle.com website will be used to map to a fortress *userId*, and will be sent inside the saml assertion.  Use the sam* user who will be allowed full access to fortress-saml-sample web app.

#### 4. complete the registration via confirmation message sent to email address entered above.

#### 5. login into SSOCircle.com IdP

 Enter the **User Name** and **Password** from above.

 ![SSO Circle Login page](./src/main/javadoc/doc-files/SSO-Circle-Login.png "Login Page")

#### 6. Add Service Provider Metadata to SSOCircle.com

 a. click on **Manage Metadata**

 b. click on **Add new Service Provider**

 ![SSO Circle Manage Metadata page](./src/main/javadoc/doc-files/SSO-Circle-Metadata.png "Manager Metadata")

 c. Enter the FQDN of the ServiceProvider ex.: sp.cohos.de

 enter hostname for your tomcat machine

 **Use previous value**: The host-name used here must match that created during SP metadata gen step here: [SPRING-SECURITY-SAML2-SAMPLE.md](SPRING-SECURITY-SAML2-SAMPLE.md).

 d. enable the **LastName** checkbox:

 e. paste the same metadata saved from before during SP metadata gen step here: [SPRING-SECURITY-SAML2-SAMPLE.md](SPRING-SECURITY-SAML2-SAMPLE.md).

 f. click on **Submit** button

 g. wait for the confirmation message.  This could take a minute or two.
 ![SSO Circle Successful Metadata Import page](https://github.com/shawnmckinney/fortress-saml-demo/blob/master/src/main/javadoc/doc-files/SSO-Circle-Metadata-Success.png "Successful Import")

#### 7. The IdP should be ready for use.  Return to the **Prepare fortress-saml-demo package** section of the [README.md](README.md) doc to complete this demo.