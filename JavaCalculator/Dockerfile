from openjdk
COPY ./src/main/java/*.java /main/java/
WORKDIR /main/java
RUN javac Main.java Calculator.java
ENTRYPOINT ["java", "Main"]
