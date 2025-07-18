FROM mongo:6.0.2
COPY --chown=999 keyFile /etc/mongo/keyFile
RUN chmod 400 /etc/mongo/keyFile
COPY --chown=999 mongod.conf /etc/mongod.conf