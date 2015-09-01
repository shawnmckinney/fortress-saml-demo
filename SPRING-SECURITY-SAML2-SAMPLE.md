# fortress-saml-sample SPRING-SECURITY-SAML2-SAMPLE

 Last updated: August 31, 2015

 This document demonstrates how to download and install shibboleth-sample-java-sp

 It is used here to generate metadata to register fortress-saml-sample service provider with the ssocircle.com Identity Provider.

-------------------------------------------------------------------------------

## Prerequisites
1. Java 7 (or greater) sdk
2. Gradle - to build shibboleth-sample-java-sp
3. Tomcat7 - to deploy shibboleth-sample-java-sp

-------------------------------------------------------------------------------

1. download package:

 [https://github.com/UniconLabs/shibboleth-sample-java-sp/archive/master.zip](SSO Circle IdP)

2. extract:

3. replace in securityContext.xml:
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
4. add to sp.properties:

 idp.metadata=/home/smckinn/tmp/spring-saml/1/shibboleth-sample-java-sp-master/idp-metadata.xml

5. add corresponding file to folder

6. deploy (follow readme.md, run the build and deploy in shibboleth-sample-java-sp package )

7. hit the spring sample app:

 [http://hostname:8080/sp](Spring Saml Landing Page)

 ![Spring Saml Landing page](src/main/javadoc/doc-files/Spring-Saml-Landing-Page.png "Landing Page")

8. Click on 'Metadata Administration'

9. Accept defaults and click on 'Login' button

 ![Spring Saml Login page](src/main/javadoc/doc-files/Spring-Saml-Login-Page.png "Login Page")

10. Click on 'Generate new service provider metadata ' button

 ![Spring Saml Generate SP Metadata page](src/main/javadoc/doc-files/Spring-Saml-Generate-Metadata.png "Generate SP Metadata")

11. Entity Id

 enter a unique value

 e.g. foofighters

12. Entity Base URL:

 e.g. http://host-name:8080/fortress-saml-demo

 where 'host-name' corresponds with your machine's host name

 ![Spring Saml Generate SP Metadata page](src/main/javadoc/doc-files/Spring-Saml-Metadata-Generation-Page.png "Generate SP Metadata Page")

13. Click on 'Generate Metadata' button at the bottom of the page

 ![Spring Saml Generate SP Metadata button](src/main/javadoc/doc-files/Spring-Saml-Generate-Metadata-Button.png "Generate SP Metadata Button")

14. Copy the buffer

 ![Spring Saml Generate SP Metadata copy](src/main/javadoc/doc-files/Spring-Saml-Copy-Metadata.png "Generate SP Metadata Copy")

15. Save it with an '.xml' extension.  We'll need it later on during [REGISTER-SSOCIRCLE.md](REGISTER-SSOCIRCLE.md)