# YaMvp

[![Build Status](https://travis-ci.org/Piasy/YaMvp.svg?branch=master)](https://travis-ci.org/Piasy/YaMvp) [![codecov](https://codecov.io/gh/Piasy/YaMvp/branch/master/graph/badge.svg)](https://codecov.io/gh/Piasy/YaMvp)
 [ ![Download](https://api.bintray.com/packages/piasy/maven/YaMvp/images/download.svg) ](https://bintray.com/piasy/maven/YaMvp/_latestVersion)

Yet another MVP library. Super simple, but with enough functionality.

## Demo

![demo](art/yamvp-example.gif)

## Modules

``` gradle
allprojects {
    repositories {
        maven {
            url  "http://dl.bintray.com/piasy/maven"
        }
    }
}
```

### YaMvp

The core MVP part, only 3 classes, less than 100 lines code.

+ Presenter is not an interface
+ YaViewDelegate: Composition over inheritance

``` gradle
compile 'com.github.piasy:YaMvp:1.3.1'
```

### YaMvp-Rx

RxJava Subscription management.

``` gradle
compile 'com.github.piasy:YaMvp-Rx:1.3.1'
```

+ YaRxDelegate: Composition over inheritance

### YaMvp-Component

If you want inheritance, here you are.

``` gradle
compile 'com.github.piasy:YaMvp-Component:1.3.1'
```

### YaMvp-Dagger2

And if you want Dagger2 integrated too, here you are.

``` gradle
compile 'com.github.piasy:YaMvp-Dagger2:1.3.1'
```

## Test code showcase

Please see each module's test code, it's cool.

+ JUnit test
+ Android JUnit test
+ Espresso test recording

## Disclaim

Activity/Fragment kill and restore are not considered yet.
