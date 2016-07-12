### TODO: Convert these notes into a proper how-to guide


0. logonto http://localhost:8080/sp

a. regenerate SP metadata

b. use fortress-saml-demo for entityid
c. use hostname & context in url:

note this is case sensitive!

This will not work:
http://IL1SCOLSP102:8080/fortress-saml-demo

This does work:
http://il1scolsp102:8080/fortress-saml-demo
1

1. cd src/main/resources/metadata

2. pull idp metadata:

wget --no-check-certificate https://IL1SCOLIDP102/idp/shibboleth -O idp-IL1SCOLIDP102-metadata.xml

wget --no-check-certificate https://idp.symas.com/idp/shibboleth -O idp-symas-com-metadata.xml

wget --no-check-certificate https://IL1SCOLIDP103/idp/shibboleth -O idp-IL1SCOLIDP103-metadata.xml

3. push sp metadata:

scp /home/smckinn/Development/fortress-saml-demo/src/main/resources/metadata/fortress-saml-demo-sp.xml root@IL1SCOLIDP102:/opt/shibboleth-idp/metadata

scp /home/smckinn/Development/fortress-saml-demo/src/main/resources/metadata/fortress-saml-demo-sp.xml root@idp.symas.com:/opt/shibboleth-idp/metadata

scp /usr/local/fortress/fortress-saml-demo/src/main/resources/metadata/fortress-saml-demo-sp.xml root@IL1SCOLIDP103:/opt/shibboleth-idp/metadata  

scp /home/smckinn/Development/fortress-saml-demo/src/main/resources/metadata/fortress-saml-demo-sp-il1scolsp102.xml root@IL1SCOLIDP103:/opt/shibboleth-idp/metadata/fortress-saml-demo-sp.xml  


4. update IdP Files:

a. relying-party.xml

add:

   <bean parent="RelyingPartyByName" c:relyingPartyIds="fortress-saml-demo">
          <property name="profileConfigurations">	
          <list>
          <!-- Your refs or beans here. -->
              <bean parent="SAML2.SSO" p:encryptAssertions="false" />
          </list>
          </property>
    </bean>


b. metadata-providers.xml

add:

  <MetadataProvider id="fortress-saml-demo"  xsi:type="FilesystemMetadataProvider" metadataFile="/opt/shibboleth-idp/metadata/fortress-saml-demo-sp.xml"/>

c. attribute-filter.xml

add:

    <afp:AttributeFilterPolicy id="fortress-demo">
        <afp:PolicyRequirementRule xsi:type="basic:AttributeRequesterString" value="fortress-saml-demo" />

        <afp:AttributeRule attributeID="uid">
            <afp:PermitValueRule xsi:type="basic:ANY" />
        </afp:AttributeRule>

        <afp:AttributeRule attributeID="mail">
            <afp:PermitValueRule xsi:type="basic:ANY" />
        </afp:AttributeRule>
    </afp:AttributeFilterPolicy>

d. to add new attributes in request, i.e. role assignments (work in progress)

edit:
attribute-resolver-ldap.xml, attribute-resolver.xml

5. restart idp

6. add metadata to sp side

Note:  Not needed with the caching metadata provider

a. edit securityContext.xml

b. add
                <bean class="org.opensaml.saml2.metadata.provider.HTTPMetadataProvider">
                    <constructor-arg>
                        <value type="java.lang.String">https://il1scolidp103/idp/shibboleth</value>
                    </constructor-arg>
                    <constructor-arg>
                        <value type="int">5000</value>
                    </constructor-arg>
                    <property name="parserPool" ref="parserPool"/>
                </bean>

only needed for http metadata provider:

7. add IdP Cert to SP trust store:

a. pull it down:

 ls -l /etc/ssl/certs/IL1SCOLIDP102.crt
 ls -l /etc/ssl/certs/IL1SCOLIDP103.crt

scp root@IL1SCOLIDP102:/etc/ssl/certs/IL1SCOLIDP102.crt /home/smckinn/Development/fortress-saml-demo/src/main/resources
scp root@IL1SCOLIDP103:/etc/ssl/certs/IL1SCOLIDP103.crt /usr/local/fortress/fortress-saml-demo/src/main/resources  

b. import:1

keytool -import -alias symas-idp -file /home/smckinn/Development/fortress-saml-demo/src/main/resources/IL1SCOLIDP102.crt -keystore cacerts -storepass changeit
 
keytool -import -alias symas-idp -file /home/smckinn/Development/fortress-saml-demo/src/main/resources/IL1SCOLIDP102.crt -keystore /home/smckinn/JavaTools/apache-tomcat-8.0.21/conf/mykeystore -storepass changeit

keytool -import -alias symas-idp -file /usr/local/fortress/fortress-saml-demo/src/main/resources/IL1SCOLIDP103.crt -keystore /usr/local/tomcat7/conf/mykeystore -storepass changeit

8. redeploy saml demo app

9. test

http://IL1SCOLSP102:8080/fortress-saml-demo

http://IL1SCOLSP102:8080/fortress-saml-demo
http://il1scolsp102:8080/fortress-saml-demo

# If using tomcat TLS, the URL in the SP metadata must be like this or ssocircle/shibboleth idp will reject (if like this https://il1scolsp102):
https://il1scolsp102:443/fortress-saml-demo


principal
AAdzZWNyZXQxlL3coTmKXEDXK8CjrdGVFHATLEQcVOYdnHLF1BLRtA+aKLVNwO/Skrmaok2f0IwVVxcwu075WFIo+AyhezhjlU6Q7+0qdRe85OWVjqUc1ZNJtiaGhlAVOOb/zWZ3o6AAtWruRg==


-------------------------------------------
Important Tip Troubleshooting Tip:
-------------------------------------------

Anytime the SP metadata is regenned, 

1. it has to be SCP over to IdP.  
2. Next IdP must be restarted.  
3. Next the IdP metadata must be repulled to SP with wget.  
4. Finally the SP must be redeployed.  
