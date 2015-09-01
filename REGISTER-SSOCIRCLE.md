# fortress-saml-sample REGISTER-SSOCIRCLE

 This document demonstrates how to register for a new account with SSOCircle.com IdP.  This website will be the *Identity Provider* **(IdP)** for fortress-saml-demo's *Service Provider* **(SP)**

-------------------------------------------------------------------------------
## Prerequisites
 Completion of the steps under [SPRING-SECURITY-SAML2-SAMPLE.md](SPRING-SECURITY-SAML2-SAMPLE.md) document.

-------------------------------------------------------------------------------

## How to enable a new account on SSOCircle.com

1. Goto website:

 [http://www.ssocircle.com](SSO Circle IdP)

2. Click on **Signin/Register**->**Register** menu option.

3. Fill in the info in the web form:

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

 note: this field maps to a fortress *userId*.  For now the *sam** user will be allowed full access to fortress-saml-sample web app.

 Required Field Full Name:
 anything

 Required Field Email Address:
 this email address must be valid.  it is needed for registration confirmation later.
 ```
 **Remember these values**: The **User Name** and **Password** will be used during testing and serve as the IdP credentials.  The **Last Name** maps to a userId field in Fortress and will be used during authorization use cases.

4. complete the registration via confirmation message sent to email address entered above.

5. login into SSOCircle.com IdP

 Enter the **User Name** and **Password** from above.

 ![SSO Circle Login page](src/main/javadoc/doc-files/SSO-Circle-Login.png "Login Page")

6. Add Service Provider Metadata to SSOCircle.com

 a. click on **Manage Metadata**

 b. click on **Add new Service Provider**
 ![SSO Circle Manage Metadata page](src/main/javadoc/doc-files/SSO-Circle-Metadata.png "Manager Metadata")

 c. Enter the FQDN of the ServiceProvider ex.: sp.cohos.de

 enter hostname for your tomcat machine

 d. enable the **LastName** checkbox:

 e. click on **Submit** button

 f. wait for the confirmation message (could take a couple of minutes)
 ![SSO Circle Successful Metadata Import page](src/main/javadoc/doc-files/SSO-Circle-Metadata-Success.png "Successful Import")

6. Return to the **Prepare fortress-saml-demo package** section of the [README.md](README.md) doc.