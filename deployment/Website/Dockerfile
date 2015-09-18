FROM debian
RUN su root \
  && echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" > /etc/apt/sources.list.d/webupd8team-java.list \
  && echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" >> /etc/apt/sources.list.d/webupd8team-java.list \
  && apt-key adv --keyserver keyserver.ubuntu.com --recv-keys EEA14886 \
  && apt-get update \
  && echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections \
  && echo debconf shared/accepted-oracle-license-v1-1 seen true | debconf-set-selections \
  && apt-get install -y oracle-java8-installer \
  && java -version \
  && adduser \
	  --system \
	  --shell /bin/bash \
	  --gecos 'Tomcat Java Servlet and JSP engine' \
	  --group \
	  --disabled-password \
	  --home /home/tomcat \
	  tomcat \
  && mkdir -p ~/tmp \
  && cd ~/tmp \
  && wget http://www.us.apache.org/dist/tomcat/tomcat-8/v8.0.26/bin/apache-tomcat-8.0.26.tar.gz \
  && tar xvzf ./apache-tomcat-8.0.26.tar.gz \
  && rm ./apache-tomcat-8.0.26.tar.gz \
  && mkdir -p /usr/share/tomcat8 \
  && mv ~/tmp/apache-tomcat-8.0.26 /usr/share/tomcat8 \
  && rm -f /usr/share/tomcat \
  && ln -s /usr/share/tomcat8/apache-tomcat-8.0.26 /usr/share/tomcat \
  && chown -R tomcat:tomcat /usr/share/tomcat8 \
  && chmod +x /usr/share/tomcat/bin/*.sh \
  && mkdir -p /usr/share/tomcat/webapps/subscriptionsviewer/

COPY subscribers/ /usr/share/tomcat/webapps/subscribers/

EXPOSE 80 443 8080

ENTRYPOINT ["/usr/share/tomcat/bin/catalina.sh", "run"]