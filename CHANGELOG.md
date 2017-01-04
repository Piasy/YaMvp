# Change log

+ v1.3.1
  - Update deps;
  - Generate dagger2 MemberInjector here avoid lib users get "Note: Generating a MembersInjector for com.github.piasy.yamvp.dagger2.YaMvpDiFragment. Prefer to run the dagger processor over that class instead."
+ v1.3.0
  - Remove integration for RxJava 1.x.
  - DI should not be bound with MVP.
+ v1.2.0
  - Add support for RxJava 2.x.
+ v1.1.0
  - Fix NPE during activity recreation, e.g. configuration changes, activity killed by system.
+ v1.0.1
  - `YaMvpDiFragment`: change `mPresenter` from `private` into `protected`.
