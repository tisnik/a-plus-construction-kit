# a+construction-kit

Interactive visual designer for cloud-native applications.

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
