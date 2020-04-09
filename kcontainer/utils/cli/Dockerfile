FROM node:12

WORKDIR "/tools"
ADD . /tools
#GITSECURE REMEDIATION 
RUN  apt-get update && apt-get install -y --fix-missing  

ENV PATH="/tools:${PATH}"

ENTRYPOINT ["bash"]

