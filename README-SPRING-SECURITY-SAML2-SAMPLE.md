# fortress-saml-sample README-SPRING-SECURITY0SAML2-SAMPLE

 Last updated: August 31, 2015

 This document demonstrates how to download and install a spring saml security sample.

  It is used to generate metadata needed to register the spring saml SP with IdP.

-------------------------------------------------------------------------------

## Prerequisites
1. Java 7 (or greater) sdk
2. Gradle
3. Tomcat7

-------------------------------------------------------------------------------

1. download package:

 [https://github.com/UniconLabs/shibboleth-sample-java-sp/archive/master.zip](SSO Circle IdP)

2. extract:

3. replace in securityContext.xml:

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

4. add to sp.properties:

 idp.metadata=/home/smckinn/tmp/spring-saml/1/shibboleth-sample-java-sp-master/idp-metadata.xml

5. add corresponding file to folder

6. deploy (follow readme.md, run the build and deploy in shibboleth-sample-java-sp package )

7. hit the spring sample app:

 http://hostname:8080/sp

8. Click on 'SAML LoginMetadata Administration'

9. Accept defaults and click on 'Login' button

10. Click on 'Generate new service provider metadata ' button

11. Entity Id

 enter a unique value

 foo-fighters

12. Entity Base URL:

 http://host-name:8080/fortress-saml-demo

 where host-name corresponds with your machine host name

13. Click on 'Generate Metadata' button at the bottom of the page

14. Copy the buffer

15. Save it to file.  Will need it later during README-REGISTER-SSOCIRCLE.md
