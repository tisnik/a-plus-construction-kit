# a+construction-kit

Interactive visual designer for cloud-native applications.

## Motivation

Let's suppose that developer need to create and deploy simple web service to
cloud. The implementation of that service might take some time, let's say one
day. We could argue that developer's work is done at this point.

The problem, however, is that many other things needs to be finished to
successfully deploy such web service. Namely:

- Interfaces to data storage or storages needs to be configured properly.
- Interfaces to streaming tools usually needs to be configured too.
- Dtto if the web service has to talk with other services via some message broker.
- Logging needs to be configured.
- Alerts needs to be configured.
- Dashboard or with service metrics needs to be created.
- Connection to tracking tool.
- CI/CD setup.
- And the service itself needs to be deployed to selected cloud, of course.

For smaller web services (and we live in microservices world after all) it
might mean, that time to deliver the service might be five to ten times longer
than the solution (algorithm, source code) itself!

Also, many project setup and configuration is done by copy&pasting old code
from "somewhere". It would be much better to have a more intelligent solution.

Clearly there's space for a tool that take care of all those "minor" tasks that
have to be done.

!(https://github.com/tisnik/a-plus-construction-kit/blob/master/www/code_is_not_all.png)


## Solution

The problem can be split into two parts:

1. Code itself.
1. Configuration, interfaces to external services, and deployment.

The first part can not be automated, but the second part can be fully created by computer, based on provided "schema".

The "schema" is basically architecture of service or microservice created by user:

The following things can be created automatically based on schema provided:

- Project stub for selected language
- Dependency setup for selected language
- Interfaces to storage, streaming platforms, message brokers
- Interfaces to other systems (Prometheus/Grafana etc.)
- Deployment configuration
- Deployment scripts


## Look

!(https://github.com/tisnik/a-plus-construction-kit/blob/master/www/schema.png)

## Installation

```bash
git clone https://github.com/tisnik/a-plus-construction-kit
```

## Usage

### When Clojure+Leiningen are installed

```bash
lein run -- $@
```

### When just JRE is installed and uberjar is prepared

```bash
java -cp ack.jar ack.core
```

### Uberjar preparation

```bash
lein uberjar
mv target/uberjar/ack-0.1.0-SNAPSHOT-standalone.jar ack.jar
```

### Development mode

In this case changes in sources are immediatelly made live.

```bash
lein ring server-headless
```



### TODO

1. Model for frontend-backend application
1. Graphical editor for frontend-backend application
1. Add missing Go libraries into the model
1. Add missing Python libraries into the model


### Known bugs

...


## License

Copyright © 2018-2023 Pavel Tišnovský

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
