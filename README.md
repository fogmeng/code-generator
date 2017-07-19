# code-generator
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0) [![Release Version](https://img.shields.io/badge/release-1.0.0-red.svg)](https://github.com/TiFG/code-generator/releases) [![Build Status](https://travis-ci.org/TiFG/code-generator.svg?branch=master)](https://travis-ci.org/TiFG/code-generator)

## Overview
A simple java code generator library.

## Requirements
The minimum requirements to run the quick start are:
* JDK 1.7 or above
* A java-based project management software like [Maven](https://maven.apache.org/) or [Gradle](http://gradle.org/)

## Quick Start

### 1. maven dependency
```
<dependency>
	<groupId>com.mindflow</groupId>
	<artifactId>code-generator</artifactId>
	<version>1.0.0</version>
</dependency>
```

### 2. demo code
```
    Lemon lemon = new Lemon.Builder()
            .srcPath(new File("/path/src"))
            .build();

    public void run() throws IOException {

        Request request = new Request.Builder()
                .className("com.mindflow.User")
                .addInterface(Serializable.class.getName())
                .addField(new FieldVO(FieldType.Long, "id"))
                .addField(new FieldVO(FieldType.String, "name"))
                .addField(new FieldVO(FieldType.Integer, "age"))
                .addField(new FieldVO(FieldType.Date, "birthday"))
                .addField(new FieldVO(FieldType.Boolean, "gender"))
                .build();

        Response response = lemon.execute(request);
        System.out.println(response.getCode());
        System.out.println(response.getPath());
    }

```