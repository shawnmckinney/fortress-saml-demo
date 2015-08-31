# fortress-saml-sample README-REGISTER-SSOCIRCLE

 Last updated: August 31, 2015

 This document demonstrates how to register for a new account with SSOCircle.com IdP.

-------------------------------------------------------------------------------
## Prerequisites
1. Completion [README-DEPLOY-SPRING-SAMPLE.md](README-DEPLOY-SPRING-SAMPLE.md)

-------------------------------------------------------------------------------

## How to enable a new account on SSOCircle.com


1. goto website:

[http://www.ssocircle.com](SSO Circle IdP)

2. click on Signin/Register->Register

3. fill in the info:

 ![SSO Circle Registration page](src/main/javadoc/doc-files/SSO-Circle-Registration.png "java EE loging page")
![SSO Circle Registration page](src/main/javadoc/doc-files/SSO-Circle-Registration.png "Super User")

User Name [a-zA-Z.-]:
this is the userid you will use to log into the IdP

Required Field Password - at least 8 characters:
this is the password you will use to log into the IdP

Required Field First Name:
enter anything

Required Field Last Name:
enter: sssuperuser

note: this field maps to the user name in fortress for security.  For now use 'sssuperuser'

Required Field Full Name:
enter anything you want here

Required Field Email Address:
this email address must be valid.  it is needed for registration confirmation later.

4. complete the registration

5. login into SSOCircle.com IdP

6. click on 'Manager Metadata'

7. click on 'Add new Service Provider'

8. Enter the FQDN of the ServiceProvider ex.: sp.cohos.de

enter hostname for your tomcat machine

9. enable the 'LastName' checkboxe:

10. click submit
