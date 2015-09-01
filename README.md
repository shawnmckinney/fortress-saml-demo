# fortress-saml-sample README

 Last updated: August 31, 2015

 This document demonstrates how to build and deploy the fortress saml sample.

-------------------------------------------------------------------------------
## Prerequisites
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
## How to generate SP metadata and register with IdP ssocircle.com

1. Complete these steps first: [README-SPRING-SECURITY-SAML2-SAMPLE.md](README-SPRING-SECURITY-SAML2-SAMPLE.md)

2. Complete these steps next: [README-REGISTER-SSOCIRCLE.md](README-REGISTER-SSOCIRCLE.md)

## How to prepare fortress saml package

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
## How to build and deploy

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

 ## How to test with security enabled

 1. Here are the user to role assignments:

  ![wicket-sample security policy](src/main/javadoc/doc-files/wicket-sample-security-policy.gif)

 2. Open link to [http://localhost:8080/wicket-sample](http://localhost:8080/wicket-sample)

 3. Use the following creds:

  * wssuperuser/password
    ![SuperUser](src/main/javadoc/doc-files/Screenshot-wicket-sample-wssuperuser-small.png "Super User")


  * wsuser1/password
    ![WsUser1](src/main/javadoc/doc-files/Screenshot-wicket-sample-wsuser1-small.png "WsUser1")


  * wsuser2/password
    ![WsUser2](src/main/javadoc/doc-files/Screenshot-wicket-sample-wsuser2-small.png "WsUser2")


  * wsuser3/password
    ![WsUser3](src/main/javadoc/doc-files/Screenshot-wicket-sample-wsuser3-small.png "WsUser3")

 4. click on the page links

 5. click on the buttons

 6. Notice that security is now enabled, and how each user has different access rights.