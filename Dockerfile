FROM iotrmcninfctreg001.azurecr.cn/otr/jdk:8
MAINTAINER zzzhao <zzzhao@thoughtworks.com>

COPY build/libs/actorsdemo.jar /app/actorsdemo.jar

WORKDIR /app

CMD ["-jar", "actorsdemo.jar"]
