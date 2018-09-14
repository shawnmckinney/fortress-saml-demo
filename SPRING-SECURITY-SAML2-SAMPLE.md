# fortress-saml-sample SPRING-SECURITY-SAML2-SAMPLE

 This document describes how to download and install Unicon's [shibboleth-sample-java-sp](https://github.com/UniconLabs/shibboleth-sample-java-sp) sample.

 This document also describes how to use the shibboleth-sample-java-sp app to generate metadata needed later to register the fortress-saml-demo *Service Provider* with the ssocircle.com *Identity Provider*.

 Alternatively, the [spring-security-saml-sample](https://github.com/spring-projects/spring-security-saml/tree/master/sample) may be used to do the same thing (generate SP metadata) but those steps are not here.

-------------------------------------------------------------------------------

## Prerequisites
1. Java 8 sdk
2. Gradle - to build shibboleth-sample-java-sp
3. Tomcat8 - to deploy shibboleth-sample-java-sp

-------------------------------------------------------------------------------

#### 1. Download package and extract:

 [shibboleth-sample-java-sp](https://github.com/UniconLabs/shibboleth-sample-java-sp/archive/master.zip)

#### 2. Edit securityContext.xml file:

 ![securityContext.xml](https://github.com/UniconLabs/shibboleth-sample-java-sp/blob/master/src/main/webapp/WEB-INF/securityContext.xml) file, replace the **metadata** bean declaration with:

 ```
 <bean id="metadata" class="org.springframework.security.saml.metadata.CachingMetadataManager">
    <constructor-arg>
        <list>
            <bean class="org.opensaml.saml2.metadata.provider.HTTPMetadataProvider">
                <constructor-arg>
                    <value type="java.lang.String">http://idp.ssocircle.com/idp-meta.xml</value>
                </constructor-arg>
                <constructor-arg>
                    <value type="int">5000</value>
                </constructor-arg>
                <property name="parserPool" ref="parserPool"/>
            </bean>
        </list>
    </constructor-arg>
 </bean>
 ```

#### 3. Pull down IdP metadata from ssocircle.com

 ```
 wget http://idp.ssocircle.com/idp-meta.xml -o /tmp/idp-metadata.xml
 ```

#### 4. Edit the sp.properties file:

 ![sp.properties](https://github.com/UniconLabs/shibboleth-sample-java-sp/blob/master/src/main/webapp/WEB-INF/sp.properties) file.
 replace the *idp.metadata* tag with a pointer to file just downloaded to local harddrive:

 ```
 idp.metadata=/tmp/idp-metadata.xml
 ```

#### 5. Deploy shibboleth-sample-java-sp package
 follow the ![README.md](https://github.com/UniconLabs/shibboleth-sample-java-sp/blob/master/README.md) steps to build and deploy.

#### 6. Pull up the shibboleth-sample-java-sp home page in the browser:

 a. Use this URL: [http://localhost:8080/sp](Spring Saml Landing Page)
 b. Looks like this:

 ![Spring Saml Landing page](src/main/javadoc/doc-files/Spring-Saml-Landing-Page.png "Landing Page")

#### 7. Click on **Metadata Administration** link.

#### 8. Accept default uid/password, and click on **Login** button

 ![Spring Saml Login page](src/main/javadoc/doc-files/Spring-Saml-Login-Page.png "Login Page")

#### 9. Click on **Generate new service provider metadata** button

 ![Spring Saml Generate SP Metadata page](src/main/javadoc/doc-files/Spring-Saml-Generate-Metadata.png "Generate SP Metadata")

#### 10. Entity Id

 enter a unique value

 e.g. *fortress-saml-demo*

 **Remember this value**  It is used inside the ![securityContext.xml](src/main/webapp/WEB-INF/securityContext.xml) file during the [fortress-saml-demo](README.md) setup and links SP with the IdP.

#### 11. Entity Base URL:

 e.g. *http://host-name:8080/fortress-saml-demo*  (where **host-name** corresponds with your machine's host name)

 **Remember this value**: The host-name will be entered during the [REGISTER-SSOCIRCLE.md](REGISTER-SSOCIRCLE.md) setup.

 ![Spring Saml Generate SP Metadata page](https://github.com/shawnmckinney/fortress-saml-demo/blob/master/src/main/javadoc/doc-files/Spring-Saml-Metadata-Generation-Page.png "Generate SP Metadata Page")

#### 12. Click on **Generate Metadata** button at the bottom of the page

 ![Spring Saml Generate SP Metadata button](https://github.com/shawnmckinney/fortress-saml-demo/blob/master/src/main/javadoc/doc-files/Spring-Saml-Generate-Metadata-Button.png "Generate SP Metadata Button")

#### 13. Copy the buffer

 ![Spring Saml Generate SP Metadata copy](https://github.com/shawnmckinney/fortress-saml-demo/blob/master/src/main/javadoc/doc-files/Spring-Saml-Copy-Metadata.png "Generate SP Metadata Copy")

#### 14. Create a new file:

 Save it with an '.xml' extension.  We'll need it later on during the [REGISTER-SSOCIRCLE.md](REGISTER-SSOCIRCLE.md) steps.

 **Save this file** It is needed during the [REGISTER-SSOCIRCLE.md](REGISTER-SSOCIRCLE.md) setup.

#### 15. Complete the steps under

 **How to enable a new account on SSOCircle.com** section of the [REGISTER-SSOCIRCLE.md](REGISTER-SSOCIRCLE.md) doc.