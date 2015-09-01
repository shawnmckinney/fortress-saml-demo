# Overview of the fortress-saml-sample README

 * This document demonstrates how to build and deploy the fortress saml sample.
 * It builds on two excellent references:
  * [spring-security-saml](https://github.com/spring-projects/spring-security-saml) - Spring's SAML sample is the first place java developers should look for basic SAML 2.0 programming concepts..
  * [shibboleth-sample-java-sp](https://github.com/UniconLabs/shibboleth-sample-java-sp) - Unicon's sample is where ones goes to understand how to combine Spring SAML with Shibboleth IdP.
 * This sample is a third step.  It shows how to hook Apache Directory Fortress in with a common Identity Provider which provides SSO as-a-service.  This allows us to
 bypass the complexities of a full IdP setup in order to focus on the Service Provider side.
 * This project was not meant to illustrate the various SAML 2.0 use cases.  It does show how Apache Directory Fortress can be combined with a simple SAML use case and Spring Security.
 * Here we use the Apache Wicket web framework. To learn the details of combining Apache Wicket and Fortress, check out:
 [wicket-sample](https://github.com/shawnmckinney/wicket-sample)

-------------------------------------------------------------------------------
## fortress-sample-sample prerequisites
1. Java 7 (or greater) sdk
2. Git
3. Apache Maven 3
4. Completion of these steps under the [Apache Fortress Ten Minute Guide](http://directory.apache.org/fortress/gen-docs/latest/apidocs/org/apache/directory/fortress/core/doc-files/ten-minute-guide.html):
    * [Setup Apache Directory Server](http://directory.apache.org/fortress/gen-docs/latest/apidocs/org/apache/directory/fortress/core/doc-files/apache-directory-server.html)
    * [Setup Apache Directory Studio](http://directory.apache.org/fortress/gen-docs/latest/apidocs/org/apache/directory/fortress/core/doc-files/apache-directory-studio.html)
    * [Build Apache Fortress Core](http://directory.apache.org/fortress/gen-docs/latest/apidocs/org/apache/directory/fortress/core/doc-files/apache-fortress-core.html)
    * [Build Apache Fortress Realm](http://directory.apache.org/fortress/gen-docs/latest/apidocs/org/apache/directory/fortress/core/doc-files/apache-fortress-realm.html)
    * [Setup Apache Tomcat Web Server](http://directory.apache.org/fortress/gen-docs/latest/apidocs/org/apache/directory/fortress/core/doc-files/apache-tomcat.html)
    * [Build Apache Fortress Web](http://directory.apache.org/fortress/gen-docs/latest/apidocs/org/apache/directory/fortress/core/doc-files/apache-fortress-web.html)

-------------------------------------------------------------------------------
## Generate SP metadata and register with IdP ssocircle.com

1. Complete these steps first: [SPRING-SECURITY-SAML2-SAMPLE.md](SPRING-SECURITY-SAML2-SAMPLE.md)

2. Complete these steps next: [REGISTER-SSOCIRCLE.md](REGISTER-SSOCIRCLE.md)

## Prepare fortress-saml-sample package

1. [Download ZIP](https://github.com/shawnmckinney/fortress-saml-sample/archive/master.zip)

2. Extract the zip archive to your local machine.

3. cd fortress-saml-sample-master

4. Rename [fortress.properties.example](src/main/resources/fortress.properties.example) to fortress.properties.

 Prepare fortress for ldap server usage.

 After completing the fortress ten minute guide, this step should be familiar to you.  It is how the fortress runtime gets hooked in with a remote ldap server.
 ```properties
# This param tells fortress what type of ldap server in use:
ldap.server.type=apacheds

# Use value from [Set Hostname Entry]:
host=localhost

# if ApacheDS is listening on TLS port:
port=10389

# These credentials are used for read/write access to all nodes under suffix:
admin.user=uid=admin,ou=system
admin.pw=secret

# This is min/max settings for LDAP administrator pool connections that have read/write access to all nodes under suffix:
min.admin.conn=1
max.admin.conn=10

# This node contains fortress properties stored on behalf of connecting LDAP clients:
config.realm=DEFAULT
config.root=ou=Config,dc=example,dc=com

# Used by application security components:
perms.cached=true

# Fortress uses a cache:
ehcache.config.file=ehcache.xml
 ```

-------------------------------------------------------------------------------
## Build and deploy fortress-saml-sample

1. Set java and maven home env variables.

2. Run this command from the root package:

  Deploy to tomcat server:
  ```maven
 mvn clean tomcat:deploy -Dload.file
  ```

  Or if already deployed:
  ```maven
 mvn clean tomcat:redeploy -Dload.file
  ```

   -Dload.file tells maven to also load the wicket sample security policy into ldap.  Since the load needs to happen just once, you may drop it from future ops:
  ```maven
 mvn tomcat:redeploy
  ```
 *Note: if problem  with auto-deploy, manually deploy wicket-sample.war to webapps*

-------------------------------------------------------------------------------

## Test fortress security with spring saml sso enabled

 1. Here are the user to role assignments:
 ![fortress-saml-sample security policy](src/main/javadoc/doc-files/fortress-saml-sample-security-policy.xml)

 2. Open link to [http://localhost:8080/fortress-saml-sample](http://localhost:8080/fortress-saml-sample)

 3. You will be rerouted to IdP.  Enter the **User Name**, **Password** values from [REGISTER-SSOCIRCLE.md](REGISTER-SSOCIRCLE.md).
 ![IdP Login Page](src/main/javadoc/doc-files/SSO-Circle-IdP-Login-Page.png "IdP Login Page")
 Click on **Continue SAML Single Sign On** button after the bot checks.

 4. If everything works, you will be redirected to the fortress-saml-sample web page.  The sam* user should see the following:
 ![sam*](src/main/javadoc/doc-files/Fortress-Saml-Demo-SuperUser.png "Home Page - sam*")

 5. Try a different user.
  * Map to different fortress users at [**MY Profile**](https://idp.ssocircle.com/sso/hos/SelfCare.jsp) at ssocircle.com.
  * Enter a new value for **Surname**.
  * Use one of these: sam1, sam2, sam3 or sam*.
  * Enter IdP password and click on **Submit** to save and continue.
  ![User Profile Page](src/main/javadoc/doc-files/SSO-Circle-Change-Sam1-User.png "User Profile Page")
  * Delete the cookies from browser corresponding with the IdP and SP websites.
  * Perform login sequence again.  Now the home page will have different authorizations, pertaining to fortress userId mapped to IdP login.
  ![sam1](src/main/javadoc/doc-files/Fortress-Saml-User1-Page.png "Home Page - sam1")

 6. Each fortress userId (mapped to **Last Name** field at IdP) has different access policy.
  * sam1 - access to page one
  * sam2 - access to page two
  * sam3 - access to page three
  * sam* - access to all pages