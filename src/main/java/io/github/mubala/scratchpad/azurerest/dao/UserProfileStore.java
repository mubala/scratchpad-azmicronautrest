package io.github.mubala.scratchpad.azurerest.dao;

import com.mongodb.reactivestreams.client.MongoClient;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class UserProfileStore {

  private final MongoClient mongoClient;

  public UserProfileStore() {
    this.mongoClient = new MongoClientFactory().mongoClient();
  }

  public Publisher<String> getDatabaseNames() {
    Subscriber<? super String> t =
        new Subscriber<String>() {
          @Override
          public void onSubscribe(Subscription s) {
            System.out.println("on sub");
          }

          @Override
          public void onNext(String s) {
            System.out.println(s + "  ");
          }

          @Override
          public void onError(Throwable t) {
            System.out.println(t + " Error  ");
          }

          @Override
          public void onComplete() {}
        };
    System.out.println(mongoClient);
    return mongoClient.listDatabaseNames();
    // return null ;
    /*
    return mongoClient
            .getDatabase("testdb")
            .getCollection("user", UserProfile.class);*/
  }
}
