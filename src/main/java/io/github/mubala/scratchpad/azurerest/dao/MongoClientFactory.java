package io.github.mubala.scratchpad.azurerest.dao;

import com.google.common.flogger.FluentLogger;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.netty.NettyStreamFactoryFactory;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Replaces;
import io.reactivex.Observable;

import javax.inject.Singleton;
import java.util.Arrays;

@Factory
public class MongoClientFactory {

  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  @Singleton
  @Bean(preDestroy = "close")
  @Replaces(MongoClient.class)
  public MongoClient mongoClient() {

    MongoClient mongoClient = null;
    try {

      // Formulate a ConnectionString based on data read using the encryptedStoreService
      String connectionString = "mongodb://";
      ServerAddress serverAddress = new ServerAddress(".mongo.cosmos.azure.com", 10255);
      ClusterSettings clusterSettings =
          ClusterSettings.builder().hosts(Arrays.asList(serverAddress)).build();
      MongoClientSettings.Builder settings =
          MongoClientSettings.builder()
              .applyToClusterSettings(build -> build.applySettings(clusterSettings));

      MongoCredential credential =
          MongoCredential.createCredential("test-db1", "testdb", "password==".toCharArray());
      settings.credential(credential);
      settings.streamFactoryFactory(NettyStreamFactoryFactory.builder().build());
      settings.applyToSslSettings(b -> b.enabled(true));

      logger.atInfo().log(" Settings Created  %s ", settings);
      mongoClient = MongoClients.create(settings.build());

      // logger.atInfo().log(" Created  %s ", mongoClient.toString());
      mongoClient = MongoClients.create(connectionString);
      Observable.fromPublisher(mongoClient.listDatabases())
          .blockingIterable()
          .forEach(System.out::println);
    } catch (Exception e) {
      logger.atSevere().log("--Error occurred %s", e);
      e.printStackTrace();
    }

    return mongoClient;
  }
}
