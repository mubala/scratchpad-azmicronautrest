package io.github.mubala.scratchpad.azurerest.service;

import io.github.mubala.scratchpad.azurerest.dao.UserProfileStore;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.reactivex.Observable;

@Controller("/")
public class UserProfileServiceController {

  UserProfileStore profileStore;

  public UserProfileServiceController() {
    profileStore = new UserProfileStore();
  }

  @Post("/databases")
  public Observable<String> listDb() {
    return Observable.fromPublisher(profileStore.getDatabaseNames());
  }
}
