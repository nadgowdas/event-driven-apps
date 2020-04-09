FROM node:12 as builder

WORKDIR "/app"
ADD . /app
USER root   
RUN   cd ui \
      && npm install \
      && npm install -g @angular/cli \
      && ng build \
      && cd ../server \
      && npm install \
#GITSECURE REMEDIATION 
RUN  apk update && apk add  


#GITSECURE REMEDIATION 
RUN  apt-get update && apt-get install -y --fix-missing  

MAINTAINER https://github.com/ibm-cloud-architecture - IBM - Jerome Boyer

WORKDIR /kc-ui/server
COPY --from=builder /app ..

ENV PORT 3000
EXPOSE 3000

CMD ["node", "dist/server/server.js"]
